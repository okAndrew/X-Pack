package com.epam.lab.controller.services;

import java.io.File;
import java.util.Calendar;

public class FileService {
	public static final String PATH = "D:/files/";
	public static final int MAX_FILES = 5;
	
	public void run() {
		getFolder();
	}
	
	/*
	 * return current month/year String for folder (201311, 201312, 20141 ...)
	 */
	private static String getCurDatePath() {
		Calendar now = Calendar.getInstance();
		
		int year = now.get(Calendar.YEAR);
		int month = now.get(Calendar.MONTH);
		
		StringBuilder temp = new StringBuilder().append(year).append(month);
		
		return temp.toString();
	}
	
	/*
	 * return current folder to save files
	 */
	public static File getFolder() {
		File curFolder = null;
		File path = new File(PATH + getCurDatePath());
		
		if (!path.exists()) {
			path.mkdirs();
		}
		
		File[] folders = path.listFiles();
		
		int curFolderName = 0;
		for (int i = folders.length; i > 0; i--) {
			if (folders[i-1].isDirectory()) {
				// if last folder is not valid
				try {
					curFolderName = Integer.valueOf(folders[i-1].getName());
				} catch (NumberFormatException e) {
					continue;
				}
				
				// if last folder is full else get current last valid folder
				if (folders[i-1].listFiles().length >= MAX_FILES) {
					curFolder = newIncfolder(curFolderName);
				} else {
					curFolder = folders[i-1];
				}
				
				break;
			}
		}
		
		// if month folder is empty
		if (curFolder == null) {
			curFolder = new File(path + "/" + 1);
			curFolder.mkdir();
		}
		
		return curFolder;
	}
	
	// create new increment valid folder
	private static File newIncfolder(int cur) {
		File newFolder = null;
		StringBuilder path = new StringBuilder().append(PATH).append(getCurDatePath()).append("/");
		
		while (newFolder == null) {
			File folder = new File(path.toString() + cur);
			
			//if folder !exist
			if (folder.mkdir()) {
				newFolder = folder;
			} else {
				cur++;
			}
		}
		
		return newFolder;
	}
}
