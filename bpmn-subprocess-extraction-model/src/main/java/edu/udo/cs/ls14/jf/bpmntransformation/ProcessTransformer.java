/**
 */
package edu.udo.cs.ls14.jf.bpmntransformation;

import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Process Transformer</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see edu.udo.cs.ls14.jf.bpmntransformation.BpmnTransformationPackage#getProcessTransformer()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface ProcessTransformer extends EObject {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model exceptions="edu.udo.cs.ls14.jf.bpmnanalysis.Exception"
	 * @generated
	 */
	ProcessTransformation transform(ProcessMatching matching) throws Exception;

} // ProcessTransformer
