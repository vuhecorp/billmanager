SET SCHEMA EBILLINGDEV;

DROP TABLE BILL_ITEM_TEMPLATE;
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

    GRANT ALTER ON TABLE BILL_ITEM_TEMPLATE TO USER DB2ADMIN;
    GRANT CONTROL ON TABLE BILL_ITEM_TEMPLATE TO USER DB2ADMIN;
    GRANT DELETE ON TABLE BILL_ITEM_TEMPLATE TO USER DB2ADMIN;
    GRANT INDEX ON TABLE BILL_ITEM_TEMPLATE TO USER DB2ADMIN;
    GRANT INSERT ON TABLE BILL_ITEM_TEMPLATE TO USER DB2ADMIN;
    GRANT REFERENCES ON TABLE BILL_ITEM_TEMPLATE TO USER DB2ADMIN;
    GRANT SELECT ON TABLE BILL_ITEM_TEMPLATE TO USER DB2ADMIN;
    GRANT UPDATE ON TABLE BILL_ITEM_TEMPLATE TO USER DB2ADMIN;


CREATE TABLE BILL ( 
      ID BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY ( START WITH 1 INCREMENT BY 1 MINVALUE -9223372036854775808 MAXVALUE 9223372036854775807 CACHE 20 )
    , DESCRIPTOR VARCHAR(50) NOT NULL WITH DEFAULT '' -- JANUARY, WEEK 2, MOND
    , CYCLE_TYPE VARCHAR(30) NOT NULL WITH DEFAULT '' -- YEARLY, MONTH, WEEK, BIWEEK, DAILY
    , YEAR INT NOT NULL WITH DEFAULT -1 
    , MONTH INT NOT NULL WITH DEFAULT -1 -- 1 FOR JAN, 2 FOR FEB
    , WEEK INT NOT NULL WITH DEFAULT -1 -- 1 FOR WEEK ONE, 2 FOR WEEK 2
    , DAY  INT NOT NULL WITH DEFAULT -1 -- 1 FOR SUN , 2 FOR MON
    , START_DATE DATE NOT NULL
    , END_DATE DATE NOT NULL
    , STATUS VARCHAR(20) NOT NULL WITH DEFAULT '' 
    , STATUS_DATE TIMESTAMP NOT NULL 
    , ACTIVE INT NOT NULL WITH DEFAULT 1
    , USERNAME VARCHAR(50) NOT NULL WITH DEFAULT ''
    , CREATED_ON TIMESTAMP NOT NULL WITH DEFAULT NULL
    , CREATED_BY VARCHAR(50) NOT NULL WITH DEFAULT ''
    , MODIFIED_ON TIMESTAMP NOT NULL WITH DEFAULT NULL
    , MODIFIED_BY VARCHAR(50) NOT NULL WITH DEFAULT ''
    , PRIMARY KEY ( ID ) );



CREATE TABLE BILL_ITEM
(
      ID BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY ( START WITH 1 INCREMENT BY 1 MINVALUE -9223372036854775808 MAXVALUE 9223372036854775807 CACHE 20 )
    , TEMPLATE_ID BIGINT NOT NULL
    , BILL_ID BIGINT NOT NULL  
    , TYPE_CODE VARCHAR(20) NOT NULL WITH DEFAULT ''
    , RECURRING_CODE VARCHAR(20) NOT NULL WITH DEFAULT ''
    , NAME VARCHAR(50) NOT NULL WITH DEFAULT ''
    , DESCRIPTION VARCHAR(100) NOT NULL WITH DEFAULT ''
    , AMOUNT DECIMAL(9,2) NOT NULL WITH DEFAULT 0
    , DATE_DUE DATE NOT NULL WITH DEFAULT NULL
    , TIME_DUE TIME NOT NULL WITH DEFAULT NULL
    , PAY_TO VARCHAR(150) NOT NULL WITH DEFAULT ''
    , ENTITY_CODE VARCHAR(20) NOT NULL WITH DEFAULT ''
    , STATUS VARCHAR(20) NOT NULL WITH DEFAULT ''
    , STATUS_DATE TIMESTAMP NOT NULL WITH DEFAULT NULL
    , CREATED_ON TIMESTAMP NOT NULL WITH DEFAULT NULL
    , CREATED_BY VARCHAR(50) NOT NULL WITH DEFAULT ''
    , MODIFIED_ON TIMESTAMP NOT NULL WITH DEFAULT NULL
    , MODIFIED_BY VARCHAR(50) NOT NULL WITH DEFAULT ''
    , PRIMARY KEY ( ID ) );


CREATE TABLE USER
( 
    ID BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY ( START WITH 1 INCREMENT BY 1 MINVALUE -9223372036854775808 MAXVALUE 9223372036854775807 CACHE 20 )
  , USERNAME VARCHAR(50) NOT NULL WITH DEFAULT ''
  , PRIMARY KEY ( ID ) );

  CREATE TABLE RECURRING_CODE
( 
    ID BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY ( START WITH 1 INCREMENT BY 1 MINVALUE -9223372036854775808 MAXVALUE 9223372036854775807 CACHE 20 )
  , CODE VARCHAR(50) NOT NULL WITH DEFAULT ''
  , PRIMARY KEY ( ID ) );


  CREATE VIEW "EBILLINGDEV"."V_BILL_TEMPLATE_TYPE" ("USERNAME", "RECURRING_CODE", "COUNT") AS

        SELECT 
            TMP.USERNAME
            , RECURRING_CODE
            , COUNT(*) AS COUNT
            FROM BILL_ITEM_TEMPLATE TMP
            LEFT JOIN USER 
                ON USER.USERNAME = TMP.USERNAME
            LEFT JOIN RECURRING_CODE RCOD
                ON RCOD.CODE = TMP.RECURRING_CODE
            WHERE ACTIVE = 1
            GROUP BY 
                TMP.USERNAME
                , RECURRING_CODE;

