package org.eclipse.scout.apps.helloworld.server.spring;

import org.springframework.context.ApplicationContext;

public class SPRING {

	private static ApplicationContext s_context = null;

	public static void setContext(ApplicationContext context) {
		s_context = context;	
	}

	public static ApplicationContext getContext() {
		return s_context;
	}

	public static <T> T get(Class<T> clazz) {
		if(s_context == null) {
			return null;
		}

		return s_context.getBean(clazz);

	}
}
