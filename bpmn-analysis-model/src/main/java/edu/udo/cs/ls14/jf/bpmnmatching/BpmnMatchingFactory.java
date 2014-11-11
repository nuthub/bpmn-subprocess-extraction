/**
 */
package edu.udo.cs.ls14.jf.bpmnmatching;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see edu.udo.cs.ls14.jf.bpmnmatching.BpmnMatchingPackage
 * @generated
 */
public interface BpmnMatchingFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	BpmnMatchingFactory eINSTANCE = edu.udo.cs.ls14.jf.bpmnmatching.impl.BpmnMatchingFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Node Matching</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Node Matching</em>'.
	 * @generated
	 */
	NodeMatching createNodeMatching();

	/**
	 * Returns a new object of class '<em>Node Pair</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Node Pair</em>'.
	 * @generated
	 */
	NodePair createNodePair();

	/**
	 * Returns a new object of class '<em>Fragment Matching</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Fragment Matching</em>'.
	 * @generated
	 */
	FragmentMatching createFragmentMatching();

	/**
	 * Returns a new object of class '<em>Fragment Pair</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Fragment Pair</em>'.
	 * @generated
	 */
	FragmentPair createFragmentPair();

	/**
	 * Returns a new object of class '<em>Process Matching</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Process Matching</em>'.
	 * @generated
	 */
	ProcessMatching createProcessMatching();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	BpmnMatchingPackage getBpmnMatchingPackage();

} //BpmnMatchingFactory
