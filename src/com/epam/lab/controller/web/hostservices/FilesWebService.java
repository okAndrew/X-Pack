package com.epam.lab.controller.web.hostservices;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.epam.lab.controller.exceptions.FileTooLargeException;
import com.epam.lab.controller.exceptions.notfound.FolderNotFoundException;
import com.epam.lab.controller.exceptions.notfound.TokenNotFoundException;
import com.epam.lab.controller.exceptions.notfound.UserNotFoundException;
import com.epam.lab.controller.services.file.UserFileService;
import com.epam.lab.controller.services.file.UserFileServiceImpl;
import com.epam.lab.controller.services.file.UserFileUploader;
import com.epam.lab.controller.services.token4auth.Token4AuthService;
import com.epam.lab.controller.services.token4auth.Token4AuthServiceImpl;
import com.epam.lab.model.UserFile;
import com.sun.jersey.multipart.FormDataBodyPart;
import com.sun.jersey.multipart.FormDataMultiPart;

@Path("files")
public class FilesWebService {

	private static Logger logger = Logger.getLogger(FilesWebService.class);
	private static final String TOKEN_NOT_FOUND = "Token not found";
	private static final String FOLDER_NOT_FOUND = "Folder not found";
	private static final String FILE_TOO_LARGE = "File too large for upload";
	private static final String ERROR_UPLOADING = "Error uploading";

	@POST
	@Path("upload/{idFolder}/{token}")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public JSONArray uploadFile(@PathParam("token") String token,
			@PathParam("idFolder") String idFolderStr,
			FormDataMultiPart multiPart) {
		long idFolder = Long.valueOf(idFolderStr);
		String message = verifyAccessRequest(token, idFolder);
		if (message != null) {
			JSONObject jsonError = buildJsonError(message);
			return new JSONArray().put(jsonError);
		}
		JSONArray resultArray = uploadFiles(multiPart, idFolder);
		return resultArray;
	}

	@GET
	@Path("getfrom/{token}/{idfolder}")
	@Produces(MediaType.APPLICATION_JSON)
	public JSONArray getByIdFolder(@PathParam("token") String token,
			@PathParam("idfolder") int idFolder) {
		JSONArray resultArray = null;
		String message = verifyAccessRequest(token, idFolder);
		if (message != null) {
			JSONObject jsonError = buildJsonError(message);
			return new JSONArray().put(jsonError);
		} else {
			resultArray = getFiles(idFolder);
		}
		return resultArray;
	}

	private JSONArray getFiles(long idFolder) {
		UserFileService fileService = new UserFileServiceImpl();
		List<UserFile> listFiles = fileService.getByFolderId(idFolder);
		JSONArray jsonArray = new JSONArray();
		for (UserFile userFile : listFiles) {
			JSONObject jsonFile = toJson(userFile);
			jsonArray.put(jsonFile);
		}
		return jsonArray;
	}

	private JSONArray uploadFiles(FormDataMultiPart multiPart, long idFolder) {
		JSONArray resultArray = new JSONArray();
		List<FormDataBodyPart> fields = multiPart.getFields("file");
		for (FormDataBodyPart field : fields) {
			InputStream inputStream = field.getValueAs(InputStream.class);
			String fileName = field.getFormDataContentDisposition()
					.getFileName();
			JSONObject jsonObject = uploadFile(idFolder, inputStream, fileName);
			resultArray.put(jsonObject);
		}
		return resultArray;
	}

	private JSONObject uploadFile(long idFolder, InputStream inputStream,
			String fileName) {
		UserFile uploadedFile = null;
		String message = null;
		try {
			uploadedFile = new UserFileUploader().uploadFile(inputStream,
					fileName, idFolder);
		} catch (FolderNotFoundException e) {
			logger.warn(e);
			message = FOLDER_NOT_FOUND;
		} catch (IOException e) {
			logger.warn(e);
			message = ERROR_UPLOADING;
		} catch (FileTooLargeException e) {
			logger.warn(e);
			message = FILE_TOO_LARGE;
		}
		JSONObject jsonObject = null;
		if (uploadedFile != null) {
			jsonObject = toJson(uploadedFile);
		} else {
			jsonObject = buildJsonError(message);
		}
		return jsonObject;
	}

	private String verifyAccessRequest(String token, long idFolder) {
		String message = null;
		Token4AuthService service = new Token4AuthServiceImpl();
		boolean itUserFolder = false;
		try {
			itUserFolder = service.verifyAccessToFolder(token, idFolder);
		} catch (TokenNotFoundException e) {
			logger.warn(e);
			message = TOKEN_NOT_FOUND;
		} catch (UserNotFoundException e) {
			logger.warn(e);
			message = TOKEN_NOT_FOUND;
		} catch (FolderNotFoundException e) {
			logger.warn(e);
			message = FOLDER_NOT_FOUND;
		}
		if (itUserFolder == false) {
			message = FOLDER_NOT_FOUND;
		}
		return message;
	}

	private JSONObject buildJsonError(String mes) {
		JSONObject jsonOb = new JSONObject();
		try {
			jsonOb.put("error", mes);
		} catch (JSONException e) {
			logger.error(e);
		}
		return jsonOb;
	}

	private JSONObject toJson(UserFile userFile) {
		JSONObject jsonOb = new JSONObject();
		try {
			jsonOb.put("fileName", userFile.getNameIncome());
			jsonOb.put("url",
					"http://localhost:8080/dreamhost/download?fileid="
							+ userFile.getId());
			jsonOb.put("idFolder", userFile.getIdFolder());
			jsonOb.put("size", userFile.getSize());
			jsonOb.put("changeDate", userFile.getDate());
		} catch (JSONException e) {
			logger.error(e);
		}
		return jsonOb;
	}

}
