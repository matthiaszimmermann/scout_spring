package org.eclipse.scout.apps.helloworld.server.helloworld;

import org.eclipse.scout.apps.helloworld.server.ServerSession;
import org.eclipse.scout.apps.helloworld.shared.helloworld.HelloWorldFormData;
import org.eclipse.scout.apps.helloworld.shared.helloworld.IHelloWorldFormService;

/**
 * <h3>{@link HelloWorldFormService}</h3>
 *
 * @author mzi
 */
public class HelloWorldFormService implements IHelloWorldFormService {

	@Override
	public HelloWorldFormData load(HelloWorldFormData input) {
		StringBuilder msg = new StringBuilder();
		msg.append("Hello ").append(ServerSession.get().getUserId()).append("!");
		input.getMessage().setValue(msg.toString());
		return input;
	}
}
