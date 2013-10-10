package com.epam.lab.view.servlets.file;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.lab.controller.services.file.FileService;

@WebServlet("/DeleteFile")
public class DeleteFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String USER_PAGE = "Test";

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher;
		String[] rs = request.getParameterValues("files");
		if (rs != null) {
			for (int i = 0; i < rs.length; i++) {
				FileService.deleteFiles(Integer.parseInt(rs[i]));
			}
			dispatcher = request.getRequestDispatcher(USER_PAGE);
		} else {
			request.setAttribute("message",
					"Error! Please select files to delete");
			dispatcher = request.getRequestDispatcher(USER_PAGE);
		}
		dispatcher.forward(request, response);
	}

}
