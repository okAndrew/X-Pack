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
import com.epam.lab.model.UserFile;
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
		FileService fileService = new FileService();
		FolderService folderService = new FolderService();
		HttpSession session = request.getSession(false);
		long userId = (long) session.getAttribute("adminUserid");
		long folderId;
		if (session.getAttribute("folderid") == null) {
			folderId = folderService.getRoot(userId).getId();
			session.setAttribute("folderid", folderId);
		} else {
			folderId = (long) session.getAttribute("folderid");
		}
		Folder currentFolder = folderService.get(folderId);
		List<Folder> folders = folderService.get(userId, folderId);
		List<UserFile> files = fileService.getByFolderId(folderId);
		request.setAttribute("folders", folders);
		request.setAttribute("files", files);
		request.setAttribute("currentFolder", currentFolder);

		RequestDispatcher requestDispatcher = request
				.getRequestDispatcher(ADMIN_USER_FILES_JSP);
		requestDispatcher.forward(request, response);
	}

}
