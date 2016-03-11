package org.eclipse.scout.apps.helloworld.shared.text;

import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.shared.services.common.text.AbstractDynamicNlsTextProviderService;

/**
 * <h3>{@link DefaultTextProviderService}</h3>
 *
 * @author mzi
 */
@Order(-2000)
public class DefaultTextProviderService extends AbstractDynamicNlsTextProviderService {
	@Override
	protected String getDynamicNlsBaseName() {
		return "org.eclipse.scout.apps.helloworld.shared.texts.Texts";
	}
}
