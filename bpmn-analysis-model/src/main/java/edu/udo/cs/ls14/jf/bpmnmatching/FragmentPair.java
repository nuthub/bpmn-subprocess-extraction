/**
 */
package edu.udo.cs.ls14.jf.bpmnmatching;

import edu.udo.cs.ls14.jf.bpmnanalysis.Fragment;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Fragment Pair</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link edu.udo.cs.ls14.jf.bpmnmatching.FragmentPair#getA <em>A</em>}</li>
 *   <li>{@link edu.udo.cs.ls14.jf.bpmnmatching.FragmentPair#getB <em>B</em>}</li>
 * </ul>
 * </p>
 *
 * @see edu.udo.cs.ls14.jf.bpmnmatching.BpmnMatchingPackage#getFragmentPair()
 * @model
 * @generated
 */
public interface FragmentPair extends EObject {
	/**
	 * Returns the value of the '<em><b>A</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>A</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>A</em>' reference.
	 * @see #setA(Fragment)
	 * @see edu.udo.cs.ls14.jf.bpmnmatching.BpmnMatchingPackage#getFragmentPair_A()
	 * @model required="true"
	 * @generated
	 */
	Fragment getA();

	/**
	 * Sets the value of the '{@link edu.udo.cs.ls14.jf.bpmnmatching.FragmentPair#getA <em>A</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>A</em>' reference.
	 * @see #getA()
	 * @generated
	 */
	void setA(Fragment value);

	/**
	 * Returns the value of the '<em><b>B</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>B</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>B</em>' reference.
	 * @see #setB(Fragment)
	 * @see edu.udo.cs.ls14.jf.bpmnmatching.BpmnMatchingPackage#getFragmentPair_B()
	 * @model required="true"
	 * @generated
	 */
	Fragment getB();

	/**
	 * Sets the value of the '{@link edu.udo.cs.ls14.jf.bpmnmatching.FragmentPair#getB <em>B</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>B</em>' reference.
	 * @see #getB()
	 * @generated
	 */
	void setB(Fragment value);

} // FragmentPair
