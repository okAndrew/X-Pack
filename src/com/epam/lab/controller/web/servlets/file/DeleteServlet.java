package com.epam.lab.controller.web.servlets.file;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.lab.controller.services.user.UserServiceImpl;

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String USER_PAGE = "userpage";

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		long userId = (long) session.getAttribute("userid");
		if (request.getParameter("deletesingle")!=null) {
			UserServiceImpl service = new UserServiceImpl();
			String[] fileId = { request.getParameter("fileid") };
			String[] folderId = { request.getParameter("folderid") };
			service.deleteFilesAndFolders(fileId, folderId);
		} else {
			String[] foldersId = request.getParameterValues("folders");
			String[] filesId = request.getParameterValues("files");
			UserServiceImpl service = new UserServiceImpl();
			service.deleteFilesAndFolders(filesId, foldersId);
		}
		response.sendRedirect(USER_PAGE);
	}
}