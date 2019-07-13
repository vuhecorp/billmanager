package com.hersa.sample.app.bom.bill;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.hecorp.api.dao.AbstractBaseManager;
import com.hecorp.api.dao.ApplicationException;
import com.hersa.sample.app.bom.billitem.BillItem;
import com.hersa.sample.app.bom.billsummary.BillSummary;
import com.hersa.sample.app.dao.bill.BillCreateException;
import com.hersa.sample.app.dao.bill.BillDB;
import com.hersa.sample.app.dao.bill.BillDTO;

public class BillManager extends AbstractBaseManager{

	public List<Bill> retrieveBillsByUserName(String userName){
		return convert(this.getBillDAO().listBillByUsername(userName, BillDB.ORDERBY_MONTH));
	}
	
	public void createBill(Bill bill) throws ApplicationException {
		createBill(bill, null);
	}
	
	public void createBill(Bill bill, Connection external) throws ApplicationException{
		Connection connection = external;
		boolean local = false;
		
		try {
			if (connection == null) {
				local = true;
				connection = getDefautlConnectionProvider().openConnection();
			}
			
			this.getBillDAO(connection).createBill(bill.getDto());
		} catch (BillCreateException e) {
			e.printStackTrace();
			throw new ApplicationException("An error occurred while creating the bill object");
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("an unexpected error occured");
		}finally {
			if (local) {
				close(connection);
			}
		}
	}
	public static Bill initBillFromItem(BillItem billItem){
		Bill bill = new Bill();
		
		return bill;
	}
	
	public static Bill convertSummaryToBill(BillSummary summary) {
		Bill bill = new Bill();
		
		return bill;
	}
	
	public List<Bill> convert(BillDTO[] dtos){
		List<Bill> list = new ArrayList<Bill>();
		
		if (dtos == null) {
			return list;
		}
		
		for (BillDTO dto : dtos) {
			list.add(new Bill(dto));
		}
		return list;
	}
}
