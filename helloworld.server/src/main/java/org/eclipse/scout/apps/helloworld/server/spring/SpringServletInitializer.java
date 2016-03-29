package org.eclipse.scout.apps.helloworld.server.spring;

import org.eclipse.scout.rt.platform.BEANS;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

// web.xml based (works)
// see http://www.programming-free.com/2014/01/spring-mvc-40-restful-web-services.html
public class SpringServletInitializer extends DispatcherServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected WebApplicationContext createWebApplicationContext(ApplicationContext parent) {
		return BEANS.get(ScoutSpringBridge.class).getSpringContext();
	}
}

// other approach (without web.xml - doesn't work)
// http://websystique.com/springmvc/spring-mvc-4-restful-web-services-crud-example-resttemplate/
//public class SpringServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
// 
//    @Override
//    protected Class<?>[] getRootConfigClasses() {
//        return new Class[] { SpringConfiguration.class };
//    }
//  
//    @Override
//    protected Class<?>[] getServletConfigClasses() {
//        return null;
//    }
//    
//    @Override
//    protected WebApplicationContext createServletApplicationContext() {
//    	return BEANS.get(SpringContext.class).getContext();
//    }
//  
//    @Override
//    protected String[] getServletMappings() {
//        return new String[] { "/api/" };
//    }
// 
//}