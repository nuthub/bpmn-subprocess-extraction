/**
 */
package edu.udo.cs.ls14.jf.bpmntransformation;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
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
	 * The meta object id for the '{@link edu.udo.cs.ls14.jf.bpmntransformation.impl.ProcessExtractionImpl <em>Process Extraction</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.udo.cs.ls14.jf.bpmntransformation.impl.ProcessExtractionImpl
	 * @see edu.udo.cs.ls14.jf.bpmntransformation.impl.BpmnTransformationPackageImpl#getProcessExtraction()
	 * @generated
	 */
	int PROCESS_EXTRACTION = 0;

	/**
	 * The feature id for the '<em><b>Process Matching</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_EXTRACTION__PROCESS_MATCHING = 0;

	/**
	 * The feature id for the '<em><b>Results</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_EXTRACTION__RESULTS = 1;

	/**
	 * The number of structural features of the '<em>Process Extraction</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_EXTRACTION_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Process Extraction</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_EXTRACTION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link edu.udo.cs.ls14.jf.bpmntransformation.impl.ExtractionResultEntryImpl <em>Extraction Result Entry</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.udo.cs.ls14.jf.bpmntransformation.impl.ExtractionResultEntryImpl
	 * @see edu.udo.cs.ls14.jf.bpmntransformation.impl.BpmnTransformationPackageImpl#getExtractionResultEntry()
	 * @generated
	 */
	int EXTRACTION_RESULT_ENTRY = 1;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTRACTION_RESULT_ENTRY__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTRACTION_RESULT_ENTRY__VALUE = 1;

	/**
	 * The number of structural features of the '<em>Extraction Result Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTRACTION_RESULT_ENTRY_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Extraction Result Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTRACTION_RESULT_ENTRY_OPERATION_COUNT = 0;


	/**
	 * Returns the meta object for class '{@link edu.udo.cs.ls14.jf.bpmntransformation.ProcessExtraction <em>Process Extraction</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Process Extraction</em>'.
	 * @see edu.udo.cs.ls14.jf.bpmntransformation.ProcessExtraction
	 * @generated
	 */
	EClass getProcessExtraction();

	/**
	 * Returns the meta object for the containment reference '{@link edu.udo.cs.ls14.jf.bpmntransformation.ProcessExtraction#getProcessMatching <em>Process Matching</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Process Matching</em>'.
	 * @see edu.udo.cs.ls14.jf.bpmntransformation.ProcessExtraction#getProcessMatching()
	 * @see #getProcessExtraction()
	 * @generated
	 */
	EReference getProcessExtraction_ProcessMatching();

	/**
	 * Returns the meta object for the map '{@link edu.udo.cs.ls14.jf.bpmntransformation.ProcessExtraction#getResults <em>Results</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Results</em>'.
	 * @see edu.udo.cs.ls14.jf.bpmntransformation.ProcessExtraction#getResults()
	 * @see #getProcessExtraction()
	 * @generated
	 */
	EReference getProcessExtraction_Results();

	/**
	 * Returns the meta object for class '{@link java.util.Map.Entry <em>Extraction Result Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Extraction Result Entry</em>'.
	 * @see java.util.Map.Entry
	 * @model keyDataType="org.eclipse.emf.ecore.EString" keyRequired="true"
	 *        valueType="org.eclipse.bpmn2.Definitions" valueContainment="true" valueRequired="true"
	 * @generated
	 */
	EClass getExtractionResultEntry();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see java.util.Map.Entry
	 * @see #getExtractionResultEntry()
	 * @generated
	 */
	EAttribute getExtractionResultEntry_Key();

	/**
	 * Returns the meta object for the containment reference '{@link java.util.Map.Entry <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Value</em>'.
	 * @see java.util.Map.Entry
	 * @see #getExtractionResultEntry()
	 * @generated
	 */
	EReference getExtractionResultEntry_Value();

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
		 * The meta object literal for the '{@link edu.udo.cs.ls14.jf.bpmntransformation.impl.ProcessExtractionImpl <em>Process Extraction</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.udo.cs.ls14.jf.bpmntransformation.impl.ProcessExtractionImpl
		 * @see edu.udo.cs.ls14.jf.bpmntransformation.impl.BpmnTransformationPackageImpl#getProcessExtraction()
		 * @generated
		 */
		EClass PROCESS_EXTRACTION = eINSTANCE.getProcessExtraction();

		/**
		 * The meta object literal for the '<em><b>Process Matching</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROCESS_EXTRACTION__PROCESS_MATCHING = eINSTANCE.getProcessExtraction_ProcessMatching();

		/**
		 * The meta object literal for the '<em><b>Results</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROCESS_EXTRACTION__RESULTS = eINSTANCE.getProcessExtraction_Results();

		/**
		 * The meta object literal for the '{@link edu.udo.cs.ls14.jf.bpmntransformation.impl.ExtractionResultEntryImpl <em>Extraction Result Entry</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.udo.cs.ls14.jf.bpmntransformation.impl.ExtractionResultEntryImpl
		 * @see edu.udo.cs.ls14.jf.bpmntransformation.impl.BpmnTransformationPackageImpl#getExtractionResultEntry()
		 * @generated
		 */
		EClass EXTRACTION_RESULT_ENTRY = eINSTANCE.getExtractionResultEntry();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXTRACTION_RESULT_ENTRY__KEY = eINSTANCE.getExtractionResultEntry_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXTRACTION_RESULT_ENTRY__VALUE = eINSTANCE.getExtractionResultEntry_Value();

	}

} //BpmnTransformationPackage
