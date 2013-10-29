package com.epam.lab.controller.web.servlets.user.browser;

import java.io.IOException;
import java.util.Iterator;
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

@WebServlet("/search")
public class BrowserSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String BROWSER_JSP = "WEB-INF/jsp/user/browser.jsp";
	private static final String USER_PAGE = "userpage";

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		long userId = (long) session.getAttribute("userid");
		String searchText = request.getParameter("searchtext");
		if (searchText != null) {
			FolderServiceImpl folderService = new FolderServiceImpl();
			List<UserFile> files = new UserFileServiceImpl().getSearchedFiles(
					userId, searchText);
			List<Folder> folders = folderService
					.getSearched(userId, searchText);
			removeCurentDirFromResponse(request, folders);
			request.setAttribute("files", files);
			request.setAttribute("folders", folders);

			// additional params
			long folderId = (long) request.getSession()
					.getAttribute("folderid");
			Folder currentFolder = folderService.get(folderId);
			List<Folder> folderPath = folderService.getFolderPath(folderId);
			request.setAttribute("currentFolder", currentFolder);
			request.setAttribute("folderpath", folderPath);

			request.getRequestDispatcher(BROWSER_JSP)
					.include(request, response);
		} else {
			response.sendRedirect(USER_PAGE);
		}
	}

	private void removeCurentDirFromResponse(HttpServletRequest request,
			List<Folder> folders) {
		Long idCurFolder = (Long) request.getSession().getAttribute("folderid");
		if (idCurFolder == null)
			return;
		Iterator<Folder> iterator = folders.iterator();
		while (iterator.hasNext()) {
			if (iterator.next().getId() == idCurFolder) {
				iterator.remove();
				break;
			}
		}
	}
}
