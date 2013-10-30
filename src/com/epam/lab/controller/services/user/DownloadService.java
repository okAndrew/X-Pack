package com.epam.lab.controller.services.user;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;

import com.epam.lab.controller.services.statistics.traffichistory.TrafficHistoryServiceImpl;
import com.epam.lab.controller.utils.TimeStampManager;
import com.epam.lab.model.TrafficHistory;

public class DownloadService {
		
	public void download(File file, OutputStream os, String userId) throws IOException {
		InputStream fis = new FileInputStream(file);
		byte[] bufferData = new byte[1024];
		int read = 0;
		int size = 0;
		while ((read = fis.read(bufferData)) != -1) {
			os.write(bufferData, 0, read);
			size += read;
		}
		createTrafficHistory(Long.parseLong(userId), size);
		os.flush();
		os.close();
		fis.close();
	}
	
	private void createTrafficHistory(long userId, int read){
		TrafficHistory traffic = new TrafficHistory();
		Timestamp date = TimeStampManager.getFormatCurrentTimeStamp();
		traffic.setUserId(userId).setDate(date).setSize(read);
		TrafficHistoryServiceImpl traficService = new TrafficHistoryServiceImpl();
		traficService.insert(traffic);
	}
}