package org.gs.web;

import javax.inject.Inject;

import org.gs.web.auth.SigninResource;
import org.gs.web.auth.UserParamInjectionResolver.UserBinder;

import com.google.inject.Singleton;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

@Singleton
public class WebApplication extends Application<WebConfiguration> {
	@Inject
	private StreamControlResource streamControlResource;
	
	@Inject
	private MosaicConfigResource mosaicConfigResource;
	
	@Inject
	private UserBinder userBinder;
	
	@Inject
	private SigninResource signinResource;
	
	public WebApplication() {
	}
	
	@Override
	public void initialize(Bootstrap<WebConfiguration> bootstrap) {
		AssetsBundle assetsBundle = new AssetsBundle("/web", "/app");
		bootstrap.addBundle(assetsBundle);
	}
	
	@Override
	public void run(WebConfiguration arg0, Environment environment) throws Exception {
		//		environment.jersey().setUrlPattern("/api/*");
		
		environment.jersey().register(this.userBinder);
		environment.jersey().register(this.streamControlResource);
		environment.jersey().register(this.mosaicConfigResource);
		environment.jersey().register(this.signinResource);
	}
}
