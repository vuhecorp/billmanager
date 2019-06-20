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
package com.hersa.sample.app.dao.bill;
import com.hecorp.api.dao.AbstractD;
import java.io.Serializable;
import java.math.BigDecimal;
public class BillDTO extends AbstractD  implements Serializable {

private static final long serialVersionUID = 1L;
private int _Active;

private String _CreatedBy;

private java.util.Date _CreatedOn;

private String _CycleType;

private int _Day;

private String _Descriptor;

private java.util.Date _EndDate;

private long _Id;

private String _ModifiedBy;

private java.util.Date _ModifiedOn;

private int _Month;

private java.util.Date _StartDate;

private String _Status;

private java.util.Date _StatusDate;

private String _Username;

private int _Week;

private int _Year;



public int getActive() { 
  return _Active; 
}
public void setActive(int value) { 
  _Active = value; 
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

public String getCycleType() { 
  return _CycleType; 
}
public void setCycleType(String value) { 
  _CycleType = value; 
}

public int getDay() { 
  return _Day; 
}
public void setDay(int value) { 
  _Day = value; 
}

public String getDescriptor() { 
  return _Descriptor; 
}
public void setDescriptor(String value) { 
  _Descriptor = value; 
}

public java.util.Date getEndDate() { 
  return _EndDate; 
}
public void setEndDate(java.util.Date value) { 
  _EndDate = value; 
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

public java.util.Date getStartDate() { 
  return _StartDate; 
}
public void setStartDate(java.util.Date value) { 
  _StartDate = value; 
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
  buf.append("CreatedBy=" + _CreatedBy + "| "); 
  buf.append("CreatedOn=" + _CreatedOn + "| "); 
  buf.append("CycleType=" + _CycleType + "| "); 
  buf.append("Day=" + _Day + "| "); 
  buf.append("Descriptor=" + _Descriptor + "| "); 
  buf.append("EndDate=" + _EndDate + "| "); 
  buf.append("Id=" + _Id + "| "); 
  buf.append("ModifiedBy=" + _ModifiedBy + "| "); 
  buf.append("ModifiedOn=" + _ModifiedOn + "| "); 
  buf.append("Month=" + _Month + "| "); 
  buf.append("StartDate=" + _StartDate + "| "); 
  buf.append("Status=" + _Status + "| "); 
  buf.append("StatusDate=" + _StatusDate + "| "); 
  buf.append("Username=" + _Username + "| "); 
  buf.append("Week=" + _Week + "| "); 
  buf.append("Year=" + _Year + "| "); 
  return buf.toString();  }}
/***************** end *********************/ 
