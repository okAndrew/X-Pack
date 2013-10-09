package com.epam.lab.view.servlets.file;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.lab.controller.services.TestService;
import com.epam.lab.model.File;

/**
 * Servlet implementation class Test
 */
@WebServlet("/Test")
public class FilePage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		TestService service = new TestService();
		List<File> files = service.getAllFiles(1);
		request.setAttribute("files", files);
		request.getRequestDispatcher("WEB-INF/jsp/user/user.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		TestService service = new TestService();
		List<File> files = service.getAllFiles(1);
		request.setAttribute("files", files);
		request.getRequestDispatcher("WEB-INF/jsp/user/user.jsp").forward(request, response);
	}

}
