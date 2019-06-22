
CREATE OR REPLACE VIEW "V_BILL_ITEM_SUMMARY" 
(     "ID"
    , "TEMPLATE_ID"
    , "BILL_ID"
    , "TYPE_CODE"
    , "RECURRING_CODE"
    , "YEAR"
    , "MONTH"
    , "WEEK"
    , "DAY"
    , "NAME"
    , "DESCRIPTION"
    , "BILLED_AMOUNT"
    , "PAID_AMOUNT"
    , "DATE_DUE"
    , "TIME_DUE"
    , "PAY_TO"
    , "ENTITY_CODE"
    , "STATUS"
    , "STATUS_DATE"
    , "ACTIVE"
    , "USERNAME"
    , "CREATED_ON"
    , "CREATED_BY"
    , "MODIFIED_ON"
    , "MODIFIED_BY") AS
SELECT 
      ID
    , TEMPLATE_ID
    , BILL_ID
    , TYPE_CODE
    , RECURRING_CODE
    , YEAR
    , MONTH
    , WEEK
    , DAY
    , NAME
    , DESCRIPTION
    , BI.AMOUNT AS BILLED_AMOUNT
    , BIT.TOTAL_PAID AS PAID_AMOUNT
    , DATE_DUE
    , TIME_DUE
    , PAY_TO
    , ENTITY_CODE
    , STATUS
    , STATUS_DATE
    , ACTIVE
    , USERNAME
    , CREATED_ON
    , CREATED_BY
    , MODIFIED_ON
    , MODIFIED_BY
    FROM BILL_ITEM AS BI
    INNER JOIN V_BILL_ITEM_TOTAL AS BIT 
    ON BI.ID = BIT.ITEM_ID
    WHERE BI.ACTIVE = 1;

