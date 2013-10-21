package com.epam.lab.controller.web.servlets.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.lab.controller.services.file.UserFileServiceImpl;

@WebServlet("/download")
public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		UserFileServiceImpl fileService = new UserFileServiceImpl();
		long fileId = Long.valueOf(request.getParameter("fileid"));
		com.epam.lab.model.UserFile f = fileService.get(fileId);
		String filePath = f.getPath() + File.separator + f.getName();
		File file = new File(filePath);
		if (!file.exists()) {
			throw new ServletException("File doesn't exists on server.");
		}
		InputStream fis = new FileInputStream(file);
		response.setContentType("application/octet-stream; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentLength((int) file.length());
		response.setHeader("Content-Disposition", "attachment; filename="
				+ URLEncoder.encode(f.getNameIncome(), "UTF-8"));
		ServletOutputStream os = response.getOutputStream();
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
