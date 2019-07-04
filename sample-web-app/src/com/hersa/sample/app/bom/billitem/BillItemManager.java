package com.hersa.sample.app.bom.billitem;

import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;

import com.hecorp.api.dao.AbstractBaseManager;
import com.hersa.sample.app.bom.billitemsummary.BillItemSummary;
import com.hersa.sample.app.bom.billitemtemplate.BillItemTemplate;
import com.hersa.sample.app.dao.billitem.BillItemDB;
import com.hersa.sample.app.dao.billitem.BillItemDTO;
import com.hersa.sample.app.schedule.BillScheduleEvent;
import com.hersa.sample.app.utils.BillManagerUtils;

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
	
	public static BillItemTemplate convertToTemplate(BillItemSummary item) {
		
		BillItemTemplate template = new BillItemTemplate();
		
		template.setTypeCode(item.getTypeCode());
		template.setRecurring(item.isRecurring() ? 1 : 0);
		template.setRecurringCode(item.getRecurringCode());
		template.setName(item.getName());
		template.setDescription(item.getDescription());
		template.setAmount(item.getBilledAmount());
		template.setDayDue(calcDayDue(item));
		template.setTimeDue(item.getTimeDue());
		template.setPayTo(item.getPayTo());
		
		return template;
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
			dayDue = dateTime.getDayOfWeek();
		}else if (BillManagerUtils.RECURRING_CODE_MONTH.equalsIgnoreCase(recurringCode)) {
			dayDue = dateTime.getDayOfMonth();
		}
		
		return dayDue;
	}
}
