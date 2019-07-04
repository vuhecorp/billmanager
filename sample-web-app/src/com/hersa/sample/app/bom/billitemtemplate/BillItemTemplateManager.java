package com.hersa.sample.app.bom.billitemtemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hecorp.api.dao.AbstractBaseManager;
import com.hersa.sample.app.bom.billitem.BillItem;
import com.hersa.sample.app.dao.billitemtemplate.BillItemTemplateDTO;

public class BillItemTemplateManager extends AbstractBaseManager {

	public List<BillItemTemplate> retrieveAllBillItemTemplates(){
		return convert(this.getBillItemTemplateDAO().listAllBillItemTemplate());
	}
	
	public List<BillItemTemplate> retrieveAllBillItemTemplatesByUserName(String userName){
		return convert(this.getBillItemTemplateDAO().listBillItemTemplateByUsername(userName));
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
