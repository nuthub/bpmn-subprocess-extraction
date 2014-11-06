/**
 */
package edu.udo.cs.ls14.jf.bpmnanalysis.impl;

import edu.udo.cs.ls14.jf.bpmnanalysis.BpmnAnalysisPackage;
import edu.udo.cs.ls14.jf.bpmnanalysis.NodeMatching;
import edu.udo.cs.ls14.jf.bpmnanalysis.NodePair;

import java.util.Collection;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Node Matching</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link edu.udo.cs.ls14.jf.bpmnanalysis.impl.NodeMatchingImpl#getPairs <em>Pairs</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class NodeMatchingImpl extends AnalysisResultImpl implements NodeMatching {
	/**
	 * The cached value of the '{@link #getPairs() <em>Pairs</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPairs()
	 * @generated
	 * @ordered
	 */
	protected EList<NodePair> pairs;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected NodeMatchingImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BpmnAnalysisPackage.Literals.NODE_MATCHING;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<NodePair> getPairs() {
		if (pairs == null) {
			pairs = new EObjectResolvingEList<NodePair>(NodePair.class, this, BpmnAnalysisPackage.NODE_MATCHING__PAIRS);
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
			case BpmnAnalysisPackage.NODE_MATCHING__PAIRS:
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
			case BpmnAnalysisPackage.NODE_MATCHING__PAIRS:
				getPairs().clear();
				getPairs().addAll((Collection<? extends NodePair>)newValue);
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
			case BpmnAnalysisPackage.NODE_MATCHING__PAIRS:
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
			case BpmnAnalysisPackage.NODE_MATCHING__PAIRS:
				return pairs != null && !pairs.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //NodeMatchingImpl
