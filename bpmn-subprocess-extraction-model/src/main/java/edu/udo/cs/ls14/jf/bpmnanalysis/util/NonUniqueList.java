package edu.udo.cs.ls14.jf.bpmnanalysis.util;

import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectEList;

public class NonUniqueList<E> extends EObjectEList<E> {

	/**
	 * https://bugs.eclipse.org/bugs/show_bug.cgi?id=89325
	 */
	
	private static final long serialVersionUID = 5365083419747685568L;

	public NonUniqueList(Class<?> dataClass, InternalEObject owner, int featureID) {
		super(dataClass, owner, featureID);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected boolean isUnique() {
		return false;
	}
}
