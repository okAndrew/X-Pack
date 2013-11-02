package com.epam.lab.controller.web.servlets.admin.users.simpleuser.newarch.userpayments;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.lab.controller.services.payment.PaymentServiceImpl;
import com.epam.lab.controller.web.servlets.admin.users.simpleuser.newarch.AdminSimpleUserPageCommand;
import com.epam.lab.model.Payment;

public class AdminUserPaymentsCommand implements AdminSimpleUserPageCommand {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String page = null;
		HttpSession session = request.getSession();
		PaymentServiceImpl psevrive = new PaymentServiceImpl();
		List<Payment> list = psevrive.getAllPayByUserId((long) session
				.getAttribute("adminUserid"));
		request.setAttribute("listPayments", list);
		page = "adminUserPayments";
		return page;

	}

}
