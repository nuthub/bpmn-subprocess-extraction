package edu.udo.cs.ls14.jf.application;

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

import edu.udo.cs.ls14.jf.bpmn.utils.ProcessLoader;
import edu.udo.cs.ls14.jf.bpmnanalysis.Fragment;
import edu.udo.cs.ls14.jf.bpmnmatching.FragmentPair;
import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;
import edu.udo.cs.ls14.jf.transformation.FragmentComparatorSize;
import edu.udo.cs.ls14.jf.transformation.FragmentExtractor;
import edu.udo.cs.ls14.jf.transformation.LabelGenerator;
import edu.udo.cs.ls14.jf.transformation.LocationFixer;
import edu.udo.cs.ls14.jf.utils.bpmn.Bpmn2ResourceSet;

/**
 * handles only with definitions
 * 
 * @author flake
 *
 */
public class Application {

	private static final Logger LOG = LoggerFactory
			.getLogger(Application.class);
	private String targetDir = "/tmp/resources/";
	private Bpmn2ResourceSet resourceSet = new Bpmn2ResourceSet(targetDir);

	public Map<String, Resource> extract(ProcessMatching pMatching)
			throws Exception {
		new File(targetDir).mkdirs();

		String filename1 = ProcessLoader.getProcessFromDefinitions(
				pMatching.getAnalysis1().getDefinitions()).getName()
				+ "_transformed.bpmn";
		String filename2 = ProcessLoader.getProcessFromDefinitions(
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
			// 1. Determine "better" fragment
			Fragment fragmentToExtract = FragmentComparatorSize
					.getBetter(fPair);
			Process process1 = ProcessLoader.getProcessFromDefinitions(fPair
					.getA().getDefinitions());
			Process process2 = ProcessLoader.getProcessFromDefinitions(fPair
					.getB().getDefinitions());

			// create new (sub)process
			String idExtracted = process1.getName() + "_" + process2.getName()
					+ "_extracted_process-" + fragmentCounter++;
			String filenameExtracted = idExtracted + ".bpmn";
			Definitions defsExtracted = EcoreUtil.copy(fragmentToExtract
					.getDefinitions());
			Resource resExtracted = resourceSet.createResource(
					filenameExtracted, defsExtracted);
			defsExtracted.setTargetNamespace("http://bla");
			defsExtracted.setTargetNamespace(resExtracted.getURI().toString()
					.toLowerCase());

			extractor.cropFragment(resExtracted, defsExtracted,
					fragmentToExtract);
			extractor.replaceId(resExtracted, ProcessLoader
					.getProcessFromDefinitions(defsExtracted).getId(),
					EcoreUtil.generateUUID());
			results.put(filenameExtracted, resExtracted);
			resExtracted.save(null);

			// callActivity parameters
			String callActivityName = LabelGenerator
					.getLabel(fragmentToExtract);
			Process calledElement = ProcessLoader
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

	/*
	 * public void matchAndExtract(Pair<String, Resource> model1, Pair<String,
	 * Resource> model2, File targetDir) throws Exception {
	 * 
	 * Resource res1 = ResourceCopier.copy(model1.getValue1(), new
	 * File(targetDir, "/" + model1.getValue0() +
	 * "_transformed.bpmn2").toString()); Resource res2 =
	 * ResourceCopier.copy(model2.getValue1(), new File(targetDir, "/" +
	 * model2.getValue0() + "_transformed.bpmn2").toString());
	 * 
	 * // Determine candidates ProcessMatching matching =
	 * ProcessMatchingChain.createProcessMatching( model1.getValue1(),
	 * model2.getValue1()); FragmentExtractor extractor = new
	 * FragmentExtractor();
	 * 
	 * // Loop over all matching pairs int processCounter = 1; for (FragmentPair
	 * pair : matching.getFragmentMatching().getPairs()) { // select fragment to
	 * extract Fragment fragmentToExtract =
	 * FragmentComparatorSize.getBetter(pair); // copy resource: Resource is not
	 * copied! String targetFilename = model1.getValue0() + "_" +
	 * model2.getValue0() + "_extracted_process-" + processCounter++ + ".bpmn2";
	 * Definitions definitions = fragmentToExtract.getDefinitions(); Definitions
	 * newDefinitions = EcoreUtil.copy(definitions); Bpmn2ResourceFactoryImpl
	 * resFactory = new Bpmn2ResourceFactoryImpl(); Resource newResource =
	 * resFactory.createResource(URI .createURI(targetDir + "/" +
	 * targetFilename));
	 * newResource.getContents().addAll(newDefinitions.eContents());
	 * System.out.println(newResource); newResource.save(null); // Resource
	 * newResource = ResourceCopier.copy( // fragmentToExtract.getResource(),
	 * new File(targetDir, "/" // + targetFilename).toString()); // // // create
	 * new subprocess // LOG.info("Creating process from " + fragmentToExtract +
	 * " in " // + newResource.getURI()); //
	 * extractor.createProcessFromFragment(newResource, // fragmentToExtract);
	 * // newResource.save(null); // LOG.info("Created process from " +
	 * fragmentToExtract + " in " // + newResource.getURI()); // // // replace
	 * fragments by call activities // String callActivityName =
	 * getCallActivityName(fragmentToExtract); // Process calledElement =
	 * ProcessLoader // .getProcessFromResource(newResource); // // // replace
	 * fragment in Process1 // LOG.info("Replacing " + pair.getA() + " in " +
	 * res1.getURI()); // extractor.replaceFragmentByCallActivity(res1,
	 * pair.getA(), // callActivityName, calledElement); // LOG.info("Fixing " +
	 * res1.getURI()); // doFixes(res1, calledElement); // res1.save(null); //
	 * LOG.info("Replaced " + pair.getA() + " in " + res1.getURI()); // // //
	 * replace fragment in Process2 // LOG.info("Replacing " + pair.getB() +
	 * " in " + res2.getURI()); // extractor.replaceFragmentByCallActivity(res2,
	 * pair.getB(), // callActivityName, calledElement); // LOG.info("Fixing " +
	 * res1.getURI()); // doFixes(res2, calledElement); // res2.save(null); //
	 * LOG.info("Replaced " + pair.getB() + " in " + res2.getURI()); } }
	 */

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
