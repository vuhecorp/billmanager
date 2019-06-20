package com.hersa.sample.app.dao.billitem;
public final class BillItemDB   {
  public static final String ORDERBY_AMOUNT = "AMOUNT";    
 
  public static final String ORDERBY_BILLID = "BILL_ID";    
 
  public static final String ORDERBY_CREATEDBY = "CREATED_BY";    
 
  public static final String ORDERBY_CREATEDON = "CREATED_ON";    
 
  public static final String ORDERBY_DATEDUE = "DATE_DUE";    
 
  public static final String ORDERBY_DESCRIPTION = "DESCRIPTION";    
 
  public static final String ORDERBY_ENTITYCODE = "ENTITY_CODE";    
 
  public static final String ORDERBY_ID = "ID";    
 
  public static final String ORDERBY_MODIFIEDBY = "MODIFIED_BY";    
 
  public static final String ORDERBY_MODIFIEDON = "MODIFIED_ON";    
 
  public static final String ORDERBY_NAME = "NAME";    
 
  public static final String ORDERBY_PAYTO = "PAY_TO";    
 
  public static final String ORDERBY_RECURRINGCODE = "RECURRING_CODE";    
 
  public static final String ORDERBY_STATUS = "STATUS";    
 
  public static final String ORDERBY_STATUSDATE = "STATUS_DATE";    
 
  public static final String ORDERBY_TEMPLATEID = "TEMPLATE_ID";    
 
  public static final String ORDERBY_TIMEDUE = "TIME_DUE";    
 
  public static final String ORDERBY_TYPECODE = "TYPE_CODE";    
 
      
  private static final int DESC = 0;    
  private static final int ASC  = 1;    
  private String orderBys = ""; 
      
  /** Allows the user to easily construct a list of ORDER BY columns   
   *  Example:   
   *      BillItemDB helper = new BillItemDB();   
   *      String order = helper.add(ORDERBY_DISPLAYNAME).add(ORDERBY_DISPLAYORDER).toString();   
   * **/   
    public  BillItemDB add(String column, int type) {   
        if (orderBys.length() > 0) {   
            orderBys += ",";   
        }   
        orderBys += column;   
        if (type == DESC) {   
            orderBys += " DESC";   
        }   
        return this;             
    }   
    public  BillItemDB add(String column) {   
        return add(column, ASC);             
    }   
    public String toString() {   
        return orderBys;   
    }   
      
} 
