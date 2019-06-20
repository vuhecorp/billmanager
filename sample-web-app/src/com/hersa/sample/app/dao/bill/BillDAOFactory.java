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
  import java.sql.Connection;      
  import java.math.BigDecimal;     
    public final class BillDAOFactory {         
     //private static final BillDAO dao = new BillDAOImpl();      
     private BillDAOFactory() {      
     }      
     public final static BillDAO getDAO() {      
             return new BillDAOImpl();       
         }       
     public final static BillDAO getDAO(boolean external) {      
         return (external ? new BillDAOImplXA(): new BillDAOImpl());      
     }                 
      public final static BillDAO getDAO(boolean external, Connection nestedConnection) {      
          BillDAO dao = (external ? new BillDAOImplXA() : new BillDAOImpl());      
          dao.setConnection(nestedConnection);      
          return dao;      
      }      
      public final static BillDAO getDAO(Connection nestedConnection) {      
          return getDAO(false, nestedConnection);      
      }               
    }         
