package com.hecorp.api.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

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

	public void markCreated(BOM bom, String userName) {
		markCreated(bom, userName, new Date());
	}
	
	public void markCreated(BOM bom , String userName, Date date) {
		bom.setCreatedBy(userName);
		bom.setCreatedOn(date);
	    markUpdated(bom, userName, date);
	}
	
	public void markUpdated(BOM bom , String userName) {
		markUpdated(bom, userName, new Date());
	}
	
	public void markUpdated(BOM bom , String userName, Date date) {
		bom.setModifiedBy(userName);
		bom.setModifiedOn(date);
	}
	
	public void close(Connection connection) {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void rollback(Connection connection) {
		try {
			connection.rollback();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
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
