package com.hersa.sample.app.schedule;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.context.FacesContext;

import org.primefaces.component.schedule.Schedule;
import org.primefaces.model.LazyScheduleModel;

import com.hersa.sample.app.bom.billitemsummary.BillItemSummary;
import com.hersa.sample.app.bom.billitemsummary.BillItemSummaryManager;
import com.hersa.sample.app.utils.BillManagerUtils;
import com.hersa.sample.app.web.MyBillsPage;

public class LazyBillScheduleModel extends LazyScheduleModel {

    /**
	 * 
	 */
	private static final long serialVersionUID = -2095506456969060969L;
	
	private List<BillItemSummary> lazyBillItems = new ArrayList<BillItemSummary>();
	private MyBillsPage billsPage;
	private Date start;
	private Date end;
	
	public LazyBillScheduleModel(MyBillsPage billsPage) {
		this.billsPage = billsPage;
	}
	
    public void loadEvents(Date start, Date end) {
    	
    	//re-use these to reload the events.
    	this.start = start;
    	this.end = end;
    	
    	System.err.println("LOADING : " + start + " " + end );
    	
    	lazyBillItems.clear();
    	BillItemSummaryManager bism = new BillItemSummaryManager();
		lazyBillItems  = bism.retriveBillItemSummaryByRange(billsPage.getSessionUser().getUserName(), start, end);

		for (BillItemSummary billItemSummary : lazyBillItems ) {
			BillScheduleEvent item = new BillScheduleEvent(billItemSummary);
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
    
    /**
     * Use after create, update or delete
     */
    public void reloadCurrentDateRange() {
    	this.loadEvents(start, end);
    }
	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}
  
}
