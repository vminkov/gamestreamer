package org.gs.web.auth;

import javax.inject.Inject;

import org.gs.auth.User;

import com.google.common.base.Optional;

public class BearerTokenAuthenticator implements io.dropwizard.auth.Authenticator<String, User> {
	@Inject
	private UserSessionsController users;
	
	@Override
	public Optional<User> authenticate(String token) {
		return Optional.of(users.getUser(token));
	}
}
