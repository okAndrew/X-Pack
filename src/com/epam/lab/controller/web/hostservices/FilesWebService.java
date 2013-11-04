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
import org.codehaus.jettison.json.JSONObject;

import com.epam.lab.controller.exceptions.FileTooLargeException;
import com.epam.lab.controller.exceptions.notfound.TokenNotFoundException;
import com.epam.lab.controller.services.file.FileUploadServiceImpl;
import com.epam.lab.controller.services.file.UserFileService;
import com.epam.lab.controller.services.file.UserFileServiceImpl;
import com.epam.lab.controller.services.token4auth.Token4AuthService;
import com.epam.lab.controller.services.token4auth.Token4AuthServiceImpl;
import com.epam.lab.controller.services.user.UserServiceImpl;
import com.epam.lab.controller.utils.JSONBuilder;
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
	private static final String FILE_NOT_FOUND = "File not found";
	private static final String FILE_TOO_LARGE = "File too large for upload";
	private static final String ERROR_UPLOADING = "Error uploading";
	private Token4AuthService tokenService = new Token4AuthServiceImpl();
	private JSONBuilder jsonBuilder = new JSONBuilder();

	@POST
	@Path("upload/{idFolder}/{token}")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Response uploadFile(@PathParam("token") String token,
			@PathParam("idFolder") String idFolderStr,
			FormDataMultiPart multiPart) {
		JSONArray resultArray = null;
		Long idFolder = Long.parseLong(idFolderStr);
		if (idFolder == null) {
			return Response.status(404).entity(idFolderStr + FOLDER_NOT_FOUND)
					.build();
		}
		Folder folder = null;
		try {
			folder = tokenService.verifyAccessRequest(token, idFolder);
		} catch (TokenNotFoundException e) {
			logger.error(e);
			return Response.status(401).entity(TOKEN_NOT_FOUND).build();
		}
		if (folder == null) {
			return Response.status(404).entity(FOLDER_NOT_FOUND).build();
		} else {
			Token4Auth tokenData = tokenService.getByToken(token);
			resultArray = uploadFiles(multiPart, folder.getId(),
					tokenData.getIdUser());
		}
		return Response.status(201).entity(resultArray).build();
	}

	@GET
	@Path("getfrom/{idfolder}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getFilesFromFolder(@PathParam("token") String token,
			@PathParam("idfolder") String idFolderStr) {
		JSONArray resultArray = null;
		Long idFolder = Long.parseLong(idFolderStr);
		if (idFolder == null) {
			return Response.status(404).entity(idFolderStr + FOLDER_NOT_FOUND)
					.build();
		}
		Folder folder = null;
		try {
			folder = tokenService.verifyAccessRequest(token, idFolder);
		} catch (TokenNotFoundException e) {
			e.printStackTrace();
			return Response.status(401).entity(TOKEN_NOT_FOUND).build();
		}
		if (folder == null) {
			return Response.status(404).entity(FOLDER_NOT_FOUND).build();
		} else {
			resultArray = getFiles(folder.getId());
		}
		return Response.status(201).entity(resultArray).build();
	}

	@DELETE
	@Path("delete/{token}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteFiles(@PathParam("token") String token,
			List<Long> idFiles) {
		Token4Auth tokenData = tokenService.getByToken(token);
		if (tokenData == null || tokenService.isActive(tokenData) == false) {
			return Response.status(401).entity(TOKEN_NOT_FOUND).build();
		}
		JSONArray errors = new JSONArray();
		UserFileServiceImpl fileService = new UserFileServiceImpl();
		for (Long idFile : idFiles) {
			UserFile userFile = fileService.get(idFile);
			if (userFile != null
					&& userFile.getIdUser() == tokenData.getIdUser()) {
				fileService.delete(idFile);
			} else {
				errors.put(jsonBuilder.createJsonQuality("error",
						FILE_NOT_FOUND + idFile));
			}
		}
		if (errors.length() != 0) {
			return Response.status(400).entity(errors).build();
		} else {
			return Response.status(200).build();
		}
	}

	private JSONArray getFiles(long idFolder) {
		UserFileService fileService = new UserFileServiceImpl();
		List<UserFile> listFiles = fileService.getByFolderId(idFolder);
		JSONArray jsonArray = new JSONArray();
		for (UserFile userFile : listFiles) {
			JSONObject jsonFile = jsonBuilder.userFileToJson(userFile);
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
			jsonObject = jsonBuilder.userFileToJson(uploadedFile);
		} else {
			jsonObject = jsonBuilder.createJsonQuality("error", message);
		}
		return jsonObject;
	}

}