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
  import java.sql.Connection;      
  import java.math.BigDecimal;     
    public final class BillItemDAOFactory {         
     //private static final BillItemDAO dao = new BillItemDAOImpl();      
     private BillItemDAOFactory() {      
     }      
     public final static BillItemDAO getDAO() {      
             return new BillItemDAOImpl();       
         }       
     public final static BillItemDAO getDAO(boolean external) {      
         return (external ? new BillItemDAOImplXA(): new BillItemDAOImpl());      
     }                 
      public final static BillItemDAO getDAO(boolean external, Connection nestedConnection) {      
          BillItemDAO dao = (external ? new BillItemDAOImplXA() : new BillItemDAOImpl());      
          dao.setConnection(nestedConnection);      
          return dao;      
      }      
      public final static BillItemDAO getDAO(Connection nestedConnection) {      
          return getDAO(false, nestedConnection);      
      }               
    }         
