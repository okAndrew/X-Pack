package com.epam.lab.controller.web.servlets.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.lab.controller.services.SessionHistoryService;
import com.epam.lab.controller.utils.CurrentTimeStamp;
import com.epam.lab.model.SessionHistory;

@WebServlet("/signOutAdmin")
public class SignOutAdminServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public SignOutAdminServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		session.invalidate();
		response.sendRedirect("signInAdmin");
	}

}
