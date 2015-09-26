package org.gs.web;

import org.gs.web.auth.UserSessionsController;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

public class GuiceModule extends AbstractModule {
	
	@Override
	protected void configure() {
	}
	
	@Provides
	@Singleton
	public UserSessionsController userController() {
		return new UserSessionsController();
	}
}
