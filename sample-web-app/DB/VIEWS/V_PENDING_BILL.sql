/*
    Used by the scheduled JOBs 
        
        1.) MONTLY_BILL_JOB
        2.) WEEKLY_BILL_JOB 
    
    at run time to calculate bill that are pending creation,
    based off of how many and which bills have been created
    per user.  
 */
CREATE OR REPLACE VIEW V_PENDING_BILL (
      USERNAME
    , RECURRING_CODE


) AS
SELECT 
	  PENDING.USERNAME
	, PENDING.RECURRING_CODE


FROM V_BILL_TEMPLATE_TYPE PENDING
    LEFT JOIN (
        SELECT * FROM BILL 
            WHERE YEAR  =  YEAR (CURRENT TIMESTAMP)
            AND MONTH   =  MONTH (CURRENT TIMESTAMP)
            AND START_DATE = CASE WHEN CYCLE_TYPE = 'MONTH'
                                THEN FIRST_DAY(CURRENT_DATE)
                            ELSE DATE(CURRENT_DATE) END	
            AND END_DATE   = CASE WHEN CYCLE_TYPE = 'MONTH' 
                                THEN LAST_DAY(CURRENT_DATE)
                            ELSE DATE(CURRENT_DATE + 6 DAYS)
                            END
    ) CREATED 
ON PENDING.USERNAME = CREATED.USERNAME
	AND PENDING.RECURRING_CODE = CREATED.CYCLE_TYPE
WHERE PENDING.USERNAME IS NOT NULL
    AND CREATED.USERNAME IS NULL;

 COMMENT ON TABLE V_PENDING_BILL
     IS 'LISTS ALL USERS FOR WHICH BILLS HAVE NOT YET BEEN CREATED AT TIME OF JOB';
	    