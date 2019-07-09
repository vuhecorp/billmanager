package com.hersa.sample.app.bom.billitemsummary;

import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;

import com.hecorp.api.dao.AbstractBaseManager;
import com.hersa.sample.app.bom.billitem.BillItem;
import com.hersa.sample.app.bom.billitemtemplate.BillItemTemplate;
import com.hersa.sample.app.dao.billitem.BillItemDB;
import com.hersa.sample.app.dao.billitemsummary.VBillItemSummaryDTO;
import com.hersa.sample.app.utils.BillManagerUtils;

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
	
	/**
	 * Use for create operation only. Does not contain valid IDs
	 * @param itemSummary
	 * @return
	 */
	public static BillItemTemplate convertToTemplate(BillItemSummary itemSummary) {
		
		BillItemTemplate template = new BillItemTemplate();
		
		template.setActive(itemSummary.getActive());
		template.setTypeCode(itemSummary.getTypeCode());
		template.setRecurring(itemSummary.isRecurring() ? 1 : 0);
		template.setRecurringCode(itemSummary.getRecurringCode());
		template.setName(itemSummary.getName());
		template.setDescription(itemSummary.getDescription());
		template.setAmount(itemSummary.getBilledAmount());
		template.setDayDue(calcDayDue(itemSummary));
		template.setTimeDue(itemSummary.getTimeDue());
		template.setPayTo(itemSummary.getPayTo());
		
		return template;
	}
	
	/**
	 * Use for create operation only. Does not contain valid IDs
	 * @param itemSummary
	 * @return
	 */
	public static BillItem convertToBillItem(BillItemSummary itemSummary) {
		BillItem item = new BillItem();
		
		/*REQUIRED fields to set:
		 * 	1. billid    
		 *  2. template id*/
		item.setActive(itemSummary.getActive());
		item.setTemplateId(-1);
		item.setAmount(itemSummary.getBilledAmount());
		item.setDateDue(itemSummary.getDateDue());
		item.setTimeDue(itemSummary.getTimeDue());
		item.setDay(itemSummary.getDay());
		item.setDescription(itemSummary.getDescription());
		item.setWeek(itemSummary.getWeek());
		item.setMonth(itemSummary.getMonth());
		item.setYear(itemSummary.getYear());
		item.setName(itemSummary.getName());
		item.setPayTo(itemSummary.getPayTo());
		item.setTypeCode(itemSummary.getTypeCode());
		item.setRecurringCode(itemSummary.getRecurringCode());
		return item;
	}

	/**
	 * calc week day depending on recurring type and date
	 * @param item
	 * @return
	 */
	private static int calcDayDue(BillItemSummary item) {
		int dayDue           = 0;
		String recurringCode = item.getRecurringCode();
		Date dueDate         = item.getDateDue();
		DateTime dateTime    = new DateTime(dueDate);
		
		if (BillManagerUtils.RECURRING_CODE_WEEK.equalsIgnoreCase(recurringCode)) {
			//the system runs on 0-7 , however; Joda Time runs on 1 - 7. Adjust accordingly 
			dayDue = dateTime.getDayOfWeek() == 7 ? 0 : dateTime.getDayOfWeek();
		}else if (BillManagerUtils.RECURRING_CODE_MONTH.equalsIgnoreCase(recurringCode)) {
			dayDue = dateTime.getDayOfMonth();
		}
		
		return dayDue;
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
