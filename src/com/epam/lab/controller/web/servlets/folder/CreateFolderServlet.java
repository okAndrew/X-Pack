package com.epam.lab.controller.web.servlets.folder;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.lab.controller.services.folder.FolderServiceImpl;

@WebServlet("/createfolder")
public class CreateFolderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String USER_PAGE = "userpage";

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		long folderId = (long) session.getAttribute("folderid");
		long userId = (long) session.getAttribute("userid");
		RequestDispatcher dispatcher;
		String validError = null;
		String folderName = request.getParameter("foldername");
		FolderServiceImpl service = new FolderServiceImpl();
		validError = service.makeFolder(folderName, userId, folderId);
		if (validError == null) {
			dispatcher = request.getRequestDispatcher(USER_PAGE);
		} else {
			request.setAttribute("message", validError);
			dispatcher = request.getRequestDispatcher(USER_PAGE);
		}

		dispatcher.forward(request, response);
	}
}
