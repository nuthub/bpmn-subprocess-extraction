/**
 */
package edu.udo.cs.ls14.jf.bpmnmatching;

import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Process Matcher</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see edu.udo.cs.ls14.jf.bpmnmatching.BpmnMatchingPackage#getProcessMatcher()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface ProcessMatcher extends EObject {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model exceptions="edu.udo.cs.ls14.jf.bpmnanalysis.Exception"
	 * @generated
	 */
	ProcessMatching match(ProcessAnalysis analysis1, ProcessAnalysis analysis2) throws Exception;

} // ProcessMatcher
