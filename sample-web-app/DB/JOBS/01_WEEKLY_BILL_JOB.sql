
/*
  Scheduled to run on a weekly basis to 
  create the user's weekly bills as
  configured in the BILL_ITEM_TEMPLATE 
  table. 

  Related: WEEKLY_TRIGGER

  ((DAY (CURRENT TIMESTAMP) - DAYOFWEEK(CURRENT TIMESTAMP) + 13) / 7) 
*/
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
         , WEEK (CURRENT TIMESTAMP)
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