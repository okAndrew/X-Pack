package com.epam.lab.controller.web.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.epam.lab.controller.services.SignInService;
import com.epam.lab.model.User;

@WebServlet("/signin")
public class SignInServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String SIGNIN_JSP = "WEB-INF/jsp/signin.jsp";
	private static final String USER_PAGE = "userpage";
	static Logger logger = Logger.getLogger(SignInServlet.class);

	public SignInServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher requestDispatcher = request
				.getRequestDispatcher(SIGNIN_JSP);
		requestDispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = null;
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		SignInService signIn = new SignInService();
		User user = signIn.signIn(email, password);

		if (user != null) {
			if (user.getIsActivated()) {
				HttpSession session = request.getSession(false);
				session.setAttribute("userid", user.getId());
				session.setAttribute("userRole", user.getRole());
				session.setAttribute("userLogin", user.getLogin());
				response.sendRedirect(USER_PAGE);
			} else {
				request.setAttribute("message",
						"You is not activated. Please check you email");
				dispatcher = request.getRequestDispatcher(SIGNIN_JSP);
				dispatcher.forward(request, response);
			}
		} else {
			request.setAttribute("message", "Check email/password");
			dispatcher = request.getRequestDispatcher(SIGNIN_JSP);
			dispatcher.forward(request, response);
		}

	}

}
