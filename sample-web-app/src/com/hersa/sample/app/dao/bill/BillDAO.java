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
  import com.hecorp.api.dao.AbstractDA;
  import com.hecorp.api.dao.DAOStreamReader;
  import java.sql.Connection;      
  import java.io.InputStream;     
  import java.io.OutputStream;     
  import java.math.BigDecimal;      
  public interface BillDAO extends AbstractDA {

     public void open();
     public void close();
     public void setConnection(Connection con); 
     public BillDTO createBill( BillDTO obj )throws BillCreateException ; 
     public void updateBill( BillDTO obj ) throws BillUpdateException, BillFinderException ;
     public void deleteBill( BillDTO obj ) throws BillDeleteException, BillFinderException; 
     public void deleteBillWhere(String whereClause, Object[] whereParams, int[] paramTypes) throws BillDeleteException, BillFinderException; 
     public BillDTO[] listAllBill();
     public BillDTO[] listAllBill(String orderByColumn);
     public BillDTO [] listBill(String whereClause, Object[] whereParams, int[] paramTypes, String orderClause); 
     public BillDTO [] customBill(String completeSQL); 
     public int countBill(String whereClause, Object[] whereParams, int[] paramTypes); 
     public BillDTO [] searchPageableBill(String whereClause, Object[] whereParams, int[] paramTypes, String orderClause, int startIndex, int pageSize); 
public BillDTO [] listBillByPK(long _Id); 
public BillDTO [] listBillByPK(long _Id, String orderByColumn); 
public int countBillByPK(long _Id); 




public BillDTO [] listBillByActive(int _Active); 
public BillDTO [] listBillByActive(int _Active, String orderByColumn); 
public int countBillByActive(int _Active); 

  public void deleteBillByActive(int _Active) throws BillFinderException, BillDeleteException; 




public BillDTO [] listBillByCreatedBy(String _CreatedBy); 
public BillDTO [] listBillByCreatedBy(String _CreatedBy, String orderByColumn); 
public int countBillByCreatedBy(String _CreatedBy); 

  public void deleteBillByCreatedBy(String _CreatedBy) throws BillFinderException, BillDeleteException; 




public BillDTO [] listBillByCreatedOn(java.util.Date _CreatedOn); 
public BillDTO [] listBillByCreatedOn(java.util.Date _CreatedOn, String orderByColumn); 
public int countBillByCreatedOn(java.util.Date _CreatedOn); 

  public void deleteBillByCreatedOn(java.util.Date _CreatedOn) throws BillFinderException, BillDeleteException; 




public BillDTO [] listBillByCycleType(String _CycleType); 
public BillDTO [] listBillByCycleType(String _CycleType, String orderByColumn); 
public int countBillByCycleType(String _CycleType); 

  public void deleteBillByCycleType(String _CycleType) throws BillFinderException, BillDeleteException; 




public BillDTO [] listBillByDay(int _Day); 
public BillDTO [] listBillByDay(int _Day, String orderByColumn); 
public int countBillByDay(int _Day); 

  public void deleteBillByDay(int _Day) throws BillFinderException, BillDeleteException; 




public BillDTO [] listBillByDescriptor(String _Descriptor); 
public BillDTO [] listBillByDescriptor(String _Descriptor, String orderByColumn); 
public int countBillByDescriptor(String _Descriptor); 

  public void deleteBillByDescriptor(String _Descriptor) throws BillFinderException, BillDeleteException; 




public BillDTO [] listBillByEndDate(java.util.Date _EndDate); 
public BillDTO [] listBillByEndDate(java.util.Date _EndDate, String orderByColumn); 
public int countBillByEndDate(java.util.Date _EndDate); 

  public void deleteBillByEndDate(java.util.Date _EndDate) throws BillFinderException, BillDeleteException; 




public BillDTO [] listBillById(long _Id); 
public BillDTO [] listBillById(long _Id, String orderByColumn); 
public int countBillById(long _Id); 

  public void deleteBillById(long _Id) throws BillFinderException, BillDeleteException; 




public BillDTO [] listBillByModifiedBy(String _ModifiedBy); 
public BillDTO [] listBillByModifiedBy(String _ModifiedBy, String orderByColumn); 
public int countBillByModifiedBy(String _ModifiedBy); 

  public void deleteBillByModifiedBy(String _ModifiedBy) throws BillFinderException, BillDeleteException; 




public BillDTO [] listBillByModifiedOn(java.util.Date _ModifiedOn); 
public BillDTO [] listBillByModifiedOn(java.util.Date _ModifiedOn, String orderByColumn); 
public int countBillByModifiedOn(java.util.Date _ModifiedOn); 

  public void deleteBillByModifiedOn(java.util.Date _ModifiedOn) throws BillFinderException, BillDeleteException; 




public BillDTO [] listBillByMonth(int _Month); 
public BillDTO [] listBillByMonth(int _Month, String orderByColumn); 
public int countBillByMonth(int _Month); 

  public void deleteBillByMonth(int _Month) throws BillFinderException, BillDeleteException; 




public BillDTO [] listBillByStartDate(java.util.Date _StartDate); 
public BillDTO [] listBillByStartDate(java.util.Date _StartDate, String orderByColumn); 
public int countBillByStartDate(java.util.Date _StartDate); 

  public void deleteBillByStartDate(java.util.Date _StartDate) throws BillFinderException, BillDeleteException; 




public BillDTO [] listBillByStatus(String _Status); 
public BillDTO [] listBillByStatus(String _Status, String orderByColumn); 
public int countBillByStatus(String _Status); 

  public void deleteBillByStatus(String _Status) throws BillFinderException, BillDeleteException; 




public BillDTO [] listBillByStatusDate(java.util.Date _StatusDate); 
public BillDTO [] listBillByStatusDate(java.util.Date _StatusDate, String orderByColumn); 
public int countBillByStatusDate(java.util.Date _StatusDate); 

  public void deleteBillByStatusDate(java.util.Date _StatusDate) throws BillFinderException, BillDeleteException; 




public BillDTO [] listBillByUsername(String _Username); 
public BillDTO [] listBillByUsername(String _Username, String orderByColumn); 
public int countBillByUsername(String _Username); 

  public void deleteBillByUsername(String _Username) throws BillFinderException, BillDeleteException; 




public BillDTO [] listBillByWeek(int _Week); 
public BillDTO [] listBillByWeek(int _Week, String orderByColumn); 
public int countBillByWeek(int _Week); 

  public void deleteBillByWeek(int _Week) throws BillFinderException, BillDeleteException; 




public BillDTO [] listBillByYear(int _Year); 
public BillDTO [] listBillByYear(int _Year, String orderByColumn); 
public int countBillByYear(int _Year); 

  public void deleteBillByYear(int _Year) throws BillFinderException, BillDeleteException; 






 /*************** end *****************/
}
