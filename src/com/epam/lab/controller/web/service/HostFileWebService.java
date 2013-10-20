package com.epam.lab.controller.web.service;

import java.io.InputStream;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.epam.lab.controller.services.file.UserFileUploader;
import com.epam.lab.model.UserFile;
import com.sun.jersey.multipart.FormDataBodyPart;
import com.sun.jersey.multipart.FormDataMultiPart;
import com.sun.jersey.multipart.FormDataParam;

@Path("file")
public class HostFileWebService {

	private static Logger logger = Logger.getLogger(HostFileWebService.class);

	@POST
	@Path("upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Response uploadFile(@FormDataParam("token") String token,
			FormDataMultiPart multiPart) {
		JSONArray resultArray = new JSONArray();
		List<FormDataBodyPart> fields = multiPart.getFields("file");
		for (FormDataBodyPart field : fields) {
			UserFile uploadedFile = new UserFileUploader(13).uploadFile(
					field.getValueAs(InputStream.class),
					field.getFormDataContentDisposition());
			putObjToArray(resultArray, uploadedFile);
		}
		return Response.status(200).entity(resultArray).build();
	}

	private void putObjToArray(JSONArray resultArray, UserFile uploadedFile) {
		JSONObject jsonObject = null;
		try {
			jsonObject = toJson(uploadedFile);
			// if some exc. will catched, object will not placed
			resultArray.put(jsonObject);
		} catch (JSONException e) {
			logger.error(e);
		}
	}

	private JSONObject toJson(UserFile userFile) throws JSONException {
		JSONObject jsonOb = new JSONObject();
		jsonOb.put("fileName", userFile.getNameIncome());
		jsonOb.put("url", userFile.getId());
		jsonOb.put("idFolder", userFile.getIdFolder());
		jsonOb.put("fileLocation", userFile);
		jsonOb.put("size", userFile.getSize());
		jsonOb.put("changeDate", userFile.getDate());
		return jsonOb;
	}

}
