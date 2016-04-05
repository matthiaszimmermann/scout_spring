package org.eclipse.scout.apps.helloworld.server.spring;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.eclipse.scout.rt.platform.ApplicationScoped;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.BeanMetaData;
import org.eclipse.scout.rt.platform.CreateImmediately;
import org.eclipse.scout.rt.platform.IBean;
import org.eclipse.scout.rt.platform.IBeanInstanceProducer;
import org.eclipse.scout.rt.platform.IPlatform.State;
import org.eclipse.scout.rt.platform.IPlatformListener;
import org.eclipse.scout.rt.platform.PlatformEvent;
import org.eclipse.scout.rt.platform.inventory.ClassInventory;
import org.eclipse.scout.rt.platform.inventory.IClassInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

/**
 * Scout bean containing the Spring application context. 
 * On platform startup the Spring application context is created and on shutdown the Spring context is destroyed again.
 * Spring beans are auto-discovered and registered with the Scout bean manager.
 */
@ApplicationScoped
@CreateImmediately
public class SpringScoutBridge {

	/**
	 * Spring annotations to query for registration with the Scout bean manager.
	 * {@link Component}, {@link Service}, {@link Controller}, {@link Repository}.
	 */
	public static final List<Class<? extends Annotation>> SPRING_ANNOTATIONS = Arrays.asList(
			Component.class, 
			Service.class, 
			Controller.class, 
			Repository.class);
	
	private static final Logger LOG = LoggerFactory.getLogger(SpringScoutBridge.class);
	private AnnotationConfigWebApplicationContext m_springContext;

	/** 
	 * Returns the Spring application context.
	 */
	public AnnotationConfigWebApplicationContext getSpringContext() {
		return m_springContext;

	}

	/** 
	 * Sets the Spring application context.
	 */
	public void setSpringContext(AnnotationConfigWebApplicationContext springContext) {
		m_springContext = springContext;
	}
	
	/**
	 * Creates a Spring application context at application startup.
	 * During startup all classes annotated with any annotation defined in {@link #SPRING_ANNOTATIONS} 
	 * are registered with the Scout bean manager.
	 */
	@PostConstruct
	public void postConstruct() {
		m_springContext = new AnnotationConfigWebApplicationContext();
		m_springContext.register(SpringConfiguration.class);
		m_springContext.refresh();

		// Register Spring beans in Scout bean manager
		for (Class<?> springAnnotation: SPRING_ANNOTATIONS) {
			for (IClassInfo classInfo : ClassInventory.get().getKnownAnnotatedTypes(springAnnotation)) {
				Class<?> clazz = classInfo.resolveClass();
				registerSpringBean(clazz);
				
				String annotation = springAnnotation.getSimpleName();
				LOG.info("Registered Spring {} {}", annotation, clazz.getName());
			}
		}
		
		showBanner();
	}

	private void showBanner() {
		StringBuilder result = new StringBuilder("Show banner\n");
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("banner.txt").getFile());
		
		try (Scanner scanner = new Scanner(file)) {

			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				result.append(line).append("\n");
			}

			scanner.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		LOG.info(result.toString());
	}

	@PreDestroy // TODO mzi --> push for support of @PreDestroy for Scout beans 
	public void destory() {
		m_springContext.close();
	}

	/**
	 * Registers the provided class with the Scout bean manager.
	 * This allows to retrieve it with {@link BEANS#get(Class)}.
	 */
	public <T> void registerSpringBean(Class<T> springBeanClazz) {
		BEANS.getBeanManager().registerBean(new BeanMetaData(springBeanClazz).withProducer(new IBeanInstanceProducer<T>() {

			@Override
			public T produce(IBean<T> bean) {
				return m_springContext.getBean(springBeanClazz);
			}
		}));
	}

	/**
	 * Scout platform listener to destroy the Spring context.
	 * Work around class as Scout beans do not (yet) support the @PreDestroy annotation. 
	 */
	public static class PlatformListener implements IPlatformListener {

		@Override
		public void stateChanged(PlatformEvent event) {
			if (event.getState() == State.PlatformStopping) {
				BEANS.get(SpringScoutBridge.class).destory();
			}
		}
	}
}
