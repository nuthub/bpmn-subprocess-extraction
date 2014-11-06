/**
 */
package edu.udo.cs.ls14.jf.bpmnanalysis;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
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
 * @see edu.udo.cs.ls14.jf.bpmnanalysis.BpmnAnalysisFactory
 * @model kind="package"
 * @generated
 */
public interface BpmnAnalysisPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "bpmnanalysis";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://jf.ls14.cs.udo.edu/bpmnanalysis/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "bpmnanalysis";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	BpmnAnalysisPackage eINSTANCE = edu.udo.cs.ls14.jf.bpmnanalysis.impl.BpmnAnalysisPackageImpl.init();

	/**
	 * The meta object id for the '{@link edu.udo.cs.ls14.jf.bpmnanalysis.impl.AnalysisImpl <em>Analysis</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.impl.AnalysisImpl
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.impl.BpmnAnalysisPackageImpl#getAnalysis()
	 * @generated
	 */
	int ANALYSIS = 0;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANALYSIS__ID = 0;

	/**
	 * The feature id for the '<em><b>Definitions</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANALYSIS__DEFINITIONS = 1;

	/**
	 * The feature id for the '<em><b>Results</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANALYSIS__RESULTS = 2;

	/**
	 * The number of structural features of the '<em>Analysis</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANALYSIS_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Analysis</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANALYSIS_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link edu.udo.cs.ls14.jf.bpmnanalysis.impl.AnalysisResultEntryImpl <em>Analysis Result Entry</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.impl.AnalysisResultEntryImpl
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.impl.BpmnAnalysisPackageImpl#getAnalysisResultEntry()
	 * @generated
	 */
	int ANALYSIS_RESULT_ENTRY = 1;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANALYSIS_RESULT_ENTRY__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANALYSIS_RESULT_ENTRY__VALUE = 1;

	/**
	 * The number of structural features of the '<em>Analysis Result Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANALYSIS_RESULT_ENTRY_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Analysis Result Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANALYSIS_RESULT_ENTRY_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link edu.udo.cs.ls14.jf.bpmnanalysis.impl.AnalysisResultImpl <em>Analysis Result</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.impl.AnalysisResultImpl
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.impl.BpmnAnalysisPackageImpl#getAnalysisResult()
	 * @generated
	 */
	int ANALYSIS_RESULT = 2;

	/**
	 * The number of structural features of the '<em>Analysis Result</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANALYSIS_RESULT_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Analysis Result</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANALYSIS_RESULT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link edu.udo.cs.ls14.jf.bpmnanalysis.impl.ProcessStructureTreeImpl <em>Process Structure Tree</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.impl.ProcessStructureTreeImpl
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.impl.BpmnAnalysisPackageImpl#getProcessStructureTree()
	 * @generated
	 */
	int PROCESS_STRUCTURE_TREE = 7;

	/**
	 * The meta object id for the '{@link edu.udo.cs.ls14.jf.bpmnanalysis.impl.FragmentImpl <em>Fragment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.impl.FragmentImpl
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.impl.BpmnAnalysisPackageImpl#getFragment()
	 * @generated
	 */
	int FRAGMENT = 8;

	/**
	 * The meta object id for the '{@link edu.udo.cs.ls14.jf.bpmnanalysis.impl.BehavioralProfileImpl <em>Behavioral Profile</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.impl.BehavioralProfileImpl
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.impl.BpmnAnalysisPackageImpl#getBehavioralProfile()
	 * @generated
	 */
	int BEHAVIORAL_PROFILE = 3;

	/**
	 * The feature id for the '<em><b>Traces</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEHAVIORAL_PROFILE__TRACES = ANALYSIS_RESULT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Relations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEHAVIORAL_PROFILE__RELATIONS = ANALYSIS_RESULT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Behavioral Profile</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEHAVIORAL_PROFILE_FEATURE_COUNT = ANALYSIS_RESULT_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Behavioral Profile</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEHAVIORAL_PROFILE_OPERATION_COUNT = ANALYSIS_RESULT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link edu.udo.cs.ls14.jf.bpmnanalysis.impl.BehavioralRelationImpl <em>Behavioral Relation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.impl.BehavioralRelationImpl
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.impl.BpmnAnalysisPackageImpl#getBehavioralRelation()
	 * @generated
	 */
	int BEHAVIORAL_RELATION = 4;

	/**
	 * The feature id for the '<em><b>Left</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEHAVIORAL_RELATION__LEFT = 0;

	/**
	 * The feature id for the '<em><b>Right</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEHAVIORAL_RELATION__RIGHT = 1;

	/**
	 * The feature id for the '<em><b>Relation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEHAVIORAL_RELATION__RELATION = 2;

	/**
	 * The number of structural features of the '<em>Behavioral Relation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEHAVIORAL_RELATION_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Behavioral Relation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEHAVIORAL_RELATION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link edu.udo.cs.ls14.jf.bpmnanalysis.impl.ConditionRelationEntryImpl <em>Condition Relation Entry</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.impl.ConditionRelationEntryImpl
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.impl.BpmnAnalysisPackageImpl#getConditionRelationEntry()
	 * @generated
	 */
	int CONDITION_RELATION_ENTRY = 6;

	/**
	 * The meta object id for the '{@link edu.udo.cs.ls14.jf.bpmnanalysis.impl.ConditionalProfileImpl <em>Conditional Profile</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.impl.ConditionalProfileImpl
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.impl.BpmnAnalysisPackageImpl#getConditionalProfile()
	 * @generated
	 */
	int CONDITIONAL_PROFILE = 5;

	/**
	 * The feature id for the '<em><b>Relations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITIONAL_PROFILE__RELATIONS = ANALYSIS_RESULT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Conditional Profile</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITIONAL_PROFILE_FEATURE_COUNT = ANALYSIS_RESULT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Conditional Profile</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITIONAL_PROFILE_OPERATION_COUNT = ANALYSIS_RESULT_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Key</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITION_RELATION_ENTRY__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITION_RELATION_ENTRY__VALUE = 1;

	/**
	 * The number of structural features of the '<em>Condition Relation Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITION_RELATION_ENTRY_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Condition Relation Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITION_RELATION_ENTRY_OPERATION_COUNT = 0;

	/**
	 * The feature id for the '<em><b>Fragments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_STRUCTURE_TREE__FRAGMENTS = ANALYSIS_RESULT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Process Structure Tree</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_STRUCTURE_TREE_FEATURE_COUNT = ANALYSIS_RESULT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Process Structure Tree</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_STRUCTURE_TREE_OPERATION_COUNT = ANALYSIS_RESULT_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Entry</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FRAGMENT__ENTRY = 0;

	/**
	 * The feature id for the '<em><b>Exit</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FRAGMENT__EXIT = 1;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FRAGMENT__PARENT = 2;

	/**
	 * The number of structural features of the '<em>Fragment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FRAGMENT_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Fragment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FRAGMENT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link edu.udo.cs.ls14.jf.bpmnanalysis.impl.TraceImpl <em>Trace</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.impl.TraceImpl
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.impl.BpmnAnalysisPackageImpl#getTrace()
	 * @generated
	 */
	int TRACE = 9;

	/**
	 * The feature id for the '<em><b>Nodes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACE__NODES = 0;

	/**
	 * The feature id for the '<em><b>Finished</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACE__FINISHED = 1;

	/**
	 * The number of structural features of the '<em>Trace</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACE_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Trace</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link edu.udo.cs.ls14.jf.bpmnanalysis.impl.TraceProfileImpl <em>Trace Profile</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.impl.TraceProfileImpl
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.impl.BpmnAnalysisPackageImpl#getTraceProfile()
	 * @generated
	 */
	int TRACE_PROFILE = 10;

	/**
	 * The feature id for the '<em><b>Traces</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACE_PROFILE__TRACES = ANALYSIS_RESULT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Trace Profile</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACE_PROFILE_FEATURE_COUNT = ANALYSIS_RESULT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Trace Profile</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACE_PROFILE_OPERATION_COUNT = ANALYSIS_RESULT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link edu.udo.cs.ls14.jf.bpmnanalysis.impl.NodeMatchingImpl <em>Node Matching</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.impl.NodeMatchingImpl
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.impl.BpmnAnalysisPackageImpl#getNodeMatching()
	 * @generated
	 */
	int NODE_MATCHING = 11;

	/**
	 * The feature id for the '<em><b>Pairs</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_MATCHING__PAIRS = ANALYSIS_RESULT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Node Matching</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_MATCHING_FEATURE_COUNT = ANALYSIS_RESULT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Node Matching</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_MATCHING_OPERATION_COUNT = ANALYSIS_RESULT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link edu.udo.cs.ls14.jf.bpmnanalysis.impl.NodePairImpl <em>Node Pair</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.impl.NodePairImpl
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.impl.BpmnAnalysisPackageImpl#getNodePair()
	 * @generated
	 */
	int NODE_PAIR = 12;

	/**
	 * The feature id for the '<em><b>A</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_PAIR__A = 0;

	/**
	 * The feature id for the '<em><b>B</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_PAIR__B = 1;

	/**
	 * The number of structural features of the '<em>Node Pair</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_PAIR_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Node Pair</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_PAIR_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link edu.udo.cs.ls14.jf.bpmnanalysis.impl.FragmentMatchingImpl <em>Fragment Matching</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.impl.FragmentMatchingImpl
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.impl.BpmnAnalysisPackageImpl#getFragmentMatching()
	 * @generated
	 */
	int FRAGMENT_MATCHING = 13;

	/**
	 * The feature id for the '<em><b>Pairs</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FRAGMENT_MATCHING__PAIRS = ANALYSIS_RESULT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Fragment Matching</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FRAGMENT_MATCHING_FEATURE_COUNT = ANALYSIS_RESULT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Fragment Matching</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FRAGMENT_MATCHING_OPERATION_COUNT = ANALYSIS_RESULT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link edu.udo.cs.ls14.jf.bpmnanalysis.impl.FragmentPairImpl <em>Fragment Pair</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.impl.FragmentPairImpl
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.impl.BpmnAnalysisPackageImpl#getFragmentPair()
	 * @generated
	 */
	int FRAGMENT_PAIR = 14;

	/**
	 * The feature id for the '<em><b>A</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FRAGMENT_PAIR__A = 0;

	/**
	 * The feature id for the '<em><b>B</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FRAGMENT_PAIR__B = 1;

	/**
	 * The number of structural features of the '<em>Fragment Pair</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FRAGMENT_PAIR_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Fragment Pair</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FRAGMENT_PAIR_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link edu.udo.cs.ls14.jf.bpmnanalysis.BehavioralRelationType <em>Behavioral Relation Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.BehavioralRelationType
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.impl.BpmnAnalysisPackageImpl#getBehavioralRelationType()
	 * @generated
	 */
	int BEHAVIORAL_RELATION_TYPE = 15;


	/**
	 * Returns the meta object for class '{@link edu.udo.cs.ls14.jf.bpmnanalysis.Analysis <em>Analysis</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Analysis</em>'.
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.Analysis
	 * @generated
	 */
	EClass getAnalysis();

	/**
	 * Returns the meta object for the attribute '{@link edu.udo.cs.ls14.jf.bpmnanalysis.Analysis#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.Analysis#getId()
	 * @see #getAnalysis()
	 * @generated
	 */
	EAttribute getAnalysis_Id();

	/**
	 * Returns the meta object for the containment reference '{@link edu.udo.cs.ls14.jf.bpmnanalysis.Analysis#getDefinitions <em>Definitions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Definitions</em>'.
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.Analysis#getDefinitions()
	 * @see #getAnalysis()
	 * @generated
	 */
	EReference getAnalysis_Definitions();

	/**
	 * Returns the meta object for the map '{@link edu.udo.cs.ls14.jf.bpmnanalysis.Analysis#getResults <em>Results</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Results</em>'.
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.Analysis#getResults()
	 * @see #getAnalysis()
	 * @generated
	 */
	EReference getAnalysis_Results();

	/**
	 * Returns the meta object for class '{@link java.util.Map.Entry <em>Analysis Result Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Analysis Result Entry</em>'.
	 * @see java.util.Map.Entry
	 * @model keyDataType="org.eclipse.emf.ecore.xml.type.ID" keyRequired="true"
	 *        valueType="edu.udo.cs.ls14.jf.bpmnanalysis.AnalysisResult" valueContainment="true" valueRequired="true"
	 * @generated
	 */
	EClass getAnalysisResultEntry();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see java.util.Map.Entry
	 * @see #getAnalysisResultEntry()
	 * @generated
	 */
	EAttribute getAnalysisResultEntry_Key();

	/**
	 * Returns the meta object for the containment reference '{@link java.util.Map.Entry <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Value</em>'.
	 * @see java.util.Map.Entry
	 * @see #getAnalysisResultEntry()
	 * @generated
	 */
	EReference getAnalysisResultEntry_Value();

	/**
	 * Returns the meta object for class '{@link edu.udo.cs.ls14.jf.bpmnanalysis.AnalysisResult <em>Analysis Result</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Analysis Result</em>'.
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.AnalysisResult
	 * @generated
	 */
	EClass getAnalysisResult();

	/**
	 * Returns the meta object for class '{@link edu.udo.cs.ls14.jf.bpmnanalysis.ProcessStructureTree <em>Process Structure Tree</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Process Structure Tree</em>'.
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.ProcessStructureTree
	 * @generated
	 */
	EClass getProcessStructureTree();

	/**
	 * Returns the meta object for the containment reference list '{@link edu.udo.cs.ls14.jf.bpmnanalysis.ProcessStructureTree#getFragments <em>Fragments</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Fragments</em>'.
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.ProcessStructureTree#getFragments()
	 * @see #getProcessStructureTree()
	 * @generated
	 */
	EReference getProcessStructureTree_Fragments();

	/**
	 * Returns the meta object for class '{@link edu.udo.cs.ls14.jf.bpmnanalysis.Fragment <em>Fragment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Fragment</em>'.
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.Fragment
	 * @generated
	 */
	EClass getFragment();

	/**
	 * Returns the meta object for the reference '{@link edu.udo.cs.ls14.jf.bpmnanalysis.Fragment#getEntry <em>Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Entry</em>'.
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.Fragment#getEntry()
	 * @see #getFragment()
	 * @generated
	 */
	EReference getFragment_Entry();

	/**
	 * Returns the meta object for the reference '{@link edu.udo.cs.ls14.jf.bpmnanalysis.Fragment#getExit <em>Exit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Exit</em>'.
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.Fragment#getExit()
	 * @see #getFragment()
	 * @generated
	 */
	EReference getFragment_Exit();

	/**
	 * Returns the meta object for the reference '{@link edu.udo.cs.ls14.jf.bpmnanalysis.Fragment#getParent <em>Parent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Parent</em>'.
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.Fragment#getParent()
	 * @see #getFragment()
	 * @generated
	 */
	EReference getFragment_Parent();

	/**
	 * Returns the meta object for class '{@link edu.udo.cs.ls14.jf.bpmnanalysis.Trace <em>Trace</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Trace</em>'.
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.Trace
	 * @generated
	 */
	EClass getTrace();

	/**
	 * Returns the meta object for the reference list '{@link edu.udo.cs.ls14.jf.bpmnanalysis.Trace#getNodes <em>Nodes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Nodes</em>'.
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.Trace#getNodes()
	 * @see #getTrace()
	 * @generated
	 */
	EReference getTrace_Nodes();

	/**
	 * Returns the meta object for the attribute '{@link edu.udo.cs.ls14.jf.bpmnanalysis.Trace#isFinished <em>Finished</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Finished</em>'.
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.Trace#isFinished()
	 * @see #getTrace()
	 * @generated
	 */
	EAttribute getTrace_Finished();

	/**
	 * Returns the meta object for class '{@link edu.udo.cs.ls14.jf.bpmnanalysis.TraceProfile <em>Trace Profile</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Trace Profile</em>'.
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.TraceProfile
	 * @generated
	 */
	EClass getTraceProfile();

	/**
	 * Returns the meta object for the containment reference list '{@link edu.udo.cs.ls14.jf.bpmnanalysis.TraceProfile#getTraces <em>Traces</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Traces</em>'.
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.TraceProfile#getTraces()
	 * @see #getTraceProfile()
	 * @generated
	 */
	EReference getTraceProfile_Traces();

	/**
	 * Returns the meta object for class '{@link edu.udo.cs.ls14.jf.bpmnanalysis.NodeMatching <em>Node Matching</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Node Matching</em>'.
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.NodeMatching
	 * @generated
	 */
	EClass getNodeMatching();

	/**
	 * Returns the meta object for the reference list '{@link edu.udo.cs.ls14.jf.bpmnanalysis.NodeMatching#getPairs <em>Pairs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Pairs</em>'.
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.NodeMatching#getPairs()
	 * @see #getNodeMatching()
	 * @generated
	 */
	EReference getNodeMatching_Pairs();

	/**
	 * Returns the meta object for class '{@link edu.udo.cs.ls14.jf.bpmnanalysis.NodePair <em>Node Pair</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Node Pair</em>'.
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.NodePair
	 * @generated
	 */
	EClass getNodePair();

	/**
	 * Returns the meta object for the reference '{@link edu.udo.cs.ls14.jf.bpmnanalysis.NodePair#getA <em>A</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>A</em>'.
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.NodePair#getA()
	 * @see #getNodePair()
	 * @generated
	 */
	EReference getNodePair_A();

	/**
	 * Returns the meta object for the reference '{@link edu.udo.cs.ls14.jf.bpmnanalysis.NodePair#getB <em>B</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>B</em>'.
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.NodePair#getB()
	 * @see #getNodePair()
	 * @generated
	 */
	EReference getNodePair_B();

	/**
	 * Returns the meta object for class '{@link edu.udo.cs.ls14.jf.bpmnanalysis.FragmentMatching <em>Fragment Matching</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Fragment Matching</em>'.
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.FragmentMatching
	 * @generated
	 */
	EClass getFragmentMatching();

	/**
	 * Returns the meta object for the reference list '{@link edu.udo.cs.ls14.jf.bpmnanalysis.FragmentMatching#getPairs <em>Pairs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Pairs</em>'.
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.FragmentMatching#getPairs()
	 * @see #getFragmentMatching()
	 * @generated
	 */
	EReference getFragmentMatching_Pairs();

	/**
	 * Returns the meta object for class '{@link edu.udo.cs.ls14.jf.bpmnanalysis.FragmentPair <em>Fragment Pair</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Fragment Pair</em>'.
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.FragmentPair
	 * @generated
	 */
	EClass getFragmentPair();

	/**
	 * Returns the meta object for the reference '{@link edu.udo.cs.ls14.jf.bpmnanalysis.FragmentPair#getA <em>A</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>A</em>'.
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.FragmentPair#getA()
	 * @see #getFragmentPair()
	 * @generated
	 */
	EReference getFragmentPair_A();

	/**
	 * Returns the meta object for the reference '{@link edu.udo.cs.ls14.jf.bpmnanalysis.FragmentPair#getB <em>B</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>B</em>'.
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.FragmentPair#getB()
	 * @see #getFragmentPair()
	 * @generated
	 */
	EReference getFragmentPair_B();

	/**
	 * Returns the meta object for class '{@link edu.udo.cs.ls14.jf.bpmnanalysis.BehavioralProfile <em>Behavioral Profile</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Behavioral Profile</em>'.
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.BehavioralProfile
	 * @generated
	 */
	EClass getBehavioralProfile();

	/**
	 * Returns the meta object for the containment reference list '{@link edu.udo.cs.ls14.jf.bpmnanalysis.BehavioralProfile#getTraces <em>Traces</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Traces</em>'.
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.BehavioralProfile#getTraces()
	 * @see #getBehavioralProfile()
	 * @generated
	 */
	EReference getBehavioralProfile_Traces();

	/**
	 * Returns the meta object for the containment reference list '{@link edu.udo.cs.ls14.jf.bpmnanalysis.BehavioralProfile#getRelations <em>Relations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Relations</em>'.
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.BehavioralProfile#getRelations()
	 * @see #getBehavioralProfile()
	 * @generated
	 */
	EReference getBehavioralProfile_Relations();

	/**
	 * Returns the meta object for class '{@link edu.udo.cs.ls14.jf.bpmnanalysis.BehavioralRelation <em>Behavioral Relation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Behavioral Relation</em>'.
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.BehavioralRelation
	 * @generated
	 */
	EClass getBehavioralRelation();

	/**
	 * Returns the meta object for the reference '{@link edu.udo.cs.ls14.jf.bpmnanalysis.BehavioralRelation#getLeft <em>Left</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Left</em>'.
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.BehavioralRelation#getLeft()
	 * @see #getBehavioralRelation()
	 * @generated
	 */
	EReference getBehavioralRelation_Left();

	/**
	 * Returns the meta object for the reference '{@link edu.udo.cs.ls14.jf.bpmnanalysis.BehavioralRelation#getRight <em>Right</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Right</em>'.
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.BehavioralRelation#getRight()
	 * @see #getBehavioralRelation()
	 * @generated
	 */
	EReference getBehavioralRelation_Right();

	/**
	 * Returns the meta object for the attribute '{@link edu.udo.cs.ls14.jf.bpmnanalysis.BehavioralRelation#getRelation <em>Relation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Relation</em>'.
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.BehavioralRelation#getRelation()
	 * @see #getBehavioralRelation()
	 * @generated
	 */
	EAttribute getBehavioralRelation_Relation();

	/**
	 * Returns the meta object for class '{@link java.util.Map.Entry <em>Condition Relation Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Condition Relation Entry</em>'.
	 * @see java.util.Map.Entry
	 * @model keyType="org.eclipse.bpmn2.FlowNode" keyRequired="true"
	 *        valueType="org.eclipse.bpmn2.FormalExpression" valueMany="true" valueOrdered="false"
	 * @generated
	 */
	EClass getConditionRelationEntry();

	/**
	 * Returns the meta object for the reference '{@link java.util.Map.Entry <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Key</em>'.
	 * @see java.util.Map.Entry
	 * @see #getConditionRelationEntry()
	 * @generated
	 */
	EReference getConditionRelationEntry_Key();

	/**
	 * Returns the meta object for the reference list '{@link java.util.Map.Entry <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Value</em>'.
	 * @see java.util.Map.Entry
	 * @see #getConditionRelationEntry()
	 * @generated
	 */
	EReference getConditionRelationEntry_Value();

	/**
	 * Returns the meta object for class '{@link edu.udo.cs.ls14.jf.bpmnanalysis.ConditionalProfile <em>Conditional Profile</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Conditional Profile</em>'.
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.ConditionalProfile
	 * @generated
	 */
	EClass getConditionalProfile();

	/**
	 * Returns the meta object for the map '{@link edu.udo.cs.ls14.jf.bpmnanalysis.ConditionalProfile#getRelations <em>Relations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Relations</em>'.
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.ConditionalProfile#getRelations()
	 * @see #getConditionalProfile()
	 * @generated
	 */
	EReference getConditionalProfile_Relations();

	/**
	 * Returns the meta object for enum '{@link edu.udo.cs.ls14.jf.bpmnanalysis.BehavioralRelationType <em>Behavioral Relation Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Behavioral Relation Type</em>'.
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.BehavioralRelationType
	 * @generated
	 */
	EEnum getBehavioralRelationType();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	BpmnAnalysisFactory getBpmnAnalysisFactory();

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
		 * The meta object literal for the '{@link edu.udo.cs.ls14.jf.bpmnanalysis.impl.AnalysisImpl <em>Analysis</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.udo.cs.ls14.jf.bpmnanalysis.impl.AnalysisImpl
		 * @see edu.udo.cs.ls14.jf.bpmnanalysis.impl.BpmnAnalysisPackageImpl#getAnalysis()
		 * @generated
		 */
		EClass ANALYSIS = eINSTANCE.getAnalysis();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ANALYSIS__ID = eINSTANCE.getAnalysis_Id();

		/**
		 * The meta object literal for the '<em><b>Definitions</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ANALYSIS__DEFINITIONS = eINSTANCE.getAnalysis_Definitions();

		/**
		 * The meta object literal for the '<em><b>Results</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ANALYSIS__RESULTS = eINSTANCE.getAnalysis_Results();

		/**
		 * The meta object literal for the '{@link edu.udo.cs.ls14.jf.bpmnanalysis.impl.AnalysisResultEntryImpl <em>Analysis Result Entry</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.udo.cs.ls14.jf.bpmnanalysis.impl.AnalysisResultEntryImpl
		 * @see edu.udo.cs.ls14.jf.bpmnanalysis.impl.BpmnAnalysisPackageImpl#getAnalysisResultEntry()
		 * @generated
		 */
		EClass ANALYSIS_RESULT_ENTRY = eINSTANCE.getAnalysisResultEntry();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ANALYSIS_RESULT_ENTRY__KEY = eINSTANCE.getAnalysisResultEntry_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ANALYSIS_RESULT_ENTRY__VALUE = eINSTANCE.getAnalysisResultEntry_Value();

		/**
		 * The meta object literal for the '{@link edu.udo.cs.ls14.jf.bpmnanalysis.impl.AnalysisResultImpl <em>Analysis Result</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.udo.cs.ls14.jf.bpmnanalysis.impl.AnalysisResultImpl
		 * @see edu.udo.cs.ls14.jf.bpmnanalysis.impl.BpmnAnalysisPackageImpl#getAnalysisResult()
		 * @generated
		 */
		EClass ANALYSIS_RESULT = eINSTANCE.getAnalysisResult();

		/**
		 * The meta object literal for the '{@link edu.udo.cs.ls14.jf.bpmnanalysis.impl.ProcessStructureTreeImpl <em>Process Structure Tree</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.udo.cs.ls14.jf.bpmnanalysis.impl.ProcessStructureTreeImpl
		 * @see edu.udo.cs.ls14.jf.bpmnanalysis.impl.BpmnAnalysisPackageImpl#getProcessStructureTree()
		 * @generated
		 */
		EClass PROCESS_STRUCTURE_TREE = eINSTANCE.getProcessStructureTree();

		/**
		 * The meta object literal for the '<em><b>Fragments</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROCESS_STRUCTURE_TREE__FRAGMENTS = eINSTANCE.getProcessStructureTree_Fragments();

		/**
		 * The meta object literal for the '{@link edu.udo.cs.ls14.jf.bpmnanalysis.impl.FragmentImpl <em>Fragment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.udo.cs.ls14.jf.bpmnanalysis.impl.FragmentImpl
		 * @see edu.udo.cs.ls14.jf.bpmnanalysis.impl.BpmnAnalysisPackageImpl#getFragment()
		 * @generated
		 */
		EClass FRAGMENT = eINSTANCE.getFragment();

		/**
		 * The meta object literal for the '<em><b>Entry</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FRAGMENT__ENTRY = eINSTANCE.getFragment_Entry();

		/**
		 * The meta object literal for the '<em><b>Exit</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FRAGMENT__EXIT = eINSTANCE.getFragment_Exit();

		/**
		 * The meta object literal for the '<em><b>Parent</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FRAGMENT__PARENT = eINSTANCE.getFragment_Parent();

		/**
		 * The meta object literal for the '{@link edu.udo.cs.ls14.jf.bpmnanalysis.impl.TraceImpl <em>Trace</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.udo.cs.ls14.jf.bpmnanalysis.impl.TraceImpl
		 * @see edu.udo.cs.ls14.jf.bpmnanalysis.impl.BpmnAnalysisPackageImpl#getTrace()
		 * @generated
		 */
		EClass TRACE = eINSTANCE.getTrace();

		/**
		 * The meta object literal for the '<em><b>Nodes</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRACE__NODES = eINSTANCE.getTrace_Nodes();

		/**
		 * The meta object literal for the '<em><b>Finished</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRACE__FINISHED = eINSTANCE.getTrace_Finished();

		/**
		 * The meta object literal for the '{@link edu.udo.cs.ls14.jf.bpmnanalysis.impl.TraceProfileImpl <em>Trace Profile</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.udo.cs.ls14.jf.bpmnanalysis.impl.TraceProfileImpl
		 * @see edu.udo.cs.ls14.jf.bpmnanalysis.impl.BpmnAnalysisPackageImpl#getTraceProfile()
		 * @generated
		 */
		EClass TRACE_PROFILE = eINSTANCE.getTraceProfile();

		/**
		 * The meta object literal for the '<em><b>Traces</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRACE_PROFILE__TRACES = eINSTANCE.getTraceProfile_Traces();

		/**
		 * The meta object literal for the '{@link edu.udo.cs.ls14.jf.bpmnanalysis.impl.NodeMatchingImpl <em>Node Matching</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.udo.cs.ls14.jf.bpmnanalysis.impl.NodeMatchingImpl
		 * @see edu.udo.cs.ls14.jf.bpmnanalysis.impl.BpmnAnalysisPackageImpl#getNodeMatching()
		 * @generated
		 */
		EClass NODE_MATCHING = eINSTANCE.getNodeMatching();

		/**
		 * The meta object literal for the '<em><b>Pairs</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NODE_MATCHING__PAIRS = eINSTANCE.getNodeMatching_Pairs();

		/**
		 * The meta object literal for the '{@link edu.udo.cs.ls14.jf.bpmnanalysis.impl.NodePairImpl <em>Node Pair</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.udo.cs.ls14.jf.bpmnanalysis.impl.NodePairImpl
		 * @see edu.udo.cs.ls14.jf.bpmnanalysis.impl.BpmnAnalysisPackageImpl#getNodePair()
		 * @generated
		 */
		EClass NODE_PAIR = eINSTANCE.getNodePair();

		/**
		 * The meta object literal for the '<em><b>A</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NODE_PAIR__A = eINSTANCE.getNodePair_A();

		/**
		 * The meta object literal for the '<em><b>B</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NODE_PAIR__B = eINSTANCE.getNodePair_B();

		/**
		 * The meta object literal for the '{@link edu.udo.cs.ls14.jf.bpmnanalysis.impl.FragmentMatchingImpl <em>Fragment Matching</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.udo.cs.ls14.jf.bpmnanalysis.impl.FragmentMatchingImpl
		 * @see edu.udo.cs.ls14.jf.bpmnanalysis.impl.BpmnAnalysisPackageImpl#getFragmentMatching()
		 * @generated
		 */
		EClass FRAGMENT_MATCHING = eINSTANCE.getFragmentMatching();

		/**
		 * The meta object literal for the '<em><b>Pairs</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FRAGMENT_MATCHING__PAIRS = eINSTANCE.getFragmentMatching_Pairs();

		/**
		 * The meta object literal for the '{@link edu.udo.cs.ls14.jf.bpmnanalysis.impl.FragmentPairImpl <em>Fragment Pair</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.udo.cs.ls14.jf.bpmnanalysis.impl.FragmentPairImpl
		 * @see edu.udo.cs.ls14.jf.bpmnanalysis.impl.BpmnAnalysisPackageImpl#getFragmentPair()
		 * @generated
		 */
		EClass FRAGMENT_PAIR = eINSTANCE.getFragmentPair();

		/**
		 * The meta object literal for the '<em><b>A</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FRAGMENT_PAIR__A = eINSTANCE.getFragmentPair_A();

		/**
		 * The meta object literal for the '<em><b>B</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FRAGMENT_PAIR__B = eINSTANCE.getFragmentPair_B();

		/**
		 * The meta object literal for the '{@link edu.udo.cs.ls14.jf.bpmnanalysis.impl.BehavioralProfileImpl <em>Behavioral Profile</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.udo.cs.ls14.jf.bpmnanalysis.impl.BehavioralProfileImpl
		 * @see edu.udo.cs.ls14.jf.bpmnanalysis.impl.BpmnAnalysisPackageImpl#getBehavioralProfile()
		 * @generated
		 */
		EClass BEHAVIORAL_PROFILE = eINSTANCE.getBehavioralProfile();

		/**
		 * The meta object literal for the '<em><b>Traces</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BEHAVIORAL_PROFILE__TRACES = eINSTANCE.getBehavioralProfile_Traces();

		/**
		 * The meta object literal for the '<em><b>Relations</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BEHAVIORAL_PROFILE__RELATIONS = eINSTANCE.getBehavioralProfile_Relations();

		/**
		 * The meta object literal for the '{@link edu.udo.cs.ls14.jf.bpmnanalysis.impl.BehavioralRelationImpl <em>Behavioral Relation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.udo.cs.ls14.jf.bpmnanalysis.impl.BehavioralRelationImpl
		 * @see edu.udo.cs.ls14.jf.bpmnanalysis.impl.BpmnAnalysisPackageImpl#getBehavioralRelation()
		 * @generated
		 */
		EClass BEHAVIORAL_RELATION = eINSTANCE.getBehavioralRelation();

		/**
		 * The meta object literal for the '<em><b>Left</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BEHAVIORAL_RELATION__LEFT = eINSTANCE.getBehavioralRelation_Left();

		/**
		 * The meta object literal for the '<em><b>Right</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BEHAVIORAL_RELATION__RIGHT = eINSTANCE.getBehavioralRelation_Right();

		/**
		 * The meta object literal for the '<em><b>Relation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BEHAVIORAL_RELATION__RELATION = eINSTANCE.getBehavioralRelation_Relation();

		/**
		 * The meta object literal for the '{@link edu.udo.cs.ls14.jf.bpmnanalysis.impl.ConditionRelationEntryImpl <em>Condition Relation Entry</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.udo.cs.ls14.jf.bpmnanalysis.impl.ConditionRelationEntryImpl
		 * @see edu.udo.cs.ls14.jf.bpmnanalysis.impl.BpmnAnalysisPackageImpl#getConditionRelationEntry()
		 * @generated
		 */
		EClass CONDITION_RELATION_ENTRY = eINSTANCE.getConditionRelationEntry();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONDITION_RELATION_ENTRY__KEY = eINSTANCE.getConditionRelationEntry_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONDITION_RELATION_ENTRY__VALUE = eINSTANCE.getConditionRelationEntry_Value();

		/**
		 * The meta object literal for the '{@link edu.udo.cs.ls14.jf.bpmnanalysis.impl.ConditionalProfileImpl <em>Conditional Profile</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.udo.cs.ls14.jf.bpmnanalysis.impl.ConditionalProfileImpl
		 * @see edu.udo.cs.ls14.jf.bpmnanalysis.impl.BpmnAnalysisPackageImpl#getConditionalProfile()
		 * @generated
		 */
		EClass CONDITIONAL_PROFILE = eINSTANCE.getConditionalProfile();

		/**
		 * The meta object literal for the '<em><b>Relations</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONDITIONAL_PROFILE__RELATIONS = eINSTANCE.getConditionalProfile_Relations();

		/**
		 * The meta object literal for the '{@link edu.udo.cs.ls14.jf.bpmnanalysis.BehavioralRelationType <em>Behavioral Relation Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.udo.cs.ls14.jf.bpmnanalysis.BehavioralRelationType
		 * @see edu.udo.cs.ls14.jf.bpmnanalysis.impl.BpmnAnalysisPackageImpl#getBehavioralRelationType()
		 * @generated
		 */
		EEnum BEHAVIORAL_RELATION_TYPE = eINSTANCE.getBehavioralRelationType();

	}

} //BpmnAnalysisPackage
