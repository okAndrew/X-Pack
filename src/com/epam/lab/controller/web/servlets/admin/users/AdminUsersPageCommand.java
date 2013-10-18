package com.epam.lab.controller.web.servlets.admin.users;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AdminUsersPageCommand {

	public String execute(HttpServletRequest request, HttpServletResponse response);
}
