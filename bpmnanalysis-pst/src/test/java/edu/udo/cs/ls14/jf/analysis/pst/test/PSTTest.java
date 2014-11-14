package edu.udo.cs.ls14.jf.analysis.pst.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.DocumentRoot;
import org.jbpt.utils.IOUtils;
import org.junit.Before;
import org.junit.Test;

import edu.udo.cs.ls14.jf.analysis.pst.PSTBuilder;
import edu.udo.cs.ls14.jf.bpmn.utils.Bpmn2ResourceSet;
import edu.udo.cs.ls14.jf.bpmnanalysis.Fragment;
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessStructureTree;

public class PSTTest {
	private Bpmn2ResourceSet resSet = null;

	@Before
	public void setUp() {
		resSet = new Bpmn2ResourceSet(getClass().getResource(
				"/edu/udo/cs/ls14/jf/bpmn/test/").getPath());
	}

	@Test
	public void testEventBasedGatewayExclusive() throws Exception {
		String basename = "event-based-gateway-exclusive";
		ProcessStructureTree pst = runTest(basename);
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
		String basename = "event-based-gateway-parallel";
		ProcessStructureTree pst = runTest(basename);
		List<Fragment> frags = pst.getFragments();
		assertEquals(3, frags.size());
		assertFragsContainByName(frags, "1", "6");
		assertFragsContainByName(frags, "2", "4");
		assertFragsContainByName(frags, "3", "5");
	}

	@Test
	public void testPM1() throws Exception {
		String basename = "PM1-mit-Fragment1";
		ProcessStructureTree pst = runTest(basename);
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
		String basename = "PM2-mit-Fragment1";
		ProcessStructureTree pst = runTest(basename);
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
		String basename = "sequence";
		ProcessStructureTree pst = runTest(basename);
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
		String basename = "xor-example";
		ProcessStructureTree pst = runTest(basename);
		List<Fragment> frags = pst.getFragments();
		assertEquals(4, frags.size());
		assertFragsContainByName(frags, "1", "2");
		assertFragsContainByName(frags, "2", "7");
		assertFragsContainByName(frags, "3", "5");
		assertFragsContainByName(frags, "4", "6");
	}

	@Test
	public void testOverlapping() throws Exception {
		String basename = "overlapping";
		ProcessStructureTree pst = runTest(basename);
		List<Fragment> frags = pst.getFragments();
		assertEquals(6, frags.size());
		assertFragsContainByName(frags, "1", "7");
		assertFragsContainByName(frags, "3", "5");
		assertFragsContainByName(frags, "4", "6");
		assertFragsContainByName(frags, "7", "8");
		assertFragsContainByName(frags, "9", "10");
		assertFragsContainByName(frags, "8", "12");
	}

	@Test
	public void testLoopingXor() throws Exception {
		String basename = "looping-xor";
		ProcessStructureTree pst = runTest(basename);
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
		String basename = "looping-events-example";
		ProcessStructureTree pst = runTest(basename);
		List<Fragment> frags = pst.getFragments();
		assertEquals(4, frags.size());
		assertFragsContainByName(frags, "f1", "f2");
		assertFragsContainByName(frags, "f2", "f7");
		assertFragsContainByName(frags, "f3", "f4");
		assertFragsContainByName(frags, "f5", "f6");
	}

	public ProcessStructureTree runTest(String basename) throws Exception {
		System.out.println("Creating PST for " + basename);
		Definitions definitions = ((DocumentRoot) resSet
				.loadResource(basename + ".bpmn").getContents().get(0))
				.getDefinitions();
		PSTBuilder pstBuilder = new PSTBuilder();
		ProcessStructureTree pst = pstBuilder.getTree(definitions);
		IOUtils.invokeDOT("/tmp", basename + "-undirectedgraph.png",
				pstBuilder.getGraphAsDot());
		IOUtils.invokeDOT("/tmp", basename + "-spanningtree.png",
				pstBuilder.getSpanningTreeAsDot());
		IOUtils.invokeDOT("/tmp", basename + "-pst.png",
				pstBuilder.getStructureTreeAsDot());
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
