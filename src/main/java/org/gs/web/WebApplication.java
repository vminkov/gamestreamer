package org.gs.web;

import javax.inject.Inject;

import org.gs.web.auth.SigninResource;
import org.gs.web.auth.UserParamInjectionResolver.UserBinder;

import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class WebApplication extends Application<Configuration> {
	@Inject
	private StreamControlResource streamControlResource;
	
	@Inject
	private UserBinder userBinder;
	
	@Inject
	private SigninResource signinResource;
	
	public WebApplication() {
	}
	
	@Override
	public void initialize(Bootstrap<Configuration> bootstrap) {
		AssetsBundle assetsBundle = new AssetsBundle("/web", "/app");
		bootstrap.addBundle(assetsBundle);
	}
	
	@Override
	public void run(Configuration arg0, Environment environment) throws Exception {
		//		environment.jersey().setUrlPattern("/api/*");
		
		environment.jersey().register(this.userBinder);
		environment.jersey().register(this.streamControlResource);
		environment.jersey().register(this.signinResource);
	}
}
