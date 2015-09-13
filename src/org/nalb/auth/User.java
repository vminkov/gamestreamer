package org.nalb.auth;

public class User {
	private String name;
	private String sessionKey;
	private String email;
	
	User(String name, String sessionKey, String email) {
		this.name = name;
		this.sessionKey = sessionKey;
		this.email = email;
	}
}
