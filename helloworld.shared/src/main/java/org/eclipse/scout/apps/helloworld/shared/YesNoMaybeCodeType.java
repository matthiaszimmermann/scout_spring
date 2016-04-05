package org.eclipse.scout.apps.helloworld.shared;

import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.code.AbstractCode;
import org.eclipse.scout.rt.shared.services.common.code.AbstractCodeType;

public class YesNoMaybeCodeType extends AbstractCodeType<String, String> {

	private static final long serialVersionUID = 1L;
	public static final String ID = "YesNoMaybeCodeType";

	@Override
	public String getId() {
		return ID;
	}

	@Order(1000)
	public static class YesCode extends AbstractCode<String> {
		private static final long serialVersionUID = 1L;
		public static final String ID = "yes";

		@Override
		protected String getConfiguredText() {
			return TEXTS.get("Yes");
		}

		@Override
		public String getId() {
			return ID;
		}
	}

	@Order(2000)
	public static class NoCode extends AbstractCode<String> {
		private static final long serialVersionUID = 1L;
		public static final String ID = "no";

		@Override
		protected String getConfiguredText() {
			return TEXTS.get("No");
		}

		@Override
		public String getId() {
			return ID;
		}
	}


	@Order(3000)
	public static class MaybeCode extends AbstractCode<String> {
		private static final long serialVersionUID = 1L;
		public static final String ID = "maybe";

		@Override
		protected String getConfiguredText() {
			return TEXTS.get("Maybe");
		}

		@Override
		public String getId() {
			return ID;
		}
	}


}
