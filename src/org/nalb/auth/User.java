package org.nalb.auth;

public class User {
	private String name;
	private String sessionKey;
	private String email;
	
	public User(String name, String sessionKey, String email) {
		this.name = name;
		this.sessionKey = sessionKey;
		this.email = email;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSessionKey() {
		return sessionKey;
	}
	
	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "User: " + name + " with email: " + email + " sessKey: " + sessionKey;
	}
}
