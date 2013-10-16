package com.epam.lab.controller.web.servlets.admin.users.simpleuser;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
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

@WebServlet("/adminUserFiles")
public class AdminUserFilesServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String ADMIN_USER_FILES_JSP = "WEB-INF/jsp/admin/users/simpleUser/adminUserFiles.jsp";

	public AdminUserFilesServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		FolderService service = new FolderService();
		FileService service2 = new FileService();
		HttpSession session = request.getSession(false);
		long userid = (long) session.getAttribute("userid");
		long folderId;
		if (session.getAttribute("folderId") == null) {
			folderId = service.getRootId(userid);
			session.setAttribute("folderId", folderId);
		} else {
			folderId = (long) session.getAttribute("folderId");
		}
		Folder currentFolder = service.getFolder(folderId);
		List<Folder> folders = service.getFolders(userid, folderId);
		List<File> files = service2.getFiles(userid, folderId);
		request.setAttribute("folders", folders);
		request.setAttribute("files", files);
		request.setAttribute("currentFolder", currentFolder);

		RequestDispatcher requestDispatcher = request
				.getRequestDispatcher(ADMIN_USER_FILES_JSP);
		requestDispatcher.forward(request, response);
	}

}
