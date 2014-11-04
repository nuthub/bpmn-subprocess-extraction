package edu.udo.cs.ls14.jf.services.types;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlSeeAlso({ ConditionalRelationType.class })
@XmlRootElement
public class ConditionalProfileType {

	public ArrayList<ConditionalRelationType> conditions = new ArrayList<ConditionalRelationType>();

}
