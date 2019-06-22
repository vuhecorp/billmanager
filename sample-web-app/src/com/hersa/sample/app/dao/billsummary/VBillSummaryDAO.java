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
  import com.hecorp.api.dao.AbstractDA;
  import com.hecorp.api.dao.DAOStreamReader;
  import java.sql.Connection;      
  import java.io.InputStream;     
  import java.io.OutputStream;     
  import java.math.BigDecimal;      
  public interface VBillSummaryDAO extends AbstractDA {

     public void open();
     public void close();
     public void setConnection(Connection con); 
     public VBillSummaryDTO createVBillSummary( VBillSummaryDTO obj )throws VBillSummaryCreateException ; 
     public void updateVBillSummary( VBillSummaryDTO obj ) throws VBillSummaryUpdateException, VBillSummaryFinderException ;
     public void deleteVBillSummary( VBillSummaryDTO obj ) throws VBillSummaryDeleteException, VBillSummaryFinderException; 
     public void deleteVBillSummaryWhere(String whereClause, Object[] whereParams, int[] paramTypes) throws VBillSummaryDeleteException, VBillSummaryFinderException; 
     public VBillSummaryDTO[] listAllVBillSummary();
     public VBillSummaryDTO[] listAllVBillSummary(String orderByColumn);
     public VBillSummaryDTO [] listVBillSummary(String whereClause, Object[] whereParams, int[] paramTypes, String orderClause); 
     public VBillSummaryDTO [] customVBillSummary(String completeSQL); 
     public int countVBillSummary(String whereClause, Object[] whereParams, int[] paramTypes); 
     public VBillSummaryDTO [] searchPageableVBillSummary(String whereClause, Object[] whereParams, int[] paramTypes, String orderClause, int startIndex, int pageSize); 



public VBillSummaryDTO [] listVBillSummaryByCreatedBy(String _CreatedBy); 
public VBillSummaryDTO [] listVBillSummaryByCreatedBy(String _CreatedBy, String orderByColumn); 
public int countVBillSummaryByCreatedBy(String _CreatedBy); 

  public void deleteVBillSummaryByCreatedBy(String _CreatedBy) throws VBillSummaryFinderException, VBillSummaryDeleteException; 




public VBillSummaryDTO [] listVBillSummaryByCreatedOn(java.util.Date _CreatedOn); 
public VBillSummaryDTO [] listVBillSummaryByCreatedOn(java.util.Date _CreatedOn, String orderByColumn); 
public int countVBillSummaryByCreatedOn(java.util.Date _CreatedOn); 

  public void deleteVBillSummaryByCreatedOn(java.util.Date _CreatedOn) throws VBillSummaryFinderException, VBillSummaryDeleteException; 




public VBillSummaryDTO [] listVBillSummaryByCycleType(String _CycleType); 
public VBillSummaryDTO [] listVBillSummaryByCycleType(String _CycleType, String orderByColumn); 
public int countVBillSummaryByCycleType(String _CycleType); 

  public void deleteVBillSummaryByCycleType(String _CycleType) throws VBillSummaryFinderException, VBillSummaryDeleteException; 




public VBillSummaryDTO [] listVBillSummaryByDay(int _Day); 
public VBillSummaryDTO [] listVBillSummaryByDay(int _Day, String orderByColumn); 
public int countVBillSummaryByDay(int _Day); 

  public void deleteVBillSummaryByDay(int _Day) throws VBillSummaryFinderException, VBillSummaryDeleteException; 




public VBillSummaryDTO [] listVBillSummaryByDescriptor(String _Descriptor); 
public VBillSummaryDTO [] listVBillSummaryByDescriptor(String _Descriptor, String orderByColumn); 
public int countVBillSummaryByDescriptor(String _Descriptor); 

  public void deleteVBillSummaryByDescriptor(String _Descriptor) throws VBillSummaryFinderException, VBillSummaryDeleteException; 




public VBillSummaryDTO [] listVBillSummaryByEndDate(java.util.Date _EndDate); 
public VBillSummaryDTO [] listVBillSummaryByEndDate(java.util.Date _EndDate, String orderByColumn); 
public int countVBillSummaryByEndDate(java.util.Date _EndDate); 

  public void deleteVBillSummaryByEndDate(java.util.Date _EndDate) throws VBillSummaryFinderException, VBillSummaryDeleteException; 




public VBillSummaryDTO [] listVBillSummaryById(long _Id); 
public VBillSummaryDTO [] listVBillSummaryById(long _Id, String orderByColumn); 
public int countVBillSummaryById(long _Id); 

  public void deleteVBillSummaryById(long _Id) throws VBillSummaryFinderException, VBillSummaryDeleteException; 




public VBillSummaryDTO [] listVBillSummaryByModifiedBy(String _ModifiedBy); 
public VBillSummaryDTO [] listVBillSummaryByModifiedBy(String _ModifiedBy, String orderByColumn); 
public int countVBillSummaryByModifiedBy(String _ModifiedBy); 

  public void deleteVBillSummaryByModifiedBy(String _ModifiedBy) throws VBillSummaryFinderException, VBillSummaryDeleteException; 




public VBillSummaryDTO [] listVBillSummaryByModifiedOn(java.util.Date _ModifiedOn); 
public VBillSummaryDTO [] listVBillSummaryByModifiedOn(java.util.Date _ModifiedOn, String orderByColumn); 
public int countVBillSummaryByModifiedOn(java.util.Date _ModifiedOn); 

  public void deleteVBillSummaryByModifiedOn(java.util.Date _ModifiedOn) throws VBillSummaryFinderException, VBillSummaryDeleteException; 




public VBillSummaryDTO [] listVBillSummaryByMonth(int _Month); 
public VBillSummaryDTO [] listVBillSummaryByMonth(int _Month, String orderByColumn); 
public int countVBillSummaryByMonth(int _Month); 

  public void deleteVBillSummaryByMonth(int _Month) throws VBillSummaryFinderException, VBillSummaryDeleteException; 




public VBillSummaryDTO [] listVBillSummaryByStartDate(java.util.Date _StartDate); 
public VBillSummaryDTO [] listVBillSummaryByStartDate(java.util.Date _StartDate, String orderByColumn); 
public int countVBillSummaryByStartDate(java.util.Date _StartDate); 

  public void deleteVBillSummaryByStartDate(java.util.Date _StartDate) throws VBillSummaryFinderException, VBillSummaryDeleteException; 




public VBillSummaryDTO [] listVBillSummaryByStatus(String _Status); 
public VBillSummaryDTO [] listVBillSummaryByStatus(String _Status, String orderByColumn); 
public int countVBillSummaryByStatus(String _Status); 

  public void deleteVBillSummaryByStatus(String _Status) throws VBillSummaryFinderException, VBillSummaryDeleteException; 




public VBillSummaryDTO [] listVBillSummaryByStatusDate(java.util.Date _StatusDate); 
public VBillSummaryDTO [] listVBillSummaryByStatusDate(java.util.Date _StatusDate, String orderByColumn); 
public int countVBillSummaryByStatusDate(java.util.Date _StatusDate); 

  public void deleteVBillSummaryByStatusDate(java.util.Date _StatusDate) throws VBillSummaryFinderException, VBillSummaryDeleteException; 




public VBillSummaryDTO [] listVBillSummaryByTotalBilled(BigDecimal _TotalBilled); 
public VBillSummaryDTO [] listVBillSummaryByTotalBilled(BigDecimal _TotalBilled, String orderByColumn); 
public int countVBillSummaryByTotalBilled(BigDecimal _TotalBilled); 

  public void deleteVBillSummaryByTotalBilled(BigDecimal _TotalBilled) throws VBillSummaryFinderException, VBillSummaryDeleteException; 




public VBillSummaryDTO [] listVBillSummaryByTotalPaid(int _TotalPaid); 
public VBillSummaryDTO [] listVBillSummaryByTotalPaid(int _TotalPaid, String orderByColumn); 
public int countVBillSummaryByTotalPaid(int _TotalPaid); 

  public void deleteVBillSummaryByTotalPaid(int _TotalPaid) throws VBillSummaryFinderException, VBillSummaryDeleteException; 




public VBillSummaryDTO [] listVBillSummaryByUsername(String _Username); 
public VBillSummaryDTO [] listVBillSummaryByUsername(String _Username, String orderByColumn); 
public int countVBillSummaryByUsername(String _Username); 

  public void deleteVBillSummaryByUsername(String _Username) throws VBillSummaryFinderException, VBillSummaryDeleteException; 




public VBillSummaryDTO [] listVBillSummaryByWeek(int _Week); 
public VBillSummaryDTO [] listVBillSummaryByWeek(int _Week, String orderByColumn); 
public int countVBillSummaryByWeek(int _Week); 

  public void deleteVBillSummaryByWeek(int _Week) throws VBillSummaryFinderException, VBillSummaryDeleteException; 




public VBillSummaryDTO [] listVBillSummaryByYear(int _Year); 
public VBillSummaryDTO [] listVBillSummaryByYear(int _Year, String orderByColumn); 
public int countVBillSummaryByYear(int _Year); 

  public void deleteVBillSummaryByYear(int _Year) throws VBillSummaryFinderException, VBillSummaryDeleteException; 










 /*************** end *****************/
}
