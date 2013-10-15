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
import com.epam.lab.model.File;
import com.epam.lab.model.Folder;

@WebServlet("/userpage")
public class UserPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String USER_JSP = "WEB-INF/jsp/user/user.jsp";

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		FileService service2 = new FileService();
		FolderService service = new FolderService();
		HttpSession session = request.getSession(false);
		long userid =  (long) session.getAttribute("userid");
		long folderId;
		if (session.getAttribute("folderId")==null) {
			folderId = service.getRootId(userid);
			session.setAttribute("folderId", folderId);
		} else {
			folderId = (long) session.getAttribute("folderId");
		}
		Folder currentFolder = service.getFolderById(folderId);
		List<Folder> folders = service.getFolders(userid, folderId);
		List<File> files = service2.getFiles(userid, folderId);
		request.setAttribute("folders", folders);
		request.setAttribute("files", files);
		request.setAttribute("currentFolder", currentFolder);
		request.getRequestDispatcher(USER_JSP).forward(request, response);
	}

}
