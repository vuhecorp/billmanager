
CREATE OR REPLACE VIEW V_CREATED_BILL (
      USERNAME
    , RECURRING_CODE
    , START_DATE
    , END_DATE
) AS
SELECT 
	  CREATED.USERNAME
	, CREATED.CYCLE_TYPE
    , CREATED.START_DATE
    , CREATED.END_DATE
FROM V_PENDING_BILL PENDING
    RIGHT JOIN (
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
WHERE PENDING.USERNAME IS NULL
    AND CREATED.USERNAME IS NOT NULL;


 COMMENT ON TABLE V_CREATED_BILL
     IS 'LISTS ALL USERS FOR WHICH BILLS HAVE BEEN CREATED AT THE TIME OF JOB';   
