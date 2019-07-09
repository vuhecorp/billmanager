package com.hersa.sample.app.bom.billitem;

import java.sql.Connection;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.hecorp.api.dao.AbstractBaseManager;
import com.hecorp.api.dao.ApplicationException;
import com.hersa.sample.app.dao.billitem.BillItemCreateException;
import com.hersa.sample.app.dao.billitem.BillItemDB;
import com.hersa.sample.app.dao.billitem.BillItemDTO;

public class BillItemManager extends AbstractBaseManager{

	public List<BillItem> retriveBillItemsByBillId(long id, String userName){
		String whereClause   = "WHERE BILL_ID = ? AND USERNAME = ?";
		Object[] whereParams = {id, userName};
		int[] paramTypes     = {Types.BIGINT, Types.VARCHAR};
		String orderBy       = " ORDER BY " + BillItemDB.ORDERBY_DATEDUE + " ASC";
		return convert(this.getBillItemDAO().listBillItem(whereClause, whereParams, paramTypes, orderBy));
	}
	
	public List<BillItem> retriveBillItemsByMonth(String userName, int month){
		String whereClause   = " WHERE USERNAME = ? ";
		Object[] whereParams = {userName};
		int[] paramTypes     = {Types.VARCHAR};
		String orderBy       = " ORDER BY " + BillItemDB.ORDERBY_DATEDUE + " ASC";
		return convert(this.getBillItemDAO().listBillItem(whereClause, whereParams, paramTypes, orderBy));
	}
	
	public void createBillItem(BillItem billItem) throws ApplicationException {
		createBillItem(billItem, null);
	}
	
	public void createBillItem(BillItem billItem, Connection external) throws ApplicationException {
		
		Connection connection = external;
		boolean local 		  = false;
		
		try {
			
			if (connection == null) {
				local = true;
				connection = getDefautlConnectionProvider().openConnection();
			}
			
			this.getBillItemDAO(connection).createBillItem(billItem.getDto());
		} catch (BillItemCreateException e) {
			e.printStackTrace();
			throw new ApplicationException("An error occurred while creating bill item");
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("An unexpected error occured.");
		}finally {
			if (local) {
				close(connection);
			}
		}
	}
	
	public List<BillItem> convert(BillItemDTO[] dtos){
		List<BillItem> list = new ArrayList<BillItem>();
		
		if (dtos == null) {
			return list;
		}
		
		for (BillItemDTO dto : dtos) {
			list.add(new BillItem(dto));
		}
		return list;
	}
	
}
