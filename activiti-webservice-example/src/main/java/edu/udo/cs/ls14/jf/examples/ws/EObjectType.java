package edu.udo.cs.ls14.jf.examples.ws;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.activiti.engine.impl.variable.ValueFields;
import org.activiti.engine.impl.variable.VariableType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;

public class EObjectType implements VariableType {

	public String getTypeName() {
		return "eobject";
	}

	public boolean isAbleToStore(Object value) {
		if (value == null) {
			return true;
		}
		return EObject.class.isAssignableFrom(value.getClass());
	}

	public boolean isCachable() {
		return true;
	}

	public void setValue(Object value, ValueFields valueFields) {
		XMLResource xres = new XMLResourceImpl();
		xres.getContents().add((EObject) value);
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			xres.save(os, null);
		} catch (IOException e) {
			// TODO: implement exception handling
			e.printStackTrace();
		}
		valueFields.setBytes(os.toByteArray());
	}

	public Object getValue(ValueFields valueFields) {
		try {
			XMLResource xres = new XMLResourceImpl();
			xres.load(new ByteArrayInputStream(valueFields.getBytes()), null);
			return xres.getContents().get(0);
		} catch (IOException e) {
			// TODO: implement exception handling
			e.printStackTrace();
		}
		return null;
	}

}
