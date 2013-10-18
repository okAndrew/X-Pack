package com.epam.lab.controller.web.servlets.user.browser;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.lab.controller.services.file.FileService;
import com.epam.lab.controller.services.folder.FolderService;
import com.epam.lab.model.UserFile;
import com.epam.lab.model.Folder;

@WebServlet("/search")
public class BrowserSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String SEARCH_JSP = "WEB-INF/jsp/user/search.jsp";
	private static final String USER_PAGE = "userpage";

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		long userId = (long) session.getAttribute("userid");
		if (request.getParameter("searchtext") != null) {
			String text = request.getParameter("searchtext");
			List<UserFile> files = new FileService().getSearchedFiles(userId, text);
			List<Folder> folders = new FolderService().getSearched(
					userId, text);
			request.setAttribute("files", files);
			request.setAttribute("folders", folders);
			request.getRequestDispatcher(SEARCH_JSP).forward(request, response);
		} else {
			response.sendRedirect(USER_PAGE);
		}
	}

}
