package com.hersa.sample.app.web;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.primefaces.PrimeFaces;
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
import com.hersa.sample.app.bom.billitem.BillItem;
import com.hersa.sample.app.bom.billitemsummary.BillItemSummary;
import com.hersa.sample.app.bom.billitemsummary.BillItemSummaryManager;
import com.hersa.sample.app.bom.billitemtemplate.BillItemTemplate;
import com.hersa.sample.app.bom.billsummary.BillSummary;
import com.hersa.sample.app.bom.billsummary.BillSummaryManager;
import com.hersa.sample.app.schedule.BillScheduleEvent;
import com.hersa.sample.app.schedule.LazyBillScheduleModel;
import com.hersa.sample.app.utils.BillManagerUtils;

@ManagedBean
@ViewScoped
public class MyBillsPage extends AbstractFacesPage implements Serializable {

	/* ==========================================================================================================================
	 * Member Variables
	 * ==========================================================================================================================*/
	
	private static final long serialVersionUID = -2635878856409734237L;
	
	public static final String PARAM_CREATE_BILL_TEMPLATE = "bill.template.obj";
	public static final String PARAM_CREATE_BILL_ITEM     = "bill.item.obj";
	
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
			Map<String, Object> createParams =  onBeforeUpdateSchedule();
			onUpdateSchedule(createParams);
			onAfterUpdateSchedule();
		} catch(ApplicationException e) {
			e.printStackTrace();
			addErrorMessage("eventDetailsForm:eventDetails", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			addErrorMessage("eventDetailsForm:eventDetails", "An unexpected error has occured");
		}
		return response;
	}
	
	/**
	 * Create template, bill item objects. 
	 * Initialize required values.
	 * Verify current cycle.
	 * 
	 * @throws ApplicationException
	 */
	private Map<String, Object> onBeforeUpdateSchedule() throws ApplicationException{
		
		//place objects in param map. we need to access these in the create method
		//in order to transaction the create statements.
		Map<String, Object> createBillItemParams = new HashMap<String, Object>();
		BillItemSummary itemSummary  		     = event.getBillItemSummary();
		
		//if the user specified recurring, we need to create a new template
		//for this bill item.
		if(itemSummary.isRecurring()) {
			BillItemTemplate billTemplateItem = BillItemSummaryManager.convertToTemplate(itemSummary);
			createBillItemParams.put(PARAM_CREATE_BILL_TEMPLATE, billTemplateItem);
		}
		
		//convert the item summary to a BillItem object. 
		BillItem billItem = BillItemSummaryManager.convertToBillItem(itemSummary);
		createBillItemParams.put(PARAM_CREATE_BILL_ITEM, billItem);
		
		//set required fields
		setDueDateFields(billItem);
		
		//verify if current bill cycle exists.
		verifyCurrentBillCycle(billItem);
		
		return createBillItemParams;
	}
	
	/**
	 * Perform update/create logic
	 * @param createParams 
	 * @throws ApplicationException
	 */
	private void onUpdateSchedule(Map<String, Object> createParams) throws ApplicationException{
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Update models
	 * @throws ApplicationException
	 */
	private void onAfterUpdateSchedule() throws ApplicationException{
		addInfoMessage("Your new bill item has been successfully created.");
		PrimeFaces.current().executeScript("PF('eventDialog').hide();");
	}
	
	/**
	 * Determine recurring type date colums according to 
	 * recurring type code. 
	 * @param billItem
	 */
	private void setDueDateFields(BillItem billItem) throws ApplicationException{
		
		String code      = billItem.getRecurringCode();
		DateTime dateDue = new DateTime(billItem.getDateDue().getTime());
		
		billItem.setDay(-1);
		billItem.setWeek(-1);
		billItem.setMonth(dateDue.getMonthOfYear());
		billItem.setYear(dateDue.getYear());
		
		if (Constants.RECURRING_WEEK.equalsIgnoreCase(code)) {
			billItem.setWeek(dateDue.getWeekOfWeekyear());
		}
		
	}

	private void verifyCurrentBillCycle(BillItem billItem) throws ApplicationException{
		String mapKey = billItem.getRecurringCode();
		try {
			Map<Integer, BillSummary> billMap = myBillsMap.get(mapKey);
			
			int listKey = -1;
			
			if(Constants.RECURRING_MONTH.equalsIgnoreCase(mapKey)) {
				listKey = billItem.getMonth();
			}else if (Constants.RECURRING_WEEK.equalsIgnoreCase(mapKey)) {
				listKey = billItem.getWeek();
			}else {
				throw new ApplicationException("Unsupported recurring code encountered.");
			}
			
			int h = 9;
		} catch(ApplicationException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("An error occurred during bill cycle verification.");
		}
		
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

	public String onCancelScheduleBtnClick() {
		String response = "";
		try {
			event = new BillScheduleEvent();
		} catch (Exception e) {
			addErrorMessage("An error occurred during cancel action.");
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
		DateTime dueDate = new DateTime(((Date) selectEvent.getObject()).getTime());
		dueDate = BillManagerUtils.getDateTimeZoneOffset(dueDate);
		
		event = new BillScheduleEvent();
		event.getBillItemSummary().setActive(1);
		event.getBillItemSummary().setDateDue(dueDate.toDate());
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
	
	private void validateStringField(boolean required, int max, int min, String name, String value) throws ApplicationException{
		
		if (required && StringUtils.isBlank(value)) {
			throw new ApplicationException(name + " is a required field.");
		}
		
		boolean validateMax = max > 0;
		boolean validateMin = min > 0;
		
		if (validateMax) {
			if (value.length() > max) {
				throw new ApplicationException(name + " max length is " + max);
			}
		}
		
		if (validateMin) {
			if (value.length() < min) {
				throw new ApplicationException(name + " min length is " + min);
			}
		}
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
