package edu.udo.cs.ls14.jf.ws.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.eclipse.bpmn2.Bpmn2Package;
import org.eclipse.bpmn2.util.Bpmn2ResourceFactoryImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;

import edu.udo.cs.ls14.jf.bpmn.utils.EObjectXmlConverter;

public abstract class Bpmn2AdapterBase<T extends EObject> extends
		XmlAdapter<String, T> {

	@SuppressWarnings("unchecked")
	@Override
	public T unmarshal(String v) throws Exception {
		registerFactoriesAndPackages();
		return (T) EObjectXmlConverter.xml2EObject("bpmn", v);
	}

	@Override
	public String marshal(T v) throws Exception {
		registerFactoriesAndPackages();
		return EObjectXmlConverter.eObject2Xml("bpmn", v);
	}

	private void registerFactoriesAndPackages() {
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap()
				.putIfAbsent("bpmn", new Bpmn2ResourceFactoryImpl());
		EPackage.Registry.INSTANCE.put(Bpmn2Package.eNS_URI,
				Bpmn2Package.eINSTANCE);
	}

}
