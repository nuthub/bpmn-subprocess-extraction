package edu.udo.cs.ls14.jf.bpmn.app.variables;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.UUID;

import org.camunda.bpm.engine.impl.variable.ValueFields;
import org.camunda.bpm.engine.impl.variable.VariableType;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.DocumentRoot;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;

import edu.udo.cs.ls14.jf.bpmn.util.Bpmn2ResourceSet;

/**
 * Variable type for EMF based BPMN Definitions. This enables Camunda and
 * Activiti to use Definitions objects as process variables.
 * 
 * @author Julian Flake
 *
 */
public class DefinitionsType implements VariableType {

	private static final String EXTENSION = "bpmn";

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
		return Definitions.class.getName();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isAbleToStore(Object value) {
		if (value == null) {
			return true;
		}
		return Definitions.class.isAssignableFrom(value.getClass());
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
			Resource res = Bpmn2ResourceSet.getInstance().createResource(uri);
			ByteArrayInputStream bis = new ByteArrayInputStream(valueFields
					.getByteArrayValue().getBytes());
			res.load(bis, null);
			Definitions defs = ((DocumentRoot) res.getContents().get(0))
					.getDefinitions();
			return defs;
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
			((Definitions) value).eResource().save(bos, null);
			valueFields.setByteArrayValue(bos.toByteArray());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
