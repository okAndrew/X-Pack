package com.epam.lab.controller.web.servlets.file;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.lab.controller.services.UserService;


@WebServlet("/deletefile")
public class DeleteFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String USER_PAGE = "userpage";

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher;
		HttpSession session = request.getSession(false);
		long userId = (long) session.getAttribute("userid");
		String[] rs2 = request.getParameter("folders").split(",");
		String[] rs = request.getParameter("files").split(",");
		UserService service = new UserService();
		service.deleteFilesAndFolders(rs, rs2, userId);
		// if (rs == null && rs2 == null) {
		// request.setAttribute("message",
		// "Error! Please select files to delete");
		// dispatcher = request.getRequestDispatcher(USER_PAGE);
		// }

		response.sendRedirect(USER_PAGE);
		// dispatcher = request.getRequestDispatcher(USER_PAGE);
		// dispatcher.forward(request, response);
	}
}