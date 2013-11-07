package com.epam.lab.controller.web.servlets.file;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.lab.controller.services.file.UserFileService;
import com.epam.lab.controller.services.file.UserFileServiceImpl;

@WebServlet("/changepublicstate")
public class ChangePublicStateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		long fileId = Long.parseLong(request.getParameter("fileId"));
		boolean state = Boolean.parseBoolean(request.getParameter("state"));
		UserFileService service = new UserFileServiceImpl();
		service.changePublicState(fileId, state);
		if (state == true) {
			String url = getURLWithContextPath(request);
			String link = service.getLink(fileId, url);
			PrintWriter writer = response.getWriter();
			writer.print(link);
			writer.close();
		}
	}

	private static String getURLWithContextPath(HttpServletRequest request) {
		StringBuilder siteLink = new StringBuilder();
		siteLink.append(request.getScheme()).append("://")
				.append(request.getServerName()).append(":")
				.append(request.getServerPort())
				.append(request.getContextPath());
		return siteLink.toString();
	}
}
