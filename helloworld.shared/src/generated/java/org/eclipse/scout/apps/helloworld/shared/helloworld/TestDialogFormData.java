package org.eclipse.scout.apps.helloworld.shared.helloworld;

import javax.annotation.Generated;

import org.eclipse.scout.rt.shared.data.form.AbstractFormData;
import org.eclipse.scout.rt.shared.data.form.fields.AbstractValueFieldData;

/**
 * <b>NOTE:</b><br>
 * This class is auto generated by the Scout SDK. No manual modifications
 * recommended.
 */
@Generated(value = "org.eclipse.scout.apps.helloworld.client.dialog.TestDialogForm", comments = "This class is auto generated by the Scout SDK. No manual modifications recommended.")
public class TestDialogFormData extends AbstractFormData {

	private static final long serialVersionUID = 1L;

	public Field1 getField1() {
		return getFieldByClass(Field1.class);
	}

	public Field2 getField2() {
		return getFieldByClass(Field2.class);
	}

	public Field3 getField3() {
		return getFieldByClass(Field3.class);
	}

	public Field4 getField4() {
		return getFieldByClass(Field4.class);
	}

	public Field5 getField5() {
		return getFieldByClass(Field5.class);
	}

	public Field6 getField6() {
		return getFieldByClass(Field6.class);
	}

	public static class Field1 extends AbstractValueFieldData<String> {

		private static final long serialVersionUID = 1L;
	}

	public static class Field2 extends AbstractValueFieldData<String> {

		private static final long serialVersionUID = 1L;
	}

	public static class Field3 extends AbstractValueFieldData<String> {

		private static final long serialVersionUID = 1L;
	}

	public static class Field4 extends AbstractValueFieldData<String> {

		private static final long serialVersionUID = 1L;
	}

	public static class Field5 extends AbstractValueFieldData<String> {

		private static final long serialVersionUID = 1L;
	}

	public static class Field6 extends AbstractValueFieldData<String> {

		private static final long serialVersionUID = 1L;
	}
}
