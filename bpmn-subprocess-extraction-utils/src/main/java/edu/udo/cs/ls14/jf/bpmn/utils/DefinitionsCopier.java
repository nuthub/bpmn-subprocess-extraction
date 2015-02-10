package edu.udo.cs.ls14.jf.bpmn.utils;

import java.lang.reflect.Method;
import java.util.UUID;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil.Copier;

/**
 * Sets random ids to every element of a definitions object, which has the
 * method setId(String) while deep copying that object.
 * 
 * @author Julian Flake
 *
 */
public class DefinitionsCopier extends Copier {

	private static final long serialVersionUID = 7184121980550717203L;

	@Override
	public void copyReferences() {
		// for every copied object
		for (EObject obj : values()) {
			try {
				// check, if it has a method setId(String)
				Method method = obj.getClass().getMethod("setId",
						new Class[] { String.class });
				// and invoke that method
				method.invoke(obj, "_" + UUID.randomUUID().toString());
			} catch (Exception e) {
				// do nothing, if method could not be called
			}
		}
		// proceed with super method
		super.copyReferences();
	}

}
