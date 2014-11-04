package edu.udo.cs.ls14.jf.services.adapter;

import java.util.ArrayList;
import java.util.Map;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.javatuples.Pair;

import edu.udo.cs.ls14.jf.analysis.behaviorprofile.BehavioralProfile;
import edu.udo.cs.ls14.jf.analysis.behaviorprofile.RelationType;
import edu.udo.cs.ls14.jf.services.types.BehaviourProfileType;
import edu.udo.cs.ls14.jf.services.types.OrderRelationType;

public class BehaviourProfileAdapter extends
		XmlAdapter<BehaviourProfileType, BehavioralProfile> {

	@Override
	public BehavioralProfile unmarshal(BehaviourProfileType profile)
			throws Exception {
		BehavioralProfile map = new BehavioralProfile();
		for (OrderRelationType relation : profile.relation) {
			map.put(Pair.with(relation.a, relation.b), relation.relationType);
		}
		return map;
	}

	@Override
	public BehaviourProfileType marshal(BehavioralProfile v) throws Exception {
		BehaviourProfileType profile = new BehaviourProfileType();
		profile.relation = new ArrayList<OrderRelationType>();
		for (Map.Entry<Pair<String, String>, RelationType> entry : v.entrySet()) {
			profile.relation
					.add(new OrderRelationType(entry.getKey().getValue0(),
							entry.getKey().getValue1(), entry.getValue()));
		}
		return profile;
	}

}
