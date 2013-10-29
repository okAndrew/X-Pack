package com.epam.lab.controller.web.servlets.admin.users.simpleuser.files;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/adminUserfoldernav")
public class AdminFoldersNavigationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ADMIN_USER_FILES = "adminUserFiles";
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long folderId = Long.valueOf(request.getParameter("folderid"));
		HttpSession session = request.getSession(false);
		session.setAttribute("folderid", folderId);
		response.sendRedirect(ADMIN_USER_FILES);
	}

}
