package com.epam.lab.view.servlets.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.lab.controller.services.file.FileService;

@WebServlet("/downloadfile")
public class FileDownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String USER_PAGE = "userpage";

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		long fileId = Long.valueOf(request.getParameter("fileId"));
		String filePath = FileService.getFileById(fileId).getPath()
				+ File.separator + FileService.getFileById(fileId).getName();
		File file = new File(filePath);
		
		if (!file.exists()) {
			throw new ServletException("File doesn't exists on server.");
		}
		System.out
				.println("File location on server::" + file.getAbsolutePath());
		ServletContext ctx = getServletContext();
		InputStream fis = new FileInputStream(file);
		String mimeType = ctx.getMimeType(file.getAbsolutePath());
		response.setContentType(mimeType != null ? mimeType
				: "application/octet-stream");
		response.setContentLength((int) file.length());
		response.setHeader("Content-Disposition", "attachment; filename=\""
				+ FileService.getFileById(fileId).getNameIncome() + "\"");
		ServletOutputStream os = response.getOutputStream();
		byte[] bufferData = new byte[1024];
		int read = 0;
		while ((read = fis.read(bufferData)) != -1) {
			os.write(bufferData, 0, read);
		}
		os.flush();
		os.close();
		fis.close();
		System.out.println("File downloaded at client successfully");

	}

}
