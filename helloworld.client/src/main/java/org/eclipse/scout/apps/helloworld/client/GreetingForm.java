package org.eclipse.scout.apps.helloworld.client;

import org.eclipse.scout.apps.helloworld.client.GreetingForm.MainBox.TopBox;
import org.eclipse.scout.apps.helloworld.client.GreetingForm.MainBox.TopBox.MessageField;
import org.eclipse.scout.apps.helloworld.client.GreetingForm.MainBox.TopBox.OpenDialogButton;
import org.eclipse.scout.apps.helloworld.client.GreetingForm.MainBox.TopBox.QuestionButtonGroupBox;
import org.eclipse.scout.apps.helloworld.client.dialog.TestDialogForm;
import org.eclipse.scout.apps.helloworld.shared.IGreetingService;
import org.eclipse.scout.apps.helloworld.shared.YesNoMaybeCodeType;
import org.eclipse.scout.apps.helloworld.shared.helloworld.GreetingFormData;
import org.eclipse.scout.rt.client.dto.FormData;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.radiobuttongroup.AbstractRadioButtonGroup;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.shared.AbstractIcons;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.code.ICodeType;

/**
 * <h3>{@link GreetingForm}</h3>
 *
 * @author mzi
 */
@FormData(value = GreetingFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class GreetingForm extends AbstractForm {

	public GreetingForm() {
		setHandler(new ViewHandler());
	}

	@Override
	protected boolean getConfiguredAskIfNeedSave() {
		return false;
	}

	@Override
	protected int getConfiguredModalityHint() {
		return MODALITY_HINT_MODELESS;
	}

	@Override
	protected String getConfiguredIconId() {
		return AbstractIcons.World;
	}

	public MainBox getMainBox() {
		return getFieldByClass(MainBox.class);
	}

	public OpenDialogButton getOpenDialogButton() {
		return getFieldByClass(OpenDialogButton.class);
	}

	public QuestionButtonGroupBox getQuestionButtonGroupBox() {
		return getFieldByClass(QuestionButtonGroupBox.class);
	}

	public TopBox getTopBox() {
		return getFieldByClass(TopBox.class);
	}

	public MessageField getMessageField() {
		return getFieldByClass(MessageField.class);
	}

	@Order(1000)
	public class MainBox extends AbstractGroupBox {

		@Order(1000)
		public class TopBox extends AbstractGroupBox {

			@Override
			protected String getConfiguredLabel() {
				return TEXTS.get("MessageFromServer");
			}

			@Order(1000)
			public class MessageField extends AbstractStringField {
				@Override
				protected int getConfiguredGridW() {
					return 2;
				}

				@Override
				protected String getConfiguredLabel() {
					return TEXTS.get("Message");
				}

				@Override
				protected boolean getConfiguredEnabled() {
					return false;
				}
			}

			@Order(2000)
			public class QuestionButtonGroupBox extends AbstractRadioButtonGroup<String> {
				@Override
				protected String getConfiguredLabel() {
					return TEXTS.get("YesNo");
				}
				
				@Override
				protected Class<? extends ICodeType<?, String>> getConfiguredCodeType() {
					return YesNoMaybeCodeType.class;
				}
				
				// return value has to match number of options
				@Override
                protected int getConfiguredGridH() {
                       return 3;
                }
			}

			@Order(3000)
			public class OpenDialogButton extends AbstractButton {
				@Override
				protected String getConfiguredLabel() {
					return TEXTS.get("OpenDialogForm");
				}

				@Override
				protected boolean getConfiguredProcessButton() {
					return true;
				}
				
				@Override
				protected void execClickAction() {
					TestDialogForm form = new TestDialogForm();
					form.start();
				}
			}
			
			
		}
	}

	public class ViewHandler extends AbstractFormHandler {

		@Override
		protected void execLoad() {
			IGreetingService service = BEANS.get(IGreetingService.class);
			GreetingFormData formData = new GreetingFormData();
			exportFormData(formData);
			formData = service.load(formData);
			importFormData(formData);
		}
	}
}
