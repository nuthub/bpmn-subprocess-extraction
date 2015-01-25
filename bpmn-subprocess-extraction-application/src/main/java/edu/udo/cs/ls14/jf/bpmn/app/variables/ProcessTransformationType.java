package edu.udo.cs.ls14.jf.bpmn.app.variables;

import org.camunda.bpm.engine.impl.variable.ValueFields;
import org.camunda.bpm.engine.impl.variable.VariableType;

import edu.udo.cs.ls14.jf.bpmn.utils.EObjectXmlConverter;
import edu.udo.cs.ls14.jf.bpmntransformation.ProcessTransformation;

public class ProcessTransformationType implements VariableType {

	private static final String EXTENSION = "bpmntransformation";

	@Override
	public String getTypeName() {
		return EXTENSION;
	}

	@Override
	public String getTypeNameForValue(Object value) {
		return ProcessTransformation.class.getName();
	}

	@Override
	public boolean isAbleToStore(Object value) {
		if (value == null) {
			return true;
		}
		return ProcessTransformation.class.isAssignableFrom(value.getClass());
	}

	@Override
	public boolean isCachable() {
		return true;
	}

	@Override
	public Object getValue(ValueFields valueFields) {
		try {
			return EObjectXmlConverter.xml2EObject(EXTENSION, new String(
					valueFields.getByteArrayValue().getBytes()));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void setValue(Object value, ValueFields valueFields) {
		try {
			valueFields.setByteArrayValue(EObjectXmlConverter.eObject2Xml(
					EXTENSION, (ProcessTransformation) value).getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}