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

@WebServlet("/showlink")
public class ShowLinkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		long fileId = Long.parseLong(request.getParameter("fileId"));
		UserFileService service = new UserFileServiceImpl();
		String link = service.getLink(fileId);
		PrintWriter writer = response.getWriter();
		writer.print(link);
		writer.close();
	}

}
