package com.epam.lab.controller.web.servlets.admin.users.simpleuser.files;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.lab.controller.services.file.UserFileServiceImpl;
import com.epam.lab.controller.services.folder.FolderServiceImpl;
import com.epam.lab.controller.services.user.UserServiceImpl;
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
		UserServiceImpl userService = new UserServiceImpl();
		HttpSession session = request.getSession(false);
		
		UserFileServiceImpl fileServiceImpl = new UserFileServiceImpl();
		long userId = (long) session.getAttribute("adminUserid");
		List<UserFile> list = fileServiceImpl.getByUserId(userId);
		request.setAttribute("filelist", list);

		request.setAttribute("parent", "adminUserFiles");
		request.getRequestDispatcher(ADMIN_USER_FILES_JSP).forward(request, response);
	}

}
