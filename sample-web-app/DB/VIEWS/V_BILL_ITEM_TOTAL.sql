
CREATE OR REPLACE VIEW V_BILL_ITEM_TOTAL(
      ITEM_ID
    , TOTAL_PAID
)AS 
SELECT 
      BI.ID AS ITEM_ID
    , SUM(COALESCE(TRAN.AMOUNT, 0)) AS TOTAL_PAID
    FROM BILL_ITEM BI
    LEFT OUTER JOIN TRANSACTION TRAN
    ON BI.ID = TRAN.BILL_ITEM_ID
    AND TRAN.ACTIVE = 1
    GROUP BY BI.ID;

 
