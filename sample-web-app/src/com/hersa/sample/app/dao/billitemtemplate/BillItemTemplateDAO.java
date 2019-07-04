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
  import com.hecorp.api.dao.AbstractDA;
  import com.hecorp.api.dao.DAOStreamReader;
  import java.sql.Connection;      
  import java.io.InputStream;     
  import java.io.OutputStream;     
  import java.math.BigDecimal;      
  public interface BillItemTemplateDAO extends AbstractDA {

     public void open();
     public void close();
     public void setConnection(Connection con); 
     public BillItemTemplateDTO createBillItemTemplate( BillItemTemplateDTO obj )throws BillItemTemplateCreateException ; 
     public void updateBillItemTemplate( BillItemTemplateDTO obj ) throws BillItemTemplateUpdateException, BillItemTemplateFinderException ;
     public void deleteBillItemTemplate( BillItemTemplateDTO obj ) throws BillItemTemplateDeleteException, BillItemTemplateFinderException; 
     public void deleteBillItemTemplateWhere(String whereClause, Object[] whereParams, int[] paramTypes) throws BillItemTemplateDeleteException, BillItemTemplateFinderException; 
     public BillItemTemplateDTO[] listAllBillItemTemplate();
     public BillItemTemplateDTO[] listAllBillItemTemplate(String orderByColumn);
     public BillItemTemplateDTO [] listBillItemTemplate(String whereClause, Object[] whereParams, int[] paramTypes, String orderClause); 
     public BillItemTemplateDTO [] customBillItemTemplate(String completeSQL); 
     public int countBillItemTemplate(String whereClause, Object[] whereParams, int[] paramTypes); 
     public BillItemTemplateDTO [] searchPageableBillItemTemplate(String whereClause, Object[] whereParams, int[] paramTypes, String orderClause, int startIndex, int pageSize); 
public BillItemTemplateDTO [] listBillItemTemplateByPK(long _Id); 
public BillItemTemplateDTO [] listBillItemTemplateByPK(long _Id, String orderByColumn); 
public int countBillItemTemplateByPK(long _Id); 




public BillItemTemplateDTO [] listBillItemTemplateByActive(int _Active); 
public BillItemTemplateDTO [] listBillItemTemplateByActive(int _Active, String orderByColumn); 
public int countBillItemTemplateByActive(int _Active); 

  public void deleteBillItemTemplateByActive(int _Active) throws BillItemTemplateFinderException, BillItemTemplateDeleteException; 




public BillItemTemplateDTO [] listBillItemTemplateByAmount(BigDecimal _Amount); 
public BillItemTemplateDTO [] listBillItemTemplateByAmount(BigDecimal _Amount, String orderByColumn); 
public int countBillItemTemplateByAmount(BigDecimal _Amount); 

  public void deleteBillItemTemplateByAmount(BigDecimal _Amount) throws BillItemTemplateFinderException, BillItemTemplateDeleteException; 




public BillItemTemplateDTO [] listBillItemTemplateByCreatedBy(String _CreatedBy); 
public BillItemTemplateDTO [] listBillItemTemplateByCreatedBy(String _CreatedBy, String orderByColumn); 
public int countBillItemTemplateByCreatedBy(String _CreatedBy); 

  public void deleteBillItemTemplateByCreatedBy(String _CreatedBy) throws BillItemTemplateFinderException, BillItemTemplateDeleteException; 




public BillItemTemplateDTO [] listBillItemTemplateByCreatedOn(java.util.Date _CreatedOn); 
public BillItemTemplateDTO [] listBillItemTemplateByCreatedOn(java.util.Date _CreatedOn, String orderByColumn); 
public int countBillItemTemplateByCreatedOn(java.util.Date _CreatedOn); 

  public void deleteBillItemTemplateByCreatedOn(java.util.Date _CreatedOn) throws BillItemTemplateFinderException, BillItemTemplateDeleteException; 




public BillItemTemplateDTO [] listBillItemTemplateByDayDue(int _DayDue); 
public BillItemTemplateDTO [] listBillItemTemplateByDayDue(int _DayDue, String orderByColumn); 
public int countBillItemTemplateByDayDue(int _DayDue); 

  public void deleteBillItemTemplateByDayDue(int _DayDue) throws BillItemTemplateFinderException, BillItemTemplateDeleteException; 




public BillItemTemplateDTO [] listBillItemTemplateByDescription(String _Description); 
public BillItemTemplateDTO [] listBillItemTemplateByDescription(String _Description, String orderByColumn); 
public int countBillItemTemplateByDescription(String _Description); 

  public void deleteBillItemTemplateByDescription(String _Description) throws BillItemTemplateFinderException, BillItemTemplateDeleteException; 




public BillItemTemplateDTO [] listBillItemTemplateByEntityCode(String _EntityCode); 
public BillItemTemplateDTO [] listBillItemTemplateByEntityCode(String _EntityCode, String orderByColumn); 
public int countBillItemTemplateByEntityCode(String _EntityCode); 

  public void deleteBillItemTemplateByEntityCode(String _EntityCode) throws BillItemTemplateFinderException, BillItemTemplateDeleteException; 




public BillItemTemplateDTO [] listBillItemTemplateByFromDate(java.util.Date _FromDate); 
public BillItemTemplateDTO [] listBillItemTemplateByFromDate(java.util.Date _FromDate, String orderByColumn); 
public int countBillItemTemplateByFromDate(java.util.Date _FromDate); 

  public void deleteBillItemTemplateByFromDate(java.util.Date _FromDate) throws BillItemTemplateFinderException, BillItemTemplateDeleteException; 




public BillItemTemplateDTO [] listBillItemTemplateById(long _Id); 
public BillItemTemplateDTO [] listBillItemTemplateById(long _Id, String orderByColumn); 
public int countBillItemTemplateById(long _Id); 

  public void deleteBillItemTemplateById(long _Id) throws BillItemTemplateFinderException, BillItemTemplateDeleteException; 




public BillItemTemplateDTO [] listBillItemTemplateByModifiedBy(String _ModifiedBy); 
public BillItemTemplateDTO [] listBillItemTemplateByModifiedBy(String _ModifiedBy, String orderByColumn); 
public int countBillItemTemplateByModifiedBy(String _ModifiedBy); 

  public void deleteBillItemTemplateByModifiedBy(String _ModifiedBy) throws BillItemTemplateFinderException, BillItemTemplateDeleteException; 




public BillItemTemplateDTO [] listBillItemTemplateByModifiedOn(java.util.Date _ModifiedOn); 
public BillItemTemplateDTO [] listBillItemTemplateByModifiedOn(java.util.Date _ModifiedOn, String orderByColumn); 
public int countBillItemTemplateByModifiedOn(java.util.Date _ModifiedOn); 

  public void deleteBillItemTemplateByModifiedOn(java.util.Date _ModifiedOn) throws BillItemTemplateFinderException, BillItemTemplateDeleteException; 




public BillItemTemplateDTO [] listBillItemTemplateByName(String _Name); 
public BillItemTemplateDTO [] listBillItemTemplateByName(String _Name, String orderByColumn); 
public int countBillItemTemplateByName(String _Name); 

  public void deleteBillItemTemplateByName(String _Name) throws BillItemTemplateFinderException, BillItemTemplateDeleteException; 




public BillItemTemplateDTO [] listBillItemTemplateByPayTo(String _PayTo); 
public BillItemTemplateDTO [] listBillItemTemplateByPayTo(String _PayTo, String orderByColumn); 
public int countBillItemTemplateByPayTo(String _PayTo); 

  public void deleteBillItemTemplateByPayTo(String _PayTo) throws BillItemTemplateFinderException, BillItemTemplateDeleteException; 




public BillItemTemplateDTO [] listBillItemTemplateByQuartzExp(String _QuartzExp); 
public BillItemTemplateDTO [] listBillItemTemplateByQuartzExp(String _QuartzExp, String orderByColumn); 
public int countBillItemTemplateByQuartzExp(String _QuartzExp); 

  public void deleteBillItemTemplateByQuartzExp(String _QuartzExp) throws BillItemTemplateFinderException, BillItemTemplateDeleteException; 




public BillItemTemplateDTO [] listBillItemTemplateByRecurring(int _Recurring); 
public BillItemTemplateDTO [] listBillItemTemplateByRecurring(int _Recurring, String orderByColumn); 
public int countBillItemTemplateByRecurring(int _Recurring); 

  public void deleteBillItemTemplateByRecurring(int _Recurring) throws BillItemTemplateFinderException, BillItemTemplateDeleteException; 




public BillItemTemplateDTO [] listBillItemTemplateByRecurringCode(String _RecurringCode); 
public BillItemTemplateDTO [] listBillItemTemplateByRecurringCode(String _RecurringCode, String orderByColumn); 
public int countBillItemTemplateByRecurringCode(String _RecurringCode); 

  public void deleteBillItemTemplateByRecurringCode(String _RecurringCode) throws BillItemTemplateFinderException, BillItemTemplateDeleteException; 




public BillItemTemplateDTO [] listBillItemTemplateByTimeDue(java.util.Date _TimeDue); 
public BillItemTemplateDTO [] listBillItemTemplateByTimeDue(java.util.Date _TimeDue, String orderByColumn); 
public int countBillItemTemplateByTimeDue(java.util.Date _TimeDue); 

  public void deleteBillItemTemplateByTimeDue(java.util.Date _TimeDue) throws BillItemTemplateFinderException, BillItemTemplateDeleteException; 




public BillItemTemplateDTO [] listBillItemTemplateByToDate(java.util.Date _ToDate); 
public BillItemTemplateDTO [] listBillItemTemplateByToDate(java.util.Date _ToDate, String orderByColumn); 
public int countBillItemTemplateByToDate(java.util.Date _ToDate); 

  public void deleteBillItemTemplateByToDate(java.util.Date _ToDate) throws BillItemTemplateFinderException, BillItemTemplateDeleteException; 




public BillItemTemplateDTO [] listBillItemTemplateByTypeCode(String _TypeCode); 
public BillItemTemplateDTO [] listBillItemTemplateByTypeCode(String _TypeCode, String orderByColumn); 
public int countBillItemTemplateByTypeCode(String _TypeCode); 

  public void deleteBillItemTemplateByTypeCode(String _TypeCode) throws BillItemTemplateFinderException, BillItemTemplateDeleteException; 




public BillItemTemplateDTO [] listBillItemTemplateByUsername(String _Username); 
public BillItemTemplateDTO [] listBillItemTemplateByUsername(String _Username, String orderByColumn); 
public int countBillItemTemplateByUsername(String _Username); 

  public void deleteBillItemTemplateByUsername(String _Username) throws BillItemTemplateFinderException, BillItemTemplateDeleteException; 






 /*************** end *****************/
}
