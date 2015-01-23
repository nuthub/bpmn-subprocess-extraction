/**
 */
package edu.udo.cs.ls14.jf.bpmnanalysis;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
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
	 * The meta object id for the '{@link edu.udo.cs.ls14.jf.bpmnanalysis.impl.ProcessAnalysisImpl <em>Process Analysis</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.impl.ProcessAnalysisImpl
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.impl.BpmnAnalysisPackageImpl#getProcessAnalysis()
	 * @generated
	 */
	int PROCESS_ANALYSIS = 0;

	/**
	 * The feature id for the '<em><b>Definitions</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_ANALYSIS__DEFINITIONS = 0;

	/**
	 * The feature id for the '<em><b>Results</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_ANALYSIS__RESULTS = 1;

	/**
	 * The number of structural features of the '<em>Process Analysis</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_ANALYSIS_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Process Analysis</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_ANALYSIS_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link edu.udo.cs.ls14.jf.bpmnanalysis.impl.ProcessAnalysisResultEntryImpl <em>Process Analysis Result Entry</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.impl.ProcessAnalysisResultEntryImpl
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.impl.BpmnAnalysisPackageImpl#getProcessAnalysisResultEntry()
	 * @generated
	 */
	int PROCESS_ANALYSIS_RESULT_ENTRY = 1;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_ANALYSIS_RESULT_ENTRY__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_ANALYSIS_RESULT_ENTRY__VALUE = 1;

	/**
	 * The number of structural features of the '<em>Process Analysis Result Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_ANALYSIS_RESULT_ENTRY_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Process Analysis Result Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_ANALYSIS_RESULT_ENTRY_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link edu.udo.cs.ls14.jf.bpmnanalysis.impl.ProcessAnalysisResultImpl <em>Process Analysis Result</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.impl.ProcessAnalysisResultImpl
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.impl.BpmnAnalysisPackageImpl#getProcessAnalysisResult()
	 * @generated
	 */
	int PROCESS_ANALYSIS_RESULT = 2;

	/**
	 * The number of structural features of the '<em>Process Analysis Result</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_ANALYSIS_RESULT_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Process Analysis Result</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_ANALYSIS_RESULT_OPERATION_COUNT = 0;

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
	 * The feature id for the '<em><b>Relations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEHAVIORAL_PROFILE__RELATIONS = PROCESS_ANALYSIS_RESULT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Behavioral Profile</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEHAVIORAL_PROFILE_FEATURE_COUNT = PROCESS_ANALYSIS_RESULT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Behavioral Profile</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEHAVIORAL_PROFILE_OPERATION_COUNT = PROCESS_ANALYSIS_RESULT_OPERATION_COUNT + 0;

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
	int CONDITIONAL_PROFILE__RELATIONS = PROCESS_ANALYSIS_RESULT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Conditional Profile</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITIONAL_PROFILE_FEATURE_COUNT = PROCESS_ANALYSIS_RESULT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Conditional Profile</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITIONAL_PROFILE_OPERATION_COUNT = PROCESS_ANALYSIS_RESULT_OPERATION_COUNT + 0;

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
	 * The meta object id for the '{@link edu.udo.cs.ls14.jf.bpmnanalysis.impl.ProcessStructureTreeImpl <em>Process Structure Tree</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.impl.ProcessStructureTreeImpl
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.impl.BpmnAnalysisPackageImpl#getProcessStructureTree()
	 * @generated
	 */
	int PROCESS_STRUCTURE_TREE = 7;

	/**
	 * The feature id for the '<em><b>Fragments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_STRUCTURE_TREE__FRAGMENTS = PROCESS_ANALYSIS_RESULT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Process Structure Tree</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_STRUCTURE_TREE_FEATURE_COUNT = PROCESS_ANALYSIS_RESULT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Process Structure Tree</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_STRUCTURE_TREE_OPERATION_COUNT = PROCESS_ANALYSIS_RESULT_OPERATION_COUNT + 0;

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
	 * The feature id for the '<em><b>Parent</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FRAGMENT__PARENT = 0;

	/**
	 * The feature id for the '<em><b>Entry</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FRAGMENT__ENTRY = 1;

	/**
	 * The feature id for the '<em><b>Exit</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FRAGMENT__EXIT = 2;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FRAGMENT__LABEL = 3;

	/**
	 * The feature id for the '<em><b>Center</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FRAGMENT__CENTER = 4;

	/**
	 * The number of structural features of the '<em>Fragment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FRAGMENT_FEATURE_COUNT = 5;

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
	int TRACE_PROFILE__TRACES = PROCESS_ANALYSIS_RESULT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Trace Profile</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACE_PROFILE_FEATURE_COUNT = PROCESS_ANALYSIS_RESULT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Trace Profile</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACE_PROFILE_OPERATION_COUNT = PROCESS_ANALYSIS_RESULT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalyzer <em>Process Analyzer</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalyzer
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.impl.BpmnAnalysisPackageImpl#getProcessAnalyzer()
	 * @generated
	 */
	int PROCESS_ANALYZER = 11;

	/**
	 * The number of structural features of the '<em>Process Analyzer</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_ANALYZER_FEATURE_COUNT = 0;

	/**
	 * The operation id for the '<em>Analyze</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_ANALYZER___ANALYZE__DEFINITIONS = 0;

	/**
	 * The number of operations of the '<em>Process Analyzer</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_ANALYZER_OPERATION_COUNT = 1;

	/**
	 * The meta object id for the '{@link edu.udo.cs.ls14.jf.bpmnanalysis.BehavioralRelationType <em>Behavioral Relation Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.BehavioralRelationType
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.impl.BpmnAnalysisPackageImpl#getBehavioralRelationType()
	 * @generated
	 */
	int BEHAVIORAL_RELATION_TYPE = 12;


	/**
	 * Returns the meta object for class '{@link edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis <em>Process Analysis</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Process Analysis</em>'.
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis
	 * @generated
	 */
	EClass getProcessAnalysis();

	/**
	 * Returns the meta object for the containment reference '{@link edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis#getDefinitions <em>Definitions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Definitions</em>'.
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis#getDefinitions()
	 * @see #getProcessAnalysis()
	 * @generated
	 */
	EReference getProcessAnalysis_Definitions();

	/**
	 * Returns the meta object for the map '{@link edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis#getResults <em>Results</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Results</em>'.
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis#getResults()
	 * @see #getProcessAnalysis()
	 * @generated
	 */
	EReference getProcessAnalysis_Results();

	/**
	 * Returns the meta object for class '{@link java.util.Map.Entry <em>Process Analysis Result Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Process Analysis Result Entry</em>'.
	 * @see java.util.Map.Entry
	 * @model keyDataType="org.eclipse.emf.ecore.xml.type.ID" keyRequired="true"
	 *        valueType="edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysisResult" valueContainment="true" valueRequired="true"
	 * @generated
	 */
	EClass getProcessAnalysisResultEntry();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see java.util.Map.Entry
	 * @see #getProcessAnalysisResultEntry()
	 * @generated
	 */
	EAttribute getProcessAnalysisResultEntry_Key();

	/**
	 * Returns the meta object for the containment reference '{@link java.util.Map.Entry <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Value</em>'.
	 * @see java.util.Map.Entry
	 * @see #getProcessAnalysisResultEntry()
	 * @generated
	 */
	EReference getProcessAnalysisResultEntry_Value();

	/**
	 * Returns the meta object for class '{@link edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysisResult <em>Process Analysis Result</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Process Analysis Result</em>'.
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysisResult
	 * @generated
	 */
	EClass getProcessAnalysisResult();

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
	 * Returns the meta object for the attribute '{@link edu.udo.cs.ls14.jf.bpmnanalysis.Fragment#getLabel <em>Label</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Label</em>'.
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.Fragment#getLabel()
	 * @see #getFragment()
	 * @generated
	 */
	EAttribute getFragment_Label();

	/**
	 * Returns the meta object for the containment reference '{@link edu.udo.cs.ls14.jf.bpmnanalysis.Fragment#getCenter <em>Center</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Center</em>'.
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.Fragment#getCenter()
	 * @see #getFragment()
	 * @generated
	 */
	EReference getFragment_Center();

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
	 * Returns the meta object for class '{@link edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalyzer <em>Process Analyzer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Process Analyzer</em>'.
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalyzer
	 * @generated
	 */
	EClass getProcessAnalyzer();

	/**
	 * Returns the meta object for the '{@link edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalyzer#analyze(org.eclipse.bpmn2.Definitions) <em>Analyze</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Analyze</em>' operation.
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalyzer#analyze(org.eclipse.bpmn2.Definitions)
	 * @generated
	 */
	EOperation getProcessAnalyzer__Analyze__Definitions();

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
		 * The meta object literal for the '{@link edu.udo.cs.ls14.jf.bpmnanalysis.impl.ProcessAnalysisImpl <em>Process Analysis</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.udo.cs.ls14.jf.bpmnanalysis.impl.ProcessAnalysisImpl
		 * @see edu.udo.cs.ls14.jf.bpmnanalysis.impl.BpmnAnalysisPackageImpl#getProcessAnalysis()
		 * @generated
		 */
		EClass PROCESS_ANALYSIS = eINSTANCE.getProcessAnalysis();

		/**
		 * The meta object literal for the '<em><b>Definitions</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROCESS_ANALYSIS__DEFINITIONS = eINSTANCE.getProcessAnalysis_Definitions();

		/**
		 * The meta object literal for the '<em><b>Results</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROCESS_ANALYSIS__RESULTS = eINSTANCE.getProcessAnalysis_Results();

		/**
		 * The meta object literal for the '{@link edu.udo.cs.ls14.jf.bpmnanalysis.impl.ProcessAnalysisResultEntryImpl <em>Process Analysis Result Entry</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.udo.cs.ls14.jf.bpmnanalysis.impl.ProcessAnalysisResultEntryImpl
		 * @see edu.udo.cs.ls14.jf.bpmnanalysis.impl.BpmnAnalysisPackageImpl#getProcessAnalysisResultEntry()
		 * @generated
		 */
		EClass PROCESS_ANALYSIS_RESULT_ENTRY = eINSTANCE.getProcessAnalysisResultEntry();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROCESS_ANALYSIS_RESULT_ENTRY__KEY = eINSTANCE.getProcessAnalysisResultEntry_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROCESS_ANALYSIS_RESULT_ENTRY__VALUE = eINSTANCE.getProcessAnalysisResultEntry_Value();

		/**
		 * The meta object literal for the '{@link edu.udo.cs.ls14.jf.bpmnanalysis.impl.ProcessAnalysisResultImpl <em>Process Analysis Result</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.udo.cs.ls14.jf.bpmnanalysis.impl.ProcessAnalysisResultImpl
		 * @see edu.udo.cs.ls14.jf.bpmnanalysis.impl.BpmnAnalysisPackageImpl#getProcessAnalysisResult()
		 * @generated
		 */
		EClass PROCESS_ANALYSIS_RESULT = eINSTANCE.getProcessAnalysisResult();

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
		 * The meta object literal for the '<em><b>Parent</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FRAGMENT__PARENT = eINSTANCE.getFragment_Parent();

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
		 * The meta object literal for the '<em><b>Label</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FRAGMENT__LABEL = eINSTANCE.getFragment_Label();

		/**
		 * The meta object literal for the '<em><b>Center</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FRAGMENT__CENTER = eINSTANCE.getFragment_Center();

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
		 * The meta object literal for the '{@link edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalyzer <em>Process Analyzer</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalyzer
		 * @see edu.udo.cs.ls14.jf.bpmnanalysis.impl.BpmnAnalysisPackageImpl#getProcessAnalyzer()
		 * @generated
		 */
		EClass PROCESS_ANALYZER = eINSTANCE.getProcessAnalyzer();

		/**
		 * The meta object literal for the '<em><b>Analyze</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation PROCESS_ANALYZER___ANALYZE__DEFINITIONS = eINSTANCE.getProcessAnalyzer__Analyze__Definitions();

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
