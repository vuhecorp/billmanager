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
package com.hersa.sample.app.dao.billitemsummary;
  import java.sql.Connection;      
  import java.math.BigDecimal;     
    public final class VBillItemSummaryDAOFactory {         
     //private static final VBillItemSummaryDAO dao = new VBillItemSummaryDAOImpl();      
     private VBillItemSummaryDAOFactory() {      
     }      
     public final static VBillItemSummaryDAO getDAO() {      
             return new VBillItemSummaryDAOImpl();       
         }       
     public final static VBillItemSummaryDAO getDAO(boolean external) {      
         return (external ? new VBillItemSummaryDAOImplXA(): new VBillItemSummaryDAOImpl());      
     }                 
      public final static VBillItemSummaryDAO getDAO(boolean external, Connection nestedConnection) {      
          VBillItemSummaryDAO dao = (external ? new VBillItemSummaryDAOImplXA() : new VBillItemSummaryDAOImpl());      
          dao.setConnection(nestedConnection);      
          return dao;      
      }      
      public final static VBillItemSummaryDAO getDAO(Connection nestedConnection) {      
          return getDAO(false, nestedConnection);      
      }               
    }         
