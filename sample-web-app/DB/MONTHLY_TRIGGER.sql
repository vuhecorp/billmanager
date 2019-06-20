SET SCHEMA EBILLINGDEV;
CREATE TRIGGER MONTHLY_TRIG
      AFTER INSERT ON BILL
      REFERENCING NEW AS NEW_BILL
      FOR EACH ROW
  	  WHEN (NEW_BILL.CYCLE_TYPE = 'MONTH')
      INSERT INTO BILL_ITEM
            ( BILL_ID
            , TEMPLATE_ID
            , TYPE_CODE
            , RECURRING_CODE
            , NAME
            , DESCRIPTION
            , AMOUNT
            , DATE_DUE
            , TIME_DUE
            , PAY_TO
            , STATUS
            , STATUS_DATE
            , CREATED_ON
            , CREATED_BY
            , MODIFIED_ON
            , MODIFIED_BY)
        SELECT 
               NEW_BILL.ID AS BILL_ID
             , TMP.ID  
             , TMP.TYPE_CODE
             , TMP.RECURRING_CODE
             , TMP.NAME
             , TMP.DESCRIPTION
             , TMP.AMOUNT
             , FIRST_DAY(CURRENT_DATE) + (TMP.DAY_DUE - 1) DAYS
             , TMP.TIME_DUE
             , TMP.PAY_TO
             , 'DUE'
             , CURRENT TIMESTAMP
             , CURRENT TIMESTAMP
             , 'JOB'
             , CURRENT TIMESTAMP
             , 'JOB'
            FROM BILL_ITEM_TEMPLATE TMP
            WHERE TMP.RECURRING_CODE = NEW_BILL.CYCLE_TYPE
            AND TMP.USERNAME = NEW_BILL.USERNAME
            AND TMP.ACTIVE = 1
            