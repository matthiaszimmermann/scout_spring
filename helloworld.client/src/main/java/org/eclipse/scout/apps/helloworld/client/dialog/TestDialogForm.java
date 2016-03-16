package org.eclipse.scout.apps.helloworld.client.dialog;

import org.eclipse.scout.apps.helloworld.client.dialog.TestDialogForm.MainBox.OkButton;
import org.eclipse.scout.apps.helloworld.client.helloworld.HelloWorldForm.MainBox.TopBox;
import org.eclipse.scout.apps.helloworld.shared.helloworld.TestDialogFormData;
import org.eclipse.scout.rt.client.dto.FormData;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCancelButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractOkButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.shared.AbstractIcons;

/**
 * <h3>{@link TestDialogForm}</h3>
 *
 * @author mzi
 */
@FormData(value = TestDialogFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class TestDialogForm extends AbstractForm {

	public TestDialogForm() {
		setHandler(new ViewHandler());
	}

	@Override
	protected String getConfiguredTitle() {
		return "Test Dialog";
	}
	
	@Override
	protected boolean getConfiguredAskIfNeedSave() {
		return false;
	}
	
	@Override
	protected String getConfiguredIconId() {
		return AbstractIcons.Star;
	}

	public MainBox getMainBox() {
		return getFieldByClass(MainBox.class);
	}

	public OkButton getOkButton() {
		return getFieldByClass(OkButton.class);
	}

	public TopBox getTopBox() {
		return getFieldByClass(TopBox.class);
	}

	@Order(1000)
	public class MainBox extends AbstractGroupBox {

		@Order(1000)
		public class TopBox extends AbstractGroupBox {

			@Order(1000)
			public class Field1 extends AbstractStringField {
				@Override
				protected String getConfiguredLabel() {
					return "Field 1";
				}
			}
			
			@Order(2000)
			public class Field2 extends AbstractStringField {
				@Override
				protected String getConfiguredLabel() {
					return "Field 2";
				}
			}
			
			@Order(3000)
			public class Field3 extends AbstractStringField {
				@Override
				protected String getConfiguredLabel() {
					return "Field 3";
				}
			}
			
			@Order(4000)
			public class Field4 extends AbstractStringField {
				@Override
				protected String getConfiguredLabel() {
					return "Field 4";
				}
			}
			
			@Order(5000)
			public class Field5 extends AbstractStringField {
				@Override
				protected String getConfiguredLabel() {
					return "Field 5";
				}
			}
			
			@Order(6000)
			public class Field6 extends AbstractStringField {
				@Override
				protected String getConfiguredLabel() {
					return "Field 6";
				}
			}
		}

		@Order(2000)
		public class OkButton extends AbstractOkButton {
		}

		@Order(3000)
		public class CancelButton extends AbstractCancelButton {
		}
		
		
	}

	public class ViewHandler extends AbstractFormHandler {
	}
}
