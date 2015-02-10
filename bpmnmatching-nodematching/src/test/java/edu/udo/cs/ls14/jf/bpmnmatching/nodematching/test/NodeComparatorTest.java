package edu.udo.cs.ls14.jf.bpmnmatching.nodematching.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.eclipse.bpmn2.Bpmn2Factory;
import org.eclipse.bpmn2.FlowNode;
import org.junit.Test;

import edu.udo.cs.ls14.jf.bpmnmatching.nodematching.NodeComparator;

public class NodeComparatorTest {

	@Test
	public void test1() {
		NodeComparator c = new NodeComparator();
		FlowNode a1 = Bpmn2Factory.eINSTANCE.createTask();
		a1.setName("Die Waren versenden");
		FlowNode a2 = Bpmn2Factory.eINSTANCE.createTask();
		a2.setName("Den Artikel verschicken.");
		assertTrue(c.isEquivalent(a1, a2));
		assertTrue(c.isEquivalent(a2, a1));
	}

	@Test
	public void test2() {
		NodeComparator c = new NodeComparator();
		FlowNode a1 = Bpmn2Factory.eINSTANCE.createTask();
		a1.setName("Waren verpacken");
		FlowNode a2 = Bpmn2Factory.eINSTANCE.createTask();
		a2.setName("Güter verpacken.");
		assertTrue(c.isEquivalent(a1, a2));
		assertTrue(c.isEquivalent(a2, a1));
	}

	@Test
	public void test3() {
		NodeComparator c = new NodeComparator();
		FlowNode a1 = Bpmn2Factory.eINSTANCE.createTask();
		a1.setName("ein Label");
		FlowNode a2 = Bpmn2Factory.eINSTANCE.createTask();
		a2.setName("ein anderes Label");
		assertFalse(c.isEquivalent(a1, a2));
		assertFalse(c.isEquivalent(a2, a1));
	}

	@Test
	public void test4() {
		NodeComparator c = new NodeComparator();
		FlowNode a1 = Bpmn2Factory.eINSTANCE.createTask();
		a1.setName("ein Wort");
		FlowNode a2 = Bpmn2Factory.eINSTANCE.createTask();
		a2.setName("ein Hund und eine Katze");
		assertFalse(c.isEquivalent(a2, a1));
		assertFalse(c.isEquivalent(a1, a2));
	}

	@Test
	public void test5() {
		NodeComparator c = new NodeComparator();
		FlowNode a1 = Bpmn2Factory.eINSTANCE.createIntermediateCatchEvent();
		a1.setName("sameLabel");
		FlowNode a2 = Bpmn2Factory.eINSTANCE.createTask();
		a2.setName("sameLabel");
		assertFalse(c.isEquivalent(a2, a1));
		assertFalse(c.isEquivalent(a1, a2));
	}

	@Test
	public void testNonInterestingInput() {
		NodeComparator c = new NodeComparator();
		FlowNode a1 = Bpmn2Factory.eINSTANCE.createExclusiveGateway();
		a1.setName("sameLabel");
		FlowNode a2 = Bpmn2Factory.eINSTANCE.createTask();
		a2.setName("sameLabel");
		assertFalse(c.isEquivalent(a1, a2));
		assertFalse(c.isEquivalent(a2, a1));
	}
	@Test
	public void testNullLabels() {
		NodeComparator c = new NodeComparator();
		FlowNode a1 = Bpmn2Factory.eINSTANCE.createTask();
		a1.setName("");
		FlowNode a2 = Bpmn2Factory.eINSTANCE.createTask();
		a2.setName(null);
		assertNull(a2.getName());
		assertTrue(c.isEquivalent(a1, a2));
		assertTrue(c.isEquivalent(a2, a1));
	}
}
