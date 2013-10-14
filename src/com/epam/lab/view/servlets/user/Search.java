package com.epam.lab.view.servlets.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.lab.controller.services.SearchService;
import com.epam.lab.model.File;
import com.epam.lab.model.Folder;

@WebServlet("/search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String SEARCH_JSP = "WEB-INF/jsp/user/search.jsp";

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		long userId = (long) session.getAttribute("userid");
		String text = request.getParameter("searchtext");
		SearchService service = new SearchService(text, userId);
		List<File> files = service.getFiles();
		List<Folder> folders = service.getFolders();
		request.setAttribute("files", files);
		request.setAttribute("folders", folders);
		request.getRequestDispatcher(SEARCH_JSP).forward(request, response);
	}

}
