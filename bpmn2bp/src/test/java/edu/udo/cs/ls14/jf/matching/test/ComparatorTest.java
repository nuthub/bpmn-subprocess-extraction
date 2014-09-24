package edu.udo.cs.ls14.jf.matching.test;

import static org.junit.Assert.assertTrue;

import org.eclipse.bpmn2.Bpmn2Factory;
import org.eclipse.bpmn2.FlowNode;
import org.junit.Test;

import edu.udo.cs.ls14.jf.matching.Comparator;

public class ComparatorTest {

	@Test
	public void test1() {
		Comparator c = new Comparator();
		FlowNode a1 = Bpmn2Factory.eINSTANCE.createTask();
		a1.setName("Die Waren versenden");
		FlowNode a2 = Bpmn2Factory.eINSTANCE.createTask();
		a2.setName("Den Artikel verschicken.");
		assertTrue(c.isEquivalent(a1, a2));
	}
	@Test
	public void test2() {
		Comparator c = new Comparator();
		FlowNode a1 = Bpmn2Factory.eINSTANCE.createTask();
		a1.setName("Waren verpacken");
		FlowNode a2 = Bpmn2Factory.eINSTANCE.createTask();
		a2.setName("Güter verpacken.");
		assertTrue(c.isEquivalent(a1, a2));
	}
}
