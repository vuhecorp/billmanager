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
  import com.hecorp.api.dao.AbstractDA;
  import com.hecorp.api.dao.DAOStreamReader;
  import java.sql.Connection;      
  import java.io.InputStream;     
  import java.io.OutputStream;     
  import java.math.BigDecimal;      
  public interface VBillItemSummaryDAO extends AbstractDA {

     public void open();
     public void close();
     public void setConnection(Connection con); 
     public VBillItemSummaryDTO createVBillItemSummary( VBillItemSummaryDTO obj )throws VBillItemSummaryCreateException ; 
     public void updateVBillItemSummary( VBillItemSummaryDTO obj ) throws VBillItemSummaryUpdateException, VBillItemSummaryFinderException ;
     public void deleteVBillItemSummary( VBillItemSummaryDTO obj ) throws VBillItemSummaryDeleteException, VBillItemSummaryFinderException; 
     public void deleteVBillItemSummaryWhere(String whereClause, Object[] whereParams, int[] paramTypes) throws VBillItemSummaryDeleteException, VBillItemSummaryFinderException; 
     public VBillItemSummaryDTO[] listAllVBillItemSummary();
     public VBillItemSummaryDTO[] listAllVBillItemSummary(String orderByColumn);
     public VBillItemSummaryDTO [] listVBillItemSummary(String whereClause, Object[] whereParams, int[] paramTypes, String orderClause); 
     public VBillItemSummaryDTO [] customVBillItemSummary(String completeSQL); 
     public int countVBillItemSummary(String whereClause, Object[] whereParams, int[] paramTypes); 
     public VBillItemSummaryDTO [] searchPageableVBillItemSummary(String whereClause, Object[] whereParams, int[] paramTypes, String orderClause, int startIndex, int pageSize); 



public VBillItemSummaryDTO [] listVBillItemSummaryByActive(int _Active); 
public VBillItemSummaryDTO [] listVBillItemSummaryByActive(int _Active, String orderByColumn); 
public int countVBillItemSummaryByActive(int _Active); 

  public void deleteVBillItemSummaryByActive(int _Active) throws VBillItemSummaryFinderException, VBillItemSummaryDeleteException; 




public VBillItemSummaryDTO [] listVBillItemSummaryByBilledAmount(BigDecimal _BilledAmount); 
public VBillItemSummaryDTO [] listVBillItemSummaryByBilledAmount(BigDecimal _BilledAmount, String orderByColumn); 
public int countVBillItemSummaryByBilledAmount(BigDecimal _BilledAmount); 

  public void deleteVBillItemSummaryByBilledAmount(BigDecimal _BilledAmount) throws VBillItemSummaryFinderException, VBillItemSummaryDeleteException; 




public VBillItemSummaryDTO [] listVBillItemSummaryByBillId(long _BillId); 
public VBillItemSummaryDTO [] listVBillItemSummaryByBillId(long _BillId, String orderByColumn); 
public int countVBillItemSummaryByBillId(long _BillId); 

  public void deleteVBillItemSummaryByBillId(long _BillId) throws VBillItemSummaryFinderException, VBillItemSummaryDeleteException; 




public VBillItemSummaryDTO [] listVBillItemSummaryByCreatedBy(String _CreatedBy); 
public VBillItemSummaryDTO [] listVBillItemSummaryByCreatedBy(String _CreatedBy, String orderByColumn); 
public int countVBillItemSummaryByCreatedBy(String _CreatedBy); 

  public void deleteVBillItemSummaryByCreatedBy(String _CreatedBy) throws VBillItemSummaryFinderException, VBillItemSummaryDeleteException; 




public VBillItemSummaryDTO [] listVBillItemSummaryByCreatedOn(java.util.Date _CreatedOn); 
public VBillItemSummaryDTO [] listVBillItemSummaryByCreatedOn(java.util.Date _CreatedOn, String orderByColumn); 
public int countVBillItemSummaryByCreatedOn(java.util.Date _CreatedOn); 

  public void deleteVBillItemSummaryByCreatedOn(java.util.Date _CreatedOn) throws VBillItemSummaryFinderException, VBillItemSummaryDeleteException; 




public VBillItemSummaryDTO [] listVBillItemSummaryByDateDue(java.util.Date _DateDue); 
public VBillItemSummaryDTO [] listVBillItemSummaryByDateDue(java.util.Date _DateDue, String orderByColumn); 
public int countVBillItemSummaryByDateDue(java.util.Date _DateDue); 

  public void deleteVBillItemSummaryByDateDue(java.util.Date _DateDue) throws VBillItemSummaryFinderException, VBillItemSummaryDeleteException; 




public VBillItemSummaryDTO [] listVBillItemSummaryByDay(int _Day); 
public VBillItemSummaryDTO [] listVBillItemSummaryByDay(int _Day, String orderByColumn); 
public int countVBillItemSummaryByDay(int _Day); 

  public void deleteVBillItemSummaryByDay(int _Day) throws VBillItemSummaryFinderException, VBillItemSummaryDeleteException; 




public VBillItemSummaryDTO [] listVBillItemSummaryByDescription(String _Description); 
public VBillItemSummaryDTO [] listVBillItemSummaryByDescription(String _Description, String orderByColumn); 
public int countVBillItemSummaryByDescription(String _Description); 

  public void deleteVBillItemSummaryByDescription(String _Description) throws VBillItemSummaryFinderException, VBillItemSummaryDeleteException; 




public VBillItemSummaryDTO [] listVBillItemSummaryByEntityCode(String _EntityCode); 
public VBillItemSummaryDTO [] listVBillItemSummaryByEntityCode(String _EntityCode, String orderByColumn); 
public int countVBillItemSummaryByEntityCode(String _EntityCode); 

  public void deleteVBillItemSummaryByEntityCode(String _EntityCode) throws VBillItemSummaryFinderException, VBillItemSummaryDeleteException; 




public VBillItemSummaryDTO [] listVBillItemSummaryById(long _Id); 
public VBillItemSummaryDTO [] listVBillItemSummaryById(long _Id, String orderByColumn); 
public int countVBillItemSummaryById(long _Id); 

  public void deleteVBillItemSummaryById(long _Id) throws VBillItemSummaryFinderException, VBillItemSummaryDeleteException; 




public VBillItemSummaryDTO [] listVBillItemSummaryByModifiedBy(String _ModifiedBy); 
public VBillItemSummaryDTO [] listVBillItemSummaryByModifiedBy(String _ModifiedBy, String orderByColumn); 
public int countVBillItemSummaryByModifiedBy(String _ModifiedBy); 

  public void deleteVBillItemSummaryByModifiedBy(String _ModifiedBy) throws VBillItemSummaryFinderException, VBillItemSummaryDeleteException; 




public VBillItemSummaryDTO [] listVBillItemSummaryByModifiedOn(java.util.Date _ModifiedOn); 
public VBillItemSummaryDTO [] listVBillItemSummaryByModifiedOn(java.util.Date _ModifiedOn, String orderByColumn); 
public int countVBillItemSummaryByModifiedOn(java.util.Date _ModifiedOn); 

  public void deleteVBillItemSummaryByModifiedOn(java.util.Date _ModifiedOn) throws VBillItemSummaryFinderException, VBillItemSummaryDeleteException; 




public VBillItemSummaryDTO [] listVBillItemSummaryByMonth(int _Month); 
public VBillItemSummaryDTO [] listVBillItemSummaryByMonth(int _Month, String orderByColumn); 
public int countVBillItemSummaryByMonth(int _Month); 

  public void deleteVBillItemSummaryByMonth(int _Month) throws VBillItemSummaryFinderException, VBillItemSummaryDeleteException; 




public VBillItemSummaryDTO [] listVBillItemSummaryByName(String _Name); 
public VBillItemSummaryDTO [] listVBillItemSummaryByName(String _Name, String orderByColumn); 
public int countVBillItemSummaryByName(String _Name); 

  public void deleteVBillItemSummaryByName(String _Name) throws VBillItemSummaryFinderException, VBillItemSummaryDeleteException; 




public VBillItemSummaryDTO [] listVBillItemSummaryByPaidAmount(BigDecimal _PaidAmount); 
public VBillItemSummaryDTO [] listVBillItemSummaryByPaidAmount(BigDecimal _PaidAmount, String orderByColumn); 
public int countVBillItemSummaryByPaidAmount(BigDecimal _PaidAmount); 

  public void deleteVBillItemSummaryByPaidAmount(BigDecimal _PaidAmount) throws VBillItemSummaryFinderException, VBillItemSummaryDeleteException; 




public VBillItemSummaryDTO [] listVBillItemSummaryByPayTo(String _PayTo); 
public VBillItemSummaryDTO [] listVBillItemSummaryByPayTo(String _PayTo, String orderByColumn); 
public int countVBillItemSummaryByPayTo(String _PayTo); 

  public void deleteVBillItemSummaryByPayTo(String _PayTo) throws VBillItemSummaryFinderException, VBillItemSummaryDeleteException; 




public VBillItemSummaryDTO [] listVBillItemSummaryByRecurringCode(String _RecurringCode); 
public VBillItemSummaryDTO [] listVBillItemSummaryByRecurringCode(String _RecurringCode, String orderByColumn); 
public int countVBillItemSummaryByRecurringCode(String _RecurringCode); 

  public void deleteVBillItemSummaryByRecurringCode(String _RecurringCode) throws VBillItemSummaryFinderException, VBillItemSummaryDeleteException; 




public VBillItemSummaryDTO [] listVBillItemSummaryByStatus(String _Status); 
public VBillItemSummaryDTO [] listVBillItemSummaryByStatus(String _Status, String orderByColumn); 
public int countVBillItemSummaryByStatus(String _Status); 

  public void deleteVBillItemSummaryByStatus(String _Status) throws VBillItemSummaryFinderException, VBillItemSummaryDeleteException; 




public VBillItemSummaryDTO [] listVBillItemSummaryByStatusDate(java.util.Date _StatusDate); 
public VBillItemSummaryDTO [] listVBillItemSummaryByStatusDate(java.util.Date _StatusDate, String orderByColumn); 
public int countVBillItemSummaryByStatusDate(java.util.Date _StatusDate); 

  public void deleteVBillItemSummaryByStatusDate(java.util.Date _StatusDate) throws VBillItemSummaryFinderException, VBillItemSummaryDeleteException; 




public VBillItemSummaryDTO [] listVBillItemSummaryByTemplateId(long _TemplateId); 
public VBillItemSummaryDTO [] listVBillItemSummaryByTemplateId(long _TemplateId, String orderByColumn); 
public int countVBillItemSummaryByTemplateId(long _TemplateId); 

  public void deleteVBillItemSummaryByTemplateId(long _TemplateId) throws VBillItemSummaryFinderException, VBillItemSummaryDeleteException; 




public VBillItemSummaryDTO [] listVBillItemSummaryByTimeDue(java.util.Date _TimeDue); 
public VBillItemSummaryDTO [] listVBillItemSummaryByTimeDue(java.util.Date _TimeDue, String orderByColumn); 
public int countVBillItemSummaryByTimeDue(java.util.Date _TimeDue); 

  public void deleteVBillItemSummaryByTimeDue(java.util.Date _TimeDue) throws VBillItemSummaryFinderException, VBillItemSummaryDeleteException; 




public VBillItemSummaryDTO [] listVBillItemSummaryByTypeCode(String _TypeCode); 
public VBillItemSummaryDTO [] listVBillItemSummaryByTypeCode(String _TypeCode, String orderByColumn); 
public int countVBillItemSummaryByTypeCode(String _TypeCode); 

  public void deleteVBillItemSummaryByTypeCode(String _TypeCode) throws VBillItemSummaryFinderException, VBillItemSummaryDeleteException; 




public VBillItemSummaryDTO [] listVBillItemSummaryByUsername(String _Username); 
public VBillItemSummaryDTO [] listVBillItemSummaryByUsername(String _Username, String orderByColumn); 
public int countVBillItemSummaryByUsername(String _Username); 

  public void deleteVBillItemSummaryByUsername(String _Username) throws VBillItemSummaryFinderException, VBillItemSummaryDeleteException; 




public VBillItemSummaryDTO [] listVBillItemSummaryByWeek(int _Week); 
public VBillItemSummaryDTO [] listVBillItemSummaryByWeek(int _Week, String orderByColumn); 
public int countVBillItemSummaryByWeek(int _Week); 

  public void deleteVBillItemSummaryByWeek(int _Week) throws VBillItemSummaryFinderException, VBillItemSummaryDeleteException; 




public VBillItemSummaryDTO [] listVBillItemSummaryByYear(int _Year); 
public VBillItemSummaryDTO [] listVBillItemSummaryByYear(int _Year, String orderByColumn); 
public int countVBillItemSummaryByYear(int _Year); 

  public void deleteVBillItemSummaryByYear(int _Year) throws VBillItemSummaryFinderException, VBillItemSummaryDeleteException; 







 /*************** end *****************/
}
