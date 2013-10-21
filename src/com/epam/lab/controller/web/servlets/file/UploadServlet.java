package com.epam.lab.controller.web.servlets.file;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import com.epam.lab.controller.services.file.UserFileUploader;

@WebServlet("/upload")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger
			.getLogger(UploadServlet.class);
	private static final String USER_PAGE = "userpage";

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if (ServletFileUpload.isMultipartContent(request)) {
			try {
				HttpSession session = request.getSession(false);
				long folderId = (long) session.getAttribute("folderid");
				ServletFileUpload upload = new ServletFileUpload(
						new DiskFileItemFactory());
				upload.setHeaderEncoding("UTF-8");
				List<FileItem> items = upload.parseRequest(request);
				UserFileUploader uploader = new UserFileUploader(folderId);
				uploader.uploadFiles(items);
			} catch (FileUploadException e) {
				logger.error(e);
				e.printStackTrace();
			}
		}
		response.sendRedirect(USER_PAGE);
	}
}