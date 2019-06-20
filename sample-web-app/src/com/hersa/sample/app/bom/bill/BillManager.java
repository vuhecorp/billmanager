package com.hersa.sample.app.bom.bill;

import java.util.ArrayList;
import java.util.List;

import com.hecorp.api.dao.AbstractBaseManager;
import com.hersa.sample.app.dao.bill.BillDB;
import com.hersa.sample.app.dao.bill.BillDTO;

public class BillManager extends AbstractBaseManager{

	public List<Bill> retrieveBillsByUserName(String userName){
		return convert(this.getBillDAO().listBillByUsername(userName, BillDB.ORDERBY_MONTH));
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
