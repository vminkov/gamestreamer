package org.gs.web;

import io.dropwizard.Configuration;
import io.dropwizard.jetty.HttpConnectorFactory;
import io.dropwizard.server.DefaultServerFactory;

public class WebConfiguration extends Configuration {
	public WebConfiguration() {
		DefaultServerFactory serverFactory = (DefaultServerFactory) this.getServerFactory();
		HttpConnectorFactory httpConnectorFactory = new HttpConnectorFactory();
		httpConnectorFactory.setPort(8000);
		serverFactory.getApplicationConnectors().clear();
		serverFactory.getApplicationConnectors().add(httpConnectorFactory);
		
		// Override admin port
		final HttpConnectorFactory adminConnectionFactory = new HttpConnectorFactory();
		adminConnectionFactory.setPort(8082);
		serverFactory.getAdminConnectors().clear();
		serverFactory.getAdminConnectors().add(adminConnectionFactory);
	}
}
