package edu.udo.cs.ls14.jf.services.types;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

import edu.udo.cs.ls14.jf.analysis.behaviorprofile.RelationType;

public class OrderRelationType {

	@XmlAttribute
	public String a;
	@XmlAttribute
	public String b;
	@XmlValue
	public RelationType relationType;

	public OrderRelationType() {
	}

	public OrderRelationType(String a, String b, RelationType relationType) {
		this.a = a;
		this.b = b;
		this.relationType = relationType;
	}

}
