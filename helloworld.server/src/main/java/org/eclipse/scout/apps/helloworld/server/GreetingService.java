package org.eclipse.scout.apps.helloworld.server;

import org.eclipse.scout.apps.helloworld.server.model.Greeting;
import org.eclipse.scout.apps.helloworld.server.model.Hello;
import org.eclipse.scout.apps.helloworld.shared.IGreetingService;
import org.eclipse.scout.apps.helloworld.shared.helloworld.GreetingFormData;
import org.eclipse.scout.rt.platform.BEANS;

/**
 * Implementation of the {@link IGreetingService} Scout bean.
 * Implicit Scout bean annotation via the implemented interface.
 */
public class GreetingService implements IGreetingService {
	
	/**
	 * Sets the message field content.
	 * The implementation is making use of a Scout bean and a Spring component.
	 * Using the Spring autowiring does not work as the necessary Spring context is missing.
	 * Instead, you may access the Spring component using the Scout bean manager. 
	 */
	@Override
	public GreetingFormData load(GreetingFormData input) {
    	String user = ServerSession.get().getUserId();
    	
    	Long id = BEANS.get(Counter.class).nextValue(); // access a Scout bean
    	String content = BEANS.get(Hello.class).getText(user); // accessing a Spring component as a Scout bean
		    	
		input.getMessage().setValue((new Greeting(id, content)).toString());
		
		return input;
	}
}
