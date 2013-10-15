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
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FolderService service = new FolderService();
		HttpSession session = request.getSession(false);
		long folderId = (long) session.getAttribute("folderId");
		String folderName = request.getParameter("folderName");
		long userId = (long) session.getAttribute("userid");
		if (!service.isFolderExist(folderId, folderName)) {
			service.createFolder(folderName, userId, folderId);
		} else {
			// error message
		}
		response.sendRedirect(USER_PAGE);
	}

}
