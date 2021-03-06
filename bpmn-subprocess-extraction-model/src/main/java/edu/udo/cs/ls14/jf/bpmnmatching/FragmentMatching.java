/**
 */
package edu.udo.cs.ls14.jf.bpmnmatching;

import java.util.List;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Fragment Matching</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link edu.udo.cs.ls14.jf.bpmnmatching.FragmentMatching#getPairs <em>Pairs</em>}</li>
 * </ul>
 * </p>
 *
 * @see edu.udo.cs.ls14.jf.bpmnmatching.BpmnMatchingPackage#getFragmentMatching()
 * @model
 * @generated
 */
public interface FragmentMatching extends EObject {
	/**
	 * Returns the value of the '<em><b>Pairs</b></em>' containment reference list.
	 * The list contents are of type {@link edu.udo.cs.ls14.jf.bpmnmatching.FragmentPair}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Pairs</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Pairs</em>' containment reference list.
	 * @see edu.udo.cs.ls14.jf.bpmnmatching.BpmnMatchingPackage#getFragmentMatching_Pairs()
	 * @model containment="true"
	 * @generated
	 */
	List<FragmentPair> getPairs();

} // FragmentMatching
