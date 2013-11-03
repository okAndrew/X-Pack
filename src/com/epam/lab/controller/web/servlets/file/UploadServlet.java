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

import com.epam.lab.controller.exceptions.FileTooLargeException;
import com.epam.lab.controller.services.file.FileUploadServiceImpl;
import com.epam.lab.controller.services.user.UserServiceImpl;

@WebServlet("/upload")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(UploadServlet.class);

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if (ServletFileUpload.isMultipartContent(request)) {
			try {
				HttpSession session = request.getSession();
				Long folderId = (Long) session.getAttribute("folderid");
				Long userId = (Long) session.getAttribute("userid");
				ServletFileUpload upload = new ServletFileUpload(
						new DiskFileItemFactory());
				upload.setHeaderEncoding("UTF-8");
				UserServiceImpl userService = new UserServiceImpl();
				upload.setSizeMax(userService.getFreeSize(userId));
				List<FileItem> items = upload.parseRequest(request);
				FileUploadServiceImpl uploadService = new FileUploadServiceImpl();
				uploadService.uploadUserFiles(folderId, userId, items);
			} catch (FileUploadException | FileTooLargeException e) {
				logger.warn(e);
				e.printStackTrace();
				// 'have no free space' message
			}
		} else {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
		}
	}

}