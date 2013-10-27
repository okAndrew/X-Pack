package com.epam.lab.controller.web.servlets.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.lab.controller.services.file.UserFileServiceImpl;
import com.epam.lab.controller.services.folder.FolderServiceImpl;

/**
 * Servlet implementation class MoveFolderServlet
 */
@WebServlet("/move")
public class MoveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String USER_PAGE = "userpage";

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		if (request.getParameter("fileidmove") == null
				|| request.getParameter("fileidmove").equals("")) {
			FolderServiceImpl folderservice = new FolderServiceImpl();
			if (request.getParameter("folderidtarget") == null
					|| request.getParameter("folderidtarget").equals("")) {
				request.setAttribute("message",
						" Please choose folder for move");
				request.getRequestDispatcher(USER_PAGE).forward(request,
						response);
			} else {
				long folderidmove = Long.parseLong(request
						.getParameter("folderidmove"));
				long folderidtarget = Long.parseLong(request
						.getParameter("folderidtarget"));
				folderservice.movefolder(folderidmove, folderidtarget);
				response.sendRedirect(USER_PAGE);
			}
		}else if(request.getParameter("folderidmove") == null
				|| request.getParameter("folderidmove").equals("")){
			UserFileServiceImpl fileservice = new UserFileServiceImpl();
			if (request.getParameter("folderidtarget") == null
					|| request.getParameter("folderidtarget").equals("")) {
				request.setAttribute("message",
						" Please choose folder for move");
				request.getRequestDispatcher(USER_PAGE).forward(request,
						response);
			} else {
				long fileidmove = Long.parseLong(request
						.getParameter("fileidmove"));
				long folderidtarget = Long.parseLong(request
						.getParameter("folderidtarget"));
				fileservice.moveFile(fileidmove, folderidtarget);
				response.sendRedirect(USER_PAGE);
			}
		}
	}

}
