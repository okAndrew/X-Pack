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
		long time = timestamp.getTime() + 1000 * 60 * 60 * 24
				* calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		timestamp.setTime(time);
		return timestamp;
	}

}
