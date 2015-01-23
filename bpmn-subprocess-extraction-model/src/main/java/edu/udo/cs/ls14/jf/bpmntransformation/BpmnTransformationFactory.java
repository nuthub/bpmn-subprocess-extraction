/**
 */
package edu.udo.cs.ls14.jf.bpmntransformation;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see edu.udo.cs.ls14.jf.bpmntransformation.BpmnTransformationPackage
 * @generated
 */
public interface BpmnTransformationFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	BpmnTransformationFactory eINSTANCE = edu.udo.cs.ls14.jf.bpmntransformation.impl.BpmnTransformationFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Process Transformation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Process Transformation</em>'.
	 * @generated
	 */
	ProcessTransformation createProcessTransformation();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	BpmnTransformationPackage getBpmnTransformationPackage();

} //BpmnTransformationFactory
