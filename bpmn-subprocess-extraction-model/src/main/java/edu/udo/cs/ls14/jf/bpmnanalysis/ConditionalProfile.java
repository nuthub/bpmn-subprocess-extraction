/**
 */
package edu.udo.cs.ls14.jf.bpmnanalysis;

import java.util.List;
import java.util.Map;

import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.FormalExpression;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Conditional Profile</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link edu.udo.cs.ls14.jf.bpmnanalysis.ConditionalProfile#getRelations <em>Relations</em>}</li>
 * </ul>
 * </p>
 *
 * @see edu.udo.cs.ls14.jf.bpmnanalysis.BpmnAnalysisPackage#getConditionalProfile()
 * @model
 * @generated
 */
public interface ConditionalProfile extends ProcessAnalysisResult {
	/**
	 * Returns the value of the '<em><b>Relations</b></em>' map.
	 * The key is of type {@link org.eclipse.bpmn2.FlowNode},
	 * and the value is of type list of {@link org.eclipse.bpmn2.FormalExpression},
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Relations</em>' map isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Relations</em>' map.
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.BpmnAnalysisPackage#getConditionalProfile_Relations()
	 * @model mapType="edu.udo.cs.ls14.jf.bpmnanalysis.ConditionRelationEntry<org.eclipse.bpmn2.FlowNode, org.eclipse.bpmn2.FormalExpression>"
	 * @generated
	 */
	Map<FlowNode, List<FormalExpression>> getRelations();

} // ConditionalProfile
