package com.epam.lab.controller.web.hostservices;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
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
import com.epam.lab.controller.services.token4auth.Token4AuthService;
import com.epam.lab.controller.services.token4auth.Token4AuthServiceImpl;
import com.epam.lab.controller.services.user.UserServiceImpl;
import com.epam.lab.model.Folder;
import com.epam.lab.model.Token4Auth;
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
	public JSONObject createFolder(@PathParam("token") String token,
			Folder folder) {
		JSONObject response = null;
		String message = verifyRequest(token, folder.getIdUpper());
		if (message != null) {
			response = buildJsonError(message);
		} else {
			User user = null;
			try {
				user = getUserByToken(token);
			} catch (TokenNotFoundException e) {
				logger.error(e);
				return null;
			}
			Folder createdFolder = new FolderServiceImpl().createFolder(
					folder.getName(), user.getId(), folder.getIdUpper());
			response = folderToJson(createdFolder);
		}
		return response;
	}

	@GET
	@Path("getroot/{token}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject getRootFolder(@PathParam("token") String token) {
		String message = null;
		User user = null;
		Folder root = null;
		try {
			user = getUserByToken(token);
			root = getRootByUser(user);
			if (root == null)
				throw new FolderNotFoundException();
		} catch (TokenNotFoundException e) {
			logger.warn(e);
			message = TOKEN_NOT_FOUND;
		} catch (FolderNotFoundException e) {
			logger.error(e);
			message = TOKEN_NOT_FOUND;
		}
		JSONObject response = null;
		if (message != null) {
			response = buildJsonError(message);
		} else {
			response = folderToJson(root);
		}
		return response;
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

	private JSONObject folderToJson(Folder folder) {
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

	private Folder getRootByUser(User user) {
		Folder root = new FolderServiceImpl().getRoot(user.getId());
		return root;
	}

	private User getUserByToken(String token) throws TokenNotFoundException {
		Token4Auth tokenData = new Token4AuthServiceImpl().getByToken(token);
		if (tokenData == null) {
			throw new TokenNotFoundException();
		}
		User user = new UserServiceImpl().get(tokenData.getIdUser());
		return user;
	}

	private String verifyRequest(String token, long idFolder) {
		String message = null;
		Token4AuthService service = new Token4AuthServiceImpl();
		Folder userFolder = null;
		try {
			userFolder = service.verifyAccessToFolder(token, idFolder);
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
		if (userFolder == null) {
			message = FOLDER_NOT_FOUND;
		}
		return message;
	}

}
