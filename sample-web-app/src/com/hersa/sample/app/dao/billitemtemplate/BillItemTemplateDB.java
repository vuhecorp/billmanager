package com.hersa.sample.app.dao.billitemtemplate;
public final class BillItemTemplateDB   {
  public static final String ORDERBY_ACTIVE = "ACTIVE";    
 
  public static final String ORDERBY_AMOUNT = "AMOUNT";    
 
  public static final String ORDERBY_CREATEDBY = "CREATED_BY";    
 
  public static final String ORDERBY_CREATEDON = "CREATED_ON";    
 
  public static final String ORDERBY_DAYDUE = "DAY_DUE";    
 
  public static final String ORDERBY_DESCRIPTION = "DESCRIPTION";    
 
  public static final String ORDERBY_ENTITYCODE = "ENTITY_CODE";    
 
  public static final String ORDERBY_FROMDATE = "FROM_DATE";    
 
  public static final String ORDERBY_ID = "ID";    
 
  public static final String ORDERBY_MODIFIEDBY = "MODIFIED_BY";    
 
  public static final String ORDERBY_MODIFIEDON = "MODIFIED_ON";    
 
  public static final String ORDERBY_NAME = "NAME";    
 
  public static final String ORDERBY_PAYTO = "PAY_TO";    
 
  public static final String ORDERBY_QUARTZEXP = "QUARTZ_EXP";    
 
  public static final String ORDERBY_RECURRING = "RECURRING";    
 
  public static final String ORDERBY_RECURRINGCODE = "RECURRING_CODE";    
 
  public static final String ORDERBY_TIMEDUE = "TIME_DUE";    
 
  public static final String ORDERBY_TODATE = "TO_DATE";    
 
  public static final String ORDERBY_TYPECODE = "TYPE_CODE";    
 
  public static final String ORDERBY_USERNAME = "USERNAME";    
 
      
  private static final int DESC = 0;    
  private static final int ASC  = 1;    
  private String orderBys = ""; 
      
  /** Allows the user to easily construct a list of ORDER BY columns   
   *  Example:   
   *      BillItemTemplateDB helper = new BillItemTemplateDB();   
   *      String order = helper.add(ORDERBY_DISPLAYNAME).add(ORDERBY_DISPLAYORDER).toString();   
   * **/   
    public  BillItemTemplateDB add(String column, int type) {   
        if (orderBys.length() > 0) {   
            orderBys += ",";   
        }   
        orderBys += column;   
        if (type == DESC) {   
            orderBys += " DESC";   
        }   
        return this;             
    }   
    public  BillItemTemplateDB add(String column) {   
        return add(column, ASC);             
    }   
    public String toString() {   
        return orderBys;   
    }   
      
} 
