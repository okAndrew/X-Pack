package com.epam.lab.view.servlets.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
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

@WebServlet("/adminUserInfo")
public class AdminUserInfoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String ADMIN_USER_INFO_JSP = "WEB-INF/jsp/admin/adminUserInfo.jsp";
	private static final Logger logger = Logger
			.getLogger(AdminUserInfoServlet.class);

	public AdminUserInfoServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		UserService service = new UserService();
		PaymentService psevrive = new PaymentService();

		User user = service.getUserById(5);// userId
		List<Payment> list = psevrive.getAllPayByUserId(3);// userId

		request.setAttribute("user", user);
		request.setAttribute("listPayments", list);
		RequestDispatcher requestDispatcher = request
				.getRequestDispatcher(ADMIN_USER_INFO_JSP);
		requestDispatcher.forward(request, response);
	}

}
