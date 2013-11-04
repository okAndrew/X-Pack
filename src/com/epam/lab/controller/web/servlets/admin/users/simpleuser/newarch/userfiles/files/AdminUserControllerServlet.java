package com.epam.lab.controller.web.servlets.admin.users.simpleuser.newarch.userfiles.files;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/adminUsercontroller")
public class AdminUserControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String DOWNLOAD_SERVLET = "download";
	private static final String DELETE_SERVLET = "admindelete";
	private static final String SEARCH_SERVLET = "adminsearch";
	private static final String ADMIN_USER_FILES = "adminUserFiles";

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("search") != null) {
			request.getRequestDispatcher(SEARCH_SERVLET).forward(request,
					response);
		} else if (request.getParameterValues("files") == null) {
			request.setAttribute("message", "Unchecked checkboxes");
			request.getRequestDispatcher(ADMIN_USER_FILES).forward(request,
					response);
		} else if (request.getParameter("download") != null) {
			request.getRequestDispatcher(DOWNLOAD_SERVLET).forward(request,
					response);
		} else if (request.getParameter("delete") != null) {
			request.getRequestDispatcher(DELETE_SERVLET).forward(request,
					response);
		}
	}

}
