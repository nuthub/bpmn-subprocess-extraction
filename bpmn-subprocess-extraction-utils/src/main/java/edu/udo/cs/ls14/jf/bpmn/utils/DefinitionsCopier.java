package edu.udo.cs.ls14.jf.bpmn.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.UUID;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil.Copier;

public class DefinitionsCopier extends Copier {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7184121980550717203L;

	@Override
	public void copyReferences() {
		for (Map.Entry<EObject, EObject> entry : entrySet()) {
			try {
				Method methodToFind = entry.getValue().getClass()
						.getMethod("setId", new Class[] { String.class });
				methodToFind.invoke(entry.getValue(), "_"
						+ UUID.randomUUID().toString());
			} catch (NoSuchMethodException | SecurityException
					| IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
			}
		}
		super.copyReferences();
	};
	

}
