package com.epam.lab.controller.web.servlets.admin.users.simpleuser.newarch.userpayments;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.lab.controller.services.payment.PaymentServiceImpl;
import com.epam.lab.controller.web.servlets.admin.users.simpleuser.newarch.AdminSimpleUserPageCommand;
import com.epam.lab.model.Payment;

public class AdminUserPaymentsByDateCommand implements
		AdminSimpleUserPageCommand {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String page = null;
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		PaymentServiceImpl psevrive = new PaymentServiceImpl();
		HttpSession session = request.getSession();
		long userId = (long) session.getAttribute("adminUserid");
		String enddateRequest = request.getParameter("endDate");
		String startdateRequest = request.getParameter("startDate");
		if (startdateRequest != "") {
			List<Payment> list = psevrive.getPayByPeriod(userId,
					startdateRequest, enddateRequest);
			request.setAttribute("listPayments", list);
			request.setAttribute("notFullList", true);
			request.setAttribute("messagePeriod", "Your_payments_for_period");
			try {
				request.setAttribute("startperiod", sf.parseObject(startdateRequest));
				request.setAttribute("endperiod", sf.parseObject(enddateRequest));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
		} else {
			request.setAttribute("message", "Please_select_period");
		}
		page = "adminUserPayments";
		return page;
	}
}
