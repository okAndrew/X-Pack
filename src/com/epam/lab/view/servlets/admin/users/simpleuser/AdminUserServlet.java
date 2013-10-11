package com.epam.lab.view.servlets.admin.users.simpleuser;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.epam.lab.controller.services.PaymentService;
import com.epam.lab.controller.services.UserService;
import com.epam.lab.model.Payment;
import com.epam.lab.model.User;

@WebServlet("/adminUser")
public class AdminUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ADMIN_USER_JSP = "WEB-INF/jsp/admin/users/simpleUser/adminUser.jsp";
	private static final Logger logger = Logger
			.getLogger(AdminUserServlet.class);

	public AdminUserServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// HttpSession session = request.getSession(false);
		//
		// int userId = Integer.parseInt(request.getParameter("userid"));
		// session.setAttribute("userid", userId);
		// UserService service = new UserService();
		// PaymentService psevrive = new PaymentService();
		//
		// User user = service.getUserById(userId);// userId
		// List<Payment> list = psevrive.getAllPayByUserId(userId);// userId
		//
		// request.setAttribute("user", user);
		// request.setAttribute("listPayments", list);
		//
		// request.getRequestDispatcher(ADMIN_USER_JSP).forward(request,
		// response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		PaymentService psevrive = new PaymentService();

		int userId = Integer.parseInt(request.getParameter("userid"));
		session.setAttribute("userid", userId);

		User user = UserService.getUserById(userId);
		List<Payment> list = psevrive.getAllPayByUserId(userId);// userId

		request.setAttribute("user", user);
		request.setAttribute("listPayments", list);

		request.getRequestDispatcher(ADMIN_USER_JSP).forward(request, response);
	}

}
