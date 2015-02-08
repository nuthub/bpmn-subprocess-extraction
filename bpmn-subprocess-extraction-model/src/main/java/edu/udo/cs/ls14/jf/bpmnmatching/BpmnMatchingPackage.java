/**
 */
package edu.udo.cs.ls14.jf.bpmnmatching;

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
 * @see edu.udo.cs.ls14.jf.bpmnmatching.BpmnMatchingFactory
 * @model kind="package"
 * @generated
 */
public interface BpmnMatchingPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "bpmnmatching";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://jf.ls14.cs.udo.edu/bpmnmatching/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "bpmnmatching";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	BpmnMatchingPackage eINSTANCE = edu.udo.cs.ls14.jf.bpmnmatching.impl.BpmnMatchingPackageImpl.init();

	/**
	 * The meta object id for the '{@link edu.udo.cs.ls14.jf.bpmnmatching.impl.ProcessMatchingImpl <em>Process Matching</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.udo.cs.ls14.jf.bpmnmatching.impl.ProcessMatchingImpl
	 * @see edu.udo.cs.ls14.jf.bpmnmatching.impl.BpmnMatchingPackageImpl#getProcessMatching()
	 * @generated
	 */
	int PROCESS_MATCHING = 0;

	/**
	 * The feature id for the '<em><b>Analysis1</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_MATCHING__ANALYSIS1 = 0;

	/**
	 * The feature id for the '<em><b>Analysis2</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_MATCHING__ANALYSIS2 = 1;

	/**
	 * The feature id for the '<em><b>Node Matching</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_MATCHING__NODE_MATCHING = 2;

	/**
	 * The feature id for the '<em><b>Fragment Matching</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_MATCHING__FRAGMENT_MATCHING = 3;

	/**
	 * The number of structural features of the '<em>Process Matching</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_MATCHING_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Process Matching</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_MATCHING_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link edu.udo.cs.ls14.jf.bpmnmatching.impl.NodeMatchingImpl <em>Node Matching</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.udo.cs.ls14.jf.bpmnmatching.impl.NodeMatchingImpl
	 * @see edu.udo.cs.ls14.jf.bpmnmatching.impl.BpmnMatchingPackageImpl#getNodeMatching()
	 * @generated
	 */
	int NODE_MATCHING = 1;

	/**
	 * The feature id for the '<em><b>Pairs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_MATCHING__PAIRS = 0;

	/**
	 * The number of structural features of the '<em>Node Matching</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_MATCHING_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Node Matching</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_MATCHING_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link edu.udo.cs.ls14.jf.bpmnmatching.impl.NodePairImpl <em>Node Pair</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.udo.cs.ls14.jf.bpmnmatching.impl.NodePairImpl
	 * @see edu.udo.cs.ls14.jf.bpmnmatching.impl.BpmnMatchingPackageImpl#getNodePair()
	 * @generated
	 */
	int NODE_PAIR = 2;

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
	 * The meta object id for the '{@link edu.udo.cs.ls14.jf.bpmnmatching.impl.FragmentMatchingImpl <em>Fragment Matching</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.udo.cs.ls14.jf.bpmnmatching.impl.FragmentMatchingImpl
	 * @see edu.udo.cs.ls14.jf.bpmnmatching.impl.BpmnMatchingPackageImpl#getFragmentMatching()
	 * @generated
	 */
	int FRAGMENT_MATCHING = 3;

	/**
	 * The feature id for the '<em><b>Pairs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FRAGMENT_MATCHING__PAIRS = 0;

	/**
	 * The number of structural features of the '<em>Fragment Matching</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FRAGMENT_MATCHING_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Fragment Matching</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FRAGMENT_MATCHING_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link edu.udo.cs.ls14.jf.bpmnmatching.impl.FragmentPairImpl <em>Fragment Pair</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.udo.cs.ls14.jf.bpmnmatching.impl.FragmentPairImpl
	 * @see edu.udo.cs.ls14.jf.bpmnmatching.impl.BpmnMatchingPackageImpl#getFragmentPair()
	 * @generated
	 */
	int FRAGMENT_PAIR = 4;

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
	 * The feature id for the '<em><b>Better</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FRAGMENT_PAIR__BETTER = 2;

	/**
	 * The feature id for the '<em><b>Extracted Process</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FRAGMENT_PAIR__EXTRACTED_PROCESS = 3;

	/**
	 * The number of structural features of the '<em>Fragment Pair</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FRAGMENT_PAIR_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Fragment Pair</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FRAGMENT_PAIR_OPERATION_COUNT = 0;


	/**
	 * The meta object id for the '{@link edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatcher <em>Process Matcher</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatcher
	 * @see edu.udo.cs.ls14.jf.bpmnmatching.impl.BpmnMatchingPackageImpl#getProcessMatcher()
	 * @generated
	 */
	int PROCESS_MATCHER = 5;

	/**
	 * The number of structural features of the '<em>Process Matcher</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_MATCHER_FEATURE_COUNT = 0;

	/**
	 * The operation id for the '<em>Match</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_MATCHER___MATCH__PROCESSANALYSIS_PROCESSANALYSIS = 0;

	/**
	 * The number of operations of the '<em>Process Matcher</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_MATCHER_OPERATION_COUNT = 1;


	/**
	 * Returns the meta object for class '{@link edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching <em>Process Matching</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Process Matching</em>'.
	 * @see edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching
	 * @generated
	 */
	EClass getProcessMatching();

	/**
	 * Returns the meta object for the containment reference '{@link edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching#getAnalysis1 <em>Analysis1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Analysis1</em>'.
	 * @see edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching#getAnalysis1()
	 * @see #getProcessMatching()
	 * @generated
	 */
	EReference getProcessMatching_Analysis1();

	/**
	 * Returns the meta object for the containment reference '{@link edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching#getAnalysis2 <em>Analysis2</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Analysis2</em>'.
	 * @see edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching#getAnalysis2()
	 * @see #getProcessMatching()
	 * @generated
	 */
	EReference getProcessMatching_Analysis2();

	/**
	 * Returns the meta object for the containment reference '{@link edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching#getNodeMatching <em>Node Matching</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Node Matching</em>'.
	 * @see edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching#getNodeMatching()
	 * @see #getProcessMatching()
	 * @generated
	 */
	EReference getProcessMatching_NodeMatching();

	/**
	 * Returns the meta object for the containment reference '{@link edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching#getFragmentMatching <em>Fragment Matching</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Fragment Matching</em>'.
	 * @see edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching#getFragmentMatching()
	 * @see #getProcessMatching()
	 * @generated
	 */
	EReference getProcessMatching_FragmentMatching();

	/**
	 * Returns the meta object for class '{@link edu.udo.cs.ls14.jf.bpmnmatching.NodeMatching <em>Node Matching</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Node Matching</em>'.
	 * @see edu.udo.cs.ls14.jf.bpmnmatching.NodeMatching
	 * @generated
	 */
	EClass getNodeMatching();

	/**
	 * Returns the meta object for the containment reference list '{@link edu.udo.cs.ls14.jf.bpmnmatching.NodeMatching#getPairs <em>Pairs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Pairs</em>'.
	 * @see edu.udo.cs.ls14.jf.bpmnmatching.NodeMatching#getPairs()
	 * @see #getNodeMatching()
	 * @generated
	 */
	EReference getNodeMatching_Pairs();

	/**
	 * Returns the meta object for class '{@link edu.udo.cs.ls14.jf.bpmnmatching.NodePair <em>Node Pair</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Node Pair</em>'.
	 * @see edu.udo.cs.ls14.jf.bpmnmatching.NodePair
	 * @generated
	 */
	EClass getNodePair();

	/**
	 * Returns the meta object for the reference '{@link edu.udo.cs.ls14.jf.bpmnmatching.NodePair#getA <em>A</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>A</em>'.
	 * @see edu.udo.cs.ls14.jf.bpmnmatching.NodePair#getA()
	 * @see #getNodePair()
	 * @generated
	 */
	EReference getNodePair_A();

	/**
	 * Returns the meta object for the reference '{@link edu.udo.cs.ls14.jf.bpmnmatching.NodePair#getB <em>B</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>B</em>'.
	 * @see edu.udo.cs.ls14.jf.bpmnmatching.NodePair#getB()
	 * @see #getNodePair()
	 * @generated
	 */
	EReference getNodePair_B();

	/**
	 * Returns the meta object for class '{@link edu.udo.cs.ls14.jf.bpmnmatching.FragmentMatching <em>Fragment Matching</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Fragment Matching</em>'.
	 * @see edu.udo.cs.ls14.jf.bpmnmatching.FragmentMatching
	 * @generated
	 */
	EClass getFragmentMatching();

	/**
	 * Returns the meta object for the containment reference list '{@link edu.udo.cs.ls14.jf.bpmnmatching.FragmentMatching#getPairs <em>Pairs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Pairs</em>'.
	 * @see edu.udo.cs.ls14.jf.bpmnmatching.FragmentMatching#getPairs()
	 * @see #getFragmentMatching()
	 * @generated
	 */
	EReference getFragmentMatching_Pairs();

	/**
	 * Returns the meta object for class '{@link edu.udo.cs.ls14.jf.bpmnmatching.FragmentPair <em>Fragment Pair</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Fragment Pair</em>'.
	 * @see edu.udo.cs.ls14.jf.bpmnmatching.FragmentPair
	 * @generated
	 */
	EClass getFragmentPair();

	/**
	 * Returns the meta object for the reference '{@link edu.udo.cs.ls14.jf.bpmnmatching.FragmentPair#getA <em>A</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>A</em>'.
	 * @see edu.udo.cs.ls14.jf.bpmnmatching.FragmentPair#getA()
	 * @see #getFragmentPair()
	 * @generated
	 */
	EReference getFragmentPair_A();

	/**
	 * Returns the meta object for the reference '{@link edu.udo.cs.ls14.jf.bpmnmatching.FragmentPair#getB <em>B</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>B</em>'.
	 * @see edu.udo.cs.ls14.jf.bpmnmatching.FragmentPair#getB()
	 * @see #getFragmentPair()
	 * @generated
	 */
	EReference getFragmentPair_B();

	/**
	 * Returns the meta object for the reference '{@link edu.udo.cs.ls14.jf.bpmnmatching.FragmentPair#getBetter <em>Better</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Better</em>'.
	 * @see edu.udo.cs.ls14.jf.bpmnmatching.FragmentPair#getBetter()
	 * @see #getFragmentPair()
	 * @generated
	 */
	EReference getFragmentPair_Better();

	/**
	 * Returns the meta object for the reference '{@link edu.udo.cs.ls14.jf.bpmnmatching.FragmentPair#getExtractedProcess <em>Extracted Process</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Extracted Process</em>'.
	 * @see edu.udo.cs.ls14.jf.bpmnmatching.FragmentPair#getExtractedProcess()
	 * @see #getFragmentPair()
	 * @generated
	 */
	EReference getFragmentPair_ExtractedProcess();

	/**
	 * Returns the meta object for class '{@link edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatcher <em>Process Matcher</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Process Matcher</em>'.
	 * @see edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatcher
	 * @generated
	 */
	EClass getProcessMatcher();

	/**
	 * Returns the meta object for the '{@link edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatcher#match(edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis, edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis) <em>Match</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Match</em>' operation.
	 * @see edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatcher#match(edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis, edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis)
	 * @generated
	 */
	EOperation getProcessMatcher__Match__ProcessAnalysis_ProcessAnalysis();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	BpmnMatchingFactory getBpmnMatchingFactory();

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
		 * The meta object literal for the '{@link edu.udo.cs.ls14.jf.bpmnmatching.impl.ProcessMatchingImpl <em>Process Matching</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.udo.cs.ls14.jf.bpmnmatching.impl.ProcessMatchingImpl
		 * @see edu.udo.cs.ls14.jf.bpmnmatching.impl.BpmnMatchingPackageImpl#getProcessMatching()
		 * @generated
		 */
		EClass PROCESS_MATCHING = eINSTANCE.getProcessMatching();

		/**
		 * The meta object literal for the '<em><b>Analysis1</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROCESS_MATCHING__ANALYSIS1 = eINSTANCE.getProcessMatching_Analysis1();

		/**
		 * The meta object literal for the '<em><b>Analysis2</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROCESS_MATCHING__ANALYSIS2 = eINSTANCE.getProcessMatching_Analysis2();

		/**
		 * The meta object literal for the '<em><b>Node Matching</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROCESS_MATCHING__NODE_MATCHING = eINSTANCE.getProcessMatching_NodeMatching();

		/**
		 * The meta object literal for the '<em><b>Fragment Matching</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROCESS_MATCHING__FRAGMENT_MATCHING = eINSTANCE.getProcessMatching_FragmentMatching();

		/**
		 * The meta object literal for the '{@link edu.udo.cs.ls14.jf.bpmnmatching.impl.NodeMatchingImpl <em>Node Matching</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.udo.cs.ls14.jf.bpmnmatching.impl.NodeMatchingImpl
		 * @see edu.udo.cs.ls14.jf.bpmnmatching.impl.BpmnMatchingPackageImpl#getNodeMatching()
		 * @generated
		 */
		EClass NODE_MATCHING = eINSTANCE.getNodeMatching();

		/**
		 * The meta object literal for the '<em><b>Pairs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NODE_MATCHING__PAIRS = eINSTANCE.getNodeMatching_Pairs();

		/**
		 * The meta object literal for the '{@link edu.udo.cs.ls14.jf.bpmnmatching.impl.NodePairImpl <em>Node Pair</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.udo.cs.ls14.jf.bpmnmatching.impl.NodePairImpl
		 * @see edu.udo.cs.ls14.jf.bpmnmatching.impl.BpmnMatchingPackageImpl#getNodePair()
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
		 * The meta object literal for the '{@link edu.udo.cs.ls14.jf.bpmnmatching.impl.FragmentMatchingImpl <em>Fragment Matching</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.udo.cs.ls14.jf.bpmnmatching.impl.FragmentMatchingImpl
		 * @see edu.udo.cs.ls14.jf.bpmnmatching.impl.BpmnMatchingPackageImpl#getFragmentMatching()
		 * @generated
		 */
		EClass FRAGMENT_MATCHING = eINSTANCE.getFragmentMatching();

		/**
		 * The meta object literal for the '<em><b>Pairs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FRAGMENT_MATCHING__PAIRS = eINSTANCE.getFragmentMatching_Pairs();

		/**
		 * The meta object literal for the '{@link edu.udo.cs.ls14.jf.bpmnmatching.impl.FragmentPairImpl <em>Fragment Pair</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.udo.cs.ls14.jf.bpmnmatching.impl.FragmentPairImpl
		 * @see edu.udo.cs.ls14.jf.bpmnmatching.impl.BpmnMatchingPackageImpl#getFragmentPair()
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
		 * The meta object literal for the '<em><b>Better</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FRAGMENT_PAIR__BETTER = eINSTANCE.getFragmentPair_Better();

		/**
		 * The meta object literal for the '<em><b>Extracted Process</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FRAGMENT_PAIR__EXTRACTED_PROCESS = eINSTANCE.getFragmentPair_ExtractedProcess();

		/**
		 * The meta object literal for the '{@link edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatcher <em>Process Matcher</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatcher
		 * @see edu.udo.cs.ls14.jf.bpmnmatching.impl.BpmnMatchingPackageImpl#getProcessMatcher()
		 * @generated
		 */
		EClass PROCESS_MATCHER = eINSTANCE.getProcessMatcher();

		/**
		 * The meta object literal for the '<em><b>Match</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation PROCESS_MATCHER___MATCH__PROCESSANALYSIS_PROCESSANALYSIS = eINSTANCE.getProcessMatcher__Match__ProcessAnalysis_ProcessAnalysis();

	}

} //BpmnMatchingPackage
