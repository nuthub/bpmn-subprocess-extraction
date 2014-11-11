package edu.udo.cs.ls14.jf.services.bpmn.test;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.javatuples.Pair;

public class XmlStringPairAdapter extends XmlAdapter<String, Pair<String, String>> {

	@Override
	public String marshal(Pair<String, String> v) throws Exception {
		return v.getValue0() + "," + v.getValue1();
	}

	@Override
	public Pair<String, String> unmarshal(String v) throws Exception {
		String[] strArr = v.split(",");
		return Pair.with(strArr[0], strArr[1]);
	}

}
