/**
 */
package edu.udo.cs.ls14.jf.bpmnmatching.impl;

import edu.udo.cs.ls14.jf.bpmnanalysis.Fragment;

import edu.udo.cs.ls14.jf.bpmnmatching.BpmnMatchingPackage;
import edu.udo.cs.ls14.jf.bpmnmatching.FragmentPair;

import org.eclipse.bpmn2.CallableElement;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Fragment Pair</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link edu.udo.cs.ls14.jf.bpmnmatching.impl.FragmentPairImpl#getA <em>A</em>}</li>
 *   <li>{@link edu.udo.cs.ls14.jf.bpmnmatching.impl.FragmentPairImpl#getB <em>B</em>}</li>
 *   <li>{@link edu.udo.cs.ls14.jf.bpmnmatching.impl.FragmentPairImpl#getBetter <em>Better</em>}</li>
 *   <li>{@link edu.udo.cs.ls14.jf.bpmnmatching.impl.FragmentPairImpl#getExtractedProcess <em>Extracted Process</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FragmentPairImpl extends MinimalEObjectImpl.Container implements FragmentPair {
	/**
	 * The cached value of the '{@link #getA() <em>A</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getA()
	 * @generated
	 * @ordered
	 */
	protected Fragment a;

	/**
	 * The cached value of the '{@link #getB() <em>B</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getB()
	 * @generated
	 * @ordered
	 */
	protected Fragment b;

	/**
	 * The cached value of the '{@link #getBetter() <em>Better</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBetter()
	 * @generated
	 * @ordered
	 */
	protected Fragment better;

	/**
	 * The cached value of the '{@link #getExtractedProcess() <em>Extracted Process</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExtractedProcess()
	 * @generated
	 * @ordered
	 */
	protected CallableElement extractedProcess;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FragmentPairImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BpmnMatchingPackage.Literals.FRAGMENT_PAIR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Fragment getA() {
		if (a != null && a.eIsProxy()) {
			InternalEObject oldA = (InternalEObject)a;
			a = (Fragment)eResolveProxy(oldA);
			if (a != oldA) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, BpmnMatchingPackage.FRAGMENT_PAIR__A, oldA, a));
			}
		}
		return a;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Fragment basicGetA() {
		return a;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setA(Fragment newA) {
		Fragment oldA = a;
		a = newA;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BpmnMatchingPackage.FRAGMENT_PAIR__A, oldA, a));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Fragment getB() {
		if (b != null && b.eIsProxy()) {
			InternalEObject oldB = (InternalEObject)b;
			b = (Fragment)eResolveProxy(oldB);
			if (b != oldB) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, BpmnMatchingPackage.FRAGMENT_PAIR__B, oldB, b));
			}
		}
		return b;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Fragment basicGetB() {
		return b;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setB(Fragment newB) {
		Fragment oldB = b;
		b = newB;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BpmnMatchingPackage.FRAGMENT_PAIR__B, oldB, b));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Fragment getBetter() {
		if (better != null && better.eIsProxy()) {
			InternalEObject oldBetter = (InternalEObject)better;
			better = (Fragment)eResolveProxy(oldBetter);
			if (better != oldBetter) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, BpmnMatchingPackage.FRAGMENT_PAIR__BETTER, oldBetter, better));
			}
		}
		return better;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Fragment basicGetBetter() {
		return better;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBetter(Fragment newBetter) {
		Fragment oldBetter = better;
		better = newBetter;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BpmnMatchingPackage.FRAGMENT_PAIR__BETTER, oldBetter, better));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CallableElement getExtractedProcess() {
		if (extractedProcess != null && extractedProcess.eIsProxy()) {
			InternalEObject oldExtractedProcess = (InternalEObject)extractedProcess;
			extractedProcess = (CallableElement)eResolveProxy(oldExtractedProcess);
			if (extractedProcess != oldExtractedProcess) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, BpmnMatchingPackage.FRAGMENT_PAIR__EXTRACTED_PROCESS, oldExtractedProcess, extractedProcess));
			}
		}
		return extractedProcess;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CallableElement basicGetExtractedProcess() {
		return extractedProcess;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExtractedProcess(CallableElement newExtractedProcess) {
		CallableElement oldExtractedProcess = extractedProcess;
		extractedProcess = newExtractedProcess;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BpmnMatchingPackage.FRAGMENT_PAIR__EXTRACTED_PROCESS, oldExtractedProcess, extractedProcess));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case BpmnMatchingPackage.FRAGMENT_PAIR__A:
				if (resolve) return getA();
				return basicGetA();
			case BpmnMatchingPackage.FRAGMENT_PAIR__B:
				if (resolve) return getB();
				return basicGetB();
			case BpmnMatchingPackage.FRAGMENT_PAIR__BETTER:
				if (resolve) return getBetter();
				return basicGetBetter();
			case BpmnMatchingPackage.FRAGMENT_PAIR__EXTRACTED_PROCESS:
				if (resolve) return getExtractedProcess();
				return basicGetExtractedProcess();
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
			case BpmnMatchingPackage.FRAGMENT_PAIR__A:
				setA((Fragment)newValue);
				return;
			case BpmnMatchingPackage.FRAGMENT_PAIR__B:
				setB((Fragment)newValue);
				return;
			case BpmnMatchingPackage.FRAGMENT_PAIR__BETTER:
				setBetter((Fragment)newValue);
				return;
			case BpmnMatchingPackage.FRAGMENT_PAIR__EXTRACTED_PROCESS:
				setExtractedProcess((CallableElement)newValue);
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
			case BpmnMatchingPackage.FRAGMENT_PAIR__A:
				setA((Fragment)null);
				return;
			case BpmnMatchingPackage.FRAGMENT_PAIR__B:
				setB((Fragment)null);
				return;
			case BpmnMatchingPackage.FRAGMENT_PAIR__BETTER:
				setBetter((Fragment)null);
				return;
			case BpmnMatchingPackage.FRAGMENT_PAIR__EXTRACTED_PROCESS:
				setExtractedProcess((CallableElement)null);
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
			case BpmnMatchingPackage.FRAGMENT_PAIR__A:
				return a != null;
			case BpmnMatchingPackage.FRAGMENT_PAIR__B:
				return b != null;
			case BpmnMatchingPackage.FRAGMENT_PAIR__BETTER:
				return better != null;
			case BpmnMatchingPackage.FRAGMENT_PAIR__EXTRACTED_PROCESS:
				return extractedProcess != null;
		}
		return super.eIsSet(featureID);
	}

} //FragmentPairImpl
