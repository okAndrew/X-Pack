package com.epam.lab.controller.web.servlets.user.browser;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/usercontroller")
public class UserControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String DOWNLOAD_SERVLET = "downloadfiles";
	private static final String DELETE_SERVLET = "delete";
	private static final String USER_PAGE_SERVLET = "userpage";

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameterValues("folders") == null
				&& request.getParameterValues("files") == null) {
			request.setAttribute("message", "Unchecked checkboxes");
			request.getRequestDispatcher(USER_PAGE_SERVLET).forward(request,
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
