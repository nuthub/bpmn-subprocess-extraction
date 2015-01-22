/**
 */
package edu.udo.cs.ls14.jf.bpmnmatching;

import org.eclipse.bpmn2.FlowNode;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Node Pair</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link edu.udo.cs.ls14.jf.bpmnmatching.NodePair#getA <em>A</em>}</li>
 *   <li>{@link edu.udo.cs.ls14.jf.bpmnmatching.NodePair#getB <em>B</em>}</li>
 * </ul>
 * </p>
 *
 * @see edu.udo.cs.ls14.jf.bpmnmatching.BpmnMatchingPackage#getNodePair()
 * @model
 * @generated
 */
public interface NodePair extends EObject {
	/**
	 * Returns the value of the '<em><b>A</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>A</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>A</em>' reference.
	 * @see #setA(FlowNode)
	 * @see edu.udo.cs.ls14.jf.bpmnmatching.BpmnMatchingPackage#getNodePair_A()
	 * @model required="true"
	 * @generated
	 */
	FlowNode getA();

	/**
	 * Sets the value of the '{@link edu.udo.cs.ls14.jf.bpmnmatching.NodePair#getA <em>A</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>A</em>' reference.
	 * @see #getA()
	 * @generated
	 */
	void setA(FlowNode value);

	/**
	 * Returns the value of the '<em><b>B</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>B</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>B</em>' reference.
	 * @see #setB(FlowNode)
	 * @see edu.udo.cs.ls14.jf.bpmnmatching.BpmnMatchingPackage#getNodePair_B()
	 * @model required="true"
	 * @generated
	 */
	FlowNode getB();

	/**
	 * Sets the value of the '{@link edu.udo.cs.ls14.jf.bpmnmatching.NodePair#getB <em>B</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>B</em>' reference.
	 * @see #getB()
	 * @generated
	 */
	void setB(FlowNode value);

} // NodePair
