package com.epam.lab.controller.web.servlets.admin.users;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.lab.controller.services.user.UserServiceImpl;
import com.epam.lab.controller.utils.CheckListUtil;

public class AdminActivateUsersCommand implements AdminUsersPageCommand {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		String page = null;
		String[] chekedUsers = request.getParameterValues("checkUser");
		CheckListUtil checkUtil = new CheckListUtil();
		if (!checkUtil.check(chekedUsers)) {
			request.setAttribute("message", "Please check users!!!");
			page = "adminUsersPage";
			return page;
		} else {
			UserServiceImpl service = new UserServiceImpl();
			service.activateUsers(chekedUsers);
			page = "adminUsersPage";
		}
		return page;
	}
}