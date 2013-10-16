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

import org.apache.log4j.Logger;

import com.epam.lab.controller.services.PaymentService;
import com.epam.lab.controller.services.UserService;
import com.epam.lab.controller.services.file.FileService;
import com.epam.lab.controller.services.folder.FolderService;
import com.epam.lab.model.File;
import com.epam.lab.model.Folder;
import com.epam.lab.model.Payment;
import com.epam.lab.model.User;

@WebServlet("/adminUserSearchFiles")
public class AdminUserSearchFilesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ADMIN_USER_FILES_JSP = "WEB-INF/jsp/admin/users/simpleUser/adminUserFiles.jsp";
	private static final Logger logger = Logger
			.getLogger(AdminUserSearchFilesServlet.class);

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
		long userId = (long) session.getAttribute("userid");
		if (request.getParameter("searchtext") != null) {
			String text = request.getParameter("searchtext");
			List<File> files = new FileService().getSearchedFiles(userId, text);
			List<Folder> folders = new FolderService().getSearchedFolders(
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