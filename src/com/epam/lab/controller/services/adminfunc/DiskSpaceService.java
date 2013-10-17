package com.epam.lab.controller.services.adminfunc;

import java.io.File;

public class DiskSpaceService {
	private File disk = new File("d:");
	
	public float getFreeSpace(){
		return disk.getFreeSpace()/1024/1024/1024;
	}
	
	public float getTotalSpace(){
		return disk.getTotalSpace()/1024/1024/1024;
	}
}
