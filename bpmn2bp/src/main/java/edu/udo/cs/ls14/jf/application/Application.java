package edu.udo.cs.ls14.jf.application;

import java.io.File;
import java.io.FileOutputStream;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.henshin.interpreter.EGraph;
import org.eclipse.emf.henshin.interpreter.impl.EGraphImpl;
import org.javatuples.Pair;

import edu.udo.cs.ls14.jf.processmatching.ProcessMatching;
import edu.udo.cs.ls14.jf.processmatching.ProcessMatchingChain;
import edu.udo.cs.ls14.jf.pst.Fragment;
import edu.udo.cs.ls14.jf.transformation.CoordinateCalculator;
import edu.udo.cs.ls14.jf.transformation.FragmentComparator;
import edu.udo.cs.ls14.jf.transformation.FragmentExtractor;
import edu.udo.cs.ls14.jf.utils.bpmn.ProcessLoader;

public class Application {

	
	public void matchAndExtract(Pair<String, Resource> model1, Pair<String, Resource> model2, File targetDir) throws Exception {
		
		File process1File = new File(targetDir + "/" + model1.getValue0()
				+ "_transformed.bpmn2");
		File process2File = new File(targetDir + "/" + model2.getValue1()
				+ "_transformed.bpmn2");

		// Determine candidates
		ProcessMatching matching = ProcessMatchingChain.getCandidates(model1.getValue1(),
				model2.getValue1());
		FragmentExtractor extractor = new FragmentExtractor();

		// Loop over all matching pairs
		int i = 1;
		for (Pair<Fragment, Fragment> pair : matching
				.getFragmentCorrespondences()) {
			File newProcessFile = new File(targetDir + "/" + model1.getValue0() + "_"
					+ model2.getValue0() + "extracted_process-" + i++ + ".bpmn2");
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
			Pair<Float, Float> coords1 = CoordinateCalculator.getCoords(model1.getValue1(),
					pair.getValue0());
			EGraph graph1 = new EGraphImpl(model1.getValue1());
			extractor.replaceFragmentByCallActivity(graph1, pair.getValue0(),
					callActivitiyName, coords1);
			model1.getValue1().save(new FileOutputStream(process1File), null);

			// replace fragment in Process2
			Pair<Float, Float> coords2 = CoordinateCalculator.getCoords(model2.getValue1(),
					pair.getValue1());
			EGraph graph2 = new EGraphImpl(model2.getValue1());
			extractor.replaceFragmentByCallActivity(graph2, pair.getValue1(),
					callActivitiyName, coords2);
			model2.getValue1().save(new FileOutputStream(process2File), null);
		}
	}

	private String getCallActivityName(Fragment fragmentToExtract) {
		// TODO: build name
		return "My new Call Activity which potentially could have a very long label with different labels concatenated to describe, which activities are contained in the process called by the call activity";
	}


}
