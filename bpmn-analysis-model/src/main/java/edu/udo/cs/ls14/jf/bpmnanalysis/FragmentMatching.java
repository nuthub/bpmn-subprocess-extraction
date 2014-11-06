/**
 */
package edu.udo.cs.ls14.jf.bpmnanalysis;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Fragment Matching</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link edu.udo.cs.ls14.jf.bpmnanalysis.FragmentMatching#getPairs <em>Pairs</em>}</li>
 * </ul>
 * </p>
 *
 * @see edu.udo.cs.ls14.jf.bpmnanalysis.BpmnAnalysisPackage#getFragmentMatching()
 * @model
 * @generated
 */
public interface FragmentMatching extends AnalysisResult {
	/**
	 * Returns the value of the '<em><b>Pairs</b></em>' reference list.
	 * The list contents are of type {@link edu.udo.cs.ls14.jf.bpmnanalysis.FragmentPair}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Pairs</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Pairs</em>' reference list.
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.BpmnAnalysisPackage#getFragmentMatching_Pairs()
	 * @model
	 * @generated
	 */
	EList<FragmentPair> getPairs();

} // FragmentMatching
