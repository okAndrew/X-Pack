package com.epam.lab.controller.web.servlets.file;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.lab.controller.services.file.UserFileServiceImpl;
import com.epam.lab.controller.services.folder.FolderServiceImpl;
import com.epam.lab.model.Folder;

@WebServlet("/useredit")
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String USER_PAGE = "userpage";

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		request.setCharacterEncoding("UTF-8");
		String folderName = request.getParameter("foldername");
		String fileName = request.getParameter("filename");
		if (fileName != null && !fileName.equals("")) {
			long fileId = Long.parseLong(request.getParameter("fileid"));
			long folderId = (long) session.getAttribute("folderid");
			UserFileServiceImpl service = new UserFileServiceImpl();
			if (service.check(folderId, fileName)) {
				request.setAttribute("message", "File exists");
				request.getRequestDispatcher(USER_PAGE).forward(request,
						response);
			} else {
				service.rename(fileId, fileName);
				response.sendRedirect(USER_PAGE);
			}
		} else if (folderName != null && !folderName.equals("")) {
			long userId = (long) session.getAttribute("userid");
			long upperId = (long) session.getAttribute("folderid");
			long folderId = Long.parseLong(request.getParameter("folderid"));
			FolderServiceImpl service = new FolderServiceImpl();
			
			if (service.check(folderName, userId, upperId)) {
				request.setAttribute("message", "Folder exists");
				request.getRequestDispatcher(USER_PAGE).forward(request,
						response);
			} else {
				Folder folder = service.get(folderId);
				folder.setName(folderName);
				service.update(folder);
				response.sendRedirect(USER_PAGE);
			}
		} else {
			request.setAttribute("message",
					"Please, enter name, before editing");
			request.getRequestDispatcher(USER_PAGE).forward(request, response);
		}

	}
}
