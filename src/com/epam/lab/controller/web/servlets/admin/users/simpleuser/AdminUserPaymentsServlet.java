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
import com.epam.lab.controller.services.PaymentService;
import com.epam.lab.model.Payment;

@WebServlet("/adminUserPayments")
public class AdminUserPaymentsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String ADMIN_USER_PAYMENTS_JSP = "WEB-INF/jsp/admin/users/simpleUser/adminUserPayments.jsp";

	public AdminUserPaymentsServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);

		PaymentService psevrive = new PaymentService();

		List<Payment> list = psevrive.getAllPayByUserId((long) session
				.getAttribute("adminUserid"));

		request.setAttribute("listPayments", list);

		RequestDispatcher requestDispatcher = request
				.getRequestDispatcher(ADMIN_USER_PAYMENTS_JSP);
		requestDispatcher.forward(request, response);
	}

}
