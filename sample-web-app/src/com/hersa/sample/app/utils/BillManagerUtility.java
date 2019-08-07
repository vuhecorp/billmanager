package com.hersa.sample.app.utils;

import java.sql.Connection;
import java.util.Map;

import com.hecorp.api.dao.ApplicationException;
import com.hersa.sample.app.bom.bill.Bill;
import com.hersa.sample.app.bom.bill.BillManager;
import com.hersa.sample.app.bom.billitem.BillItem;
import com.hersa.sample.app.bom.billitem.BillItemManager;
import com.hersa.sample.app.bom.billitemtemplate.BillItemTemplate;
import com.hersa.sample.app.bom.billitemtemplate.BillItemTemplateManager;

public class BillManagerUtility {

	public static final String PARAM_CREATE_BILL_TEMPLATE = "bill.template.obj";
	public static final String PARAM_CREATE_BILL_ITEM     = "bill.item.obj";
	public static final String PARAM_CREATE_BILL  		  = "bill.bill.obj";
	
	public BillManagerUtility() {
		
	}

	public void createNewBillItem(Map<String, Object> params) throws ApplicationException {

		Exception ex = null;
		
		BillItemTemplateManager btm = new BillItemTemplateManager();
		BillManager bm 			    = new BillManager();
		BillItemManager bim 		= new BillItemManager();
		Connection connection       = null;
		
		try {
			
			BillItem billItem = (BillItem) params.get(PARAM_CREATE_BILL_ITEM);
			connection 		  = btm.getDefautlConnectionProvider().openConnection();
			
			connection.setAutoCommit(false);
			
			if (params.containsKey(PARAM_CREATE_BILL_TEMPLATE)) {
				BillItemTemplate template = (BillItemTemplate) params.get(PARAM_CREATE_BILL_TEMPLATE);
				template.setQuartzExp("");
				template.setEntityCode("");
				btm.createBillItemTemplate(template, connection);
				billItem.setTemplateId(template.getId());
			}
			
			if (params.containsKey(PARAM_CREATE_BILL)) {
				Bill bill = (Bill) params.get(PARAM_CREATE_BILL);
				bm.createBill(bill, connection);
				billItem.setBillId(bill.getId());
			}
			
			billItem.setEntityCode("");
			bim.createBillItem(billItem, connection);
			connection.commit();
			
		} catch (ApplicationException e) {
			ex = e;
			throw e;
		} catch (Exception e) {
			ex = e;
			throw new ApplicationException("An unhandled exception occred while creating bill item.");
		}finally {
			
			if (ex != null) {
				btm.rollback(connection);
			}
			
			btm.close(connection);
		}
	}
	

}
