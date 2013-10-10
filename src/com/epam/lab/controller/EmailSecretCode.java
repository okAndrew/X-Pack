package com.epam.lab.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/EmailSecretCode")
public class EmailSecretCode extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String to = (String) request.getAttribute("to"); //"savruksergiy@gmail.com";
		String sub = "sub"; //(String) request.getAttribute("sub"); //"TEST DREAMHOST";
		String msg = "msg"; //(String) request.getAttribute("msg"); //"This mail is from localhost/dreamhost/";
		
		SendMail.send(to, sub, msg);
	}

}
