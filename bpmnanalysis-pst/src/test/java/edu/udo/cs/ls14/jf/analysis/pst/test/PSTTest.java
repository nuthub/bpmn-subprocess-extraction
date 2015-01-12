package edu.udo.cs.ls14.jf.analysis.pst.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.eclipse.bpmn2.Definitions;
import org.junit.Before;
import org.junit.Test;

import edu.udo.cs.ls14.jf.analysis.pst.PSTBuilder;
import edu.udo.cs.ls14.jf.bpmn.utils.Bpmn2ResourceSet;
import edu.udo.cs.ls14.jf.bpmnanalysis.Fragment;
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessStructureTree;
import edu.udo.cs.ls14.jf.registry.Registries;

public class PSTTest {

	@Before
	public void setUp() {
		Registries.registerAll();
	}

	@Test
	public void testComplete1() throws Exception {
		String basename = "complete1";
		String pathname = "/bpmn/complete/";
		ProcessStructureTree pst = runTest(pathname, basename);
		assertEquals(14,pst.getFragments().size());
	}


	@Test
	public void testEventBasedGatewayExclusive() throws Exception {
		String pathname = "/bpmn/eventBasedGateway/";
		String basename = "event-based-gateway-exclusive";
		ProcessStructureTree pst = runTest(pathname, basename);
		List<Fragment> frags = pst.getFragments();
		assertEquals(3, frags.size());
		assertFragsContainByName(frags, "1", "6");
		assertFragsContainByName(frags, "2", "4");
		assertFragsContainByName(frags, "3", "5");

		Map<String, Fragment> fragMap = frags.stream()
				.collect(
						Collectors.toMap(f -> f.getEntry().getName() + "#"
								+ f.getExit().getName(),
								Function.<Fragment> identity()));

		assertEquals(null, fragMap.get("1#6").getParent());
		// parent(2,4) == (1,6)
		assertEquals("1", fragMap.get("2#4").getParent().getEntry().getName());
		assertEquals("6", fragMap.get("2#4").getParent().getExit().getName());
		// parent(1,6) == (3,5)
		assertEquals("1", fragMap.get("3#5").getParent().getEntry().getName());
		assertEquals("6", fragMap.get("3#5").getParent().getExit().getName());
	}

	@Test
	public void testEventBasedGatewayParallel() throws Exception {
		String pathname = "/bpmn/eventBasedGateway/";
		String basename = "event-based-gateway-parallel";
		ProcessStructureTree pst = runTest(pathname, basename);
		List<Fragment> frags = pst.getFragments();
		assertEquals(3, frags.size());
		assertFragsContainByName(frags, "1", "6");
		assertFragsContainByName(frags, "2", "4");
		assertFragsContainByName(frags, "3", "5");
	}

	@Test
	public void testPM1() throws Exception {
		String pathname = "/bpmn/";
		String basename = "PM1-mit-Fragment1";
		ProcessStructureTree pst = runTest(pathname, basename);
		List<Fragment> frags = pst.getFragments();
		assertEquals(6, frags.size());
		assertFragsContainByName(frags, "1", "2");
		assertFragsContainByName(frags, "2", "9");
		assertFragsContainByName(frags, "3", "6");
		assertFragsContainByName(frags, "4", "7");
		assertFragsContainByName(frags, "5", "8");
		assertFragsContainByName(frags, "9", "10");
	}

	@Test
	public void testPM2() throws Exception {
		String pathname = "/bpmn/";
		String basename = "PM2-mit-Fragment1";
		ProcessStructureTree pst = runTest(pathname, basename);
		List<Fragment> frags = pst.getFragments();
		assertEquals(7, frags.size());
		assertFragsContainByName(frags, "1", "2");
		assertFragsContainByName(frags, "2", "11");
		assertFragsContainByName(frags, "3", "9");
		assertFragsContainByName(frags, "4", "10");
		assertFragsContainByName(frags, "5", "7");
		assertFragsContainByName(frags, "6", "8");
		assertFragsContainByName(frags, "11", "12");
	}

	@Test
	public void testSequence() throws Exception {
		String pathname = "/bpmn/sequences/";
		String basename = "sequence1";
		ProcessStructureTree pst = runTest(pathname, basename);
		List<Fragment> frags = pst.getFragments();
		assertEquals(5, frags.size());
		assertFragsContainByName(frags, "1", "2");
		assertFragsContainByName(frags, "2", "3");
		assertFragsContainByName(frags, "3", "4");
		assertFragsContainByName(frags, "4", "5");
		assertFragsContainByName(frags, "5", "6");
	}

	@Test
	public void testXorExample() throws Exception {
		String pathname = "/bpmn/exclusiveGateway/";
		String basename = "xor-example";
		ProcessStructureTree pst = runTest(pathname, basename);
		List<Fragment> frags = pst.getFragments();
		assertEquals(4, frags.size());
		assertFragsContainByName(frags, "1", "2");
		assertFragsContainByName(frags, "2", "7");
		assertFragsContainByName(frags, "3", "5");
		assertFragsContainByName(frags, "4", "6");
	}

	@Test
	public void testLoopingXor() throws Exception {
		String pathname = "/bpmn/exclusiveGateway/";
		String basename = "looping-xor";
		ProcessStructureTree pst = runTest(pathname, basename);
		List<Fragment> frags = pst.getFragments();
		assertEquals(5, frags.size());
		assertFragsContainByName(frags, "1", "10");
		assertFragsContainByName(frags, "2", "3");
		assertFragsContainByName(frags, "4", "9");
		assertFragsContainByName(frags, "5", "7");
		assertFragsContainByName(frags, "6", "8");
	}

	@Test
	public void testLoopingEvents() throws Exception {
		String pathname = "/bpmn/loops/";
		String basename = "looping-events-example";
		ProcessStructureTree pst = runTest(pathname, basename);
		List<Fragment> frags = pst.getFragments();
		assertEquals(4, frags.size());
		assertFragsContainByName(frags, "f1", "f2");
		assertFragsContainByName(frags, "f2", "f7");
		assertFragsContainByName(frags, "f3", "f4");
		assertFragsContainByName(frags, "f5", "f6");
	}

	public ProcessStructureTree runTest(String pathname, String basename) throws Exception {
		System.out.println("Creating PST for " + basename);
		Definitions definitions = Bpmn2ResourceSet.getInstance()
				.loadDefinitions(
						getClass().getResource(pathname + basename + ".bpmn")
								.getPath());
		PSTBuilder pstBuilder = new PSTBuilder();
		ProcessStructureTree pst = pstBuilder.getTree(definitions);
		pstBuilder.writeDebugFiles("/tmp/" + pathname, basename);
		return pst;
	}

	private void assertFragsContainByName(List<Fragment> frags, String entryId,
			String exitId) {
		boolean contains = false;
		for (Fragment f : frags) {
			if (entryId.equals(f.getEntry().getName())
					&& exitId.equals(f.getExit().getName())) {
				contains = true;
			}
		}
		assertTrue(contains);

	}
}
