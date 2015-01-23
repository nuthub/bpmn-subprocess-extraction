/**
 */
package edu.udo.cs.ls14.jf.bpmntransformation.impl;

import edu.udo.cs.ls14.jf.bpmnanalysis.BpmnAnalysisPackage;
import edu.udo.cs.ls14.jf.bpmnmatching.BpmnMatchingPackage;
import edu.udo.cs.ls14.jf.bpmntransformation.BpmnTransformationFactory;
import edu.udo.cs.ls14.jf.bpmntransformation.BpmnTransformationPackage;
import edu.udo.cs.ls14.jf.bpmntransformation.ProcessTransformation;
import edu.udo.cs.ls14.jf.bpmntransformation.ProcessTransformer;
import java.util.Map;
import org.eclipse.bpmn2.Bpmn2Package;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class BpmnTransformationPackageImpl extends EPackageImpl implements BpmnTransformationPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass processTransformationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass transformationResultEntryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass processTransformerEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see edu.udo.cs.ls14.jf.bpmntransformation.BpmnTransformationPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private BpmnTransformationPackageImpl() {
		super(eNS_URI, BpmnTransformationFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link BpmnTransformationPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static BpmnTransformationPackage init() {
		if (isInited) return (BpmnTransformationPackage)EPackage.Registry.INSTANCE.getEPackage(BpmnTransformationPackage.eNS_URI);

		// Obtain or create and register package
		BpmnTransformationPackageImpl theBpmnTransformationPackage = (BpmnTransformationPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof BpmnTransformationPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new BpmnTransformationPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		BpmnMatchingPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theBpmnTransformationPackage.createPackageContents();

		// Initialize created meta-data
		theBpmnTransformationPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theBpmnTransformationPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(BpmnTransformationPackage.eNS_URI, theBpmnTransformationPackage);
		return theBpmnTransformationPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProcessTransformation() {
		return processTransformationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProcessTransformation_ProcessMatching() {
		return (EReference)processTransformationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProcessTransformation_Results() {
		return (EReference)processTransformationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTransformationResultEntry() {
		return transformationResultEntryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTransformationResultEntry_Key() {
		return (EAttribute)transformationResultEntryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTransformationResultEntry_Value() {
		return (EReference)transformationResultEntryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProcessTransformer() {
		return processTransformerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getProcessTransformer__Transform__ProcessMatching() {
		return processTransformerEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BpmnTransformationFactory getBpmnTransformationFactory() {
		return (BpmnTransformationFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		processTransformationEClass = createEClass(PROCESS_TRANSFORMATION);
		createEReference(processTransformationEClass, PROCESS_TRANSFORMATION__PROCESS_MATCHING);
		createEReference(processTransformationEClass, PROCESS_TRANSFORMATION__RESULTS);

		transformationResultEntryEClass = createEClass(TRANSFORMATION_RESULT_ENTRY);
		createEAttribute(transformationResultEntryEClass, TRANSFORMATION_RESULT_ENTRY__KEY);
		createEReference(transformationResultEntryEClass, TRANSFORMATION_RESULT_ENTRY__VALUE);

		processTransformerEClass = createEClass(PROCESS_TRANSFORMER);
		createEOperation(processTransformerEClass, PROCESS_TRANSFORMER___TRANSFORM__PROCESSMATCHING);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		BpmnMatchingPackage theBpmnMatchingPackage = (BpmnMatchingPackage)EPackage.Registry.INSTANCE.getEPackage(BpmnMatchingPackage.eNS_URI);
		Bpmn2Package theBpmn2Package = (Bpmn2Package)EPackage.Registry.INSTANCE.getEPackage(Bpmn2Package.eNS_URI);
		BpmnAnalysisPackage theBpmnAnalysisPackage = (BpmnAnalysisPackage)EPackage.Registry.INSTANCE.getEPackage(BpmnAnalysisPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes

		// Initialize classes, features, and operations; add parameters
		initEClass(processTransformationEClass, ProcessTransformation.class, "ProcessTransformation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getProcessTransformation_ProcessMatching(), theBpmnMatchingPackage.getProcessMatching(), null, "processMatching", null, 1, 1, ProcessTransformation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProcessTransformation_Results(), this.getTransformationResultEntry(), null, "results", null, 0, -1, ProcessTransformation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(transformationResultEntryEClass, Map.Entry.class, "TransformationResultEntry", !IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTransformationResultEntry_Key(), ecorePackage.getEString(), "key", null, 1, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTransformationResultEntry_Value(), theBpmn2Package.getDefinitions(), null, "value", null, 1, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(processTransformerEClass, ProcessTransformer.class, "ProcessTransformer", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		EOperation op = initEOperation(getProcessTransformer__Transform__ProcessMatching(), this.getProcessTransformation(), "transform", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, theBpmnMatchingPackage.getProcessMatching(), "matching", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEException(op, theBpmnAnalysisPackage.getException());

		// Create resource
		createResource(eNS_URI);
	}

} //BpmnTransformationPackageImpl
