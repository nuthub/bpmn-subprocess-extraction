package edu.udo.cs.ls14.jf.services.adapter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.logging.Logger;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.apache.commons.lang3.NotImplementedException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;

// TODO: make use of EobjectXmlConverter
public class XmlEObjectTypeAdapter extends XmlAdapter<String, EObject> {

	private static final Logger LOG = Logger
			.getLogger(XmlEObjectTypeAdapter.class.getName());

	@Override
	public EObject unmarshal(String v) throws Exception {
		XMLResource xres = new XMLResourceImpl();
		ByteArrayInputStream is = new ByteArrayInputStream(v.getBytes());
		xres.load(is, null);
		if (xres.getContents().size() != 1) {
			throw new NotImplementedException(
					"Only XMI with exactly one root supported, found "
							+ xres.getContents().size());
		}
		return xres.getContents().get(0);
	}

	@Override
	public String marshal(EObject v) throws Exception {
		XMLResource xres = new XMLResourceImpl();
		xres.setEncoding("UTF-8");
		xres.getContents().add(v);
		ByteArrayOutputStream os = new ByteArrayOutputStream();

		try {
			xres.save(os, null);
		} catch (NullPointerException e) {
			e.printStackTrace();
			LOG.severe("could not create XMLResource, NPE occurred (dangling symlink?)");
			return "";
		}
		return os.toString();
	}

}
