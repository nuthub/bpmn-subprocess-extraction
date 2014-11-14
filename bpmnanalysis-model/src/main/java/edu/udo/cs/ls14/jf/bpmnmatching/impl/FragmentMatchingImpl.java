/**
 */
package edu.udo.cs.ls14.jf.bpmnmatching.impl;

import edu.udo.cs.ls14.jf.bpmnmatching.BpmnMatchingPackage;
import edu.udo.cs.ls14.jf.bpmnmatching.FragmentMatching;
import edu.udo.cs.ls14.jf.bpmnmatching.FragmentPair;

import java.util.Collection;

import java.util.List;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Fragment Matching</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link edu.udo.cs.ls14.jf.bpmnmatching.impl.FragmentMatchingImpl#getPairs <em>Pairs</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FragmentMatchingImpl extends MinimalEObjectImpl.Container implements FragmentMatching {
	/**
	 * The cached value of the '{@link #getPairs() <em>Pairs</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPairs()
	 * @generated
	 * @ordered
	 */
	protected EList<FragmentPair> pairs;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FragmentMatchingImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BpmnMatchingPackage.Literals.FRAGMENT_MATCHING;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<FragmentPair> getPairs() {
		if (pairs == null) {
			pairs = new EObjectResolvingEList<FragmentPair>(FragmentPair.class, this, BpmnMatchingPackage.FRAGMENT_MATCHING__PAIRS);
		}
		return pairs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case BpmnMatchingPackage.FRAGMENT_MATCHING__PAIRS:
				return getPairs();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case BpmnMatchingPackage.FRAGMENT_MATCHING__PAIRS:
				getPairs().clear();
				getPairs().addAll((Collection<? extends FragmentPair>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case BpmnMatchingPackage.FRAGMENT_MATCHING__PAIRS:
				getPairs().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case BpmnMatchingPackage.FRAGMENT_MATCHING__PAIRS:
				return pairs != null && !pairs.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //FragmentMatchingImpl
