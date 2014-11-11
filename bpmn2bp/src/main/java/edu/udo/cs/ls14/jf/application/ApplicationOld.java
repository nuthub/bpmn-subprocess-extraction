package edu.udo.cs.ls14.jf.application;

import java.io.File;

import org.eclipse.bpmn2.Process;
import org.eclipse.emf.ecore.resource.Resource;
import org.javatuples.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.udo.cs.ls14.jf.analysis.pst.FragmentOld;
import edu.udo.cs.ls14.jf.bpmn.utils.ProcessLoader;
import edu.udo.cs.ls14.jf.processmatching.ProcessMatchingOld;
import edu.udo.cs.ls14.jf.processmatching.ProcessMatchingChain;
import edu.udo.cs.ls14.jf.transformation.FragmentComparatorOld;
import edu.udo.cs.ls14.jf.transformation.FragmentExtractorOld;
import edu.udo.cs.ls14.jf.transformation.LabelGeneratorOld;
import edu.udo.cs.ls14.jf.transformation.LocationFixer;
import edu.udo.cs.ls14.jf.utils.bpmn.ResourceCopier;

@Deprecated
public class ApplicationOld {

	private static final Logger LOG = LoggerFactory.getLogger(ApplicationOld.class);
	/*
	public void matchAndExtract(Pair<String, Resource> model1,
			Pair<String, Resource> model2, File targetDir) throws Exception {

		Resource res1 = ResourceCopier.copy(model1.getValue1(),
				new File(targetDir, "/" + model1.getValue0()
						+ "_transformed.bpmn2").toString());
		Resource res2 = ResourceCopier.copy(model2.getValue1(),
				new File(targetDir, "/" + model2.getValue0()
						+ "_transformed.bpmn2").toString());

		// Determine candidates
		ProcessMatchingOld matching = ProcessMatchingChain.getCandidatesOld(
				model1.getValue1(), model2.getValue1());
		FragmentExtractorOld extractor = new FragmentExtractorOld();

		// Loop over all matching pairs
		int processCounter = 1;
		for (Pair<FragmentOld, FragmentOld> pair : matching
				.getFragmentCorrespondencesOld()) {
			// select fragment to extract
			FragmentOld fragmentToExtract = FragmentComparatorOld.getBetter(pair);
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

	private String getCallActivityName(FragmentOld fragment) throws Exception {
		return LabelGeneratorOld.getLabel(fragment);
	}

*/
	}
