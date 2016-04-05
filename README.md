# Eclipse Scout Spring Integration
This example project combines Eclipse Scout and Spring Core/Context/Webmvc into a single application.

The setup integrates the standard Eclipse Scout Hello World application (Scout Neon M6) with the basics provided in [https://spring.io/guides/gs/rest-service/](Spring Boot REST tutorial)

## Demo
1. Start the application using launcher `helloworld-all-dev.launch` in module `helloworld.all.app.dev`
3. Use the application
  * [http://localhost:8082/](http://localhost:8082/): Scout HTML5 user interface 
  * [http://localhost:8082/greeting?name=Alice](http://localhost:8082/greeting?name=Alice): Spring Boot REST service

## Adding Spring to the Eclipse Scout Hello World
1. Create a standard Eclipse Scout "Hello World"
2. Add `spring-webmvc` to the `pom.xml` of the `helloworld` module:
```
<project ...
  
	<properties>
		...
		<org.springframework.version>4.2.5.RELEASE</org.springframework.version>
		<com.fasterxml.jackson.version>2.5.3</com.fasterxml.jackson.version>
		...
	</properties>
  
  	...
  
	<dependencyManagement>
		<dependencies>
		  	...
			<!-- Spring Dependencies -->
			<dependency>
				<groupId>org.springframework</groupId>
				<artifactId>spring-webmvc</artifactId>
				<version>${org.springframework.version}</version>
			</dependency>
			
			<!-- Jackson Dependencies -->
			<dependency>
				<groupId>com.fasterxml.jackson.core</groupId>
				<artifactId>jackson-databind</artifactId>
				<version>${com.fasterxml.jackson.version}</version>
			</dependency>
		</dependencies>
	</dependencyManagement>
</project>
```
3. Add the `spring-webmvc` and the `jackson-databind` dependency to the `pom.xml` of the `helloworld.server` module.
```
<project ...
  
  	...
	<dependencies>
    		...
		<!-- springframework dependencies: spring-webmvc needed for providing rest services -->
		<!-- includes spring-core and spring-context -->
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-webmvc</artifactId>
		</dependency>
		
		<!-- jackson dependencies: message conversion to json -->
		<!-- TODO mzi: verify if this is really necessary-->
		<dependency>
			<groupId>com.fasterxml.jackson.core</groupId>
			<artifactId>jackson-databind</artifactId>
		</dependency>
	</dependencies>
</project>
```

## Starting SpringBoot and creating a REST service

The integration of Spring into the Scout server is implemented in the package `org.eclipse.scout.apps.helloworld.server.spring` of the `helloworld.server` module.

Class `SpringScoutBridge` starts immediately after the Scout platform is available. 
This class creates the Spring context to access all Spring components and registers the components with the Scout bean manager.
The class `SpringScoutBridge` also invokes the configuration class `SpringConfiguration`.

The Spring REST service mentioned above is then implemented in class `GreetingRestController`. 
Internally this REST service is accessing both a Scout bean to get counter values and makes use of the Spring @Autowired feature.

```
@RestController
public class GreetingRestController {

  @Autowired
  Hello hello;

  @RequestMapping("/greeting")
  @ResponseBody
  public String greeting(@RequestParam(value="name", defaultValue="World") String name) {
    	Long id = BEANS.get(Counter.class).nextValue(); // access a Scout bean
	String content = hello.getText(name); // access a Spring component
		
	return new Greeting(id, content).toString();
  }
}
```

Finally, the Scout bean `GreetingService` shows how to access Spring components from the context of Scout beans.

```
public class GreetingService implements IGreetingService {

	@Override
	public GreetingFormData load(GreetingFormData input) {
    	String user = ServerSession.get().getUserId();
    	
    	Long id = BEANS.get(Counter.class).nextValue(); // access a Scout bean
    	String content = BEANS.get(Hello.class).getText(user); // accessing a Spring component as a Scout bean
		    	
		input.getMessage().setValue((new Greeting(id, content)).toString());
		
		return input;
	}
}
```
