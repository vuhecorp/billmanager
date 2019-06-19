package com.hersa.sample.app.web;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean
@ViewScoped
public class IndexPage extends AbstractFacesPage implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6761756688661978051L;
	
	public IndexPage() {
		super();
		super.pageTitle = "Index Page";
	}
	
	@PostConstruct
	public void onPageLoad() {
		super.onPageLoad();
	}
	
	
}
