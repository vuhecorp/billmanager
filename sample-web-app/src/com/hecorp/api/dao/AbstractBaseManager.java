package com.hecorp.api.dao;

import java.sql.Connection;

import com.hersa.sample.app.dao.bill.BillDAO;
import com.hersa.sample.app.dao.bill.BillDAOFactory;
import com.hersa.sample.app.dao.billitem.BillItemDAO;
import com.hersa.sample.app.dao.billitem.BillItemDAOFactory;
import com.hersa.sample.app.dao.billitemsummary.VBillItemSummaryDAO;
import com.hersa.sample.app.dao.billitemsummary.VBillItemSummaryDAOFactory;
import com.hersa.sample.app.dao.billitemtemplate.BillItemTemplateDAO;
import com.hersa.sample.app.dao.billitemtemplate.BillItemTemplateDAOFactory;
import com.hersa.sample.app.dao.billsummary.VBillSummaryDAO;
import com.hersa.sample.app.dao.billsummary.VBillSummaryDAOFactory;

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
	
	public BillItemDAO getBillItemDAO() {
		BillItemDAO dao = BillItemDAOFactory.getDAO();
		dao.setConnectionProvider(this.getDefautlConnectionProvider());
		return dao;
	}
	
	public BillItemDAO getBillItemDAO(Connection connection) {
		BillItemDAO dao = BillItemDAOFactory.getDAO(connection);
		return dao;
	}
	
	public BillDAO getBillDAO() {
		BillDAO dao = BillDAOFactory.getDAO();
		dao.setConnectionProvider(this.getDefautlConnectionProvider());
		return dao;
	}
	
	public BillDAO getBillDAO(Connection connection) {
		BillDAO dao = BillDAOFactory.getDAO(connection);
		return dao;
	}
	
	public VBillSummaryDAO getBillSummaryDAO() {
		VBillSummaryDAO dao = VBillSummaryDAOFactory.getDAO();
		dao.setConnectionProvider(this.getDefautlConnectionProvider());
		return dao;
	}
	
	public VBillSummaryDAO getBillSummaryDAO(Connection connection) {
		VBillSummaryDAO dao = VBillSummaryDAOFactory.getDAO(connection);
		return dao;
	}
	
	public VBillItemSummaryDAO getBillItemSummaryDAO() {
		VBillItemSummaryDAO dao = VBillItemSummaryDAOFactory.getDAO();
		dao.setConnectionProvider(this.getDefautlConnectionProvider());
		return dao;
	}
	
	public VBillItemSummaryDAO getBillItemSummaryDAO(Connection connection) {
		VBillItemSummaryDAO dao = VBillItemSummaryDAOFactory.getDAO(connection);
		return dao;
	}
}
