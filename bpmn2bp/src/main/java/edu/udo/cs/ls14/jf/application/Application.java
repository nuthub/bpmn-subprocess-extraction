package edu.udo.cs.ls14.jf.application;

import java.io.File;

import org.eclipse.bpmn2.Process;
import org.eclipse.emf.ecore.resource.Resource;
import org.javatuples.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.udo.cs.ls14.jf.analysis.pst.Fragment;
import edu.udo.cs.ls14.jf.processmatching.ProcessMatching;
import edu.udo.cs.ls14.jf.processmatching.ProcessMatchingChain;
import edu.udo.cs.ls14.jf.transformation.FragmentComparator;
import edu.udo.cs.ls14.jf.transformation.FragmentExtractor;
import edu.udo.cs.ls14.jf.transformation.LabelGenerator;
import edu.udo.cs.ls14.jf.transformation.LocationFixer;
import edu.udo.cs.ls14.jf.utils.bpmn.ProcessLoader;
import edu.udo.cs.ls14.jf.utils.bpmn.ResourceCopier;

public class Application {

	private static final Logger LOG = LoggerFactory.getLogger(Application.class);
	
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
			String targetFilename = model1.getValue0() + "_"
					+ model2.getValue0() + "_extracted_process-"
					+ processCounter++ + ".bpmn2";
			Resource newResource = ResourceCopier.copy(
					fragmentToExtract.getResource(), new File(targetDir, "/"
							+ targetFilename).toString());

			// create new subprocess
			LOG.info("Creating process from " + fragmentToExtract + " in " + newResource.getURI());
			extractor.createProcessFromFragment(newResource, fragmentToExtract);
			newResource.save(null);
			LOG.info("Created process from " + fragmentToExtract + " in " + newResource.getURI());
			// replace fragments by call activities
			String callActivityName = getCallActivityName(fragmentToExtract);
			Process calledElement = ProcessLoader
					.getProcessFromResource(newResource);
			// replace fragment in Process1
			LOG.info("Replacing " + pair.getValue0() + " in " + res1.getURI());
			extractor.replaceFragmentByCallActivity(res1, pair.getValue0(),
					callActivityName, calledElement);
			LOG.info("Fixing " + res1.getURI());
			doFixes(res1, calledElement);
			res1.save(null);
			LOG.info("Replaced " + pair.getValue0() + " in " + res1.getURI());

			// replace fragment in Process2
			LOG.info("Replacing " + pair.getValue1() + " in " + res2.getURI());
			extractor.replaceFragmentByCallActivity(res2, pair.getValue1(),
					callActivityName, calledElement);
			LOG.info("Fixing " + res1.getURI());
			doFixes(res2, calledElement);
			res2.save(null);
			LOG.info("Replaced " + pair.getValue1() + " in " + res2.getURI());
		}
	}

	private void doFixes(Resource resource, Process calledElement) throws Exception {
		resource.save(null);
		String newLocation = calledElement.eResource().getURI().toString();
		String oldLocation = new File(newLocation).getName();
		LocationFixer fixer = new LocationFixer();
		fixer.fixLocations(resource, oldLocation, newLocation);
	}

	private String getCallActivityName(Fragment fragment) throws Exception {
		return LabelGenerator.getLabel(fragment);
	}

}
