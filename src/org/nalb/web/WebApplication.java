package org.nalb.web;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.nalb.web.auth.SigninResource;
import org.nalb.web.auth.UserParamInjectionResolver.UserBinder;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class WebApplication extends Application<WebConfiguration> {
	@Inject
	private WebInterface webInterface;
	
	@Inject
	private UserBinder userBinder;
	
	@Inject
	private SigninResource signinResource;
	
	public WebApplication() {
	}
	
	@Override
	public void run(WebConfiguration arg0, Environment environment) throws Exception {
		environment.jersey().register(this.userBinder);
		
		environment.jersey().register(this.webInterface);
		environment.jersey().register(this.signinResource);
	}
}
