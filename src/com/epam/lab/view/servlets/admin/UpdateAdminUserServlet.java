package com.epam.lab.view.servlets.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.lab.controller.services.PaymentService;
import com.epam.lab.controller.services.UserService;
import com.epam.lab.model.Payment;
import com.epam.lab.model.User;

@WebServlet("/updateUser")
public class UpdateAdminUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ADMIN_USER_JSP = "WEB-INF/jsp/admin/adminUser.jsp";
	private static final Logger logger = Logger
			.getLogger(UpdateAdminUserServlet.class);

	public UpdateAdminUserServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// int userId =
		// Integer.parseInt(request.getParameter("userid"));
		UserService service = new UserService();
//		PrintWriter out = response.getWriter();
//		out.print("hello");
//		User user = service.getUserById(2);
//		request.setAttribute("user", user);

		request.getRequestDispatcher(ADMIN_USER_JSP).forward(request, response);
	}

}
