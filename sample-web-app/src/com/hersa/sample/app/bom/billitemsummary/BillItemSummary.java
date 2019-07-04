package com.hersa.sample.app.bom.billitemsummary;

import java.math.BigDecimal;
import java.util.Date;

import com.hersa.sample.app.dao.billitemsummary.VBillItemSummaryDTO;

public class BillItemSummary {

	protected VBillItemSummaryDTO dto;
	
	
	public BillItemSummary() {
		initialize();
	}

	public BillItemSummary(VBillItemSummaryDTO dto) {
		initialize();
		this.dto = dto;
	}
	private void initialize() {
		this.dto = new VBillItemSummaryDTO();
	}

	public VBillItemSummaryDTO getDto() {
		return dto;
	}

	public void setDto(VBillItemSummaryDTO dto) {
		this.dto = dto;
	}

	/*==============
	 * Delegates
	 *==============*/
	public int getActive() {
		return dto.getActive();
	}

	public void setActive(int value) {
		dto.setActive(value);
	}

	public BigDecimal getBilledAmount() {
		return dto.getBilledAmount();
	}

	public void setBilledAmount(BigDecimal value) {
		dto.setBilledAmount(value);
	}

	public long getBillId() {
		return dto.getBillId();
	}

	public void setBillId(long value) {
		dto.setBillId(value);
	}

	public String getCreatedBy() {
		return dto.getCreatedBy();
	}

	public void setCreatedBy(String value) {
		dto.setCreatedBy(value);
	}

	public Date getCreatedOn() {
		return dto.getCreatedOn();
	}

	public void setCreatedOn(Date value) {
		dto.setCreatedOn(value);
	}

	public Date getDateDue() {
		return dto.getDateDue();
	}

	public void setDateDue(Date value) {
		dto.setDateDue(value);
	}

	public String getDescription() {
		return dto.getDescription();
	}

	public void setDescription(String value) {
		dto.setDescription(value);
	}

	public String getEntityCode() {
		return dto.getEntityCode();
	}

	public void setEntityCode(String value) {
		dto.setEntityCode(value);
	}

	public long getId() {
		return dto.getId();
	}

	public void setId(long value) {
		dto.setId(value);
	}

	public String getModifiedBy() {
		return dto.getModifiedBy();
	}

	public void setModifiedBy(String value) {
		dto.setModifiedBy(value);
	}

	public Date getModifiedOn() {
		return dto.getModifiedOn();
	}

	public void setModifiedOn(Date value) {
		dto.setModifiedOn(value);
	}

	public String getName() {
		return dto.getName();
	}

	public void setName(String value) {
		dto.setName(value);
	}

	public BigDecimal getPaidAmount() {
		return dto.getPaidAmount();
	}

	public void setPaidAmount(BigDecimal value) {
		dto.setPaidAmount(value);
	}

	public String getPayTo() {
		return dto.getPayTo();
	}

	public void setPayTo(String value) {
		dto.setPayTo(value);
	}

	public String getRecurringCode() {
		return dto.getRecurringCode();
	}

	public void setRecurringCode(String value) {
		dto.setRecurringCode(value);
	}

	public String getStatus() {
		return dto.getStatus();
	}

	public void setStatus(String value) {
		dto.setStatus(value);
	}

	public Date getStatusDate() {
		return dto.getStatusDate();
	}

	public void setStatusDate(Date value) {
		dto.setStatusDate(value);
	}

	public long getTemplateId() {
		return dto.getTemplateId();
	}

	public void setTemplateId(long value) {
		dto.setTemplateId(value);
	}

	public Date getTimeDue() {
		return dto.getTimeDue();
	}

	public void setTimeDue(Date value) {
		dto.setTimeDue(value);
	}

	public String getTypeCode() {
		return dto.getTypeCode();
	}

	public void setTypeCode(String value) {
		dto.setTypeCode(value);
	}

	public String getUsername() {
		return dto.getUsername();
	}

	public void setUsername(String value) {
		dto.setUsername(value);
	}

	public String getInfo() {
		return dto.getInfo();
	}

	public int getDay() {
		return dto.getDay();
	}

	public void setDay(int value) {
		dto.setDay(value);
	}

	public int getMonth() {
		return dto.getMonth();
	}

	public void setMonth(int value) {
		dto.setMonth(value);
	}

	public int getWeek() {
		return dto.getWeek();
	}

	public void setWeek(int value) {
		dto.setWeek(value);
	}

	public int getYear() {
		return dto.getYear();
	}

	public void setYear(int value) {
		dto.setYear(value);
	}

	public int getRecurring() {
		return dto.getRecurring();
	}

	public void setRecurring(int value) {
		dto.setRecurring(value);
	}
	
	public boolean isRecurring() {
		return this.dto.getRecurring() == 1;
	}
	
	public void setRecurring(boolean recurring) {
		this.dto.setRecurring(recurring ? 1 : 0);
	}

}
