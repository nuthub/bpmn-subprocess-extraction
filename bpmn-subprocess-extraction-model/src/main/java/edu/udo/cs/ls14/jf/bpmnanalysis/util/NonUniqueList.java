package edu.udo.cs.ls14.jf.bpmnanalysis.util;

import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectEList;

/**
 * Returns false for isUnique() calls. Also see
 * https://bugs.eclipse.org/bugs/show_bug.cgi?id=89325. With this list type it
 * is possible to create 0..* associations which contain a single object
 * multiple times.
 * 
 * @author Julian Flake
 *
 * @param <E>
 *            the associated type
 */
public class NonUniqueList<E> extends EObjectEList<E> {

	/**
	 * generaed UID
	 */
	private static final long serialVersionUID = 5365083419747685568L;

	/**
	 * {@inheritDoc}
	 */
	public NonUniqueList(Class<?> dataClass, InternalEObject owner,
			int featureID) {
		super(dataClass, owner, featureID);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected boolean isUnique() {
		return false;
	}
}
