package org.eclipse.scout.apps.helloworld.client;

import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithNodes;
import org.eclipse.scout.rt.client.ui.form.IForm;
import org.eclipse.scout.rt.shared.TEXTS;

/**
 * <h3>{@link GreetingPage}</h3>
 *
 * @author mzi
 */
public class GreetingPage extends AbstractPageWithNodes {

	@Override
	protected boolean getConfiguredLeaf() {
		return true;
	}

	@Override
	protected boolean getConfiguredTableVisible() {
		return false;
	}

	@Override
	protected String getConfiguredTitle() {
		return TEXTS.get("HelloWorld");
	}

	@Override
	protected Class<? extends IForm> getConfiguredDetailForm() {
		return GreetingForm.class;
	}
}
