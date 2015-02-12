package edu.udo.cs.ls14.jf.bpmn.app.variables;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;

import org.camunda.bpm.engine.impl.variable.ValueFields;
import org.camunda.bpm.engine.impl.variable.VariableType;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;

import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis;
import edu.udo.cs.ls14.jf.bpmnanalysis.util.BpmnAnalysisResourceFactoryImpl;

/**
 * Variable type for EMF based ProcessAnalysis objects. This enables Camunda and
 * Activiti to use ProcessAnalysis objects as process variables.
 * 
 * @author Julian Flake
 *
 */
public class ProcessAnalysisType implements VariableType {

	private static final String EXTENSION = "bpmnanalysis";

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
		return ProcessAnalysis.class.getName();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isAbleToStore(Object value) {
		if (value == null) {
			return true;
		}
		return ProcessAnalysis.class.isAssignableFrom(value.getClass());
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
			Resource res = new BpmnAnalysisResourceFactoryImpl()
					.createResource(uri);
			ByteArrayInputStream bis = new ByteArrayInputStream(valueFields
					.getByteArrayValue().getBytes());
			res.load(bis, null);
			if (!(res.getContents().get(0) instanceof ProcessAnalysis)) {
				throw new IOException("Couldn't get Definitions from value!");
			}
			return (ProcessAnalysis) res.getContents().get(0);
		} catch (IOException e) {
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
			if (!(value instanceof ProcessAnalysis)) {
				throw new IOException(
						"ProcessAnalysisType:setValue() called with object of wrong type.");
			}
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			((ProcessAnalysis) value).eResource().save(bos, null);
			valueFields.setByteArrayValue(bos.toByteArray());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
