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

import com.epam.lab.controller.services.UserService;
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(SIGNIN_JSP);
		requestDispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher;
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		UserService service = new UserService();
		User user = service.getUser(email, password);
		
		if (user != null) {
			HttpSession session = request.getSession();
			session.setAttribute("userid", user.getId());
			response.sendRedirect(USER_PAGE);
		} else {
			request.setAttribute("message", "Error! Check you email and password");
			dispatcher = request.getRequestDispatcher(SIGNIN_JSP);
			dispatcher.forward(request, response);
		}
	}

}
