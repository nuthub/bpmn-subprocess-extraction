/**
 */
package edu.udo.cs.ls14.jf.bpmnanalysis.impl;

import edu.udo.cs.ls14.jf.bpmnanalysis.*;

import java.util.Map;

import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.FormalExpression;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class BpmnAnalysisFactoryImpl extends EFactoryImpl implements BpmnAnalysisFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static BpmnAnalysisFactory init() {
		try {
			BpmnAnalysisFactory theBpmnAnalysisFactory = (BpmnAnalysisFactory)EPackage.Registry.INSTANCE.getEFactory(BpmnAnalysisPackage.eNS_URI);
			if (theBpmnAnalysisFactory != null) {
				return theBpmnAnalysisFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new BpmnAnalysisFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BpmnAnalysisFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case BpmnAnalysisPackage.ANALYSIS: return createAnalysis();
			case BpmnAnalysisPackage.ANALYSIS_RESULT_ENTRY: return (EObject)createAnalysisResultEntry();
			case BpmnAnalysisPackage.BEHAVIORAL_PROFILE: return createBehavioralProfile();
			case BpmnAnalysisPackage.BEHAVIORAL_RELATION: return createBehavioralRelation();
			case BpmnAnalysisPackage.CONDITIONAL_PROFILE: return createConditionalProfile();
			case BpmnAnalysisPackage.CONDITION_RELATION_ENTRY: return (EObject)createConditionRelationEntry();
			case BpmnAnalysisPackage.PROCESS_STRUCTURE_TREE: return createProcessStructureTree();
			case BpmnAnalysisPackage.FRAGMENT: return createFragment();
			case BpmnAnalysisPackage.TRACE: return createTrace();
			case BpmnAnalysisPackage.TRACE_PROFILE: return createTraceProfile();
			case BpmnAnalysisPackage.NODE_MATCHING: return createNodeMatching();
			case BpmnAnalysisPackage.NODE_PAIR: return createNodePair();
			case BpmnAnalysisPackage.FRAGMENT_MATCHING: return createFragmentMatching();
			case BpmnAnalysisPackage.FRAGMENT_PAIR: return createFragmentPair();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case BpmnAnalysisPackage.BEHAVIORAL_RELATION_TYPE:
				return createBehavioralRelationTypeFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case BpmnAnalysisPackage.BEHAVIORAL_RELATION_TYPE:
				return convertBehavioralRelationTypeToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Analysis createAnalysis() {
		AnalysisImpl analysis = new AnalysisImpl();
		return analysis;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Map.Entry<String, AnalysisResult> createAnalysisResultEntry() {
		AnalysisResultEntryImpl analysisResultEntry = new AnalysisResultEntryImpl();
		return analysisResultEntry;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProcessStructureTree createProcessStructureTree() {
		ProcessStructureTreeImpl processStructureTree = new ProcessStructureTreeImpl();
		return processStructureTree;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Fragment createFragment() {
		FragmentImpl fragment = new FragmentImpl();
		return fragment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Trace createTrace() {
		TraceImpl trace = new TraceImpl();
		return trace;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TraceProfile createTraceProfile() {
		TraceProfileImpl traceProfile = new TraceProfileImpl();
		return traceProfile;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NodeMatching createNodeMatching() {
		NodeMatchingImpl nodeMatching = new NodeMatchingImpl();
		return nodeMatching;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NodePair createNodePair() {
		NodePairImpl nodePair = new NodePairImpl();
		return nodePair;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FragmentMatching createFragmentMatching() {
		FragmentMatchingImpl fragmentMatching = new FragmentMatchingImpl();
		return fragmentMatching;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FragmentPair createFragmentPair() {
		FragmentPairImpl fragmentPair = new FragmentPairImpl();
		return fragmentPair;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BehavioralProfile createBehavioralProfile() {
		BehavioralProfileImpl behavioralProfile = new BehavioralProfileImpl();
		return behavioralProfile;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BehavioralRelation createBehavioralRelation() {
		BehavioralRelationImpl behavioralRelation = new BehavioralRelationImpl();
		return behavioralRelation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Map.Entry<FlowNode, EList<FormalExpression>> createConditionRelationEntry() {
		ConditionRelationEntryImpl conditionRelationEntry = new ConditionRelationEntryImpl();
		return conditionRelationEntry;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConditionalProfile createConditionalProfile() {
		ConditionalProfileImpl conditionalProfile = new ConditionalProfileImpl();
		return conditionalProfile;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BehavioralRelationType createBehavioralRelationTypeFromString(EDataType eDataType, String initialValue) {
		BehavioralRelationType result = BehavioralRelationType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertBehavioralRelationTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BpmnAnalysisPackage getBpmnAnalysisPackage() {
		return (BpmnAnalysisPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static BpmnAnalysisPackage getPackage() {
		return BpmnAnalysisPackage.eINSTANCE;
	}

} //BpmnAnalysisFactoryImpl
