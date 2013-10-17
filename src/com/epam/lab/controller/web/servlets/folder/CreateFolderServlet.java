package com.epam.lab.controller.web.servlets.folder;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.lab.controller.services.folder.FolderService;

@WebServlet("/createfolder")
public class CreateFolderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String USER_PAGE = "userpage";

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		FolderService service = new FolderService();
		HttpSession session = request.getSession(false);
		long folderId = (long) session.getAttribute("folderid");
		long userId = (long) session.getAttribute("userid");
		if (request.getParameter("foldername") == null
				|| request.getParameter("foldername").equals("")) {
			request.setAttribute("message",
					"Please, enter folder's name, before create it");
			request.getRequestDispatcher(USER_PAGE).forward(request, response);
		} else {
			String folderName = request.getParameter("foldername");
			if (service.check(folderName, userId, folderId)) {
				request.setAttribute("message", "Folder exist");
				request.getRequestDispatcher(USER_PAGE).forward(request,
						response);
			} else {
				service.create(folderName, userId, folderId);
				response.sendRedirect(USER_PAGE);
			}
		}
	}
}
