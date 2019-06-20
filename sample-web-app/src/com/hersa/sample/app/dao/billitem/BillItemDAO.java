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
  import com.hecorp.api.dao.AbstractDA;
  import com.hecorp.api.dao.DAOStreamReader;
  import java.sql.Connection;      
  import java.io.InputStream;     
  import java.io.OutputStream;     
  import java.math.BigDecimal;      
  public interface BillItemDAO extends AbstractDA {

     public void open();
     public void close();
     public void setConnection(Connection con); 
     public BillItemDTO createBillItem( BillItemDTO obj )throws BillItemCreateException ; 
     public void updateBillItem( BillItemDTO obj ) throws BillItemUpdateException, BillItemFinderException ;
     public void deleteBillItem( BillItemDTO obj ) throws BillItemDeleteException, BillItemFinderException; 
     public void deleteBillItemWhere(String whereClause, Object[] whereParams, int[] paramTypes) throws BillItemDeleteException, BillItemFinderException; 
     public BillItemDTO[] listAllBillItem();
     public BillItemDTO[] listAllBillItem(String orderByColumn);
     public BillItemDTO [] listBillItem(String whereClause, Object[] whereParams, int[] paramTypes, String orderClause); 
     public BillItemDTO [] customBillItem(String completeSQL); 
     public int countBillItem(String whereClause, Object[] whereParams, int[] paramTypes); 
     public BillItemDTO [] searchPageableBillItem(String whereClause, Object[] whereParams, int[] paramTypes, String orderClause, int startIndex, int pageSize); 
public BillItemDTO [] listBillItemByPK(long _Id); 
public BillItemDTO [] listBillItemByPK(long _Id, String orderByColumn); 
public int countBillItemByPK(long _Id); 




public BillItemDTO [] listBillItemByAmount(BigDecimal _Amount); 
public BillItemDTO [] listBillItemByAmount(BigDecimal _Amount, String orderByColumn); 
public int countBillItemByAmount(BigDecimal _Amount); 

  public void deleteBillItemByAmount(BigDecimal _Amount) throws BillItemFinderException, BillItemDeleteException; 




public BillItemDTO [] listBillItemByBillId(long _BillId); 
public BillItemDTO [] listBillItemByBillId(long _BillId, String orderByColumn); 
public int countBillItemByBillId(long _BillId); 

  public void deleteBillItemByBillId(long _BillId) throws BillItemFinderException, BillItemDeleteException; 




public BillItemDTO [] listBillItemByCreatedBy(String _CreatedBy); 
public BillItemDTO [] listBillItemByCreatedBy(String _CreatedBy, String orderByColumn); 
public int countBillItemByCreatedBy(String _CreatedBy); 

  public void deleteBillItemByCreatedBy(String _CreatedBy) throws BillItemFinderException, BillItemDeleteException; 




public BillItemDTO [] listBillItemByCreatedOn(java.util.Date _CreatedOn); 
public BillItemDTO [] listBillItemByCreatedOn(java.util.Date _CreatedOn, String orderByColumn); 
public int countBillItemByCreatedOn(java.util.Date _CreatedOn); 

  public void deleteBillItemByCreatedOn(java.util.Date _CreatedOn) throws BillItemFinderException, BillItemDeleteException; 




public BillItemDTO [] listBillItemByDateDue(java.util.Date _DateDue); 
public BillItemDTO [] listBillItemByDateDue(java.util.Date _DateDue, String orderByColumn); 
public int countBillItemByDateDue(java.util.Date _DateDue); 

  public void deleteBillItemByDateDue(java.util.Date _DateDue) throws BillItemFinderException, BillItemDeleteException; 




public BillItemDTO [] listBillItemByDescription(String _Description); 
public BillItemDTO [] listBillItemByDescription(String _Description, String orderByColumn); 
public int countBillItemByDescription(String _Description); 

  public void deleteBillItemByDescription(String _Description) throws BillItemFinderException, BillItemDeleteException; 




public BillItemDTO [] listBillItemByEntityCode(String _EntityCode); 
public BillItemDTO [] listBillItemByEntityCode(String _EntityCode, String orderByColumn); 
public int countBillItemByEntityCode(String _EntityCode); 

  public void deleteBillItemByEntityCode(String _EntityCode) throws BillItemFinderException, BillItemDeleteException; 




public BillItemDTO [] listBillItemById(long _Id); 
public BillItemDTO [] listBillItemById(long _Id, String orderByColumn); 
public int countBillItemById(long _Id); 

  public void deleteBillItemById(long _Id) throws BillItemFinderException, BillItemDeleteException; 




public BillItemDTO [] listBillItemByModifiedBy(String _ModifiedBy); 
public BillItemDTO [] listBillItemByModifiedBy(String _ModifiedBy, String orderByColumn); 
public int countBillItemByModifiedBy(String _ModifiedBy); 

  public void deleteBillItemByModifiedBy(String _ModifiedBy) throws BillItemFinderException, BillItemDeleteException; 




public BillItemDTO [] listBillItemByModifiedOn(java.util.Date _ModifiedOn); 
public BillItemDTO [] listBillItemByModifiedOn(java.util.Date _ModifiedOn, String orderByColumn); 
public int countBillItemByModifiedOn(java.util.Date _ModifiedOn); 

  public void deleteBillItemByModifiedOn(java.util.Date _ModifiedOn) throws BillItemFinderException, BillItemDeleteException; 




public BillItemDTO [] listBillItemByName(String _Name); 
public BillItemDTO [] listBillItemByName(String _Name, String orderByColumn); 
public int countBillItemByName(String _Name); 

  public void deleteBillItemByName(String _Name) throws BillItemFinderException, BillItemDeleteException; 




public BillItemDTO [] listBillItemByPayTo(String _PayTo); 
public BillItemDTO [] listBillItemByPayTo(String _PayTo, String orderByColumn); 
public int countBillItemByPayTo(String _PayTo); 

  public void deleteBillItemByPayTo(String _PayTo) throws BillItemFinderException, BillItemDeleteException; 




public BillItemDTO [] listBillItemByRecurringCode(String _RecurringCode); 
public BillItemDTO [] listBillItemByRecurringCode(String _RecurringCode, String orderByColumn); 
public int countBillItemByRecurringCode(String _RecurringCode); 

  public void deleteBillItemByRecurringCode(String _RecurringCode) throws BillItemFinderException, BillItemDeleteException; 




public BillItemDTO [] listBillItemByStatus(String _Status); 
public BillItemDTO [] listBillItemByStatus(String _Status, String orderByColumn); 
public int countBillItemByStatus(String _Status); 

  public void deleteBillItemByStatus(String _Status) throws BillItemFinderException, BillItemDeleteException; 




public BillItemDTO [] listBillItemByStatusDate(java.util.Date _StatusDate); 
public BillItemDTO [] listBillItemByStatusDate(java.util.Date _StatusDate, String orderByColumn); 
public int countBillItemByStatusDate(java.util.Date _StatusDate); 

  public void deleteBillItemByStatusDate(java.util.Date _StatusDate) throws BillItemFinderException, BillItemDeleteException; 




public BillItemDTO [] listBillItemByTemplateId(long _TemplateId); 
public BillItemDTO [] listBillItemByTemplateId(long _TemplateId, String orderByColumn); 
public int countBillItemByTemplateId(long _TemplateId); 

  public void deleteBillItemByTemplateId(long _TemplateId) throws BillItemFinderException, BillItemDeleteException; 




public BillItemDTO [] listBillItemByTimeDue(java.util.Date _TimeDue); 
public BillItemDTO [] listBillItemByTimeDue(java.util.Date _TimeDue, String orderByColumn); 
public int countBillItemByTimeDue(java.util.Date _TimeDue); 

  public void deleteBillItemByTimeDue(java.util.Date _TimeDue) throws BillItemFinderException, BillItemDeleteException; 




public BillItemDTO [] listBillItemByTypeCode(String _TypeCode); 
public BillItemDTO [] listBillItemByTypeCode(String _TypeCode, String orderByColumn); 
public int countBillItemByTypeCode(String _TypeCode); 

  public void deleteBillItemByTypeCode(String _TypeCode) throws BillItemFinderException, BillItemDeleteException; 






 /*************** end *****************/
}
