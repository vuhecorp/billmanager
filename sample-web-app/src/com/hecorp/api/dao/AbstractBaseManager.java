package com.hecorp.api.dao;

import java.sql.Connection;

import com.hersa.sample.app.dao.billitemtemplate.BillItemTemplateDAO;
import com.hersa.sample.app.dao.billitemtemplate.BillItemTemplateDAOFactory;

public class AbstractBaseManager {

	public ConnectionProvider getDefautlConnectionProvider() {
		ConnectionProvider factory = new DefaultConnectionProvider(JNDI.EBILLING);
		return factory;
	}
	
	public BillItemTemplateDAO getBillItemTemplateDAO() {
		BillItemTemplateDAO dao = BillItemTemplateDAOFactory.getDAO();
		dao.setConnectionProvider(this.getDefautlConnectionProvider());
		return dao;
	}
	
	public BillItemTemplateDAO getBillItemTemplateDAO(Connection connection) {
		BillItemTemplateDAO dao = BillItemTemplateDAOFactory.getDAO(connection);
		return dao;
	}
}
