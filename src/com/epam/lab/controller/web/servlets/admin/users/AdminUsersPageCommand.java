package com.epam.lab.controller.web.servlets.admin.users;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AdminUsersPageCommand {

	String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException;
}
