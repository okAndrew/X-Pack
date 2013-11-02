package com.epam.lab.controller.web.hostservices;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.epam.lab.controller.exceptions.FileTooLargeException;
import com.epam.lab.controller.exceptions.notfound.TokenNotFoundException;
import com.epam.lab.controller.services.file.FileUploadServiceImpl;
import com.epam.lab.controller.services.file.UserFileService;
import com.epam.lab.controller.services.file.UserFileServiceImpl;
import com.epam.lab.controller.services.token4auth.Token4AuthService;
import com.epam.lab.controller.services.token4auth.Token4AuthServiceImpl;
import com.epam.lab.controller.services.user.UserServiceImpl;
import com.epam.lab.model.Folder;
import com.epam.lab.model.Token4Auth;
import com.epam.lab.model.User;
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
	private Token4AuthService tokenService = new Token4AuthServiceImpl();

	@POST
	@Path("upload/{idFolder}/{token}")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Response uploadFile(@PathParam("token") String token,
			@PathParam("idFolder") String idFolderStr,
			FormDataMultiPart multiPart) {
		Response response;
		JSONArray resultArray = null;
		long idFolder = Long.valueOf(idFolderStr);
		Folder folder;
		try {
			folder = tokenService.verifyAccessRequest(token, idFolder);
		} catch (TokenNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		if (folder == null) {
			return null;
		} else {
			Token4Auth tokenData = tokenService.getByToken(token);
			resultArray = uploadFiles(multiPart, folder.getId(),
					tokenData.getIdUser());
		}
		return Response.status(200).entity(resultArray).build();
	}

	@GET
	@Path("getfrom/{idfolder}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public JSONArray getFilesFromFolder(@PathParam("token") String token,
			@PathParam("idfolder") int idFolder) {
		JSONArray resultArray = null;
		Folder folder = null;
		try {
			folder = tokenService.verifyAccessRequest(token, idFolder);
		} catch (TokenNotFoundException e) {
			e.printStackTrace();
		}
		if (folder == null) {
		} else {
			resultArray = getFiles(idFolder);
		}
		return resultArray;
	}

	@DELETE
	@Path("delete/{token}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteFiles(@PathParam("token") String token,
			List<UserFile> files) {
		Token4AuthServiceImpl tokenService = new Token4AuthServiceImpl();
		Token4Auth tokenData = tokenService.getByToken(token);
		// tokenData
		UserFileServiceImpl fileService = new UserFileServiceImpl();
		for (UserFile file : files) {

			fileService.delete(file.getId());
		}
		return Response.status(200).build();
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

	private JSONArray uploadFiles(FormDataMultiPart multiPart, long idFolder,
			long idUser) {
		JSONArray resultArray = new JSONArray();
		List<FormDataBodyPart> fields = multiPart.getFields("file");
		for (FormDataBodyPart field : fields) {
			InputStream inputStream = field.getValueAs(InputStream.class);
			String fileName = field.getFormDataContentDisposition()
					.getFileName();
			JSONObject jsonObject = uploadFile(inputStream, fileName, idFolder,
					idUser);
			resultArray.put(jsonObject);
		}
		return resultArray;
	}

	private JSONObject uploadFile(InputStream inputStream, String fileName,
			long idFolder, long idUser) {
		UserFile uploadedFile = null;
		String message = null;
		try {
			UserFileServiceImpl userFileService = new UserFileServiceImpl();
			UserFile fileInfo = userFileService.createFileInfo(fileName,
					idFolder, idUser, true, 0L);
			User user = new UserServiceImpl().get(idUser);
			new FileUploadServiceImpl().uploadFile(inputStream, fileInfo, user);
			uploadedFile = userFileService.getByName(fileInfo.getName());
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
					"http://192.168.12.66:8080/dreamhost/download?file="
							+ userFile.getName());
			jsonOb.put("idFolder", userFile.getIdFolder());
			jsonOb.put("size", userFile.getSize());
			jsonOb.put("changeDate", userFile.getDate());
		} catch (JSONException e) {
			logger.error(e);
		}
		return jsonOb;
	}

}