package com.epam.lab.controller.web.servlets.user.browser;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/videocontent")
public class VideoContentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIDEO_CONTENT_JSP = "WEB-INF/jsp/user/myspace/videocontent.jsp";
	private static final String DOWNLOAD_SERVLET = "fileservlet?fileid=";

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String videoSRC = DOWNLOAD_SERVLET + request.getParameter("id");
		request.setAttribute("videoSRC", videoSRC);
		request.getRequestDispatcher(VIDEO_CONTENT_JSP).include(request,
				response);
	}

}
