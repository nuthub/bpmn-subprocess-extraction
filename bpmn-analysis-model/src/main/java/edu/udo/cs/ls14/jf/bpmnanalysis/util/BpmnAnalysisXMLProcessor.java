/**
 */
package edu.udo.cs.ls14.jf.bpmnanalysis.util;

import edu.udo.cs.ls14.jf.bpmnanalysis.BpmnAnalysisPackage;

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
public class BpmnAnalysisXMLProcessor extends XMLProcessor {

	/**
	 * Public constructor to instantiate the helper.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BpmnAnalysisXMLProcessor() {
		super((EPackage.Registry.INSTANCE));
		BpmnAnalysisPackage.eINSTANCE.eClass();
	}
	
	/**
	 * Register for "*" and "xml" file extensions the BpmnAnalysisResourceFactoryImpl factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected Map<String, Resource.Factory> getRegistrations() {
		if (registrations == null) {
			super.getRegistrations();
			registrations.put(XML_EXTENSION, new BpmnAnalysisResourceFactoryImpl());
			registrations.put(STAR_EXTENSION, new BpmnAnalysisResourceFactoryImpl());
		}
		return registrations;
	}

} //BpmnAnalysisXMLProcessor
