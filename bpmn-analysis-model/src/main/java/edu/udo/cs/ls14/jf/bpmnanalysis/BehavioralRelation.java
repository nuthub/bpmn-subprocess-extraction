/**
 */
package edu.udo.cs.ls14.jf.bpmnanalysis;

import org.eclipse.bpmn2.FlowNode;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Behavioral Relation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link edu.udo.cs.ls14.jf.bpmnanalysis.BehavioralRelation#getLeft <em>Left</em>}</li>
 *   <li>{@link edu.udo.cs.ls14.jf.bpmnanalysis.BehavioralRelation#getRight <em>Right</em>}</li>
 *   <li>{@link edu.udo.cs.ls14.jf.bpmnanalysis.BehavioralRelation#getRelation <em>Relation</em>}</li>
 * </ul>
 * </p>
 *
 * @see edu.udo.cs.ls14.jf.bpmnanalysis.BpmnAnalysisPackage#getBehavioralRelation()
 * @model
 * @generated
 */
public interface BehavioralRelation extends EObject {
	/**
	 * Returns the value of the '<em><b>Left</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Left</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Left</em>' reference.
	 * @see #setLeft(FlowNode)
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.BpmnAnalysisPackage#getBehavioralRelation_Left()
	 * @model required="true"
	 * @generated
	 */
	FlowNode getLeft();

	/**
	 * Sets the value of the '{@link edu.udo.cs.ls14.jf.bpmnanalysis.BehavioralRelation#getLeft <em>Left</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Left</em>' reference.
	 * @see #getLeft()
	 * @generated
	 */
	void setLeft(FlowNode value);

	/**
	 * Returns the value of the '<em><b>Right</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Right</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Right</em>' reference.
	 * @see #setRight(FlowNode)
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.BpmnAnalysisPackage#getBehavioralRelation_Right()
	 * @model required="true"
	 * @generated
	 */
	FlowNode getRight();

	/**
	 * Sets the value of the '{@link edu.udo.cs.ls14.jf.bpmnanalysis.BehavioralRelation#getRight <em>Right</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Right</em>' reference.
	 * @see #getRight()
	 * @generated
	 */
	void setRight(FlowNode value);

	/**
	 * Returns the value of the '<em><b>Relation</b></em>' attribute.
	 * The literals are from the enumeration {@link edu.udo.cs.ls14.jf.bpmnanalysis.BehavioralRelationType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Relation</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Relation</em>' attribute.
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.BehavioralRelationType
	 * @see #setRelation(BehavioralRelationType)
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.BpmnAnalysisPackage#getBehavioralRelation_Relation()
	 * @model required="true"
	 * @generated
	 */
	BehavioralRelationType getRelation();

	/**
	 * Sets the value of the '{@link edu.udo.cs.ls14.jf.bpmnanalysis.BehavioralRelation#getRelation <em>Relation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Relation</em>' attribute.
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.BehavioralRelationType
	 * @see #getRelation()
	 * @generated
	 */
	void setRelation(BehavioralRelationType value);

} // BehavioralRelation
