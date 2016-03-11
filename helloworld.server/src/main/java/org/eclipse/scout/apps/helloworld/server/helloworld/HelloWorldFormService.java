package org.eclipse.scout.apps.helloworld.server.helloworld;

import org.eclipse.scout.apps.helloworld.server.Counter;
import org.eclipse.scout.apps.helloworld.server.ServerSession;
import org.eclipse.scout.apps.helloworld.server.model.Greeting;
import org.eclipse.scout.apps.helloworld.shared.helloworld.HelloWorldFormData;
import org.eclipse.scout.apps.helloworld.shared.helloworld.IHelloWorldFormService;
import org.eclipse.scout.rt.platform.BEANS;

/**
 * <h3>{@link HelloWorldFormService}</h3>
 *
 * @author mzi
 */
public class HelloWorldFormService implements IHelloWorldFormService {

    private static final String template = "Hello, %s!";

	@Override
	public HelloWorldFormData load(HelloWorldFormData input) {
    	Long id = BEANS.get(Counter.class).nextValue();
    	String content = String.format(template, ServerSession.get().getUserId());
		Greeting greeting = new Greeting(id, content);
		
		input.getMessage().setValue(greeting.getContent());
		
		return input;
	}
}
