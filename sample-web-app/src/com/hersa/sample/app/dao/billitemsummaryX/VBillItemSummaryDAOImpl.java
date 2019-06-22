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
package com.hersa.sample.app.dao.billitemsummaryX;
import com.hecorp.api.dao.AbstractDAImpl;
import com.hecorp.api.dao.DAOStreamReader;
import com.hecorp.api.dao.ConnectionUtil;      
import com.hecorp.api.dao.DefaultConnectionProvider;      
import java.sql.Connection; 
import java.sql.PreparedStatement; 
import java.sql.Statement;
import java.sql.ResultSet; 
import java.sql.SQLException; 
import java.util.ArrayList; 
import java.util.List; 
import java.math.BigDecimal;      
import java.io.IOException; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.BufferedInputStream; 
import org.apache.log4j.Logger; 
import org.apache.log4j.Level;
import com.hecorp.api.dao.DAOException;
import com.hecorp.api.dao.ServiceLocator;
class VBillItemSummaryDAOImpl extends AbstractDAImpl implements VBillItemSummaryDAO {

  private static final Logger logger = 
      Logger.getLogger(VBillItemSummaryDAOImpl.class);

  protected String tableName = VBillItemSummaryPrefix.PREFIX + "V_BILL_ITEM_SUMMARY"; 
  public String getTable(){
      return "\"" + this.tableName + "\""; 
}
  public void setTable(String table){
      this.tableName = table; 
}
  protected String getSelectSQL(){
      String sql = " SELECT ACTIVE, BILLED_AMOUNT, BILL_ID, CREATED_BY, CREATED_ON, DATE_DUE, DAY, DESCRIPTION, ENTITY_CODE, ID, MODIFIED_BY, MODIFIED_ON, MONTH, NAME, PAID_AMOUNT, PAY_TO, RECURRING_CODE, STATUS, STATUS_DATE, TEMPLATE_ID, TIME_DUE, TYPE_CODE, USERNAME, WEEK, YEAR, ACTIVE, BILLED_AMOUNT, BILL_ID, CREATED_BY, CREATED_ON, DATE_DUE, DAY, DESCRIPTION, ENTITY_CODE, ID, MODIFIED_BY, MODIFIED_ON, MONTH, NAME, PAID_AMOUNT, PAY_TO, RECURRING_CODE, STATUS, STATUS_DATE, TEMPLATE_ID, TIME_DUE, TYPE_CODE, USERNAME, WEEK, YEAR FROM " + getTable() + " " ;
      return sql;
}
  protected String getSelectCountSQL(){
      String sql = " SELECT COUNT(*) FROM " + getTable();
      return sql;
}
  protected String getInsertSQL(){
      String sql = " INSERT INTO " + getTable() + " ( ACTIVE, BILLED_AMOUNT, BILL_ID, CREATED_BY, CREATED_ON, DATE_DUE, DAY, DESCRIPTION, ENTITY_CODE, ID, MODIFIED_BY, MODIFIED_ON, MONTH, NAME, PAID_AMOUNT, PAY_TO, RECURRING_CODE, STATUS, STATUS_DATE, TEMPLATE_ID, TIME_DUE, TYPE_CODE, USERNAME, WEEK, YEAR, ACTIVE, BILLED_AMOUNT, BILL_ID, CREATED_BY, CREATED_ON, DATE_DUE, DAY, DESCRIPTION, ENTITY_CODE, ID, MODIFIED_BY, MODIFIED_ON, MONTH, NAME, PAID_AMOUNT, PAY_TO, RECURRING_CODE, STATUS, STATUS_DATE, TEMPLATE_ID, TIME_DUE, TYPE_CODE, USERNAME, WEEK, YEAR ) VALUES  ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";
      return sql;
}
  protected String getUpdateSQL(){
      String sql = " UPDATE " + getTable() + " SET  ACTIVE = ? , BILLED_AMOUNT = ? , BILL_ID = ? , CREATED_BY = ? , CREATED_ON = ? , DATE_DUE = ? , DAY = ? , DESCRIPTION = ? , ENTITY_CODE = ? , ID = ? , MODIFIED_BY = ? , MODIFIED_ON = ? , MONTH = ? , NAME = ? , PAID_AMOUNT = ? , PAY_TO = ? , RECURRING_CODE = ? , STATUS = ? , STATUS_DATE = ? , TEMPLATE_ID = ? , TIME_DUE = ? , TYPE_CODE = ? , USERNAME = ? , WEEK = ? , YEAR = ? , ACTIVE = ? , BILLED_AMOUNT = ? , BILL_ID = ? , CREATED_BY = ? , CREATED_ON = ? , DATE_DUE = ? , DAY = ? , DESCRIPTION = ? , ENTITY_CODE = ? , ID = ? , MODIFIED_BY = ? , MODIFIED_ON = ? , MONTH = ? , NAME = ? , PAID_AMOUNT = ? , PAY_TO = ? , RECURRING_CODE = ? , STATUS = ? , STATUS_DATE = ? , TEMPLATE_ID = ? , TIME_DUE = ? , TYPE_CODE = ? , USERNAME = ? , WEEK = ? , YEAR = ?  WHERE ";
      return sql;
}
  protected String getUpdateBlobsSQL(){
      String sql = " UPDATE  " + getTable() + "  SET  ACTIVE = ? , BILLED_AMOUNT = ? , BILL_ID = ? , CREATED_BY = ? , CREATED_ON = ? , DATE_DUE = ? , DAY = ? , DESCRIPTION = ? , ENTITY_CODE = ? , ID = ? , MODIFIED_BY = ? , MODIFIED_ON = ? , MONTH = ? , NAME = ? , PAID_AMOUNT = ? , PAY_TO = ? , RECURRING_CODE = ? , STATUS = ? , STATUS_DATE = ? , TEMPLATE_ID = ? , TIME_DUE = ? , TYPE_CODE = ? , USERNAME = ? , WEEK = ? , YEAR = ? , ACTIVE = ? , BILLED_AMOUNT = ? , BILL_ID = ? , CREATED_BY = ? , CREATED_ON = ? , DATE_DUE = ? , DAY = ? , DESCRIPTION = ? , ENTITY_CODE = ? , ID = ? , MODIFIED_BY = ? , MODIFIED_ON = ? , MONTH = ? , NAME = ? , PAID_AMOUNT = ? , PAY_TO = ? , RECURRING_CODE = ? , STATUS = ? , STATUS_DATE = ? , TEMPLATE_ID = ? , TIME_DUE = ? , TYPE_CODE = ? , USERNAME = ? , WEEK = ? , YEAR = ?  WHERE ";
      return sql;
}
  protected String getDeleteSQL(){
      String sql = " DELETE FROM  " + getTable() + "  WHERE  " ;
      return sql;
}
  protected String getDeleteBySQL(){
      String sql = " DELETE FROM " + getTable() ;
      return sql;
}

  public VBillItemSummaryDAOImpl() { 
       factory = new DefaultConnectionProvider( VBillItemSummaryJNDI.NON_XA_DATASOURCE ); 
  } 
    protected Connection openConnection() {  
     if (connectionSetByCaller) return this.con;    
     try {   
         Connection connection = factory.openConnection();   
         if (isReadOnly()) {     
             try {   
                 connection.setReadOnly(isReadOnly());   
             } catch (SQLException e) {      
                 e.printStackTrace();    
             }   
         }   
         return connection;  
       } catch (Exception e) {   
         if (logger.isEnabledFor(Level.ERROR)) {   
             logger.error(e);   
         }   
        try { 	throw new DAOException(e.getMessage(), e);  } catch (DAOException e1) { 	e1.printStackTrace(); }   
     }   
         return con;  
   }    
     
     
 protected void closeConnection(Connection con) {   
     if (!this.persistReadOnly) {            
         this.readOnly = false;          
     }          
     if (connectionSetByCaller) { 
        return ;    
     }    
      try {   
         factory.closeConnection(con);   
     } catch (SQLException e) {   
         logSQLException(e);   
     }   
 }   
     @Override   
     public void setReadOnly(boolean readOnly) {   
         this.readOnly = readOnly;   
     }   

    public void open() {;}  
    public void close() {;}  
    protected Logger getLogger() {  
      return logger; 
    } 

  public final VBillItemSummaryDTO [] listAllVBillItemSummary(String orderByColumn) { 
    String order = "";    
     if (orderByColumn != null && orderByColumn.trim().length() > 0) {    
        order = " ORDER BY " + orderByColumn;     
     }    
    return listVBillItemSummary(null, null, null, order); 
 } 

public final VBillItemSummaryDTO [] listAllVBillItemSummary() { 
    return listVBillItemSummary(null, null, null, null); 
 } 

public final VBillItemSummaryDTO [] customVBillItemSummary(String completeSQL ) { 
   if (logger.isEnabledFor(Level.TRACE)) {     
       logger.trace(">> listVBillItemSummary()");  
   }   
   VBillItemSummaryDTO[] _VBillItemSummary = null;  
   ResultSet rs = null;    
   PreparedStatement stmt = null;  
   Connection con = openConnection();  
   try {   
           stmt = con.prepareStatement(completeSQL);    
           stmt.setMaxRows(getMaxRows());            rs = stmt.executeQuery();       
           if (rs.next()) {        
               List list = new ArrayList();        
               do {        
                   list.add(extractVBillItemSummary(rs));        
               } while (rs.next());        
               _VBillItemSummary =      
                   (VBillItemSummaryDTO[]) list.toArray(new VBillItemSummaryDTO[list.size()]);       
           } else {        
               _VBillItemSummary = new VBillItemSummaryDTO[0];        
           }       
   } catch (SQLException e) {      
           logSQLException(e);     
          try { 	throw new DAOException(e.getMessage(), e);  } catch (DAOException e1) { 	e1.printStackTrace(); }      
   } finally {     
           if (rs != null) {       
               try {       
                   rs.close();     
               } catch (SQLException e) {      
                   logSQLException(e);     
               }       
           }       
           if (stmt != null) {     
               try {       
                   stmt.close();       
               } catch (SQLException e) {      
                   logSQLException(e);     
               }       
           }       
           closeConnection(con);       
   }       
   if (logger.isEnabledFor(Level.TRACE)) {     
           logger.trace("<< findVBillItemSummary()");     
   }       
   return _VBillItemSummary;        
 }  

public final VBillItemSummaryDTO [] listVBillItemSummary(String whereClause, Object[] whereParams, int[] paramTypes, String orderClause ) { 
   if (logger.isEnabledFor(Level.TRACE)) {     
       logger.trace(">> listVBillItemSummary()");  
   }   
   if (paginationStartIndex > 0) {    
      return searchPageableVBillItemSummary(whereClause, whereParams, paramTypes, orderClause, paginationStartIndex, paginationPageSize);  
   }  
   VBillItemSummaryDTO[] _VBillItemSummary = null;  
   ResultSet rs = null;    
   PreparedStatement stmt = null;  
   Connection con = openConnection();  
   if (whereClause == null) {      
       whereClause = "";     
   }           
   if (orderClause == null) {      
       orderClause = "";     
   }                   
   try {   
           stmt = con.prepareStatement(getSelectSQL() + whereClause + orderClause);    
           stmt.setMaxRows(getMaxRows());            if (whereParams != null) {      
               for (int i = 0; i < whereParams.length; i++) {      
                   if (whereParams[i] == null) { 
                       stmt.setNull(i+1, paramTypes[i]);   
                   } else { 
                       stmt.setObject(i+1, whereParams[i], paramTypes[i]);     
                   }  
               }   
           }               
           rs = stmt.executeQuery();       
           if (rs.next()) {        
               List list = new ArrayList();        
               do {        
                   list.add(extractVBillItemSummary(rs));        
               } while (rs.next());        
               _VBillItemSummary =      
                   (VBillItemSummaryDTO[]) list.toArray(new VBillItemSummaryDTO[list.size()]);       
           } else {        
               _VBillItemSummary = new VBillItemSummaryDTO[0];        
           }       
   } catch (SQLException e) {      
           logSQLException(e);     
          try { 	throw new DAOException(e.getMessage(), e);  } catch (DAOException e1) { 	e1.printStackTrace(); }      
   } finally {     
           if (rs != null) {       
               try {       
                   rs.close();     
               } catch (SQLException e) {      
                   logSQLException(e);     
               }       
           }       
           if (stmt != null) {     
               try {       
                   stmt.close();       
               } catch (SQLException e) {      
                   logSQLException(e);     
               }       
           }       
           closeConnection(con);       
   }       
   if (logger.isEnabledFor(Level.TRACE)) {     
           logger.trace("<< findVBillItemSummary()");     
   }       
   return _VBillItemSummary;        
 }  

public final VBillItemSummaryDTO [] searchPageableVBillItemSummary(String whereClause, Object[] whereParams, int[] paramTypes, String orderClause,int startIndex, int pageSize ) { 
   if (logger.isEnabledFor(Level.TRACE)) {     
       logger.trace(">> searchPageableVBillItemSummary()");  
   }   
   VBillItemSummaryDTO[] _VBillItemSummary = null;  
   ResultSet rs = null;    
   PreparedStatement stmt = null;  
   Connection con = openConnection();  
   if (whereClause == null) {      
       whereClause = "";     
   }           
   if (orderClause == null) {      
       orderClause = "";     
   }                   
   try {   
           int maxRowsToFetch = startIndex + pageSize-1;     
           int len = pageSize;		     
           stmt = con.prepareStatement(     
           		getSelectSQL() + whereClause + orderClause,     
           		ResultSet.TYPE_SCROLL_INSENSITIVE,     
           		ResultSet.CONCUR_READ_ONLY);		     
           stmt.setMaxRows(maxRowsToFetch);     
           stmt.setFetchSize(pageSize);     
           if (whereParams != null) {      
               for (int i = 0; i < whereParams.length; i++) {      
                   if (whereParams[i] == null) { 
                       stmt.setNull(i+1, paramTypes[i]);   
                   } else { 
                       stmt.setObject(i+1, whereParams[i], paramTypes[i]);     
                   }  
               }   
           }               
           rs = stmt.executeQuery();       
           if (rs.absolute(startIndex)) {        
               List list = new ArrayList();        
               do {        
                   list.add(extractVBillItemSummary(rs));        
               } while ((rs.next()) && (--len > 0));      
               _VBillItemSummary =      
                   (VBillItemSummaryDTO[]) list.toArray(new VBillItemSummaryDTO[list.size()]);       
           } else {        
               _VBillItemSummary = new VBillItemSummaryDTO[0];        
           }       
   } catch (SQLException e) {      
           logSQLException(e);     
          try { 	throw new DAOException(e.getMessage(), e);  } catch (DAOException e1) { 	e1.printStackTrace(); }      
   } finally {     
           if (rs != null) {       
               try {       
                   rs.close();     
               } catch (SQLException e) {      
                   logSQLException(e);     
               }       
           }       
           if (stmt != null) {     
               try {       
                   stmt.close();       
               } catch (SQLException e) {      
                   logSQLException(e);     
               }       
           }       
           closeConnection(con);       
   }       
   if (logger.isEnabledFor(Level.TRACE)) {     
           logger.trace("<< searchPageableVBillItemSummary()");     
   }       
   return _VBillItemSummary;        
 }  

public final int countVBillItemSummary(String whereClause, Object[] whereParams, int[] paramTypes ) { 
   if (logger.isEnabledFor(Level.TRACE)) {     
       logger.trace(">> countVBillItemSummary()");  
   }   
   int count = 0;    
   ResultSet rs = null;    
   PreparedStatement stmt = null;  
   Connection con = openConnection();  
   if (whereClause == null) {      
       whereClause = "";     
   }           
   try {   
           stmt = con.prepareStatement(getSelectCountSQL() + whereClause );    
           if (whereParams != null) {      
               for (int i = 0; i < whereParams.length; i++) {      
                   if (whereParams[i] == null) { 
                       stmt.setNull(i+1, paramTypes[i]);   
                   } else { 
                       stmt.setObject(i+1, whereParams[i], paramTypes[i]);     
                   }  
               }   
           }               
           rs = stmt.executeQuery();       
           if (rs.next()) {        
               List list = new ArrayList();        
               do {        
                   count = rs.getInt(1);   
               } while (rs.next());        
           }       
   } catch (SQLException e) {      
           logSQLException(e);     
          try { 	throw new DAOException(e.getMessage(), e);  } catch (DAOException e1) { 	e1.printStackTrace(); }      
   } finally {     
           if (rs != null) {       
               try {       
                   rs.close();     
               } catch (SQLException e) {      
                   logSQLException(e);     
               }       
           }       
           if (stmt != null) {     
               try {       
                   stmt.close();       
               } catch (SQLException e) {      
                   logSQLException(e);     
               }       
           }       
           closeConnection(con);       
   }       
   if (logger.isEnabledFor(Level.TRACE)) {     
           logger.trace("<< findVBillItemSummary()");     
   }       
   return count;     
 }  

  public final VBillItemSummaryDTO [] listVBillItemSummaryByActive(int _Active) { 
     return listVBillItemSummaryByActive( _Active, null);     
 } 

  public final VBillItemSummaryDTO [] listVBillItemSummaryByActive(int _Active, String orderByColumn) { 
    String whereClause = " WHERE ACTIVE = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Integer(_Active);   
    types[0] = java.sql.Types.INTEGER;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listVBillItemSummary(whereClause, objs, types, order); 
 } 



  public final int countVBillItemSummaryByActive(int _Active) { 
    String whereClause = " WHERE ACTIVE = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Integer(_Active);   
    types[0] = java.sql.Types.INTEGER;   
    return countVBillItemSummary(whereClause, objs, types); 
 } 




  public final VBillItemSummaryDTO [] listVBillItemSummaryByBilledAmount(BigDecimal _BilledAmount) { 
     return listVBillItemSummaryByBilledAmount( _BilledAmount, null);     
 } 

  public final VBillItemSummaryDTO [] listVBillItemSummaryByBilledAmount(BigDecimal _BilledAmount, String orderByColumn) { 
    String whereClause = " WHERE BILLED_AMOUNT = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _BilledAmount;   
    types[0] = java.sql.Types.DECIMAL;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listVBillItemSummary(whereClause, objs, types, order); 
 } 



  public final int countVBillItemSummaryByBilledAmount(BigDecimal _BilledAmount) { 
    String whereClause = " WHERE BILLED_AMOUNT = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _BilledAmount;   
    types[0] = java.sql.Types.DECIMAL;   
    return countVBillItemSummary(whereClause, objs, types); 
 } 




  public final VBillItemSummaryDTO [] listVBillItemSummaryByBillId(long _BillId) { 
     return listVBillItemSummaryByBillId( _BillId, null);     
 } 

  public final VBillItemSummaryDTO [] listVBillItemSummaryByBillId(long _BillId, String orderByColumn) { 
    String whereClause = " WHERE BILL_ID = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Long(_BillId);   
    types[0] = java.sql.Types.BIGINT;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listVBillItemSummary(whereClause, objs, types, order); 
 } 



  public final int countVBillItemSummaryByBillId(long _BillId) { 
    String whereClause = " WHERE BILL_ID = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Long(_BillId);   
    types[0] = java.sql.Types.BIGINT;   
    return countVBillItemSummary(whereClause, objs, types); 
 } 




  public final VBillItemSummaryDTO [] listVBillItemSummaryByCreatedBy(String _CreatedBy) { 
     return listVBillItemSummaryByCreatedBy( _CreatedBy, null);     
 } 

  public final VBillItemSummaryDTO [] listVBillItemSummaryByCreatedBy(String _CreatedBy, String orderByColumn) { 
    String whereClause = " WHERE CREATED_BY = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _CreatedBy;   
    types[0] = java.sql.Types.VARCHAR;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listVBillItemSummary(whereClause, objs, types, order); 
 } 



  public final int countVBillItemSummaryByCreatedBy(String _CreatedBy) { 
    String whereClause = " WHERE CREATED_BY = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _CreatedBy;   
    types[0] = java.sql.Types.VARCHAR;   
    return countVBillItemSummary(whereClause, objs, types); 
 } 




  public final VBillItemSummaryDTO [] listVBillItemSummaryByCreatedOn(java.util.Date _CreatedOn) { 
     return listVBillItemSummaryByCreatedOn( _CreatedOn, null);     
 } 

  public final VBillItemSummaryDTO [] listVBillItemSummaryByCreatedOn(java.util.Date _CreatedOn, String orderByColumn) { 
    String whereClause = " WHERE CREATED_ON = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.sql.Timestamp(_CreatedOn.getTime());   
    types[0] = java.sql.Types.TIMESTAMP;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listVBillItemSummary(whereClause, objs, types, order); 
 } 



  public final int countVBillItemSummaryByCreatedOn(java.util.Date _CreatedOn) { 
    String whereClause = " WHERE CREATED_ON = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.sql.Timestamp(_CreatedOn.getTime());   
    types[0] = java.sql.Types.TIMESTAMP;   
    return countVBillItemSummary(whereClause, objs, types); 
 } 




  public final VBillItemSummaryDTO [] listVBillItemSummaryByDateDue(java.util.Date _DateDue) { 
     return listVBillItemSummaryByDateDue( _DateDue, null);     
 } 

  public final VBillItemSummaryDTO [] listVBillItemSummaryByDateDue(java.util.Date _DateDue, String orderByColumn) { 
    String whereClause = " WHERE DATE_DUE = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.sql.Timestamp(_DateDue.getTime());   
    types[0] = java.sql.Types.TIMESTAMP;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listVBillItemSummary(whereClause, objs, types, order); 
 } 



  public final int countVBillItemSummaryByDateDue(java.util.Date _DateDue) { 
    String whereClause = " WHERE DATE_DUE = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.sql.Timestamp(_DateDue.getTime());   
    types[0] = java.sql.Types.TIMESTAMP;   
    return countVBillItemSummary(whereClause, objs, types); 
 } 




  public final VBillItemSummaryDTO [] listVBillItemSummaryByDay(int _Day) { 
     return listVBillItemSummaryByDay( _Day, null);     
 } 

  public final VBillItemSummaryDTO [] listVBillItemSummaryByDay(int _Day, String orderByColumn) { 
    String whereClause = " WHERE DAY = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Integer(_Day);   
    types[0] = java.sql.Types.INTEGER;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listVBillItemSummary(whereClause, objs, types, order); 
 } 



  public final int countVBillItemSummaryByDay(int _Day) { 
    String whereClause = " WHERE DAY = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Integer(_Day);   
    types[0] = java.sql.Types.INTEGER;   
    return countVBillItemSummary(whereClause, objs, types); 
 } 




  public final VBillItemSummaryDTO [] listVBillItemSummaryByDescription(String _Description) { 
     return listVBillItemSummaryByDescription( _Description, null);     
 } 

  public final VBillItemSummaryDTO [] listVBillItemSummaryByDescription(String _Description, String orderByColumn) { 
    String whereClause = " WHERE DESCRIPTION = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _Description;   
    types[0] = java.sql.Types.VARCHAR;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listVBillItemSummary(whereClause, objs, types, order); 
 } 



  public final int countVBillItemSummaryByDescription(String _Description) { 
    String whereClause = " WHERE DESCRIPTION = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _Description;   
    types[0] = java.sql.Types.VARCHAR;   
    return countVBillItemSummary(whereClause, objs, types); 
 } 




  public final VBillItemSummaryDTO [] listVBillItemSummaryByEntityCode(String _EntityCode) { 
     return listVBillItemSummaryByEntityCode( _EntityCode, null);     
 } 

  public final VBillItemSummaryDTO [] listVBillItemSummaryByEntityCode(String _EntityCode, String orderByColumn) { 
    String whereClause = " WHERE ENTITY_CODE = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _EntityCode;   
    types[0] = java.sql.Types.VARCHAR;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listVBillItemSummary(whereClause, objs, types, order); 
 } 



  public final int countVBillItemSummaryByEntityCode(String _EntityCode) { 
    String whereClause = " WHERE ENTITY_CODE = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _EntityCode;   
    types[0] = java.sql.Types.VARCHAR;   
    return countVBillItemSummary(whereClause, objs, types); 
 } 




  public final VBillItemSummaryDTO [] listVBillItemSummaryById(long _Id) { 
     return listVBillItemSummaryById( _Id, null);     
 } 

  public final VBillItemSummaryDTO [] listVBillItemSummaryById(long _Id, String orderByColumn) { 
    String whereClause = " WHERE ID = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Long(_Id);   
    types[0] = java.sql.Types.BIGINT;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listVBillItemSummary(whereClause, objs, types, order); 
 } 



  public final int countVBillItemSummaryById(long _Id) { 
    String whereClause = " WHERE ID = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Long(_Id);   
    types[0] = java.sql.Types.BIGINT;   
    return countVBillItemSummary(whereClause, objs, types); 
 } 




  public final VBillItemSummaryDTO [] listVBillItemSummaryByModifiedBy(String _ModifiedBy) { 
     return listVBillItemSummaryByModifiedBy( _ModifiedBy, null);     
 } 

  public final VBillItemSummaryDTO [] listVBillItemSummaryByModifiedBy(String _ModifiedBy, String orderByColumn) { 
    String whereClause = " WHERE MODIFIED_BY = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _ModifiedBy;   
    types[0] = java.sql.Types.VARCHAR;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listVBillItemSummary(whereClause, objs, types, order); 
 } 



  public final int countVBillItemSummaryByModifiedBy(String _ModifiedBy) { 
    String whereClause = " WHERE MODIFIED_BY = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _ModifiedBy;   
    types[0] = java.sql.Types.VARCHAR;   
    return countVBillItemSummary(whereClause, objs, types); 
 } 




  public final VBillItemSummaryDTO [] listVBillItemSummaryByModifiedOn(java.util.Date _ModifiedOn) { 
     return listVBillItemSummaryByModifiedOn( _ModifiedOn, null);     
 } 

  public final VBillItemSummaryDTO [] listVBillItemSummaryByModifiedOn(java.util.Date _ModifiedOn, String orderByColumn) { 
    String whereClause = " WHERE MODIFIED_ON = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.sql.Timestamp(_ModifiedOn.getTime());   
    types[0] = java.sql.Types.TIMESTAMP;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listVBillItemSummary(whereClause, objs, types, order); 
 } 



  public final int countVBillItemSummaryByModifiedOn(java.util.Date _ModifiedOn) { 
    String whereClause = " WHERE MODIFIED_ON = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.sql.Timestamp(_ModifiedOn.getTime());   
    types[0] = java.sql.Types.TIMESTAMP;   
    return countVBillItemSummary(whereClause, objs, types); 
 } 




  public final VBillItemSummaryDTO [] listVBillItemSummaryByMonth(int _Month) { 
     return listVBillItemSummaryByMonth( _Month, null);     
 } 

  public final VBillItemSummaryDTO [] listVBillItemSummaryByMonth(int _Month, String orderByColumn) { 
    String whereClause = " WHERE MONTH = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Integer(_Month);   
    types[0] = java.sql.Types.INTEGER;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listVBillItemSummary(whereClause, objs, types, order); 
 } 



  public final int countVBillItemSummaryByMonth(int _Month) { 
    String whereClause = " WHERE MONTH = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Integer(_Month);   
    types[0] = java.sql.Types.INTEGER;   
    return countVBillItemSummary(whereClause, objs, types); 
 } 




  public final VBillItemSummaryDTO [] listVBillItemSummaryByName(String _Name) { 
     return listVBillItemSummaryByName( _Name, null);     
 } 

  public final VBillItemSummaryDTO [] listVBillItemSummaryByName(String _Name, String orderByColumn) { 
    String whereClause = " WHERE NAME = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _Name;   
    types[0] = java.sql.Types.VARCHAR;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listVBillItemSummary(whereClause, objs, types, order); 
 } 



  public final int countVBillItemSummaryByName(String _Name) { 
    String whereClause = " WHERE NAME = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _Name;   
    types[0] = java.sql.Types.VARCHAR;   
    return countVBillItemSummary(whereClause, objs, types); 
 } 




  public final VBillItemSummaryDTO [] listVBillItemSummaryByPaidAmount(BigDecimal _PaidAmount) { 
     return listVBillItemSummaryByPaidAmount( _PaidAmount, null);     
 } 

  public final VBillItemSummaryDTO [] listVBillItemSummaryByPaidAmount(BigDecimal _PaidAmount, String orderByColumn) { 
    String whereClause = " WHERE PAID_AMOUNT = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _PaidAmount;   
    types[0] = java.sql.Types.DECIMAL;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listVBillItemSummary(whereClause, objs, types, order); 
 } 



  public final int countVBillItemSummaryByPaidAmount(BigDecimal _PaidAmount) { 
    String whereClause = " WHERE PAID_AMOUNT = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _PaidAmount;   
    types[0] = java.sql.Types.DECIMAL;   
    return countVBillItemSummary(whereClause, objs, types); 
 } 




  public final VBillItemSummaryDTO [] listVBillItemSummaryByPayTo(String _PayTo) { 
     return listVBillItemSummaryByPayTo( _PayTo, null);     
 } 

  public final VBillItemSummaryDTO [] listVBillItemSummaryByPayTo(String _PayTo, String orderByColumn) { 
    String whereClause = " WHERE PAY_TO = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _PayTo;   
    types[0] = java.sql.Types.VARCHAR;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listVBillItemSummary(whereClause, objs, types, order); 
 } 



  public final int countVBillItemSummaryByPayTo(String _PayTo) { 
    String whereClause = " WHERE PAY_TO = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _PayTo;   
    types[0] = java.sql.Types.VARCHAR;   
    return countVBillItemSummary(whereClause, objs, types); 
 } 




  public final VBillItemSummaryDTO [] listVBillItemSummaryByRecurringCode(String _RecurringCode) { 
     return listVBillItemSummaryByRecurringCode( _RecurringCode, null);     
 } 

  public final VBillItemSummaryDTO [] listVBillItemSummaryByRecurringCode(String _RecurringCode, String orderByColumn) { 
    String whereClause = " WHERE RECURRING_CODE = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _RecurringCode;   
    types[0] = java.sql.Types.VARCHAR;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listVBillItemSummary(whereClause, objs, types, order); 
 } 



  public final int countVBillItemSummaryByRecurringCode(String _RecurringCode) { 
    String whereClause = " WHERE RECURRING_CODE = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _RecurringCode;   
    types[0] = java.sql.Types.VARCHAR;   
    return countVBillItemSummary(whereClause, objs, types); 
 } 




  public final VBillItemSummaryDTO [] listVBillItemSummaryByStatus(String _Status) { 
     return listVBillItemSummaryByStatus( _Status, null);     
 } 

  public final VBillItemSummaryDTO [] listVBillItemSummaryByStatus(String _Status, String orderByColumn) { 
    String whereClause = " WHERE STATUS = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _Status;   
    types[0] = java.sql.Types.VARCHAR;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listVBillItemSummary(whereClause, objs, types, order); 
 } 



  public final int countVBillItemSummaryByStatus(String _Status) { 
    String whereClause = " WHERE STATUS = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _Status;   
    types[0] = java.sql.Types.VARCHAR;   
    return countVBillItemSummary(whereClause, objs, types); 
 } 




  public final VBillItemSummaryDTO [] listVBillItemSummaryByStatusDate(java.util.Date _StatusDate) { 
     return listVBillItemSummaryByStatusDate( _StatusDate, null);     
 } 

  public final VBillItemSummaryDTO [] listVBillItemSummaryByStatusDate(java.util.Date _StatusDate, String orderByColumn) { 
    String whereClause = " WHERE STATUS_DATE = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.sql.Timestamp(_StatusDate.getTime());   
    types[0] = java.sql.Types.TIMESTAMP;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listVBillItemSummary(whereClause, objs, types, order); 
 } 



  public final int countVBillItemSummaryByStatusDate(java.util.Date _StatusDate) { 
    String whereClause = " WHERE STATUS_DATE = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.sql.Timestamp(_StatusDate.getTime());   
    types[0] = java.sql.Types.TIMESTAMP;   
    return countVBillItemSummary(whereClause, objs, types); 
 } 




  public final VBillItemSummaryDTO [] listVBillItemSummaryByTemplateId(long _TemplateId) { 
     return listVBillItemSummaryByTemplateId( _TemplateId, null);     
 } 

  public final VBillItemSummaryDTO [] listVBillItemSummaryByTemplateId(long _TemplateId, String orderByColumn) { 
    String whereClause = " WHERE TEMPLATE_ID = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Long(_TemplateId);   
    types[0] = java.sql.Types.BIGINT;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listVBillItemSummary(whereClause, objs, types, order); 
 } 



  public final int countVBillItemSummaryByTemplateId(long _TemplateId) { 
    String whereClause = " WHERE TEMPLATE_ID = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Long(_TemplateId);   
    types[0] = java.sql.Types.BIGINT;   
    return countVBillItemSummary(whereClause, objs, types); 
 } 




  public final VBillItemSummaryDTO [] listVBillItemSummaryByTimeDue(java.util.Date _TimeDue) { 
     return listVBillItemSummaryByTimeDue( _TimeDue, null);     
 } 

  public final VBillItemSummaryDTO [] listVBillItemSummaryByTimeDue(java.util.Date _TimeDue, String orderByColumn) { 
    String whereClause = " WHERE TIME_DUE = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.sql.Timestamp(_TimeDue.getTime());   
    types[0] = java.sql.Types.TIMESTAMP;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listVBillItemSummary(whereClause, objs, types, order); 
 } 



  public final int countVBillItemSummaryByTimeDue(java.util.Date _TimeDue) { 
    String whereClause = " WHERE TIME_DUE = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.sql.Timestamp(_TimeDue.getTime());   
    types[0] = java.sql.Types.TIMESTAMP;   
    return countVBillItemSummary(whereClause, objs, types); 
 } 




  public final VBillItemSummaryDTO [] listVBillItemSummaryByTypeCode(String _TypeCode) { 
     return listVBillItemSummaryByTypeCode( _TypeCode, null);     
 } 

  public final VBillItemSummaryDTO [] listVBillItemSummaryByTypeCode(String _TypeCode, String orderByColumn) { 
    String whereClause = " WHERE TYPE_CODE = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _TypeCode;   
    types[0] = java.sql.Types.VARCHAR;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listVBillItemSummary(whereClause, objs, types, order); 
 } 



  public final int countVBillItemSummaryByTypeCode(String _TypeCode) { 
    String whereClause = " WHERE TYPE_CODE = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _TypeCode;   
    types[0] = java.sql.Types.VARCHAR;   
    return countVBillItemSummary(whereClause, objs, types); 
 } 




  public final VBillItemSummaryDTO [] listVBillItemSummaryByUsername(String _Username) { 
     return listVBillItemSummaryByUsername( _Username, null);     
 } 

  public final VBillItemSummaryDTO [] listVBillItemSummaryByUsername(String _Username, String orderByColumn) { 
    String whereClause = " WHERE USERNAME = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _Username;   
    types[0] = java.sql.Types.VARCHAR;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listVBillItemSummary(whereClause, objs, types, order); 
 } 



  public final int countVBillItemSummaryByUsername(String _Username) { 
    String whereClause = " WHERE USERNAME = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _Username;   
    types[0] = java.sql.Types.VARCHAR;   
    return countVBillItemSummary(whereClause, objs, types); 
 } 




  public final VBillItemSummaryDTO [] listVBillItemSummaryByWeek(int _Week) { 
     return listVBillItemSummaryByWeek( _Week, null);     
 } 

  public final VBillItemSummaryDTO [] listVBillItemSummaryByWeek(int _Week, String orderByColumn) { 
    String whereClause = " WHERE WEEK = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Integer(_Week);   
    types[0] = java.sql.Types.INTEGER;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listVBillItemSummary(whereClause, objs, types, order); 
 } 



  public final int countVBillItemSummaryByWeek(int _Week) { 
    String whereClause = " WHERE WEEK = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Integer(_Week);   
    types[0] = java.sql.Types.INTEGER;   
    return countVBillItemSummary(whereClause, objs, types); 
 } 




  public final VBillItemSummaryDTO [] listVBillItemSummaryByYear(int _Year) { 
     return listVBillItemSummaryByYear( _Year, null);     
 } 

  public final VBillItemSummaryDTO [] listVBillItemSummaryByYear(int _Year, String orderByColumn) { 
    String whereClause = " WHERE YEAR = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Integer(_Year);   
    types[0] = java.sql.Types.INTEGER;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listVBillItemSummary(whereClause, objs, types, order); 
 } 



  public final int countVBillItemSummaryByYear(int _Year) { 
    String whereClause = " WHERE YEAR = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Integer(_Year);   
    types[0] = java.sql.Types.INTEGER;   
    return countVBillItemSummary(whereClause, objs, types); 
 } 




  








  public final VBillItemSummaryDTO createVBillItemSummary(VBillItemSummaryDTO _VBillItemSummary)
    throws VBillItemSummaryCreateException {  
         if (logger.isEnabledFor(Level.TRACE)) {
             logger.trace(">> createVBillItemSummary()");
         }  
             PreparedStatement stmt = null;   
             Connection con = openConnection();
         try { 
                 int i = 1;
                 stmt = con.prepareStatement(getInsertSQL(), Statement.RETURN_GENERATED_KEYS);
                  stmt.setInt(i++, _VBillItemSummary.getActive()); 
                  stmt.setBigDecimal(i++, _VBillItemSummary.getBilledAmount()); 
                  stmt.setLong(i++, _VBillItemSummary.getBillId()); 
                  stmt.setString(i++, _VBillItemSummary.getCreatedBy()); 
                  if (_VBillItemSummary.getCreatedOn() != null) { 
                     stmt.setTimestamp(i++, new java.sql.Timestamp(_VBillItemSummary.getCreatedOn().getTime()) ); 
                  } else {
                     stmt.setNull(i++, java.sql.Types.TIMESTAMP); 
                  } 
                  if (_VBillItemSummary.getDateDue() != null) { 
                     stmt.setTimestamp(i++, new java.sql.Timestamp(_VBillItemSummary.getDateDue().getTime()) ); 
                  } else {
                     stmt.setNull(i++, java.sql.Types.TIMESTAMP); 
                  } 
                  stmt.setInt(i++, _VBillItemSummary.getDay()); 
                  stmt.setString(i++, _VBillItemSummary.getDescription()); 
                  stmt.setString(i++, _VBillItemSummary.getEntityCode()); 
                  stmt.setLong(i++, _VBillItemSummary.getId()); 
                  stmt.setString(i++, _VBillItemSummary.getModifiedBy()); 
                  if (_VBillItemSummary.getModifiedOn() != null) { 
                     stmt.setTimestamp(i++, new java.sql.Timestamp(_VBillItemSummary.getModifiedOn().getTime()) ); 
                  } else {
                     stmt.setNull(i++, java.sql.Types.TIMESTAMP); 
                  } 
                  stmt.setInt(i++, _VBillItemSummary.getMonth()); 
                  stmt.setString(i++, _VBillItemSummary.getName()); 
                  stmt.setBigDecimal(i++, _VBillItemSummary.getPaidAmount()); 
                  stmt.setString(i++, _VBillItemSummary.getPayTo()); 
                  stmt.setString(i++, _VBillItemSummary.getRecurringCode()); 
                  stmt.setString(i++, _VBillItemSummary.getStatus()); 
                  if (_VBillItemSummary.getStatusDate() != null) { 
                     stmt.setTimestamp(i++, new java.sql.Timestamp(_VBillItemSummary.getStatusDate().getTime()) ); 
                  } else {
                     stmt.setNull(i++, java.sql.Types.TIMESTAMP); 
                  } 
                  stmt.setLong(i++, _VBillItemSummary.getTemplateId()); 
                  if (_VBillItemSummary.getTimeDue() != null) { 
                     stmt.setTimestamp(i++, new java.sql.Timestamp(_VBillItemSummary.getTimeDue().getTime()) ); 
                  } else {
                     stmt.setNull(i++, java.sql.Types.TIMESTAMP); 
                  } 
                  stmt.setString(i++, _VBillItemSummary.getTypeCode()); 
                  stmt.setString(i++, _VBillItemSummary.getUsername()); 
                  stmt.setInt(i++, _VBillItemSummary.getWeek()); 
                  stmt.setInt(i++, _VBillItemSummary.getYear()); 
                
                 stmt.executeUpdate();     
         } catch (SQLException e) {     
             logSQLException(e);     
             if (isSQLConstraintViolated(e)) {     
                 throw new VBillItemSummaryCreateException(e.getMessage(), e);     
             }     
            try { 	throw new DAOException(e.getMessage(), e);  } catch (DAOException e1) { 	e1.printStackTrace(); }     
         } finally {     
             if (stmt != null) {     
                 try {     
                     stmt.close();     
                 } catch (SQLException e) {     
                     logSQLException(e);     
                 }     
             }     
             closeConnection(con);     
         }     
         if (logger.isEnabledFor(Level.TRACE)) {     
             logger.trace("<< createVBillItemSummary()");     
         }     
         return _VBillItemSummary;     
     }       

  public final void updateVBillItemSummary(VBillItemSummaryDTO _VBillItemSummary) 
    throws VBillItemSummaryUpdateException, VBillItemSummaryFinderException  {      
    if (logger.isEnabledFor(Level.TRACE)) {         
       logger.trace(">> updateVBillItemSummary()");         
    }       
    PreparedStatement stmt = null;      
    Connection con = openConnection();      
       try {        
           int i = 1;       
           stmt = con.prepareStatement(getUpdateSQL());         
                  stmt.setInt(i++,  _VBillItemSummary.getActive() ); 
                  stmt.setBigDecimal(i++,  _VBillItemSummary.getBilledAmount() ); 
                  stmt.setLong(i++,  _VBillItemSummary.getBillId() ); 
                  stmt.setString(i++,  _VBillItemSummary.getCreatedBy() ); 
             if (_VBillItemSummary.getCreatedOn() != null) { 
                  stmt.setTimestamp(i++,  new java.sql.Timestamp(_VBillItemSummary.getCreatedOn().getTime()) ); 
             } else { 
                  stmt.setNull(i++, java.sql.Types.TIMESTAMP); 
             }
             if (_VBillItemSummary.getDateDue() != null) { 
                  stmt.setTimestamp(i++,  new java.sql.Timestamp(_VBillItemSummary.getDateDue().getTime()) ); 
             } else { 
                  stmt.setNull(i++, java.sql.Types.TIMESTAMP); 
             }
                  stmt.setInt(i++,  _VBillItemSummary.getDay() ); 
                  stmt.setString(i++,  _VBillItemSummary.getDescription() ); 
                  stmt.setString(i++,  _VBillItemSummary.getEntityCode() ); 
                  stmt.setLong(i++,  _VBillItemSummary.getId() ); 
                  stmt.setString(i++,  _VBillItemSummary.getModifiedBy() ); 
             if (_VBillItemSummary.getModifiedOn() != null) { 
                  stmt.setTimestamp(i++,  new java.sql.Timestamp(_VBillItemSummary.getModifiedOn().getTime()) ); 
             } else { 
                  stmt.setNull(i++, java.sql.Types.TIMESTAMP); 
             }
                  stmt.setInt(i++,  _VBillItemSummary.getMonth() ); 
                  stmt.setString(i++,  _VBillItemSummary.getName() ); 
                  stmt.setBigDecimal(i++,  _VBillItemSummary.getPaidAmount() ); 
                  stmt.setString(i++,  _VBillItemSummary.getPayTo() ); 
                  stmt.setString(i++,  _VBillItemSummary.getRecurringCode() ); 
                  stmt.setString(i++,  _VBillItemSummary.getStatus() ); 
             if (_VBillItemSummary.getStatusDate() != null) { 
                  stmt.setTimestamp(i++,  new java.sql.Timestamp(_VBillItemSummary.getStatusDate().getTime()) ); 
             } else { 
                  stmt.setNull(i++, java.sql.Types.TIMESTAMP); 
             }
                  stmt.setLong(i++,  _VBillItemSummary.getTemplateId() ); 
             if (_VBillItemSummary.getTimeDue() != null) { 
                  stmt.setTimestamp(i++,  new java.sql.Timestamp(_VBillItemSummary.getTimeDue().getTime()) ); 
             } else { 
                  stmt.setNull(i++, java.sql.Types.TIMESTAMP); 
             }
                  stmt.setString(i++,  _VBillItemSummary.getTypeCode() ); 
                  stmt.setString(i++,  _VBillItemSummary.getUsername() ); 
                  stmt.setInt(i++,  _VBillItemSummary.getWeek() ); 
                  stmt.setInt(i++,  _VBillItemSummary.getYear() ); 
               
  /*** now set the criteria, PK values ***/ 
           int count = stmt.executeUpdate();        
           if (count == 0) {        
               throw new VBillItemSummaryFinderException("");       
           }        
       } catch (SQLException e) {       
           logSQLException(e);      
           if (isSQLConstraintViolated(e)) {        
               throw new VBillItemSummaryUpdateException("");       
           }        
          try { 	throw new DAOException(e.getMessage(), e);  } catch (DAOException e1) { 	e1.printStackTrace(); }       
       } finally {      
           if (stmt != null) {      
               try {        
                   stmt.close();        
               } catch (SQLException e) {       
                   logSQLException(e);      
               }        
           }        
           closeConnection(con);        
       }        
       if (logger.isEnabledFor(Level.TRACE)) {      
           logger.trace("<< updateVBillItemSummary()");         
       }        
   }        

  public final void deleteVBillItemSummary(VBillItemSummaryDTO _VBillItemSummary) 
    throws VBillItemSummaryFinderException, VBillItemSummaryDeleteException  {      
    if (logger.isEnabledFor(Level.TRACE)) {         
       logger.trace(">> deleteVBillItemSummary()");         
    }       
    PreparedStatement stmt = null;      
    Connection con = openConnection();      
       try {        
           int i = 1;       
           stmt = con.prepareStatement(getDeleteSQL());         
           int count = stmt.executeUpdate();        
           if (count == 0) {        
               throw new VBillItemSummaryFinderException("");       
           }        
       } catch (SQLException e) {       
           logSQLException(e);      
           if (isSQLConstraintViolated(e)) {        
               throw new VBillItemSummaryDeleteException("");       
           }        
          try { 	throw new DAOException(e.getMessage(), e);  } catch (DAOException e1) { 	e1.printStackTrace(); }       
       } finally {      
           if (stmt != null) {      
               try {        
                   stmt.close();        
               } catch (SQLException e) {       
                   logSQLException(e);      
               }        
           }        
           closeConnection(con);        
       }        
       if (logger.isEnabledFor(Level.TRACE)) {      
           logger.trace("<< deleteVBillItemSummary()");         
       }        
   }        

  public final void deleteVBillItemSummaryWhere(String whereClause, Object[] whereParams, int[] paramTypes)   
    throws VBillItemSummaryFinderException, VBillItemSummaryDeleteException  {      
   if (logger.isEnabledFor(Level.TRACE)) {     
       logger.trace(">> listVBillItemSummary()");  
   }   
   VBillItemSummaryDTO[] _VBillItemSummary = null;  
   ResultSet rs = null;    
   PreparedStatement stmt = null;  
   Connection con = openConnection();  
   if (whereClause == null) {      
       whereClause = "";     
   }           
   try {   
           stmt = con.prepareStatement(getDeleteBySQL() + whereClause);   
           if (whereParams != null) {      
               for (int i = 0; i < whereParams.length; i++) {      
                   if (whereParams[i] == null) { 
                       stmt.setNull(i+1, paramTypes[i]);   
                   } else {
                       stmt.setObject(i+1, whereParams[i], paramTypes[i]);     
                   }  
               }   
           }               
           int count = stmt.executeUpdate();        
           if (count == 0) {        
               throw new VBillItemSummaryFinderException("");       
           }        
   } catch (SQLException e) {       
           logSQLException(e);      
           if (isSQLConstraintViolated(e)) {        
               throw new VBillItemSummaryDeleteException(e.getMessage(), e);      
           }        
          try { 	throw new DAOException(e.getMessage(), e);  } catch (DAOException e1) { 	e1.printStackTrace(); }       
   } finally {      
           if (stmt != null) {      
               try {        
                   stmt.close();        
               } catch (SQLException e) {       
                   logSQLException(e);      
               }        
           }        
           closeConnection(con);        
   }        
   if (logger.isEnabledFor(Level.TRACE)) {      
       logger.trace("<< deleteVBillItemSummary()");         
   }        
   }        

  public final void deleteVBillItemSummaryByActive(int _Active) throws VBillItemSummaryFinderException, VBillItemSummaryDeleteException{ 
    String whereClause = " WHERE ACTIVE = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Integer(_Active);   
    types[0] = java.sql.Types.INTEGER;   
    deleteVBillItemSummaryWhere(whereClause, objs, types); 
 } 




  public final void deleteVBillItemSummaryByBilledAmount(BigDecimal _BilledAmount) throws VBillItemSummaryFinderException, VBillItemSummaryDeleteException{ 
    String whereClause = " WHERE BILLED_AMOUNT = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _BilledAmount;   
    types[0] = java.sql.Types.DECIMAL;   
    deleteVBillItemSummaryWhere(whereClause, objs, types); 
 } 




  public final void deleteVBillItemSummaryByBillId(long _BillId) throws VBillItemSummaryFinderException, VBillItemSummaryDeleteException{ 
    String whereClause = " WHERE BILL_ID = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Long(_BillId);   
    types[0] = java.sql.Types.BIGINT;   
    deleteVBillItemSummaryWhere(whereClause, objs, types); 
 } 




  public final void deleteVBillItemSummaryByCreatedBy(String _CreatedBy) throws VBillItemSummaryFinderException, VBillItemSummaryDeleteException{ 
    String whereClause = " WHERE CREATED_BY = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _CreatedBy;   
    types[0] = java.sql.Types.VARCHAR;   
    deleteVBillItemSummaryWhere(whereClause, objs, types); 
 } 




  public final void deleteVBillItemSummaryByCreatedOn(java.util.Date _CreatedOn) throws VBillItemSummaryFinderException, VBillItemSummaryDeleteException{ 
    String whereClause = " WHERE CREATED_ON = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.sql.Timestamp(_CreatedOn.getTime());   
    types[0] = java.sql.Types.TIMESTAMP;   
    deleteVBillItemSummaryWhere(whereClause, objs, types); 
 } 




  public final void deleteVBillItemSummaryByDateDue(java.util.Date _DateDue) throws VBillItemSummaryFinderException, VBillItemSummaryDeleteException{ 
    String whereClause = " WHERE DATE_DUE = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.sql.Timestamp(_DateDue.getTime());   
    types[0] = java.sql.Types.TIMESTAMP;   
    deleteVBillItemSummaryWhere(whereClause, objs, types); 
 } 




  public final void deleteVBillItemSummaryByDay(int _Day) throws VBillItemSummaryFinderException, VBillItemSummaryDeleteException{ 
    String whereClause = " WHERE DAY = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Integer(_Day);   
    types[0] = java.sql.Types.INTEGER;   
    deleteVBillItemSummaryWhere(whereClause, objs, types); 
 } 




  public final void deleteVBillItemSummaryByDescription(String _Description) throws VBillItemSummaryFinderException, VBillItemSummaryDeleteException{ 
    String whereClause = " WHERE DESCRIPTION = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _Description;   
    types[0] = java.sql.Types.VARCHAR;   
    deleteVBillItemSummaryWhere(whereClause, objs, types); 
 } 




  public final void deleteVBillItemSummaryByEntityCode(String _EntityCode) throws VBillItemSummaryFinderException, VBillItemSummaryDeleteException{ 
    String whereClause = " WHERE ENTITY_CODE = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _EntityCode;   
    types[0] = java.sql.Types.VARCHAR;   
    deleteVBillItemSummaryWhere(whereClause, objs, types); 
 } 




  public final void deleteVBillItemSummaryById(long _Id) throws VBillItemSummaryFinderException, VBillItemSummaryDeleteException{ 
    String whereClause = " WHERE ID = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Long(_Id);   
    types[0] = java.sql.Types.BIGINT;   
    deleteVBillItemSummaryWhere(whereClause, objs, types); 
 } 




  public final void deleteVBillItemSummaryByModifiedBy(String _ModifiedBy) throws VBillItemSummaryFinderException, VBillItemSummaryDeleteException{ 
    String whereClause = " WHERE MODIFIED_BY = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _ModifiedBy;   
    types[0] = java.sql.Types.VARCHAR;   
    deleteVBillItemSummaryWhere(whereClause, objs, types); 
 } 




  public final void deleteVBillItemSummaryByModifiedOn(java.util.Date _ModifiedOn) throws VBillItemSummaryFinderException, VBillItemSummaryDeleteException{ 
    String whereClause = " WHERE MODIFIED_ON = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.sql.Timestamp(_ModifiedOn.getTime());   
    types[0] = java.sql.Types.TIMESTAMP;   
    deleteVBillItemSummaryWhere(whereClause, objs, types); 
 } 




  public final void deleteVBillItemSummaryByMonth(int _Month) throws VBillItemSummaryFinderException, VBillItemSummaryDeleteException{ 
    String whereClause = " WHERE MONTH = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Integer(_Month);   
    types[0] = java.sql.Types.INTEGER;   
    deleteVBillItemSummaryWhere(whereClause, objs, types); 
 } 




  public final void deleteVBillItemSummaryByName(String _Name) throws VBillItemSummaryFinderException, VBillItemSummaryDeleteException{ 
    String whereClause = " WHERE NAME = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _Name;   
    types[0] = java.sql.Types.VARCHAR;   
    deleteVBillItemSummaryWhere(whereClause, objs, types); 
 } 




  public final void deleteVBillItemSummaryByPaidAmount(BigDecimal _PaidAmount) throws VBillItemSummaryFinderException, VBillItemSummaryDeleteException{ 
    String whereClause = " WHERE PAID_AMOUNT = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _PaidAmount;   
    types[0] = java.sql.Types.DECIMAL;   
    deleteVBillItemSummaryWhere(whereClause, objs, types); 
 } 




  public final void deleteVBillItemSummaryByPayTo(String _PayTo) throws VBillItemSummaryFinderException, VBillItemSummaryDeleteException{ 
    String whereClause = " WHERE PAY_TO = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _PayTo;   
    types[0] = java.sql.Types.VARCHAR;   
    deleteVBillItemSummaryWhere(whereClause, objs, types); 
 } 




  public final void deleteVBillItemSummaryByRecurringCode(String _RecurringCode) throws VBillItemSummaryFinderException, VBillItemSummaryDeleteException{ 
    String whereClause = " WHERE RECURRING_CODE = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _RecurringCode;   
    types[0] = java.sql.Types.VARCHAR;   
    deleteVBillItemSummaryWhere(whereClause, objs, types); 
 } 




  public final void deleteVBillItemSummaryByStatus(String _Status) throws VBillItemSummaryFinderException, VBillItemSummaryDeleteException{ 
    String whereClause = " WHERE STATUS = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _Status;   
    types[0] = java.sql.Types.VARCHAR;   
    deleteVBillItemSummaryWhere(whereClause, objs, types); 
 } 




  public final void deleteVBillItemSummaryByStatusDate(java.util.Date _StatusDate) throws VBillItemSummaryFinderException, VBillItemSummaryDeleteException{ 
    String whereClause = " WHERE STATUS_DATE = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.sql.Timestamp(_StatusDate.getTime());   
    types[0] = java.sql.Types.TIMESTAMP;   
    deleteVBillItemSummaryWhere(whereClause, objs, types); 
 } 




  public final void deleteVBillItemSummaryByTemplateId(long _TemplateId) throws VBillItemSummaryFinderException, VBillItemSummaryDeleteException{ 
    String whereClause = " WHERE TEMPLATE_ID = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Long(_TemplateId);   
    types[0] = java.sql.Types.BIGINT;   
    deleteVBillItemSummaryWhere(whereClause, objs, types); 
 } 




  public final void deleteVBillItemSummaryByTimeDue(java.util.Date _TimeDue) throws VBillItemSummaryFinderException, VBillItemSummaryDeleteException{ 
    String whereClause = " WHERE TIME_DUE = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.sql.Timestamp(_TimeDue.getTime());   
    types[0] = java.sql.Types.TIMESTAMP;   
    deleteVBillItemSummaryWhere(whereClause, objs, types); 
 } 




  public final void deleteVBillItemSummaryByTypeCode(String _TypeCode) throws VBillItemSummaryFinderException, VBillItemSummaryDeleteException{ 
    String whereClause = " WHERE TYPE_CODE = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _TypeCode;   
    types[0] = java.sql.Types.VARCHAR;   
    deleteVBillItemSummaryWhere(whereClause, objs, types); 
 } 




  public final void deleteVBillItemSummaryByUsername(String _Username) throws VBillItemSummaryFinderException, VBillItemSummaryDeleteException{ 
    String whereClause = " WHERE USERNAME = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _Username;   
    types[0] = java.sql.Types.VARCHAR;   
    deleteVBillItemSummaryWhere(whereClause, objs, types); 
 } 




  public final void deleteVBillItemSummaryByWeek(int _Week) throws VBillItemSummaryFinderException, VBillItemSummaryDeleteException{ 
    String whereClause = " WHERE WEEK = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Integer(_Week);   
    types[0] = java.sql.Types.INTEGER;   
    deleteVBillItemSummaryWhere(whereClause, objs, types); 
 } 




  public final void deleteVBillItemSummaryByYear(int _Year) throws VBillItemSummaryFinderException, VBillItemSummaryDeleteException{ 
    String whereClause = " WHERE YEAR = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Integer(_Year);   
    types[0] = java.sql.Types.INTEGER;   
    deleteVBillItemSummaryWhere(whereClause, objs, types); 
 } 




  

public final VBillItemSummaryDTO extractVBillItemSummary(ResultSet rs)       
    throws SQLException {  
         VBillItemSummaryDTO obj = new VBillItemSummaryDTO();          
         int i = 1;      
         obj.setActive( rs.getInt(i++) );       
         obj.setBilledAmount( rs.getBigDecimal(i++) );       
         obj.setBillId( rs.getLong(i++) );       
         obj.setCreatedBy( rs.getString(i++) );       
         obj.setCreatedOn( rs.getTimestamp(i++) );       
         obj.setDateDue( rs.getTimestamp(i++) );       
         obj.setDay( rs.getInt(i++) );       
         obj.setDescription( rs.getString(i++) );       
         obj.setEntityCode( rs.getString(i++) );       
         obj.setId( rs.getLong(i++) );       
         obj.setModifiedBy( rs.getString(i++) );       
         obj.setModifiedOn( rs.getTimestamp(i++) );       
         obj.setMonth( rs.getInt(i++) );       
         obj.setName( rs.getString(i++) );       
         obj.setPaidAmount( rs.getBigDecimal(i++) );       
         obj.setPayTo( rs.getString(i++) );       
         obj.setRecurringCode( rs.getString(i++) );       
         obj.setStatus( rs.getString(i++) );       
         obj.setStatusDate( rs.getTimestamp(i++) );       
         obj.setTemplateId( rs.getLong(i++) );       
         obj.setTimeDue( rs.getTimestamp(i++) );       
         obj.setTypeCode( rs.getString(i++) );       
         obj.setUsername( rs.getString(i++) );       
         obj.setWeek( rs.getInt(i++) );       
         obj.setYear( rs.getInt(i++) );       
        
 return obj;  } 


 /*************** end *****************/
}
