package com.epam.lab.controller.web.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.lab.controller.services.payment.PaymentServiceImpl;

@WebServlet("/CreatePaymentServlet")
public class CreatePaymentServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String PRICING_JSP = "pricing";

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(PRICING_JSP);
		HttpSession session = request.getSession(false);
		
		long userId = Long.valueOf(session.getAttribute("userid").toString());
		int months = Integer.valueOf(request.getParameter("months"));
		long tariffId = Long.valueOf(request.getParameter("tariffId"));
		
		new PaymentServiceImpl().pay(tariffId, months, userId);
		
		requestDispatcher.forward(request, response);
	}
}
