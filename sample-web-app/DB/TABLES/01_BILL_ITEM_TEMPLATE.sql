/*
  
*/
CREATE TABLE BILL_ITEM_TEMPLATE
( 
    ID BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY ( START WITH 1 INCREMENT BY 1 MINVALUE -9223372036854775808 MAXVALUE 9223372036854775807 CACHE 20 )
  , TYPE_CODE VARCHAR(20) NOT NULL WITH DEFAULT 'NONE'
  , RECURRING INT NOT NULL DEFAULT 1
  , RECURRING_CODE VARCHAR(20) NOT NULL WITH DEFAULT 'MONTH'
  , NAME VARCHAR(50) NOT NULL WITH DEFAULT ''
  , DESCRIPTION VARCHAR(100) NOT NULL WITH DEFAULT ''
  , AMOUNT DECIMAL(9,2) NOT NULL WITH DEFAULT 0
  , DAY_DUE INT NOT NULL DEFAULT 0
  , TIME_DUE TIME NOT NULL WITH DEFAULT CAST (CURRENT TIMESTAMP AS TIME) 
  , QUARTZ_EXP VARCHAR(150) NOT NULL WITH DEFAULT ''
  , FROM_DATE DATE WITH DEFAULT NULL
  , TO_DATE DATE WITH DEFAULT NULL
  , PAY_TO VARCHAR(150) NOT NULL WITH DEFAULT ''
  , ENTITY_CODE VARCHAR(20) NOT NULL WITH DEFAULT '' 
  , ACTIVE INT NOT NULL WITH DEFAULT 1
  , USERNAME VARCHAR(50) NOT NULL WITH DEFAULT ''
  , CREATED_ON TIMESTAMP NOT NULL WITH DEFAULT NULL
  , CREATED_BY VARCHAR(50) NOT NULL WITH DEFAULT ''
  , MODIFIED_ON TIMESTAMP NOT NULL WITH DEFAULT NULL
  , MODIFIED_BY VARCHAR(50) NOT NULL WITH DEFAULT ''
  ,PRIMARY KEY ( ID ) );