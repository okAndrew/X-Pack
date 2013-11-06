package com.epam.lab.controller.web.servlets.security;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.smartcardio.ATR;

@WebServlet("/restorepassword")
public class RestorePassword extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final String SIGNIN_JSP = "signin";
	private static final String RESTORE_JSP = "";
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
