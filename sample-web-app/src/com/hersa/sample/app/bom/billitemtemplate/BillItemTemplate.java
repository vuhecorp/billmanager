package com.hersa.sample.app.bom.billitemtemplate;

import java.math.BigDecimal;
import java.util.Date;

import com.hecorp.api.dao.BOM;
import com.hersa.sample.app.dao.billitemtemplate.BillItemTemplateDTO;

public class BillItemTemplate implements BOM {

	protected BillItemTemplateDTO dto;
	
	public BillItemTemplate() {
		initialize();
	}
	
	public BillItemTemplate(BillItemTemplateDTO dto) {
		initialize();
		this.dto = dto;
	}

	private void initialize() {
		this.dto = new BillItemTemplateDTO();
	}
	
	public BillItemTemplateDTO getDto() {
		return dto;
	}
	
	public void setDto(BillItemTemplateDTO dto) {
		this.dto = dto;
	}
	
/* ============
 * Delegates
 * ============*/
	public BigDecimal getAmount() {
		return dto.getAmount();
	}

	public void setAmount(BigDecimal value) {
		dto.setAmount(value);
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

	public String getDescription() {
		return dto.getDescription();
	}

	public void setDescription(String value) {
		dto.setDescription(value);
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

	public String getRecurringCode() {
		return dto.getRecurringCode();
	}

	public void setRecurringCode(String value) {
		dto.setRecurringCode(value);
	}

	public String getTypeCode() {
		return dto.getTypeCode();
	}

	public void setTypeCode(String value) {
		dto.setTypeCode(value);
	}

	public String getInfo() {
		return dto.getInfo();
	}

	public int getDayDue() {
		return dto.getDayDue();
	}

	public void setDayDue(int value) {
		dto.setDayDue(value);
	}

	public String getEntityCode() {
		return dto.getEntityCode();
	}

	public void setEntityCode(String value) {
		dto.setEntityCode(value);
	}

	public String getPayTo() {
		return dto.getPayTo();
	}

	public void setPayTo(String value) {
		dto.setPayTo(value);
	}

	public String getQuartzExp() {
		return dto.getQuartzExp();
	}

	public void setQuartzExp(String value) {
		dto.setQuartzExp(value);
	}

	public int getActive() {
		return dto.getActive();
	}

	public void setActive(int value) {
		dto.setActive(value);
	}

	public Date getFromDate() {
		return dto.getFromDate();
	}

	public void setFromDate(Date value) {
		dto.setFromDate(value);
	}

	public Date getToDate() {
		return dto.getToDate();
	}

	public void setToDate(Date value) {
		dto.setToDate(value);
	}

	public String getUsername() {
		return dto.getUsername();
	}

	public void setUsername(String value) {
		dto.setUsername(value);
	}

	public int getRecurring() {
		return dto.getRecurring();
	}

	public void setRecurring(int value) {
		dto.setRecurring(value);
	}

	public Date getTimeDue() {
		return dto.getTimeDue();
	}

	public void setTimeDue(Date value) {
		dto.setTimeDue(value);
	}
	
	/* =================
	 * delegates
	 * ================*/
}
