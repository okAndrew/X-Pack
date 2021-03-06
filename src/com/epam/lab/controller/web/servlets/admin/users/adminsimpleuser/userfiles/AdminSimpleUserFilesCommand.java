package com.epam.lab.controller.web.servlets.admin.users.adminsimpleuser.userfiles;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.lab.controller.services.file.UserFileServiceImpl;
import com.epam.lab.controller.web.servlets.admin.users.adminsimpleuser.AdminSimpleUserPageCommand;
import com.epam.lab.model.UserFile;

public class AdminSimpleUserFilesCommand implements AdminSimpleUserPageCommand {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String page = null;
		UserFileServiceImpl fileServiceImpl = new UserFileServiceImpl();
		HttpSession session = request.getSession(false);
		long userId = (long) session.getAttribute("adminUserid");
		List<UserFile> list = fileServiceImpl.getByUserId(userId);
		request.setAttribute("filelist", list);
		page = "WEB-INF/jsp/admin/users/simpleUser/adminUser.jsp";
		return page;
	}

}
