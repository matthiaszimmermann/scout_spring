package org.eclipse.scout.apps.helloworld.server.spring;

import org.eclipse.scout.apps.helloworld.server.Counter;
import org.eclipse.scout.apps.helloworld.server.model.Greeting;

import org.eclipse.scout.rt.platform.BEANS;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
    	Long id = BEANS.get(Counter.class).nextValue();
    	String content = SPRING.get(Hello.class).getText(name);
        return new Greeting(id, content);
    }
}
