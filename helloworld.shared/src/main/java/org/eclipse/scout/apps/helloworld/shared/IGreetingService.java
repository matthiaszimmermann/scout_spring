package org.eclipse.scout.apps.helloworld.shared;

import org.eclipse.scout.apps.helloworld.shared.helloworld.GreetingFormData;
import org.eclipse.scout.rt.platform.service.IService;
import org.eclipse.scout.rt.shared.TunnelToServer;

/**
 * <h3>{@link IGreetingService}</h3>
 *
 * @author mzi
 */
@TunnelToServer
public interface IGreetingService extends IService {
	GreetingFormData load(GreetingFormData input);
}
