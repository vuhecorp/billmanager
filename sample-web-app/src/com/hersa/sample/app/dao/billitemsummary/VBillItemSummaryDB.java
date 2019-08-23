package com.hersa.sample.app.dao.billitemsummary;
public final class VBillItemSummaryDB   {
  public static final String ORDERBY_ACTIVE = "ACTIVE";    
 
  public static final String ORDERBY_BILLEDAMOUNT = "BILLED_AMOUNT";    
 
  public static final String ORDERBY_BILLID = "BILL_ID";    
 
  public static final String ORDERBY_CREATEDBY = "CREATED_BY";    
 
  public static final String ORDERBY_CREATEDON = "CREATED_ON";    
 
  public static final String ORDERBY_DATEDUE = "DATE_DUE";    
 
  public static final String ORDERBY_DAY = "DAY";    
 
  public static final String ORDERBY_DESCRIPTION = "DESCRIPTION";    
 
  public static final String ORDERBY_DUEON = "DUE_ON";    
 
  public static final String ORDERBY_ENTITYCODE = "ENTITY_CODE";    
 
  public static final String ORDERBY_ID = "ID";    
 
  public static final String ORDERBY_MODIFIEDBY = "MODIFIED_BY";    
 
  public static final String ORDERBY_MODIFIEDON = "MODIFIED_ON";    
 
  public static final String ORDERBY_MONTH = "MONTH";    
 
  public static final String ORDERBY_NAME = "NAME";    
 
  public static final String ORDERBY_PAIDAMOUNT = "PAID_AMOUNT";    
 
  public static final String ORDERBY_PAYTO = "PAY_TO";    
 
  public static final String ORDERBY_RECURRING = "RECURRING";    
 
  public static final String ORDERBY_RECURRINGCODE = "RECURRING_CODE";    
 
  public static final String ORDERBY_STATUS = "STATUS";    
 
  public static final String ORDERBY_STATUSDATE = "STATUS_DATE";    
 
  public static final String ORDERBY_TEMPLATEID = "TEMPLATE_ID";    
 
  public static final String ORDERBY_TIMEDUE = "TIME_DUE";    
 
  public static final String ORDERBY_TYPECODE = "TYPE_CODE";    
 
  public static final String ORDERBY_USERNAME = "USERNAME";    
 
  public static final String ORDERBY_WEEK = "WEEK";    
 
  public static final String ORDERBY_YEAR = "YEAR";    
 
  private static final int DESC = 0;    
  private static final int ASC  = 1;    
  private String orderBys = ""; 
      
  /** Allows the user to easily construct a list of ORDER BY columns   
   *  Example:   
   *      VBillItemSummaryDB helper = new VBillItemSummaryDB();   
   *      String order = helper.add(ORDERBY_DISPLAYNAME).add(ORDERBY_DISPLAYORDER).toString();   
   * **/   
    public  VBillItemSummaryDB add(String column, int type) {   
        if (orderBys.length() > 0) {   
            orderBys += ",";   
        }   
        orderBys += column;   
        if (type == DESC) {   
            orderBys += " DESC";   
        }   
        return this;             
    }   
    public  VBillItemSummaryDB add(String column) {   
        return add(column, ASC);             
    }   
    public String toString() {   
        return orderBys;   
    }   
      
} 
