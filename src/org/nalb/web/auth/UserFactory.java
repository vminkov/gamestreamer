package org.nalb.web.auth;

import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.internal.inject.AbstractContainerRequestValueFactory;
import org.nalb.auth.User;

import com.google.inject.Singleton;

@Singleton
public class UserFactory extends AbstractContainerRequestValueFactory<User> {
	@Inject
	private UserController userController;
	
	@Override
	public User provide() {
		String auth = getContainerRequest().getHeaderString(HttpHeaders.AUTHORIZATION);
		User result = userController.getUser(auth);
		
		if (result == null) {
			throw new WebApplicationException(Response.Status.FORBIDDEN);
		}
		
		return result;
	}
	
	@Override
	public void dispose(User arg0) {
	}
}
