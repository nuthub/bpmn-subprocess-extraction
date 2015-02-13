package edu.udo.cs.ls14.jf.bpmn.ws.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.eclipse.emf.ecore.EObject;

import edu.udo.cs.ls14.jf.bpmn.util.Registries;
import edu.udo.cs.ls14.jf.bpmnmatching.BpmnMatchingPackage;

/**
 * Abstract XMLAdapter implementation for objects of the BpmnMatching package.
 * 
 * @author JulianFlake
 *
 * @param <T>
 *            concrete Type
 */
public abstract class BpmnMatchingAdapterBase<T extends EObject> extends
		XmlAdapter<String, T> {

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public T unmarshal(String v) throws Exception {
		Registries.registerBpmnMatching();
		return (T) EObjectXmlConverter
				.xml2EObject(BpmnMatchingPackage.eNAME, v);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String marshal(T v) throws Exception {
		Registries.registerBpmnMatching();
		return EObjectXmlConverter.eObject2Xml(BpmnMatchingPackage.eNAME, v);
	}

}
