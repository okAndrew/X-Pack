package com.epam.lab.controller.web.servlets.admin.users.simpleuser.files;

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

@WebServlet("/adminsearch")
public class AdminBrowserSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String BROWSER_JSP = "WEB-INF/jsp/user/adminbrowser.jsp";
	private static final String ADMIN_USER_FILES = "adminUserFiles";

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		long userId = (long) session.getAttribute("adminUserid");
		String searchText = request.getParameter("searchtext");
		if (searchText != null) {
			FolderServiceImpl folderService = new FolderServiceImpl();
			List<UserFile> files = new UserFileServiceImpl().getSearchedFiles(
					userId, searchText);
			List<Folder> folders = folderService
					.getSearched(userId, searchText);
			request.setAttribute("files", files);
			request.setAttribute("folders", folders);

			// additional params
			long folderId = folderService.getRoot(userId).getId();
			session.setAttribute("folderid", folderId);
			Folder currentFolder = folderService.get(folderId);
			List<Folder> folderPath = folderService.getFolderPath(folderId);
			request.setAttribute("currentFolder", currentFolder);
			request.setAttribute("folderpath", folderPath);

			request.getRequestDispatcher(BROWSER_JSP)
					.include(request, response);
		} else {
			response.sendRedirect(ADMIN_USER_FILES);
		}
	}
}
