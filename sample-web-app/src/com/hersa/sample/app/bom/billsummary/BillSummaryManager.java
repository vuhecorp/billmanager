package com.hersa.sample.app.bom.billsummary;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.hecorp.api.dao.AbstractBaseManager;
import com.hersa.sample.app.dao.bill.BillDB;
import com.hersa.sample.app.dao.billsummary.VBillSummaryDB;
import com.hersa.sample.app.dao.billsummary.VBillSummaryDTO;

public class BillSummaryManager extends AbstractBaseManager{

	public List<BillSummary> retrieveBillSummaryByUserName(String userName){
		return convert(this.getBillSummaryDAO().listVBillSummaryByUsername(userName, BillDB.ORDERBY_MONTH));
	}
	
	public List<BillSummary> retrieveBillSummariesByMonth(String userName , int month){
		String whereClause = " WHERE USERNAME = ? AND MONTH = ? AND TOTAL_BILLED > 0";
		Object[] whereParams = {userName, month};
		int[] paramTypes = {Types.VARCHAR, Types.INTEGER};
		String orderClause = " ORDER BY " + VBillSummaryDB.ORDERBY_STARTDATE + " ASC";
		return convert(this.getBillSummaryDAO().listVBillSummary(whereClause, whereParams, paramTypes, orderClause));
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
