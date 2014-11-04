package edu.udo.cs.ls14.jf.services.adapter.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.javatuples.Pair;
import org.junit.Test;

import edu.udo.cs.ls14.jf.analysis.behaviorprofile.BehavioralProfile;
import edu.udo.cs.ls14.jf.analysis.behaviorprofile.RelationType;
import edu.udo.cs.ls14.jf.services.adapter.BehavioralProfileAdapter;
import edu.udo.cs.ls14.jf.services.types.BehaviourProfileType;
import edu.udo.cs.ls14.jf.services.types.OrderRelationType;

public class BehavioralProfileAdapterTest {

	@Test
	public void testMarshal() throws Exception {
		BehavioralProfile bp = new BehavioralProfile();
		bp.put(Pair.with("A", "B"), RelationType.NO_SUCCESSION);
		bp.put(Pair.with("A", "C"), RelationType.DIRECT_PREDECESSOR);
		bp.put(Pair.with("A", "D"), RelationType.DIRECT_SUCCESSOR);
		bp.put(Pair.with("A", "E"), RelationType.PARALLEL);
		BehavioralProfileAdapter adapter = new BehavioralProfileAdapter();
		BehaviourProfileType bpt = adapter.marshal(bp);
		assertTrue(bpt.relation.contains(new OrderRelationType("A", "B",
				RelationType.NO_SUCCESSION)));
		assertTrue(bpt.relation.contains(new OrderRelationType("A", "C",
				RelationType.DIRECT_PREDECESSOR)));
		assertTrue(bpt.relation.contains(new OrderRelationType("A", "D",
				RelationType.DIRECT_SUCCESSOR)));
		assertTrue(bpt.relation.contains(new OrderRelationType("A", "E",
				RelationType.PARALLEL)));
		assertEquals(4, bpt.relation.size());
	}

	@Test
	public void testUnmarshal() throws Exception {
		BehaviourProfileType bpt = new BehaviourProfileType();
		bpt.relation.add(new OrderRelationType("A", "B",
				RelationType.NO_SUCCESSION));
		bpt.relation.add(new OrderRelationType("A", "C",
				RelationType.DIRECT_PREDECESSOR));
		bpt.relation.add(new OrderRelationType("A", "D",
				RelationType.DIRECT_SUCCESSOR));
		bpt.relation
				.add(new OrderRelationType("A", "E", RelationType.PARALLEL));

		BehavioralProfileAdapter adapter = new BehavioralProfileAdapter();
		BehavioralProfile bp = adapter.unmarshal(bpt);

		assertEquals(RelationType.NO_SUCCESSION, bp.get(Pair.with("A", "B")));
		assertEquals(RelationType.DIRECT_PREDECESSOR,
				bp.get(Pair.with("A", "C")));
		assertEquals(RelationType.DIRECT_SUCCESSOR, bp.get(Pair.with("A", "D")));
		assertEquals(RelationType.PARALLEL, bp.get(Pair.with("A", "E")));
		assertEquals(4, bp.size());
	}

}
