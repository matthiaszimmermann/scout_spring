package org.eclipse.scout.apps.helloworld.server.spring;

import org.eclipse.scout.apps.helloworld.server.model.Hello;
import org.eclipse.scout.rt.platform.util.StringUtility;
import org.springframework.stereotype.Component;

/**
 * The Spring hello @Component. 
 */
@Component
public class HelloImpl implements Hello {
	
	private static final String HELLO_TEMPLATE = "Hello, %s!";
	private static final String HELLO = "Hello!";

	public String getText(String name) {
		if(StringUtility.hasText(name)) {
			return String.format(HELLO_TEMPLATE, name);
		}
		else {
			return HELLO;
		}
	}
}
