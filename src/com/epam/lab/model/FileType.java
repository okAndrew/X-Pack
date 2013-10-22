package com.epam.lab.model;

import java.util.ArrayList;
import java.util.List;

public enum FileType {
	IMAGE("IMAGE", "jpg", "png", "gif"), AUDIO("AUDIO", "avi", "mp4"), VIDEO(
			"VIDEO", "mp3"), TEXT("TEXT", "txt"), ARCHIVE("ARCHIVE", "zip",
			"rar"), OTHERTYPE("OTHERTYPE");

	private static final List<FileType> types;
	static {
		types = new ArrayList<FileType>();
		types.add(IMAGE);
		types.add(AUDIO);
		types.add(VIDEO);
		types.add(TEXT);
		types.add(ARCHIVE);
		// types.add(OTHERTYPE); empty type
	}

	private String[] extensions;
	private String name;

	FileType(String name, String... extensions) {
		this.name = name;
		this.extensions = extensions;
	}

	public static FileType findByExtention(String extention) {
		FileType result = OTHERTYPE;
		for (FileType type : types) {
			for (String e : type.getExtensions()) {
				if (e.equals(extention)) {
					result = type;
					break;
				}
			}
			if (result != OTHERTYPE)
				break;
		}
		return result;
	}

	public static FileType findByName(String name) {
		FileType result = OTHERTYPE;
		for (FileType type : types) {
			if (type.getName().equals(name)) {
				result = type;
				break;
			}
			if (result != OTHERTYPE)
				break;
		}
		return result;
	}

	public String[] getExtensions() {
		return extensions;
	}

	public void setExtensions(String[] extensions) {
		this.extensions = extensions;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
