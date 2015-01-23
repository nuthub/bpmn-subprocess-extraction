/**
 */
package edu.udo.cs.ls14.jf.bpmntransformation.impl;

import edu.udo.cs.ls14.jf.bpmntransformation.*;

import java.util.Map;

import org.eclipse.bpmn2.Definitions;

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
public class BpmnTransformationFactoryImpl extends EFactoryImpl implements BpmnTransformationFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static BpmnTransformationFactory init() {
		try {
			BpmnTransformationFactory theBpmnTransformationFactory = (BpmnTransformationFactory)EPackage.Registry.INSTANCE.getEFactory(BpmnTransformationPackage.eNS_URI);
			if (theBpmnTransformationFactory != null) {
				return theBpmnTransformationFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new BpmnTransformationFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BpmnTransformationFactoryImpl() {
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
			case BpmnTransformationPackage.PROCESS_TRANSFORMATION: return createProcessTransformation();
			case BpmnTransformationPackage.TRANSFORMATION_RESULT_ENTRY: return (EObject)createTransformationResultEntry();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProcessTransformation createProcessTransformation() {
		ProcessTransformationImpl processTransformation = new ProcessTransformationImpl();
		return processTransformation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Map.Entry<String, Definitions> createTransformationResultEntry() {
		TransformationResultEntryImpl transformationResultEntry = new TransformationResultEntryImpl();
		return transformationResultEntry;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BpmnTransformationPackage getBpmnTransformationPackage() {
		return (BpmnTransformationPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static BpmnTransformationPackage getPackage() {
		return BpmnTransformationPackage.eINSTANCE;
	}

} //BpmnTransformationFactoryImpl
