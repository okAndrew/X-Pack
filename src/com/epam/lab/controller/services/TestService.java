package com.epam.lab.controller.services;

import java.util.List;

import com.epam.lab.controller.dao.impl.FileDAOImpl;
import com.epam.lab.model.File;



public class TestService {
	public List<File> getAllFiles(long iduser){
		List<File> files = null;
		FileDAOImpl filedaoimpl = new FileDAOImpl();
		files = filedaoimpl.getAllbyUserId(iduser);
		return files;
	}
}
