package com.hersa.sample.app.web;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.component.schedule.Schedule;
import org.primefaces.event.DateViewChangeEvent;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleModel;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.donut.DonutChartDataSet;
import org.primefaces.model.charts.donut.DonutChartModel;

import com.hecorp.api.dao.ApplicationException;
import com.hersa.sample.app.bom.billitemsummary.BillItemSummary;
import com.hersa.sample.app.bom.billsummary.BillSummary;
import com.hersa.sample.app.bom.billsummary.BillSummaryManager;
import com.hersa.sample.app.schedule.BillScheduleEvent;
import com.hersa.sample.app.schedule.LazyBillScheduleModel;

@ManagedBean
@ViewScoped
public class MyBillsPage extends AbstractFacesPage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2635878856409734237L;
	
	/* ==========================================================================================================================
	 * Member Variables
	 * ==========================================================================================================================*/
	private List<BillSummary> myBills;
	private Map<String, Map<Integer, BillSummary>> myBillsMap;
	
	private List<BillItemSummary> myBillItems = new ArrayList<BillItemSummary>();

	private ScheduleModel eventModel;
	private LazyBillScheduleModel lazyEventModel;

	private BillScheduleEvent event = new BillScheduleEvent();
	private DonutChartModel typeDonutModel = new DonutChartModel();
	private DonutChartModel categoryDonutModel = new DonutChartModel();

	Map<String, String> categoryColorMap = new HashMap<String, String>();
	Map<String, String> typeColorMap = new HashMap<String, String>();
	
	
	/* ==========================================================================================================================
	 * Constructors / Page Load
	 * ==========================================================================================================================*/
	
	public MyBillsPage() {
		super();
		
		pageTitle = "My Bills";
		this.myBills = new ArrayList<BillSummary>();
		this.myBillsMap = new HashMap<String, Map<Integer, BillSummary>>();
		this.setMyBillItems(new ArrayList<BillItemSummary>());
		eventModel = new DefaultScheduleModel();
		
		categoryColorMap.put("CC", "rgb(46, 184, 46)");
		categoryColorMap.put("TRNSPT", "rgb(54, 162, 235)");
		categoryColorMap.put("UTIL", "rgb(255, 99, 132)");
		categoryColorMap.put("FOOD", "rgb(255, 204, 0)");
		
		typeColorMap.put("MONTH", "rgb(46, 184, 46)");
		typeColorMap.put("WEEK", "rgb(54, 162, 235)");
		
	}

	@PostConstruct
	public void onPageLoad() {
		super.onPageLoad();
		lazyEventModel = new LazyBillScheduleModel(this);
		Schedule schedule = (Schedule) FacesContext.getCurrentInstance().getViewRoot().findComponent("myBillForm:myschedule");
	}

	public void viewActionMethod() {
		//System.err.println("PAGE TEST");
	}
	
	public void loadMyBills(int month) {
		BillSummaryManager bsm = new BillSummaryManager();
		myBills = bsm.retrieveBillSummariesByMonth(sessionUser.getUserName() , month);
		
		//init bills map. 
		Thread thread = new Thread() {
			public void run() {

				myBillsMap.clear();

				Map<Integer, BillSummary> monthMap = new HashMap<Integer, BillSummary>();
				Map<Integer, BillSummary> weekMap  = new HashMap<Integer, BillSummary>();

				for (BillSummary bill : myBills) {
					
					String recurrType = bill.getCycleType();
					Integer key       = recurrType.equalsIgnoreCase(Constants.RECURRING_MONTH) ? bill.getMonth() : bill.getWeek();
					
					if (recurrType.equalsIgnoreCase(Constants.RECURRING_MONTH) ) {
						monthMap.put(key, bill);
					}else {
						weekMap.put(key, bill);
					}
				}
				
				myBillsMap.put(Constants.RECURRING_MONTH, monthMap);
				myBillsMap.put(Constants.RECURRING_WEEK, weekMap);
			}
		};

		thread.start();
		
		createTypeDonutModel();
	}

	/* ==========================================================================================================================
	 * Action Methods
	 * ==========================================================================================================================*/
	
	public String onUpdateScheduleBtnClick() {
		String response = "";
		try {
			onBeforeUpdateSchedule();
			onUpdateSchedule();
			onAfterUpdateSchedule();
		} catch(ApplicationException e) {
			addErrorMessage(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			addErrorMessage("An unexpected error has occured");
		}
		return response;
	}
	
	/**
	 * Validate use inputs
	 * @throws ApplicationException
	 */
	private void onBeforeUpdateSchedule() throws ApplicationException{
		BillItemSummary billItem = event.getBillItemSummary();
		
	}

	/**
	 * Perform update/create logic
	 * @throws ApplicationException
	 */
	private void onUpdateSchedule() throws ApplicationException{
		// TODO Auto-generated method stub
		
	}

	/**
	 * Update models
	 * @throws ApplicationException
	 */
	private void onAfterUpdateSchedule() throws ApplicationException{
		// TODO Auto-generated method stub
		
	}

	public void addEvent() {
		if (event.getId() == null) {
			eventModel.addEvent(event);
		}else {
			eventModel.updateEvent(event);
		}
		event = new BillScheduleEvent();
	}
	
	public String onEditBtnClick() {
		String response = "";
		try {
			boolean editMode = event.isEditMode();
			if (editMode) {
				event.setEditMode(false);
			}else {
				event.setEditMode(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	/* ==========================================================================================================================
	 * Schedule Event Listeners
	 * ==========================================================================================================================*/
	
	public void onEventSelect(SelectEvent selectEvent) {
		event = (BillScheduleEvent) selectEvent.getObject();
		event.setEditMode(false);
	}

	public void onDateSelect(SelectEvent selectEvent) {
		//event = new BillScheduleEvent("", (Date) selectEvent.getObject(), (Date) selectEvent.getObject());
		Date dueDate = (Date) selectEvent.getObject();
		event = new BillScheduleEvent();
		event.getBillItemSummary().setDateDue(dueDate);
		event.getBillItemSummary().setTimeDue(new Date());
		event.getBillItemSummary().setRecurring(1);
		event.getBillItemSummary().setRecurringCode(Constants.RECURRING_MONTH);
		event.setEditMode(true);
	}

	public void onEventMove(ScheduleEntryMoveEvent event) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event moved",
				"Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());

		addMessage(message);
	}

	public void onEventResize(ScheduleEntryResizeEvent event) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event resized",
				"Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());

		addMessage(message);
	}

	public void onViewChange(SelectEvent e) {
		String view = e.getObject().toString();
		System.err.println(view);
	}

	public void onDateViewChange(DateViewChangeEvent e) {
		int month = e.getMonth();
		System.err.println(month);
	}
	
	/* ==========================================================================================================================
	 * Create Chart Models
	 * ==========================================================================================================================*/

	public void createTypeDonutModel() {
		typeDonutModel = new DonutChartModel();
		ChartData data = new ChartData();

		DonutChartDataSet dataSet = new DonutChartDataSet();
		List<Number> values = new ArrayList<>();
		List<String> bgColors = new ArrayList<>();
		List<String> labels = new ArrayList<>();
		
		for (BillSummary billSummary : myBills) {
			values.add(billSummary.getTotalBilled());
			labels.add(billSummary.getCycleType());
			bgColors.add(typeColorMap.get(billSummary.getCycleType()));
		}
		
		dataSet.setData(values);
		dataSet.setBackgroundColor(bgColors);
		data.addChartDataSet(dataSet);
		data.setLabels(labels);

		typeDonutModel.setData(data);
	}
	
	public void createCategoryDonutModel() {

		Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
		categoryDonutModel = new DonutChartModel();
		ChartData data = new ChartData();

		DonutChartDataSet dataSet = new DonutChartDataSet();

		for (BillItemSummary item : myBillItems) {
			String category = item.getTypeCode();
			BigDecimal amount = item.getBilledAmount();

			BigDecimal total = map.get(category);

			if (total == null || total.compareTo(BigDecimal.ZERO) == 0) {
				map.put(category, amount);
				continue;
			}

			total = total.add(amount);
			map.put(category, total);
		}

		List<Number> values = new ArrayList<>();
		List<String> bgColors = new ArrayList<>();
		List<String> labels = new ArrayList<>();

		for (Entry<String, BigDecimal> entry : map.entrySet()) {
			String category = entry.getKey();
			BigDecimal amount = entry.getValue();

			values.add(amount);
			bgColors.add(categoryColorMap.get(category));
			labels.add(category);

		}

		dataSet.setData(values);
		dataSet.setBackgroundColor(bgColors);
		data.addChartDataSet(dataSet);
		data.setLabels(labels);

		categoryDonutModel.setData(data);
		
		System.err.println("CREATE DONUT");
	}
	
	/* ==========================================================================================================================
	 * Helper Methods
	 * ==========================================================================================================================*/
	
	private void addMessage(FacesMessage message) {
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
	/* ==========================================================================================================================
	 * Accessors
	 * ==========================================================================================================================*/
	
	public List<BillSummary> getMyBills() {
		return myBills;
	}

	public void setMyBills(List<BillSummary> myBills) {
		this.myBills = myBills;
	}

	public List<BillItemSummary> getMyBillItems() {
		return myBillItems;
	}

	public void setMyBillItems(List<BillItemSummary> myBillItems) {
		this.myBillItems = myBillItems;
	}

	public ScheduleModel getEventModel() {
		return eventModel;
	}

	public void setEventModel(ScheduleModel eventModel) {
		this.eventModel = eventModel;
	}

	public BillScheduleEvent getEvent() {
		return event;
	}

	public void setEvent(BillScheduleEvent event) {
		this.event = event;
	}
	
	public DonutChartModel getTypeDonutModel() {
		return typeDonutModel;
	}

	public void setTypeDonutModel(DonutChartModel typeDonutModel) {
		this.typeDonutModel = typeDonutModel;
	}

	public DonutChartModel getCategoryDonutModel() {
		return categoryDonutModel;
	}

	public void setCategoryDonutModel(DonutChartModel categoryDonutModel) {
		this.categoryDonutModel = categoryDonutModel;
	}

	public ScheduleModel getLazyEventModel() {
		return lazyEventModel;
	}

	public void setLazyEventModel(LazyBillScheduleModel lazyEventModel) {
		this.lazyEventModel = lazyEventModel;
	}

	/*
	 * public void changeMonth(String month) { Schedule schedule = (Schedule)
	 * FacesContext.getCurrentInstance().getViewRoot().findComponent(
	 * "myBillForm:myschedule"); ScheduleModel model = (ScheduleModel)
	 * schedule.getValue(); CommandButton button = (CommandButton)
	 * FacesContext.getCurrentInstance().getViewRoot().findComponent(
	 * "myBillForm:currentDate");
	 * 
	 * Calendar cal = Calendar.getInstance(); Calendar today = today();
	 * cal.setTime((Date)schedule.getInitialDate());
	 * 
	 * if( month.equals("previous") ) { cal.add(Calendar.MONTH, -1); if(
	 * cal.get(Calendar.MONTH) == today.get(Calendar.MONTH) )
	 * button.setDisabled(true); else button.setDisabled(false); } else if (
	 * month.equals("next") ) { cal.add(Calendar.MONTH, 1); if(
	 * cal.get(Calendar.MONTH) == today.get(Calendar.MONTH) )
	 * button.setDisabled(true); else button.setDisabled(false); } else { cal =
	 * today; button.setDisabled(true); }
	 * 
	 * schedule.setInitialDate(cal.getTime());
	 * 
	 * LazyScheduleModel lazyModel = ((LazyScheduleModel) model); lazyModel.clear();
	 * }
	 */
}
