package org.eclipse.scout.apps.helloworld.server.spring;

import org.eclipse.scout.apps.helloworld.server.Counter;
import org.eclipse.scout.apps.helloworld.server.model.Greeting;
import org.eclipse.scout.rt.platform.BEANS;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * test for this rest service: http://localhost:8080/api/greeting?name=Alice
 * expected result (the id value may differ): {"id":1,"content":"Hello, Alice!"} 
 */
@RestController
public class GreetingRestController {

	// TODO mzi --> try to get this working (it is with spring boot... what magic is spring boot adding to get this running?)
//	  @RequestMapping("/greeting")
//	  public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
//	  	Long id = BEANS.get(Counter.class).nextValue(); // use a scout bean
//	  	String content = BEANS.get(Hello.class).getText(name); // use a spring component
//	      return new Greeting(id, content);
//	  }

	// this works but it's not what we want
	@RequestMapping("/greeting")
	@ResponseBody
	public String greeting(@RequestParam(value="name", defaultValue="World") String name) {
		Long id = BEANS.get(Counter.class).nextValue(); // use a scout bean
		Hello h = BEANS.get(Hello.class); // use a spring component
		
		return new Greeting(id, h.getText(name)).toString();
	}
}
