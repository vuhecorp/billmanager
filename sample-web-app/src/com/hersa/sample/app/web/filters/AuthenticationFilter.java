package com.hersa.sample.app.web.filters;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hersa.sample.app.bom.user.User;
import com.hersa.sample.app.web.SessionContext;

/**
 * Servlet Filter implementation class AuthenticationFilter
 */
@WebFilter(
		dispatcherTypes = {DispatcherType.REQUEST }
					, 
		urlPatterns = { 
				"/AuthenticationFilter", 
				"/pages/*"
		})
public class AuthenticationFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AuthenticationFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest  = (HttpServletRequest) request;
		User user = (User) httpRequest.getSession().getAttribute(SessionContext.SESSION_USER);
		if (user == null || !user.isAuthenticated()) {
			HttpServletResponse httpResponse = (HttpServletResponse) response;
			String remoteIp = httpRequest.getRemoteAddr();
			String requestURI = httpRequest.getRequestURI();
			System.out.println("Remote IP Address: " + remoteIp + " has attempted an unauthorized request to resource " + requestURI );
			httpResponse.sendRedirect("../error.xhtml");
			
		}else {
			chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
