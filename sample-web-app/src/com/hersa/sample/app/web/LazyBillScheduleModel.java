package com.hersa.sample.app.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.context.FacesContext;

import org.primefaces.component.schedule.Schedule;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.LazyScheduleModel;

import com.hersa.sample.app.bom.billitemsummary.BillItemSummary;
import com.hersa.sample.app.bom.billitemsummary.BillItemSummaryManager;
import com.hersa.sample.app.utils.BillManagerUtils;

public class LazyBillScheduleModel extends LazyScheduleModel {

    /**
	 * 
	 */
	private static final long serialVersionUID = -2095506456969060969L;
	
	private List<BillItemSummary> lazyBillItems = new ArrayList<BillItemSummary>();
	
	private MyBillsPage billsPage;
	
	public LazyBillScheduleModel(MyBillsPage billsPage) {
		this.billsPage = billsPage;
	}
	
    public void loadEvents(Date start, Date end) {
    	
    	System.err.println("LOADING : " + start + " " + end );
    	
    	lazyBillItems.clear();
    	BillItemSummaryManager bism = new BillItemSummaryManager();
		//int month = BillManagerUtils.getIntMonth(start);
		lazyBillItems  = bism.retriveBillItemSummaryByRange(billsPage.getSessionUser().getUserName(), start, end);

		for (BillItemSummary billItemSummary : lazyBillItems ) {
			DefaultScheduleEvent item = new DefaultScheduleEvent(billItemSummary.getName(),
					billItemSummary.getDateDue(), billItemSummary.getDateDue());
			item.setStyleClass(billItemSummary.getTypeCode().toLowerCase() + "-event");
			addEvent(item);
		}   
		
		billsPage.setMyBillItems(lazyBillItems);
		billsPage.createCategoryDonutModel();
		
		Schedule schedule = (Schedule) FacesContext.getCurrentInstance().getViewRoot().findComponent("myBillForm:myschedule");
		
		String nxtDate = schedule.getNextDayThreshold();
		System.err.println("next:  " + nxtDate);
		String slot = schedule.getSlotDuration();
		System.err.println("slot:  " + slot);
		String calc = schedule.getWeekNumberCalculation();
		System.err.println("calc:  " + calc);
		String view = schedule.getView();
		System.err.println("view:  " + view);
		int month = BillManagerUtils.getMedianDateMonth(start, end);
		
		billsPage.loadMyBills(month);
    }
  
}
