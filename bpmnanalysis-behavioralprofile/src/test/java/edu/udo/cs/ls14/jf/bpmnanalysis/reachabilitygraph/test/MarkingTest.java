package edu.udo.cs.ls14.jf.bpmnanalysis.reachabilitygraph.test;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.udo.cs.ls14.jf.bpmnanalysis.reachabilitygraph.Marking;
import fr.lip6.move.pnml.ptnet.Place;
import fr.lip6.move.pnml.ptnet.PtnetFactory;

public class MarkingTest {

	@Test
	public void testMarkingEquals() {
		Marking m1 = new Marking();
		Marking m2 = new Marking();

		Place p1 = PtnetFactory.eINSTANCE.createPlace();
		Place p2 = PtnetFactory.eINSTANCE.createPlace();
		Place p3 = PtnetFactory.eINSTANCE.createPlace();
		Place p4 = PtnetFactory.eINSTANCE.createPlace();
		p1.setId("p0");
		p2.setId("p1");
		p3.setId("p0");
		p4.setId("p1");
		assertNotEquals(p1,p3);
		assertNotEquals(p2,p4);

		m1.add(p1);
		m1.add(p2);
		m2.add(p1);
		m2.add(p2);
		assertEquals(m1,m2);
		m2.add(p2);
		m2.add(p1);
		assertEquals(m1,m2);
		
		m2.clear();
		m2.add(p3);
		m2.add(p4);
		assertNotEquals(m1,m2);
		
	}
	
}
