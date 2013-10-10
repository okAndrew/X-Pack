package com.epam.lab.view.servlets.file;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.epam.lab.controller.services.file.FileService;
import com.epam.lab.model.File;
import com.epam.lab.view.servlets.SignUp;

/**
 * Servlet implementation class Test
 */
@WebServlet("/Test")
public class FilePage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String USER_JSP = "WEB-INF/jsp/user/user.jsp";
	static Logger logger = Logger.getLogger(SignUp.class);
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		long userid =  (long) session.getAttribute("userid");
		List<File> files = FileService.getAllFiles(userid);
		request.setAttribute("files", files);
		request.getRequestDispatcher(USER_JSP).forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		long userid =  (long) session.getAttribute("userid");
		List<File> files = FileService.getAllFiles(userid);
		request.setAttribute("files", files);
		request.getRequestDispatcher(USER_JSP).forward(request, response);
	}

}
