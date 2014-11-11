/**
 */
package edu.udo.cs.ls14.jf.bpmnanalysis;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see edu.udo.cs.ls14.jf.bpmnanalysis.BpmnAnalysisPackage
 * @generated
 */
public interface BpmnAnalysisFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	BpmnAnalysisFactory eINSTANCE = edu.udo.cs.ls14.jf.bpmnanalysis.impl.BpmnAnalysisFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Process Analysis</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Process Analysis</em>'.
	 * @generated
	 */
	ProcessAnalysis createProcessAnalysis();

	/**
	 * Returns a new object of class '<em>Behavioral Profile</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Behavioral Profile</em>'.
	 * @generated
	 */
	BehavioralProfile createBehavioralProfile();

	/**
	 * Returns a new object of class '<em>Behavioral Relation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Behavioral Relation</em>'.
	 * @generated
	 */
	BehavioralRelation createBehavioralRelation();

	/**
	 * Returns a new object of class '<em>Conditional Profile</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Conditional Profile</em>'.
	 * @generated
	 */
	ConditionalProfile createConditionalProfile();

	/**
	 * Returns a new object of class '<em>Process Structure Tree</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Process Structure Tree</em>'.
	 * @generated
	 */
	ProcessStructureTree createProcessStructureTree();

	/**
	 * Returns a new object of class '<em>Fragment</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Fragment</em>'.
	 * @generated
	 */
	Fragment createFragment();

	/**
	 * Returns a new object of class '<em>Trace</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Trace</em>'.
	 * @generated
	 */
	Trace createTrace();

	/**
	 * Returns a new object of class '<em>Trace Profile</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Trace Profile</em>'.
	 * @generated
	 */
	TraceProfile createTraceProfile();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	BpmnAnalysisPackage getBpmnAnalysisPackage();

} //BpmnAnalysisFactory
