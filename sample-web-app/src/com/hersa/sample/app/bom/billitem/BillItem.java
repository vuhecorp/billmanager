package com.hersa.sample.app.bom.billitem;

import java.math.BigDecimal;
import java.util.Date;

import com.hersa.sample.app.dao.billitem.BillItemDTO;

public class BillItem {

	protected BillItemDTO dto;
	
	public BillItem() {
		initialize();
	}

	public BillItem(BillItemDTO dto) {
		initialize();
		this.dto = dto;
	}
	private void initialize() {
		this.dto = new BillItemDTO();
	}

	public BillItemDTO getDto() {
		return dto;
	}

	public void setDto(BillItemDTO dto) {
		this.dto = dto;
	}

	public BigDecimal getAmount() {
		return dto.getAmount();
	}

	public void setAmount(BigDecimal value) {
		dto.setAmount(value);
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

	public String getInfo() {
		return dto.getInfo();
	}
	
	/*== delegates ==*/
	
}
