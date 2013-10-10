package com.epam.lab.view.servlets.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/userfoldernav")
public class UserFolderNavigation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long folderId = Long.valueOf(request.getParameter("folderId"));
		request.getSession().setAttribute("folderId", folderId);
		request.getRequestDispatcher("Test").forward(request, response);
	}

}
