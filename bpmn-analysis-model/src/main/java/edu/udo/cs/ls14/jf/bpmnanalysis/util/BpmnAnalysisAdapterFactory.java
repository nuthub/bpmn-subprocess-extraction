/**
 */
package edu.udo.cs.ls14.jf.bpmnanalysis.util;

import edu.udo.cs.ls14.jf.bpmnanalysis.*;

import java.util.Map;

import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.FormalExpression;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see edu.udo.cs.ls14.jf.bpmnanalysis.BpmnAnalysisPackage
 * @generated
 */
public class BpmnAnalysisAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static BpmnAnalysisPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BpmnAnalysisAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = BpmnAnalysisPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BpmnAnalysisSwitch<Adapter> modelSwitch =
		new BpmnAnalysisSwitch<Adapter>() {
			@Override
			public Adapter caseAnalysis(Analysis object) {
				return createAnalysisAdapter();
			}
			@Override
			public Adapter caseAnalysisResultEntry(Map.Entry<String, AnalysisResult> object) {
				return createAnalysisResultEntryAdapter();
			}
			@Override
			public Adapter caseAnalysisResult(AnalysisResult object) {
				return createAnalysisResultAdapter();
			}
			@Override
			public Adapter caseBehavioralProfile(BehavioralProfile object) {
				return createBehavioralProfileAdapter();
			}
			@Override
			public Adapter caseBehavioralRelation(BehavioralRelation object) {
				return createBehavioralRelationAdapter();
			}
			@Override
			public Adapter caseConditionalProfile(ConditionalProfile object) {
				return createConditionalProfileAdapter();
			}
			@Override
			public Adapter caseConditionRelationEntry(Map.Entry<FlowNode, EList<FormalExpression>> object) {
				return createConditionRelationEntryAdapter();
			}
			@Override
			public Adapter caseProcessStructureTree(ProcessStructureTree object) {
				return createProcessStructureTreeAdapter();
			}
			@Override
			public Adapter caseFragment(Fragment object) {
				return createFragmentAdapter();
			}
			@Override
			public Adapter caseTrace(Trace object) {
				return createTraceAdapter();
			}
			@Override
			public Adapter caseTraceProfile(TraceProfile object) {
				return createTraceProfileAdapter();
			}
			@Override
			public Adapter caseNodeMatching(NodeMatching object) {
				return createNodeMatchingAdapter();
			}
			@Override
			public Adapter caseNodePair(NodePair object) {
				return createNodePairAdapter();
			}
			@Override
			public Adapter caseFragmentMatching(FragmentMatching object) {
				return createFragmentMatchingAdapter();
			}
			@Override
			public Adapter caseFragmentPair(FragmentPair object) {
				return createFragmentPairAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link edu.udo.cs.ls14.jf.bpmnanalysis.Analysis <em>Analysis</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.Analysis
	 * @generated
	 */
	public Adapter createAnalysisAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link java.util.Map.Entry <em>Analysis Result Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see java.util.Map.Entry
	 * @generated
	 */
	public Adapter createAnalysisResultEntryAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link edu.udo.cs.ls14.jf.bpmnanalysis.AnalysisResult <em>Analysis Result</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.AnalysisResult
	 * @generated
	 */
	public Adapter createAnalysisResultAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link edu.udo.cs.ls14.jf.bpmnanalysis.ProcessStructureTree <em>Process Structure Tree</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.ProcessStructureTree
	 * @generated
	 */
	public Adapter createProcessStructureTreeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link edu.udo.cs.ls14.jf.bpmnanalysis.Fragment <em>Fragment</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.Fragment
	 * @generated
	 */
	public Adapter createFragmentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link edu.udo.cs.ls14.jf.bpmnanalysis.Trace <em>Trace</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.Trace
	 * @generated
	 */
	public Adapter createTraceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link edu.udo.cs.ls14.jf.bpmnanalysis.TraceProfile <em>Trace Profile</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.TraceProfile
	 * @generated
	 */
	public Adapter createTraceProfileAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link edu.udo.cs.ls14.jf.bpmnanalysis.NodeMatching <em>Node Matching</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.NodeMatching
	 * @generated
	 */
	public Adapter createNodeMatchingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link edu.udo.cs.ls14.jf.bpmnanalysis.NodePair <em>Node Pair</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.NodePair
	 * @generated
	 */
	public Adapter createNodePairAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link edu.udo.cs.ls14.jf.bpmnanalysis.FragmentMatching <em>Fragment Matching</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.FragmentMatching
	 * @generated
	 */
	public Adapter createFragmentMatchingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link edu.udo.cs.ls14.jf.bpmnanalysis.FragmentPair <em>Fragment Pair</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.FragmentPair
	 * @generated
	 */
	public Adapter createFragmentPairAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link edu.udo.cs.ls14.jf.bpmnanalysis.BehavioralProfile <em>Behavioral Profile</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.BehavioralProfile
	 * @generated
	 */
	public Adapter createBehavioralProfileAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link edu.udo.cs.ls14.jf.bpmnanalysis.BehavioralRelation <em>Behavioral Relation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.BehavioralRelation
	 * @generated
	 */
	public Adapter createBehavioralRelationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link java.util.Map.Entry <em>Condition Relation Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see java.util.Map.Entry
	 * @generated
	 */
	public Adapter createConditionRelationEntryAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link edu.udo.cs.ls14.jf.bpmnanalysis.ConditionalProfile <em>Conditional Profile</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see edu.udo.cs.ls14.jf.bpmnanalysis.ConditionalProfile
	 * @generated
	 */
	public Adapter createConditionalProfileAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //BpmnAnalysisAdapterFactory
