package edu.udo.cs.ls14.jf.pst.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.net.URL;
import java.util.Set;

import org.eclipse.bpmn2.Process;
import org.jbpt.utils.IOUtils;
import org.junit.Test;

import edu.udo.cs.ls14.jf.pst.Fragment;
import edu.udo.cs.ls14.jf.pst.PST;
import edu.udo.cs.ls14.jf.utils.bpmn.ProcessLoader;

public class PSTTest {

	@Test
	public void testPM1() throws Exception {
		String basename = "PM1-mit-Fragment1";
		Set<Fragment> frags = runTest(basename);
		assertEquals(9, frags.size());
		assertTrue(contains(frags, "sid-C6506147-1F35-4235-88C2-9EDFD483F4C6",
				"sid-93B31B1C-6F42-4974-B551-705587AA85D2"));
		assertTrue(contains(frags, "sid-0AD5228D-887A-4D19-87E7-1219EAE576C9",
				"SequenceFlow_4"));

		assertTrue(contains(frags, "sid-0AD5228D-887A-4D19-87E7-1219EAE576C9",
				"SequenceFlow_4"));
		assertTrue(contains(frags, "sid-03B5B5AF-A1F0-4E20-AFB9-38B826AB8D3E",
				"sid-F6EDEAA1-882C-491F-8852-A994BE20ACCB"));
		assertTrue(contains(frags, "sid-16C4A25C-59E5-4B8E-851F-F05BBEE8CE71",
				"sid-0AD5228D-887A-4D19-87E7-1219EAE576C9"));
		assertTrue(contains(frags, "sid-16C4A25C-59E5-4B8E-851F-F05BBEE8CE71",
				"SequenceFlow_4"));
		assertTrue(contains(frags, "sid-EFED9E2B-3B01-44F9-82E6-3727E2B1CA76",
				"sid-C465B7EB-B45B-435F-B4F8-A68E96E38708"));

		assertTrue(contains(frags, "SequenceFlow_3", "SequenceFlow_4"));
		assertTrue(contains(frags, "sid-16C4A25C-59E5-4B8E-851F-F05BBEE8CE71",
				"SequenceFlow_3"));
		assertTrue(contains(frags, "SequenceFlow_3",
				"sid-0AD5228D-887A-4D19-87E7-1219EAE576C9"));

	}

	private boolean contains(Set<Fragment> frags, String entryId, String exitId) {
		for (Fragment f : frags) {
			if (entryId.equals(f.getEntry().getId())
					&& exitId.equals(f.getExit().getId())) {
				return true;
			}
			// TODO: remove this case after order of fragment edges is
			// determined
			if (exitId.equals(f.getEntry().getId())
					&& entryId.equals(f.getExit().getId())) {
				return true;
			}
		}
		return false;
	}

	public Set<Fragment> runTest(String basename) throws Exception {
		System.out.println("Creating PST for " + basename);
		URL url = PSTTest.class.getResource("../../bpmn/" + basename
				+ ".bpmn");
		Process process = ProcessLoader.loadFirstProcessFromResource(url);

		PST pst = new PST();
		Set<Fragment> fragments = pst.createFromProcess(process);
		IOUtils.invokeDOT("/tmp", basename + "-undirectedgraph.png",
				pst.getGraphAsDot());
		IOUtils.invokeDOT("/tmp", basename + "-spanningtree.png",
				pst.getSpanningTreeAsDot());
		System.out.println("The fragments are: ");
		fragments.stream().forEach(
				f -> System.out.println(f.getEntry().getId() + " -> "
						+ f.getExit().getId()));

		return fragments;
	}
}
