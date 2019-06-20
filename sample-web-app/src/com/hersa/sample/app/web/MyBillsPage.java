package com.hersa.sample.app.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.hersa.sample.app.bom.bill.Bill;
import com.hersa.sample.app.bom.bill.BillManager;

@ManagedBean
@SessionScoped
public class MyBillsPage extends AbstractFacesPage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2635878856409734237L;
	private List<Bill> myBills;
	public MyBillsPage() {
		super();
		pageTitle = "My Bills";
		this.myBills = new ArrayList<Bill>();
	}
	
	@PostConstruct
	public void onPageLoad() {
		super.onPageLoad();
		loadMyBills();
	}

	private void loadMyBills() {
		BillManager bm = new BillManager();
		myBills = bm.retrieveBillsByUserName(sessionUser.getUserName());
	}

	public List<Bill> getMyBills() {
		return myBills;
	}

	public void setMyBills(List<Bill> myBills) {
		this.myBills = myBills;
	}
}
