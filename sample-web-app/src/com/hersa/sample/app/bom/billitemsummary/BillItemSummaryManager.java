package com.hersa.sample.app.bom.billitemsummary;

import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hecorp.api.dao.AbstractBaseManager;
import com.hersa.sample.app.dao.billitem.BillItemDB;
import com.hersa.sample.app.dao.billitemsummary.VBillItemSummaryDTO;

public class BillItemSummaryManager extends AbstractBaseManager {

	public List<BillItemSummary> retriveBillItemSummaryByMonth(String userName, int month){
		String whereClause = " WHERE USERNAME = ? AND MONTH = ?";
		Object[] whereParams = {userName, month};
		int[] paramTypes = {Types.VARCHAR, Types.INTEGER};
		String orderClause = " ORDER BY " + BillItemDB.ORDERBY_DATEDUE + " ASC";
		return convert(this.getBillItemSummaryDAO().listVBillItemSummary(whereClause, whereParams, paramTypes, orderClause));
	}
	
	public List<BillItemSummary> retriveBillItemSummaryByRange(String userName, Date start, Date end){
		String whereClause = " WHERE USERNAME = ? AND DATE_DUE BETWEEN  ? AND ? ";
		Object[] whereParams = {userName, start, end};
		int[] paramTypes = {Types.VARCHAR, Types.DATE, Types.DATE};
		String orderClause = " ORDER BY " + BillItemDB.ORDERBY_DATEDUE + " ASC";
		return convert(this.getBillItemSummaryDAO().listVBillItemSummary(whereClause, whereParams, paramTypes, orderClause));
	}
	
	
	public List<BillItemSummary> convert(VBillItemSummaryDTO[] dtos){
		List<BillItemSummary> list = new ArrayList<BillItemSummary>();
		
		if (dtos == null) {
			return list;
		}
		
		for (VBillItemSummaryDTO dto : dtos) {
			list.add(new BillItemSummary(dto));
		}
		return list;
	}
}
