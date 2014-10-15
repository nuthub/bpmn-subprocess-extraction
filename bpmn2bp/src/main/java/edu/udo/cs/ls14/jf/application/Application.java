package edu.udo.cs.ls14.jf.application;

import java.io.File;

import org.eclipse.bpmn2.Process;
import org.eclipse.emf.ecore.resource.Resource;
import org.javatuples.Pair;

import edu.udo.cs.ls14.jf.processmatching.ProcessMatching;
import edu.udo.cs.ls14.jf.processmatching.ProcessMatchingChain;
import edu.udo.cs.ls14.jf.pst.Fragment;
import edu.udo.cs.ls14.jf.transformation.FragmentComparator;
import edu.udo.cs.ls14.jf.transformation.FragmentExtractor;
import edu.udo.cs.ls14.jf.transformation.LabelGenerator;
import edu.udo.cs.ls14.jf.utils.bpmn.ProcessLoader;
import edu.udo.cs.ls14.jf.utils.bpmn.ResourceCopier;

public class Application {

	public void matchAndExtract(Pair<String, Resource> model1,
			Pair<String, Resource> model2, File targetDir) throws Exception {

		Resource res1 = ResourceCopier.copy(model1.getValue1(),
				new File(targetDir, "/" + model1.getValue0()
						+ "_transformed.bpmn2").toString());
		Resource res2 = ResourceCopier.copy(model2.getValue1(),
				new File(targetDir, "/" + model2.getValue0()
						+ "_transformed.bpmn2").toString());

		// Determine candidates
		ProcessMatching matching = ProcessMatchingChain.getCandidates(
				model1.getValue1(), model2.getValue1());
		FragmentExtractor extractor = new FragmentExtractor();

		// Loop over all matching pairs
		int processCounter = 1;
		for (Pair<Fragment, Fragment> pair : matching
				.getFragmentCorrespondences()) {
			// select fragment to extract
			Fragment fragmentToExtract = FragmentComparator.getBetter(pair);
			// copy resource: Resource is not copied!
			Resource newResource = ResourceCopier.copy(
					fragmentToExtract.getResource(), new File(targetDir, "/"
							+ model1.getValue0() + "_" + model2.getValue0()
							+ "_extracted_process-" + processCounter++
							+ ".bpmn2").toString());

			// create new subprocess
			extractor.createProcessFromFragment(newResource, fragmentToExtract);
			newResource.save(null);
			// replace fragments by call activities
			String callActivityName = getCallActivityName(fragmentToExtract);
			Process calledElement = ProcessLoader
					.getProcessFromResource(newResource);
			// replace fragment in Process1
			extractor.replaceFragmentByCallActivity(res1, pair.getValue0(),
					callActivityName, calledElement);
			// replace fragment in Process2
			extractor.replaceFragmentByCallActivity(res2, pair.getValue1(),
					callActivityName, calledElement);
			System.out.println(calledElement.eResource());
		}
		res1.save(null);
		res2.save(null);
	}

	private String getCallActivityName(Fragment fragment) throws Exception {
		return LabelGenerator.getLabel(fragment);
	}

}
