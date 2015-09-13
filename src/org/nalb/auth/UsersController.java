package org.nalb.auth;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import javax.inject.Singleton;

@Singleton
public class UsersController {
	private final Map<String, User> currentUsers = new HashMap<>();
	
	public User getUser(String sessionKey) {
		return this.currentUsers.get(sessionKey);
	}
	
	public String authenticate(String email, String password) {
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
			throw new RuntimeException("Invalid user");
		}
		if (!dbPassword.equals(password)) {
			throw new RuntimeException("Invalid password");
		}
		
		String name = usersProps.getProperty(email + "/" + "NAME");
		String sessionKey = UUID.randomUUID().toString();
		
		this.currentUsers.put(sessionKey, new User(name, sessionKey, email));
		
		return sessionKey;
	}
}
