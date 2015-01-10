/**
 */
package edu.udo.cs.ls14.jf.bpmnmatching.impl;

import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis;
import edu.udo.cs.ls14.jf.bpmnmatching.BpmnMatchingPackage;
import edu.udo.cs.ls14.jf.bpmnmatching.FragmentMatching;
import edu.udo.cs.ls14.jf.bpmnmatching.NodeMatching;
import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Process Matching</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link edu.udo.cs.ls14.jf.bpmnmatching.impl.ProcessMatchingImpl#getAnalysis1 <em>Analysis1</em>}</li>
 *   <li>{@link edu.udo.cs.ls14.jf.bpmnmatching.impl.ProcessMatchingImpl#getAnalysis2 <em>Analysis2</em>}</li>
 *   <li>{@link edu.udo.cs.ls14.jf.bpmnmatching.impl.ProcessMatchingImpl#getNodeMatching <em>Node Matching</em>}</li>
 *   <li>{@link edu.udo.cs.ls14.jf.bpmnmatching.impl.ProcessMatchingImpl#getFragmentMatching <em>Fragment Matching</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ProcessMatchingImpl extends MinimalEObjectImpl.Container implements ProcessMatching {
	/**
	 * The cached value of the '{@link #getAnalysis1() <em>Analysis1</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAnalysis1()
	 * @generated
	 * @ordered
	 */
	protected ProcessAnalysis analysis1;

	/**
	 * The cached value of the '{@link #getAnalysis2() <em>Analysis2</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAnalysis2()
	 * @generated
	 * @ordered
	 */
	protected ProcessAnalysis analysis2;

	/**
	 * The cached value of the '{@link #getNodeMatching() <em>Node Matching</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNodeMatching()
	 * @generated
	 * @ordered
	 */
	protected NodeMatching nodeMatching;

	/**
	 * The cached value of the '{@link #getFragmentMatching() <em>Fragment Matching</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFragmentMatching()
	 * @generated
	 * @ordered
	 */
	protected FragmentMatching fragmentMatching;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ProcessMatchingImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BpmnMatchingPackage.Literals.PROCESS_MATCHING;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProcessAnalysis getAnalysis1() {
		return analysis1;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAnalysis1(ProcessAnalysis newAnalysis1, NotificationChain msgs) {
		ProcessAnalysis oldAnalysis1 = analysis1;
		analysis1 = newAnalysis1;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BpmnMatchingPackage.PROCESS_MATCHING__ANALYSIS1, oldAnalysis1, newAnalysis1);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAnalysis1(ProcessAnalysis newAnalysis1) {
		if (newAnalysis1 != analysis1) {
			NotificationChain msgs = null;
			if (analysis1 != null)
				msgs = ((InternalEObject)analysis1).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BpmnMatchingPackage.PROCESS_MATCHING__ANALYSIS1, null, msgs);
			if (newAnalysis1 != null)
				msgs = ((InternalEObject)newAnalysis1).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BpmnMatchingPackage.PROCESS_MATCHING__ANALYSIS1, null, msgs);
			msgs = basicSetAnalysis1(newAnalysis1, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BpmnMatchingPackage.PROCESS_MATCHING__ANALYSIS1, newAnalysis1, newAnalysis1));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProcessAnalysis getAnalysis2() {
		return analysis2;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAnalysis2(ProcessAnalysis newAnalysis2, NotificationChain msgs) {
		ProcessAnalysis oldAnalysis2 = analysis2;
		analysis2 = newAnalysis2;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BpmnMatchingPackage.PROCESS_MATCHING__ANALYSIS2, oldAnalysis2, newAnalysis2);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAnalysis2(ProcessAnalysis newAnalysis2) {
		if (newAnalysis2 != analysis2) {
			NotificationChain msgs = null;
			if (analysis2 != null)
				msgs = ((InternalEObject)analysis2).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BpmnMatchingPackage.PROCESS_MATCHING__ANALYSIS2, null, msgs);
			if (newAnalysis2 != null)
				msgs = ((InternalEObject)newAnalysis2).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BpmnMatchingPackage.PROCESS_MATCHING__ANALYSIS2, null, msgs);
			msgs = basicSetAnalysis2(newAnalysis2, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BpmnMatchingPackage.PROCESS_MATCHING__ANALYSIS2, newAnalysis2, newAnalysis2));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NodeMatching getNodeMatching() {
		return nodeMatching;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetNodeMatching(NodeMatching newNodeMatching, NotificationChain msgs) {
		NodeMatching oldNodeMatching = nodeMatching;
		nodeMatching = newNodeMatching;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BpmnMatchingPackage.PROCESS_MATCHING__NODE_MATCHING, oldNodeMatching, newNodeMatching);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNodeMatching(NodeMatching newNodeMatching) {
		if (newNodeMatching != nodeMatching) {
			NotificationChain msgs = null;
			if (nodeMatching != null)
				msgs = ((InternalEObject)nodeMatching).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BpmnMatchingPackage.PROCESS_MATCHING__NODE_MATCHING, null, msgs);
			if (newNodeMatching != null)
				msgs = ((InternalEObject)newNodeMatching).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BpmnMatchingPackage.PROCESS_MATCHING__NODE_MATCHING, null, msgs);
			msgs = basicSetNodeMatching(newNodeMatching, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BpmnMatchingPackage.PROCESS_MATCHING__NODE_MATCHING, newNodeMatching, newNodeMatching));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FragmentMatching getFragmentMatching() {
		return fragmentMatching;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetFragmentMatching(FragmentMatching newFragmentMatching, NotificationChain msgs) {
		FragmentMatching oldFragmentMatching = fragmentMatching;
		fragmentMatching = newFragmentMatching;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BpmnMatchingPackage.PROCESS_MATCHING__FRAGMENT_MATCHING, oldFragmentMatching, newFragmentMatching);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFragmentMatching(FragmentMatching newFragmentMatching) {
		if (newFragmentMatching != fragmentMatching) {
			NotificationChain msgs = null;
			if (fragmentMatching != null)
				msgs = ((InternalEObject)fragmentMatching).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BpmnMatchingPackage.PROCESS_MATCHING__FRAGMENT_MATCHING, null, msgs);
			if (newFragmentMatching != null)
				msgs = ((InternalEObject)newFragmentMatching).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BpmnMatchingPackage.PROCESS_MATCHING__FRAGMENT_MATCHING, null, msgs);
			msgs = basicSetFragmentMatching(newFragmentMatching, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BpmnMatchingPackage.PROCESS_MATCHING__FRAGMENT_MATCHING, newFragmentMatching, newFragmentMatching));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case BpmnMatchingPackage.PROCESS_MATCHING__ANALYSIS1:
				return basicSetAnalysis1(null, msgs);
			case BpmnMatchingPackage.PROCESS_MATCHING__ANALYSIS2:
				return basicSetAnalysis2(null, msgs);
			case BpmnMatchingPackage.PROCESS_MATCHING__NODE_MATCHING:
				return basicSetNodeMatching(null, msgs);
			case BpmnMatchingPackage.PROCESS_MATCHING__FRAGMENT_MATCHING:
				return basicSetFragmentMatching(null, msgs);
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
			case BpmnMatchingPackage.PROCESS_MATCHING__ANALYSIS1:
				return getAnalysis1();
			case BpmnMatchingPackage.PROCESS_MATCHING__ANALYSIS2:
				return getAnalysis2();
			case BpmnMatchingPackage.PROCESS_MATCHING__NODE_MATCHING:
				return getNodeMatching();
			case BpmnMatchingPackage.PROCESS_MATCHING__FRAGMENT_MATCHING:
				return getFragmentMatching();
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
			case BpmnMatchingPackage.PROCESS_MATCHING__ANALYSIS1:
				setAnalysis1((ProcessAnalysis)newValue);
				return;
			case BpmnMatchingPackage.PROCESS_MATCHING__ANALYSIS2:
				setAnalysis2((ProcessAnalysis)newValue);
				return;
			case BpmnMatchingPackage.PROCESS_MATCHING__NODE_MATCHING:
				setNodeMatching((NodeMatching)newValue);
				return;
			case BpmnMatchingPackage.PROCESS_MATCHING__FRAGMENT_MATCHING:
				setFragmentMatching((FragmentMatching)newValue);
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
			case BpmnMatchingPackage.PROCESS_MATCHING__ANALYSIS1:
				setAnalysis1((ProcessAnalysis)null);
				return;
			case BpmnMatchingPackage.PROCESS_MATCHING__ANALYSIS2:
				setAnalysis2((ProcessAnalysis)null);
				return;
			case BpmnMatchingPackage.PROCESS_MATCHING__NODE_MATCHING:
				setNodeMatching((NodeMatching)null);
				return;
			case BpmnMatchingPackage.PROCESS_MATCHING__FRAGMENT_MATCHING:
				setFragmentMatching((FragmentMatching)null);
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
			case BpmnMatchingPackage.PROCESS_MATCHING__ANALYSIS1:
				return analysis1 != null;
			case BpmnMatchingPackage.PROCESS_MATCHING__ANALYSIS2:
				return analysis2 != null;
			case BpmnMatchingPackage.PROCESS_MATCHING__NODE_MATCHING:
				return nodeMatching != null;
			case BpmnMatchingPackage.PROCESS_MATCHING__FRAGMENT_MATCHING:
				return fragmentMatching != null;
		}
		return super.eIsSet(featureID);
	}

} //ProcessMatchingImpl
