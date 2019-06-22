package com.hersa.sample.app.utils;
import java.util.Calendar;
import java.util.Date;

public class BillManagerUtils {

	public static int getIntMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int month = cal.get(Calendar.MONTH) + 1;
		return month;
	}
}
