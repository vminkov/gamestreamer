package org.nalb.web.auth;

import javax.inject.Inject;

import org.nalb.auth.User;

import com.google.common.base.Optional;

public class BearerTokenAuthenticator implements io.dropwizard.auth.Authenticator<String, User> {
	@Inject
	private UserController users;
	
	@Override
	public Optional<User> authenticate(String token) {
		return Optional.of(users.getUser(token));
	}
}
