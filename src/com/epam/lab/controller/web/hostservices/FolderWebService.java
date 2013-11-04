package com.epam.lab.controller.web.hostservices;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONObject;

import com.epam.lab.controller.exceptions.notfound.TokenNotFoundException;
import com.epam.lab.controller.services.folder.FolderServiceImpl;
import com.epam.lab.controller.services.token4auth.Token4AuthService;
import com.epam.lab.controller.services.token4auth.Token4AuthServiceImpl;
import com.epam.lab.controller.services.user.UserServiceImpl;
import com.epam.lab.controller.utils.JSONBuilder;
import com.epam.lab.model.Folder;
import com.epam.lab.model.User;

@Path("folder")
public class FolderWebService {
	private static Logger logger = Logger.getLogger(FilesWebService.class);
	private static final String TOKEN_NOT_FOUND = "Token not found";
	private static final String FOLDER_NOT_FOUND = "Folder not found";
	private Token4AuthService tokenService = new Token4AuthServiceImpl();
	private JSONBuilder jsonBuilder = new JSONBuilder();

	@POST
	@Path("create/{token}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createFolder(@PathParam("token") String token, Folder folder) {
		Folder upperFolder = null;
		try {
			upperFolder = tokenService.verifyAccessRequest(token,
					folder.getIdUpper());
		} catch (TokenNotFoundException e) {
			logger.error(e);
			return Response.status(401).entity(TOKEN_NOT_FOUND).build();
		}
		if (upperFolder == null) {
			return Response.status(404).entity(FOLDER_NOT_FOUND).build();
		}
		User user = new UserServiceImpl().get(upperFolder.getIdUser());
		Folder createdFolder = new FolderServiceImpl().createFolder(
				folder.getName(), user.getId(), upperFolder.getId());
		JSONObject jsonResult = jsonBuilder.toJson(createdFolder);
		return Response.status(201).entity(jsonResult).build();
	}
}
