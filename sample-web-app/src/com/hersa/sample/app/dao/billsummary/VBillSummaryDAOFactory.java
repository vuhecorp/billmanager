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
  import java.sql.Connection;      
  import java.math.BigDecimal;     
    public final class VBillSummaryDAOFactory {         
     //private static final VBillSummaryDAO dao = new VBillSummaryDAOImpl();      
     private VBillSummaryDAOFactory() {      
     }      
     public final static VBillSummaryDAO getDAO() {      
             return new VBillSummaryDAOImpl();       
         }       
     public final static VBillSummaryDAO getDAO(boolean external) {      
         return (external ? new VBillSummaryDAOImplXA(): new VBillSummaryDAOImpl());      
     }                 
      public final static VBillSummaryDAO getDAO(boolean external, Connection nestedConnection) {      
          VBillSummaryDAO dao = (external ? new VBillSummaryDAOImplXA() : new VBillSummaryDAOImpl());      
          dao.setConnection(nestedConnection);      
          return dao;      
      }      
      public final static VBillSummaryDAO getDAO(Connection nestedConnection) {      
          return getDAO(false, nestedConnection);      
      }               
    }         
