package com.epam.lab.controller.web.servlets.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.lab.controller.services.file.FileService;
import com.epam.lab.controller.services.folder.FolderService;

/**
 * Servlet implementation class MoveFolderServlet
 */
@WebServlet("/move")
public class MoveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String USER_PAGE = "userpage";

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		long folderidtarget = Long.parseLong(request
				.getParameter("folderidtarget"));
		long fileidmove = Long.parseLong(request.getParameter("fileidmove"));
		FileService fileservice = new FileService();
		fileservice.movefile(fileidmove, foldSeridtarget);
		response.sendRedirect(USER_PAGE);
		//
		// long folderidmove =
		// Long.parseLong(request.getParameter("folderidmove"));
		//
		// if(request.getParameter("fileidmove").equals("") ||
		// request.getParameter("fileidmove") == null){
		// //filemove
		// } else{
		// request.setAttribute("message", " Please choose file");
		// request.getRequestDispatcher(USER_PAGE).forward(request,
		// response);
		// } else if(request.getParameter("fileidmove").equals("") ||
		// request.getParameter("fileidmove") == null)){
		// //foldermove
		// }
		//
		// FolderService folderservice = new FolderService();
		// folderservice.movefolder(folderidmove, folderidtarget);
		//
	}

}
