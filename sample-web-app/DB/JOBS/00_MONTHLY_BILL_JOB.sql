
INSERT INTO BILL (
      CYCLE_TYPE
    , YEAR
    , MONTH
    , START_DATE
    , END_DATE
    , STATUS
    , STATUS_DATE
    , USERNAME
    , CREATED_ON
    , CREATED_BY
    , MODIFIED_ON
    , MODIFIED_BY
)SELECT
           'MONTH'
         , YEAR (CURRENT TIMESTAMP)
         , MONTH (CURRENT TIMESTAMP)
         , FIRST_DAY(CURRENT_DATE)
         , LAST_DAY(CURRENT_DATE)
         , 'OPEN'
         , CURRENT TIMESTAMP
         , USERNAME
         , CURRENT TIMESTAMP
         , 'INSTALL'
         , CURRENT TIMESTAMP
         , 'INSTALL'
         FROM  V_BILL_TEMPLATE_TYPE 
         WHERE RECURRING_CODE = 'MONTH';