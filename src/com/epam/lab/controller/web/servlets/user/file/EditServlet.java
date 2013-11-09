package com.epam.lab.controller.web.servlets.user.file;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.lab.controller.services.file.UserFileServiceImpl;

@WebServlet("/useredit")
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String USER_PAGE = "userpage";

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String name = request.getParameter("editname");
		UserFileServiceImpl service = new UserFileServiceImpl();
		long upperId = (long) session.getAttribute("folderid");
		long userId = (long) session.getAttribute("userid");
		boolean result = false;
		if (request.getParameter("fileid") != null
				&& !request.getParameter("fileid").equals("")) {
			long fileId = Long.parseLong(request.getParameter("fileid"));
			service.editFileOrFolder(name, fileId, upperId, 0, userId);
		} else if (request.getParameter("folderid") != null
				&& !request.getParameter("folderid").equals("")) {
			long folderId = Long.parseLong(request.getParameter("folderid"));
			result = service.editFileOrFolder(name, 0, upperId, folderId, userId);
		}
		if(result == true){
			request.setAttribute("message", "Folder with this name is already exist");
		}
		request.getRequestDispatcher(USER_PAGE).include(request, response);
	}
}
