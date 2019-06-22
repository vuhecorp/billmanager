package com.hersa.sample.app.bom.billsummary;

import java.util.ArrayList;
import java.util.List;

import com.hecorp.api.dao.AbstractBaseManager;
import com.hersa.sample.app.dao.bill.BillDB;
import com.hersa.sample.app.dao.billsummary.VBillSummaryDTO;

public class BillSummaryManager extends AbstractBaseManager{

	public List<BillSummary> retrieveBillSummaryByUserName(String userName){
		return convert(this.getBillSummaryDAO().listVBillSummaryByUsername(userName, BillDB.ORDERBY_MONTH));
	}
	
	public List<BillSummary> convert(VBillSummaryDTO[] dtos){
		List<BillSummary> list = new ArrayList<BillSummary>();
		
		if (dtos == null) {
			return list;
		}
		
		for (VBillSummaryDTO dto : dtos) {
			list.add(new BillSummary(dto));
		}
		return list;
	}
}
