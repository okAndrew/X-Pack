package com.epam.lab.controller.web.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.lab.model.Role;

/**
 * Servlet Filter implementation class UserVertifyFilter
 */
@WebFilter(urlPatterns = { "/adminUsersPage", "/adminPage", "/signOutAdmin",
		"/employeeController", "/adminUserDeleteFile",
		"/userEmployeeController", "/adminUserFiles", "/adminUserInfo",
		"/adminUserPayments", "/adminUserSearchFiles", "/adminUser",
		"/paymentsByDate", "/updateUser", "/addTariff", "/adminTarrifsPage",
		"/tariffsController", "/adminFilesPage", "/adminLoggingPage"})

public class AdminVerifyFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession(false);
		System.out.println("privet");
		if (session != null) {
			Object userid = session.getAttribute("userid");
			if (userid != null) {
				Object role = session.getAttribute("userRole");
				if (role != null && role.equals(Role.ADMIN)) {
					chain.doFilter(request, response);
					return;
				}
			}
		}
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		httpResponse.sendRedirect("signin");
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}
}
