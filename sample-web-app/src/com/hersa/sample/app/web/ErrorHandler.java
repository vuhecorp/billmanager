package com.hersa.sample.app.web;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class ErrorHandler {

	public String getStatusCode() {
		String val = String.valueOf((Integer) FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
				.get("javax.servlet.error.status_code"));
		return val;
	}

	public String getMessage() {
		String val = "";
		try {
			val = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
					.get("javax.servlet.error.message");
		} catch (Exception e) {
			val = "An unhandled error occurred";
		}

		return val;
	}

	public String getExceptionType() {
		String val = FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
				.get("javax.servlet.error.exception_type").toString();
		return val;
	}

	public String getException() {
		String val = "";
		try {
			val = (String) ((Exception) FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
					.get("javax.servlet.error.exception")).toString();
		} catch (Exception e) {
			val = "No exception information available";
		}
		return val;
	}

	public String getRequestURI() {
		return (String) FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
				.get("javax.servlet.error.request_uri");
	}

	public String getServletName() {
		return (String) FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
				.get("javax.servlet.error.servlet_name");
	}
}
