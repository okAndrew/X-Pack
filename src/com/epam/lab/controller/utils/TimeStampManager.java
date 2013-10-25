package com.epam.lab.controller.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeStampManager {

	public static Timestamp getFormatCurrentTimeStamp() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String dateTime = dateFormat.format(new Date().getTime());
		return Timestamp.valueOf(dateTime);
	}

	public static Timestamp getCurrentTime() {
		return new Timestamp(new Date().getTime());
	}

	public static Timestamp addNumberOfMonth(Timestamp timestamp, int months) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(timestamp.getTime());
		calendar.add(Calendar.MONTH, months);
		timestamp.setTime(calendar.getTimeInMillis());
		return timestamp;
	}

	public static int daysBetween(Timestamp t1, Timestamp t2) {
		int diffSeconds = (int) ((t2.getTime() / 1000) - (t1.getTime() / 1000));
		return (int) (diffSeconds / (60 * 60 * 24));
	}

	public static Timestamp getStartOfDay(Timestamp timestamp) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(timestamp.getTime());
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DATE), 0, 0, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		timestamp.setTime(calendar.getTimeInMillis());
		return timestamp;
	}

	public static Timestamp getEndOfDay(Timestamp timestamp) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(timestamp.getTime());
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DATE), 23, 59, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		timestamp.setTime(calendar.getTimeInMillis());
		return timestamp;
	}

	public static Timestamp getStartOfWeek(Timestamp timestamp) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(timestamp.getTime());
		while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
			calendar.add(Calendar.DATE, -1);
		}
		calendar.add(Calendar.WEEK_OF_MONTH, -1);
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DATE), 0, 0, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		timestamp.setTime(calendar.getTimeInMillis());
		return timestamp;
	}

	public static Timestamp getEndOfWeek(Timestamp timestamp) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(timestamp.getTime());
		while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
			calendar.add(Calendar.DATE, -1);
		}
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DATE), 23, 59, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		timestamp.setTime(calendar.getTimeInMillis());
		return timestamp;
	}

	public static Timestamp getStartOfMonth(Timestamp timestamp) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(timestamp.getTime());
		while (calendar.get(Calendar.DAY_OF_MONTH) != 1) {
			calendar.add(Calendar.DATE, -1);
		}
		calendar.add(Calendar.MONTH, -1);
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DATE), 0, 0, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		timestamp.setTime(calendar.getTimeInMillis());
		return timestamp;
	}

	public static Timestamp getEndOfMonth(Timestamp timestamp) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(timestamp.getTime());
		while (calendar.get(Calendar.DAY_OF_MONTH) != 1) {
			calendar.add(Calendar.DATE, -1);
		}
		calendar.add(Calendar.DATE, -1);
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DATE), 23, 59, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		timestamp.setTime(calendar.getTimeInMillis());
		return timestamp;
	}

}
