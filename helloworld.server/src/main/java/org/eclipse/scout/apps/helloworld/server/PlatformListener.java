package org.eclipse.scout.apps.helloworld.server;

import java.util.Arrays;

import org.eclipse.scout.rt.platform.IPlatform.State;
import org.eclipse.scout.rt.platform.IPlatformListener;
import org.eclipse.scout.rt.platform.PlatformEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

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
