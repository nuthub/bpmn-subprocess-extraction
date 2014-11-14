package edu.udo.cs.ls14.jf.bpmnapplication;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.Process;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.udo.cs.ls14.jf.bpmn.utils.Bpmn2ResourceSet;
import edu.udo.cs.ls14.jf.bpmn.utils.ProcessUtil;
import edu.udo.cs.ls14.jf.bpmnmatching.FragmentPair;
import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;
import edu.udo.cs.ls14.jf.transformation.FragmentExtractor;
import edu.udo.cs.ls14.jf.transformation.LabelGenerator;
import edu.udo.cs.ls14.jf.transformation.LocationFixer;

/**
 * handles only with definitions
 * 
 * @author flake
 *
 */
public class ProcessExtraction {

	private static final Logger LOG = LoggerFactory
			.getLogger(ProcessExtraction.class);
	private String targetDir = "/tmp/resources/";
	private Bpmn2ResourceSet resourceSet = new Bpmn2ResourceSet(targetDir);

	public Map<String, Resource> extract(ProcessMatching pMatching)
			throws Exception {
		new File(targetDir).mkdirs();

		String filename1 = ProcessUtil.getProcessFromDefinitions(
				pMatching.getAnalysis1().getDefinitions()).getName()
				+ "_transformed.bpmn";
		String filename2 = ProcessUtil.getProcessFromDefinitions(
				pMatching.getAnalysis2().getDefinitions()).getName()
				+ "_transformed.bpmn";

		Resource res1 = resourceSet.createResource(filename1, pMatching
				.getAnalysis1().getDefinitions());
		Resource res2 = resourceSet.createResource(filename2, pMatching
				.getAnalysis2().getDefinitions());
		Map<String, Resource> results = new HashMap<String, Resource>();
		results.put(filename1, res1);
		results.put(filename2, res2);

		FragmentExtractor extractor = new FragmentExtractor();
		// Loop over all fragment matchings
		int fragmentCounter = 1;
		for (FragmentPair fPair : pMatching.getFragmentMatching().getPairs()) {
			// create new (sub)process
			String idExtracted = getExtractedProcessIdPrefix(fPair) + fragmentCounter++;
			Definitions defsExtracted = EcoreUtil.copy(fPair.getBetter()
					.getDefinitions());
			Resource resExtracted = resourceSet.createResource(idExtracted
					+ ".bpmn", defsExtracted);
			defsExtracted.setTargetNamespace(resExtracted.getURI().toString()
					.toLowerCase());

			extractor.cropFragment(resExtracted, defsExtracted,
					fPair.getBetter());
			extractor.replaceId(resExtracted, ProcessUtil
					.getProcessFromDefinitions(defsExtracted).getId(),
					EcoreUtil.generateUUID());
			results.put(idExtracted + ".bpmn", resExtracted);
			resExtracted.save(null);

			// callActivity parameters
			String callActivityName = LabelGenerator
					.getLabel(fPair.getBetter());
			Process calledElement = ProcessUtil
					.getProcessFromDefinitions(defsExtracted);
			System.out.println(calledElement.getId());

			// replace fragment in Process1
			LOG.info("Replacing " + fPair.getA());
			extractor.replaceFragmentByCallActivity(res1, fPair.getA(),
					callActivityName, calledElement);

			LOG.info("Replaced " + fPair.getA());

			// replace fragment in Process2
			LOG.info("Replacing " + fPair.getB());
			extractor.replaceFragmentByCallActivity(res2, fPair.getB(),
					callActivityName, calledElement);
			LOG.info("Replaced " + fPair.getB());

			// Fix imports
			LOG.info("Fixing imports (" + res1.getURI() + " / " + res2.getURI()
					+ ")");
			res1.save(null);
			doFixes(res1, calledElement);
			res2.save(null);
			doFixes(res2, calledElement);
			LOG.info("Imports fixed.");
		}

		for (Entry<String, Resource> res : results.entrySet()) {
			System.out.println("saving " + res.getValue().getURI() + " to "
					+ res.getKey());
			res.getValue().save(null);
		}
		return results;
	}

	private String getExtractedProcessIdPrefix(FragmentPair fPair)
			throws Exception {
		return ProcessUtil.getProcessFromDefinitions(
				fPair.getA().getDefinitions()).getName()
				+ "_"
				+ ProcessUtil.getProcessFromDefinitions(
						fPair.getB().getDefinitions()).getName()
				+ "_extracted_process-";
	}

	public Bpmn2ResourceSet getResourceSet() {
		return resourceSet;
	}

	private void doFixes(Resource resource, Process calledElement)
			throws Exception {
		// fix location of import to absolute URI
		System.out.println(resource.getContents().get(0));
		LocationFixer fixer = new LocationFixer();
		fixer.fixLocations(resource, new File((String) calledElement
				.eResource().getURI().toString()).getName(), calledElement
				.eResource().getURI().toString());

	}

}
