
CREATE OR REPLACE TRIGGER WEEKLY_UPDATE_TRIG
      AFTER UPDATE ON BILL
      REFERENCING NEW AS UPDATED_BILL
      FOR EACH ROW
      WHEN (UPDATED_BILL.CYCLE_TYPE = 'WEEK' AND UPDATED_BILL.MODIFIED_BY = 'WEEKLY JOB') 
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
               UPDATED_BILL.ID AS BILL_ID
             , TMP.ID  
             , TMP.TYPE_CODE
             , TMP.RECURRING_CODE
             , UPDATED_BILL.YEAR
             , MONTH (DATE(CURRENT TIMESTAMP + (TMP.DAY_DUE - 1)))
             , UPDATED_BILL.WEEK 
             , UPDATED_BILL.DAY 
             , TMP.NAME
             , TMP.DESCRIPTION
             , TMP.AMOUNT
             , DATE(CURRENT TIMESTAMP + (TMP.DAY_DUE - 1))
             , TMP.TIME_DUE
             , TMP.PAY_TO
             , 'DUE'
             , CURRENT TIMESTAMP
             , UPDATED_BILL.USERNAME
             , CURRENT TIMESTAMP
             , 'WEEKLY JOB'
             , CURRENT TIMESTAMP
             , 'WEEKLY JOB'
        FROM BILL_ITEM_TEMPLATE TMP
       		LEFT JOIN (
       			SELECT * FROM BILL_ITEM 
       				WHERE DATE_DUE BETWEEN UPDATED_BILL.START_DATE AND UPDATED_BILL.END_DATE
       		) BI
       			ON TMP.ID = BI.TEMPLATE_ID
            WHERE TMP.RECURRING_CODE = UPDATED_BILL.CYCLE_TYPE
            	AND TMP.USERNAME = UPDATED_BILL.USERNAME
            	AND TMP.ACTIVE = 1   
            	AND TMP.ID IS NOT NULL
            	AND BI.TEMPLATE_ID IS NULL; 