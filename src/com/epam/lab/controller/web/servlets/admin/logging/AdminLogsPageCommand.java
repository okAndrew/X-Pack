package com.epam.lab.controller.web.servlets.admin.logging;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AdminLogsPageCommand {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}