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
class BillItemTemplateDAOImpl extends AbstractDAImpl implements BillItemTemplateDAO {

  private static final Logger logger = 
      Logger.getLogger(BillItemTemplateDAOImpl.class);

  protected String tableName = BillItemTemplatePrefix.PREFIX + "BILL_ITEM_TEMPLATE"; 
  public String getTable(){
      return "\"" + this.tableName + "\""; 
}
  public void setTable(String table){
      this.tableName = table; 
}
  protected String getSelectSQL(){
      String sql = " SELECT ACTIVE, AMOUNT, CREATED_BY, CREATED_ON, DAY_DUE, DESCRIPTION, ENTITY_CODE, FROM_DATE, ID, MODIFIED_BY, MODIFIED_ON, NAME, PAY_TO, QUARTZ_EXP, RECURRING_CODE, TO_DATE, TYPE_CODE, USERNAME FROM " + getTable() + " " ;
      return sql;
}
  protected String getSelectCountSQL(){
      String sql = " SELECT COUNT(*) FROM " + getTable();
      return sql;
}
  protected String getInsertSQL(){
      String sql = " INSERT INTO " + getTable() + " ( ACTIVE, AMOUNT, CREATED_BY, CREATED_ON, DAY_DUE, DESCRIPTION, ENTITY_CODE, FROM_DATE, MODIFIED_BY, MODIFIED_ON, NAME, PAY_TO, QUARTZ_EXP, RECURRING_CODE, TO_DATE, TYPE_CODE, USERNAME ) VALUES  ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";
      return sql;
}
  protected String getUpdateSQL(){
      String sql = " UPDATE " + getTable() + " SET  ACTIVE = ? , AMOUNT = ? , CREATED_BY = ? , CREATED_ON = ? , DAY_DUE = ? , DESCRIPTION = ? , ENTITY_CODE = ? , FROM_DATE = ? , MODIFIED_BY = ? , MODIFIED_ON = ? , NAME = ? , PAY_TO = ? , QUARTZ_EXP = ? , RECURRING_CODE = ? , TO_DATE = ? , TYPE_CODE = ? , USERNAME = ?  WHERE ID = ? ";
      return sql;
}
  protected String getUpdateBlobsSQL(){
      String sql = " UPDATE  " + getTable() + "  SET  ACTIVE = ? , AMOUNT = ? , CREATED_BY = ? , CREATED_ON = ? , DAY_DUE = ? , DESCRIPTION = ? , ENTITY_CODE = ? , FROM_DATE = ? , MODIFIED_BY = ? , MODIFIED_ON = ? , NAME = ? , PAY_TO = ? , QUARTZ_EXP = ? , RECURRING_CODE = ? , TO_DATE = ? , TYPE_CODE = ? , USERNAME = ?  WHERE ID = ? ";
      return sql;
}
  protected String getDeleteSQL(){
      String sql = " DELETE FROM  " + getTable() + "  WHERE ID = ?  " ;
      return sql;
}
  protected String getDeleteBySQL(){
      String sql = " DELETE FROM " + getTable() ;
      return sql;
}

  public BillItemTemplateDAOImpl() { 
       factory = new DefaultConnectionProvider( BillItemTemplateJNDI.NON_XA_DATASOURCE ); 
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

  public final BillItemTemplateDTO [] listAllBillItemTemplate(String orderByColumn) { 
    String order = "";    
     if (orderByColumn != null && orderByColumn.trim().length() > 0) {    
        order = " ORDER BY " + orderByColumn;     
     }    
    return listBillItemTemplate(null, null, null, order); 
 } 

public final BillItemTemplateDTO [] listAllBillItemTemplate() { 
    return listBillItemTemplate(null, null, null, null); 
 } 

public final BillItemTemplateDTO [] customBillItemTemplate(String completeSQL ) { 
   if (logger.isEnabledFor(Level.TRACE)) {     
       logger.trace(">> listBillItemTemplate()");  
   }   
   BillItemTemplateDTO[] _BillItemTemplate = null;  
   ResultSet rs = null;    
   PreparedStatement stmt = null;  
   Connection con = openConnection();  
   try {   
           stmt = con.prepareStatement(completeSQL);    
           stmt.setMaxRows(getMaxRows());            rs = stmt.executeQuery();       
           if (rs.next()) {        
               List list = new ArrayList();        
               do {        
                   list.add(extractBillItemTemplate(rs));        
               } while (rs.next());        
               _BillItemTemplate =      
                   (BillItemTemplateDTO[]) list.toArray(new BillItemTemplateDTO[list.size()]);       
           } else {        
               _BillItemTemplate = new BillItemTemplateDTO[0];        
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
           logger.trace("<< findBillItemTemplate()");     
   }       
   return _BillItemTemplate;        
 }  

public final BillItemTemplateDTO [] listBillItemTemplate(String whereClause, Object[] whereParams, int[] paramTypes, String orderClause ) { 
   if (logger.isEnabledFor(Level.TRACE)) {     
       logger.trace(">> listBillItemTemplate()");  
   }   
   if (paginationStartIndex > 0) {    
      return searchPageableBillItemTemplate(whereClause, whereParams, paramTypes, orderClause, paginationStartIndex, paginationPageSize);  
   }  
   BillItemTemplateDTO[] _BillItemTemplate = null;  
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
                   list.add(extractBillItemTemplate(rs));        
               } while (rs.next());        
               _BillItemTemplate =      
                   (BillItemTemplateDTO[]) list.toArray(new BillItemTemplateDTO[list.size()]);       
           } else {        
               _BillItemTemplate = new BillItemTemplateDTO[0];        
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
           logger.trace("<< findBillItemTemplate()");     
   }       
   return _BillItemTemplate;        
 }  

public final BillItemTemplateDTO [] searchPageableBillItemTemplate(String whereClause, Object[] whereParams, int[] paramTypes, String orderClause,int startIndex, int pageSize ) { 
   if (logger.isEnabledFor(Level.TRACE)) {     
       logger.trace(">> searchPageableBillItemTemplate()");  
   }   
   BillItemTemplateDTO[] _BillItemTemplate = null;  
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
                   list.add(extractBillItemTemplate(rs));        
               } while ((rs.next()) && (--len > 0));      
               _BillItemTemplate =      
                   (BillItemTemplateDTO[]) list.toArray(new BillItemTemplateDTO[list.size()]);       
           } else {        
               _BillItemTemplate = new BillItemTemplateDTO[0];        
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
           logger.trace("<< searchPageableBillItemTemplate()");     
   }       
   return _BillItemTemplate;        
 }  

public final int countBillItemTemplate(String whereClause, Object[] whereParams, int[] paramTypes ) { 
   if (logger.isEnabledFor(Level.TRACE)) {     
       logger.trace(">> countBillItemTemplate()");  
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
           logger.trace("<< findBillItemTemplate()");     
   }       
   return count;     
 }  

  public final BillItemTemplateDTO [] listBillItemTemplateByPK(long _Id) { 
     return listBillItemTemplateByPK( _Id, null);     
 } 

  public final BillItemTemplateDTO [] listBillItemTemplateByPK(long _Id, String orderByColumn) { 
    String whereClause = " WHERE ID = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Long(_Id);   
    types[0] = java.sql.Types.BIGINT;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listBillItemTemplate(whereClause, objs, types, order); 
 } 



  public final int countBillItemTemplateByPK(long _Id) { 
    String whereClause = " WHERE ID = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Long(_Id);   
    types[0] = java.sql.Types.BIGINT;   
    return countBillItemTemplate(whereClause, objs, types); 
 } 




  public final BillItemTemplateDTO [] listBillItemTemplateByActive(int _Active) { 
     return listBillItemTemplateByActive( _Active, null);     
 } 

  public final BillItemTemplateDTO [] listBillItemTemplateByActive(int _Active, String orderByColumn) { 
    String whereClause = " WHERE ACTIVE = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Integer(_Active);   
    types[0] = java.sql.Types.INTEGER;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listBillItemTemplate(whereClause, objs, types, order); 
 } 



  public final int countBillItemTemplateByActive(int _Active) { 
    String whereClause = " WHERE ACTIVE = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Integer(_Active);   
    types[0] = java.sql.Types.INTEGER;   
    return countBillItemTemplate(whereClause, objs, types); 
 } 




  public final BillItemTemplateDTO [] listBillItemTemplateByAmount(BigDecimal _Amount) { 
     return listBillItemTemplateByAmount( _Amount, null);     
 } 

  public final BillItemTemplateDTO [] listBillItemTemplateByAmount(BigDecimal _Amount, String orderByColumn) { 
    String whereClause = " WHERE AMOUNT = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _Amount;   
    types[0] = java.sql.Types.DECIMAL;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listBillItemTemplate(whereClause, objs, types, order); 
 } 



  public final int countBillItemTemplateByAmount(BigDecimal _Amount) { 
    String whereClause = " WHERE AMOUNT = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _Amount;   
    types[0] = java.sql.Types.DECIMAL;   
    return countBillItemTemplate(whereClause, objs, types); 
 } 




  public final BillItemTemplateDTO [] listBillItemTemplateByCreatedBy(String _CreatedBy) { 
     return listBillItemTemplateByCreatedBy( _CreatedBy, null);     
 } 

  public final BillItemTemplateDTO [] listBillItemTemplateByCreatedBy(String _CreatedBy, String orderByColumn) { 
    String whereClause = " WHERE CREATED_BY = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _CreatedBy;   
    types[0] = java.sql.Types.VARCHAR;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listBillItemTemplate(whereClause, objs, types, order); 
 } 



  public final int countBillItemTemplateByCreatedBy(String _CreatedBy) { 
    String whereClause = " WHERE CREATED_BY = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _CreatedBy;   
    types[0] = java.sql.Types.VARCHAR;   
    return countBillItemTemplate(whereClause, objs, types); 
 } 




  public final BillItemTemplateDTO [] listBillItemTemplateByCreatedOn(java.util.Date _CreatedOn) { 
     return listBillItemTemplateByCreatedOn( _CreatedOn, null);     
 } 

  public final BillItemTemplateDTO [] listBillItemTemplateByCreatedOn(java.util.Date _CreatedOn, String orderByColumn) { 
    String whereClause = " WHERE CREATED_ON = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.sql.Timestamp(_CreatedOn.getTime());   
    types[0] = java.sql.Types.TIMESTAMP;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listBillItemTemplate(whereClause, objs, types, order); 
 } 



  public final int countBillItemTemplateByCreatedOn(java.util.Date _CreatedOn) { 
    String whereClause = " WHERE CREATED_ON = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.sql.Timestamp(_CreatedOn.getTime());   
    types[0] = java.sql.Types.TIMESTAMP;   
    return countBillItemTemplate(whereClause, objs, types); 
 } 




  public final BillItemTemplateDTO [] listBillItemTemplateByDayDue(int _DayDue) { 
     return listBillItemTemplateByDayDue( _DayDue, null);     
 } 

  public final BillItemTemplateDTO [] listBillItemTemplateByDayDue(int _DayDue, String orderByColumn) { 
    String whereClause = " WHERE DAY_DUE = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Integer(_DayDue);   
    types[0] = java.sql.Types.INTEGER;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listBillItemTemplate(whereClause, objs, types, order); 
 } 



  public final int countBillItemTemplateByDayDue(int _DayDue) { 
    String whereClause = " WHERE DAY_DUE = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Integer(_DayDue);   
    types[0] = java.sql.Types.INTEGER;   
    return countBillItemTemplate(whereClause, objs, types); 
 } 




  public final BillItemTemplateDTO [] listBillItemTemplateByDescription(String _Description) { 
     return listBillItemTemplateByDescription( _Description, null);     
 } 

  public final BillItemTemplateDTO [] listBillItemTemplateByDescription(String _Description, String orderByColumn) { 
    String whereClause = " WHERE DESCRIPTION = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _Description;   
    types[0] = java.sql.Types.VARCHAR;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listBillItemTemplate(whereClause, objs, types, order); 
 } 



  public final int countBillItemTemplateByDescription(String _Description) { 
    String whereClause = " WHERE DESCRIPTION = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _Description;   
    types[0] = java.sql.Types.VARCHAR;   
    return countBillItemTemplate(whereClause, objs, types); 
 } 




  public final BillItemTemplateDTO [] listBillItemTemplateByEntityCode(String _EntityCode) { 
     return listBillItemTemplateByEntityCode( _EntityCode, null);     
 } 

  public final BillItemTemplateDTO [] listBillItemTemplateByEntityCode(String _EntityCode, String orderByColumn) { 
    String whereClause = " WHERE ENTITY_CODE = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _EntityCode;   
    types[0] = java.sql.Types.VARCHAR;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listBillItemTemplate(whereClause, objs, types, order); 
 } 



  public final int countBillItemTemplateByEntityCode(String _EntityCode) { 
    String whereClause = " WHERE ENTITY_CODE = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _EntityCode;   
    types[0] = java.sql.Types.VARCHAR;   
    return countBillItemTemplate(whereClause, objs, types); 
 } 




  public final BillItemTemplateDTO [] listBillItemTemplateByFromDate(java.util.Date _FromDate) { 
     return listBillItemTemplateByFromDate( _FromDate, null);     
 } 

  public final BillItemTemplateDTO [] listBillItemTemplateByFromDate(java.util.Date _FromDate, String orderByColumn) { 
    String whereClause = " WHERE FROM_DATE = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.sql.Timestamp(_FromDate.getTime());   
    types[0] = java.sql.Types.TIMESTAMP;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listBillItemTemplate(whereClause, objs, types, order); 
 } 



  public final int countBillItemTemplateByFromDate(java.util.Date _FromDate) { 
    String whereClause = " WHERE FROM_DATE = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.sql.Timestamp(_FromDate.getTime());   
    types[0] = java.sql.Types.TIMESTAMP;   
    return countBillItemTemplate(whereClause, objs, types); 
 } 




  public final BillItemTemplateDTO [] listBillItemTemplateById(long _Id) { 
     return listBillItemTemplateById( _Id, null);     
 } 

  public final BillItemTemplateDTO [] listBillItemTemplateById(long _Id, String orderByColumn) { 
    String whereClause = " WHERE ID = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Long(_Id);   
    types[0] = java.sql.Types.BIGINT;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listBillItemTemplate(whereClause, objs, types, order); 
 } 



  public final int countBillItemTemplateById(long _Id) { 
    String whereClause = " WHERE ID = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Long(_Id);   
    types[0] = java.sql.Types.BIGINT;   
    return countBillItemTemplate(whereClause, objs, types); 
 } 




  public final BillItemTemplateDTO [] listBillItemTemplateByModifiedBy(String _ModifiedBy) { 
     return listBillItemTemplateByModifiedBy( _ModifiedBy, null);     
 } 

  public final BillItemTemplateDTO [] listBillItemTemplateByModifiedBy(String _ModifiedBy, String orderByColumn) { 
    String whereClause = " WHERE MODIFIED_BY = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _ModifiedBy;   
    types[0] = java.sql.Types.VARCHAR;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listBillItemTemplate(whereClause, objs, types, order); 
 } 



  public final int countBillItemTemplateByModifiedBy(String _ModifiedBy) { 
    String whereClause = " WHERE MODIFIED_BY = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _ModifiedBy;   
    types[0] = java.sql.Types.VARCHAR;   
    return countBillItemTemplate(whereClause, objs, types); 
 } 




  public final BillItemTemplateDTO [] listBillItemTemplateByModifiedOn(java.util.Date _ModifiedOn) { 
     return listBillItemTemplateByModifiedOn( _ModifiedOn, null);     
 } 

  public final BillItemTemplateDTO [] listBillItemTemplateByModifiedOn(java.util.Date _ModifiedOn, String orderByColumn) { 
    String whereClause = " WHERE MODIFIED_ON = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.sql.Timestamp(_ModifiedOn.getTime());   
    types[0] = java.sql.Types.TIMESTAMP;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listBillItemTemplate(whereClause, objs, types, order); 
 } 



  public final int countBillItemTemplateByModifiedOn(java.util.Date _ModifiedOn) { 
    String whereClause = " WHERE MODIFIED_ON = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.sql.Timestamp(_ModifiedOn.getTime());   
    types[0] = java.sql.Types.TIMESTAMP;   
    return countBillItemTemplate(whereClause, objs, types); 
 } 




  public final BillItemTemplateDTO [] listBillItemTemplateByName(String _Name) { 
     return listBillItemTemplateByName( _Name, null);     
 } 

  public final BillItemTemplateDTO [] listBillItemTemplateByName(String _Name, String orderByColumn) { 
    String whereClause = " WHERE NAME = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _Name;   
    types[0] = java.sql.Types.VARCHAR;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listBillItemTemplate(whereClause, objs, types, order); 
 } 



  public final int countBillItemTemplateByName(String _Name) { 
    String whereClause = " WHERE NAME = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _Name;   
    types[0] = java.sql.Types.VARCHAR;   
    return countBillItemTemplate(whereClause, objs, types); 
 } 




  public final BillItemTemplateDTO [] listBillItemTemplateByPayTo(String _PayTo) { 
     return listBillItemTemplateByPayTo( _PayTo, null);     
 } 

  public final BillItemTemplateDTO [] listBillItemTemplateByPayTo(String _PayTo, String orderByColumn) { 
    String whereClause = " WHERE PAY_TO = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _PayTo;   
    types[0] = java.sql.Types.VARCHAR;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listBillItemTemplate(whereClause, objs, types, order); 
 } 



  public final int countBillItemTemplateByPayTo(String _PayTo) { 
    String whereClause = " WHERE PAY_TO = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _PayTo;   
    types[0] = java.sql.Types.VARCHAR;   
    return countBillItemTemplate(whereClause, objs, types); 
 } 




  public final BillItemTemplateDTO [] listBillItemTemplateByQuartzExp(String _QuartzExp) { 
     return listBillItemTemplateByQuartzExp( _QuartzExp, null);     
 } 

  public final BillItemTemplateDTO [] listBillItemTemplateByQuartzExp(String _QuartzExp, String orderByColumn) { 
    String whereClause = " WHERE QUARTZ_EXP = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _QuartzExp;   
    types[0] = java.sql.Types.VARCHAR;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listBillItemTemplate(whereClause, objs, types, order); 
 } 



  public final int countBillItemTemplateByQuartzExp(String _QuartzExp) { 
    String whereClause = " WHERE QUARTZ_EXP = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _QuartzExp;   
    types[0] = java.sql.Types.VARCHAR;   
    return countBillItemTemplate(whereClause, objs, types); 
 } 




  public final BillItemTemplateDTO [] listBillItemTemplateByRecurringCode(String _RecurringCode) { 
     return listBillItemTemplateByRecurringCode( _RecurringCode, null);     
 } 

  public final BillItemTemplateDTO [] listBillItemTemplateByRecurringCode(String _RecurringCode, String orderByColumn) { 
    String whereClause = " WHERE RECURRING_CODE = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _RecurringCode;   
    types[0] = java.sql.Types.VARCHAR;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listBillItemTemplate(whereClause, objs, types, order); 
 } 



  public final int countBillItemTemplateByRecurringCode(String _RecurringCode) { 
    String whereClause = " WHERE RECURRING_CODE = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _RecurringCode;   
    types[0] = java.sql.Types.VARCHAR;   
    return countBillItemTemplate(whereClause, objs, types); 
 } 




  public final BillItemTemplateDTO [] listBillItemTemplateByToDate(java.util.Date _ToDate) { 
     return listBillItemTemplateByToDate( _ToDate, null);     
 } 

  public final BillItemTemplateDTO [] listBillItemTemplateByToDate(java.util.Date _ToDate, String orderByColumn) { 
    String whereClause = " WHERE TO_DATE = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.sql.Timestamp(_ToDate.getTime());   
    types[0] = java.sql.Types.TIMESTAMP;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listBillItemTemplate(whereClause, objs, types, order); 
 } 



  public final int countBillItemTemplateByToDate(java.util.Date _ToDate) { 
    String whereClause = " WHERE TO_DATE = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.sql.Timestamp(_ToDate.getTime());   
    types[0] = java.sql.Types.TIMESTAMP;   
    return countBillItemTemplate(whereClause, objs, types); 
 } 




  public final BillItemTemplateDTO [] listBillItemTemplateByTypeCode(String _TypeCode) { 
     return listBillItemTemplateByTypeCode( _TypeCode, null);     
 } 

  public final BillItemTemplateDTO [] listBillItemTemplateByTypeCode(String _TypeCode, String orderByColumn) { 
    String whereClause = " WHERE TYPE_CODE = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _TypeCode;   
    types[0] = java.sql.Types.VARCHAR;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listBillItemTemplate(whereClause, objs, types, order); 
 } 



  public final int countBillItemTemplateByTypeCode(String _TypeCode) { 
    String whereClause = " WHERE TYPE_CODE = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _TypeCode;   
    types[0] = java.sql.Types.VARCHAR;   
    return countBillItemTemplate(whereClause, objs, types); 
 } 




  public final BillItemTemplateDTO [] listBillItemTemplateByUsername(String _Username) { 
     return listBillItemTemplateByUsername( _Username, null);     
 } 

  public final BillItemTemplateDTO [] listBillItemTemplateByUsername(String _Username, String orderByColumn) { 
    String whereClause = " WHERE USERNAME = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _Username;   
    types[0] = java.sql.Types.VARCHAR;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listBillItemTemplate(whereClause, objs, types, order); 
 } 



  public final int countBillItemTemplateByUsername(String _Username) { 
    String whereClause = " WHERE USERNAME = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _Username;   
    types[0] = java.sql.Types.VARCHAR;   
    return countBillItemTemplate(whereClause, objs, types); 
 } 









      
      
   
public void retrieveBlobDataByPK(String columnName, long _Id, OutputStream out) throws  Exception {  
}  
   
   
   
public void retrieveBlobDataByPK(String columnName, long _Id, DAOStreamReader reader) throws  Exception {  
   
    if (logger.isEnabledFor(Level.TRACE)) {  
            logger.trace(">> retrieveBlobDataByPK()");  
     }   
        ResultSet rs = null;  
        String sql = "SELECT "  + columnName + " FROM " + getTable()  
                     + "  WHERE ID = ? ";  
        PreparedStatement stmt = null;  
        Connection con = openConnection();  
        try {  
            int i = 1;  
            stmt = con.prepareStatement(sql);  
      stmt.setObject(i++, new java.lang.Long(_Id), java.sql.Types.BIGINT );   

            rs = stmt.executeQuery();  
            if (!rs.next()) {  
                throw new BillItemTemplateFinderException("");  
            }  
            int blobColPosition = 1;
            processBlobData(reader, rs, blobColPosition );  
        } catch (SQLException e) {  
            logSQLException(sql, e);  
            throw new DAOException(e);  
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
            logger.trace("<< retrieveBlobDataByPK()");  
        }  
    }  
  
  public final BillItemTemplateDTO createBillItemTemplate(BillItemTemplateDTO _BillItemTemplate)
    throws BillItemTemplateCreateException {  
         if (logger.isEnabledFor(Level.TRACE)) {
             logger.trace(">> createBillItemTemplate()");
         }  
             PreparedStatement stmt = null;   
             Connection con = openConnection();
         try { 
                 int i = 1;
                 stmt = con.prepareStatement(getInsertSQL(), Statement.RETURN_GENERATED_KEYS);
                  stmt.setInt(i++, _BillItemTemplate.getActive()); 
                  stmt.setBigDecimal(i++, _BillItemTemplate.getAmount()); 
                  stmt.setString(i++, _BillItemTemplate.getCreatedBy()); 
                  if (_BillItemTemplate.getCreatedOn() != null) { 
                     stmt.setTimestamp(i++, new java.sql.Timestamp(_BillItemTemplate.getCreatedOn().getTime()) ); 
                  } else {
                     stmt.setNull(i++, java.sql.Types.TIMESTAMP); 
                  } 
                  stmt.setInt(i++, _BillItemTemplate.getDayDue()); 
                  stmt.setString(i++, _BillItemTemplate.getDescription()); 
                  stmt.setString(i++, _BillItemTemplate.getEntityCode()); 
                  if (_BillItemTemplate.getFromDate() != null) { 
                     stmt.setTimestamp(i++, new java.sql.Timestamp(_BillItemTemplate.getFromDate().getTime()) ); 
                  } else {
                     stmt.setNull(i++, java.sql.Types.TIMESTAMP); 
                  } 
                  stmt.setString(i++, _BillItemTemplate.getModifiedBy()); 
                  if (_BillItemTemplate.getModifiedOn() != null) { 
                     stmt.setTimestamp(i++, new java.sql.Timestamp(_BillItemTemplate.getModifiedOn().getTime()) ); 
                  } else {
                     stmt.setNull(i++, java.sql.Types.TIMESTAMP); 
                  } 
                  stmt.setString(i++, _BillItemTemplate.getName()); 
                  stmt.setString(i++, _BillItemTemplate.getPayTo()); 
                  stmt.setString(i++, _BillItemTemplate.getQuartzExp()); 
                  stmt.setString(i++, _BillItemTemplate.getRecurringCode()); 
                  if (_BillItemTemplate.getToDate() != null) { 
                     stmt.setTimestamp(i++, new java.sql.Timestamp(_BillItemTemplate.getToDate().getTime()) ); 
                  } else {
                     stmt.setNull(i++, java.sql.Types.TIMESTAMP); 
                  } 
                  stmt.setString(i++, _BillItemTemplate.getTypeCode()); 
                  stmt.setString(i++, _BillItemTemplate.getUsername()); 
                 stmt.executeUpdate();     
                  int generatedId = -1;      
                  ResultSet rs = stmt.getGeneratedKeys();      
                  while (rs.next()) {      
                     generatedId = (int) rs.getLong(1);      
                  }       
                  rs.close();      
                  _BillItemTemplate.setId(generatedId);     
         } catch (SQLException e) {     
             logSQLException(e);     
             if (isSQLConstraintViolated(e)) {     
                 throw new BillItemTemplateCreateException(e.getMessage(), e);     
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
             logger.trace("<< createBillItemTemplate()");     
         }     
         return _BillItemTemplate;     
     }       

  public final void updateBillItemTemplate(BillItemTemplateDTO _BillItemTemplate) 
    throws BillItemTemplateUpdateException, BillItemTemplateFinderException  {      
    if (logger.isEnabledFor(Level.TRACE)) {         
       logger.trace(">> updateBillItemTemplate()");         
    }       
    PreparedStatement stmt = null;      
    Connection con = openConnection();      
       try {        
           int i = 1;       
           stmt = con.prepareStatement(getUpdateSQL());         
                  stmt.setInt(i++,  _BillItemTemplate.getActive() ); 
                  stmt.setBigDecimal(i++,  _BillItemTemplate.getAmount() ); 
                  stmt.setString(i++,  _BillItemTemplate.getCreatedBy() ); 
             if (_BillItemTemplate.getCreatedOn() != null) { 
                  stmt.setTimestamp(i++,  new java.sql.Timestamp(_BillItemTemplate.getCreatedOn().getTime()) ); 
             } else { 
                  stmt.setNull(i++, java.sql.Types.TIMESTAMP); 
             }
                  stmt.setInt(i++,  _BillItemTemplate.getDayDue() ); 
                  stmt.setString(i++,  _BillItemTemplate.getDescription() ); 
                  stmt.setString(i++,  _BillItemTemplate.getEntityCode() ); 
             if (_BillItemTemplate.getFromDate() != null) { 
                  stmt.setTimestamp(i++,  new java.sql.Timestamp(_BillItemTemplate.getFromDate().getTime()) ); 
             } else { 
                  stmt.setNull(i++, java.sql.Types.TIMESTAMP); 
             }
                  stmt.setString(i++,  _BillItemTemplate.getModifiedBy() ); 
             if (_BillItemTemplate.getModifiedOn() != null) { 
                  stmt.setTimestamp(i++,  new java.sql.Timestamp(_BillItemTemplate.getModifiedOn().getTime()) ); 
             } else { 
                  stmt.setNull(i++, java.sql.Types.TIMESTAMP); 
             }
                  stmt.setString(i++,  _BillItemTemplate.getName() ); 
                  stmt.setString(i++,  _BillItemTemplate.getPayTo() ); 
                  stmt.setString(i++,  _BillItemTemplate.getQuartzExp() ); 
                  stmt.setString(i++,  _BillItemTemplate.getRecurringCode() ); 
             if (_BillItemTemplate.getToDate() != null) { 
                  stmt.setTimestamp(i++,  new java.sql.Timestamp(_BillItemTemplate.getToDate().getTime()) ); 
             } else { 
                  stmt.setNull(i++, java.sql.Types.TIMESTAMP); 
             }
                  stmt.setString(i++,  _BillItemTemplate.getTypeCode() ); 
                  stmt.setString(i++,  _BillItemTemplate.getUsername() ); 
  /*** now set the criteria, PK values ***/ 
             stmt.setLong(i++,  _BillItemTemplate.getId() ); 
           int count = stmt.executeUpdate();        
           if (count == 0) {        
               throw new BillItemTemplateFinderException("");       
           }        
       } catch (SQLException e) {       
           logSQLException(e);      
           if (isSQLConstraintViolated(e)) {        
               throw new BillItemTemplateUpdateException("");       
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
           logger.trace("<< updateBillItemTemplate()");         
       }        
   }        

  public final void deleteBillItemTemplate(BillItemTemplateDTO _BillItemTemplate) 
    throws BillItemTemplateFinderException, BillItemTemplateDeleteException  {      
    if (logger.isEnabledFor(Level.TRACE)) {         
       logger.trace(">> deleteBillItemTemplate()");         
    }       
    PreparedStatement stmt = null;      
    Connection con = openConnection();      
       try {        
           int i = 1;       
           stmt = con.prepareStatement(getDeleteSQL());         
             stmt.setLong(i++,  _BillItemTemplate.getId() ); 
           int count = stmt.executeUpdate();        
           if (count == 0) {        
               throw new BillItemTemplateFinderException("");       
           }        
       } catch (SQLException e) {       
           logSQLException(e);      
           if (isSQLConstraintViolated(e)) {        
               throw new BillItemTemplateDeleteException("");       
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
           logger.trace("<< deleteBillItemTemplate()");         
       }        
   }        

  public final void deleteBillItemTemplateWhere(String whereClause, Object[] whereParams, int[] paramTypes)   
    throws BillItemTemplateFinderException, BillItemTemplateDeleteException  {      
   if (logger.isEnabledFor(Level.TRACE)) {     
       logger.trace(">> listBillItemTemplate()");  
   }   
   BillItemTemplateDTO[] _BillItemTemplate = null;  
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
               throw new BillItemTemplateFinderException("");       
           }        
   } catch (SQLException e) {       
           logSQLException(e);      
           if (isSQLConstraintViolated(e)) {        
               throw new BillItemTemplateDeleteException(e.getMessage(), e);      
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
       logger.trace("<< deleteBillItemTemplate()");         
   }        
   }        

  public final void deleteBillItemTemplateByActive(int _Active) throws BillItemTemplateFinderException, BillItemTemplateDeleteException{ 
    String whereClause = " WHERE ACTIVE = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Integer(_Active);   
    types[0] = java.sql.Types.INTEGER;   
    deleteBillItemTemplateWhere(whereClause, objs, types); 
 } 




  public final void deleteBillItemTemplateByAmount(BigDecimal _Amount) throws BillItemTemplateFinderException, BillItemTemplateDeleteException{ 
    String whereClause = " WHERE AMOUNT = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _Amount;   
    types[0] = java.sql.Types.DECIMAL;   
    deleteBillItemTemplateWhere(whereClause, objs, types); 
 } 




  public final void deleteBillItemTemplateByCreatedBy(String _CreatedBy) throws BillItemTemplateFinderException, BillItemTemplateDeleteException{ 
    String whereClause = " WHERE CREATED_BY = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _CreatedBy;   
    types[0] = java.sql.Types.VARCHAR;   
    deleteBillItemTemplateWhere(whereClause, objs, types); 
 } 




  public final void deleteBillItemTemplateByCreatedOn(java.util.Date _CreatedOn) throws BillItemTemplateFinderException, BillItemTemplateDeleteException{ 
    String whereClause = " WHERE CREATED_ON = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.sql.Timestamp(_CreatedOn.getTime());   
    types[0] = java.sql.Types.TIMESTAMP;   
    deleteBillItemTemplateWhere(whereClause, objs, types); 
 } 




  public final void deleteBillItemTemplateByDayDue(int _DayDue) throws BillItemTemplateFinderException, BillItemTemplateDeleteException{ 
    String whereClause = " WHERE DAY_DUE = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Integer(_DayDue);   
    types[0] = java.sql.Types.INTEGER;   
    deleteBillItemTemplateWhere(whereClause, objs, types); 
 } 




  public final void deleteBillItemTemplateByDescription(String _Description) throws BillItemTemplateFinderException, BillItemTemplateDeleteException{ 
    String whereClause = " WHERE DESCRIPTION = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _Description;   
    types[0] = java.sql.Types.VARCHAR;   
    deleteBillItemTemplateWhere(whereClause, objs, types); 
 } 




  public final void deleteBillItemTemplateByEntityCode(String _EntityCode) throws BillItemTemplateFinderException, BillItemTemplateDeleteException{ 
    String whereClause = " WHERE ENTITY_CODE = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _EntityCode;   
    types[0] = java.sql.Types.VARCHAR;   
    deleteBillItemTemplateWhere(whereClause, objs, types); 
 } 




  public final void deleteBillItemTemplateByFromDate(java.util.Date _FromDate) throws BillItemTemplateFinderException, BillItemTemplateDeleteException{ 
    String whereClause = " WHERE FROM_DATE = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.sql.Timestamp(_FromDate.getTime());   
    types[0] = java.sql.Types.TIMESTAMP;   
    deleteBillItemTemplateWhere(whereClause, objs, types); 
 } 




  public final void deleteBillItemTemplateById(long _Id) throws BillItemTemplateFinderException, BillItemTemplateDeleteException{ 
    String whereClause = " WHERE ID = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Long(_Id);   
    types[0] = java.sql.Types.BIGINT;   
    deleteBillItemTemplateWhere(whereClause, objs, types); 
 } 




  public final void deleteBillItemTemplateByModifiedBy(String _ModifiedBy) throws BillItemTemplateFinderException, BillItemTemplateDeleteException{ 
    String whereClause = " WHERE MODIFIED_BY = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _ModifiedBy;   
    types[0] = java.sql.Types.VARCHAR;   
    deleteBillItemTemplateWhere(whereClause, objs, types); 
 } 




  public final void deleteBillItemTemplateByModifiedOn(java.util.Date _ModifiedOn) throws BillItemTemplateFinderException, BillItemTemplateDeleteException{ 
    String whereClause = " WHERE MODIFIED_ON = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.sql.Timestamp(_ModifiedOn.getTime());   
    types[0] = java.sql.Types.TIMESTAMP;   
    deleteBillItemTemplateWhere(whereClause, objs, types); 
 } 




  public final void deleteBillItemTemplateByName(String _Name) throws BillItemTemplateFinderException, BillItemTemplateDeleteException{ 
    String whereClause = " WHERE NAME = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _Name;   
    types[0] = java.sql.Types.VARCHAR;   
    deleteBillItemTemplateWhere(whereClause, objs, types); 
 } 




  public final void deleteBillItemTemplateByPayTo(String _PayTo) throws BillItemTemplateFinderException, BillItemTemplateDeleteException{ 
    String whereClause = " WHERE PAY_TO = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _PayTo;   
    types[0] = java.sql.Types.VARCHAR;   
    deleteBillItemTemplateWhere(whereClause, objs, types); 
 } 




  public final void deleteBillItemTemplateByQuartzExp(String _QuartzExp) throws BillItemTemplateFinderException, BillItemTemplateDeleteException{ 
    String whereClause = " WHERE QUARTZ_EXP = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _QuartzExp;   
    types[0] = java.sql.Types.VARCHAR;   
    deleteBillItemTemplateWhere(whereClause, objs, types); 
 } 




  public final void deleteBillItemTemplateByRecurringCode(String _RecurringCode) throws BillItemTemplateFinderException, BillItemTemplateDeleteException{ 
    String whereClause = " WHERE RECURRING_CODE = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _RecurringCode;   
    types[0] = java.sql.Types.VARCHAR;   
    deleteBillItemTemplateWhere(whereClause, objs, types); 
 } 




  public final void deleteBillItemTemplateByToDate(java.util.Date _ToDate) throws BillItemTemplateFinderException, BillItemTemplateDeleteException{ 
    String whereClause = " WHERE TO_DATE = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.sql.Timestamp(_ToDate.getTime());   
    types[0] = java.sql.Types.TIMESTAMP;   
    deleteBillItemTemplateWhere(whereClause, objs, types); 
 } 




  public final void deleteBillItemTemplateByTypeCode(String _TypeCode) throws BillItemTemplateFinderException, BillItemTemplateDeleteException{ 
    String whereClause = " WHERE TYPE_CODE = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _TypeCode;   
    types[0] = java.sql.Types.VARCHAR;   
    deleteBillItemTemplateWhere(whereClause, objs, types); 
 } 




  public final void deleteBillItemTemplateByUsername(String _Username) throws BillItemTemplateFinderException, BillItemTemplateDeleteException{ 
    String whereClause = " WHERE USERNAME = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _Username;   
    types[0] = java.sql.Types.VARCHAR;   
    deleteBillItemTemplateWhere(whereClause, objs, types); 
 } 




public final BillItemTemplateDTO extractBillItemTemplate(ResultSet rs)       
    throws SQLException {  
         BillItemTemplateDTO obj = new BillItemTemplateDTO();          
         int i = 1;      
         obj.setActive( rs.getInt(i++) );       
         obj.setAmount( rs.getBigDecimal(i++) );       
         obj.setCreatedBy( rs.getString(i++) );       
         obj.setCreatedOn( rs.getTimestamp(i++) );       
         obj.setDayDue( rs.getInt(i++) );       
         obj.setDescription( rs.getString(i++) );       
         obj.setEntityCode( rs.getString(i++) );       
         obj.setFromDate( rs.getTimestamp(i++) );       
         obj.setId( rs.getLong(i++) );       
         obj.setModifiedBy( rs.getString(i++) );       
         obj.setModifiedOn( rs.getTimestamp(i++) );       
         obj.setName( rs.getString(i++) );       
         obj.setPayTo( rs.getString(i++) );       
         obj.setQuartzExp( rs.getString(i++) );       
         obj.setRecurringCode( rs.getString(i++) );       
         obj.setToDate( rs.getTimestamp(i++) );       
         obj.setTypeCode( rs.getString(i++) );       
         obj.setUsername( rs.getString(i++) );       
 return obj;  } 


 /*************** end *****************/
}
