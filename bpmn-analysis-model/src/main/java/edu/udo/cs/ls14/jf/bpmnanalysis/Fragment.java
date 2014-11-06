/**
 */
package edu.udo.cs.ls14.jf.bpmnanalysis;

import org.eclipse.bpmn2.SequenceFlow;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Fragment</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link edu.udo.cs.ls14.jf.bpmnanalysis.Fragment#getEntry <em>Entry</em>}</li>
 *   <li>{@link edu.udo.cs.ls14.jf.bpmnanalysis.Fragment#getExit <em>Exit</em>}</li>
 *   <li>{@link edu.udo.cs.ls14.jf.bpmnanalysis.Fragment#getParent <em>Parent</em>}</li>
 * </ul>
 * </p>
 *
 * @see edu.udo.cs.ls14.jf.bpmnanalysis.BpmnAnalysisPackage#getFragment()
 * @model
 * @generated
 */
public interface Fragment extends EObject {
	/**
	 * Returns the value of the '<em><b>Entry</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Entry</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Entry</em>' reference.
	 * @see #setEntry(SequenceFlow)
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.BpmnAnalysisPackage#getFragment_Entry()
	 * @model required="true"
	 * @generated
	 */
	SequenceFlow getEntry();

	/**
	 * Sets the value of the '{@link edu.udo.cs.ls14.jf.bpmnanalysis.Fragment#getEntry <em>Entry</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Entry</em>' reference.
	 * @see #getEntry()
	 * @generated
	 */
	void setEntry(SequenceFlow value);

	/**
	 * Returns the value of the '<em><b>Exit</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Exit</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Exit</em>' reference.
	 * @see #setExit(SequenceFlow)
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.BpmnAnalysisPackage#getFragment_Exit()
	 * @model required="true"
	 * @generated
	 */
	SequenceFlow getExit();

	/**
	 * Sets the value of the '{@link edu.udo.cs.ls14.jf.bpmnanalysis.Fragment#getExit <em>Exit</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Exit</em>' reference.
	 * @see #getExit()
	 * @generated
	 */
	void setExit(SequenceFlow value);

	/**
	 * Returns the value of the '<em><b>Parent</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parent</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parent</em>' reference.
	 * @see #setParent(Fragment)
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.BpmnAnalysisPackage#getFragment_Parent()
	 * @model
	 * @generated
	 */
	Fragment getParent();

	/**
	 * Sets the value of the '{@link edu.udo.cs.ls14.jf.bpmnanalysis.Fragment#getParent <em>Parent</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parent</em>' reference.
	 * @see #getParent()
	 * @generated
	 */
	void setParent(Fragment value);

} // Fragment
