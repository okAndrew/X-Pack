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

	public static Timestamp addNumberOfMonth(Timestamp timestamp, int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(timestamp.getTime());
		calendar.add(Calendar.MONTH, month);
		timestamp.setTime(calendar.getTimeInMillis());
		return timestamp;
	}
	
	public static int daysBetween(Timestamp t1, Timestamp t2) {
		int diffSeconds = (int) ((t2.getTime () / 1000) - (t1.getTime () / 1000));
		return (int) (diffSeconds / (60*60*24));
	}

}
