package com.hersa.sample.app.bom.billitemtemplate;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.hecorp.api.dao.AbstractBaseManager;
import com.hecorp.api.dao.ApplicationException;
import com.hersa.sample.app.bom.billitem.BillItem;
import com.hersa.sample.app.dao.billitemtemplate.BillItemTemplateCreateException;
import com.hersa.sample.app.dao.billitemtemplate.BillItemTemplateDTO;

public class BillItemTemplateManager extends AbstractBaseManager {

	public List<BillItemTemplate> retrieveAllBillItemTemplates(){
		return convert(this.getBillItemTemplateDAO().listAllBillItemTemplate());
	}
	
	public List<BillItemTemplate> retrieveAllBillItemTemplatesByUserName(String userName){
		return convert(this.getBillItemTemplateDAO().listBillItemTemplateByUsername(userName));
	}
	
	public void createBillItemTemplate(BillItemTemplate template) throws ApplicationException {
		createBillItemTemplate(template, null);
	}
	
	public void createBillItemTemplate(BillItemTemplate template, Connection external) throws ApplicationException {
		Connection connection = external;
		boolean local  		  = false;
	
		try {
			if (connection == null) {
				local = true;
				connection = getDefautlConnectionProvider().openConnection();
			}
			
			this.getBillItemTemplateDAO(connection).createBillItemTemplate(template.getDto());
		} catch (BillItemTemplateCreateException e) {
			e.printStackTrace();
			throw new ApplicationException("An error occurred while creating template object");
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("an unexpected error occurred.");
		}finally {
			if (local) {
				close(connection);
			}
		}
	}
	
	public List<BillItemTemplate> convert(BillItemTemplateDTO[] dtos){
		List<BillItemTemplate> list = new ArrayList<BillItemTemplate>();
		if (dtos == null) {
			return list;
		}
		
		for (BillItemTemplateDTO dto : dtos) {
			list.add(new BillItemTemplate(dto));
		}
		return list;
	}
	
	public static BillItem convertToBillItem(BillItemTemplate template) {
		
		return null;
	}
}
