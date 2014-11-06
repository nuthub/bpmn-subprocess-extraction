/**
 */
package edu.udo.cs.ls14.jf.bpmnanalysis.impl;

import edu.udo.cs.ls14.jf.bpmnanalysis.BpmnAnalysisPackage;
import edu.udo.cs.ls14.jf.bpmnanalysis.NodePair;

import org.eclipse.bpmn2.FlowNode;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Node Pair</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link edu.udo.cs.ls14.jf.bpmnanalysis.impl.NodePairImpl#getA <em>A</em>}</li>
 *   <li>{@link edu.udo.cs.ls14.jf.bpmnanalysis.impl.NodePairImpl#getB <em>B</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class NodePairImpl extends MinimalEObjectImpl.Container implements NodePair {
	/**
	 * The cached value of the '{@link #getA() <em>A</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getA()
	 * @generated
	 * @ordered
	 */
	protected FlowNode a;

	/**
	 * The cached value of the '{@link #getB() <em>B</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getB()
	 * @generated
	 * @ordered
	 */
	protected FlowNode b;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected NodePairImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BpmnAnalysisPackage.Literals.NODE_PAIR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FlowNode getA() {
		if (a != null && a.eIsProxy()) {
			InternalEObject oldA = (InternalEObject)a;
			a = (FlowNode)eResolveProxy(oldA);
			if (a != oldA) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, BpmnAnalysisPackage.NODE_PAIR__A, oldA, a));
			}
		}
		return a;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FlowNode basicGetA() {
		return a;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setA(FlowNode newA) {
		FlowNode oldA = a;
		a = newA;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BpmnAnalysisPackage.NODE_PAIR__A, oldA, a));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FlowNode getB() {
		if (b != null && b.eIsProxy()) {
			InternalEObject oldB = (InternalEObject)b;
			b = (FlowNode)eResolveProxy(oldB);
			if (b != oldB) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, BpmnAnalysisPackage.NODE_PAIR__B, oldB, b));
			}
		}
		return b;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FlowNode basicGetB() {
		return b;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setB(FlowNode newB) {
		FlowNode oldB = b;
		b = newB;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BpmnAnalysisPackage.NODE_PAIR__B, oldB, b));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case BpmnAnalysisPackage.NODE_PAIR__A:
				if (resolve) return getA();
				return basicGetA();
			case BpmnAnalysisPackage.NODE_PAIR__B:
				if (resolve) return getB();
				return basicGetB();
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
			case BpmnAnalysisPackage.NODE_PAIR__A:
				setA((FlowNode)newValue);
				return;
			case BpmnAnalysisPackage.NODE_PAIR__B:
				setB((FlowNode)newValue);
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
			case BpmnAnalysisPackage.NODE_PAIR__A:
				setA((FlowNode)null);
				return;
			case BpmnAnalysisPackage.NODE_PAIR__B:
				setB((FlowNode)null);
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
			case BpmnAnalysisPackage.NODE_PAIR__A:
				return a != null;
			case BpmnAnalysisPackage.NODE_PAIR__B:
				return b != null;
		}
		return super.eIsSet(featureID);
	}

} //NodePairImpl
