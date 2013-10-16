package com.epam.lab.controller.web.servlets.admin.users.simpleuser;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.epam.lab.controller.services.PaymentService;
import com.epam.lab.controller.services.UserService;
import com.epam.lab.controller.services.file.FileService;
import com.epam.lab.controller.services.folder.FolderService;
import com.epam.lab.model.File;
import com.epam.lab.model.Folder;
import com.epam.lab.model.Payment;
import com.epam.lab.model.User;

@WebServlet("/paymentsByDate")
public class PaymentsByDateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ADMIN_USER_PAYMENTS_JSP = "WEB-INF/jsp/admin/users/simpleUser/adminUserPayments.jsp";
	private static final Logger logger = Logger
			.getLogger(PaymentsByDateServlet.class);

	public PaymentsByDateServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		PaymentService psevrive = new PaymentService();
		HttpSession session = request.getSession(false);
		long userId = (long) session.getAttribute("userid");
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

		Timestamp endDate = null;
		Timestamp startDate = null;

		if (request.getParameter("startDate") != null) {
			if (request.getParameter("endDate") != null) {
				try {
					endDate = new Timestamp(sf.parse(
							request.getParameter("endDate")).getTime());
				} catch (ParseException e) {
					e.printStackTrace();
				}
			} else {
				java.util.Date date = new java.util.Date();
				endDate = new Timestamp(date.getTime());
			}
			try {
				startDate = new Timestamp(sf.parse(
						request.getParameter("startDate")).getTime());
			} catch (ParseException e) {
				e.printStackTrace();
			}

			List<Payment> list = psevrive.getPayByPeriod(userId, startDate,
					endDate);

			request.setAttribute("listPayments", list);
			request.getRequestDispatcher(ADMIN_USER_PAYMENTS_JSP).forward(
					request, response);

		} else {
			out.print("Please select period");
			request.getRequestDispatcher(ADMIN_USER_PAYMENTS_JSP).include(
					request, response);
		}
	}
}
