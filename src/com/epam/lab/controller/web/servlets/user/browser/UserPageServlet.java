package com.epam.lab.controller.web.servlets.user.browser;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.lab.controller.services.user.UserServiceImpl;

@WebServlet("/userpage")
public class UserPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String USER_JSP = "WEB-INF/jsp/user/user.jsp";

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		processRequest(req, resp);
	}

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		UserServiceImpl userService = new UserServiceImpl();
		HttpSession session = request.getSession(false);
		long userId = (long) session.getAttribute("userid");
		boolean isBanned = userService.isBanned(userId);
		request.setAttribute("isbanned", isBanned);
		request.getRequestDispatcher("BrowserContent").include(request,
				response);
		request.getRequestDispatcher(USER_JSP).forward(request, response);
	}
}