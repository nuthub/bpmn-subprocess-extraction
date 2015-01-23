/**
 */
package edu.udo.cs.ls14.jf.bpmnanalysis;

import java.util.Map;

import org.eclipse.bpmn2.Definitions;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Process Analysis</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis#getDefinitions <em>Definitions</em>}</li>
 *   <li>{@link edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis#getResults <em>Results</em>}</li>
 * </ul>
 * </p>
 *
 * @see edu.udo.cs.ls14.jf.bpmnanalysis.BpmnAnalysisPackage#getProcessAnalysis()
 * @model
 * @generated
 */
public interface ProcessAnalysis extends EObject {

	/**
	 * Returns the value of the '<em><b>Definitions</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Definitions</em>' containment reference isn't
	 * clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Definitions</em>' containment reference.
	 * @see #setDefinitions(Definitions)
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.BpmnAnalysisPackage#getProcessAnalysis_Definitions()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Definitions getDefinitions();

	/**
	 * Sets the value of the '{@link edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis#getDefinitions <em>Definitions</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Definitions</em>' containment reference.
	 * @see #getDefinitions()
	 * @generated
	 */
	void setDefinitions(Definitions value);

	/**
	 * Returns the value of the '<em><b>Results</b></em>' map. The key is of
	 * type {@link java.lang.String}, and the value is of type
	 * {@link edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysisResult}, <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Results</em>' map isn't clear, there really
	 * should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Results</em>' map.
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.BpmnAnalysisPackage#getProcessAnalysis_Results()
	 * @model mapType=
	 *        "edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysisResultEntry<org.eclipse.emf.ecore.xml.type.ID, edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysisResult>"
	 * @generated
	 */
	Map<String, ProcessAnalysisResult> getResults();

} // ProcessAnalysis
