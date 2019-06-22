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
package com.hersa.sample.app.dao.billsummary;
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
class VBillSummaryDAOImpl extends AbstractDAImpl implements VBillSummaryDAO {

  private static final Logger logger = 
      Logger.getLogger(VBillSummaryDAOImpl.class);

  protected String tableName = VBillSummaryPrefix.PREFIX + "V_BILL_SUMMARY"; 
  public String getTable(){
      return "\"" + this.tableName + "\""; 
}
  public void setTable(String table){
      this.tableName = table; 
}
  protected String getSelectSQL(){
      String sql = " SELECT CREATED_BY, CREATED_ON, CYCLE_TYPE, DAY, DESCRIPTOR, END_DATE, ID, MODIFIED_BY, MODIFIED_ON, MONTH, START_DATE, STATUS, STATUS_DATE, TOTAL_BILLED, TOTAL_PAID, USERNAME, WEEK, YEAR, CREATED_BY, CREATED_ON, CYCLE_TYPE, DAY, DESCRIPTOR, END_DATE, ID, MODIFIED_BY, MODIFIED_ON, MONTH, START_DATE, STATUS, STATUS_DATE, TOTAL_BILLED, TOTAL_PAID, USERNAME, WEEK, YEAR FROM " + getTable() + " " ;
      return sql;
}
  protected String getSelectCountSQL(){
      String sql = " SELECT COUNT(*) FROM " + getTable();
      return sql;
}
  protected String getInsertSQL(){
      String sql = " INSERT INTO " + getTable() + " ( CREATED_BY, CREATED_ON, CYCLE_TYPE, DAY, DESCRIPTOR, END_DATE, ID, MODIFIED_BY, MODIFIED_ON, MONTH, START_DATE, STATUS, STATUS_DATE, TOTAL_BILLED, TOTAL_PAID, USERNAME, WEEK, YEAR, CREATED_BY, CREATED_ON, CYCLE_TYPE, DAY, DESCRIPTOR, END_DATE, ID, MODIFIED_BY, MODIFIED_ON, MONTH, START_DATE, STATUS, STATUS_DATE, TOTAL_BILLED, TOTAL_PAID, USERNAME, WEEK, YEAR ) VALUES  ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";
      return sql;
}
  protected String getUpdateSQL(){
      String sql = " UPDATE " + getTable() + " SET  CREATED_BY = ? , CREATED_ON = ? , CYCLE_TYPE = ? , DAY = ? , DESCRIPTOR = ? , END_DATE = ? , ID = ? , MODIFIED_BY = ? , MODIFIED_ON = ? , MONTH = ? , START_DATE = ? , STATUS = ? , STATUS_DATE = ? , TOTAL_BILLED = ? , TOTAL_PAID = ? , USERNAME = ? , WEEK = ? , YEAR = ? , CREATED_BY = ? , CREATED_ON = ? , CYCLE_TYPE = ? , DAY = ? , DESCRIPTOR = ? , END_DATE = ? , ID = ? , MODIFIED_BY = ? , MODIFIED_ON = ? , MONTH = ? , START_DATE = ? , STATUS = ? , STATUS_DATE = ? , TOTAL_BILLED = ? , TOTAL_PAID = ? , USERNAME = ? , WEEK = ? , YEAR = ?  WHERE ";
      return sql;
}
  protected String getUpdateBlobsSQL(){
      String sql = " UPDATE  " + getTable() + "  SET  CREATED_BY = ? , CREATED_ON = ? , CYCLE_TYPE = ? , DAY = ? , DESCRIPTOR = ? , END_DATE = ? , ID = ? , MODIFIED_BY = ? , MODIFIED_ON = ? , MONTH = ? , START_DATE = ? , STATUS = ? , STATUS_DATE = ? , TOTAL_BILLED = ? , TOTAL_PAID = ? , USERNAME = ? , WEEK = ? , YEAR = ? , CREATED_BY = ? , CREATED_ON = ? , CYCLE_TYPE = ? , DAY = ? , DESCRIPTOR = ? , END_DATE = ? , ID = ? , MODIFIED_BY = ? , MODIFIED_ON = ? , MONTH = ? , START_DATE = ? , STATUS = ? , STATUS_DATE = ? , TOTAL_BILLED = ? , TOTAL_PAID = ? , USERNAME = ? , WEEK = ? , YEAR = ?  WHERE ";
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

  public VBillSummaryDAOImpl() { 
       factory = new DefaultConnectionProvider( VBillSummaryJNDI.NON_XA_DATASOURCE ); 
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

  public final VBillSummaryDTO [] listAllVBillSummary(String orderByColumn) { 
    String order = "";    
     if (orderByColumn != null && orderByColumn.trim().length() > 0) {    
        order = " ORDER BY " + orderByColumn;     
     }    
    return listVBillSummary(null, null, null, order); 
 } 

public final VBillSummaryDTO [] listAllVBillSummary() { 
    return listVBillSummary(null, null, null, null); 
 } 

public final VBillSummaryDTO [] customVBillSummary(String completeSQL ) { 
   if (logger.isEnabledFor(Level.TRACE)) {     
       logger.trace(">> listVBillSummary()");  
   }   
   VBillSummaryDTO[] _VBillSummary = null;  
   ResultSet rs = null;    
   PreparedStatement stmt = null;  
   Connection con = openConnection();  
   try {   
           stmt = con.prepareStatement(completeSQL);    
           stmt.setMaxRows(getMaxRows());            rs = stmt.executeQuery();       
           if (rs.next()) {        
               List list = new ArrayList();        
               do {        
                   list.add(extractVBillSummary(rs));        
               } while (rs.next());        
               _VBillSummary =      
                   (VBillSummaryDTO[]) list.toArray(new VBillSummaryDTO[list.size()]);       
           } else {        
               _VBillSummary = new VBillSummaryDTO[0];        
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
           logger.trace("<< findVBillSummary()");     
   }       
   return _VBillSummary;        
 }  

public final VBillSummaryDTO [] listVBillSummary(String whereClause, Object[] whereParams, int[] paramTypes, String orderClause ) { 
   if (logger.isEnabledFor(Level.TRACE)) {     
       logger.trace(">> listVBillSummary()");  
   }   
   if (paginationStartIndex > 0) {    
      return searchPageableVBillSummary(whereClause, whereParams, paramTypes, orderClause, paginationStartIndex, paginationPageSize);  
   }  
   VBillSummaryDTO[] _VBillSummary = null;  
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
                   list.add(extractVBillSummary(rs));        
               } while (rs.next());        
               _VBillSummary =      
                   (VBillSummaryDTO[]) list.toArray(new VBillSummaryDTO[list.size()]);       
           } else {        
               _VBillSummary = new VBillSummaryDTO[0];        
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
           logger.trace("<< findVBillSummary()");     
   }       
   return _VBillSummary;        
 }  

public final VBillSummaryDTO [] searchPageableVBillSummary(String whereClause, Object[] whereParams, int[] paramTypes, String orderClause,int startIndex, int pageSize ) { 
   if (logger.isEnabledFor(Level.TRACE)) {     
       logger.trace(">> searchPageableVBillSummary()");  
   }   
   VBillSummaryDTO[] _VBillSummary = null;  
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
                   list.add(extractVBillSummary(rs));        
               } while ((rs.next()) && (--len > 0));      
               _VBillSummary =      
                   (VBillSummaryDTO[]) list.toArray(new VBillSummaryDTO[list.size()]);       
           } else {        
               _VBillSummary = new VBillSummaryDTO[0];        
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
           logger.trace("<< searchPageableVBillSummary()");     
   }       
   return _VBillSummary;        
 }  

public final int countVBillSummary(String whereClause, Object[] whereParams, int[] paramTypes ) { 
   if (logger.isEnabledFor(Level.TRACE)) {     
       logger.trace(">> countVBillSummary()");  
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
           logger.trace("<< findVBillSummary()");     
   }       
   return count;     
 }  

  public final VBillSummaryDTO [] listVBillSummaryByCreatedBy(String _CreatedBy) { 
     return listVBillSummaryByCreatedBy( _CreatedBy, null);     
 } 

  public final VBillSummaryDTO [] listVBillSummaryByCreatedBy(String _CreatedBy, String orderByColumn) { 
    String whereClause = " WHERE CREATED_BY = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _CreatedBy;   
    types[0] = java.sql.Types.VARCHAR;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listVBillSummary(whereClause, objs, types, order); 
 } 



  public final int countVBillSummaryByCreatedBy(String _CreatedBy) { 
    String whereClause = " WHERE CREATED_BY = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _CreatedBy;   
    types[0] = java.sql.Types.VARCHAR;   
    return countVBillSummary(whereClause, objs, types); 
 } 




  public final VBillSummaryDTO [] listVBillSummaryByCreatedOn(java.util.Date _CreatedOn) { 
     return listVBillSummaryByCreatedOn( _CreatedOn, null);     
 } 

  public final VBillSummaryDTO [] listVBillSummaryByCreatedOn(java.util.Date _CreatedOn, String orderByColumn) { 
    String whereClause = " WHERE CREATED_ON = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.sql.Timestamp(_CreatedOn.getTime());   
    types[0] = java.sql.Types.TIMESTAMP;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listVBillSummary(whereClause, objs, types, order); 
 } 



  public final int countVBillSummaryByCreatedOn(java.util.Date _CreatedOn) { 
    String whereClause = " WHERE CREATED_ON = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.sql.Timestamp(_CreatedOn.getTime());   
    types[0] = java.sql.Types.TIMESTAMP;   
    return countVBillSummary(whereClause, objs, types); 
 } 




  public final VBillSummaryDTO [] listVBillSummaryByCycleType(String _CycleType) { 
     return listVBillSummaryByCycleType( _CycleType, null);     
 } 

  public final VBillSummaryDTO [] listVBillSummaryByCycleType(String _CycleType, String orderByColumn) { 
    String whereClause = " WHERE CYCLE_TYPE = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _CycleType;   
    types[0] = java.sql.Types.VARCHAR;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listVBillSummary(whereClause, objs, types, order); 
 } 



  public final int countVBillSummaryByCycleType(String _CycleType) { 
    String whereClause = " WHERE CYCLE_TYPE = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _CycleType;   
    types[0] = java.sql.Types.VARCHAR;   
    return countVBillSummary(whereClause, objs, types); 
 } 




  public final VBillSummaryDTO [] listVBillSummaryByDay(int _Day) { 
     return listVBillSummaryByDay( _Day, null);     
 } 

  public final VBillSummaryDTO [] listVBillSummaryByDay(int _Day, String orderByColumn) { 
    String whereClause = " WHERE DAY = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Integer(_Day);   
    types[0] = java.sql.Types.INTEGER;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listVBillSummary(whereClause, objs, types, order); 
 } 



  public final int countVBillSummaryByDay(int _Day) { 
    String whereClause = " WHERE DAY = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Integer(_Day);   
    types[0] = java.sql.Types.INTEGER;   
    return countVBillSummary(whereClause, objs, types); 
 } 




  public final VBillSummaryDTO [] listVBillSummaryByDescriptor(String _Descriptor) { 
     return listVBillSummaryByDescriptor( _Descriptor, null);     
 } 

  public final VBillSummaryDTO [] listVBillSummaryByDescriptor(String _Descriptor, String orderByColumn) { 
    String whereClause = " WHERE DESCRIPTOR = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _Descriptor;   
    types[0] = java.sql.Types.VARCHAR;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listVBillSummary(whereClause, objs, types, order); 
 } 



  public final int countVBillSummaryByDescriptor(String _Descriptor) { 
    String whereClause = " WHERE DESCRIPTOR = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _Descriptor;   
    types[0] = java.sql.Types.VARCHAR;   
    return countVBillSummary(whereClause, objs, types); 
 } 




  public final VBillSummaryDTO [] listVBillSummaryByEndDate(java.util.Date _EndDate) { 
     return listVBillSummaryByEndDate( _EndDate, null);     
 } 

  public final VBillSummaryDTO [] listVBillSummaryByEndDate(java.util.Date _EndDate, String orderByColumn) { 
    String whereClause = " WHERE END_DATE = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.sql.Timestamp(_EndDate.getTime());   
    types[0] = java.sql.Types.TIMESTAMP;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listVBillSummary(whereClause, objs, types, order); 
 } 



  public final int countVBillSummaryByEndDate(java.util.Date _EndDate) { 
    String whereClause = " WHERE END_DATE = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.sql.Timestamp(_EndDate.getTime());   
    types[0] = java.sql.Types.TIMESTAMP;   
    return countVBillSummary(whereClause, objs, types); 
 } 




  public final VBillSummaryDTO [] listVBillSummaryById(long _Id) { 
     return listVBillSummaryById( _Id, null);     
 } 

  public final VBillSummaryDTO [] listVBillSummaryById(long _Id, String orderByColumn) { 
    String whereClause = " WHERE ID = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Long(_Id);   
    types[0] = java.sql.Types.BIGINT;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listVBillSummary(whereClause, objs, types, order); 
 } 



  public final int countVBillSummaryById(long _Id) { 
    String whereClause = " WHERE ID = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Long(_Id);   
    types[0] = java.sql.Types.BIGINT;   
    return countVBillSummary(whereClause, objs, types); 
 } 




  public final VBillSummaryDTO [] listVBillSummaryByModifiedBy(String _ModifiedBy) { 
     return listVBillSummaryByModifiedBy( _ModifiedBy, null);     
 } 

  public final VBillSummaryDTO [] listVBillSummaryByModifiedBy(String _ModifiedBy, String orderByColumn) { 
    String whereClause = " WHERE MODIFIED_BY = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _ModifiedBy;   
    types[0] = java.sql.Types.VARCHAR;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listVBillSummary(whereClause, objs, types, order); 
 } 



  public final int countVBillSummaryByModifiedBy(String _ModifiedBy) { 
    String whereClause = " WHERE MODIFIED_BY = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _ModifiedBy;   
    types[0] = java.sql.Types.VARCHAR;   
    return countVBillSummary(whereClause, objs, types); 
 } 




  public final VBillSummaryDTO [] listVBillSummaryByModifiedOn(java.util.Date _ModifiedOn) { 
     return listVBillSummaryByModifiedOn( _ModifiedOn, null);     
 } 

  public final VBillSummaryDTO [] listVBillSummaryByModifiedOn(java.util.Date _ModifiedOn, String orderByColumn) { 
    String whereClause = " WHERE MODIFIED_ON = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.sql.Timestamp(_ModifiedOn.getTime());   
    types[0] = java.sql.Types.TIMESTAMP;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listVBillSummary(whereClause, objs, types, order); 
 } 



  public final int countVBillSummaryByModifiedOn(java.util.Date _ModifiedOn) { 
    String whereClause = " WHERE MODIFIED_ON = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.sql.Timestamp(_ModifiedOn.getTime());   
    types[0] = java.sql.Types.TIMESTAMP;   
    return countVBillSummary(whereClause, objs, types); 
 } 




  public final VBillSummaryDTO [] listVBillSummaryByMonth(int _Month) { 
     return listVBillSummaryByMonth( _Month, null);     
 } 

  public final VBillSummaryDTO [] listVBillSummaryByMonth(int _Month, String orderByColumn) { 
    String whereClause = " WHERE MONTH = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Integer(_Month);   
    types[0] = java.sql.Types.INTEGER;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listVBillSummary(whereClause, objs, types, order); 
 } 



  public final int countVBillSummaryByMonth(int _Month) { 
    String whereClause = " WHERE MONTH = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Integer(_Month);   
    types[0] = java.sql.Types.INTEGER;   
    return countVBillSummary(whereClause, objs, types); 
 } 




  public final VBillSummaryDTO [] listVBillSummaryByStartDate(java.util.Date _StartDate) { 
     return listVBillSummaryByStartDate( _StartDate, null);     
 } 

  public final VBillSummaryDTO [] listVBillSummaryByStartDate(java.util.Date _StartDate, String orderByColumn) { 
    String whereClause = " WHERE START_DATE = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.sql.Timestamp(_StartDate.getTime());   
    types[0] = java.sql.Types.TIMESTAMP;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listVBillSummary(whereClause, objs, types, order); 
 } 



  public final int countVBillSummaryByStartDate(java.util.Date _StartDate) { 
    String whereClause = " WHERE START_DATE = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.sql.Timestamp(_StartDate.getTime());   
    types[0] = java.sql.Types.TIMESTAMP;   
    return countVBillSummary(whereClause, objs, types); 
 } 




  public final VBillSummaryDTO [] listVBillSummaryByStatus(String _Status) { 
     return listVBillSummaryByStatus( _Status, null);     
 } 

  public final VBillSummaryDTO [] listVBillSummaryByStatus(String _Status, String orderByColumn) { 
    String whereClause = " WHERE STATUS = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _Status;   
    types[0] = java.sql.Types.VARCHAR;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listVBillSummary(whereClause, objs, types, order); 
 } 



  public final int countVBillSummaryByStatus(String _Status) { 
    String whereClause = " WHERE STATUS = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _Status;   
    types[0] = java.sql.Types.VARCHAR;   
    return countVBillSummary(whereClause, objs, types); 
 } 




  public final VBillSummaryDTO [] listVBillSummaryByStatusDate(java.util.Date _StatusDate) { 
     return listVBillSummaryByStatusDate( _StatusDate, null);     
 } 

  public final VBillSummaryDTO [] listVBillSummaryByStatusDate(java.util.Date _StatusDate, String orderByColumn) { 
    String whereClause = " WHERE STATUS_DATE = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.sql.Timestamp(_StatusDate.getTime());   
    types[0] = java.sql.Types.TIMESTAMP;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listVBillSummary(whereClause, objs, types, order); 
 } 



  public final int countVBillSummaryByStatusDate(java.util.Date _StatusDate) { 
    String whereClause = " WHERE STATUS_DATE = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.sql.Timestamp(_StatusDate.getTime());   
    types[0] = java.sql.Types.TIMESTAMP;   
    return countVBillSummary(whereClause, objs, types); 
 } 




  public final VBillSummaryDTO [] listVBillSummaryByTotalBilled(BigDecimal _TotalBilled) { 
     return listVBillSummaryByTotalBilled( _TotalBilled, null);     
 } 

  public final VBillSummaryDTO [] listVBillSummaryByTotalBilled(BigDecimal _TotalBilled, String orderByColumn) { 
    String whereClause = " WHERE TOTAL_BILLED = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _TotalBilled;   
    types[0] = java.sql.Types.DECIMAL;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listVBillSummary(whereClause, objs, types, order); 
 } 



  public final int countVBillSummaryByTotalBilled(BigDecimal _TotalBilled) { 
    String whereClause = " WHERE TOTAL_BILLED = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _TotalBilled;   
    types[0] = java.sql.Types.DECIMAL;   
    return countVBillSummary(whereClause, objs, types); 
 } 




  public final VBillSummaryDTO [] listVBillSummaryByTotalPaid(int _TotalPaid) { 
     return listVBillSummaryByTotalPaid( _TotalPaid, null);     
 } 

  public final VBillSummaryDTO [] listVBillSummaryByTotalPaid(int _TotalPaid, String orderByColumn) { 
    String whereClause = " WHERE TOTAL_PAID = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Integer(_TotalPaid);   
    types[0] = java.sql.Types.INTEGER;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listVBillSummary(whereClause, objs, types, order); 
 } 



  public final int countVBillSummaryByTotalPaid(int _TotalPaid) { 
    String whereClause = " WHERE TOTAL_PAID = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Integer(_TotalPaid);   
    types[0] = java.sql.Types.INTEGER;   
    return countVBillSummary(whereClause, objs, types); 
 } 




  public final VBillSummaryDTO [] listVBillSummaryByUsername(String _Username) { 
     return listVBillSummaryByUsername( _Username, null);     
 } 

  public final VBillSummaryDTO [] listVBillSummaryByUsername(String _Username, String orderByColumn) { 
    String whereClause = " WHERE USERNAME = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _Username;   
    types[0] = java.sql.Types.VARCHAR;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listVBillSummary(whereClause, objs, types, order); 
 } 



  public final int countVBillSummaryByUsername(String _Username) { 
    String whereClause = " WHERE USERNAME = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _Username;   
    types[0] = java.sql.Types.VARCHAR;   
    return countVBillSummary(whereClause, objs, types); 
 } 




  public final VBillSummaryDTO [] listVBillSummaryByWeek(int _Week) { 
     return listVBillSummaryByWeek( _Week, null);     
 } 

  public final VBillSummaryDTO [] listVBillSummaryByWeek(int _Week, String orderByColumn) { 
    String whereClause = " WHERE WEEK = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Integer(_Week);   
    types[0] = java.sql.Types.INTEGER;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listVBillSummary(whereClause, objs, types, order); 
 } 



  public final int countVBillSummaryByWeek(int _Week) { 
    String whereClause = " WHERE WEEK = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Integer(_Week);   
    types[0] = java.sql.Types.INTEGER;   
    return countVBillSummary(whereClause, objs, types); 
 } 




  public final VBillSummaryDTO [] listVBillSummaryByYear(int _Year) { 
     return listVBillSummaryByYear( _Year, null);     
 } 

  public final VBillSummaryDTO [] listVBillSummaryByYear(int _Year, String orderByColumn) { 
    String whereClause = " WHERE YEAR = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Integer(_Year);   
    types[0] = java.sql.Types.INTEGER;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listVBillSummary(whereClause, objs, types, order); 
 } 



  public final int countVBillSummaryByYear(int _Year) { 
    String whereClause = " WHERE YEAR = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Integer(_Year);   
    types[0] = java.sql.Types.INTEGER;   
    return countVBillSummary(whereClause, objs, types); 
 } 


  public final VBillSummaryDTO createVBillSummary(VBillSummaryDTO _VBillSummary)
    throws VBillSummaryCreateException {  
         if (logger.isEnabledFor(Level.TRACE)) {
             logger.trace(">> createVBillSummary()");
         }  
             PreparedStatement stmt = null;   
             Connection con = openConnection();
         try { 
                 int i = 1;
                 stmt = con.prepareStatement(getInsertSQL(), Statement.RETURN_GENERATED_KEYS);
                  stmt.setString(i++, _VBillSummary.getCreatedBy()); 
                  if (_VBillSummary.getCreatedOn() != null) { 
                     stmt.setTimestamp(i++, new java.sql.Timestamp(_VBillSummary.getCreatedOn().getTime()) ); 
                  } else {
                     stmt.setNull(i++, java.sql.Types.TIMESTAMP); 
                  } 
                  stmt.setString(i++, _VBillSummary.getCycleType()); 
                  stmt.setInt(i++, _VBillSummary.getDay()); 
                  stmt.setString(i++, _VBillSummary.getDescriptor()); 
                  if (_VBillSummary.getEndDate() != null) { 
                     stmt.setTimestamp(i++, new java.sql.Timestamp(_VBillSummary.getEndDate().getTime()) ); 
                  } else {
                     stmt.setNull(i++, java.sql.Types.TIMESTAMP); 
                  } 
                  stmt.setLong(i++, _VBillSummary.getId()); 
                  stmt.setString(i++, _VBillSummary.getModifiedBy()); 
                  if (_VBillSummary.getModifiedOn() != null) { 
                     stmt.setTimestamp(i++, new java.sql.Timestamp(_VBillSummary.getModifiedOn().getTime()) ); 
                  } else {
                     stmt.setNull(i++, java.sql.Types.TIMESTAMP); 
                  } 
                  stmt.setInt(i++, _VBillSummary.getMonth()); 
                  if (_VBillSummary.getStartDate() != null) { 
                     stmt.setTimestamp(i++, new java.sql.Timestamp(_VBillSummary.getStartDate().getTime()) ); 
                  } else {
                     stmt.setNull(i++, java.sql.Types.TIMESTAMP); 
                  } 
                  stmt.setString(i++, _VBillSummary.getStatus()); 
                  if (_VBillSummary.getStatusDate() != null) { 
                     stmt.setTimestamp(i++, new java.sql.Timestamp(_VBillSummary.getStatusDate().getTime()) ); 
                  } else {
                     stmt.setNull(i++, java.sql.Types.TIMESTAMP); 
                  } 
                  stmt.setBigDecimal(i++, _VBillSummary.getTotalBilled()); 
                  stmt.setInt(i++, _VBillSummary.getTotalPaid()); 
                  stmt.setString(i++, _VBillSummary.getUsername()); 
                  stmt.setInt(i++, _VBillSummary.getWeek()); 
                  stmt.setInt(i++, _VBillSummary.getYear()); 
                 
                 stmt.executeUpdate();     
         } catch (SQLException e) {     
             logSQLException(e);     
             if (isSQLConstraintViolated(e)) {     
                 throw new VBillSummaryCreateException(e.getMessage(), e);     
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
             logger.trace("<< createVBillSummary()");     
         }     
         return _VBillSummary;     
     }       

  public final void updateVBillSummary(VBillSummaryDTO _VBillSummary) 
    throws VBillSummaryUpdateException, VBillSummaryFinderException  {      
    if (logger.isEnabledFor(Level.TRACE)) {         
       logger.trace(">> updateVBillSummary()");         
    }       
    PreparedStatement stmt = null;      
    Connection con = openConnection();      
       try {        
           int i = 1;       
           stmt = con.prepareStatement(getUpdateSQL());         
                  stmt.setString(i++,  _VBillSummary.getCreatedBy() ); 
             if (_VBillSummary.getCreatedOn() != null) { 
                  stmt.setTimestamp(i++,  new java.sql.Timestamp(_VBillSummary.getCreatedOn().getTime()) ); 
             } else { 
                  stmt.setNull(i++, java.sql.Types.TIMESTAMP); 
             }
                  stmt.setString(i++,  _VBillSummary.getCycleType() ); 
                  stmt.setInt(i++,  _VBillSummary.getDay() ); 
                  stmt.setString(i++,  _VBillSummary.getDescriptor() ); 
             if (_VBillSummary.getEndDate() != null) { 
                  stmt.setTimestamp(i++,  new java.sql.Timestamp(_VBillSummary.getEndDate().getTime()) ); 
             } else { 
                  stmt.setNull(i++, java.sql.Types.TIMESTAMP); 
             }
                  stmt.setLong(i++,  _VBillSummary.getId() ); 
                  stmt.setString(i++,  _VBillSummary.getModifiedBy() ); 
             if (_VBillSummary.getModifiedOn() != null) { 
                  stmt.setTimestamp(i++,  new java.sql.Timestamp(_VBillSummary.getModifiedOn().getTime()) ); 
             } else { 
                  stmt.setNull(i++, java.sql.Types.TIMESTAMP); 
             }
                  stmt.setInt(i++,  _VBillSummary.getMonth() ); 
             if (_VBillSummary.getStartDate() != null) { 
                  stmt.setTimestamp(i++,  new java.sql.Timestamp(_VBillSummary.getStartDate().getTime()) ); 
             } else { 
                  stmt.setNull(i++, java.sql.Types.TIMESTAMP); 
             }
                  stmt.setString(i++,  _VBillSummary.getStatus() ); 
             if (_VBillSummary.getStatusDate() != null) { 
                  stmt.setTimestamp(i++,  new java.sql.Timestamp(_VBillSummary.getStatusDate().getTime()) ); 
             } else { 
                  stmt.setNull(i++, java.sql.Types.TIMESTAMP); 
             }
                  stmt.setBigDecimal(i++,  _VBillSummary.getTotalBilled() ); 
                  stmt.setInt(i++,  _VBillSummary.getTotalPaid() ); 
                  stmt.setString(i++,  _VBillSummary.getUsername() ); 
                  stmt.setInt(i++,  _VBillSummary.getWeek() ); 
                  stmt.setInt(i++,  _VBillSummary.getYear() ); 
  /*** now set the criteria, PK values ***/ 
           int count = stmt.executeUpdate();        
           if (count == 0) {        
               throw new VBillSummaryFinderException("");       
           }        
       } catch (SQLException e) {       
           logSQLException(e);      
           if (isSQLConstraintViolated(e)) {        
               throw new VBillSummaryUpdateException("");       
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
           logger.trace("<< updateVBillSummary()");         
       }        
   }        

  public final void deleteVBillSummary(VBillSummaryDTO _VBillSummary) 
    throws VBillSummaryFinderException, VBillSummaryDeleteException  {      
    if (logger.isEnabledFor(Level.TRACE)) {         
       logger.trace(">> deleteVBillSummary()");         
    }       
    PreparedStatement stmt = null;      
    Connection con = openConnection();      
       try {        
           int i = 1;       
           stmt = con.prepareStatement(getDeleteSQL());         
           int count = stmt.executeUpdate();        
           if (count == 0) {        
               throw new VBillSummaryFinderException("");       
           }        
       } catch (SQLException e) {       
           logSQLException(e);      
           if (isSQLConstraintViolated(e)) {        
               throw new VBillSummaryDeleteException("");       
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
           logger.trace("<< deleteVBillSummary()");         
       }        
   }        

  public final void deleteVBillSummaryWhere(String whereClause, Object[] whereParams, int[] paramTypes)   
    throws VBillSummaryFinderException, VBillSummaryDeleteException  {      
   if (logger.isEnabledFor(Level.TRACE)) {     
       logger.trace(">> listVBillSummary()");  
   }   
   VBillSummaryDTO[] _VBillSummary = null;  
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
               throw new VBillSummaryFinderException("");       
           }        
   } catch (SQLException e) {       
           logSQLException(e);      
           if (isSQLConstraintViolated(e)) {        
               throw new VBillSummaryDeleteException(e.getMessage(), e);      
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
       logger.trace("<< deleteVBillSummary()");         
   }        
   }        

  public final void deleteVBillSummaryByCreatedBy(String _CreatedBy) throws VBillSummaryFinderException, VBillSummaryDeleteException{ 
    String whereClause = " WHERE CREATED_BY = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _CreatedBy;   
    types[0] = java.sql.Types.VARCHAR;   
    deleteVBillSummaryWhere(whereClause, objs, types); 
 } 




  public final void deleteVBillSummaryByCreatedOn(java.util.Date _CreatedOn) throws VBillSummaryFinderException, VBillSummaryDeleteException{ 
    String whereClause = " WHERE CREATED_ON = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.sql.Timestamp(_CreatedOn.getTime());   
    types[0] = java.sql.Types.TIMESTAMP;   
    deleteVBillSummaryWhere(whereClause, objs, types); 
 } 




  public final void deleteVBillSummaryByCycleType(String _CycleType) throws VBillSummaryFinderException, VBillSummaryDeleteException{ 
    String whereClause = " WHERE CYCLE_TYPE = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _CycleType;   
    types[0] = java.sql.Types.VARCHAR;   
    deleteVBillSummaryWhere(whereClause, objs, types); 
 } 




  public final void deleteVBillSummaryByDay(int _Day) throws VBillSummaryFinderException, VBillSummaryDeleteException{ 
    String whereClause = " WHERE DAY = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Integer(_Day);   
    types[0] = java.sql.Types.INTEGER;   
    deleteVBillSummaryWhere(whereClause, objs, types); 
 } 




  public final void deleteVBillSummaryByDescriptor(String _Descriptor) throws VBillSummaryFinderException, VBillSummaryDeleteException{ 
    String whereClause = " WHERE DESCRIPTOR = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _Descriptor;   
    types[0] = java.sql.Types.VARCHAR;   
    deleteVBillSummaryWhere(whereClause, objs, types); 
 } 




  public final void deleteVBillSummaryByEndDate(java.util.Date _EndDate) throws VBillSummaryFinderException, VBillSummaryDeleteException{ 
    String whereClause = " WHERE END_DATE = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.sql.Timestamp(_EndDate.getTime());   
    types[0] = java.sql.Types.TIMESTAMP;   
    deleteVBillSummaryWhere(whereClause, objs, types); 
 } 




  public final void deleteVBillSummaryById(long _Id) throws VBillSummaryFinderException, VBillSummaryDeleteException{ 
    String whereClause = " WHERE ID = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Long(_Id);   
    types[0] = java.sql.Types.BIGINT;   
    deleteVBillSummaryWhere(whereClause, objs, types); 
 } 




  public final void deleteVBillSummaryByModifiedBy(String _ModifiedBy) throws VBillSummaryFinderException, VBillSummaryDeleteException{ 
    String whereClause = " WHERE MODIFIED_BY = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _ModifiedBy;   
    types[0] = java.sql.Types.VARCHAR;   
    deleteVBillSummaryWhere(whereClause, objs, types); 
 } 




  public final void deleteVBillSummaryByModifiedOn(java.util.Date _ModifiedOn) throws VBillSummaryFinderException, VBillSummaryDeleteException{ 
    String whereClause = " WHERE MODIFIED_ON = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.sql.Timestamp(_ModifiedOn.getTime());   
    types[0] = java.sql.Types.TIMESTAMP;   
    deleteVBillSummaryWhere(whereClause, objs, types); 
 } 




  public final void deleteVBillSummaryByMonth(int _Month) throws VBillSummaryFinderException, VBillSummaryDeleteException{ 
    String whereClause = " WHERE MONTH = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Integer(_Month);   
    types[0] = java.sql.Types.INTEGER;   
    deleteVBillSummaryWhere(whereClause, objs, types); 
 } 




  public final void deleteVBillSummaryByStartDate(java.util.Date _StartDate) throws VBillSummaryFinderException, VBillSummaryDeleteException{ 
    String whereClause = " WHERE START_DATE = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.sql.Timestamp(_StartDate.getTime());   
    types[0] = java.sql.Types.TIMESTAMP;   
    deleteVBillSummaryWhere(whereClause, objs, types); 
 } 




  public final void deleteVBillSummaryByStatus(String _Status) throws VBillSummaryFinderException, VBillSummaryDeleteException{ 
    String whereClause = " WHERE STATUS = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _Status;   
    types[0] = java.sql.Types.VARCHAR;   
    deleteVBillSummaryWhere(whereClause, objs, types); 
 } 




  public final void deleteVBillSummaryByStatusDate(java.util.Date _StatusDate) throws VBillSummaryFinderException, VBillSummaryDeleteException{ 
    String whereClause = " WHERE STATUS_DATE = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.sql.Timestamp(_StatusDate.getTime());   
    types[0] = java.sql.Types.TIMESTAMP;   
    deleteVBillSummaryWhere(whereClause, objs, types); 
 } 




  public final void deleteVBillSummaryByTotalBilled(BigDecimal _TotalBilled) throws VBillSummaryFinderException, VBillSummaryDeleteException{ 
    String whereClause = " WHERE TOTAL_BILLED = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _TotalBilled;   
    types[0] = java.sql.Types.DECIMAL;   
    deleteVBillSummaryWhere(whereClause, objs, types); 
 } 




  public final void deleteVBillSummaryByTotalPaid(int _TotalPaid) throws VBillSummaryFinderException, VBillSummaryDeleteException{ 
    String whereClause = " WHERE TOTAL_PAID = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Integer(_TotalPaid);   
    types[0] = java.sql.Types.INTEGER;   
    deleteVBillSummaryWhere(whereClause, objs, types); 
 } 




  public final void deleteVBillSummaryByUsername(String _Username) throws VBillSummaryFinderException, VBillSummaryDeleteException{ 
    String whereClause = " WHERE USERNAME = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _Username;   
    types[0] = java.sql.Types.VARCHAR;   
    deleteVBillSummaryWhere(whereClause, objs, types); 
 } 




  public final void deleteVBillSummaryByWeek(int _Week) throws VBillSummaryFinderException, VBillSummaryDeleteException{ 
    String whereClause = " WHERE WEEK = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Integer(_Week);   
    types[0] = java.sql.Types.INTEGER;   
    deleteVBillSummaryWhere(whereClause, objs, types); 
 } 




  public final void deleteVBillSummaryByYear(int _Year) throws VBillSummaryFinderException, VBillSummaryDeleteException{ 
    String whereClause = " WHERE YEAR = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Integer(_Year);   
    types[0] = java.sql.Types.INTEGER;   
    deleteVBillSummaryWhere(whereClause, objs, types); 
 } 




  



public final VBillSummaryDTO extractVBillSummary(ResultSet rs)       
    throws SQLException {  
         VBillSummaryDTO obj = new VBillSummaryDTO();          
         int i = 1;      
         obj.setCreatedBy( rs.getString(i++) );       
         obj.setCreatedOn( rs.getTimestamp(i++) );       
         obj.setCycleType( rs.getString(i++) );       
         obj.setDay( rs.getInt(i++) );       
         obj.setDescriptor( rs.getString(i++) );       
         obj.setEndDate( rs.getTimestamp(i++) );       
         obj.setId( rs.getLong(i++) );       
         obj.setModifiedBy( rs.getString(i++) );       
         obj.setModifiedOn( rs.getTimestamp(i++) );       
         obj.setMonth( rs.getInt(i++) );       
         obj.setStartDate( rs.getTimestamp(i++) );       
         obj.setStatus( rs.getString(i++) );       
         obj.setStatusDate( rs.getTimestamp(i++) );       
         obj.setTotalBilled( rs.getBigDecimal(i++) );       
         obj.setTotalPaid( rs.getInt(i++) );       
         obj.setUsername( rs.getString(i++) );       
         obj.setWeek( rs.getInt(i++) );       
         obj.setYear( rs.getInt(i++) );       
             
 return obj;  } 


 /*************** end *****************/
}
