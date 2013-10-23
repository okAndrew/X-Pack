package com.epam.lab.controller.web.servlets.admin.users.simpleuser;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.lab.controller.services.payment.PaymentServiceImpl;
import com.epam.lab.controller.utils.TimeStampManager;
import com.epam.lab.model.Payment;

@WebServlet("/paymentsByDate")
public class PaymentsByDateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ADMIN_USER_PAYMENTS_JSP = "WEB-INF/jsp/admin/users/simpleUser/adminUserPayments.jsp";

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
		PaymentServiceImpl psevrive = new PaymentServiceImpl();
		HttpSession session = request.getSession(false);
		long userId = (long) session.getAttribute("adminUserid");
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
				endDate = TimeStampManager.getFormatCurrentTimeStamp();
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
