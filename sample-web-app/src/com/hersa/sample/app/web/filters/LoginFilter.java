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

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter(
		dispatcherTypes = {DispatcherType.REQUEST }
					, 
		urlPatterns = { 
				"/LoginFilter"
		})
public class LoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LoginFilter() {
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
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		
		String remoteHost   = httpRequest.getRemoteHost();
		String remoteUser   = httpRequest.getRemoteUser();
		String userAgent    = httpRequest.getHeader("User-Agent");
		String referer      = httpRequest.getHeader("referer");
		String requestedURI = httpRequest.getRequestURI(); 
		
		System.out.println("--------------------------");
		System.out.println("Incoming request:");
		System.out.println("Host: "    + remoteHost);
		System.out.println("User: "    + remoteUser);
		System.out.println("Browser: " + userAgent);
		System.out.println("Referer: " + referer);
		System.out.println("RequestedURI: " + requestedURI);
		System.out.println("--------------------------");
		
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
