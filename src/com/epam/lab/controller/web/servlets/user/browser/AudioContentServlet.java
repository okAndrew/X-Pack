package com.epam.lab.controller.web.servlets.user.browser;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.lab.controller.services.file.UserFileServiceImpl;

@WebServlet("/audiocontent")
public class AudioContentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		UserFileServiceImpl fileService = new UserFileServiceImpl();

		long fileId = fileService.getByName(request.getParameter("name"))
				.getId();
		String audioSRC = fileService.getLink(fileId);
		PrintWriter writer = response.getWriter();
		writer.write(audioSRC);
	}
}