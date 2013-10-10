package com.epam.lab.view.servlets.file;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import com.epam.lab.controller.services.file.FileUploader;
import com.epam.lab.model.User;

@WebServlet("/upload")
public class FileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger
			.getLogger(FileUploadServlet.class);

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if (ServletFileUpload.isMultipartContent(request)) {
			User user = null;
			List<String> paths = null;
			if (request.getSession(false)!=null) {
				if (request.getSession(false).getAttribute("user")!=null) {
					user = (User) request.getSession(false).getAttribute("user");
				}
			}
			try {
				ServletFileUpload upload = new ServletFileUpload(
						new DiskFileItemFactory());
				List<FileItem> items = upload.parseRequest(request);
				System.out.println(items.size());
				FileUploader uploader = new FileUploader(items, user);
				paths = uploader.run();
			} catch (FileUploadException e) {
				logger.error(e);
				e.printStackTrace();
			}
			if (user!=null) {
			}
		}
	}
}