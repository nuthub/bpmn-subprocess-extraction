/**
 */
package edu.udo.cs.ls14.jf.bpmntransformation;

import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;

import java.util.Map;

import org.eclipse.bpmn2.Definitions;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Process Transformation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link edu.udo.cs.ls14.jf.bpmntransformation.ProcessTransformation#getProcessMatching <em>Process Matching</em>}</li>
 *   <li>{@link edu.udo.cs.ls14.jf.bpmntransformation.ProcessTransformation#getResults <em>Results</em>}</li>
 * </ul>
 * </p>
 *
 * @see edu.udo.cs.ls14.jf.bpmntransformation.BpmnTransformationPackage#getProcessTransformation()
 * @model
 * @generated
 */
public interface ProcessTransformation extends EObject {
	/**
	 * Returns the value of the '<em><b>Process Matching</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Process Matching</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Process Matching</em>' containment reference.
	 * @see #setProcessMatching(ProcessMatching)
	 * @see edu.udo.cs.ls14.jf.bpmntransformation.BpmnTransformationPackage#getProcessTransformation_ProcessMatching()
	 * @model containment="true" required="true"
	 * @generated
	 */
	ProcessMatching getProcessMatching();

	/**
	 * Sets the value of the '{@link edu.udo.cs.ls14.jf.bpmntransformation.ProcessTransformation#getProcessMatching <em>Process Matching</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Process Matching</em>' containment reference.
	 * @see #getProcessMatching()
	 * @generated
	 */
	void setProcessMatching(ProcessMatching value);

	/**
	 * Returns the value of the '<em><b>Results</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link org.eclipse.bpmn2.Definitions},
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Results</em>' map isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Results</em>' map.
	 * @see edu.udo.cs.ls14.jf.bpmntransformation.BpmnTransformationPackage#getProcessTransformation_Results()
	 * @model mapType="edu.udo.cs.ls14.jf.bpmntransformation.TransformationResultEntry<org.eclipse.emf.ecore.EString, org.eclipse.bpmn2.Definitions>"
	 * @generated
	 */
	Map<String, Definitions> getResults();

} // ProcessTransformation
