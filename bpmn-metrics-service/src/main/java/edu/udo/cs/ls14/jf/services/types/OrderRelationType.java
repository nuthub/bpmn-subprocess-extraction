package edu.udo.cs.ls14.jf.services.types;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

import edu.udo.cs.ls14.jf.analysis.behaviorprofile.RelationTypeOld;

public class OrderRelationType {

	@XmlAttribute
	public String a;
	@XmlAttribute
	public String b;
	@XmlValue
	public RelationTypeOld relationType;

	public OrderRelationType() {
		super();
	}

	public OrderRelationType(String a, String b, RelationTypeOld relationType) {
		super();
		this.a = a;
		this.b = b;
		this.relationType = relationType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((a == null) ? 0 : a.hashCode());
		result = prime * result + ((b == null) ? 0 : b.hashCode());
		result = prime * result
				+ ((relationType == null) ? 0 : relationType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderRelationType other = (OrderRelationType) obj;
		if (a == null) {
			if (other.a != null)
				return false;
		} else if (!a.equals(other.a))
			return false;
		if (b == null) {
			if (other.b != null)
				return false;
		} else if (!b.equals(other.b))
			return false;
		if (relationType != other.relationType)
			return false;
		return true;
	}

}
