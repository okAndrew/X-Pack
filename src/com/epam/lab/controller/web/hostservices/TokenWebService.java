package com.epam.lab.controller.web.hostservices;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONObject;

import com.epam.lab.controller.services.token4auth.Token4AuthService;
import com.epam.lab.controller.services.token4auth.Token4AuthServiceImpl;
import com.epam.lab.controller.services.user.UserServiceImpl;
import com.epam.lab.controller.utils.MD5Encrypter;
import com.epam.lab.model.Token4Auth;
import com.epam.lab.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Path("token")
public class TokenWebService {
	private static Logger logger = Logger.getLogger(TokenWebService.class);

	@POST
	@Path("create/{email:.+@.+\\.[a-z]+}/{password}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String createToken4Download(@PathParam("email") String email,
			@PathParam("password") String password, Token4Auth tokenData) {
		User user = getUser(email, password);
		Token4Auth createdToken = null;
		String response = null;
		if (user != null) {
			createdToken = createToken(tokenData, user);
			response = createResponse(createdToken);
		}
		return response;
	}

	@GET
	@Path("info/{email:.+@.+\\.[a-z]+}/{password}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject getStatusToken4Download() {
		JSONObject response = new JSONObject();
		return response;
	}

	@PUT
	@Path("update/{email:.+@.+\\.[a-z]+}/{password}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject updateToken4Download() {
		JSONObject response = new JSONObject();
		return response;
	}

	@DELETE
	@Path("destroy/{email:.+@.+\\.[a-z]+}/{password}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteToken4Download() {
		return Response.status(200).build();
	}

	private Token4Auth createToken(Token4Auth tokenData, User user) {
		Token4AuthService service = new Token4AuthServiceImpl();
		Token4Auth createdToken = null;
		createdToken = service.createToken(user.getId(),
				tokenData.getLiveTime());
		return createdToken;
	}

	private User getUser(String email, String password) {
		UserServiceImpl userService = new UserServiceImpl();
		String encryptPass = new MD5Encrypter().encrypt(password);
		User user = userService.get(email, encryptPass);
		return user;
	}

	private String createResponse(Token4Auth createdToken) {
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
				.create();
		return gson.toJson(createdToken);
	}
}