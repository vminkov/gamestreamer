package org.gs.web.auth;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.gs.auth.User;
import org.gs.web.JsonResponse;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.annotation.JsonProperty;

@Path("/signin")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SigninResource {
	private final UserSessionsController users;
	
	@Inject
	public SigninResource(UserSessionsController factory) {
		this.users = factory;
	}
	
	@POST
	@Timed
	public SessionKeyData signin(UsernamePasswordData data) {
		try {
			System.out.println(data);
			String sessionKey = this.users.signIn(data.username, data.password);
			
			return new SessionKeyData(sessionKey);
		}
		catch (IllegalArgumentException e) {
			throw new WebApplicationException(Response.status(403).build());
		}
		catch (Exception e) {
			throw new WebApplicationException(Response.status(500).build());
		}
	}
	
	@POST
	@Path("/validate")
	public Response validateSession(SessionKeyData data) {
		System.out.println("validate: " + data);
		User user = this.users.getUser(data.sessionKey);
		if (user == null) {
			throw new WebApplicationException(Response.status(403).build());
		}
		else {
			return JsonResponse.ok();
		}
	}
}

class UsernamePasswordData {
	@JsonProperty
	String username;
	@JsonProperty
	String password;
	
	@Override
	public String toString() {
		return "User: " + username + " with password: " + password;
	}
}

class SessionKeyData {
	public SessionKeyData() {
	}
	
	public SessionKeyData(String sessionKey) {
		this.sessionKey = sessionKey;
	}
	
	@JsonProperty
	String sessionKey;
	
	@Override
	public String toString() {
		return "Session key: " + sessionKey;
	}
}
