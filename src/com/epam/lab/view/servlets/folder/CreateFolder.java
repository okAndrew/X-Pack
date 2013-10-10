package com.epam.lab.view.servlets.folder;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.lab.controller.services.folder.FolderService;

@WebServlet("/createfolder")
public class CreateFolder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		long folderId = (long) session.getAttribute("folderId");
		String folderName = request.getParameter("folderName");
		long userId = (long) session.getAttribute("userid");
		if (!FolderService.isFolderExist(folderId, folderName)) {
			FolderService.createFolder(folderName, userId, folderId);
		} else {
			// error message
		}
		request.getRequestDispatcher("Test").forward(request, response);
	}

}
