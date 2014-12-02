package edu.udo.cs.ls14.jf.bpmn.variables;

import org.camunda.bpm.engine.impl.variable.ValueFields;
import org.camunda.bpm.engine.impl.variable.VariableType;
import org.eclipse.bpmn2.Definitions;

import edu.udo.cs.ls14.jf.bpmn.utils.EObjectXmlConverter;

public class DefinitionsType implements VariableType {

	private static final String EXTENSION = "bpmn";

	@Override
	public String getTypeName() {
		return EXTENSION;
	}

	@Override
	public String getTypeNameForValue(Object value) {
		return Definitions.class.getName();
	}

	@Override
	public boolean isAbleToStore(Object value) {
		if (value == null) {
			return true;
		}
		return Definitions.class.isAssignableFrom(value.getClass());
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
					EXTENSION, (Definitions) value).getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
