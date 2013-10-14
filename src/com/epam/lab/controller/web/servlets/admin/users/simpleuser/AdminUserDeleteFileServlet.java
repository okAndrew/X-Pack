package com.epam.lab.controller.web.servlets.admin.users.simpleuser;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.epam.lab.controller.services.PaymentService;
import com.epam.lab.controller.services.UserService;
import com.epam.lab.controller.services.file.FileService;
import com.epam.lab.controller.services.folder.FolderService;
import com.epam.lab.model.Payment;
import com.epam.lab.model.User;

@WebServlet("/adminUserDeleteFile")
public class AdminUserDeleteFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ADMIN_USER_FILES_JSP = "adminUserFiles";
	private static final Logger logger = Logger
			.getLogger(AdminUserDeleteFileServlet.class);

	public AdminUserDeleteFileServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher;
		HttpSession session = request.getSession(false);
		long userId = (long) session.getAttribute("userid");
		String[] rs = request.getParameterValues("files");
		String[] rs2 = request.getParameterValues("folders");
		if (rs != null) {
			for (int i = 0; i < rs.length; i++) {
				FileService.delete(Integer.parseInt(rs[i]));
			}
		}
		if (rs2 != null) {
			for (int i = 0; i < rs2.length; i++) {
				FolderService.delete(Integer.parseInt(rs2[i]), userId);
			}
		}
		if (rs == null && rs2 == null) {
			request.setAttribute("message",
					"Error! Please select files to delete");
			dispatcher = request.getRequestDispatcher(ADMIN_USER_FILES_JSP);
		}

		dispatcher = request.getRequestDispatcher(ADMIN_USER_FILES_JSP);
		dispatcher.forward(request, response);
	}
}
