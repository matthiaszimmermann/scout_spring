package org.eclipse.scout.apps.helloworld.server.spring;

import org.eclipse.scout.rt.platform.IPlatform.State;
import org.eclipse.scout.rt.platform.IPlatformListener;
import org.eclipse.scout.rt.platform.PlatformEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class PlatformListener 
	extends SpringBootServletInitializer 
	implements IPlatformListener  
{
	private static final Logger LOG = LoggerFactory.getLogger(PlatformListener.class);

	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(PlatformListener.class);
    }
	
	@Override
	public void stateChanged(PlatformEvent event) {
		if (event.getState() == State.BeanManagerValid) {
			LOG.info("Scout platform ready");

			SpringApplication app = new SpringApplication(PlatformListener.class);
			ApplicationContext ctx = app.run();
			SPRING.setContext(ctx);
			LOG.info("Spring platform ready");
		}
	}
}
