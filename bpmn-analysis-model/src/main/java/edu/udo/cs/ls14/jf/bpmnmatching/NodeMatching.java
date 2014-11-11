/**
 */
package edu.udo.cs.ls14.jf.bpmnmatching;

import java.util.List;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Node Matching</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link edu.udo.cs.ls14.jf.bpmnmatching.NodeMatching#getPairs <em>Pairs</em>}</li>
 * </ul>
 * </p>
 *
 * @see edu.udo.cs.ls14.jf.bpmnmatching.BpmnMatchingPackage#getNodeMatching()
 * @model
 * @generated
 */
public interface NodeMatching extends EObject {
	/**
	 * Returns the value of the '<em><b>Pairs</b></em>' reference list.
	 * The list contents are of type {@link edu.udo.cs.ls14.jf.bpmnmatching.NodePair}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Pairs</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Pairs</em>' reference list.
	 * @see edu.udo.cs.ls14.jf.bpmnmatching.BpmnMatchingPackage#getNodeMatching_Pairs()
	 * @model
	 * @generated
	 */
	List<NodePair> getPairs();

} // NodeMatching
