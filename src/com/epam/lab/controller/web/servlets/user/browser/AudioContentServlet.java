package com.epam.lab.controller.web.servlets.user.browser;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AudioContentServlet
 */
@WebServlet("/audiocontent")
public class AudioContentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String AUDIO_CONTENT_JSP = "WEB-INF/jsp/user/modals/audioContent.jsp";
	private static final String DOWNLOAD_SERVLET = "download?file=";

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String audioSRC = DOWNLOAD_SERVLET + request.getParameter("name");
		request.setAttribute("audioSRC", audioSRC);
		request.getRequestDispatcher(AUDIO_CONTENT_JSP).include(request,
				response);
	}

}