package org.eclipse.scout.apps.helloworld.server.spring;

import org.eclipse.scout.apps.helloworld.server.Counter;
import org.eclipse.scout.apps.helloworld.server.model.Greeting;
import org.eclipse.scout.apps.helloworld.server.model.Hello;
import org.eclipse.scout.rt.platform.BEANS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Greeting REST service.
 * Demonstrates usage of Scout beans and Spring components including Spring autowiring. 
 * Test for this REST service: http://localhost:8080/api/greeting?name=Alice
 * Expected result (the id value may differ): {"id":1,"content":"Hello, Alice!"} 
 */
@RestController
public class GreetingRestController {

	@Autowired
	Hello hello;
	
// TODO mzi --> try to get this working (it is with spring boot... what magic is spring boot adding to get this running?)
//	  @RequestMapping("/greeting")
//	  public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
//        Long id = BEANS.get(Counter.class).nextValue(); // access a Scout bean
//        String content = hello.getText(name); // access a Spring component
//
//        return new Greeting(id, content);
//	  }

	// this works but it's not what we want
	@RequestMapping("/greeting")
	@ResponseBody
	public String greeting(@RequestParam(value="name", defaultValue="World") String name) {
		Long id = BEANS.get(Counter.class).nextValue(); // access a Scout bean
		String content = hello.getText(name); // access a Spring component
		
		return new Greeting(id, content).toString();
	}
}
