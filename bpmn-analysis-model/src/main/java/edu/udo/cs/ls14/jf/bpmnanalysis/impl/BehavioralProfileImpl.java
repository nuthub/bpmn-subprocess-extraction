/**
 */
package edu.udo.cs.ls14.jf.bpmnanalysis.impl;

import edu.udo.cs.ls14.jf.bpmnanalysis.BehavioralProfile;
import edu.udo.cs.ls14.jf.bpmnanalysis.BehavioralRelation;
import edu.udo.cs.ls14.jf.bpmnanalysis.BpmnAnalysisPackage;

import edu.udo.cs.ls14.jf.bpmnanalysis.Trace;
import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Behavioral Profile</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link edu.udo.cs.ls14.jf.bpmnanalysis.impl.BehavioralProfileImpl#getTraces <em>Traces</em>}</li>
 *   <li>{@link edu.udo.cs.ls14.jf.bpmnanalysis.impl.BehavioralProfileImpl#getRelations <em>Relations</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class BehavioralProfileImpl extends AnalysisResultImpl implements BehavioralProfile {
	/**
	 * The cached value of the '{@link #getTraces() <em>Traces</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTraces()
	 * @generated
	 * @ordered
	 */
	protected EList<Trace> traces;
	/**
	 * The cached value of the '{@link #getRelations() <em>Relations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRelations()
	 * @generated
	 * @ordered
	 */
	protected EList<BehavioralRelation> relations;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BehavioralProfileImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BpmnAnalysisPackage.Literals.BEHAVIORAL_PROFILE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Trace> getTraces() {
		if (traces == null) {
			traces = new EObjectContainmentEList<Trace>(Trace.class, this, BpmnAnalysisPackage.BEHAVIORAL_PROFILE__TRACES);
		}
		return traces;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<BehavioralRelation> getRelations() {
		if (relations == null) {
			relations = new EObjectContainmentEList<BehavioralRelation>(BehavioralRelation.class, this, BpmnAnalysisPackage.BEHAVIORAL_PROFILE__RELATIONS);
		}
		return relations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case BpmnAnalysisPackage.BEHAVIORAL_PROFILE__TRACES:
				return ((InternalEList<?>)getTraces()).basicRemove(otherEnd, msgs);
			case BpmnAnalysisPackage.BEHAVIORAL_PROFILE__RELATIONS:
				return ((InternalEList<?>)getRelations()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case BpmnAnalysisPackage.BEHAVIORAL_PROFILE__TRACES:
				return getTraces();
			case BpmnAnalysisPackage.BEHAVIORAL_PROFILE__RELATIONS:
				return getRelations();
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
			case BpmnAnalysisPackage.BEHAVIORAL_PROFILE__TRACES:
				getTraces().clear();
				getTraces().addAll((Collection<? extends Trace>)newValue);
				return;
			case BpmnAnalysisPackage.BEHAVIORAL_PROFILE__RELATIONS:
				getRelations().clear();
				getRelations().addAll((Collection<? extends BehavioralRelation>)newValue);
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
			case BpmnAnalysisPackage.BEHAVIORAL_PROFILE__TRACES:
				getTraces().clear();
				return;
			case BpmnAnalysisPackage.BEHAVIORAL_PROFILE__RELATIONS:
				getRelations().clear();
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
			case BpmnAnalysisPackage.BEHAVIORAL_PROFILE__TRACES:
				return traces != null && !traces.isEmpty();
			case BpmnAnalysisPackage.BEHAVIORAL_PROFILE__RELATIONS:
				return relations != null && !relations.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //BehavioralProfileImpl
