/**
 */
package edu.udo.cs.ls14.jf.bpmnanalysis;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Behavioral Profile</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link edu.udo.cs.ls14.jf.bpmnanalysis.BehavioralProfile#getTraces <em>Traces</em>}</li>
 *   <li>{@link edu.udo.cs.ls14.jf.bpmnanalysis.BehavioralProfile#getRelations <em>Relations</em>}</li>
 * </ul>
 * </p>
 *
 * @see edu.udo.cs.ls14.jf.bpmnanalysis.BpmnAnalysisPackage#getBehavioralProfile()
 * @model
 * @generated
 */
public interface BehavioralProfile extends AnalysisResult {
	/**
	 * Returns the value of the '<em><b>Traces</b></em>' containment reference list.
	 * The list contents are of type {@link edu.udo.cs.ls14.jf.bpmnanalysis.Trace}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Traces</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Traces</em>' containment reference list.
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.BpmnAnalysisPackage#getBehavioralProfile_Traces()
	 * @model containment="true"
	 * @generated
	 */
	EList<Trace> getTraces();

	/**
	 * Returns the value of the '<em><b>Relations</b></em>' containment reference list.
	 * The list contents are of type {@link edu.udo.cs.ls14.jf.bpmnanalysis.BehavioralRelation}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Relations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Relations</em>' containment reference list.
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.BpmnAnalysisPackage#getBehavioralProfile_Relations()
	 * @model containment="true"
	 * @generated
	 */
	EList<BehavioralRelation> getRelations();

} // BehavioralProfile
