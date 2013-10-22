package com.epam.lab.controller.services.user;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DownloadService {
	public void download(File file, OutputStream os) throws IOException {
		InputStream fis = new FileInputStream(file);
		byte[] bufferData = new byte[1024];
		int read = 0;
		while ((read = fis.read(bufferData)) != -1) {
			os.write(bufferData, 0, read);
		}
		os.flush();
		os.close();
		fis.close();
	}
}
