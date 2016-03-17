package org.eclipse.scout.apps.helloworld.server.helloworld;

import org.eclipse.scout.apps.helloworld.server.Counter;
import org.eclipse.scout.apps.helloworld.server.ServerSession;
import org.eclipse.scout.apps.helloworld.server.model.Greeting;
import org.eclipse.scout.apps.helloworld.server.spring.Hello;
import org.eclipse.scout.apps.helloworld.server.spring.SPRING;
import org.eclipse.scout.apps.helloworld.shared.helloworld.HelloWorldFormData;
import org.eclipse.scout.apps.helloworld.shared.helloworld.IHelloWorldFormService;
import org.eclipse.scout.rt.platform.BEANS;

/**
 * <h3>{@link HelloWorldFormService}</h3>
 *
 * @author mzi
 */
public class HelloWorldFormService implements IHelloWorldFormService {

	@Override
	public HelloWorldFormData load(HelloWorldFormData input) {
    	Long id = BEANS.get(Counter.class).nextValue();
    	String user = ServerSession.get().getUserId();
    	String content = SPRING.get(Hello.class).getText(user);
		Greeting greeting = new Greeting(id, content);
		
		input.getMessage().setValue(greeting.getContent());
		
		return input;
	}
}
