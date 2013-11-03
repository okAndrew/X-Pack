package com.epam.lab.controller.web.servlets.admin.users.simpleuser.newarch.userfiles.files;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.lab.controller.services.folder.FolderServiceImpl;
import com.epam.lab.controller.services.user.SearchService;
import com.epam.lab.controller.services.user.SearchServiceImpl;
import com.epam.lab.model.UserFile;

@WebServlet("/adminsearch")
public class AdminBrowserSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String BROWSER_JSP = "WEB-INF/jsp/admin/users/simpleUser/files/tableFiles.jsp";
	private static final String ADMIN_USER_FILES = "adminUserFiles";

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		long userId = (long) session.getAttribute("adminUserid");
		String searchText = request.getParameter("searchtext");
		if (searchText != null) {
			SearchService service = new SearchServiceImpl();
			long rootFolderId = new FolderServiceImpl().getRoot(userId).getId();
			service.prepareLists(rootFolderId, searchText);
			List<UserFile> filelist = service.getFiles();
			request.setAttribute("filelist", filelist);
		} else {
			response.sendRedirect(ADMIN_USER_FILES);
		}
		request.getRequestDispatcher(BROWSER_JSP).include(request, response);
		;
	}
}
