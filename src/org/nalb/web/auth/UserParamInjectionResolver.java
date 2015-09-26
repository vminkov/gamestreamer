package org.nalb.web.auth;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.container.ContainerRequestContext;

import org.glassfish.hk2.api.Factory;
import org.glassfish.hk2.api.InjectionResolver;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.api.TypeLiteral;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.process.internal.RequestScoped;
import org.glassfish.jersey.server.ContainerRequest;
import org.glassfish.jersey.server.internal.inject.AbstractValueFactoryProvider;
import org.glassfish.jersey.server.internal.inject.MultivaluedParameterExtractorProvider;
import org.glassfish.jersey.server.internal.inject.ParamInjectionResolver;
import org.glassfish.jersey.server.model.Parameter;
import org.glassfish.jersey.server.spi.internal.ValueFactoryProvider;
import org.nalb.auth.User;

@Singleton
public class UserParamInjectionResolver extends ParamInjectionResolver<BasicAuth> {
	
	public UserParamInjectionResolver() {
		super(UserFactoryProvider.class);
	}
	
	@Singleton
	public static class UserFactoryProvider extends AbstractValueFactoryProvider {
		private UserFactory factory;
		
		@Inject
		public UserFactoryProvider(MultivaluedParameterExtractorProvider mpep, ServiceLocator locator, UserFactory users) {
			super(mpep, locator, Parameter.Source.UNKNOWN);
			this.factory = users;
		}
		
		@Override
		protected Factory<?> createValueFactory(Parameter parameter) {
			Class<?> paramType = parameter.getRawType();
			BasicAuth annotation = parameter.getAnnotation(BasicAuth.class);
			if (annotation != null && paramType.isAssignableFrom(User.class)) {
				return factory;
			}
			return null;
		}
	}
	
	public static class UserBinder extends AbstractBinder {
		@Override
		public void configure() {
			//			bind(BearerTokenAuthenticator.class).to(BearerTokenAuthenticator.class).in(Singleton.class);
			
			bind(UserFactory.class).to(UserFactory.class).in(Singleton.class);
			bind(UserController.class).to(UserController.class).in(Singleton.class);
			
			bind(UserFactoryProvider.class).to(ValueFactoryProvider.class).in(Singleton.class);
			
			bind(UserParamInjectionResolver.class).to(new TypeLiteral<InjectionResolver<BasicAuth>>() {
			}).in(Singleton.class);
		}
	}
}
