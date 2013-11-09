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

import com.epam.lab.controller.services.tariff.TariffServise;
import com.epam.lab.controller.services.tariff.TariffServiseImpl;
import com.epam.lab.controller.services.token4auth.Token4AuthService;
import com.epam.lab.controller.services.token4auth.Token4AuthServiceImpl;
import com.epam.lab.controller.services.user.UserServiceImpl;
import com.epam.lab.controller.utils.JSONBuilder;
import com.epam.lab.controller.utils.MD5Encrypter;
import com.epam.lab.model.Tariff;
import com.epam.lab.model.Token4Auth;
import com.epam.lab.model.User;

@Path("token")
public class TokenWebService {
	private static Logger logger = Logger.getLogger(TokenWebService.class);
	private static final String USER_NOT_FOUND = "User not found";
	private Token4AuthService tokenService = new Token4AuthServiceImpl();
	private JSONBuilder jsonBuilder = new JSONBuilder();

	@POST
	@Path("create/{email:.+@.+\\.[a-z]+}/{password}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createToken4Download(@PathParam("email") String email,
			@PathParam("password") String password, Token4Auth tokenData) {
		User user = getUser(email, password);
		Token4Auth createdToken = null;
		if (user != null) {
			createdToken = tokenService.createToken(user.getId(),
					tokenData.getLiveTime());
			JSONObject response = jsonBuilder.toJson(createdToken);
			return Response.status(201).entity(response).build();
		} else {
			logger.warn(USER_NOT_FOUND);
			return Response.status(401).entity(USER_NOT_FOUND).build();
		}
	}

	private User getUser(String email, String password) {
		UserServiceImpl userService = new UserServiceImpl();
		String encryptPass = new MD5Encrypter().encrypt(password);
		User user = userService.get(email, encryptPass);
		TariffServise tariffServise = new TariffServiseImpl();
		Tariff tariff = tariffServise.get(user.getIdTariff());
		if (user.getIsActivated() && !user.getIsBanned()
				&& tariff.getPosition() >= 5) {
			return user;
		} else {
			return null;
		}
	}
}