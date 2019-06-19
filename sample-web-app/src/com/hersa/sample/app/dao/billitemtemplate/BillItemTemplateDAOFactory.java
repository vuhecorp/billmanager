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
  import java.sql.Connection;      
  import java.math.BigDecimal;     
    public final class BillItemTemplateDAOFactory {         
     //private static final BillItemTemplateDAO dao = new BillItemTemplateDAOImpl();      
     private BillItemTemplateDAOFactory() {      
     }      
     public final static BillItemTemplateDAO getDAO() {      
             return new BillItemTemplateDAOImpl();       
         }       
     public final static BillItemTemplateDAO getDAO(boolean external) {      
         return (external ? new BillItemTemplateDAOImplXA(): new BillItemTemplateDAOImpl());      
     }                 
      public final static BillItemTemplateDAO getDAO(boolean external, Connection nestedConnection) {      
          BillItemTemplateDAO dao = (external ? new BillItemTemplateDAOImplXA() : new BillItemTemplateDAOImpl());      
          dao.setConnection(nestedConnection);      
          return dao;      
      }      
      public final static BillItemTemplateDAO getDAO(Connection nestedConnection) {      
          return getDAO(false, nestedConnection);      
      }               
    }         
