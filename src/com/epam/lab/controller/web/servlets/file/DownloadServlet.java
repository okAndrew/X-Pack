package com.epam.lab.controller.web.servlets.file;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSessionEvent;

import com.epam.lab.controller.services.file.UserFileServiceImpl;
import com.epam.lab.controller.services.user.DownloadService;

@WebServlet("/download")
public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		processRequest(req, resp);
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserFileServiceImpl service = new UserFileServiceImpl();
		DownloadService downloadService = new DownloadService();
		File file = null;
		String fileName = null;
		if (request.getParameter("fileid") == null) {
			String[] filesIds = request.getParameterValues("files");
			String[] foldersIds = request.getParameterValues("folders");
			file = service.getArchive(filesIds, foldersIds);
			fileName = file.getName();
		} else {
			long fileId = Long.valueOf(request.getParameter("fileid"));
			com.epam.lab.model.UserFile f = service.get(fileId);
			String filePath = f.getPath() + File.separator + f.getName();
			file = new File(filePath);
			fileName = f.getNameIncome();
		}
		
		String userId = request.getSession(false).getAttribute("userid").toString();
		response.setHeader("Content-Disposition", "attachment; filename="
				+ URLEncoder.encode(fileName, "UTF-8"));
		response.setContentType("application/octet-stream; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentLength((int) file.length());
		ServletOutputStream os = response.getOutputStream();
		downloadService.download(file, os, userId);
	}
}
