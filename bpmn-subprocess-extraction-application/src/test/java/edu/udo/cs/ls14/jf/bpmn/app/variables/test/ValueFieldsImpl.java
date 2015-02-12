package edu.udo.cs.ls14.jf.bpmn.app.variables.test;

import org.camunda.bpm.engine.impl.persistence.entity.ByteArrayEntity;
import org.camunda.bpm.engine.impl.variable.ValueFields;

public class ValueFieldsImpl implements ValueFields {

	private ByteArrayEntity byteArr = new ByteArrayEntity();

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getByteArrayValueId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ByteArrayEntity getByteArrayValue() {
		return byteArr;
	}

	@Override
	public void setByteArrayValue(byte[] bytes) {
		byteArr.setBytes(bytes);
	}

	@Override
	public String getTextValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setTextValue(String textValue) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getTextValue2() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setTextValue2(String textValue2) {
		// TODO Auto-generated method stub

	}

	@Override
	public Long getLongValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLongValue(Long longValue) {
		// TODO Auto-generated method stub

	}

	@Override
	public Double getDoubleValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setDoubleValue(Double doubleValue) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object getCachedValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCachedValue(Object deserializedObject) {
		// TODO Auto-generated method stub

	}

}
