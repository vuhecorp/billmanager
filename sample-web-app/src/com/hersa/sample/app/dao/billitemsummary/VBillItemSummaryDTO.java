/*  
 * ===========================================================================  
 *                     Proprietary Rights Notice  
 * ===========================================================================  
 * All rights reserved.  This document contains valuable and proprietary  
 * properties of Hersa Corp.  This document embodies substantial  
 * creative works and confidential information, ideas and expressions, no  
 * part of which may be reproduced or transmitted IN ANY FORM OR BY ANY MEANS,  
 * electronic, mechanical or otherwise, including but not limited to photo-  
 * copying and recording or in connection with any information storage or  
 * retrieval system without the express written permission of Easy Access Inc.  
 * ===========================================================================  
 */  
package com.hersa.sample.app.dao.billitemsummary;
import com.hecorp.api.dao.AbstractD;
import java.io.Serializable;
import java.math.BigDecimal;
public class VBillItemSummaryDTO extends AbstractD  implements Serializable {

private static final long serialVersionUID = 1L;
private int _Active;

private BigDecimal _BilledAmount;

private long _BillId;

private String _CreatedBy;

private java.util.Date _CreatedOn;

private java.util.Date _DateDue;

private int _Day;

private String _Description;

private String _EntityCode;

private long _Id;

private String _ModifiedBy;

private java.util.Date _ModifiedOn;

private int _Month;

private String _Name;

private BigDecimal _PaidAmount;

private String _PayTo;

private int _Recurring;

private String _RecurringCode;

private String _Status;

private java.util.Date _StatusDate;

private long _TemplateId;

private java.util.Date _TimeDue;

private String _TypeCode;

private String _Username;

private int _Week;

private int _Year;




public int getActive() { 
  return _Active; 
}
public void setActive(int value) { 
  _Active = value; 
}

public BigDecimal getBilledAmount() { 
  return _BilledAmount; 
}
public void setBilledAmount(BigDecimal value) { 
  _BilledAmount = value; 
}

public long getBillId() { 
  return _BillId; 
}
public void setBillId(long value) { 
  _BillId = value; 
}

public String getCreatedBy() { 
  return _CreatedBy; 
}
public void setCreatedBy(String value) { 
  _CreatedBy = value; 
}

public java.util.Date getCreatedOn() { 
  return _CreatedOn; 
}
public void setCreatedOn(java.util.Date value) { 
  _CreatedOn = value; 
}

public java.util.Date getDateDue() { 
  return _DateDue; 
}
public void setDateDue(java.util.Date value) { 
  _DateDue = value; 
}

public int getDay() { 
  return _Day; 
}
public void setDay(int value) { 
  _Day = value; 
}

public String getDescription() { 
  return _Description; 
}
public void setDescription(String value) { 
  _Description = value; 
}

public String getEntityCode() { 
  return _EntityCode; 
}
public void setEntityCode(String value) { 
  _EntityCode = value; 
}

public long getId() { 
  return _Id; 
}
public void setId(long value) { 
  _Id = value; 
}

public String getModifiedBy() { 
  return _ModifiedBy; 
}
public void setModifiedBy(String value) { 
  _ModifiedBy = value; 
}

public java.util.Date getModifiedOn() { 
  return _ModifiedOn; 
}
public void setModifiedOn(java.util.Date value) { 
  _ModifiedOn = value; 
}

public int getMonth() { 
  return _Month; 
}
public void setMonth(int value) { 
  _Month = value; 
}

public String getName() { 
  return _Name; 
}
public void setName(String value) { 
  _Name = value; 
}

public BigDecimal getPaidAmount() { 
  return _PaidAmount; 
}
public void setPaidAmount(BigDecimal value) { 
  _PaidAmount = value; 
}

public String getPayTo() { 
  return _PayTo; 
}
public void setPayTo(String value) { 
  _PayTo = value; 
}

public int getRecurring() { 
  return _Recurring; 
}
public void setRecurring(int value) { 
  _Recurring = value; 
}

public String getRecurringCode() { 
  return _RecurringCode; 
}
public void setRecurringCode(String value) { 
  _RecurringCode = value; 
}

public String getStatus() { 
  return _Status; 
}
public void setStatus(String value) { 
  _Status = value; 
}

public java.util.Date getStatusDate() { 
  return _StatusDate; 
}
public void setStatusDate(java.util.Date value) { 
  _StatusDate = value; 
}

public long getTemplateId() { 
  return _TemplateId; 
}
public void setTemplateId(long value) { 
  _TemplateId = value; 
}

public java.util.Date getTimeDue() { 
  return _TimeDue; 
}
public void setTimeDue(java.util.Date value) { 
  _TimeDue = value; 
}

public String getTypeCode() { 
  return _TypeCode; 
}
public void setTypeCode(String value) { 
  _TypeCode = value; 
}

public String getUsername() { 
  return _Username; 
}
public void setUsername(String value) { 
  _Username = value; 
}

public int getWeek() { 
  return _Week; 
}
public void setWeek(int value) { 
  _Week = value; 
}

public int getYear() { 
  return _Year; 
}
public void setYear(int value) { 
  _Year = value; 
}



public String getInfo() {
 StringBuffer buf = new StringBuffer();
  buf.append("Active=" + _Active + "| "); 
  buf.append("BilledAmount=" + _BilledAmount + "| "); 
  buf.append("BillId=" + _BillId + "| "); 
  buf.append("CreatedBy=" + _CreatedBy + "| "); 
  buf.append("CreatedOn=" + _CreatedOn + "| "); 
  buf.append("DateDue=" + _DateDue + "| "); 
  buf.append("Day=" + _Day + "| "); 
  buf.append("Description=" + _Description + "| "); 
  buf.append("EntityCode=" + _EntityCode + "| "); 
  buf.append("Id=" + _Id + "| "); 
  buf.append("ModifiedBy=" + _ModifiedBy + "| "); 
  buf.append("ModifiedOn=" + _ModifiedOn + "| "); 
  buf.append("Month=" + _Month + "| "); 
  buf.append("Name=" + _Name + "| "); 
  buf.append("PaidAmount=" + _PaidAmount + "| "); 
  buf.append("PayTo=" + _PayTo + "| "); 
  buf.append("Recurring=" + _Recurring + "| "); 
  buf.append("RecurringCode=" + _RecurringCode + "| "); 
  buf.append("Status=" + _Status + "| "); 
  buf.append("StatusDate=" + _StatusDate + "| "); 
  buf.append("TemplateId=" + _TemplateId + "| "); 
  buf.append("TimeDue=" + _TimeDue + "| "); 
  buf.append("TypeCode=" + _TypeCode + "| "); 
  buf.append("Username=" + _Username + "| "); 
  buf.append("Week=" + _Week + "| "); 
  buf.append("Year=" + _Year + "| "); 
 
  return buf.toString();  }}
/***************** end *********************/ 
