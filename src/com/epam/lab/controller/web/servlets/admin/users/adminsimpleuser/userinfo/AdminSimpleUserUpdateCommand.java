package com.epam.lab.controller.web.servlets.admin.users.adminsimpleuser.userinfo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.lab.controller.services.user.UserServiceImpl;
import com.epam.lab.controller.web.servlets.admin.users.adminsimpleuser.AdminSimpleUserPageCommand;
import com.epam.lab.model.Role;

public class AdminSimpleUserUpdateCommand implements AdminSimpleUserPageCommand {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String page = null;
		String errmessage = null;
		int userId = Integer.parseInt(request.getParameter("userIdHolder"));
		String userLogin = request.getParameter("userLogin");
		String userEmail = request.getParameter("userEmail");
		boolean activated = Boolean.parseBoolean(request
				.getParameter("userActivation"));
		boolean banned = Boolean.parseBoolean(request
				.getParameter("userBanned"));
		Role role = Role.findByName(request.getParameter("userRole"));
		if (userId == (long) session.getAttribute("userid")
				&& role.equals(Role.USER)) {
			errmessage = "Admin_cann't_set_USER_role_to_himself";
		} else {
			UserServiceImpl userService = new UserServiceImpl();
			errmessage = userService.checkUpdate(userEmail, userId, userLogin,
					activated, banned, role);
		}
		request.setAttribute("message", errmessage);
		page = "adminUser?page=adminUserInfo";
		return page;
	}

}
