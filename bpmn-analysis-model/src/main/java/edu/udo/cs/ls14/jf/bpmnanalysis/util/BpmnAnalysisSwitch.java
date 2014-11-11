/**
 */
package edu.udo.cs.ls14.jf.bpmnanalysis.util;

import edu.udo.cs.ls14.jf.bpmnanalysis.*;

import java.util.Map;

import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.FormalExpression;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see edu.udo.cs.ls14.jf.bpmnanalysis.BpmnAnalysisPackage
 * @generated
 */
public class BpmnAnalysisSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static BpmnAnalysisPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BpmnAnalysisSwitch() {
		if (modelPackage == null) {
			modelPackage = BpmnAnalysisPackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @parameter ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case BpmnAnalysisPackage.PROCESS_ANALYSIS: {
				ProcessAnalysis processAnalysis = (ProcessAnalysis)theEObject;
				T result = caseProcessAnalysis(processAnalysis);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BpmnAnalysisPackage.PROCESS_ANALYSIS_RESULT_ENTRY: {
				@SuppressWarnings("unchecked") Map.Entry<String, ProcessAnalysisResult> processAnalysisResultEntry = (Map.Entry<String, ProcessAnalysisResult>)theEObject;
				T result = caseProcessAnalysisResultEntry(processAnalysisResultEntry);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BpmnAnalysisPackage.PROCESS_ANALYSIS_RESULT: {
				ProcessAnalysisResult processAnalysisResult = (ProcessAnalysisResult)theEObject;
				T result = caseProcessAnalysisResult(processAnalysisResult);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BpmnAnalysisPackage.BEHAVIORAL_PROFILE: {
				BehavioralProfile behavioralProfile = (BehavioralProfile)theEObject;
				T result = caseBehavioralProfile(behavioralProfile);
				if (result == null) result = caseProcessAnalysisResult(behavioralProfile);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BpmnAnalysisPackage.BEHAVIORAL_RELATION: {
				BehavioralRelation behavioralRelation = (BehavioralRelation)theEObject;
				T result = caseBehavioralRelation(behavioralRelation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BpmnAnalysisPackage.CONDITIONAL_PROFILE: {
				ConditionalProfile conditionalProfile = (ConditionalProfile)theEObject;
				T result = caseConditionalProfile(conditionalProfile);
				if (result == null) result = caseProcessAnalysisResult(conditionalProfile);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BpmnAnalysisPackage.CONDITION_RELATION_ENTRY: {
				@SuppressWarnings("unchecked") Map.Entry<FlowNode, EList<FormalExpression>> conditionRelationEntry = (Map.Entry<FlowNode, EList<FormalExpression>>)theEObject;
				T result = caseConditionRelationEntry(conditionRelationEntry);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BpmnAnalysisPackage.PROCESS_STRUCTURE_TREE: {
				ProcessStructureTree processStructureTree = (ProcessStructureTree)theEObject;
				T result = caseProcessStructureTree(processStructureTree);
				if (result == null) result = caseProcessAnalysisResult(processStructureTree);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BpmnAnalysisPackage.FRAGMENT: {
				Fragment fragment = (Fragment)theEObject;
				T result = caseFragment(fragment);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BpmnAnalysisPackage.TRACE: {
				Trace trace = (Trace)theEObject;
				T result = caseTrace(trace);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BpmnAnalysisPackage.TRACE_PROFILE: {
				TraceProfile traceProfile = (TraceProfile)theEObject;
				T result = caseTraceProfile(traceProfile);
				if (result == null) result = caseProcessAnalysisResult(traceProfile);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Process Analysis</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Process Analysis</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseProcessAnalysis(ProcessAnalysis object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Process Analysis Result Entry</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Process Analysis Result Entry</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseProcessAnalysisResultEntry(Map.Entry<String, ProcessAnalysisResult> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Process Analysis Result</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Process Analysis Result</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseProcessAnalysisResult(ProcessAnalysisResult object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Behavioral Profile</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Behavioral Profile</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBehavioralProfile(BehavioralProfile object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Behavioral Relation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Behavioral Relation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBehavioralRelation(BehavioralRelation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Conditional Profile</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Conditional Profile</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseConditionalProfile(ConditionalProfile object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Condition Relation Entry</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Condition Relation Entry</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseConditionRelationEntry(Map.Entry<FlowNode, EList<FormalExpression>> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Process Structure Tree</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Process Structure Tree</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseProcessStructureTree(ProcessStructureTree object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Fragment</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Fragment</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFragment(Fragment object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Trace</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Trace</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTrace(Trace object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Trace Profile</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Trace Profile</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTraceProfile(TraceProfile object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	@Override
	public T defaultCase(EObject object) {
		return null;
	}

} //BpmnAnalysisSwitch
