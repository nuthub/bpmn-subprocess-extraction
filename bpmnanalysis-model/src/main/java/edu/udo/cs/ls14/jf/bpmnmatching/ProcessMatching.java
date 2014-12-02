/**
 */
package edu.udo.cs.ls14.jf.bpmnmatching;

import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Process Matching</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching#getAnalysis1 <em>Analysis1</em>}</li>
 *   <li>{@link edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching#getAnalysis2 <em>Analysis2</em>}</li>
 *   <li>{@link edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching#getNodeMatching <em>Node Matching</em>}</li>
 *   <li>{@link edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching#getFragmentMatching <em>Fragment Matching</em>}</li>
 * </ul>
 * </p>
 *
 * @see edu.udo.cs.ls14.jf.bpmnmatching.BpmnMatchingPackage#getProcessMatching()
 * @model
 * @generated
 */
public interface ProcessMatching extends EObject {
	/**
	 * Returns the value of the '<em><b>Analysis1</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Analysis1</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Analysis1</em>' containment reference.
	 * @see #setAnalysis1(ProcessAnalysis)
	 * @see edu.udo.cs.ls14.jf.bpmnmatching.BpmnMatchingPackage#getProcessMatching_Analysis1()
	 * @model containment="true" required="true"
	 * @generated
	 */
	ProcessAnalysis getAnalysis1();

	/**
	 * Sets the value of the '{@link edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching#getAnalysis1 <em>Analysis1</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Analysis1</em>' containment reference.
	 * @see #getAnalysis1()
	 * @generated
	 */
	void setAnalysis1(ProcessAnalysis value);

	/**
	 * Returns the value of the '<em><b>Analysis2</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Analysis2</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Analysis2</em>' containment reference.
	 * @see #setAnalysis2(ProcessAnalysis)
	 * @see edu.udo.cs.ls14.jf.bpmnmatching.BpmnMatchingPackage#getProcessMatching_Analysis2()
	 * @model containment="true" required="true"
	 * @generated
	 */
	ProcessAnalysis getAnalysis2();

	/**
	 * Sets the value of the '{@link edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching#getAnalysis2 <em>Analysis2</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Analysis2</em>' containment reference.
	 * @see #getAnalysis2()
	 * @generated
	 */
	void setAnalysis2(ProcessAnalysis value);

	/**
	 * Returns the value of the '<em><b>Node Matching</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Node Matching</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Node Matching</em>' containment reference.
	 * @see #setNodeMatching(NodeMatching)
	 * @see edu.udo.cs.ls14.jf.bpmnmatching.BpmnMatchingPackage#getProcessMatching_NodeMatching()
	 * @model containment="true" required="true"
	 * @generated
	 */
	NodeMatching getNodeMatching();

	/**
	 * Sets the value of the '{@link edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching#getNodeMatching <em>Node Matching</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Node Matching</em>' containment reference.
	 * @see #getNodeMatching()
	 * @generated
	 */
	void setNodeMatching(NodeMatching value);

	/**
	 * Returns the value of the '<em><b>Fragment Matching</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Fragment Matching</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Fragment Matching</em>' containment reference.
	 * @see #setFragmentMatching(FragmentMatching)
	 * @see edu.udo.cs.ls14.jf.bpmnmatching.BpmnMatchingPackage#getProcessMatching_FragmentMatching()
	 * @model containment="true" required="true"
	 * @generated
	 */
	FragmentMatching getFragmentMatching();

	/**
	 * Sets the value of the '{@link edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching#getFragmentMatching <em>Fragment Matching</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Fragment Matching</em>' containment reference.
	 * @see #getFragmentMatching()
	 * @generated
	 */
	void setFragmentMatching(FragmentMatching value);

} // ProcessMatching
