package org.eclipse.scout.apps.helloworld.shared.helloworld;

import org.eclipse.scout.rt.platform.service.IService;
import org.eclipse.scout.rt.shared.TunnelToServer;

/**
 * <h3>{@link IHelloWorldFormService}</h3>
 *
 * @author mzi
 */
@TunnelToServer
public interface IHelloWorldFormService extends IService {
	HelloWorldFormData load(HelloWorldFormData input);
}
