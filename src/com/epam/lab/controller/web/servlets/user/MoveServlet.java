package com.epam.lab.controller.web.servlets.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.lab.controller.services.user.UserServiceImpl;

@WebServlet("/move")
public class MoveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String USER_PAGE = "userpage";

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		long userId = (long) session.getAttribute("userid");
		if (request.getParameter("idTarget")!=null && !request.getParameter("idTarget").equals("") && request.getParameterValues("moveable")!=null
				&& !request.getParameterValues("moveable")[0].equals("")) {
			long idTarget = Long.parseLong(request.getParameter("idTarget"));
			String[] moveAble = request.getParameterValues("moveable");
			UserServiceImpl service = new UserServiceImpl();
			service.moveFilesAndFolders(moveAble, idTarget, userId);
		}
		response.sendRedirect(USER_PAGE);
	}
}