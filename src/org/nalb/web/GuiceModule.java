package org.nalb.web;

import org.nalb.web.auth.UserController;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

public class GuiceModule extends AbstractModule {
	
	@Override
	protected void configure() {
	}
	
	@Provides
	@Singleton
	public UserController userController() {
		return new UserController();
	}
}
