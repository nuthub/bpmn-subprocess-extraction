/**
 */
package edu.udo.cs.ls14.jf.bpmnanalysis.util;

import org.eclipse.emf.common.util.URI;

import org.eclipse.emf.ecore.resource.Resource;

import org.eclipse.emf.ecore.resource.impl.ResourceFactoryImpl;

/**
 * <!-- begin-user-doc -->
 * The <b>Resource Factory</b> associated with the package.
 * <!-- end-user-doc -->
 * @see edu.udo.cs.ls14.jf.bpmnanalysis.util.BpmnAnalysisResourceImpl
 * @generated
 */
public class BpmnAnalysisResourceFactoryImpl extends ResourceFactoryImpl {
	/**
	 * Creates an instance of the resource factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BpmnAnalysisResourceFactoryImpl() {
		super();
	}

	/**
	 * Creates an instance of the resource.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Resource createResource(URI uri) {
		Resource result = new BpmnAnalysisResourceImpl(uri);
		return result;
	}

} //BpmnAnalysisResourceFactoryImpl
