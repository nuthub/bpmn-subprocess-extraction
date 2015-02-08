/**
 */
package edu.udo.cs.ls14.jf.bpmnmatching.util;

import edu.udo.cs.ls14.jf.bpmnmatching.*;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see edu.udo.cs.ls14.jf.bpmnmatching.BpmnMatchingPackage
 * @generated
 */
public class BpmnMatchingSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static BpmnMatchingPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BpmnMatchingSwitch() {
		if (modelPackage == null) {
			modelPackage = BpmnMatchingPackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @parameter ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case BpmnMatchingPackage.PROCESS_MATCHING: {
				ProcessMatching processMatching = (ProcessMatching)theEObject;
				T result = caseProcessMatching(processMatching);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BpmnMatchingPackage.NODE_MATCHING: {
				NodeMatching nodeMatching = (NodeMatching)theEObject;
				T result = caseNodeMatching(nodeMatching);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BpmnMatchingPackage.NODE_PAIR: {
				NodePair nodePair = (NodePair)theEObject;
				T result = caseNodePair(nodePair);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BpmnMatchingPackage.FRAGMENT_MATCHING: {
				FragmentMatching fragmentMatching = (FragmentMatching)theEObject;
				T result = caseFragmentMatching(fragmentMatching);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BpmnMatchingPackage.FRAGMENT_PAIR: {
				FragmentPair fragmentPair = (FragmentPair)theEObject;
				T result = caseFragmentPair(fragmentPair);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BpmnMatchingPackage.PROCESS_MATCHER: {
				ProcessMatcher processMatcher = (ProcessMatcher)theEObject;
				T result = caseProcessMatcher(processMatcher);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Process Matching</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Process Matching</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseProcessMatching(ProcessMatching object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Node Matching</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Node Matching</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNodeMatching(NodeMatching object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Node Pair</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Node Pair</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNodePair(NodePair object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Fragment Matching</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Fragment Matching</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFragmentMatching(FragmentMatching object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Fragment Pair</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Fragment Pair</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFragmentPair(FragmentPair object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Process Matcher</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Process Matcher</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseProcessMatcher(ProcessMatcher object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	@Override
	public T defaultCase(EObject object) {
		return null;
	}

} //BpmnMatchingSwitch
