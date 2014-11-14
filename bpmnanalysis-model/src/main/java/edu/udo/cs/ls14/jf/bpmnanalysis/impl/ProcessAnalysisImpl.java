/**
 */
package edu.udo.cs.ls14.jf.bpmnanalysis.impl;

import edu.udo.cs.ls14.jf.bpmnanalysis.BpmnAnalysisPackage;
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis;
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysisResult;

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
 * An implementation of the model object '<em><b>Process Analysis</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link edu.udo.cs.ls14.jf.bpmnanalysis.impl.ProcessAnalysisImpl#getId <em>Id</em>}</li>
 *   <li>{@link edu.udo.cs.ls14.jf.bpmnanalysis.impl.ProcessAnalysisImpl#getDefinitions <em>Definitions</em>}</li>
 *   <li>{@link edu.udo.cs.ls14.jf.bpmnanalysis.impl.ProcessAnalysisImpl#getResults <em>Results</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ProcessAnalysisImpl extends MinimalEObjectImpl.Container implements ProcessAnalysis {
	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected String id = ID_EDEFAULT;

	/**
	 * The cached value of the '{@link #getDefinitions() <em>Definitions</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefinitions()
	 * @generated
	 * @ordered
	 */
	protected Definitions definitions;

	/**
	 * The cached value of the '{@link #getResults() <em>Results</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResults()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, ProcessAnalysisResult> results;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ProcessAnalysisImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BpmnAnalysisPackage.Literals.PROCESS_ANALYSIS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setId(String newId) {
		String oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BpmnAnalysisPackage.PROCESS_ANALYSIS__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Definitions getDefinitions() {
		return definitions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDefinitions(Definitions newDefinitions, NotificationChain msgs) {
		Definitions oldDefinitions = definitions;
		definitions = newDefinitions;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BpmnAnalysisPackage.PROCESS_ANALYSIS__DEFINITIONS, oldDefinitions, newDefinitions);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDefinitions(Definitions newDefinitions) {
		if (newDefinitions != definitions) {
			NotificationChain msgs = null;
			if (definitions != null)
				msgs = ((InternalEObject)definitions).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BpmnAnalysisPackage.PROCESS_ANALYSIS__DEFINITIONS, null, msgs);
			if (newDefinitions != null)
				msgs = ((InternalEObject)newDefinitions).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BpmnAnalysisPackage.PROCESS_ANALYSIS__DEFINITIONS, null, msgs);
			msgs = basicSetDefinitions(newDefinitions, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BpmnAnalysisPackage.PROCESS_ANALYSIS__DEFINITIONS, newDefinitions, newDefinitions));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Map<String, ProcessAnalysisResult> getResults() {
		if (results == null) {
			results = new EcoreEMap<String,ProcessAnalysisResult>(BpmnAnalysisPackage.Literals.PROCESS_ANALYSIS_RESULT_ENTRY, ProcessAnalysisResultEntryImpl.class, this, BpmnAnalysisPackage.PROCESS_ANALYSIS__RESULTS);
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
			case BpmnAnalysisPackage.PROCESS_ANALYSIS__DEFINITIONS:
				return basicSetDefinitions(null, msgs);
			case BpmnAnalysisPackage.PROCESS_ANALYSIS__RESULTS:
				return ((InternalEList<?>)((EMap.InternalMapView<String, ProcessAnalysisResult>)getResults()).eMap()).basicRemove(otherEnd, msgs);
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
			case BpmnAnalysisPackage.PROCESS_ANALYSIS__ID:
				return getId();
			case BpmnAnalysisPackage.PROCESS_ANALYSIS__DEFINITIONS:
				return getDefinitions();
			case BpmnAnalysisPackage.PROCESS_ANALYSIS__RESULTS:
				if (coreType) return ((EMap.InternalMapView<String, ProcessAnalysisResult>)getResults()).eMap();
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
			case BpmnAnalysisPackage.PROCESS_ANALYSIS__ID:
				setId((String)newValue);
				return;
			case BpmnAnalysisPackage.PROCESS_ANALYSIS__DEFINITIONS:
				setDefinitions((Definitions)newValue);
				return;
			case BpmnAnalysisPackage.PROCESS_ANALYSIS__RESULTS:
				((EStructuralFeature.Setting)((EMap.InternalMapView<String, ProcessAnalysisResult>)getResults()).eMap()).set(newValue);
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
			case BpmnAnalysisPackage.PROCESS_ANALYSIS__ID:
				setId(ID_EDEFAULT);
				return;
			case BpmnAnalysisPackage.PROCESS_ANALYSIS__DEFINITIONS:
				setDefinitions((Definitions)null);
				return;
			case BpmnAnalysisPackage.PROCESS_ANALYSIS__RESULTS:
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
			case BpmnAnalysisPackage.PROCESS_ANALYSIS__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case BpmnAnalysisPackage.PROCESS_ANALYSIS__DEFINITIONS:
				return definitions != null;
			case BpmnAnalysisPackage.PROCESS_ANALYSIS__RESULTS:
				return results != null && !results.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (id: ");
		result.append(id);
		result.append(')');
		return result.toString();
	}

} //ProcessAnalysisImpl
