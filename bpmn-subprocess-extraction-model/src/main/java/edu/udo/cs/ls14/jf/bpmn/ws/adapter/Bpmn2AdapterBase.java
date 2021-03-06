package edu.udo.cs.ls14.jf.bpmn.ws.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.eclipse.bpmn2.Bpmn2Package;
import org.eclipse.emf.ecore.EObject;

import edu.udo.cs.ls14.jf.bpmn.util.Registries;

/**
 * Abstract XMLAdapter implementation for classes of the BPMN2 meta model.
 * 
 * @author Julian Flake
 *
 * @param <T>
 *            concrete Type
 */
public abstract class Bpmn2AdapterBase<T extends EObject> extends
		XmlAdapter<String, T> {

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public T unmarshal(String v) throws Exception {
		Registries.registerBpmn();
		return (T) EObjectXmlConverter.xml2EObject("bpmn", v);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String marshal(T v) throws Exception {
		Registries.registerBpmn();
		return EObjectXmlConverter.eObject2Xml("bpmn", v);
	}

}
