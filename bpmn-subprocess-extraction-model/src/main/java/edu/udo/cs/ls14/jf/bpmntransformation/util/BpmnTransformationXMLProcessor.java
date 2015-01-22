/**
 */
package edu.udo.cs.ls14.jf.bpmntransformation.util;

import edu.udo.cs.ls14.jf.bpmntransformation.BpmnTransformationPackage;

import java.util.Map;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.resource.Resource;

import org.eclipse.emf.ecore.xmi.util.XMLProcessor;

/**
 * This class contains helper methods to serialize and deserialize XML documents
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class BpmnTransformationXMLProcessor extends XMLProcessor {

	/**
	 * Public constructor to instantiate the helper.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BpmnTransformationXMLProcessor() {
		super((EPackage.Registry.INSTANCE));
		BpmnTransformationPackage.eINSTANCE.eClass();
	}
	
	/**
	 * Register for "*" and "xml" file extensions the BpmnTransformationResourceFactoryImpl factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected Map<String, Resource.Factory> getRegistrations() {
		if (registrations == null) {
			super.getRegistrations();
			registrations.put(XML_EXTENSION, new BpmnTransformationResourceFactoryImpl());
			registrations.put(STAR_EXTENSION, new BpmnTransformationResourceFactoryImpl());
		}
		return registrations;
	}

} //BpmnTransformationXMLProcessor
