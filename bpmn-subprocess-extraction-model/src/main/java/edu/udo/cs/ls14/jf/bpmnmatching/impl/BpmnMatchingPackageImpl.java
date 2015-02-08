/**
 */
package edu.udo.cs.ls14.jf.bpmnmatching.impl;

import edu.udo.cs.ls14.jf.bpmnanalysis.BpmnAnalysisPackage;

import edu.udo.cs.ls14.jf.bpmnmatching.BpmnMatchingFactory;
import edu.udo.cs.ls14.jf.bpmnmatching.BpmnMatchingPackage;
import edu.udo.cs.ls14.jf.bpmnmatching.FragmentMatching;
import edu.udo.cs.ls14.jf.bpmnmatching.FragmentPair;
import edu.udo.cs.ls14.jf.bpmnmatching.NodeMatching;
import edu.udo.cs.ls14.jf.bpmnmatching.NodePair;
import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatcher;
import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;

import org.eclipse.bpmn2.Bpmn2Package;

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
public class BpmnMatchingPackageImpl extends EPackageImpl implements BpmnMatchingPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass processMatchingEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass nodeMatchingEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass nodePairEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass fragmentMatchingEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass fragmentPairEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass processMatcherEClass = null;

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
	 * @see edu.udo.cs.ls14.jf.bpmnmatching.BpmnMatchingPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private BpmnMatchingPackageImpl() {
		super(eNS_URI, BpmnMatchingFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link BpmnMatchingPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static BpmnMatchingPackage init() {
		if (isInited) return (BpmnMatchingPackage)EPackage.Registry.INSTANCE.getEPackage(BpmnMatchingPackage.eNS_URI);

		// Obtain or create and register package
		BpmnMatchingPackageImpl theBpmnMatchingPackage = (BpmnMatchingPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof BpmnMatchingPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new BpmnMatchingPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		BpmnAnalysisPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theBpmnMatchingPackage.createPackageContents();

		// Initialize created meta-data
		theBpmnMatchingPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theBpmnMatchingPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(BpmnMatchingPackage.eNS_URI, theBpmnMatchingPackage);
		return theBpmnMatchingPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProcessMatching() {
		return processMatchingEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProcessMatching_Analysis1() {
		return (EReference)processMatchingEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProcessMatching_Analysis2() {
		return (EReference)processMatchingEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProcessMatching_NodeMatching() {
		return (EReference)processMatchingEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProcessMatching_FragmentMatching() {
		return (EReference)processMatchingEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNodeMatching() {
		return nodeMatchingEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getNodeMatching_Pairs() {
		return (EReference)nodeMatchingEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNodePair() {
		return nodePairEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getNodePair_A() {
		return (EReference)nodePairEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getNodePair_B() {
		return (EReference)nodePairEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFragmentMatching() {
		return fragmentMatchingEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFragmentMatching_Pairs() {
		return (EReference)fragmentMatchingEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFragmentPair() {
		return fragmentPairEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFragmentPair_A() {
		return (EReference)fragmentPairEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFragmentPair_B() {
		return (EReference)fragmentPairEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFragmentPair_Better() {
		return (EReference)fragmentPairEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFragmentPair_ExtractedProcess() {
		return (EReference)fragmentPairEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProcessMatcher() {
		return processMatcherEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getProcessMatcher__Match__ProcessAnalysis_ProcessAnalysis() {
		return processMatcherEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BpmnMatchingFactory getBpmnMatchingFactory() {
		return (BpmnMatchingFactory)getEFactoryInstance();
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
		processMatchingEClass = createEClass(PROCESS_MATCHING);
		createEReference(processMatchingEClass, PROCESS_MATCHING__ANALYSIS1);
		createEReference(processMatchingEClass, PROCESS_MATCHING__ANALYSIS2);
		createEReference(processMatchingEClass, PROCESS_MATCHING__NODE_MATCHING);
		createEReference(processMatchingEClass, PROCESS_MATCHING__FRAGMENT_MATCHING);

		nodeMatchingEClass = createEClass(NODE_MATCHING);
		createEReference(nodeMatchingEClass, NODE_MATCHING__PAIRS);

		nodePairEClass = createEClass(NODE_PAIR);
		createEReference(nodePairEClass, NODE_PAIR__A);
		createEReference(nodePairEClass, NODE_PAIR__B);

		fragmentMatchingEClass = createEClass(FRAGMENT_MATCHING);
		createEReference(fragmentMatchingEClass, FRAGMENT_MATCHING__PAIRS);

		fragmentPairEClass = createEClass(FRAGMENT_PAIR);
		createEReference(fragmentPairEClass, FRAGMENT_PAIR__A);
		createEReference(fragmentPairEClass, FRAGMENT_PAIR__B);
		createEReference(fragmentPairEClass, FRAGMENT_PAIR__BETTER);
		createEReference(fragmentPairEClass, FRAGMENT_PAIR__EXTRACTED_PROCESS);

		processMatcherEClass = createEClass(PROCESS_MATCHER);
		createEOperation(processMatcherEClass, PROCESS_MATCHER___MATCH__PROCESSANALYSIS_PROCESSANALYSIS);
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
		BpmnAnalysisPackage theBpmnAnalysisPackage = (BpmnAnalysisPackage)EPackage.Registry.INSTANCE.getEPackage(BpmnAnalysisPackage.eNS_URI);
		Bpmn2Package theBpmn2Package = (Bpmn2Package)EPackage.Registry.INSTANCE.getEPackage(Bpmn2Package.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes

		// Initialize classes, features, and operations; add parameters
		initEClass(processMatchingEClass, ProcessMatching.class, "ProcessMatching", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getProcessMatching_Analysis1(), theBpmnAnalysisPackage.getProcessAnalysis(), null, "analysis1", null, 1, 1, ProcessMatching.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProcessMatching_Analysis2(), theBpmnAnalysisPackage.getProcessAnalysis(), null, "analysis2", null, 1, 1, ProcessMatching.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProcessMatching_NodeMatching(), this.getNodeMatching(), null, "nodeMatching", null, 1, 1, ProcessMatching.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProcessMatching_FragmentMatching(), this.getFragmentMatching(), null, "fragmentMatching", null, 1, 1, ProcessMatching.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(nodeMatchingEClass, NodeMatching.class, "NodeMatching", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getNodeMatching_Pairs(), this.getNodePair(), null, "pairs", null, 0, -1, NodeMatching.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(nodePairEClass, NodePair.class, "NodePair", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getNodePair_A(), theBpmn2Package.getFlowNode(), null, "a", null, 1, 1, NodePair.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getNodePair_B(), theBpmn2Package.getFlowNode(), null, "b", null, 1, 1, NodePair.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(fragmentMatchingEClass, FragmentMatching.class, "FragmentMatching", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getFragmentMatching_Pairs(), this.getFragmentPair(), null, "pairs", null, 0, -1, FragmentMatching.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(fragmentPairEClass, FragmentPair.class, "FragmentPair", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getFragmentPair_A(), theBpmnAnalysisPackage.getFragment(), null, "a", null, 1, 1, FragmentPair.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFragmentPair_B(), theBpmnAnalysisPackage.getFragment(), null, "b", null, 1, 1, FragmentPair.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFragmentPair_Better(), theBpmnAnalysisPackage.getFragment(), null, "better", null, 0, 1, FragmentPair.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFragmentPair_ExtractedProcess(), theBpmn2Package.getCallableElement(), null, "extractedProcess", null, 0, 1, FragmentPair.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(processMatcherEClass, ProcessMatcher.class, "ProcessMatcher", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		EOperation op = initEOperation(getProcessMatcher__Match__ProcessAnalysis_ProcessAnalysis(), this.getProcessMatching(), "match", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, theBpmnAnalysisPackage.getProcessAnalysis(), "analysis1", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, theBpmnAnalysisPackage.getProcessAnalysis(), "analysis2", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEException(op, theBpmnAnalysisPackage.getException());

		// Create resource
		createResource(eNS_URI);
	}

} //BpmnMatchingPackageImpl
