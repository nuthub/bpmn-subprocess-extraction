package edu.udo.cs.ls14.jf.services.types;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ConditionalRelationType {

	@XmlAttribute
	public String flowNodeId;

	@XmlElement(name = "condition")
	public List<String> conditions;

}
