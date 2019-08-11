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
