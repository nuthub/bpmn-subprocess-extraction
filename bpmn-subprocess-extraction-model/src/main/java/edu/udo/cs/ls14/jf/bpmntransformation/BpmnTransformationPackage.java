/**
 */
package edu.udo.cs.ls14.jf.bpmntransformation;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see edu.udo.cs.ls14.jf.bpmntransformation.BpmnTransformationFactory
 * @model kind="package"
 * @generated
 */
public interface BpmnTransformationPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "bpmntransformation";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://jf.ls14.cs.udo.edu/bpmntransformation/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "bpmntransformation";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	BpmnTransformationPackage eINSTANCE = edu.udo.cs.ls14.jf.bpmntransformation.impl.BpmnTransformationPackageImpl.init();

	/**
	 * The meta object id for the '{@link edu.udo.cs.ls14.jf.bpmntransformation.impl.ProcessTransformationImpl <em>Process Transformation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.udo.cs.ls14.jf.bpmntransformation.impl.ProcessTransformationImpl
	 * @see edu.udo.cs.ls14.jf.bpmntransformation.impl.BpmnTransformationPackageImpl#getProcessTransformation()
	 * @generated
	 */
	int PROCESS_TRANSFORMATION = 0;

	/**
	 * The feature id for the '<em><b>Process Matching</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_TRANSFORMATION__PROCESS_MATCHING = 0;

	/**
	 * The feature id for the '<em><b>Results</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_TRANSFORMATION__RESULTS = 1;

	/**
	 * The number of structural features of the '<em>Process Transformation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_TRANSFORMATION_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Process Transformation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_TRANSFORMATION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link edu.udo.cs.ls14.jf.bpmntransformation.impl.TransformationResultEntryImpl <em>Transformation Result Entry</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.udo.cs.ls14.jf.bpmntransformation.impl.TransformationResultEntryImpl
	 * @see edu.udo.cs.ls14.jf.bpmntransformation.impl.BpmnTransformationPackageImpl#getTransformationResultEntry()
	 * @generated
	 */
	int TRANSFORMATION_RESULT_ENTRY = 1;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_RESULT_ENTRY__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_RESULT_ENTRY__VALUE = 1;

	/**
	 * The number of structural features of the '<em>Transformation Result Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_RESULT_ENTRY_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Transformation Result Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_RESULT_ENTRY_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link edu.udo.cs.ls14.jf.bpmntransformation.ProcessTransformer <em>Process Transformer</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.udo.cs.ls14.jf.bpmntransformation.ProcessTransformer
	 * @see edu.udo.cs.ls14.jf.bpmntransformation.impl.BpmnTransformationPackageImpl#getProcessTransformer()
	 * @generated
	 */
	int PROCESS_TRANSFORMER = 2;

	/**
	 * The number of structural features of the '<em>Process Transformer</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_TRANSFORMER_FEATURE_COUNT = 0;

	/**
	 * The operation id for the '<em>Transform</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_TRANSFORMER___TRANSFORM__PROCESSMATCHING = 0;

	/**
	 * The number of operations of the '<em>Process Transformer</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_TRANSFORMER_OPERATION_COUNT = 1;


	/**
	 * Returns the meta object for class '{@link edu.udo.cs.ls14.jf.bpmntransformation.ProcessTransformation <em>Process Transformation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Process Transformation</em>'.
	 * @see edu.udo.cs.ls14.jf.bpmntransformation.ProcessTransformation
	 * @generated
	 */
	EClass getProcessTransformation();

	/**
	 * Returns the meta object for the containment reference '{@link edu.udo.cs.ls14.jf.bpmntransformation.ProcessTransformation#getProcessMatching <em>Process Matching</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Process Matching</em>'.
	 * @see edu.udo.cs.ls14.jf.bpmntransformation.ProcessTransformation#getProcessMatching()
	 * @see #getProcessTransformation()
	 * @generated
	 */
	EReference getProcessTransformation_ProcessMatching();

	/**
	 * Returns the meta object for the map '{@link edu.udo.cs.ls14.jf.bpmntransformation.ProcessTransformation#getResults <em>Results</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Results</em>'.
	 * @see edu.udo.cs.ls14.jf.bpmntransformation.ProcessTransformation#getResults()
	 * @see #getProcessTransformation()
	 * @generated
	 */
	EReference getProcessTransformation_Results();

	/**
	 * Returns the meta object for class '{@link java.util.Map.Entry <em>Transformation Result Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Transformation Result Entry</em>'.
	 * @see java.util.Map.Entry
	 * @model keyDataType="org.eclipse.emf.ecore.EString" keyRequired="true"
	 *        valueType="org.eclipse.bpmn2.Definitions" valueContainment="true" valueRequired="true"
	 * @generated
	 */
	EClass getTransformationResultEntry();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see java.util.Map.Entry
	 * @see #getTransformationResultEntry()
	 * @generated
	 */
	EAttribute getTransformationResultEntry_Key();

	/**
	 * Returns the meta object for the containment reference '{@link java.util.Map.Entry <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Value</em>'.
	 * @see java.util.Map.Entry
	 * @see #getTransformationResultEntry()
	 * @generated
	 */
	EReference getTransformationResultEntry_Value();

	/**
	 * Returns the meta object for class '{@link edu.udo.cs.ls14.jf.bpmntransformation.ProcessTransformer <em>Process Transformer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Process Transformer</em>'.
	 * @see edu.udo.cs.ls14.jf.bpmntransformation.ProcessTransformer
	 * @generated
	 */
	EClass getProcessTransformer();

	/**
	 * Returns the meta object for the '{@link edu.udo.cs.ls14.jf.bpmntransformation.ProcessTransformer#transform(edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching) <em>Transform</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Transform</em>' operation.
	 * @see edu.udo.cs.ls14.jf.bpmntransformation.ProcessTransformer#transform(edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching)
	 * @generated
	 */
	EOperation getProcessTransformer__Transform__ProcessMatching();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	BpmnTransformationFactory getBpmnTransformationFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link edu.udo.cs.ls14.jf.bpmntransformation.impl.ProcessTransformationImpl <em>Process Transformation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.udo.cs.ls14.jf.bpmntransformation.impl.ProcessTransformationImpl
		 * @see edu.udo.cs.ls14.jf.bpmntransformation.impl.BpmnTransformationPackageImpl#getProcessTransformation()
		 * @generated
		 */
		EClass PROCESS_TRANSFORMATION = eINSTANCE.getProcessTransformation();

		/**
		 * The meta object literal for the '<em><b>Process Matching</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROCESS_TRANSFORMATION__PROCESS_MATCHING = eINSTANCE.getProcessTransformation_ProcessMatching();

		/**
		 * The meta object literal for the '<em><b>Results</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROCESS_TRANSFORMATION__RESULTS = eINSTANCE.getProcessTransformation_Results();

		/**
		 * The meta object literal for the '{@link edu.udo.cs.ls14.jf.bpmntransformation.impl.TransformationResultEntryImpl <em>Transformation Result Entry</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.udo.cs.ls14.jf.bpmntransformation.impl.TransformationResultEntryImpl
		 * @see edu.udo.cs.ls14.jf.bpmntransformation.impl.BpmnTransformationPackageImpl#getTransformationResultEntry()
		 * @generated
		 */
		EClass TRANSFORMATION_RESULT_ENTRY = eINSTANCE.getTransformationResultEntry();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRANSFORMATION_RESULT_ENTRY__KEY = eINSTANCE.getTransformationResultEntry_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRANSFORMATION_RESULT_ENTRY__VALUE = eINSTANCE.getTransformationResultEntry_Value();

		/**
		 * The meta object literal for the '{@link edu.udo.cs.ls14.jf.bpmntransformation.ProcessTransformer <em>Process Transformer</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.udo.cs.ls14.jf.bpmntransformation.ProcessTransformer
		 * @see edu.udo.cs.ls14.jf.bpmntransformation.impl.BpmnTransformationPackageImpl#getProcessTransformer()
		 * @generated
		 */
		EClass PROCESS_TRANSFORMER = eINSTANCE.getProcessTransformer();

		/**
		 * The meta object literal for the '<em><b>Transform</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation PROCESS_TRANSFORMER___TRANSFORM__PROCESSMATCHING = eINSTANCE.getProcessTransformer__Transform__ProcessMatching();

	}

} //BpmnTransformationPackage
