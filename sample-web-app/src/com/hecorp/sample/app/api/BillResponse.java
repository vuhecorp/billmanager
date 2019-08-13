package com.hecorp.sample.app.api;

import java.util.ArrayList;
import java.util.List;

import com.hersa.sample.app.bom.billitemtemplate.BillItemTemplate;

public class BillResponse {

	List<BillItemTemplate> items = new ArrayList<BillItemTemplate>();
	
	public BillResponse(List<BillItemTemplate> items) {
		this.items = items;
	}

	public List<BillItemTemplate> getItems() {
		return items;
	}

	public void setItems(List<BillItemTemplate> items) {
		this.items = items;
	}
	
	
}
