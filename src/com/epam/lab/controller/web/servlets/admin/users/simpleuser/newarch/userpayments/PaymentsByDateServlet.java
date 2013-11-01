package com.epam.lab.controller.web.servlets.admin.users.simpleuser.newarch.userpayments;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.lab.controller.services.payment.PaymentServiceImpl;
import com.epam.lab.controller.utils.TimeStampManager;
import com.epam.lab.model.Payment;

@WebServlet("/paymentsByDate")
public class PaymentsByDateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PaymentsByDateServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = null;
		PaymentServiceImpl psevrive = new PaymentServiceImpl();
		HttpSession session = request.getSession(false);
		long userId = (long) session.getAttribute("adminUserid");
		String enddateRequest = request.getParameter("endDate");
		String startdateRequest = request.getParameter("startDate");

		if (startdateRequest != "") {
			List<Payment> list = psevrive.getPayByPeriod(userId,
					startdateRequest, enddateRequest);
			request.setAttribute("listPayments", list);
			request.setAttribute("notFullList", true);
			request.setAttribute("messagePeriod", "Your payments for period: "
					+ startdateRequest + " - " + enddateRequest);
		} else {
			request.setAttribute("message", "Please select period");
		}

		dispatcher = request.getRequestDispatcher("adminUserPayments");
		dispatcher.forward(request, response);
	}
}
