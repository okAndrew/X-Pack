package com.epam.lab.controller.web.servlets.admin.users.simpleuser.newarch.userpayments;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.lab.controller.services.payment.PaymentServiceImpl;
import com.epam.lab.model.Payment;

@WebServlet("/adminUserPayments")
public class AdminUserPaymentsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String ADMIN_USER_PAYMENTS_JSP = "WEB-INF/jsp/admin/users/simpleUser/adminUserPayments.jsp";

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher requestDispatcher = request
				.getRequestDispatcher(ADMIN_USER_PAYMENTS_JSP);
		requestDispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher requestDispatcher = request
				.getRequestDispatcher(ADMIN_USER_PAYMENTS_JSP);
		requestDispatcher.forward(request, response);
	}

}
