package edu.udo.cs.ls14.jf.transformation.test;

import java.io.File;
import java.io.FileOutputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.henshin.interpreter.EGraph;
import org.eclipse.emf.henshin.interpreter.impl.EGraphImpl;
import org.javatuples.Pair;
import org.junit.Before;
import org.junit.Test;

import edu.udo.cs.ls14.jf.processmatching.ProcessMatching;
import edu.udo.cs.ls14.jf.processmatching.ProcessMatchingChain;
import edu.udo.cs.ls14.jf.pst.Fragment;
import edu.udo.cs.ls14.jf.transformation.CoordinateCalculator;
import edu.udo.cs.ls14.jf.transformation.FragmentComparator;
import edu.udo.cs.ls14.jf.transformation.FragmentExtractor;
import edu.udo.cs.ls14.jf.utils.bpmn.ProcessLoader;

public class TransformationTest {

	private File targetDir = null;

	@Before
	public void setUp() throws MalformedURLException {
		URL targetUrl = new URL(getClass().getResource("/")
				+ "../generated-models/");
		targetDir = new File(targetUrl.getFile());
		targetDir.mkdir();
	}

	@Test
	public void testSequenceSequence2() throws Exception {
		String basename1 = "sequence";
		String basename2 = "sequence2";
		runTest(basename1, basename2);
	}

	@Test
	public void testPM1PM2() throws Exception {
		String basename1 = "PM1-mit-Fragment1";
		String basename2 = "PM2-mit-Fragment1";
		runTest(basename1, basename2);
	}

	private void runTest(String basename1, String basename2) throws Exception {
		Resource res1 = ProcessLoader.getBpmnResource(getClass().getResource(
				"../../bpmn/" + basename1 + ".bpmn"));
		Resource res2 = ProcessLoader.getBpmnResource(getClass().getResource(
				"../../bpmn/" + basename2 + ".bpmn"));
		File process1File = new File(targetDir + "/" + basename1
				+ "_transformed.bpmn2");
		File process2File = new File(targetDir + "/" + basename2
				+ "_transformed.bpmn2");

		// Determine candidates
		ProcessMatching matching = ProcessMatchingChain.getCandidates(res1,
				res2);
		FragmentExtractor extractor = new FragmentExtractor();

		// Loop over all matching pairs
		int i = 1;
		for (Pair<Fragment, Fragment> pair : matching
				.getFragmentCorrespondences()) {
			File newProcessFile = new File(targetDir + "/" + basename1 + "_"
					+ basename2 + "extracted_process-" + i++ + ".bpmn2");
			// select fragment to extract
			Fragment fragmentToExtract = FragmentComparator.getBetter(pair);
			// copy resource
			fragmentToExtract.getResource().save(
					new FileOutputStream(newProcessFile), null);
			Resource newResource = ProcessLoader
					.getBpmnResource(newProcessFile);
			EGraph newGraph = new EGraphImpl(newResource);
			Pair<Float, Float> startEventCoords = CoordinateCalculator
					.getCoords(newResource, fragmentToExtract.getEntry()
							.getSourceRef());
			Pair<Float, Float> endEventCoords = CoordinateCalculator.getCoords(
					newResource, fragmentToExtract.getExit().getTargetRef());
			extractor.createProcessFromFragment(newGraph, fragmentToExtract,
					"My new StartEvent", startEventCoords, "My new EndEvent",
					endEventCoords);
			newResource.save(null);

			// replace fragments by call activity
			String callActivitiyName = getCallActivityName(fragmentToExtract);

			// replace fragment in Process1
			Pair<Float, Float> coords1 = CoordinateCalculator.getCoords(res1,
					pair.getValue0());
			EGraph graph1 = new EGraphImpl(res1);
			extractor.replaceFragmentByCallActivity(graph1, pair.getValue0(),
					callActivitiyName, coords1);
			res1.save(new FileOutputStream(process1File), null);

			// replace fragment in Process2
			Pair<Float, Float> coords2 = CoordinateCalculator.getCoords(res2,
					pair.getValue1());
			EGraph graph2 = new EGraphImpl(res2);
			extractor.replaceFragmentByCallActivity(graph2, pair.getValue1(),
					callActivitiyName, coords2);
			res2.save(new FileOutputStream(process2File), null);
		}
	}

	private String getCallActivityName(Fragment fragmentToExtract) {
		// TODO: build name
		return "My new Call Activity which potentially could have a very long label with different labels concatenated to describe, which activities are contained in the process called by the call activity";
	}

}
