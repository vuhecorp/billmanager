/*
  Scheduled to run on a monthly basis to 
  create the user's monthly bills  as
  configured in the BILL_ITEM_TEMPLATE 
  table. 

  Related: MONTHLY_TRIGGER

  Notes: First, run the update job. The update job will catch any 
         Bills created directly by the user, and create all remaining bill items.
         Second, Run THIS create job, this job should exclude any bills that have been 
         created for the specified bill cycle (start - end dates), the bill items for those will 
         have been created by the UPDATE job. 
*/

-- UPDATE ANY BILLS THAT HAVE BEEN DIRECTLY CREATED BY THE USER BEFORE THIS JOB RUNS --

  MERGE INTO BILL
  USING (
    SELECT * FROM V_CREATED_BILL WHERE RECURRING_CODE = 'MONTH'
    ) CREATED
  ON BILL.USERNAME = CREATED.USERNAME
  WHEN MATCHED AND BILL.CYCLE_TYPE = 'MONTH' THEN
     UPDATE SET
          BILL.MODIFIED_BY = 'MONTHLY JOB'
        , BILL.MODIFIED_ON = CURRENT TIMESTAMP;

--CREATE ALL BILL PENDING CREATION --

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
         , 'MONTHLY JOB'
         , CURRENT TIMESTAMP
         , 'MONTHLY JOB'
         FROM  V_PENDING_BILL 
         WHERE RECURRING_CODE = 'MONTH';

