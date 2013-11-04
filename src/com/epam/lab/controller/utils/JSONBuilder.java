package com.epam.lab.controller.utils;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.epam.lab.model.UserFile;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JSONBuilder {
	private static Logger logger = Logger.getLogger(JSONBuilder.class);

	public JSONObject createJsonQuality(String key, String val) {
		JSONObject jsonOb = new JSONObject();
		try {
			jsonOb.put(key, val);
		} catch (JSONException e) {
			logger.error(e);
		}
		return jsonOb;
	}

	public JSONObject userFileToJson(UserFile userFile) {
		Gson jsonOb = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
				.create();
		String json = jsonOb.toJson(userFile);
		JSONObject result = null;
		try {
			result = new JSONObject(json);
			result.put("url", "http://localhost:8080/dreamhost/download?file="
					+ userFile.getName());
		} catch (JSONException e) {
			e.printStackTrace();
			logger.error(e);
		}
		return result;
	}

	public JSONObject toJson(Object object) {
		Gson jsonOb = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
				.create();
		String json = jsonOb.toJson(object);
		JSONObject result = null;
		try {
			result = new JSONObject(json);
		} catch (JSONException e) {
			e.printStackTrace();
			logger.error(e);
		}
		return result;
	}
}