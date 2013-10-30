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

@WebFilter(urlPatterns = { "/delete", "/downloadfiles", "/download",
		"/useredit", "/upload", "/createfolder", "/ChangePasswordServlet",
		"/search", "/userfoldernav", "/usercontroller", "/userpage",
		"/EditEmailServlet", "/EditUserLoginServlet", "/settings", "/move",
		"/CreatePaymentServlet", "/pricing"})
public class UserVerifyFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession(false);
		if (session != null) {
			Object userid = session.getAttribute("userid");
			if (userid != null) {
				Object role = session.getAttribute("userRole");
				if (role != null
						&& (role.equals(Role.USER) || role.equals(Role.ADMIN))) {
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
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}
}
