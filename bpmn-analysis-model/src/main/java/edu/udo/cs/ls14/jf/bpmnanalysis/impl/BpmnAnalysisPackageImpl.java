/**
 */
package edu.udo.cs.ls14.jf.bpmnanalysis.impl;

import edu.udo.cs.ls14.jf.bpmnanalysis.Analysis;
import edu.udo.cs.ls14.jf.bpmnanalysis.AnalysisResult;
import edu.udo.cs.ls14.jf.bpmnanalysis.BehavioralProfile;
import edu.udo.cs.ls14.jf.bpmnanalysis.BehavioralRelation;
import edu.udo.cs.ls14.jf.bpmnanalysis.BehavioralRelationType;
import edu.udo.cs.ls14.jf.bpmnanalysis.BpmnAnalysisFactory;
import edu.udo.cs.ls14.jf.bpmnanalysis.BpmnAnalysisPackage;
import edu.udo.cs.ls14.jf.bpmnanalysis.ConditionalProfile;
import edu.udo.cs.ls14.jf.bpmnanalysis.Fragment;
import edu.udo.cs.ls14.jf.bpmnanalysis.FragmentMatching;
import edu.udo.cs.ls14.jf.bpmnanalysis.FragmentPair;
import edu.udo.cs.ls14.jf.bpmnanalysis.NodeMatching;
import edu.udo.cs.ls14.jf.bpmnanalysis.NodePair;
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessStructureTree;
import edu.udo.cs.ls14.jf.bpmnanalysis.Trace;
import edu.udo.cs.ls14.jf.bpmnanalysis.TraceProfile;
import java.util.Map;
import org.eclipse.bpmn2.Bpmn2Package;
import org.eclipse.bpmn2.di.BpmnDiPackage;
import org.eclipse.dd.dc.DcPackage;
import org.eclipse.dd.di.DiPackage;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class BpmnAnalysisPackageImpl extends EPackageImpl implements BpmnAnalysisPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass analysisEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass analysisResultEntryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass analysisResultEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass processStructureTreeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass fragmentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass traceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass traceProfileEClass = null;

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
	private EClass behavioralProfileEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass behavioralRelationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass conditionRelationEntryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass conditionalProfileEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum behavioralRelationTypeEEnum = null;

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
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.BpmnAnalysisPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private BpmnAnalysisPackageImpl() {
		super(eNS_URI, BpmnAnalysisFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link BpmnAnalysisPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static BpmnAnalysisPackage init() {
		if (isInited) return (BpmnAnalysisPackage)EPackage.Registry.INSTANCE.getEPackage(BpmnAnalysisPackage.eNS_URI);

		// Obtain or create and register package
		BpmnAnalysisPackageImpl theBpmnAnalysisPackage = (BpmnAnalysisPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof BpmnAnalysisPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new BpmnAnalysisPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		Bpmn2Package.eINSTANCE.eClass();
		BpmnDiPackage.eINSTANCE.eClass();
		DiPackage.eINSTANCE.eClass();
		DcPackage.eINSTANCE.eClass();
		XMLTypePackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theBpmnAnalysisPackage.createPackageContents();

		// Initialize created meta-data
		theBpmnAnalysisPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theBpmnAnalysisPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(BpmnAnalysisPackage.eNS_URI, theBpmnAnalysisPackage);
		return theBpmnAnalysisPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAnalysis() {
		return analysisEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAnalysis_Id() {
		return (EAttribute)analysisEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAnalysis_Definitions() {
		return (EReference)analysisEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAnalysis_Results() {
		return (EReference)analysisEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAnalysisResultEntry() {
		return analysisResultEntryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAnalysisResultEntry_Key() {
		return (EAttribute)analysisResultEntryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAnalysisResultEntry_Value() {
		return (EReference)analysisResultEntryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAnalysisResult() {
		return analysisResultEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProcessStructureTree() {
		return processStructureTreeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProcessStructureTree_Fragments() {
		return (EReference)processStructureTreeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFragment() {
		return fragmentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFragment_Entry() {
		return (EReference)fragmentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFragment_Exit() {
		return (EReference)fragmentEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFragment_Parent() {
		return (EReference)fragmentEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTrace() {
		return traceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTrace_Nodes() {
		return (EReference)traceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTrace_Finished() {
		return (EAttribute)traceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTraceProfile() {
		return traceProfileEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTraceProfile_Traces() {
		return (EReference)traceProfileEClass.getEStructuralFeatures().get(0);
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
	public EClass getBehavioralProfile() {
		return behavioralProfileEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBehavioralProfile_Traces() {
		return (EReference)behavioralProfileEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBehavioralProfile_Relations() {
		return (EReference)behavioralProfileEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBehavioralRelation() {
		return behavioralRelationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBehavioralRelation_Left() {
		return (EReference)behavioralRelationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBehavioralRelation_Right() {
		return (EReference)behavioralRelationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBehavioralRelation_Relation() {
		return (EAttribute)behavioralRelationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConditionRelationEntry() {
		return conditionRelationEntryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConditionRelationEntry_Key() {
		return (EReference)conditionRelationEntryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConditionRelationEntry_Value() {
		return (EReference)conditionRelationEntryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConditionalProfile() {
		return conditionalProfileEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConditionalProfile_Relations() {
		return (EReference)conditionalProfileEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getBehavioralRelationType() {
		return behavioralRelationTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BpmnAnalysisFactory getBpmnAnalysisFactory() {
		return (BpmnAnalysisFactory)getEFactoryInstance();
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
		analysisEClass = createEClass(ANALYSIS);
		createEAttribute(analysisEClass, ANALYSIS__ID);
		createEReference(analysisEClass, ANALYSIS__DEFINITIONS);
		createEReference(analysisEClass, ANALYSIS__RESULTS);

		analysisResultEntryEClass = createEClass(ANALYSIS_RESULT_ENTRY);
		createEAttribute(analysisResultEntryEClass, ANALYSIS_RESULT_ENTRY__KEY);
		createEReference(analysisResultEntryEClass, ANALYSIS_RESULT_ENTRY__VALUE);

		analysisResultEClass = createEClass(ANALYSIS_RESULT);

		behavioralProfileEClass = createEClass(BEHAVIORAL_PROFILE);
		createEReference(behavioralProfileEClass, BEHAVIORAL_PROFILE__TRACES);
		createEReference(behavioralProfileEClass, BEHAVIORAL_PROFILE__RELATIONS);

		behavioralRelationEClass = createEClass(BEHAVIORAL_RELATION);
		createEReference(behavioralRelationEClass, BEHAVIORAL_RELATION__LEFT);
		createEReference(behavioralRelationEClass, BEHAVIORAL_RELATION__RIGHT);
		createEAttribute(behavioralRelationEClass, BEHAVIORAL_RELATION__RELATION);

		conditionalProfileEClass = createEClass(CONDITIONAL_PROFILE);
		createEReference(conditionalProfileEClass, CONDITIONAL_PROFILE__RELATIONS);

		conditionRelationEntryEClass = createEClass(CONDITION_RELATION_ENTRY);
		createEReference(conditionRelationEntryEClass, CONDITION_RELATION_ENTRY__KEY);
		createEReference(conditionRelationEntryEClass, CONDITION_RELATION_ENTRY__VALUE);

		processStructureTreeEClass = createEClass(PROCESS_STRUCTURE_TREE);
		createEReference(processStructureTreeEClass, PROCESS_STRUCTURE_TREE__FRAGMENTS);

		fragmentEClass = createEClass(FRAGMENT);
		createEReference(fragmentEClass, FRAGMENT__ENTRY);
		createEReference(fragmentEClass, FRAGMENT__EXIT);
		createEReference(fragmentEClass, FRAGMENT__PARENT);

		traceEClass = createEClass(TRACE);
		createEReference(traceEClass, TRACE__NODES);
		createEAttribute(traceEClass, TRACE__FINISHED);

		traceProfileEClass = createEClass(TRACE_PROFILE);
		createEReference(traceProfileEClass, TRACE_PROFILE__TRACES);

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

		// Create enums
		behavioralRelationTypeEEnum = createEEnum(BEHAVIORAL_RELATION_TYPE);
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
		Bpmn2Package theBpmn2Package = (Bpmn2Package)EPackage.Registry.INSTANCE.getEPackage(Bpmn2Package.eNS_URI);
		XMLTypePackage theXMLTypePackage = (XMLTypePackage)EPackage.Registry.INSTANCE.getEPackage(XMLTypePackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		behavioralProfileEClass.getESuperTypes().add(this.getAnalysisResult());
		conditionalProfileEClass.getESuperTypes().add(this.getAnalysisResult());
		processStructureTreeEClass.getESuperTypes().add(this.getAnalysisResult());
		traceProfileEClass.getESuperTypes().add(this.getAnalysisResult());
		nodeMatchingEClass.getESuperTypes().add(this.getAnalysisResult());
		fragmentMatchingEClass.getESuperTypes().add(this.getAnalysisResult());

		// Initialize classes, features, and operations; add parameters
		initEClass(analysisEClass, Analysis.class, "Analysis", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAnalysis_Id(), ecorePackage.getEString(), "id", null, 0, 1, Analysis.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAnalysis_Definitions(), theBpmn2Package.getDefinitions(), null, "definitions", null, 1, 1, Analysis.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAnalysis_Results(), this.getAnalysisResultEntry(), null, "results", null, 0, -1, Analysis.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(analysisResultEntryEClass, Map.Entry.class, "AnalysisResultEntry", !IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAnalysisResultEntry_Key(), theXMLTypePackage.getID(), "key", null, 1, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAnalysisResultEntry_Value(), this.getAnalysisResult(), null, "value", null, 1, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(analysisResultEClass, AnalysisResult.class, "AnalysisResult", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(behavioralProfileEClass, BehavioralProfile.class, "BehavioralProfile", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getBehavioralProfile_Traces(), this.getTrace(), null, "traces", null, 0, -1, BehavioralProfile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getBehavioralProfile_Relations(), this.getBehavioralRelation(), null, "relations", null, 0, -1, BehavioralProfile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(behavioralRelationEClass, BehavioralRelation.class, "BehavioralRelation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getBehavioralRelation_Left(), theBpmn2Package.getFlowNode(), null, "left", null, 1, 1, BehavioralRelation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getBehavioralRelation_Right(), theBpmn2Package.getFlowNode(), null, "right", null, 1, 1, BehavioralRelation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBehavioralRelation_Relation(), this.getBehavioralRelationType(), "relation", null, 1, 1, BehavioralRelation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(conditionalProfileEClass, ConditionalProfile.class, "ConditionalProfile", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getConditionalProfile_Relations(), this.getConditionRelationEntry(), null, "relations", null, 0, -1, ConditionalProfile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(conditionRelationEntryEClass, Map.Entry.class, "ConditionRelationEntry", !IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
		initEReference(getConditionRelationEntry_Key(), theBpmn2Package.getFlowNode(), null, "key", null, 1, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getConditionRelationEntry_Value(), theBpmn2Package.getFormalExpression(), null, "value", null, 0, -1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(processStructureTreeEClass, ProcessStructureTree.class, "ProcessStructureTree", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getProcessStructureTree_Fragments(), this.getFragment(), null, "fragments", null, 0, -1, ProcessStructureTree.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(fragmentEClass, Fragment.class, "Fragment", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getFragment_Entry(), theBpmn2Package.getSequenceFlow(), null, "entry", null, 1, 1, Fragment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFragment_Exit(), theBpmn2Package.getSequenceFlow(), null, "exit", null, 1, 1, Fragment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFragment_Parent(), this.getFragment(), null, "parent", null, 0, 1, Fragment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(traceEClass, Trace.class, "Trace", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTrace_Nodes(), theBpmn2Package.getFlowNode(), null, "nodes", null, 0, -1, Trace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTrace_Finished(), ecorePackage.getEBoolean(), "finished", null, 0, 1, Trace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(traceProfileEClass, TraceProfile.class, "TraceProfile", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTraceProfile_Traces(), this.getTrace(), null, "traces", null, 0, -1, TraceProfile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(nodeMatchingEClass, NodeMatching.class, "NodeMatching", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getNodeMatching_Pairs(), this.getNodePair(), null, "pairs", null, 0, -1, NodeMatching.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(nodePairEClass, NodePair.class, "NodePair", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getNodePair_A(), theBpmn2Package.getFlowNode(), null, "a", null, 1, 1, NodePair.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getNodePair_B(), theBpmn2Package.getFlowNode(), null, "b", null, 1, 1, NodePair.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(fragmentMatchingEClass, FragmentMatching.class, "FragmentMatching", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getFragmentMatching_Pairs(), this.getFragmentPair(), null, "pairs", null, 0, -1, FragmentMatching.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(fragmentPairEClass, FragmentPair.class, "FragmentPair", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getFragmentPair_A(), this.getFragment(), null, "a", null, 1, 1, FragmentPair.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFragmentPair_B(), this.getFragment(), null, "b", null, 1, 1, FragmentPair.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(behavioralRelationTypeEEnum, BehavioralRelationType.class, "BehavioralRelationType");
		addEEnumLiteral(behavioralRelationTypeEEnum, BehavioralRelationType.NO_SUCCESSION);
		addEEnumLiteral(behavioralRelationTypeEEnum, BehavioralRelationType.DIRECT_SUCCESSOR);
		addEEnumLiteral(behavioralRelationTypeEEnum, BehavioralRelationType.DIRECT_PREDECESSOR);
		addEEnumLiteral(behavioralRelationTypeEEnum, BehavioralRelationType.PARALLEL);

		// Create resource
		createResource(eNS_URI);
	}

} //BpmnAnalysisPackageImpl
