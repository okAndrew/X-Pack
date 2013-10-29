package com.epam.lab.controller.web.servlets.user.browser;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.lab.controller.dao.file.FileDAOImpl;
import com.epam.lab.controller.dao.user.UserDAOImpl;
import com.epam.lab.controller.services.file.UserFileServiceImpl;
import com.epam.lab.controller.services.folder.FolderServiceImpl;
import com.epam.lab.model.Folder;
import com.epam.lab.model.UserFile;

@WebServlet("/VideoContent")
public class VideoContentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIDEO_CONTENT_JSP = "WEB-INF/jsp/user/modelvideo.jsp";

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		long id = Long.parseLong(request.getParameter("id"));

		String videoType = "video/mp4";
		String videoSRC = "fileservlet?fileid=" + id;
		request.setAttribute("videoType", videoType);
		request.setAttribute("videoSRC", videoSRC);
		request.getRequestDispatcher(VIDEO_CONTENT_JSP).include(request,
				response);
	}

}
