package com.epam.lab.controller.web.servlets.user.browser;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/userfoldernav")
public class FoldersNavigationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String USER_PAGE = "userpage";
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long folderId = Long.valueOf(request.getParameter("folderId"));
		HttpSession session = request.getSession(false);
		session.setAttribute("folderId", folderId);
		response.sendRedirect(USER_PAGE);
	}

}
