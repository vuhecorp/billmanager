package com.hersa.sample.app.utils;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.time.DateUtils;
import org.joda.time.DateTime;
import org.joda.time.Days;

public class BillManagerUtils {

	public static final String RECURRING_CODE_MONTH = "MONTH";
	public static final String RECURRING_CODE_WEEK = "WEEK";
	
	public static int getIntMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int month = cal.get(Calendar.MONTH) + 1;
		return month;
	}
	
	public static int getDateDifference(Date date1, Date date2) {
		long diffInMillies = Math.abs(date2.getTime() - date1.getTime());
	    long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
	    return new Long(diff).intValue();
	}
	
	public static Date getMedianDate(Date date1 , Date date2) {
		DateTime start = new DateTime(date1);
		DateTime end = new DateTime(date2);
		
		Days days = Days.daysBetween(start, end);
		
		int diff = days.getDays();
		Date median = DateUtils.addDays(date1, diff/2);
		return median;
	}
	
	public static int getMedianDateMonth(Date date1 , Date date2) {
		DateTime dateTime1 = new DateTime(date1);
		DateTime dateTime2 = new DateTime(date2);
		
		int mo1 = dateTime1.getMonthOfYear();
		int mo2 = dateTime2.getMonthOfYear();
		
		if (mo1 == mo2) {
			return mo1;
		}
		
		//if view = day, do something else.. 
		
		
		
		
		Date median = getMedianDate(date1, date2);
		int month = getIntMonth(median);
		return month;
	}
}
