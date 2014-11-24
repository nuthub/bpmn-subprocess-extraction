/**
 */
package edu.udo.cs.ls14.jf.bpmnanalysis;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.SequenceFlow;

import org.eclipse.dd.dc.Point;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Fragment</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link edu.udo.cs.ls14.jf.bpmnanalysis.Fragment#getDefinitions <em>Definitions</em>}</li>
 *   <li>{@link edu.udo.cs.ls14.jf.bpmnanalysis.Fragment#getParent <em>Parent</em>}</li>
 *   <li>{@link edu.udo.cs.ls14.jf.bpmnanalysis.Fragment#getEntry <em>Entry</em>}</li>
 *   <li>{@link edu.udo.cs.ls14.jf.bpmnanalysis.Fragment#getExit <em>Exit</em>}</li>
 *   <li>{@link edu.udo.cs.ls14.jf.bpmnanalysis.Fragment#getLabel <em>Label</em>}</li>
 *   <li>{@link edu.udo.cs.ls14.jf.bpmnanalysis.Fragment#getCenter <em>Center</em>}</li>
 * </ul>
 * </p>
 *
 * @see edu.udo.cs.ls14.jf.bpmnanalysis.BpmnAnalysisPackage#getFragment()
 * @model
 * @generated
 */
public interface Fragment extends EObject {
	/**
	 * Returns the value of the '<em><b>Definitions</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Definitions</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Definitions</em>' reference.
	 * @see #setDefinitions(Definitions)
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.BpmnAnalysisPackage#getFragment_Definitions()
	 * @model required="true"
	 * @generated
	 */
	Definitions getDefinitions();

	/**
	 * Sets the value of the '{@link edu.udo.cs.ls14.jf.bpmnanalysis.Fragment#getDefinitions <em>Definitions</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Definitions</em>' reference.
	 * @see #getDefinitions()
	 * @generated
	 */
	void setDefinitions(Definitions value);

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
	 * Returns the value of the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Label</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Label</em>' attribute.
	 * @see #setLabel(String)
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.BpmnAnalysisPackage#getFragment_Label()
	 * @model
	 * @generated
	 */
	String getLabel();

	/**
	 * Sets the value of the '{@link edu.udo.cs.ls14.jf.bpmnanalysis.Fragment#getLabel <em>Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Label</em>' attribute.
	 * @see #getLabel()
	 * @generated
	 */
	void setLabel(String value);

	/**
	 * Returns the value of the '<em><b>Center</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Center</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Center</em>' containment reference.
	 * @see #setCenter(Point)
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.BpmnAnalysisPackage#getFragment_Center()
	 * @model containment="true"
	 * @generated
	 */
	Point getCenter();

	/**
	 * Sets the value of the '{@link edu.udo.cs.ls14.jf.bpmnanalysis.Fragment#getCenter <em>Center</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Center</em>' containment reference.
	 * @see #getCenter()
	 * @generated
	 */
	void setCenter(Point value);

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
