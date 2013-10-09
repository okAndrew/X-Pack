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
import com.epam.lab.model.Payment;

@WebServlet("/adminUserTariffs")
public class AdminUserTariffsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String ADMIN_USER_TARIFFS_JSP = "WEB-INF/jsp/admin/adminUserTariffs.jsp";
	private static final Logger logger = Logger
			.getLogger(AdminUserTariffsServlet.class);

	public AdminUserTariffsServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		PaymentService psevrive = new PaymentService();

		List<Payment> list = psevrive.getAllPayByUserId(3);// userId

		request.setAttribute("listPayments", list);

		RequestDispatcher requestDispatcher = request
				.getRequestDispatcher(ADMIN_USER_TARIFFS_JSP);
		requestDispatcher.forward(request, response);
	}

}
