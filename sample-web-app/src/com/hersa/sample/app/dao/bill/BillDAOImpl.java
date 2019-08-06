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
class BillDAOImpl extends AbstractDAImpl implements BillDAO {

  private static final Logger logger = 
      Logger.getLogger(BillDAOImpl.class);

  protected String tableName = BillPrefix.PREFIX + "BILL"; 
  public String getTable(){
      return "\"" + this.tableName + "\""; 
}
  public void setTable(String table){
      this.tableName = table; 
}
  protected String getSelectSQL(){
      String sql = " SELECT ACTIVE, CREATED_BY, CREATED_ON, CYCLE_TYPE, DAY, DESCRIPTOR, END_DATE, ID, MODIFIED_BY, MODIFIED_ON, MONTH, START_DATE, STATUS, STATUS_DATE, USERNAME, WEEK, YEAR FROM " + getTable() + " " ;
      return sql;
}
  protected String getSelectCountSQL(){
      String sql = " SELECT COUNT(*) FROM " + getTable();
      return sql;
}
  protected String getInsertSQL(){
      String sql = " INSERT INTO " + getTable() + " ( ACTIVE, CREATED_BY, CREATED_ON, CYCLE_TYPE, DAY, DESCRIPTOR, END_DATE, MODIFIED_BY, MODIFIED_ON, MONTH, START_DATE, STATUS, STATUS_DATE, USERNAME, WEEK, YEAR ) VALUES  ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";
      return sql;
}
  protected String getUpdateSQL(){
      String sql = " UPDATE " + getTable() + " SET  ACTIVE = ? , CREATED_BY = ? , CREATED_ON = ? , CYCLE_TYPE = ? , DAY = ? , DESCRIPTOR = ? , END_DATE = ? , MODIFIED_BY = ? , MODIFIED_ON = ? , MONTH = ? , START_DATE = ? , STATUS = ? , STATUS_DATE = ? , USERNAME = ? , WEEK = ? , YEAR = ?  WHERE ID = ? ";
      return sql;
}
  protected String getUpdateBlobsSQL(){
      String sql = " UPDATE  " + getTable() + "  SET  ACTIVE = ? , CREATED_BY = ? , CREATED_ON = ? , CYCLE_TYPE = ? , DAY = ? , DESCRIPTOR = ? , END_DATE = ? , MODIFIED_BY = ? , MODIFIED_ON = ? , MONTH = ? , START_DATE = ? , STATUS = ? , STATUS_DATE = ? , USERNAME = ? , WEEK = ? , YEAR = ?  WHERE ID = ? ";
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

  public BillDAOImpl() { 
       factory = new DefaultConnectionProvider( BillJNDI.NON_XA_DATASOURCE ); 
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

  public final BillDTO [] listAllBill(String orderByColumn) { 
    String order = "";    
     if (orderByColumn != null && orderByColumn.trim().length() > 0) {    
        order = " ORDER BY " + orderByColumn;     
     }    
    return listBill(null, null, null, order); 
 } 

public final BillDTO [] listAllBill() { 
    return listBill(null, null, null, null); 
 } 

public final BillDTO [] customBill(String completeSQL ) { 
   if (logger.isEnabledFor(Level.TRACE)) {     
       logger.trace(">> listBill()");  
   }   
   BillDTO[] _Bill = null;  
   ResultSet rs = null;    
   PreparedStatement stmt = null;  
   Connection con = openConnection();  
   try {   
           stmt = con.prepareStatement(completeSQL);    
           stmt.setMaxRows(getMaxRows());            rs = stmt.executeQuery();       
           if (rs.next()) {        
               List list = new ArrayList();        
               do {        
                   list.add(extractBill(rs));        
               } while (rs.next());        
               _Bill =      
                   (BillDTO[]) list.toArray(new BillDTO[list.size()]);       
           } else {        
               _Bill = new BillDTO[0];        
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
           logger.trace("<< findBill()");     
   }       
   return _Bill;        
 }  

public final BillDTO [] listBill(String whereClause, Object[] whereParams, int[] paramTypes, String orderClause ) { 
   if (logger.isEnabledFor(Level.TRACE)) {     
       logger.trace(">> listBill()");  
   }   
   if (paginationStartIndex > 0) {    
      return searchPageableBill(whereClause, whereParams, paramTypes, orderClause, paginationStartIndex, paginationPageSize);  
   }  
   BillDTO[] _Bill = null;  
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
                   list.add(extractBill(rs));        
               } while (rs.next());        
               _Bill =      
                   (BillDTO[]) list.toArray(new BillDTO[list.size()]);       
           } else {        
               _Bill = new BillDTO[0];        
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
           logger.trace("<< findBill()");     
   }       
   return _Bill;        
 }  

public final BillDTO [] searchPageableBill(String whereClause, Object[] whereParams, int[] paramTypes, String orderClause,int startIndex, int pageSize ) { 
   if (logger.isEnabledFor(Level.TRACE)) {     
       logger.trace(">> searchPageableBill()");  
   }   
   BillDTO[] _Bill = null;  
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
                   list.add(extractBill(rs));        
               } while ((rs.next()) && (--len > 0));      
               _Bill =      
                   (BillDTO[]) list.toArray(new BillDTO[list.size()]);       
           } else {        
               _Bill = new BillDTO[0];        
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
           logger.trace("<< searchPageableBill()");     
   }       
   return _Bill;        
 }  

public final int countBill(String whereClause, Object[] whereParams, int[] paramTypes ) { 
   if (logger.isEnabledFor(Level.TRACE)) {     
       logger.trace(">> countBill()");  
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
           logger.trace("<< findBill()");     
   }       
   return count;     
 }  

  public final BillDTO [] listBillByPK(long _Id) { 
     return listBillByPK( _Id, null);     
 } 

  public final BillDTO [] listBillByPK(long _Id, String orderByColumn) { 
    String whereClause = " WHERE ID = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Long(_Id);   
    types[0] = java.sql.Types.BIGINT;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listBill(whereClause, objs, types, order); 
 } 



  public final int countBillByPK(long _Id) { 
    String whereClause = " WHERE ID = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Long(_Id);   
    types[0] = java.sql.Types.BIGINT;   
    return countBill(whereClause, objs, types); 
 } 




  public final BillDTO [] listBillByActive(int _Active) { 
     return listBillByActive( _Active, null);     
 } 

  public final BillDTO [] listBillByActive(int _Active, String orderByColumn) { 
    String whereClause = " WHERE ACTIVE = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Integer(_Active);   
    types[0] = java.sql.Types.INTEGER;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listBill(whereClause, objs, types, order); 
 } 



  public final int countBillByActive(int _Active) { 
    String whereClause = " WHERE ACTIVE = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Integer(_Active);   
    types[0] = java.sql.Types.INTEGER;   
    return countBill(whereClause, objs, types); 
 } 




  public final BillDTO [] listBillByCreatedBy(String _CreatedBy) { 
     return listBillByCreatedBy( _CreatedBy, null);     
 } 

  public final BillDTO [] listBillByCreatedBy(String _CreatedBy, String orderByColumn) { 
    String whereClause = " WHERE CREATED_BY = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _CreatedBy;   
    types[0] = java.sql.Types.VARCHAR;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listBill(whereClause, objs, types, order); 
 } 



  public final int countBillByCreatedBy(String _CreatedBy) { 
    String whereClause = " WHERE CREATED_BY = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _CreatedBy;   
    types[0] = java.sql.Types.VARCHAR;   
    return countBill(whereClause, objs, types); 
 } 




  public final BillDTO [] listBillByCreatedOn(java.util.Date _CreatedOn) { 
     return listBillByCreatedOn( _CreatedOn, null);     
 } 

  public final BillDTO [] listBillByCreatedOn(java.util.Date _CreatedOn, String orderByColumn) { 
    String whereClause = " WHERE CREATED_ON = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.sql.Timestamp(_CreatedOn.getTime());   
    types[0] = java.sql.Types.TIMESTAMP;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listBill(whereClause, objs, types, order); 
 } 



  public final int countBillByCreatedOn(java.util.Date _CreatedOn) { 
    String whereClause = " WHERE CREATED_ON = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.sql.Timestamp(_CreatedOn.getTime());   
    types[0] = java.sql.Types.TIMESTAMP;   
    return countBill(whereClause, objs, types); 
 } 




  public final BillDTO [] listBillByCycleType(String _CycleType) { 
     return listBillByCycleType( _CycleType, null);     
 } 

  public final BillDTO [] listBillByCycleType(String _CycleType, String orderByColumn) { 
    String whereClause = " WHERE CYCLE_TYPE = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _CycleType;   
    types[0] = java.sql.Types.VARCHAR;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listBill(whereClause, objs, types, order); 
 } 



  public final int countBillByCycleType(String _CycleType) { 
    String whereClause = " WHERE CYCLE_TYPE = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _CycleType;   
    types[0] = java.sql.Types.VARCHAR;   
    return countBill(whereClause, objs, types); 
 } 




  public final BillDTO [] listBillByDay(int _Day) { 
     return listBillByDay( _Day, null);     
 } 

  public final BillDTO [] listBillByDay(int _Day, String orderByColumn) { 
    String whereClause = " WHERE DAY = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Integer(_Day);   
    types[0] = java.sql.Types.INTEGER;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listBill(whereClause, objs, types, order); 
 } 



  public final int countBillByDay(int _Day) { 
    String whereClause = " WHERE DAY = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Integer(_Day);   
    types[0] = java.sql.Types.INTEGER;   
    return countBill(whereClause, objs, types); 
 } 




  public final BillDTO [] listBillByDescriptor(String _Descriptor) { 
     return listBillByDescriptor( _Descriptor, null);     
 } 

  public final BillDTO [] listBillByDescriptor(String _Descriptor, String orderByColumn) { 
    String whereClause = " WHERE DESCRIPTOR = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _Descriptor;   
    types[0] = java.sql.Types.VARCHAR;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listBill(whereClause, objs, types, order); 
 } 



  public final int countBillByDescriptor(String _Descriptor) { 
    String whereClause = " WHERE DESCRIPTOR = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _Descriptor;   
    types[0] = java.sql.Types.VARCHAR;   
    return countBill(whereClause, objs, types); 
 } 




  public final BillDTO [] listBillByEndDate(java.util.Date _EndDate) { 
     return listBillByEndDate( _EndDate, null);     
 } 

  public final BillDTO [] listBillByEndDate(java.util.Date _EndDate, String orderByColumn) { 
    String whereClause = " WHERE END_DATE = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.sql.Timestamp(_EndDate.getTime());   
    types[0] = java.sql.Types.TIMESTAMP;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listBill(whereClause, objs, types, order); 
 } 



  public final int countBillByEndDate(java.util.Date _EndDate) { 
    String whereClause = " WHERE END_DATE = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.sql.Timestamp(_EndDate.getTime());   
    types[0] = java.sql.Types.TIMESTAMP;   
    return countBill(whereClause, objs, types); 
 } 




  public final BillDTO [] listBillById(long _Id) { 
     return listBillById( _Id, null);     
 } 

  public final BillDTO [] listBillById(long _Id, String orderByColumn) { 
    String whereClause = " WHERE ID = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Long(_Id);   
    types[0] = java.sql.Types.BIGINT;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listBill(whereClause, objs, types, order); 
 } 



  public final int countBillById(long _Id) { 
    String whereClause = " WHERE ID = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Long(_Id);   
    types[0] = java.sql.Types.BIGINT;   
    return countBill(whereClause, objs, types); 
 } 




  public final BillDTO [] listBillByModifiedBy(String _ModifiedBy) { 
     return listBillByModifiedBy( _ModifiedBy, null);     
 } 

  public final BillDTO [] listBillByModifiedBy(String _ModifiedBy, String orderByColumn) { 
    String whereClause = " WHERE MODIFIED_BY = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _ModifiedBy;   
    types[0] = java.sql.Types.VARCHAR;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listBill(whereClause, objs, types, order); 
 } 



  public final int countBillByModifiedBy(String _ModifiedBy) { 
    String whereClause = " WHERE MODIFIED_BY = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _ModifiedBy;   
    types[0] = java.sql.Types.VARCHAR;   
    return countBill(whereClause, objs, types); 
 } 




  public final BillDTO [] listBillByModifiedOn(java.util.Date _ModifiedOn) { 
     return listBillByModifiedOn( _ModifiedOn, null);     
 } 

  public final BillDTO [] listBillByModifiedOn(java.util.Date _ModifiedOn, String orderByColumn) { 
    String whereClause = " WHERE MODIFIED_ON = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.sql.Timestamp(_ModifiedOn.getTime());   
    types[0] = java.sql.Types.TIMESTAMP;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listBill(whereClause, objs, types, order); 
 } 



  public final int countBillByModifiedOn(java.util.Date _ModifiedOn) { 
    String whereClause = " WHERE MODIFIED_ON = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.sql.Timestamp(_ModifiedOn.getTime());   
    types[0] = java.sql.Types.TIMESTAMP;   
    return countBill(whereClause, objs, types); 
 } 




  public final BillDTO [] listBillByMonth(int _Month) { 
     return listBillByMonth( _Month, null);     
 } 

  public final BillDTO [] listBillByMonth(int _Month, String orderByColumn) { 
    String whereClause = " WHERE MONTH = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Integer(_Month);   
    types[0] = java.sql.Types.INTEGER;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listBill(whereClause, objs, types, order); 
 } 



  public final int countBillByMonth(int _Month) { 
    String whereClause = " WHERE MONTH = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Integer(_Month);   
    types[0] = java.sql.Types.INTEGER;   
    return countBill(whereClause, objs, types); 
 } 




  public final BillDTO [] listBillByStartDate(java.util.Date _StartDate) { 
     return listBillByStartDate( _StartDate, null);     
 } 

  public final BillDTO [] listBillByStartDate(java.util.Date _StartDate, String orderByColumn) { 
    String whereClause = " WHERE START_DATE = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.sql.Timestamp(_StartDate.getTime());   
    types[0] = java.sql.Types.TIMESTAMP;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listBill(whereClause, objs, types, order); 
 } 



  public final int countBillByStartDate(java.util.Date _StartDate) { 
    String whereClause = " WHERE START_DATE = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.sql.Timestamp(_StartDate.getTime());   
    types[0] = java.sql.Types.TIMESTAMP;   
    return countBill(whereClause, objs, types); 
 } 




  public final BillDTO [] listBillByStatus(String _Status) { 
     return listBillByStatus( _Status, null);     
 } 

  public final BillDTO [] listBillByStatus(String _Status, String orderByColumn) { 
    String whereClause = " WHERE STATUS = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _Status;   
    types[0] = java.sql.Types.VARCHAR;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listBill(whereClause, objs, types, order); 
 } 



  public final int countBillByStatus(String _Status) { 
    String whereClause = " WHERE STATUS = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _Status;   
    types[0] = java.sql.Types.VARCHAR;   
    return countBill(whereClause, objs, types); 
 } 




  public final BillDTO [] listBillByStatusDate(java.util.Date _StatusDate) { 
     return listBillByStatusDate( _StatusDate, null);     
 } 

  public final BillDTO [] listBillByStatusDate(java.util.Date _StatusDate, String orderByColumn) { 
    String whereClause = " WHERE STATUS_DATE = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.sql.Timestamp(_StatusDate.getTime());   
    types[0] = java.sql.Types.TIMESTAMP;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listBill(whereClause, objs, types, order); 
 } 



  public final int countBillByStatusDate(java.util.Date _StatusDate) { 
    String whereClause = " WHERE STATUS_DATE = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.sql.Timestamp(_StatusDate.getTime());   
    types[0] = java.sql.Types.TIMESTAMP;   
    return countBill(whereClause, objs, types); 
 } 




  public final BillDTO [] listBillByUsername(String _Username) { 
     return listBillByUsername( _Username, null);     
 } 

  public final BillDTO [] listBillByUsername(String _Username, String orderByColumn) { 
    String whereClause = " WHERE USERNAME = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _Username;   
    types[0] = java.sql.Types.VARCHAR;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listBill(whereClause, objs, types, order); 
 } 



  public final int countBillByUsername(String _Username) { 
    String whereClause = " WHERE USERNAME = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _Username;   
    types[0] = java.sql.Types.VARCHAR;   
    return countBill(whereClause, objs, types); 
 } 




  public final BillDTO [] listBillByWeek(int _Week) { 
     return listBillByWeek( _Week, null);     
 } 

  public final BillDTO [] listBillByWeek(int _Week, String orderByColumn) { 
    String whereClause = " WHERE WEEK = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Integer(_Week);   
    types[0] = java.sql.Types.INTEGER;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listBill(whereClause, objs, types, order); 
 } 



  public final int countBillByWeek(int _Week) { 
    String whereClause = " WHERE WEEK = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Integer(_Week);   
    types[0] = java.sql.Types.INTEGER;   
    return countBill(whereClause, objs, types); 
 } 




  public final BillDTO [] listBillByYear(int _Year) { 
     return listBillByYear( _Year, null);     
 } 

  public final BillDTO [] listBillByYear(int _Year, String orderByColumn) { 
    String whereClause = " WHERE YEAR = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Integer(_Year);   
    types[0] = java.sql.Types.INTEGER;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listBill(whereClause, objs, types, order); 
 } 



  public final int countBillByYear(int _Year) { 
    String whereClause = " WHERE YEAR = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Integer(_Year);   
    types[0] = java.sql.Types.INTEGER;   
    return countBill(whereClause, objs, types); 
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
                throw new BillFinderException("");  
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
  
  public final BillDTO createBill(BillDTO _Bill)
    throws BillCreateException {  
         if (logger.isEnabledFor(Level.TRACE)) {
             logger.trace(">> createBill()");
         }  
             PreparedStatement stmt = null;   
             Connection con = openConnection();
         try { 
                 int i = 1;
                 stmt = con.prepareStatement(getInsertSQL(), Statement.RETURN_GENERATED_KEYS);
                  stmt.setInt(i++, _Bill.getActive()); 
                  stmt.setString(i++, _Bill.getCreatedBy()); 
                  if (_Bill.getCreatedOn() != null) { 
                     stmt.setTimestamp(i++, new java.sql.Timestamp(_Bill.getCreatedOn().getTime()) ); 
                  } else {
                     stmt.setNull(i++, java.sql.Types.TIMESTAMP); 
                  } 
                  stmt.setString(i++, _Bill.getCycleType()); 
                  stmt.setInt(i++, _Bill.getDay()); 
                  stmt.setString(i++, _Bill.getDescriptor()); 
                  if (_Bill.getEndDate() != null) { 
                     stmt.setTimestamp(i++, new java.sql.Timestamp(_Bill.getEndDate().getTime()) ); 
                  } else {
                     stmt.setNull(i++, java.sql.Types.TIMESTAMP); 
                  } 
                  stmt.setString(i++, _Bill.getModifiedBy()); 
                  if (_Bill.getModifiedOn() != null) { 
                     stmt.setTimestamp(i++, new java.sql.Timestamp(_Bill.getModifiedOn().getTime()) ); 
                  } else {
                     stmt.setNull(i++, java.sql.Types.TIMESTAMP); 
                  } 
                  stmt.setInt(i++, _Bill.getMonth()); 
                  if (_Bill.getStartDate() != null) { 
                     stmt.setTimestamp(i++, new java.sql.Timestamp(_Bill.getStartDate().getTime()) ); 
                  } else {
                     stmt.setNull(i++, java.sql.Types.TIMESTAMP); 
                  } 
                  stmt.setString(i++, _Bill.getStatus()); 
                  if (_Bill.getStatusDate() != null) { 
                     stmt.setTimestamp(i++, new java.sql.Timestamp(_Bill.getStatusDate().getTime()) ); 
                  } else {
                     stmt.setNull(i++, java.sql.Types.TIMESTAMP); 
                  } 
                  stmt.setString(i++, _Bill.getUsername()); 
                  stmt.setInt(i++, _Bill.getWeek()); 
                  stmt.setInt(i++, _Bill.getYear()); 
                 stmt.executeUpdate();     
                  int generatedId = -1;      
                  ResultSet rs = stmt.getGeneratedKeys();      
                  while (rs.next()) {      
                     generatedId = (int) rs.getLong(1);      
                  }       
                  rs.close();      
                  _Bill.setId(generatedId);     
         } catch (SQLException e) {     
             logSQLException(e);     
             if (isSQLConstraintViolated(e)) {     
                 throw new BillCreateException(e.getMessage(), e);     
             }     
             throw new BillCreateException(e.getMessage(), e);    
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
             logger.trace("<< createBill()");     
         }     
         return _Bill;     
     }       

  public final void updateBill(BillDTO _Bill) 
    throws BillUpdateException, BillFinderException  {      
    if (logger.isEnabledFor(Level.TRACE)) {         
       logger.trace(">> updateBill()");         
    }       
    PreparedStatement stmt = null;      
    Connection con = openConnection();      
       try {        
           int i = 1;       
           stmt = con.prepareStatement(getUpdateSQL());         
                  stmt.setInt(i++,  _Bill.getActive() ); 
                  stmt.setString(i++,  _Bill.getCreatedBy() ); 
             if (_Bill.getCreatedOn() != null) { 
                  stmt.setTimestamp(i++,  new java.sql.Timestamp(_Bill.getCreatedOn().getTime()) ); 
             } else { 
                  stmt.setNull(i++, java.sql.Types.TIMESTAMP); 
             }
                  stmt.setString(i++,  _Bill.getCycleType() ); 
                  stmt.setInt(i++,  _Bill.getDay() ); 
                  stmt.setString(i++,  _Bill.getDescriptor() ); 
             if (_Bill.getEndDate() != null) { 
                  stmt.setTimestamp(i++,  new java.sql.Timestamp(_Bill.getEndDate().getTime()) ); 
             } else { 
                  stmt.setNull(i++, java.sql.Types.TIMESTAMP); 
             }
                  stmt.setString(i++,  _Bill.getModifiedBy() ); 
             if (_Bill.getModifiedOn() != null) { 
                  stmt.setTimestamp(i++,  new java.sql.Timestamp(_Bill.getModifiedOn().getTime()) ); 
             } else { 
                  stmt.setNull(i++, java.sql.Types.TIMESTAMP); 
             }
                  stmt.setInt(i++,  _Bill.getMonth() ); 
             if (_Bill.getStartDate() != null) { 
                  stmt.setTimestamp(i++,  new java.sql.Timestamp(_Bill.getStartDate().getTime()) ); 
             } else { 
                  stmt.setNull(i++, java.sql.Types.TIMESTAMP); 
             }
                  stmt.setString(i++,  _Bill.getStatus() ); 
             if (_Bill.getStatusDate() != null) { 
                  stmt.setTimestamp(i++,  new java.sql.Timestamp(_Bill.getStatusDate().getTime()) ); 
             } else { 
                  stmt.setNull(i++, java.sql.Types.TIMESTAMP); 
             }
                  stmt.setString(i++,  _Bill.getUsername() ); 
                  stmt.setInt(i++,  _Bill.getWeek() ); 
                  stmt.setInt(i++,  _Bill.getYear() ); 
  /*** now set the criteria, PK values ***/ 
             stmt.setLong(i++,  _Bill.getId() ); 
           int count = stmt.executeUpdate();        
           if (count == 0) {        
               throw new BillFinderException("");       
           }        
       } catch (SQLException e) {       
           logSQLException(e);      
           if (isSQLConstraintViolated(e)) {        
               throw new BillUpdateException("");       
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
           logger.trace("<< updateBill()");         
       }        
   }        

  public final void deleteBill(BillDTO _Bill) 
    throws BillFinderException, BillDeleteException  {      
    if (logger.isEnabledFor(Level.TRACE)) {         
       logger.trace(">> deleteBill()");         
    }       
    PreparedStatement stmt = null;      
    Connection con = openConnection();      
       try {        
           int i = 1;       
           stmt = con.prepareStatement(getDeleteSQL());         
             stmt.setLong(i++,  _Bill.getId() ); 
           int count = stmt.executeUpdate();        
           if (count == 0) {        
               throw new BillFinderException("");       
           }        
       } catch (SQLException e) {       
           logSQLException(e);      
           if (isSQLConstraintViolated(e)) {        
               throw new BillDeleteException("");       
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
           logger.trace("<< deleteBill()");         
       }        
   }        

  public final void deleteBillWhere(String whereClause, Object[] whereParams, int[] paramTypes)   
    throws BillFinderException, BillDeleteException  {      
   if (logger.isEnabledFor(Level.TRACE)) {     
       logger.trace(">> listBill()");  
   }   
   BillDTO[] _Bill = null;  
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
               throw new BillFinderException("");       
           }        
   } catch (SQLException e) {       
           logSQLException(e);      
           if (isSQLConstraintViolated(e)) {        
               throw new BillDeleteException(e.getMessage(), e);      
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
       logger.trace("<< deleteBill()");         
   }        
   }        

  public final void deleteBillByActive(int _Active) throws BillFinderException, BillDeleteException{ 
    String whereClause = " WHERE ACTIVE = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Integer(_Active);   
    types[0] = java.sql.Types.INTEGER;   
    deleteBillWhere(whereClause, objs, types); 
 } 




  public final void deleteBillByCreatedBy(String _CreatedBy) throws BillFinderException, BillDeleteException{ 
    String whereClause = " WHERE CREATED_BY = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _CreatedBy;   
    types[0] = java.sql.Types.VARCHAR;   
    deleteBillWhere(whereClause, objs, types); 
 } 




  public final void deleteBillByCreatedOn(java.util.Date _CreatedOn) throws BillFinderException, BillDeleteException{ 
    String whereClause = " WHERE CREATED_ON = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.sql.Timestamp(_CreatedOn.getTime());   
    types[0] = java.sql.Types.TIMESTAMP;   
    deleteBillWhere(whereClause, objs, types); 
 } 




  public final void deleteBillByCycleType(String _CycleType) throws BillFinderException, BillDeleteException{ 
    String whereClause = " WHERE CYCLE_TYPE = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _CycleType;   
    types[0] = java.sql.Types.VARCHAR;   
    deleteBillWhere(whereClause, objs, types); 
 } 




  public final void deleteBillByDay(int _Day) throws BillFinderException, BillDeleteException{ 
    String whereClause = " WHERE DAY = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Integer(_Day);   
    types[0] = java.sql.Types.INTEGER;   
    deleteBillWhere(whereClause, objs, types); 
 } 




  public final void deleteBillByDescriptor(String _Descriptor) throws BillFinderException, BillDeleteException{ 
    String whereClause = " WHERE DESCRIPTOR = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _Descriptor;   
    types[0] = java.sql.Types.VARCHAR;   
    deleteBillWhere(whereClause, objs, types); 
 } 




  public final void deleteBillByEndDate(java.util.Date _EndDate) throws BillFinderException, BillDeleteException{ 
    String whereClause = " WHERE END_DATE = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.sql.Timestamp(_EndDate.getTime());   
    types[0] = java.sql.Types.TIMESTAMP;   
    deleteBillWhere(whereClause, objs, types); 
 } 




  public final void deleteBillById(long _Id) throws BillFinderException, BillDeleteException{ 
    String whereClause = " WHERE ID = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Long(_Id);   
    types[0] = java.sql.Types.BIGINT;   
    deleteBillWhere(whereClause, objs, types); 
 } 




  public final void deleteBillByModifiedBy(String _ModifiedBy) throws BillFinderException, BillDeleteException{ 
    String whereClause = " WHERE MODIFIED_BY = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _ModifiedBy;   
    types[0] = java.sql.Types.VARCHAR;   
    deleteBillWhere(whereClause, objs, types); 
 } 




  public final void deleteBillByModifiedOn(java.util.Date _ModifiedOn) throws BillFinderException, BillDeleteException{ 
    String whereClause = " WHERE MODIFIED_ON = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.sql.Timestamp(_ModifiedOn.getTime());   
    types[0] = java.sql.Types.TIMESTAMP;   
    deleteBillWhere(whereClause, objs, types); 
 } 




  public final void deleteBillByMonth(int _Month) throws BillFinderException, BillDeleteException{ 
    String whereClause = " WHERE MONTH = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Integer(_Month);   
    types[0] = java.sql.Types.INTEGER;   
    deleteBillWhere(whereClause, objs, types); 
 } 




  public final void deleteBillByStartDate(java.util.Date _StartDate) throws BillFinderException, BillDeleteException{ 
    String whereClause = " WHERE START_DATE = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.sql.Timestamp(_StartDate.getTime());   
    types[0] = java.sql.Types.TIMESTAMP;   
    deleteBillWhere(whereClause, objs, types); 
 } 




  public final void deleteBillByStatus(String _Status) throws BillFinderException, BillDeleteException{ 
    String whereClause = " WHERE STATUS = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _Status;   
    types[0] = java.sql.Types.VARCHAR;   
    deleteBillWhere(whereClause, objs, types); 
 } 




  public final void deleteBillByStatusDate(java.util.Date _StatusDate) throws BillFinderException, BillDeleteException{ 
    String whereClause = " WHERE STATUS_DATE = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.sql.Timestamp(_StatusDate.getTime());   
    types[0] = java.sql.Types.TIMESTAMP;   
    deleteBillWhere(whereClause, objs, types); 
 } 




  public final void deleteBillByUsername(String _Username) throws BillFinderException, BillDeleteException{ 
    String whereClause = " WHERE USERNAME = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _Username;   
    types[0] = java.sql.Types.VARCHAR;   
    deleteBillWhere(whereClause, objs, types); 
 } 




  public final void deleteBillByWeek(int _Week) throws BillFinderException, BillDeleteException{ 
    String whereClause = " WHERE WEEK = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Integer(_Week);   
    types[0] = java.sql.Types.INTEGER;   
    deleteBillWhere(whereClause, objs, types); 
 } 




  public final void deleteBillByYear(int _Year) throws BillFinderException, BillDeleteException{ 
    String whereClause = " WHERE YEAR = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Integer(_Year);   
    types[0] = java.sql.Types.INTEGER;   
    deleteBillWhere(whereClause, objs, types); 
 } 




public final BillDTO extractBill(ResultSet rs)       
    throws SQLException {  
         BillDTO obj = new BillDTO();          
         int i = 1;      
         obj.setActive( rs.getInt(i++) );       
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
         obj.setUsername( rs.getString(i++) );       
         obj.setWeek( rs.getInt(i++) );       
         obj.setYear( rs.getInt(i++) );       
 return obj;  } 


 /*************** end *****************/
}
