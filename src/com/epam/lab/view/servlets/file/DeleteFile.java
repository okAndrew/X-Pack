package com.epam.lab.view.servlets.file;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.lab.controller.services.file.FileService;
import com.epam.lab.controller.services.folder.FolderService;

@WebServlet("/DeleteFile")
public class DeleteFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String USER_PAGE = "userpage";

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher;
		HttpSession session = request.getSession(false);
		long userId = (long) session.getAttribute("userid");
		String[] rs = request.getParameterValues("files");
		String[] rs2 = request.getParameterValues("folders");
		if (rs != null) {
			for (int i = 0; i < rs.length; i++) {
				FileService.delete(Integer.parseInt(rs[i]));
			}
		}
		if (rs2 != null) {
			for (int i = 0; i < rs2.length; i++) {
				FolderService.delete(Integer.parseInt(rs2[i]), userId);
			}
		}
		if (rs == null && rs2 == null) {
			request.setAttribute("message",
					"Error! Please select files to delete");
			dispatcher = request.getRequestDispatcher(USER_PAGE);
		}

		dispatcher = request.getRequestDispatcher(USER_PAGE);
		dispatcher.forward(request, response);
	}
}