package com.hersa.sample.app.schedule;

import java.util.Date;

import org.primefaces.model.DefaultScheduleEvent;

import com.hersa.sample.app.bom.billitemsummary.BillItemSummary;

public class BillScheduleEvent extends DefaultScheduleEvent{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3951957125480111853L;
	
	private BillItemSummary billItemSummary;
	private boolean editMode;
	
	public BillScheduleEvent() {
		super();
		initialize();
	}
	
	public BillScheduleEvent(BillItemSummary billItemSummary) {
		super(billItemSummary.getName(), billItemSummary.getDueOn(), billItemSummary.getDueOn(), billItemSummary);
		setStyleClass(billItemSummary.getTypeCode().toLowerCase() + "-event");
		initialize();
		this.billItemSummary = billItemSummary;
	}

	public void initialize(){
		this.billItemSummary = new BillItemSummary();
	}
	
	public BillScheduleEvent(String title, Date start, Date end) {
        super(title, start, end);
    }

    public BillScheduleEvent(String title, Date start, Date end, boolean allDay) {
        super(title, start, end, allDay);
    }

    public BillScheduleEvent(String title, Date start, Date end, String styleClass) {
        super(title, start, end, styleClass);
    }

    public BillScheduleEvent(String title, Date start, Date end, Object data) {
        super(title, start, end, data);
    }
 
	public BillItemSummary getBillItemSummary() {
		return billItemSummary;
	}

	public void setBillItemSummary(BillItemSummary billItemSummary) {
		this.billItemSummary = billItemSummary;
	}

	public boolean isEditMode() {
		return editMode;
	}

	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}

}
