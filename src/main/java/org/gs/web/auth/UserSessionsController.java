package org.gs.web.auth;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import org.gs.auth.User;

import com.google.inject.Singleton;

@Singleton
public class UserSessionsController {
	private static final Map<String, User> currentUsers = new HashMap<>();
	
	public User getUser(String sessionKey) {
		return currentUsers.get(sessionKey);
	}
	
	public String signIn(String email, String password) {
		Properties usersProps = new Properties();
		try {
			usersProps.load(new FileReader("users.txt"));
		}
		catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		String dbPassword = usersProps.getProperty(email);
		if (dbPassword == null) {
			throw new IllegalArgumentException("Invalid user");
		}
		if (!dbPassword.equals(password)) {
			throw new IllegalArgumentException("Invalid password");
		}
		
		String sessionKey = null;
		User withThisEmail = new User(null, null, email);
		if (currentUsers.containsValue(withThisEmail)) {
			for (User any : currentUsers.values()) {
				if (any.equals(withThisEmail)) {
					sessionKey = any.getSessionKey();
				}
			}
		}
		else {
			
			String name = usersProps.getProperty(email + "/" + "NAME");
			sessionKey = UUID.randomUUID().toString();
			
			currentUsers.put(sessionKey, new User(name, sessionKey, email));
		}
		
		return sessionKey;
	}
}
