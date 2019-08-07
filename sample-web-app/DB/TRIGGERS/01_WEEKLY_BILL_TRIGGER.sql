
CREATE OR REPLACE TRIGGER WEEKLY_TRIG
      AFTER INSERT ON BILL
      REFERENCING NEW AS NEW_BILL
      FOR EACH ROW
      WHEN (NEW_BILL.CYCLE_TYPE = 'WEEK' AND NEW_BILL.CREATED_BY = 'JOB') 
       INSERT INTO BILL_ITEM
            ( BILL_ID
            , TEMPLATE_ID
            , TYPE_CODE
            , RECURRING_CODE
            , YEAR
            , MONTH
            , WEEK
            , DAY
            , NAME
            , DESCRIPTION
            , AMOUNT
            , DATE_DUE
            , TIME_DUE
            , PAY_TO
            , STATUS
            , STATUS_DATE
            , USERNAME
            , CREATED_ON
            , CREATED_BY
            , MODIFIED_ON
            , MODIFIED_BY)
        SELECT 
               NEW_BILL.ID AS BILL_ID
             , TMP.ID  
             , TMP.TYPE_CODE
             , TMP.RECURRING_CODE
             , NEW_BILL.YEAR
             , MONTH (DATE(CURRENT TIMESTAMP + (TMP.DAY_DUE - 1)))
             , NEW_BILL.WEEK 
             , NEW_BILL.DAY 
             , TMP.NAME
             , TMP.DESCRIPTION
             , TMP.AMOUNT
             , DATE(CURRENT TIMESTAMP + (TMP.DAY_DUE - 1))
             , TMP.TIME_DUE
             , TMP.PAY_TO
             , 'DUE'
             , CURRENT TIMESTAMP
             , NEW_BILL.USERNAME
             , CURRENT TIMESTAMP
             , 'WEEKLY JOB'
             , CURRENT TIMESTAMP
             , 'WEEKLY JOB'
        FROM BILL_ITEM_TEMPLATE TMP
       		LEFT JOIN (
       			SELECT * FROM BILL_ITEM 
       				WHERE DATE_DUE BETWEEN NEW_BILL.START_DATE AND NEW_BILL.END_DATE
       		) BI
       			ON TMP.ID = BI.TEMPLATE_ID
            WHERE TMP.RECURRING_CODE = NEW_BILL.CYCLE_TYPE
            	AND TMP.USERNAME = NEW_BILL.USERNAME
            	AND TMP.ACTIVE = 1   
            	AND TMP.ID IS NOT NULL
            	AND BI.TEMPLATE_ID IS NULL; 