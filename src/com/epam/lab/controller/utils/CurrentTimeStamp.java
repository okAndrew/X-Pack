package com.epam.lab.controller.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CurrentTimeStamp {

	public static Timestamp getCurrentTimeStamp() {
		Calendar cal = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String dateTime = dateFormat.format(cal.getTime());
		Timestamp timestamp = Timestamp.valueOf(dateTime);

		return timestamp;
	}

	public static Timestamp addMonth(Timestamp timestamp) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(timestamp.getTime());
		calendar.add(Calendar.MONTH, 1);
		timestamp.setTime(calendar.getTimeInMillis());
		return timestamp;
	}

}
