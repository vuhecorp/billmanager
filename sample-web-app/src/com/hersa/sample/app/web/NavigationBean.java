package com.hersa.sample.app.web;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.HttpSession;

@ManagedBean
@ViewScoped
public class NavigationBean extends AbstractFacesPage implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2694656189949799086L;

	public NavigationBean() {
		super();
	}
	
	@PostConstruct
	public void onPageLoad() {
		super.onPageLoad();
	}
	
	public String doSignOff() {
		HttpSession session = SessionContext.getSession();
		session.invalidate();
		return "loginPage";
	}
	
}
