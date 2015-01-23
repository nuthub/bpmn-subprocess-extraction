/**
 */
package edu.udo.cs.ls14.jf.bpmntransformation.impl;

import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;

import edu.udo.cs.ls14.jf.bpmntransformation.BpmnTransformationPackage;
import edu.udo.cs.ls14.jf.bpmntransformation.ProcessTransformation;

import java.util.Map;

import org.eclipse.bpmn2.Definitions;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Process Transformation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link edu.udo.cs.ls14.jf.bpmntransformation.impl.ProcessTransformationImpl#getProcessMatching <em>Process Matching</em>}</li>
 *   <li>{@link edu.udo.cs.ls14.jf.bpmntransformation.impl.ProcessTransformationImpl#getResults <em>Results</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ProcessTransformationImpl extends MinimalEObjectImpl.Container implements ProcessTransformation {
	/**
	 * The cached value of the '{@link #getProcessMatching() <em>Process Matching</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProcessMatching()
	 * @generated
	 * @ordered
	 */
	protected ProcessMatching processMatching;

	/**
	 * The cached value of the '{@link #getResults() <em>Results</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResults()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, Definitions> results;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ProcessTransformationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BpmnTransformationPackage.Literals.PROCESS_TRANSFORMATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProcessMatching getProcessMatching() {
		return processMatching;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetProcessMatching(ProcessMatching newProcessMatching, NotificationChain msgs) {
		ProcessMatching oldProcessMatching = processMatching;
		processMatching = newProcessMatching;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BpmnTransformationPackage.PROCESS_TRANSFORMATION__PROCESS_MATCHING, oldProcessMatching, newProcessMatching);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setProcessMatching(ProcessMatching newProcessMatching) {
		if (newProcessMatching != processMatching) {
			NotificationChain msgs = null;
			if (processMatching != null)
				msgs = ((InternalEObject)processMatching).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BpmnTransformationPackage.PROCESS_TRANSFORMATION__PROCESS_MATCHING, null, msgs);
			if (newProcessMatching != null)
				msgs = ((InternalEObject)newProcessMatching).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BpmnTransformationPackage.PROCESS_TRANSFORMATION__PROCESS_MATCHING, null, msgs);
			msgs = basicSetProcessMatching(newProcessMatching, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BpmnTransformationPackage.PROCESS_TRANSFORMATION__PROCESS_MATCHING, newProcessMatching, newProcessMatching));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Map<String, Definitions> getResults() {
		if (results == null) {
			results = new EcoreEMap<String,Definitions>(BpmnTransformationPackage.Literals.TRANSFORMATION_RESULT_ENTRY, TransformationResultEntryImpl.class, this, BpmnTransformationPackage.PROCESS_TRANSFORMATION__RESULTS);
		}
		return results.map();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case BpmnTransformationPackage.PROCESS_TRANSFORMATION__PROCESS_MATCHING:
				return basicSetProcessMatching(null, msgs);
			case BpmnTransformationPackage.PROCESS_TRANSFORMATION__RESULTS:
				return ((InternalEList<?>)((EMap.InternalMapView<String, Definitions>)getResults()).eMap()).basicRemove(otherEnd, msgs);
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
			case BpmnTransformationPackage.PROCESS_TRANSFORMATION__PROCESS_MATCHING:
				return getProcessMatching();
			case BpmnTransformationPackage.PROCESS_TRANSFORMATION__RESULTS:
				if (coreType) return ((EMap.InternalMapView<String, Definitions>)getResults()).eMap();
				else return getResults();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case BpmnTransformationPackage.PROCESS_TRANSFORMATION__PROCESS_MATCHING:
				setProcessMatching((ProcessMatching)newValue);
				return;
			case BpmnTransformationPackage.PROCESS_TRANSFORMATION__RESULTS:
				((EStructuralFeature.Setting)((EMap.InternalMapView<String, Definitions>)getResults()).eMap()).set(newValue);
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
			case BpmnTransformationPackage.PROCESS_TRANSFORMATION__PROCESS_MATCHING:
				setProcessMatching((ProcessMatching)null);
				return;
			case BpmnTransformationPackage.PROCESS_TRANSFORMATION__RESULTS:
				getResults().clear();
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
			case BpmnTransformationPackage.PROCESS_TRANSFORMATION__PROCESS_MATCHING:
				return processMatching != null;
			case BpmnTransformationPackage.PROCESS_TRANSFORMATION__RESULTS:
				return results != null && !results.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ProcessTransformationImpl
