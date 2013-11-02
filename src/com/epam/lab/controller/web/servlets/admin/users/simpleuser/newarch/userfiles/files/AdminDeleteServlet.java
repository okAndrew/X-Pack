package com.epam.lab.controller.web.servlets.admin.users.simpleuser.newarch.userfiles.files;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.lab.controller.services.user.UserServiceImpl;

@WebServlet("/admindelete")
public class AdminDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ADMIN_USER_FILES = "adminUserFiles";

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		UserServiceImpl service = new UserServiceImpl();
		Long userId = (Long) request.getSession().getAttribute("adminUserid");
		String messageToUser = request.getParameter("message");
		service.deleteFiles(request.getParameterValues("filelist"), userId,
				messageToUser);
		response.sendRedirect(ADMIN_USER_FILES);
	}
}