package org.eclipse.scout.apps.helloworld.server.spring;

import org.eclipse.scout.apps.helloworld.server.Counter;
import org.eclipse.scout.apps.helloworld.server.model.Greeting;
import org.eclipse.scout.rt.platform.BEANS;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * test for this rest service: http://localhost:8081/greeting?name=Alice
 * expected result (the id value may differ): {"id":1,"content":"Hello, Alice!"} 
 */
// TODO [mzi]: make spring boot use the available servlet container (it currently starts the embedded tomcat)
// without this rest service, it would be enough to say 'app.setWebEnvironment(false);' in the PlatformListener class
// alternatively make the scout platform use the tomcat embedded by springboot
@RestController
public class GreetingController {

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
    	Long id = BEANS.get(Counter.class).nextValue(); // use a scout bean
    	String content = SPRING.get(Hello.class).getText(name); // use a spring component
        return new Greeting(id, content);
    }
}
