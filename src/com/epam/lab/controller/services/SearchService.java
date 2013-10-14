package com.epam.lab.controller.services;

import java.util.ArrayList;
import java.util.List;

import com.epam.lab.controller.services.file.FileService;
import com.epam.lab.controller.services.folder.FolderService;
import com.epam.lab.model.File;
import com.epam.lab.model.Folder;

public class SearchService {
	private String text = null;
	private long userId;

	public SearchService(String text, long userId) {
		this.text = text;
		this.userId = userId;
	}

	public List<File> getFiles() {
		List<File> files = FileService.getAllFiles(userId);
		List<File> result = new ArrayList<File>();
		for (File file: files) {
			if (file.getNameIncome().contains(text)) {
				result.add(file);
			}
		}
		return result;
	}

	public List<Folder> getFolders(){
		List<Folder> folders = FolderService.getAllFolders(userId);
		List<Folder> result = new ArrayList<Folder>();
		for (Folder folder: folders) {
			if (folder.getName().contains(text)) {
				result.add(folder);
			}
		}
		return result;
	}
}
