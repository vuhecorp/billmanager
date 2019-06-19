package com.hersa.sample.app.web;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class MyBillsPage extends AbstractFacesPage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2635878856409734237L;

	public MyBillsPage() {
		super();
		pageTitle = "My Bills";
	}
	
	@PostConstruct
	public void onPageLoad() {
		super.onPageLoad();
	}
}
