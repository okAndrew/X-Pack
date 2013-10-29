package com.epam.lab.controller.web.servlets.admin.users.simpleuser.simpleuserpage;

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
import com.epam.lab.model.UserFile;
import com.epam.lab.model.Folder;

@WebServlet("/adminUserSearchFiles")
public class AdminUserSearchFilesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ADMIN_USER_FILES_JSP = "WEB-INF/jsp/admin/users/simpleUser/adminUserFiles.jsp";

	public AdminUserSearchFilesServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		long userId = (long) session.getAttribute("adminUserid");
		if (request.getParameter("searchtext") != null) {
			String text = request.getParameter("searchtext");
			List<UserFile> files = new UserFileServiceImpl().getSearchedFiles(userId, text);
			List<Folder> folders = new FolderServiceImpl().getSearched(
					userId, text);
			request.setAttribute("files", files);
			request.setAttribute("folders", folders);
			request.getRequestDispatcher(ADMIN_USER_FILES_JSP).forward(request,
					response);
		} else {
			response.sendRedirect(ADMIN_USER_FILES_JSP);
		}
	}
}
