package edu.udo.cs.ls14.jf.services.adapter;

import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.Set;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.FormalExpression;

import edu.udo.cs.ls14.jf.analysis.conditionalprofile.ConditionalProfile;
import edu.udo.cs.ls14.jf.services.types.ConditionalProfileType;
import edu.udo.cs.ls14.jf.services.types.ConditionalRelationType;

public class ConditionalProfileAdapter extends XmlAdapter<ConditionalProfileType, ConditionalProfile>{

	@Override
	public ConditionalProfile unmarshal(ConditionalProfileType v)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConditionalProfileType marshal(ConditionalProfile profile)
			throws Exception {
		ConditionalProfileType cpt = new ConditionalProfileType();
		for (Entry<FlowNode, Set<FormalExpression>> r : profile.entrySet()) {
			ConditionalRelationType crt = new ConditionalRelationType();
			crt.flowNodeId = r.getKey().getId();
			crt.conditions = new ArrayList<String>();
			for(FormalExpression exp: r.getValue() ) {
				crt.conditions.add(exp.getBody());
			}
			cpt.conditions.add(crt);
		}
		System.out.println(cpt.conditions);
		return cpt;
	}

}
