package com.hersa.sample.app.web;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class DashBoardPage extends AbstractFacesPage implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3220200650323542358L;
	
	public DashBoardPage() {
		super();
		pageTitle = "Dashboard Page";
	}
	
	@PostConstruct
	public void onPageLoad() {
		super.onPageLoad();
	}
}
