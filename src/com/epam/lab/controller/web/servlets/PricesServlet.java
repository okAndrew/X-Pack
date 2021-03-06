package com.epam.lab.controller.web.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.lab.controller.services.pricing.PricingServiceImpl;

@WebServlet("/pricing")
public class PricesServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final String PRICES_JSP = "WEB-INF/jsp/pricing.jsp";
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(PRICES_JSP);
		request = new PricingServiceImpl().initialize(request);
		requestDispatcher.forward(request, response);
	}

}
