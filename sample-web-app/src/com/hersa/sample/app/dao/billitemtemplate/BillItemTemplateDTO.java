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
package com.hersa.sample.app.dao.billitemtemplate;
import com.hecorp.api.dao.AbstractD;
import java.io.Serializable;
import java.math.BigDecimal;
public class BillItemTemplateDTO extends AbstractD  implements Serializable {

private static final long serialVersionUID = 1L;
private int _Active;

private BigDecimal _Amount;

private String _CreatedBy;

private java.util.Date _CreatedOn;

private int _DayDue;

private String _Description;

private String _EntityCode;

private java.util.Date _FromDate;

private long _Id;

private String _ModifiedBy;

private java.util.Date _ModifiedOn;

private String _Name;

private String _PayTo;

private String _QuartzExp;

private String _RecurringCode;

private java.util.Date _ToDate;

private String _TypeCode;

private String _Username;



public int getActive() { 
  return _Active; 
}
public void setActive(int value) { 
  _Active = value; 
}

public BigDecimal getAmount() { 
  return _Amount; 
}
public void setAmount(BigDecimal value) { 
  _Amount = value; 
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

public int getDayDue() { 
  return _DayDue; 
}
public void setDayDue(int value) { 
  _DayDue = value; 
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

public java.util.Date getFromDate() { 
  return _FromDate; 
}
public void setFromDate(java.util.Date value) { 
  _FromDate = value; 
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

public String getQuartzExp() { 
  return _QuartzExp; 
}
public void setQuartzExp(String value) { 
  _QuartzExp = value; 
}

public String getRecurringCode() { 
  return _RecurringCode; 
}
public void setRecurringCode(String value) { 
  _RecurringCode = value; 
}

public java.util.Date getToDate() { 
  return _ToDate; 
}
public void setToDate(java.util.Date value) { 
  _ToDate = value; 
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

public String getInfo() {
 StringBuffer buf = new StringBuffer();
  buf.append("Active=" + _Active + "| "); 
  buf.append("Amount=" + _Amount + "| "); 
  buf.append("CreatedBy=" + _CreatedBy + "| "); 
  buf.append("CreatedOn=" + _CreatedOn + "| "); 
  buf.append("DayDue=" + _DayDue + "| "); 
  buf.append("Description=" + _Description + "| "); 
  buf.append("EntityCode=" + _EntityCode + "| "); 
  buf.append("FromDate=" + _FromDate + "| "); 
  buf.append("Id=" + _Id + "| "); 
  buf.append("ModifiedBy=" + _ModifiedBy + "| "); 
  buf.append("ModifiedOn=" + _ModifiedOn + "| "); 
  buf.append("Name=" + _Name + "| "); 
  buf.append("PayTo=" + _PayTo + "| "); 
  buf.append("QuartzExp=" + _QuartzExp + "| "); 
  buf.append("RecurringCode=" + _RecurringCode + "| "); 
  buf.append("ToDate=" + _ToDate + "| "); 
  buf.append("TypeCode=" + _TypeCode + "| "); 
  buf.append("Username=" + _Username + "| "); 
  return buf.toString();  }}
/***************** end *********************/ 
