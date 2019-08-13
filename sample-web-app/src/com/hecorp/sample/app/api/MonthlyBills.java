package com.hecorp.sample.app.api;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.hersa.sample.app.bom.billitemtemplate.BillItemTemplate;
import com.hersa.sample.app.bom.billitemtemplate.BillItemTemplateManager;

/**
 * Servlet implementation class MonthlyBills
 */
@WebServlet("/MonthlyBills")
public class MonthlyBills extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
    public static final String PARAM_USERNAME = "request.param.user.name";   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MonthlyBills() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String jsonResponse = "";
		try {
			String userName = request.getParameter("user");
			
			if (StringUtils.isEmpty(userName)) {
				throw new Exception("Invalid or missing username");
			}
			
			String ipAddress = request.getRemoteAddr();
			
			System.out.println("---------------------------------------------------------------------------");
			System.out.println("IP: " + ipAddress + " has requested data for user " + userName.toUpperCase());
			
			BillItemTemplateManager bitm = new BillItemTemplateManager();
			List<BillItemTemplate> billItemTemplates = bitm.retrieveAllBillItemTemplatesByUserName(userName.toUpperCase());
			
			Gson gson = new Gson();
			
			jsonResponse = gson.toJson(new BillResponse(billItemTemplates));
		} catch (Exception e) {
			e.printStackTrace();
			jsonResponse  = e.getMessage();
		}
		
		response.getWriter().append(jsonResponse);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
