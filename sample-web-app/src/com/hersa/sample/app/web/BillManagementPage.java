package com.hersa.sample.app.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.hersa.sample.app.bom.billitemtemplate.BillItemTemplate;
import com.hersa.sample.app.bom.billitemtemplate.BillItemTemplateManager;

@ManagedBean(name="billManagement")
@ViewScoped
public class BillManagementPage extends AbstractFacesPage implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1621195859620592613L;
	
	public List<BillItemTemplate> billItemTemplates;
	public BillItemTemplate selectedBillItemTemplate;
	
	public BillManagementPage() {
		super();
		pageTitle = "Bill Management";
		this.billItemTemplates = new ArrayList<BillItemTemplate>();
		this.selectedBillItemTemplate = new BillItemTemplate();
	}
	
	@PostConstruct
	public void onPageLoad() {
		super.onPageLoad();
		loadBillItemTemplates();
	}
	
	private void loadBillItemTemplates() {
		BillItemTemplateManager bitm = new BillItemTemplateManager();
		billItemTemplates = bitm.retrieveAllBillItemTemplatesByUserName(sessionUser.getUserName());
	}
	
	public List<BillItemTemplate> getBillItemTemplates() {
		return billItemTemplates;
	}
	
	public void setBillItemTemplates(List<BillItemTemplate> billItemTemplates) {
		this.billItemTemplates = billItemTemplates;
	}
	
	public BillItemTemplate getSelectedBillItemTemplate() {
		return selectedBillItemTemplate;
	}
	
	public void setSelectedBillItemTemplate(BillItemTemplate selectedBillItemTemplate) {
		this.selectedBillItemTemplate = selectedBillItemTemplate;
	}
}
