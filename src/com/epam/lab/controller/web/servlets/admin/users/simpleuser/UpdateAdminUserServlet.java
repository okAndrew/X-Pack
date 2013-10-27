package com.epam.lab.controller.web.servlets.admin.users.simpleuser;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.lab.controller.services.user.UserServiceImpl;
import com.epam.lab.controller.utils.MD5Encrypter;
import com.epam.lab.model.Role;
import com.epam.lab.model.User;

@WebServlet("/updateUser")
public class UpdateAdminUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ADMIN_USER_JSP = "WEB-INF/jsp/admin/users/simpleUser/adminUser.jsp";
	private static final String MODAL_ADMIN_USER_JSP = "WEB-INF/jsp/admin/users/simpleUser/modalAdminUser.jsp";

	public UpdateAdminUserServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = null;
		int userId = Integer.parseInt(request.getParameter("userIdHolder"));
		String userLogin = request.getParameter("userLogin");// check
		String userEmail = request.getParameter("userEmail");// check
		boolean activated = Boolean.parseBoolean(request
				.getParameter("userActivation"));
		Role role = Role.findByName(request.getParameter("userRole"));

		UserServiceImpl userService = new UserServiceImpl();
		if (userService.checkEmailById(userEmail,userId)) {
			if (userService.ckeckLoginById(userLogin,userId)) {
				User user = userService.get(userId).setLogin(userLogin)
						.setEmail(userEmail).setIsActivated(activated)
						.setRole(role);

				userService.update(user);
				User user1 = userService.get(userId);
				request.setAttribute("user", user1);
				request.getRequestDispatcher(ADMIN_USER_JSP).forward(request,
						response);
			} else {
				request.setAttribute("message", "Login already exists");
				dispatcher = request.getRequestDispatcher("adminUser?userid="+userId);
				dispatcher.forward(request, response);
			}
		} else {
			request.setAttribute("message", "Email already exists");
			dispatcher = request.getRequestDispatcher("adminUser?userid="+userId);
			dispatcher.forward(request, response);
		}

	}
}
/*
 * /** RequestDispatcher dispatcher = null; String email =
 * request.getParameter("email"); String password =
 * request.getParameter("password");
 * 
 * MD5Encrypter md5 = new MD5Encrypter(); UserServiceImpl service = new
 * UserServiceImpl(); User user = service.getUser(email, md5.encrypt(password));
 * 
 * if (email != null && password != null) { if (user != null) { if
 * (user.getIsActivated()) { HttpSession session = request.getSession();
 * session.setAttribute("userid", user.getId());
 * session.setAttribute("userRole", user.getRole());
 * session.setAttribute("user", user); if (user.getRole().equals(Role.USER)) {
 * response.sendRedirect(USER_PAGE); } else if
 * (user.getRole().equals(Role.ADMIN)) { response.sendRedirect(ADMIN_HOME); } }
 * else { request.setAttribute("message",
 * "You is not activated. Please check you email"); dispatcher =
 * request.getRequestDispatcher(SIGNIN_JSP); dispatcher.forward(request,
 * response); } } else { request.setAttribute("message",
 * "Error! Check you email and password"); dispatcher =
 * request.getRequestDispatcher(SIGNIN_JSP); dispatcher.forward(request,
 * response); }/ }
 * 
 * }
 */
