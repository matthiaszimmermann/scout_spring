package org.eclipse.scout.apps.helloworld.server.spring;

import java.util.HashSet;
import java.util.Set;

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
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

/**
 * Scout bean containing the Spring application context. 
 * On platform startup the Spring application context is created and on shutdown the Spring context is destroyed again.
 */
@ApplicationScoped
@CreateImmediately
public class ScoutSpringBridge {

	private AnnotationConfigWebApplicationContext m_springContext;

	/**
	 * Creates a Spring application context at application startup.
	 * During startup all classes implementing any of the following Spring annotations are registered with the Scout bean manager.
	 * {@link Component @Component}, {@link Service @Service}, {@link Controller @Controller}, {@link Repository @Repository}.
	 */
	@PostConstruct
	public void postConstruct() {
		m_springContext = new AnnotationConfigWebApplicationContext();
		m_springContext.register(SpringConfiguration.class);
		m_springContext.refresh();
		
		// Auto discover Spring beans
		Set<IClassInfo> knownAnnotatedTypes = new HashSet<>();
		knownAnnotatedTypes.addAll(ClassInventory.get().getKnownAnnotatedTypes(Component.class));
		knownAnnotatedTypes.addAll(ClassInventory.get().getKnownAnnotatedTypes(Service.class));
		knownAnnotatedTypes.addAll(ClassInventory.get().getKnownAnnotatedTypes(Controller.class));
		knownAnnotatedTypes.addAll(ClassInventory.get().getKnownAnnotatedTypes(Repository.class));
		
		// Register Spring beans in Scout bean manager
		for (IClassInfo classInfo : knownAnnotatedTypes) {
			registerSpringBean(classInfo.resolveClass());
		}
	}
	
	@PreDestroy // TODO mzi --> make this work
	public void destory() {
		m_springContext.close();
	}
	
	/** 
	 * Returns the Spring application context.
	 */
	public AnnotationConfigWebApplicationContext getSpringContext() {
		return m_springContext;
		
	}
	
	/** 
	 * Sets the Spring application context.
	 */
	public void setSpringContext(AnnotationConfigWebApplicationContext context) {
		m_springContext = context;
	}
	
	/**
	 * Registers the provided class with the Scout bean manager.
	 * This allows to retrieve it with {@link BEANS#get(Class)}.
	 * Method should only be used to register classes with one of the following Spring annotations: 
	 * {@link Component @Component}, {@link Service @Service}, 
	 * {@link Controller @Controller}, {@link Repository @Repository}.
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
	 * Work around class as Scout beans do not support the @PreDestroy annotation. 
	 */
	public static class PlatformListener implements IPlatformListener {

		@Override
		public void stateChanged(PlatformEvent event) {
			if (event.getState() == State.PlatformStopping) {
				BEANS.get(ScoutSpringBridge.class).destory();
			}
		}
	}
}
