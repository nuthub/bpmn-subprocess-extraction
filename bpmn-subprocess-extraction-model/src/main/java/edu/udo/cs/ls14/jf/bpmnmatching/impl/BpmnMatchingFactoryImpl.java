/**
 */
package edu.udo.cs.ls14.jf.bpmnmatching.impl;

import edu.udo.cs.ls14.jf.bpmnmatching.*;

import org.eclipse.emf.ecore.EClass;
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
public class BpmnMatchingFactoryImpl extends EFactoryImpl implements BpmnMatchingFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static BpmnMatchingFactory init() {
		try {
			BpmnMatchingFactory theBpmnMatchingFactory = (BpmnMatchingFactory)EPackage.Registry.INSTANCE.getEFactory(BpmnMatchingPackage.eNS_URI);
			if (theBpmnMatchingFactory != null) {
				return theBpmnMatchingFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new BpmnMatchingFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BpmnMatchingFactoryImpl() {
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
			case BpmnMatchingPackage.PROCESS_MATCHING: return createProcessMatching();
			case BpmnMatchingPackage.NODE_MATCHING: return createNodeMatching();
			case BpmnMatchingPackage.NODE_PAIR: return createNodePair();
			case BpmnMatchingPackage.FRAGMENT_MATCHING: return createFragmentMatching();
			case BpmnMatchingPackage.FRAGMENT_PAIR: return createFragmentPair();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProcessMatching createProcessMatching() {
		ProcessMatchingImpl processMatching = new ProcessMatchingImpl();
		return processMatching;
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
	public BpmnMatchingPackage getBpmnMatchingPackage() {
		return (BpmnMatchingPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static BpmnMatchingPackage getPackage() {
		return BpmnMatchingPackage.eINSTANCE;
	}

} //BpmnMatchingFactoryImpl
