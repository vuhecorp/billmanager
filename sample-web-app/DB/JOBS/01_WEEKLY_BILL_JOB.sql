
INSERT INTO BILL (
      CYCLE_TYPE
    , YEAR
    , MONTH
    , WEEK
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
          'WEEK'
         , YEAR (CURRENT TIMESTAMP)
         , MONTH (CURRENT TIMESTAMP)
         , ((DAY (CURRENT TIMESTAMP) - DAYOFWEEK(CURRENT TIMESTAMP) + 13) / 7)
         , DATE(CURRENT_DATE)
         , DATE(CURRENT_DATE + 6 DAYS)
         , 'OPEN'
         , CURRENT TIMESTAMP
         , USERNAME
         , CURRENT TIMESTAMP
         , 'INSTALL'
         , CURRENT TIMESTAMP
         , 'INSTALL'
         FROM  V_BILL_TEMPLATE_TYPE 
         WHERE RECURRING_CODE = 'WEEK';