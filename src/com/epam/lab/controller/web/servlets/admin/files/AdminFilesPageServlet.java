package com.epam.lab.controller.web.servlets.admin.files;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.lab.controller.services.adminfunc.DiskSpaceService;
import com.epam.lab.controller.services.file.TypesService;

@WebServlet("/adminFilesPage")
public class AdminFilesPageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static String ADMIN_FILES_PAGE_JSP = "WEB-INF/jsp/admin/files/adminFilesPage.jsp";

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		getFreeSpace(request, response);
		getTotalSpace(request, response);
		getTypeFilesGroup(request, response);

		RequestDispatcher dispatcher = request
				.getRequestDispatcher(ADMIN_FILES_PAGE_JSP);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		getFreeSpace(request, response);
		getTotalSpace(request, response);
		getTypeFilesGroup(request, response);
		RequestDispatcher dispatcher = request
				.getRequestDispatcher(ADMIN_FILES_PAGE_JSP);
		dispatcher.forward(request, response);
	}

	private void getFreeSpace(HttpServletRequest request,
			HttpServletResponse response) {
		DiskSpaceService service = new DiskSpaceService();
		request.setAttribute("freeSpace", service.getFreeSpace());

	}

	private void getTotalSpace(HttpServletRequest request,
			HttpServletResponse response) {
		DiskSpaceService service = new DiskSpaceService();
		request.setAttribute("totalSpace", service.getTotalSpace());
	}

	private void getTypeFilesGroup(HttpServletRequest request,
			HttpServletResponse response) {
		TypesService service = new TypesService();
		request.setAttribute("types", service.getTypesFiles());
	}
}
