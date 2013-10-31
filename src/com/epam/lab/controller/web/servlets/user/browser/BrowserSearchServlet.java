package com.epam.lab.controller.web.servlets.user.browser;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.lab.controller.services.folder.FolderService;
import com.epam.lab.controller.services.folder.FolderServiceImpl;
import com.epam.lab.controller.services.user.SearchService;
import com.epam.lab.controller.services.user.SearchServiceImpl;
import com.epam.lab.model.Folder;
import com.epam.lab.model.UserFile;

@WebServlet("/search")
public class BrowserSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String BROWSER_JSP = "WEB-INF/jsp/user/browser.jsp";

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		long folderId = (long) session.getAttribute("folderid");
		String searchText = request.getParameter("searchtext");
		if (searchText != null && !searchText.equals("")) {
			SearchService service = new SearchServiceImpl();
			FolderService folderService = new FolderServiceImpl();
			if (service.prepareLists(folderId, searchText)) {
				
				List<UserFile> files = service.getFiles();
				List<Folder> folders = service.getFolders();
				Folder currentFolder = folderService.get(folderId);
				List<Folder> folderPath = folderService.getFolderPath(folderId);

				request.setAttribute("files", files);
				request.setAttribute("folders", folders);
				request.setAttribute("currentFolder", currentFolder);
				request.setAttribute("folderpath", folderPath);
				request.setAttribute("search", true);
				request.getRequestDispatcher(BROWSER_JSP).include(request,
						response);
				
			} else {
				//Your search returned no results
				request.setAttribute("search", true);
				request.setAttribute("search_no_result", true);
				request.getRequestDispatcher(BROWSER_JSP).include(request,
						response);
			}
		}
	}
}