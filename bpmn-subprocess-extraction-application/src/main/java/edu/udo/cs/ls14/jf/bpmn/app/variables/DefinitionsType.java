package edu.udo.cs.ls14.jf.bpmn.app.variables;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;

import org.camunda.bpm.engine.impl.variable.ValueFields;
import org.camunda.bpm.engine.impl.variable.VariableType;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;

import edu.udo.cs.ls14.jf.bpmn.utils.Bpmn2ResourceSet;

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
			URI uri = URI.createURI(UUID.randomUUID().toString() + "."
					+ EXTENSION);
			// Resource res = new
			// Bpmn2ResourceFactoryImpl().createResource(uri);
			Resource res = Bpmn2ResourceSet.getInstance().createResource(uri);
			ByteArrayInputStream bis = new ByteArrayInputStream(valueFields
					.getByteArrayValue().getBytes());
			res.load(bis, null);
			if (!(res.getContents().get(0) instanceof Definitions)) {
				throw new IOException("Couldn't get Definitions from value!");
			}
			return (Definitions) res.getContents().get(0);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void setValue(Object value, ValueFields valueFields) {
		try {
			if (!(value instanceof Definitions)) {
				throw new IOException(
						"DefinitionsType:setValue() called with object of wrong type.");
			}
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			((Definitions) value).eResource().save(bos, null);
			valueFields.setByteArrayValue(bos.toByteArray());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
