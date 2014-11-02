package edu.udo.cs.ls14.jf.activiti.variables;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.activiti.engine.impl.variable.ValueFields;
import org.activiti.engine.impl.variable.VariableType;
import org.eclipse.bpmn2.util.Bpmn2ResourceFactoryImpl;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;

public class ResourceType implements VariableType {

	public String getTypeName() {
		return "resource";
	}

	public boolean isAbleToStore(Object value) {
		if (value == null) {
			return false;
		}
		return Resource.class.isAssignableFrom(value.getClass());
	}

	public boolean isCachable() {
		return false;
	}

	public void setValue(Object value, ValueFields valueFields) {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		Resource res = (Resource) value;
		try {
			res.save(os, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		ByteArrayOutputStream os = new ByteArrayOutputStream();
//		Resource res = (Resource) value;
//		XMLResource xres = new XMLResourceImpl();
//		xres.getContents().addAll(res.getContents());
//		try {
//			xres.save(os, null);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		valueFields.setBytes(os.toByteArray());
		valueFields.setTextValue(res.getURI().toString());
	}

	public Object getValue(ValueFields valueFields) {
		try {
			return getResource(valueFields.getTextValue(),
					valueFields.getBytes());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	private Resource getResource(String uriString, byte[] byteArr)
			throws Exception {
//		registerFactories();
		URI uri = URI.createURI(uriString);
		ResourceSet resSet = new ResourceSetImpl();
		Resource res = resSet.createResource(uri);
		res.load(new ByteArrayInputStream(byteArr), null);
//		XMLResource xres = new XMLResourceImpl();
//		xres.load(new ByteArrayInputStream(byteArr), null);
		return res;
	}

//	private static void registerFactories() {
//		if (!Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap()
//				.containsKey("bpmn2")) {
//			Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(
//					"bpmn2", new Bpmn2ResourceFactoryImpl());
//		}
//		if (!Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap()
//				.containsKey("bpmn")) {
//			Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(
//					"bpmn", new Bpmn2ResourceFactoryImpl());
//		}
//	}
}
