package com.epam.lab.controller.web.servlets.admin.users.simpleuser.newarch.userfiles.files;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.lab.controller.services.file.UserFileServiceImpl;
import com.epam.lab.controller.services.folder.FolderServiceImpl;
import com.epam.lab.model.Folder;
import com.epam.lab.model.UserFile;

/**
 * Servlet implementation class BrowserContentsServlet
 */
@WebServlet("/adminBrowserContent")
public class BrowserContentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String BROWSER_JSP = "WEB-INF/jsp/admin/users/simpleUser/files/tableFiles.jsp";

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		UserFileServiceImpl fileService = new UserFileServiceImpl();
		FolderServiceImpl folderService = new FolderServiceImpl();
		HttpSession session = request.getSession(false);
		long userId = (long) session.getAttribute("adminUserid");
		long folderId;
		if (session.getAttribute("folderid") == null) {
			folderId = folderService.getRoot(userId).getId();
			session.setAttribute("folderid", folderId);
		} else {
			folderId = (long) session.getAttribute("folderid");
		}
		Folder currentFolder = folderService.get(folderId);
		List<Folder> folders = folderService.getByUpperId(folderId);
		List<UserFile> files = fileService.getByFolderId(folderId);
		List<Folder> folderPath = folderService.getFolderPath(folderId);
		request.setAttribute("folders", folders);
		request.setAttribute("files", files);
		request.setAttribute("currentFolder", currentFolder);
		request.setAttribute("folderpath", folderPath);

		request.setCharacterEncoding("UTF-8");
		if (request.getAttribute("parent") == null)
			request.getRequestDispatcher(BROWSER_JSP)
					.include(request, response);
	}
}
