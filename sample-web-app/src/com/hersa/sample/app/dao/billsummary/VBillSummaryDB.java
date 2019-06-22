package com.hersa.sample.app.dao.billsummary;
public final class VBillSummaryDB   {
  public static final String ORDERBY_CREATEDBY = "CREATED_BY";    
 
  public static final String ORDERBY_CREATEDON = "CREATED_ON";    
 
  public static final String ORDERBY_CYCLETYPE = "CYCLE_TYPE";    
 
  public static final String ORDERBY_DAY = "DAY";    
 
  public static final String ORDERBY_DESCRIPTOR = "DESCRIPTOR";    
 
  public static final String ORDERBY_ENDDATE = "END_DATE";    
 
  public static final String ORDERBY_ID = "ID";    
 
  public static final String ORDERBY_MODIFIEDBY = "MODIFIED_BY";    
 
  public static final String ORDERBY_MODIFIEDON = "MODIFIED_ON";    
 
  public static final String ORDERBY_MONTH = "MONTH";    
 
  public static final String ORDERBY_STARTDATE = "START_DATE";    
 
  public static final String ORDERBY_STATUS = "STATUS";    
 
  public static final String ORDERBY_STATUSDATE = "STATUS_DATE";    
 
  public static final String ORDERBY_TOTALBILLED = "TOTAL_BILLED";    
 
  public static final String ORDERBY_TOTALPAID = "TOTAL_PAID";    
 
  public static final String ORDERBY_USERNAME = "USERNAME";    
 
  public static final String ORDERBY_WEEK = "WEEK";    
 
  public static final String ORDERBY_YEAR = "YEAR";    
 
  
 
      
  private static final int DESC = 0;    
  private static final int ASC  = 1;    
  private String orderBys = ""; 
      
  /** Allows the user to easily construct a list of ORDER BY columns   
   *  Example:   
   *      VBillSummaryDB helper = new VBillSummaryDB();   
   *      String order = helper.add(ORDERBY_DISPLAYNAME).add(ORDERBY_DISPLAYORDER).toString();   
   * **/   
    public  VBillSummaryDB add(String column, int type) {   
        if (orderBys.length() > 0) {   
            orderBys += ",";   
        }   
        orderBys += column;   
        if (type == DESC) {   
            orderBys += " DESC";   
        }   
        return this;             
    }   
    public  VBillSummaryDB add(String column) {   
        return add(column, ASC);             
    }   
    public String toString() {   
        return orderBys;   
    }   
      
} 
