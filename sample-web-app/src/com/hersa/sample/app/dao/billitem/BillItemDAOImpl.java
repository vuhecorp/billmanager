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
class BillItemDAOImpl extends AbstractDAImpl implements BillItemDAO {

  private static final Logger logger = 
      Logger.getLogger(BillItemDAOImpl.class);

  protected String tableName = BillItemPrefix.PREFIX + "BILL_ITEM"; 
  public String getTable(){
      return "\"" + this.tableName + "\""; 
}
  public void setTable(String table){
      this.tableName = table; 
}
  protected String getSelectSQL(){
      String sql = " SELECT ACTIVE, AMOUNT, BILL_ID, CREATED_BY, CREATED_ON, DATE_DUE, DAY, DESCRIPTION, ENTITY_CODE, ID, MODIFIED_BY, MODIFIED_ON, MONTH, NAME, PAY_TO, RECURRING_CODE, STATUS, STATUS_DATE, TEMPLATE_ID, TIME_DUE, TYPE_CODE, USERNAME, WEEK, YEAR, ACTIVE, AMOUNT, BILL_ID, CREATED_BY, CREATED_ON, DATE_DUE, DAY, DESCRIPTION, ENTITY_CODE, ID, MODIFIED_BY, MODIFIED_ON, MONTH, NAME, PAY_TO, RECURRING_CODE, STATUS, STATUS_DATE, TEMPLATE_ID, TIME_DUE, TYPE_CODE, USERNAME, WEEK, YEAR FROM " + getTable() + " " ;
      return sql;
}
  protected String getSelectCountSQL(){
      String sql = " SELECT COUNT(*) FROM " + getTable();
      return sql;
}
  protected String getInsertSQL(){
      String sql = " INSERT INTO " + getTable() + " ( ACTIVE, AMOUNT, BILL_ID, CREATED_BY, CREATED_ON, DATE_DUE, DAY, DESCRIPTION, ENTITY_CODE, MODIFIED_BY, MODIFIED_ON, MONTH, NAME, PAY_TO, RECURRING_CODE, STATUS, STATUS_DATE, TEMPLATE_ID, TIME_DUE, TYPE_CODE, USERNAME, WEEK, YEAR ) VALUES  ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";
      return sql;
}
  protected String getUpdateSQL(){
      String sql = " UPDATE " + getTable() + " SET  ACTIVE = ? , AMOUNT = ? , BILL_ID = ? , CREATED_BY = ? , CREATED_ON = ? , DATE_DUE = ? , DAY = ? , DESCRIPTION = ? , ENTITY_CODE = ? , MODIFIED_BY = ? , MODIFIED_ON = ? , MONTH = ? , NAME = ? , PAY_TO = ? , RECURRING_CODE = ? , STATUS = ? , STATUS_DATE = ? , TEMPLATE_ID = ? , TIME_DUE = ? , TYPE_CODE = ? , USERNAME = ? , WEEK = ? , YEAR = ? , ACTIVE = ? , AMOUNT = ? , BILL_ID = ? , CREATED_BY = ? , CREATED_ON = ? , DATE_DUE = ? , DAY = ? , DESCRIPTION = ? , ENTITY_CODE = ? , ID = ? , MODIFIED_BY = ? , MODIFIED_ON = ? , MONTH = ? , NAME = ? , PAY_TO = ? , RECURRING_CODE = ? , STATUS = ? , STATUS_DATE = ? , TEMPLATE_ID = ? , TIME_DUE = ? , TYPE_CODE = ? , USERNAME = ? , WEEK = ? , YEAR = ?  WHERE ID = ?  and ID = ? ";
      return sql;
}
  protected String getUpdateBlobsSQL(){
      String sql = " UPDATE  " + getTable() + "  SET  ACTIVE = ? , AMOUNT = ? , BILL_ID = ? , CREATED_BY = ? , CREATED_ON = ? , DATE_DUE = ? , DAY = ? , DESCRIPTION = ? , ENTITY_CODE = ? , MODIFIED_BY = ? , MODIFIED_ON = ? , MONTH = ? , NAME = ? , PAY_TO = ? , RECURRING_CODE = ? , STATUS = ? , STATUS_DATE = ? , TEMPLATE_ID = ? , TIME_DUE = ? , TYPE_CODE = ? , USERNAME = ? , WEEK = ? , YEAR = ? , ACTIVE = ? , AMOUNT = ? , BILL_ID = ? , CREATED_BY = ? , CREATED_ON = ? , DATE_DUE = ? , DAY = ? , DESCRIPTION = ? , ENTITY_CODE = ? , ID = ? , MODIFIED_BY = ? , MODIFIED_ON = ? , MONTH = ? , NAME = ? , PAY_TO = ? , RECURRING_CODE = ? , STATUS = ? , STATUS_DATE = ? , TEMPLATE_ID = ? , TIME_DUE = ? , TYPE_CODE = ? , USERNAME = ? , WEEK = ? , YEAR = ?  WHERE ID = ?  and ID = ? ";
      return sql;
}
  protected String getDeleteSQL(){
      String sql = " DELETE FROM  " + getTable() + "  WHERE ID = ?  and ID = ?  " ;
      return sql;
}
  protected String getDeleteBySQL(){
      String sql = " DELETE FROM " + getTable() ;
      return sql;
}

  public BillItemDAOImpl() { 
       factory = new DefaultConnectionProvider( BillItemJNDI.NON_XA_DATASOURCE ); 
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

  public final BillItemDTO [] listAllBillItem(String orderByColumn) { 
    String order = "";    
     if (orderByColumn != null && orderByColumn.trim().length() > 0) {    
        order = " ORDER BY " + orderByColumn;     
     }    
    return listBillItem(null, null, null, order); 
 } 

public final BillItemDTO [] listAllBillItem() { 
    return listBillItem(null, null, null, null); 
 } 

public final BillItemDTO [] customBillItem(String completeSQL ) { 
   if (logger.isEnabledFor(Level.TRACE)) {     
       logger.trace(">> listBillItem()");  
   }   
   BillItemDTO[] _BillItem = null;  
   ResultSet rs = null;    
   PreparedStatement stmt = null;  
   Connection con = openConnection();  
   try {   
           stmt = con.prepareStatement(completeSQL);    
           stmt.setMaxRows(getMaxRows());            rs = stmt.executeQuery();       
           if (rs.next()) {        
               List list = new ArrayList();        
               do {        
                   list.add(extractBillItem(rs));        
               } while (rs.next());        
               _BillItem =      
                   (BillItemDTO[]) list.toArray(new BillItemDTO[list.size()]);       
           } else {        
               _BillItem = new BillItemDTO[0];        
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
           logger.trace("<< findBillItem()");     
   }       
   return _BillItem;        
 }  

public final BillItemDTO [] listBillItem(String whereClause, Object[] whereParams, int[] paramTypes, String orderClause ) { 
   if (logger.isEnabledFor(Level.TRACE)) {     
       logger.trace(">> listBillItem()");  
   }   
   if (paginationStartIndex > 0) {    
      return searchPageableBillItem(whereClause, whereParams, paramTypes, orderClause, paginationStartIndex, paginationPageSize);  
   }  
   BillItemDTO[] _BillItem = null;  
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
                   list.add(extractBillItem(rs));        
               } while (rs.next());        
               _BillItem =      
                   (BillItemDTO[]) list.toArray(new BillItemDTO[list.size()]);       
           } else {        
               _BillItem = new BillItemDTO[0];        
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
           logger.trace("<< findBillItem()");     
   }       
   return _BillItem;        
 }  

public final BillItemDTO [] searchPageableBillItem(String whereClause, Object[] whereParams, int[] paramTypes, String orderClause,int startIndex, int pageSize ) { 
   if (logger.isEnabledFor(Level.TRACE)) {     
       logger.trace(">> searchPageableBillItem()");  
   }   
   BillItemDTO[] _BillItem = null;  
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
                   list.add(extractBillItem(rs));        
               } while ((rs.next()) && (--len > 0));      
               _BillItem =      
                   (BillItemDTO[]) list.toArray(new BillItemDTO[list.size()]);       
           } else {        
               _BillItem = new BillItemDTO[0];        
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
           logger.trace("<< searchPageableBillItem()");     
   }       
   return _BillItem;        
 }  

public final int countBillItem(String whereClause, Object[] whereParams, int[] paramTypes ) { 
   if (logger.isEnabledFor(Level.TRACE)) {     
       logger.trace(">> countBillItem()");  
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
           logger.trace("<< findBillItem()");     
   }       
   return count;     
 }  





  public final BillItemDTO [] listBillItemByActive(int _Active) { 
     return listBillItemByActive( _Active, null);     
 } 

  public final BillItemDTO [] listBillItemByActive(int _Active, String orderByColumn) { 
    String whereClause = " WHERE ACTIVE = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Integer(_Active);   
    types[0] = java.sql.Types.INTEGER;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listBillItem(whereClause, objs, types, order); 
 } 



  public final int countBillItemByActive(int _Active) { 
    String whereClause = " WHERE ACTIVE = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Integer(_Active);   
    types[0] = java.sql.Types.INTEGER;   
    return countBillItem(whereClause, objs, types); 
 } 




  public final BillItemDTO [] listBillItemByAmount(BigDecimal _Amount) { 
     return listBillItemByAmount( _Amount, null);     
 } 

  public final BillItemDTO [] listBillItemByAmount(BigDecimal _Amount, String orderByColumn) { 
    String whereClause = " WHERE AMOUNT = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _Amount;   
    types[0] = java.sql.Types.DECIMAL;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listBillItem(whereClause, objs, types, order); 
 } 



  public final int countBillItemByAmount(BigDecimal _Amount) { 
    String whereClause = " WHERE AMOUNT = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _Amount;   
    types[0] = java.sql.Types.DECIMAL;   
    return countBillItem(whereClause, objs, types); 
 } 




  public final BillItemDTO [] listBillItemByBillId(long _BillId) { 
     return listBillItemByBillId( _BillId, null);     
 } 

  public final BillItemDTO [] listBillItemByBillId(long _BillId, String orderByColumn) { 
    String whereClause = " WHERE BILL_ID = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Long(_BillId);   
    types[0] = java.sql.Types.BIGINT;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listBillItem(whereClause, objs, types, order); 
 } 



  public final int countBillItemByBillId(long _BillId) { 
    String whereClause = " WHERE BILL_ID = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Long(_BillId);   
    types[0] = java.sql.Types.BIGINT;   
    return countBillItem(whereClause, objs, types); 
 } 




  public final BillItemDTO [] listBillItemByCreatedBy(String _CreatedBy) { 
     return listBillItemByCreatedBy( _CreatedBy, null);     
 } 

  public final BillItemDTO [] listBillItemByCreatedBy(String _CreatedBy, String orderByColumn) { 
    String whereClause = " WHERE CREATED_BY = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _CreatedBy;   
    types[0] = java.sql.Types.VARCHAR;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listBillItem(whereClause, objs, types, order); 
 } 



  public final int countBillItemByCreatedBy(String _CreatedBy) { 
    String whereClause = " WHERE CREATED_BY = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _CreatedBy;   
    types[0] = java.sql.Types.VARCHAR;   
    return countBillItem(whereClause, objs, types); 
 } 




  public final BillItemDTO [] listBillItemByCreatedOn(java.util.Date _CreatedOn) { 
     return listBillItemByCreatedOn( _CreatedOn, null);     
 } 

  public final BillItemDTO [] listBillItemByCreatedOn(java.util.Date _CreatedOn, String orderByColumn) { 
    String whereClause = " WHERE CREATED_ON = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.sql.Timestamp(_CreatedOn.getTime());   
    types[0] = java.sql.Types.TIMESTAMP;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listBillItem(whereClause, objs, types, order); 
 } 



  public final int countBillItemByCreatedOn(java.util.Date _CreatedOn) { 
    String whereClause = " WHERE CREATED_ON = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.sql.Timestamp(_CreatedOn.getTime());   
    types[0] = java.sql.Types.TIMESTAMP;   
    return countBillItem(whereClause, objs, types); 
 } 




  public final BillItemDTO [] listBillItemByDateDue(java.util.Date _DateDue) { 
     return listBillItemByDateDue( _DateDue, null);     
 } 

  public final BillItemDTO [] listBillItemByDateDue(java.util.Date _DateDue, String orderByColumn) { 
    String whereClause = " WHERE DATE_DUE = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.sql.Timestamp(_DateDue.getTime());   
    types[0] = java.sql.Types.TIMESTAMP;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listBillItem(whereClause, objs, types, order); 
 } 



  public final int countBillItemByDateDue(java.util.Date _DateDue) { 
    String whereClause = " WHERE DATE_DUE = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.sql.Timestamp(_DateDue.getTime());   
    types[0] = java.sql.Types.TIMESTAMP;   
    return countBillItem(whereClause, objs, types); 
 } 




  public final BillItemDTO [] listBillItemByDay(int _Day) { 
     return listBillItemByDay( _Day, null);     
 } 

  public final BillItemDTO [] listBillItemByDay(int _Day, String orderByColumn) { 
    String whereClause = " WHERE DAY = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Integer(_Day);   
    types[0] = java.sql.Types.INTEGER;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listBillItem(whereClause, objs, types, order); 
 } 



  public final int countBillItemByDay(int _Day) { 
    String whereClause = " WHERE DAY = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Integer(_Day);   
    types[0] = java.sql.Types.INTEGER;   
    return countBillItem(whereClause, objs, types); 
 } 




  public final BillItemDTO [] listBillItemByDescription(String _Description) { 
     return listBillItemByDescription( _Description, null);     
 } 

  public final BillItemDTO [] listBillItemByDescription(String _Description, String orderByColumn) { 
    String whereClause = " WHERE DESCRIPTION = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _Description;   
    types[0] = java.sql.Types.VARCHAR;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listBillItem(whereClause, objs, types, order); 
 } 



  public final int countBillItemByDescription(String _Description) { 
    String whereClause = " WHERE DESCRIPTION = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _Description;   
    types[0] = java.sql.Types.VARCHAR;   
    return countBillItem(whereClause, objs, types); 
 } 




  public final BillItemDTO [] listBillItemByEntityCode(String _EntityCode) { 
     return listBillItemByEntityCode( _EntityCode, null);     
 } 

  public final BillItemDTO [] listBillItemByEntityCode(String _EntityCode, String orderByColumn) { 
    String whereClause = " WHERE ENTITY_CODE = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _EntityCode;   
    types[0] = java.sql.Types.VARCHAR;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listBillItem(whereClause, objs, types, order); 
 } 



  public final int countBillItemByEntityCode(String _EntityCode) { 
    String whereClause = " WHERE ENTITY_CODE = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _EntityCode;   
    types[0] = java.sql.Types.VARCHAR;   
    return countBillItem(whereClause, objs, types); 
 } 




  public final BillItemDTO [] listBillItemById(long _Id) { 
     return listBillItemById( _Id, null);     
 } 

  public final BillItemDTO [] listBillItemById(long _Id, String orderByColumn) { 
    String whereClause = " WHERE ID = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Long(_Id);   
    types[0] = java.sql.Types.BIGINT;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listBillItem(whereClause, objs, types, order); 
 } 



  public final int countBillItemById(long _Id) { 
    String whereClause = " WHERE ID = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Long(_Id);   
    types[0] = java.sql.Types.BIGINT;   
    return countBillItem(whereClause, objs, types); 
 } 




  public final BillItemDTO [] listBillItemByModifiedBy(String _ModifiedBy) { 
     return listBillItemByModifiedBy( _ModifiedBy, null);     
 } 

  public final BillItemDTO [] listBillItemByModifiedBy(String _ModifiedBy, String orderByColumn) { 
    String whereClause = " WHERE MODIFIED_BY = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _ModifiedBy;   
    types[0] = java.sql.Types.VARCHAR;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listBillItem(whereClause, objs, types, order); 
 } 



  public final int countBillItemByModifiedBy(String _ModifiedBy) { 
    String whereClause = " WHERE MODIFIED_BY = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _ModifiedBy;   
    types[0] = java.sql.Types.VARCHAR;   
    return countBillItem(whereClause, objs, types); 
 } 




  public final BillItemDTO [] listBillItemByModifiedOn(java.util.Date _ModifiedOn) { 
     return listBillItemByModifiedOn( _ModifiedOn, null);     
 } 

  public final BillItemDTO [] listBillItemByModifiedOn(java.util.Date _ModifiedOn, String orderByColumn) { 
    String whereClause = " WHERE MODIFIED_ON = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.sql.Timestamp(_ModifiedOn.getTime());   
    types[0] = java.sql.Types.TIMESTAMP;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listBillItem(whereClause, objs, types, order); 
 } 



  public final int countBillItemByModifiedOn(java.util.Date _ModifiedOn) { 
    String whereClause = " WHERE MODIFIED_ON = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.sql.Timestamp(_ModifiedOn.getTime());   
    types[0] = java.sql.Types.TIMESTAMP;   
    return countBillItem(whereClause, objs, types); 
 } 




  public final BillItemDTO [] listBillItemByMonth(int _Month) { 
     return listBillItemByMonth( _Month, null);     
 } 

  public final BillItemDTO [] listBillItemByMonth(int _Month, String orderByColumn) { 
    String whereClause = " WHERE MONTH = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Integer(_Month);   
    types[0] = java.sql.Types.INTEGER;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listBillItem(whereClause, objs, types, order); 
 } 



  public final int countBillItemByMonth(int _Month) { 
    String whereClause = " WHERE MONTH = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Integer(_Month);   
    types[0] = java.sql.Types.INTEGER;   
    return countBillItem(whereClause, objs, types); 
 } 




  public final BillItemDTO [] listBillItemByName(String _Name) { 
     return listBillItemByName( _Name, null);     
 } 

  public final BillItemDTO [] listBillItemByName(String _Name, String orderByColumn) { 
    String whereClause = " WHERE NAME = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _Name;   
    types[0] = java.sql.Types.VARCHAR;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listBillItem(whereClause, objs, types, order); 
 } 



  public final int countBillItemByName(String _Name) { 
    String whereClause = " WHERE NAME = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _Name;   
    types[0] = java.sql.Types.VARCHAR;   
    return countBillItem(whereClause, objs, types); 
 } 




  public final BillItemDTO [] listBillItemByPayTo(String _PayTo) { 
     return listBillItemByPayTo( _PayTo, null);     
 } 

  public final BillItemDTO [] listBillItemByPayTo(String _PayTo, String orderByColumn) { 
    String whereClause = " WHERE PAY_TO = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _PayTo;   
    types[0] = java.sql.Types.VARCHAR;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listBillItem(whereClause, objs, types, order); 
 } 



  public final int countBillItemByPayTo(String _PayTo) { 
    String whereClause = " WHERE PAY_TO = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _PayTo;   
    types[0] = java.sql.Types.VARCHAR;   
    return countBillItem(whereClause, objs, types); 
 } 




  public final BillItemDTO [] listBillItemByRecurringCode(String _RecurringCode) { 
     return listBillItemByRecurringCode( _RecurringCode, null);     
 } 

  public final BillItemDTO [] listBillItemByRecurringCode(String _RecurringCode, String orderByColumn) { 
    String whereClause = " WHERE RECURRING_CODE = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _RecurringCode;   
    types[0] = java.sql.Types.VARCHAR;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listBillItem(whereClause, objs, types, order); 
 } 



  public final int countBillItemByRecurringCode(String _RecurringCode) { 
    String whereClause = " WHERE RECURRING_CODE = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _RecurringCode;   
    types[0] = java.sql.Types.VARCHAR;   
    return countBillItem(whereClause, objs, types); 
 } 




  public final BillItemDTO [] listBillItemByStatus(String _Status) { 
     return listBillItemByStatus( _Status, null);     
 } 

  public final BillItemDTO [] listBillItemByStatus(String _Status, String orderByColumn) { 
    String whereClause = " WHERE STATUS = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _Status;   
    types[0] = java.sql.Types.VARCHAR;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listBillItem(whereClause, objs, types, order); 
 } 



  public final int countBillItemByStatus(String _Status) { 
    String whereClause = " WHERE STATUS = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _Status;   
    types[0] = java.sql.Types.VARCHAR;   
    return countBillItem(whereClause, objs, types); 
 } 




  public final BillItemDTO [] listBillItemByStatusDate(java.util.Date _StatusDate) { 
     return listBillItemByStatusDate( _StatusDate, null);     
 } 

  public final BillItemDTO [] listBillItemByStatusDate(java.util.Date _StatusDate, String orderByColumn) { 
    String whereClause = " WHERE STATUS_DATE = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.sql.Timestamp(_StatusDate.getTime());   
    types[0] = java.sql.Types.TIMESTAMP;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listBillItem(whereClause, objs, types, order); 
 } 



  public final int countBillItemByStatusDate(java.util.Date _StatusDate) { 
    String whereClause = " WHERE STATUS_DATE = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.sql.Timestamp(_StatusDate.getTime());   
    types[0] = java.sql.Types.TIMESTAMP;   
    return countBillItem(whereClause, objs, types); 
 } 




  public final BillItemDTO [] listBillItemByTemplateId(long _TemplateId) { 
     return listBillItemByTemplateId( _TemplateId, null);     
 } 

  public final BillItemDTO [] listBillItemByTemplateId(long _TemplateId, String orderByColumn) { 
    String whereClause = " WHERE TEMPLATE_ID = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Long(_TemplateId);   
    types[0] = java.sql.Types.BIGINT;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listBillItem(whereClause, objs, types, order); 
 } 



  public final int countBillItemByTemplateId(long _TemplateId) { 
    String whereClause = " WHERE TEMPLATE_ID = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Long(_TemplateId);   
    types[0] = java.sql.Types.BIGINT;   
    return countBillItem(whereClause, objs, types); 
 } 




  public final BillItemDTO [] listBillItemByTimeDue(java.util.Date _TimeDue) { 
     return listBillItemByTimeDue( _TimeDue, null);     
 } 

  public final BillItemDTO [] listBillItemByTimeDue(java.util.Date _TimeDue, String orderByColumn) { 
    String whereClause = " WHERE TIME_DUE = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.sql.Timestamp(_TimeDue.getTime());   
    types[0] = java.sql.Types.TIMESTAMP;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listBillItem(whereClause, objs, types, order); 
 } 



  public final int countBillItemByTimeDue(java.util.Date _TimeDue) { 
    String whereClause = " WHERE TIME_DUE = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.sql.Timestamp(_TimeDue.getTime());   
    types[0] = java.sql.Types.TIMESTAMP;   
    return countBillItem(whereClause, objs, types); 
 } 




  public final BillItemDTO [] listBillItemByTypeCode(String _TypeCode) { 
     return listBillItemByTypeCode( _TypeCode, null);     
 } 

  public final BillItemDTO [] listBillItemByTypeCode(String _TypeCode, String orderByColumn) { 
    String whereClause = " WHERE TYPE_CODE = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _TypeCode;   
    types[0] = java.sql.Types.VARCHAR;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listBillItem(whereClause, objs, types, order); 
 } 



  public final int countBillItemByTypeCode(String _TypeCode) { 
    String whereClause = " WHERE TYPE_CODE = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _TypeCode;   
    types[0] = java.sql.Types.VARCHAR;   
    return countBillItem(whereClause, objs, types); 
 } 




  public final BillItemDTO [] listBillItemByUsername(String _Username) { 
     return listBillItemByUsername( _Username, null);     
 } 

  public final BillItemDTO [] listBillItemByUsername(String _Username, String orderByColumn) { 
    String whereClause = " WHERE USERNAME = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _Username;   
    types[0] = java.sql.Types.VARCHAR;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listBillItem(whereClause, objs, types, order); 
 } 



  public final int countBillItemByUsername(String _Username) { 
    String whereClause = " WHERE USERNAME = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _Username;   
    types[0] = java.sql.Types.VARCHAR;   
    return countBillItem(whereClause, objs, types); 
 } 




  public final BillItemDTO [] listBillItemByWeek(int _Week) { 
     return listBillItemByWeek( _Week, null);     
 } 

  public final BillItemDTO [] listBillItemByWeek(int _Week, String orderByColumn) { 
    String whereClause = " WHERE WEEK = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Integer(_Week);   
    types[0] = java.sql.Types.INTEGER;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listBillItem(whereClause, objs, types, order); 
 } 



  public final int countBillItemByWeek(int _Week) { 
    String whereClause = " WHERE WEEK = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Integer(_Week);   
    types[0] = java.sql.Types.INTEGER;   
    return countBillItem(whereClause, objs, types); 
 } 




  public final BillItemDTO [] listBillItemByYear(int _Year) { 
     return listBillItemByYear( _Year, null);     
 } 

  public final BillItemDTO [] listBillItemByYear(int _Year, String orderByColumn) { 
    String whereClause = " WHERE YEAR = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Integer(_Year);   
    types[0] = java.sql.Types.INTEGER;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listBillItem(whereClause, objs, types, order); 
 } 



  public final int countBillItemByYear(int _Year) { 
    String whereClause = " WHERE YEAR = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Integer(_Year);   
    types[0] = java.sql.Types.INTEGER;   
    return countBillItem(whereClause, objs, types); 
 } 




 









      
      
   

  public final BillItemDTO createBillItem(BillItemDTO _BillItem)
    throws BillItemCreateException {  
         if (logger.isEnabledFor(Level.TRACE)) {
             logger.trace(">> createBillItem()");
         }  
             PreparedStatement stmt = null;   
             Connection con = openConnection();
         try { 
                 int i = 1;
                 stmt = con.prepareStatement(getInsertSQL(), Statement.RETURN_GENERATED_KEYS);
                  stmt.setInt(i++, _BillItem.getActive()); 
                  stmt.setBigDecimal(i++, _BillItem.getAmount()); 
                  stmt.setLong(i++, _BillItem.getBillId()); 
                  stmt.setString(i++, _BillItem.getCreatedBy()); 
                  if (_BillItem.getCreatedOn() != null) { 
                     stmt.setTimestamp(i++, new java.sql.Timestamp(_BillItem.getCreatedOn().getTime()) ); 
                  } else {
                     stmt.setNull(i++, java.sql.Types.TIMESTAMP); 
                  } 
                  if (_BillItem.getDateDue() != null) { 
                     stmt.setTimestamp(i++, new java.sql.Timestamp(_BillItem.getDateDue().getTime()) ); 
                  } else {
                     stmt.setNull(i++, java.sql.Types.TIMESTAMP); 
                  } 
                  stmt.setInt(i++, _BillItem.getDay()); 
                  stmt.setString(i++, _BillItem.getDescription()); 
                  stmt.setString(i++, _BillItem.getEntityCode()); 
                  stmt.setString(i++, _BillItem.getModifiedBy()); 
                  if (_BillItem.getModifiedOn() != null) { 
                     stmt.setTimestamp(i++, new java.sql.Timestamp(_BillItem.getModifiedOn().getTime()) ); 
                  } else {
                     stmt.setNull(i++, java.sql.Types.TIMESTAMP); 
                  } 
                  stmt.setInt(i++, _BillItem.getMonth()); 
                  stmt.setString(i++, _BillItem.getName()); 
                  stmt.setString(i++, _BillItem.getPayTo()); 
                  stmt.setString(i++, _BillItem.getRecurringCode()); 
                  stmt.setString(i++, _BillItem.getStatus()); 
                  if (_BillItem.getStatusDate() != null) { 
                     stmt.setTimestamp(i++, new java.sql.Timestamp(_BillItem.getStatusDate().getTime()) ); 
                  } else {
                     stmt.setNull(i++, java.sql.Types.TIMESTAMP); 
                  } 
                  stmt.setLong(i++, _BillItem.getTemplateId()); 
                  if (_BillItem.getTimeDue() != null) { 
                     stmt.setTimestamp(i++, new java.sql.Timestamp(_BillItem.getTimeDue().getTime()) ); 
                  } else {
                     stmt.setNull(i++, java.sql.Types.TIMESTAMP); 
                  } 
                  stmt.setString(i++, _BillItem.getTypeCode()); 
                  stmt.setString(i++, _BillItem.getUsername()); 
                  stmt.setInt(i++, _BillItem.getWeek()); 
                  stmt.setInt(i++, _BillItem.getYear()); 
               
                 stmt.executeUpdate();     
                  int generatedId = -1;      
                  ResultSet rs = stmt.getGeneratedKeys();      
                  while (rs.next()) {      
                     generatedId = (int) rs.getLong(1);      
                  }       
                  rs.close();      
                  _BillItem.setId(generatedId);     
         } catch (SQLException e) {     
             logSQLException(e);     
             if (isSQLConstraintViolated(e)) {     
                 throw new BillItemCreateException(e.getMessage(), e);     
             }     
             throw new BillItemCreateException(e.getMessage(), e);  
         } finally {     
             if (stmt != null) {     
                 try {     
                     stmt.close();     
                 } catch (SQLException e) {     
                     logSQLException(e);     
                 }     
             }     
             if (!connectionSetByCaller) {
            	 closeConnection(con);
             }
                  
         }     
         if (logger.isEnabledFor(Level.TRACE)) {     
             logger.trace("<< createBillItem()");     
         }     
         return _BillItem;     
     }       

  public final void updateBillItem(BillItemDTO _BillItem) 
    throws BillItemUpdateException, BillItemFinderException  {      
    if (logger.isEnabledFor(Level.TRACE)) {         
       logger.trace(">> updateBillItem()");         
    }       
    PreparedStatement stmt = null;      
    Connection con = openConnection();      
       try {        
           int i = 1;       
           stmt = con.prepareStatement(getUpdateSQL());         
                  stmt.setInt(i++,  _BillItem.getActive() ); 
                  stmt.setBigDecimal(i++,  _BillItem.getAmount() ); 
                  stmt.setLong(i++,  _BillItem.getBillId() ); 
                  stmt.setString(i++,  _BillItem.getCreatedBy() ); 
             if (_BillItem.getCreatedOn() != null) { 
                  stmt.setTimestamp(i++,  new java.sql.Timestamp(_BillItem.getCreatedOn().getTime()) ); 
             } else { 
                  stmt.setNull(i++, java.sql.Types.TIMESTAMP); 
             }
             if (_BillItem.getDateDue() != null) { 
                  stmt.setTimestamp(i++,  new java.sql.Timestamp(_BillItem.getDateDue().getTime()) ); 
             } else { 
                  stmt.setNull(i++, java.sql.Types.TIMESTAMP); 
             }
                  stmt.setInt(i++,  _BillItem.getDay() ); 
                  stmt.setString(i++,  _BillItem.getDescription() ); 
                  stmt.setString(i++,  _BillItem.getEntityCode() ); 
                  stmt.setString(i++,  _BillItem.getModifiedBy() ); 
             if (_BillItem.getModifiedOn() != null) { 
                  stmt.setTimestamp(i++,  new java.sql.Timestamp(_BillItem.getModifiedOn().getTime()) ); 
             } else { 
                  stmt.setNull(i++, java.sql.Types.TIMESTAMP); 
             }
                  stmt.setInt(i++,  _BillItem.getMonth() ); 
                  stmt.setString(i++,  _BillItem.getName() ); 
                  stmt.setString(i++,  _BillItem.getPayTo() ); 
                  stmt.setString(i++,  _BillItem.getRecurringCode() ); 
                  stmt.setString(i++,  _BillItem.getStatus() ); 
             if (_BillItem.getStatusDate() != null) { 
                  stmt.setTimestamp(i++,  new java.sql.Timestamp(_BillItem.getStatusDate().getTime()) ); 
             } else { 
                  stmt.setNull(i++, java.sql.Types.TIMESTAMP); 
             }
                  stmt.setLong(i++,  _BillItem.getTemplateId() ); 
             if (_BillItem.getTimeDue() != null) { 
                  stmt.setTimestamp(i++,  new java.sql.Timestamp(_BillItem.getTimeDue().getTime()) ); 
             } else { 
                  stmt.setNull(i++, java.sql.Types.TIMESTAMP); 
             }
                  stmt.setString(i++,  _BillItem.getTypeCode() ); 
                  stmt.setString(i++,  _BillItem.getUsername() ); 
                  stmt.setInt(i++,  _BillItem.getWeek() ); 
                  stmt.setInt(i++,  _BillItem.getYear() ); 
                 
  /*** now set the criteria, PK values ***/ 
             stmt.setLong(i++,  _BillItem.getId() ); 
           int count = stmt.executeUpdate();        
           if (count == 0) {        
               throw new BillItemFinderException("");       
           }        
       } catch (SQLException e) {       
           logSQLException(e);      
           if (isSQLConstraintViolated(e)) {        
               throw new BillItemUpdateException("");       
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
           logger.trace("<< updateBillItem()");         
       }        
   }        

  public final void deleteBillItem(BillItemDTO _BillItem) 
    throws BillItemFinderException, BillItemDeleteException  {      
    if (logger.isEnabledFor(Level.TRACE)) {         
       logger.trace(">> deleteBillItem()");         
    }       
    PreparedStatement stmt = null;      
    Connection con = openConnection();      
       try {        
           int i = 1;       
           stmt = con.prepareStatement(getDeleteSQL());         
             stmt.setLong(i++,  _BillItem.getId() ); 
           int count = stmt.executeUpdate();        
           if (count == 0) {        
               throw new BillItemFinderException("");       
           }        
       } catch (SQLException e) {       
           logSQLException(e);      
           if (isSQLConstraintViolated(e)) {        
               throw new BillItemDeleteException("");       
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
           logger.trace("<< deleteBillItem()");         
       }        
   }        

  public final void deleteBillItemWhere(String whereClause, Object[] whereParams, int[] paramTypes)   
    throws BillItemFinderException, BillItemDeleteException  {      
   if (logger.isEnabledFor(Level.TRACE)) {     
       logger.trace(">> listBillItem()");  
   }   
   BillItemDTO[] _BillItem = null;  
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
               throw new BillItemFinderException("");       
           }        
   } catch (SQLException e) {       
           logSQLException(e);      
           if (isSQLConstraintViolated(e)) {        
               throw new BillItemDeleteException(e.getMessage(), e);      
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
       logger.trace("<< deleteBillItem()");         
   }        
   }        

  public final void deleteBillItemByActive(int _Active) throws BillItemFinderException, BillItemDeleteException{ 
    String whereClause = " WHERE ACTIVE = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Integer(_Active);   
    types[0] = java.sql.Types.INTEGER;   
    deleteBillItemWhere(whereClause, objs, types); 
 } 




  public final void deleteBillItemByAmount(BigDecimal _Amount) throws BillItemFinderException, BillItemDeleteException{ 
    String whereClause = " WHERE AMOUNT = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _Amount;   
    types[0] = java.sql.Types.DECIMAL;   
    deleteBillItemWhere(whereClause, objs, types); 
 } 




  public final void deleteBillItemByBillId(long _BillId) throws BillItemFinderException, BillItemDeleteException{ 
    String whereClause = " WHERE BILL_ID = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Long(_BillId);   
    types[0] = java.sql.Types.BIGINT;   
    deleteBillItemWhere(whereClause, objs, types); 
 } 




  public final void deleteBillItemByCreatedBy(String _CreatedBy) throws BillItemFinderException, BillItemDeleteException{ 
    String whereClause = " WHERE CREATED_BY = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _CreatedBy;   
    types[0] = java.sql.Types.VARCHAR;   
    deleteBillItemWhere(whereClause, objs, types); 
 } 




  public final void deleteBillItemByCreatedOn(java.util.Date _CreatedOn) throws BillItemFinderException, BillItemDeleteException{ 
    String whereClause = " WHERE CREATED_ON = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.sql.Timestamp(_CreatedOn.getTime());   
    types[0] = java.sql.Types.TIMESTAMP;   
    deleteBillItemWhere(whereClause, objs, types); 
 } 




  public final void deleteBillItemByDateDue(java.util.Date _DateDue) throws BillItemFinderException, BillItemDeleteException{ 
    String whereClause = " WHERE DATE_DUE = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.sql.Timestamp(_DateDue.getTime());   
    types[0] = java.sql.Types.TIMESTAMP;   
    deleteBillItemWhere(whereClause, objs, types); 
 } 




  public final void deleteBillItemByDay(int _Day) throws BillItemFinderException, BillItemDeleteException{ 
    String whereClause = " WHERE DAY = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Integer(_Day);   
    types[0] = java.sql.Types.INTEGER;   
    deleteBillItemWhere(whereClause, objs, types); 
 } 




  public final void deleteBillItemByDescription(String _Description) throws BillItemFinderException, BillItemDeleteException{ 
    String whereClause = " WHERE DESCRIPTION = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _Description;   
    types[0] = java.sql.Types.VARCHAR;   
    deleteBillItemWhere(whereClause, objs, types); 
 } 




  public final void deleteBillItemByEntityCode(String _EntityCode) throws BillItemFinderException, BillItemDeleteException{ 
    String whereClause = " WHERE ENTITY_CODE = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _EntityCode;   
    types[0] = java.sql.Types.VARCHAR;   
    deleteBillItemWhere(whereClause, objs, types); 
 } 




  public final void deleteBillItemById(long _Id) throws BillItemFinderException, BillItemDeleteException{ 
    String whereClause = " WHERE ID = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Long(_Id);   
    types[0] = java.sql.Types.BIGINT;   
    deleteBillItemWhere(whereClause, objs, types); 
 } 




  public final void deleteBillItemByModifiedBy(String _ModifiedBy) throws BillItemFinderException, BillItemDeleteException{ 
    String whereClause = " WHERE MODIFIED_BY = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _ModifiedBy;   
    types[0] = java.sql.Types.VARCHAR;   
    deleteBillItemWhere(whereClause, objs, types); 
 } 




  public final void deleteBillItemByModifiedOn(java.util.Date _ModifiedOn) throws BillItemFinderException, BillItemDeleteException{ 
    String whereClause = " WHERE MODIFIED_ON = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.sql.Timestamp(_ModifiedOn.getTime());   
    types[0] = java.sql.Types.TIMESTAMP;   
    deleteBillItemWhere(whereClause, objs, types); 
 } 




  public final void deleteBillItemByMonth(int _Month) throws BillItemFinderException, BillItemDeleteException{ 
    String whereClause = " WHERE MONTH = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Integer(_Month);   
    types[0] = java.sql.Types.INTEGER;   
    deleteBillItemWhere(whereClause, objs, types); 
 } 




  public final void deleteBillItemByName(String _Name) throws BillItemFinderException, BillItemDeleteException{ 
    String whereClause = " WHERE NAME = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _Name;   
    types[0] = java.sql.Types.VARCHAR;   
    deleteBillItemWhere(whereClause, objs, types); 
 } 




  public final void deleteBillItemByPayTo(String _PayTo) throws BillItemFinderException, BillItemDeleteException{ 
    String whereClause = " WHERE PAY_TO = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _PayTo;   
    types[0] = java.sql.Types.VARCHAR;   
    deleteBillItemWhere(whereClause, objs, types); 
 } 




  public final void deleteBillItemByRecurringCode(String _RecurringCode) throws BillItemFinderException, BillItemDeleteException{ 
    String whereClause = " WHERE RECURRING_CODE = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _RecurringCode;   
    types[0] = java.sql.Types.VARCHAR;   
    deleteBillItemWhere(whereClause, objs, types); 
 } 




  public final void deleteBillItemByStatus(String _Status) throws BillItemFinderException, BillItemDeleteException{ 
    String whereClause = " WHERE STATUS = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _Status;   
    types[0] = java.sql.Types.VARCHAR;   
    deleteBillItemWhere(whereClause, objs, types); 
 } 




  public final void deleteBillItemByStatusDate(java.util.Date _StatusDate) throws BillItemFinderException, BillItemDeleteException{ 
    String whereClause = " WHERE STATUS_DATE = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.sql.Timestamp(_StatusDate.getTime());   
    types[0] = java.sql.Types.TIMESTAMP;   
    deleteBillItemWhere(whereClause, objs, types); 
 } 




  public final void deleteBillItemByTemplateId(long _TemplateId) throws BillItemFinderException, BillItemDeleteException{ 
    String whereClause = " WHERE TEMPLATE_ID = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Long(_TemplateId);   
    types[0] = java.sql.Types.BIGINT;   
    deleteBillItemWhere(whereClause, objs, types); 
 } 




  public final void deleteBillItemByTimeDue(java.util.Date _TimeDue) throws BillItemFinderException, BillItemDeleteException{ 
    String whereClause = " WHERE TIME_DUE = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.sql.Timestamp(_TimeDue.getTime());   
    types[0] = java.sql.Types.TIMESTAMP;   
    deleteBillItemWhere(whereClause, objs, types); 
 } 




  public final void deleteBillItemByTypeCode(String _TypeCode) throws BillItemFinderException, BillItemDeleteException{ 
    String whereClause = " WHERE TYPE_CODE = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _TypeCode;   
    types[0] = java.sql.Types.VARCHAR;   
    deleteBillItemWhere(whereClause, objs, types); 
 } 




  public final void deleteBillItemByUsername(String _Username) throws BillItemFinderException, BillItemDeleteException{ 
    String whereClause = " WHERE USERNAME = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _Username;   
    types[0] = java.sql.Types.VARCHAR;   
    deleteBillItemWhere(whereClause, objs, types); 
 } 




  public final void deleteBillItemByWeek(int _Week) throws BillItemFinderException, BillItemDeleteException{ 
    String whereClause = " WHERE WEEK = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Integer(_Week);   
    types[0] = java.sql.Types.INTEGER;   
    deleteBillItemWhere(whereClause, objs, types); 
 } 




  public final void deleteBillItemByYear(int _Year) throws BillItemFinderException, BillItemDeleteException{ 
    String whereClause = " WHERE YEAR = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Integer(_Year);   
    types[0] = java.sql.Types.INTEGER;   
    deleteBillItemWhere(whereClause, objs, types); 
 } 




  




public final BillItemDTO extractBillItem(ResultSet rs)       
    throws SQLException {  
         BillItemDTO obj = new BillItemDTO();          
         int i = 1;      
         obj.setActive( rs.getInt(i++) );       
         obj.setAmount( rs.getBigDecimal(i++) );       
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
