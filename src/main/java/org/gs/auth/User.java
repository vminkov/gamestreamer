package org.gs.auth;

import com.google.common.base.Objects;

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
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj == this)
			return true;
		if (!(obj instanceof User)) {
			return false;
		}
		
		User other = (User) obj;
		
		return Objects.equal(email, other.email);
	}
	
	@Override
	public String toString() {
		return "User: " + name + " with email: " + email + " sessKey: " + sessionKey;
	}
}
