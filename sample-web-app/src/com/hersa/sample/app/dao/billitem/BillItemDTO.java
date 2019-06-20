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
package com.hersa.sample.app.dao.billitem;
import com.hecorp.api.dao.AbstractD;
import java.io.Serializable;
import java.math.BigDecimal;
public class BillItemDTO extends AbstractD  implements Serializable {

private static final long serialVersionUID = 1L;
private BigDecimal _Amount;

private long _BillId;

private String _CreatedBy;

private java.util.Date _CreatedOn;

private java.util.Date _DateDue;

private String _Description;

private String _EntityCode;

private long _Id;

private String _ModifiedBy;

private java.util.Date _ModifiedOn;

private String _Name;

private String _PayTo;

private String _RecurringCode;

private String _Status;

private java.util.Date _StatusDate;

private long _TemplateId;

private java.util.Date _TimeDue;

private String _TypeCode;



public BigDecimal getAmount() { 
  return _Amount; 
}
public void setAmount(BigDecimal value) { 
  _Amount = value; 
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

public String getName() { 
  return _Name; 
}
public void setName(String value) { 
  _Name = value; 
}

public String getPayTo() { 
  return _PayTo; 
}
public void setPayTo(String value) { 
  _PayTo = value; 
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

public String getInfo() {
 StringBuffer buf = new StringBuffer();
  buf.append("Amount=" + _Amount + "| "); 
  buf.append("BillId=" + _BillId + "| "); 
  buf.append("CreatedBy=" + _CreatedBy + "| "); 
  buf.append("CreatedOn=" + _CreatedOn + "| "); 
  buf.append("DateDue=" + _DateDue + "| "); 
  buf.append("Description=" + _Description + "| "); 
  buf.append("EntityCode=" + _EntityCode + "| "); 
  buf.append("Id=" + _Id + "| "); 
  buf.append("ModifiedBy=" + _ModifiedBy + "| "); 
  buf.append("ModifiedOn=" + _ModifiedOn + "| "); 
  buf.append("Name=" + _Name + "| "); 
  buf.append("PayTo=" + _PayTo + "| "); 
  buf.append("RecurringCode=" + _RecurringCode + "| "); 
  buf.append("Status=" + _Status + "| "); 
  buf.append("StatusDate=" + _StatusDate + "| "); 
  buf.append("TemplateId=" + _TemplateId + "| "); 
  buf.append("TimeDue=" + _TimeDue + "| "); 
  buf.append("TypeCode=" + _TypeCode + "| "); 
  return buf.toString();  }}
/***************** end *********************/ 
