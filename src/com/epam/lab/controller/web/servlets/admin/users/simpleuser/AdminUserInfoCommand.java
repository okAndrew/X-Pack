package com.epam.lab.controller.web.servlets.admin.users.simpleuser;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.epam.lab.controller.services.user.UserServiceImpl;
import com.epam.lab.controller.web.servlets.admin.users.simpleuser.newarch.AdminSimpleUserPageCommand;
import com.epam.lab.model.User;

public class AdminUserInfoCommand implements AdminSimpleUserPageCommand {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String page = null;
		UserServiceImpl us = new UserServiceImpl();
		HttpSession session = request.getSession(false);
		long userId = (long) session.getAttribute("adminUserid");
		User user = us.get(userId);
		request.setAttribute("user", user);
		page = "adminUser";// adminUserInfo
		return page;
	}
}
