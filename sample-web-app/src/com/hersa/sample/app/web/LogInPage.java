package com.hersa.sample.app.web;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.hersa.sample.app.bom.user.User;

@ManagedBean
@ViewScoped
public class LogInPage extends AbstractFacesPage implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8531007259148938731L;

	private String userName;
	private String password;
	
	public LogInPage() {
		super();
		super.pageTitle = "Log In";
	}
	
	@PostConstruct
	public void onPageLoad() {
		super.onPageLoad();
	}
	
	public String onLoginBtnClick(){
		String response = "pages/dashboard?faces-redirect=true";
		try {
			
			if(("vuhernandez".equalsIgnoreCase(userName) || "nhernandez".equalsIgnoreCase(userName)) && "hamer123".equalsIgnoreCase(password)) {
				User sessionUser = new User();
				sessionUser.setUserName(userName.toUpperCase());
				sessionUser.setAuthenticated(true);
				sessionUser.setAdmin(true);
				SessionContext.setSessionUser(sessionUser);
				
			}else {
				this.addErrorMessage("Invalid Credentials");
				response = "";
			}
			
		} catch (Exception e){   
			response = "";
			e.printStackTrace();
			this.addErrorMessage(e.getMessage());
		}
		return response;
	}
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
