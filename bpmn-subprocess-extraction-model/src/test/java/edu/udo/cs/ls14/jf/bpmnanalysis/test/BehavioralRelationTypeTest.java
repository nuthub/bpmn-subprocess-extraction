package edu.udo.cs.ls14.jf.bpmnanalysis.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import edu.udo.cs.ls14.jf.bpmnanalysis.BehavioralRelationType;

public class BehavioralRelationTypeTest {

	@Test
	public void testBehavioralRelationType() {
		assertEquals(BehavioralRelationType.DIRECT_PREDECESSOR,
				BehavioralRelationType
						.valueOf(BehavioralRelationType.DIRECT_PREDECESSOR
								.toString()));
		assertEquals(BehavioralRelationType.DIRECT_SUCCESSOR,
				BehavioralRelationType
						.valueOf(BehavioralRelationType.DIRECT_SUCCESSOR
								.toString()));
		assertEquals(BehavioralRelationType.PARALLEL,
				BehavioralRelationType.valueOf(BehavioralRelationType.PARALLEL
						.toString()));
		assertEquals(BehavioralRelationType.NO_SUCCESSION,
				BehavioralRelationType
						.valueOf(BehavioralRelationType.NO_SUCCESSION
								.toString()));
	}
}
