/**
 */
package edu.udo.cs.ls14.jf.bpmnanalysis.impl;

import edu.udo.cs.ls14.jf.bpmnanalysis.BpmnAnalysisPackage;
import edu.udo.cs.ls14.jf.bpmnanalysis.Fragment;

import org.eclipse.bpmn2.SequenceFlow;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Fragment</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link edu.udo.cs.ls14.jf.bpmnanalysis.impl.FragmentImpl#getEntry <em>Entry</em>}</li>
 *   <li>{@link edu.udo.cs.ls14.jf.bpmnanalysis.impl.FragmentImpl#getExit <em>Exit</em>}</li>
 *   <li>{@link edu.udo.cs.ls14.jf.bpmnanalysis.impl.FragmentImpl#getParent <em>Parent</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FragmentImpl extends MinimalEObjectImpl.Container implements Fragment {
	/**
	 * The cached value of the '{@link #getEntry() <em>Entry</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEntry()
	 * @generated
	 * @ordered
	 */
	protected SequenceFlow entry;

	/**
	 * The cached value of the '{@link #getExit() <em>Exit</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExit()
	 * @generated
	 * @ordered
	 */
	protected SequenceFlow exit;

	/**
	 * The cached value of the '{@link #getParent() <em>Parent</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParent()
	 * @generated
	 * @ordered
	 */
	protected Fragment parent;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FragmentImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BpmnAnalysisPackage.Literals.FRAGMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SequenceFlow getEntry() {
		if (entry != null && entry.eIsProxy()) {
			InternalEObject oldEntry = (InternalEObject)entry;
			entry = (SequenceFlow)eResolveProxy(oldEntry);
			if (entry != oldEntry) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, BpmnAnalysisPackage.FRAGMENT__ENTRY, oldEntry, entry));
			}
		}
		return entry;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SequenceFlow basicGetEntry() {
		return entry;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEntry(SequenceFlow newEntry) {
		SequenceFlow oldEntry = entry;
		entry = newEntry;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BpmnAnalysisPackage.FRAGMENT__ENTRY, oldEntry, entry));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SequenceFlow getExit() {
		if (exit != null && exit.eIsProxy()) {
			InternalEObject oldExit = (InternalEObject)exit;
			exit = (SequenceFlow)eResolveProxy(oldExit);
			if (exit != oldExit) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, BpmnAnalysisPackage.FRAGMENT__EXIT, oldExit, exit));
			}
		}
		return exit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SequenceFlow basicGetExit() {
		return exit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExit(SequenceFlow newExit) {
		SequenceFlow oldExit = exit;
		exit = newExit;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BpmnAnalysisPackage.FRAGMENT__EXIT, oldExit, exit));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Fragment getParent() {
		if (parent != null && parent.eIsProxy()) {
			InternalEObject oldParent = (InternalEObject)parent;
			parent = (Fragment)eResolveProxy(oldParent);
			if (parent != oldParent) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, BpmnAnalysisPackage.FRAGMENT__PARENT, oldParent, parent));
			}
		}
		return parent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Fragment basicGetParent() {
		return parent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParent(Fragment newParent) {
		Fragment oldParent = parent;
		parent = newParent;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BpmnAnalysisPackage.FRAGMENT__PARENT, oldParent, parent));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case BpmnAnalysisPackage.FRAGMENT__ENTRY:
				if (resolve) return getEntry();
				return basicGetEntry();
			case BpmnAnalysisPackage.FRAGMENT__EXIT:
				if (resolve) return getExit();
				return basicGetExit();
			case BpmnAnalysisPackage.FRAGMENT__PARENT:
				if (resolve) return getParent();
				return basicGetParent();
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
			case BpmnAnalysisPackage.FRAGMENT__ENTRY:
				setEntry((SequenceFlow)newValue);
				return;
			case BpmnAnalysisPackage.FRAGMENT__EXIT:
				setExit((SequenceFlow)newValue);
				return;
			case BpmnAnalysisPackage.FRAGMENT__PARENT:
				setParent((Fragment)newValue);
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
			case BpmnAnalysisPackage.FRAGMENT__ENTRY:
				setEntry((SequenceFlow)null);
				return;
			case BpmnAnalysisPackage.FRAGMENT__EXIT:
				setExit((SequenceFlow)null);
				return;
			case BpmnAnalysisPackage.FRAGMENT__PARENT:
				setParent((Fragment)null);
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
			case BpmnAnalysisPackage.FRAGMENT__ENTRY:
				return entry != null;
			case BpmnAnalysisPackage.FRAGMENT__EXIT:
				return exit != null;
			case BpmnAnalysisPackage.FRAGMENT__PARENT:
				return parent != null;
		}
		return super.eIsSet(featureID);
	}

} //FragmentImpl
