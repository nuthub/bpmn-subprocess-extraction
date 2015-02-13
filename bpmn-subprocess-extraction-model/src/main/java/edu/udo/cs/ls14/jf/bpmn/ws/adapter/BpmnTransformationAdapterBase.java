package edu.udo.cs.ls14.jf.bpmn.ws.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.eclipse.emf.ecore.EObject;

import edu.udo.cs.ls14.jf.bpmn.util.Registries;
import edu.udo.cs.ls14.jf.bpmntransformation.BpmnTransformationPackage;

/**
 * Abstract XMLAdapter implementation for objects of the BpmnTransformation
 * package.
 * 
 * @author JulianFlake
 *
 * @param <T>
 *            concrete Type
 */
public abstract class BpmnTransformationAdapterBase<T extends EObject> extends
		XmlAdapter<String, T> {

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public T unmarshal(String v) throws Exception {
		Registries.registerBpmnTransformation();
		return (T) EObjectXmlConverter.xml2EObject(
				BpmnTransformationPackage.eNAME, v);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String marshal(T v) throws Exception {
		Registries.registerBpmnTransformation();
		return EObjectXmlConverter.eObject2Xml(BpmnTransformationPackage.eNAME,
				v);
	}

}
