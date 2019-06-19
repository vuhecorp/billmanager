package com.hersa.sample.app.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.hersa.sample.app.bom.user.User;

public class AbstractFacesPage {

	@ManagedProperty(value = "#{applicationContext}")
	private ApplicationContext applicationContext;
	protected String pageTitle;
	
	protected User sessionUser;
	
	public AbstractFacesPage() {
		
	}
	
	public void onPageLoad() {
		sessionUser = SessionContext.getSessionUser();
	}
	
	public String addApplicationItem() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = sdf.format(new Date());
		applicationContext.putApplicationItem(date, date);
		return "";
	}
	
	public int getApplicationItemCount() {
		return applicationContext.getApplicationMapSize();
	}
	
	public FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}
	
	public ExternalContext getExternalContext() {
		return this.getFacesContext().getExternalContext();
	}
	
	public void addErrorMessage(String message) {
		this.getFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", message));
	}
	
	public HttpSession getHttpSession() {
		HttpSession httpSession = this.getHttpServletRequest().getSession();
		return httpSession;
	}
	
	public Map<String, Object> getSessionMap() {
		return this.getExternalContext().getSessionMap();
	}
	
	public HttpServletRequest getHttpServletRequest() {
		HttpServletRequest request = (HttpServletRequest) this.getExternalContext().getRequest();
		return request;
	}
	/* ================
	 * Getter / Setters 
	 * ================*/
	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	public String getPageTitle() {
		return pageTitle;
	}

	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}

	public User getSessionUser() {
		return sessionUser;
	}

	public void setSessionUser(User sessionUser) {
		this.sessionUser = sessionUser;
	}
}
