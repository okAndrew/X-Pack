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

import com.epam.lab.model.User;

@WebFilter(urlPatterns = { "/delete", "/downloadfiles", "/download",
		"/useredit", "/upload", "/createfolder", "/activation",
		"/ChangePasswordServlet", "/search", "/userfoldernav",
		"/usercontroller", "/userpage", "/EditEmailServlet",
		"/EditUserLoginServlet", "/settings", "/move", "/CreatePaymentServlet",
		"/locale" })
public class UserAccessFilter implements Filter {

	public UserAccessFilter() {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession(false);

		if (session != null) {
			User user = (User) session.getAttribute("user");
			if (user != null && user.getIdRole() == 1) {
				chain.doFilter(request, response);
				return;
			}
		}

		HttpServletResponse httpResponse = (HttpServletResponse) response;
		httpResponse.sendRedirect(httpRequest.getContextPath());
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
