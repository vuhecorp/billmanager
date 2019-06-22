package com.hersa.sample.app.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.hersa.sample.app.bom.billitemsummary.BillItemSummary;
import com.hersa.sample.app.bom.billitemsummary.BillItemSummaryManager;
import com.hersa.sample.app.bom.billsummary.BillSummary;
import com.hersa.sample.app.bom.billsummary.BillSummaryManager;
import com.hersa.sample.app.utils.BillManagerUtils;

@ManagedBean
@ViewScoped
public class MyBillsPage extends AbstractFacesPage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2635878856409734237L;
	private List<BillSummary> myBills;
	private List<BillItemSummary> myBillItems;
	
	public MyBillsPage() {
		super();
		pageTitle = "My Bills";
		this.myBills = new ArrayList<BillSummary>();
		this.setMyBillItems(new ArrayList<BillItemSummary>());
	}
	
	@PostConstruct
	public void onPageLoad() {
		super.onPageLoad();
		loadMyBills();
		loadMyBillItems();
	}

	private void loadMyBillItems() {
		BillItemSummaryManager bism= new BillItemSummaryManager();
		int month = BillManagerUtils.getIntMonth(new Date());
		myBillItems = bism.retriveBillItemSummaryByMonth(sessionUser.getUserName(), month);
	}

	private void loadMyBills() {
		BillSummaryManager bsm = new BillSummaryManager();
		myBills = bsm.retrieveBillSummaryByUserName(sessionUser.getUserName());
	}

	public List<BillSummary> getMyBills() {
		return myBills;
	}

	public void setMyBills(List<BillSummary> myBills) {
		this.myBills = myBills;
	}

	public List<BillItemSummary> getMyBillItems() {
		return myBillItems;
	}

	public void setMyBillItems(List<BillItemSummary> myBillItems) {
		this.myBillItems = myBillItems;
	}
}
