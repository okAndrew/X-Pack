package com.epam.lab.controller.web.hostservices;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.epam.lab.controller.exceptions.notfound.FolderNotFoundException;
import com.epam.lab.controller.exceptions.notfound.TokenNotFoundException;
import com.epam.lab.controller.exceptions.notfound.UserNotFoundException;
import com.epam.lab.controller.services.folder.FolderServiceImpl;
import com.epam.lab.controller.services.token4upload.Token4UploadService;
import com.epam.lab.controller.services.token4upload.Token4UploadServiceImpl;
import com.epam.lab.controller.services.user.UserServiceImpl;
import com.epam.lab.model.Folder;
import com.epam.lab.model.Token4Upload;
import com.epam.lab.model.User;

@Path("folder")
public class FolderWebService {
	private static Logger logger = Logger.getLogger(FilesWebService.class);
	private static final String TOKEN_NOT_FOUND = "Token not found";
	private static final String FOLDER_NOT_FOUND = "Folder not found";

	@POST
	@Path("create/{token}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject createFolder(@PathParam("token") String token, Folder folder) {
		String message = verifyAccessFolderRequest(token, folder.getIdUpper());
		if (message != null) {
			try {
				return new JSONObject().put("error", message);
			} catch (JSONException e) {
				logger.error(e);
				return null;
			}
		}

		Token4Upload tokenData = new Token4UploadServiceImpl()
				.getByToken(token);
		User user = new UserServiceImpl().get(tokenData	.getIdUser());
		Folder createdFolder = new FolderServiceImpl().createFolder(
				folder.getName(), user.getId(), folder.getIdUpper());
		return toJson(createdFolder);
	}

	private JSONObject toJson(Folder folder) {
		JSONObject jsonOb = new JSONObject();
		try {
			jsonOb.put("id", folder.getId());
			jsonOb.put("name", folder.getName());
			jsonOb.put("size", folder.getSize());
			jsonOb.put("changeDate", folder.getDate());
		} catch (JSONException e) {
			logger.error(e);
		}
		return jsonOb;
	}

	private String verifyAccessFolderRequest(String token, long idFolder) {
		String message = null;
		Token4UploadService service = new Token4UploadServiceImpl();
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

}
