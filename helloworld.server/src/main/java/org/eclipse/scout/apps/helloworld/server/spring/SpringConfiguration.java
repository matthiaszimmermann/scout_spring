package org.eclipse.scout.apps.helloworld.server.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Main Spring configuration class used in {@link SpringScoutBridge}.
 */
@Configuration
// @EnableWebMvc // (does not work)
@ComponentScan(basePackages = "org.eclipse.scout.apps.helloworld.server.spring")
public class SpringConfiguration {

}