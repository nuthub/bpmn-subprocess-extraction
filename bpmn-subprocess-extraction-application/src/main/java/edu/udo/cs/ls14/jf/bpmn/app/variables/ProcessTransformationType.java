package edu.udo.cs.ls14.jf.bpmn.app.variables;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;

import org.camunda.bpm.engine.impl.variable.ValueFields;
import org.camunda.bpm.engine.impl.variable.VariableType;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;

import edu.udo.cs.ls14.jf.bpmntransformation.ProcessTransformation;
import edu.udo.cs.ls14.jf.bpmntransformation.util.BpmnTransformationResourceFactoryImpl;

/**
 * Variable type for EMF based ProcessTransformation objects. This enables
 * Camunda and Activiti to use ProcessTransformation objects as process
 * variables.
 * 
 * @author Julian Flake
 *
 */
public class ProcessTransformationType implements VariableType {

	private static final String EXTENSION = "bpmntransformation";

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getTypeName() {
		return EXTENSION;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getTypeNameForValue(Object value) {
		return ProcessTransformation.class.getName();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isAbleToStore(Object value) {
		if (value == null) {
			return true;
		}
		return ProcessTransformation.class.isAssignableFrom(value.getClass());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isCachable() {
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object getValue(ValueFields valueFields) {
		try {
			URI uri = URI.createURI(UUID.randomUUID().toString() + "."
					+ EXTENSION);
			Resource res = new BpmnTransformationResourceFactoryImpl()
					.createResource(uri);
			ByteArrayInputStream bis = new ByteArrayInputStream(valueFields
					.getByteArrayValue().getBytes());
			res.load(bis, null);
			return (ProcessTransformation) res.getContents().get(0);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setValue(Object value, ValueFields valueFields) {
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			((ProcessTransformation) value).eResource().save(bos, null);
			valueFields.setByteArrayValue(bos.toByteArray());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
