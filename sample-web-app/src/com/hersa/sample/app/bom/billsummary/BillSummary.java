package com.hersa.sample.app.bom.billsummary;

import java.math.BigDecimal;
import java.util.Date;

import com.hecorp.api.dao.BOM;
import com.hersa.sample.app.dao.billsummary.VBillSummaryDTO;

public class BillSummary implements BOM{

	protected VBillSummaryDTO dto;
	
	public BillSummary() {
		initialize();
	}
	public BillSummary(VBillSummaryDTO dto) {
		initialize();
		this.dto = dto;
	}
	private void initialize() {
		this.dto = new VBillSummaryDTO();
	}
	public VBillSummaryDTO getDto() {
		return dto;
	}
	public void setDto(VBillSummaryDTO dto) {
		this.dto = dto;
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
	public String getCycleType() {
		return dto.getCycleType();
	}
	public void setCycleType(String value) {
		dto.setCycleType(value);
	}
	public int getDay() {
		return dto.getDay();
	}
	public void setDay(int value) {
		dto.setDay(value);
	}
	public String getDescriptor() {
		return dto.getDescriptor();
	}
	public void setDescriptor(String value) {
		dto.setDescriptor(value);
	}
	public Date getEndDate() {
		return dto.getEndDate();
	}
	public void setEndDate(Date value) {
		dto.setEndDate(value);
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
	public int getMonth() {
		return dto.getMonth();
	}
	public void setMonth(int value) {
		dto.setMonth(value);
	}
	public Date getStartDate() {
		return dto.getStartDate();
	}
	public void setStartDate(Date value) {
		dto.setStartDate(value);
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
	public BigDecimal getTotalBilled() {
		return dto.getTotalBilled();
	}
	public void setTotalBilled(BigDecimal value) {
		dto.setTotalBilled(value);
	}
	public int getTotalPaid() {
		return dto.getTotalPaid();
	}
	public void setTotalPaid(int value) {
		dto.setTotalPaid(value);
	}
	public String getUsername() {
		return dto.getUsername();
	}
	public void setUsername(String value) {
		dto.setUsername(value);
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
	public String getInfo() {
		return dto.getInfo();
	}
	
	
}
