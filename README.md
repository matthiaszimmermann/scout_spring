# Eclipse Scout Spring Integration
This example project combines Eclipse Scout and Spring Boot into a single application.

The setup integrates the standard Eclipse Scout Hello World application (Scout Neon M5) with the [https://spring.io/guides/gs/rest-service/](Spring Boot REST tutorial)

## Demo
1. Start the Scout server (will also start Spring boot)
2. Start the Scout client 
3. Use the application
  * [http://localhost:8082/](http://localhost:8082/): Scout HTML5 user interface 
  * [http://localhost:8081/greeting?name=Alice](http://localhost:8081/greeting?name=Alice): Spring Boot REST service

## Adding SpringBoot to the Eclipse Scout Hello World
1. Create a standard Eclipse Scout "Hello World"
2. Add `spring-boot-dependencies` to the `pom.xml` of the `helloworld` module:
```
<project ...
  
  ...
  
	<dependencyManagement>
		<dependencies>
		  ...
			<!-- Spring Boot Dependencies -->
			<dependency>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-dependencies</artifactId>
				<version>1.3.3.RELEASE</version>
				<type>pom</type>
				<scope>import</scope>
			</dependency>
		</dependencies>
	</dependencyManagement>
</project>
```
3. Add the `spring-boot-starter-web` dependency to the `pom.xml` of the `helloworld.server` module and and remove the explicit dependency to the `javax.servlet` as this is already included in `spring-boot-starter-web`:
```
<project ...
  
  ...
	<dependencies>
    ...
<!-- Spring Boot already contains the servlet api  -->
<!-- 		<dependency> -->
<!-- 			<groupId>javax.servlet</groupId> -->
<!-- 			<artifactId>javax.servlet-api</artifactId> -->
<!-- 		</dependency> -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
	</dependencies>
</project>
```

## Starting SpringBoot and creating a REST service

The integration of SpringBoot into the Scout server is implemented in the package `org.eclipse.scout.apps.helloworld.server.spring` of the `helloworld.server` module.

```
@SpringBootApplication
public class PlatformListener implements IPlatformListener {
	private static final Logger LOG = LoggerFactory.getLogger(PlatformListener.class);

	@Override
	public void stateChanged(PlatformEvent event) {
		if (event.getState() == State.BeanManagerValid) {
			LOG.info("Scout platform ready");
			
			String [] args = new String[] {};
			ApplicationContext ctx = SpringApplication.run(PlatformListener.class, args);
			
      String[] beanNames = ctx.getBeanDefinitionNames();
      Arrays.sort(beanNames);
      for (String beanName : beanNames) {
	     	LOG.info(beanName);
	    }			
		}
	}
}
```

In class `PlatformListener` method `SpringApplication.run` is called as soon as the Scout platform has started.
A REST service is then implemented in class `GreetingController`. 
Internally this REST service is accessing a Scout bean to get counter values.

```
@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
    	Long id = BEANS.get(Counter.class).nextValue();
    	String content = String.format(template, name);
        return new Greeting(id, content);
    }
}
```


