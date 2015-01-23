/**
 */
package edu.udo.cs.ls14.jf.bpmnanalysis;

import org.eclipse.bpmn2.Definitions;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Process Analyzer</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see edu.udo.cs.ls14.jf.bpmnanalysis.BpmnAnalysisPackage#getProcessAnalyzer()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface ProcessAnalyzer extends EObject {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model exceptions="edu.udo.cs.ls14.jf.bpmnanalysis.Exception"
	 * @generated
	 */
	ProcessAnalysis analyze(Definitions definitions) throws Exception;

} // ProcessAnalyzer
