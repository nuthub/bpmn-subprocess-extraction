package edu.udo.cs.ls14.jf.bpmn.app.variables;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;

import org.camunda.bpm.engine.impl.variable.ValueFields;
import org.camunda.bpm.engine.impl.variable.VariableType;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;

import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;
import edu.udo.cs.ls14.jf.bpmnmatching.util.BpmnMatchingResourceFactoryImpl;

public class ProcessMatchingType implements VariableType {

	private static final String EXTENSION = "bpmnmatching";

	@Override
	public String getTypeName() {
		return EXTENSION;
	}

	@Override
	public String getTypeNameForValue(Object value) {
		return ProcessMatching.class.getName();
	}

	@Override
	public boolean isAbleToStore(Object value) {
		if (value == null) {
			return true;
		}
		return ProcessMatching.class.isAssignableFrom(value.getClass());
	}

	@Override
	public boolean isCachable() {
		return true;
	}

	@Override
	public Object getValue(ValueFields valueFields) {
		try {
			URI uri = URI.createURI(UUID.randomUUID().toString() + "."
					+ EXTENSION);
			Resource res = new BpmnMatchingResourceFactoryImpl()
					.createResource(uri);
			ByteArrayInputStream bis = new ByteArrayInputStream(valueFields
					.getByteArrayValue().getBytes());
			res.load(bis, null);
			if (!(res.getContents().get(0) instanceof ProcessMatching)) {
				throw new IOException("Couldn't get Definitions from value!");
			}
			return (ProcessMatching) res.getContents().get(0);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void setValue(Object value, ValueFields valueFields) {
		try {
			if (!(value instanceof ProcessMatching)) {
				throw new IOException(
						"ProcessMatchingType:setValue() called with object of wrong type.");
			}
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			((ProcessMatching) value).eResource().save(bos, null);
			valueFields.setByteArrayValue(bos.toByteArray());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
