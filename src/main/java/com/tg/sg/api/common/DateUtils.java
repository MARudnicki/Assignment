package com.tg.sg.api.common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	public static String getDateInFormat(String dateString, String format) {
		try {
			DateFormat dateFormat = new SimpleDateFormat(Constant.BUS_CLIENT_DF);
			Date date = dateFormat.parse(dateString);
			DateFormat formatter = new SimpleDateFormat(format);
			String dateStr = formatter.format(date);
			return dateStr;
		} catch (Exception e) {

		}
		return "";
	}

	public static String getMilliSecondsInFormat(String milliSeconds, String format) {
		try {
			DateFormat formatter = new SimpleDateFormat(format);
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(Long.parseLong(milliSeconds));
			return formatter.format(calendar.getTime());
		} catch (Exception e) {

		}
		return "";
	}

}
