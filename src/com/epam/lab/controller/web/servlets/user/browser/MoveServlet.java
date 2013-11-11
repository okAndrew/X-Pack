package com.epam.lab.controller.web.servlets.user.browser;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.lab.controller.services.folder.FolderService;
import com.epam.lab.controller.services.folder.FolderServiceImpl;

@WebServlet("/move")
public class MoveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		long userId = (long) session.getAttribute("userid");
		String idTargetStr = request.getParameter("idTarget");
		String[] moveables = request.getParameterValues("moveable[]");
		if (idTargetStr != null && !idTargetStr.equals("") && moveables != null
				&& !moveables[0].equals("")) {
			long idTarget = Long.parseLong(idTargetStr);
			FolderService folderService = new FolderServiceImpl();
			folderService.moveFilesAndFolders(moveables, idTarget, userId);
		}
	}
}