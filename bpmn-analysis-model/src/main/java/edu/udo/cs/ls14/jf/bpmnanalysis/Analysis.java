/**
 */
package edu.udo.cs.ls14.jf.bpmnanalysis;

import org.eclipse.bpmn2.Definitions;

import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Analysis</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link edu.udo.cs.ls14.jf.bpmnanalysis.Analysis#getId <em>Id</em>}</li>
 *   <li>{@link edu.udo.cs.ls14.jf.bpmnanalysis.Analysis#getDefinitions <em>Definitions</em>}</li>
 *   <li>{@link edu.udo.cs.ls14.jf.bpmnanalysis.Analysis#getResults <em>Results</em>}</li>
 * </ul>
 * </p>
 *
 * @see edu.udo.cs.ls14.jf.bpmnanalysis.BpmnAnalysisPackage#getAnalysis()
 * @model
 * @generated
 */
public interface Analysis extends EObject {
	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.BpmnAnalysisPackage#getAnalysis_Id()
	 * @model
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link edu.udo.cs.ls14.jf.bpmnanalysis.Analysis#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>Definitions</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Definitions</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Definitions</em>' containment reference.
	 * @see #setDefinitions(Definitions)
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.BpmnAnalysisPackage#getAnalysis_Definitions()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Definitions getDefinitions();

	/**
	 * Sets the value of the '{@link edu.udo.cs.ls14.jf.bpmnanalysis.Analysis#getDefinitions <em>Definitions</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Definitions</em>' containment reference.
	 * @see #getDefinitions()
	 * @generated
	 */
	void setDefinitions(Definitions value);

	/**
	 * Returns the value of the '<em><b>Results</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link edu.udo.cs.ls14.jf.bpmnanalysis.AnalysisResult},
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Results</em>' map isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Results</em>' map.
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.BpmnAnalysisPackage#getAnalysis_Results()
	 * @model mapType="edu.udo.cs.ls14.jf.bpmnanalysis.AnalysisResultEntry<org.eclipse.emf.ecore.xml.type.ID, edu.udo.cs.ls14.jf.bpmnanalysis.AnalysisResult>"
	 * @generated
	 */
	EMap<String, AnalysisResult> getResults();

} // Analysis
