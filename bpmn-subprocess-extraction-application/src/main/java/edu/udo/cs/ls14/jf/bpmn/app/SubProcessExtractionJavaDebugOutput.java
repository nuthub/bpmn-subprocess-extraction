package edu.udo.cs.ls14.jf.bpmn.app;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.bpmn2.Activity;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.Event;

import edu.udo.cs.ls14.jf.bpmn.registry.Registries;
import edu.udo.cs.ls14.jf.bpmn.resourceset.Bpmn2ResourceSet;
import edu.udo.cs.ls14.jf.bpmn.transformation.ProcessTransformer;
import edu.udo.cs.ls14.jf.bpmn.transformation.ProcessTransformerImpl;
import edu.udo.cs.ls14.jf.bpmn.util.DefinitionsUtil;
import edu.udo.cs.ls14.jf.bpmnanalysis.BpmnAnalysisPackage;
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis;
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalyzer;
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalyzerImpl;
import edu.udo.cs.ls14.jf.bpmnanalysis.util.ProcessAnalysisUtil;
import edu.udo.cs.ls14.jf.bpmnmatching.BpmnMatchingPackage;
import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatcher;
import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatcherImpl;
import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;
import edu.udo.cs.ls14.jf.bpmnmatching.util.ProcessMatchingUtil;
import edu.udo.cs.ls14.jf.bpmntransformation.BpmnTransformationPackage;
import edu.udo.cs.ls14.jf.bpmntransformation.ProcessTransformation;
import edu.udo.cs.ls14.jf.bpmntransformation.util.ProcessTransformationUtil;

/**
 * Pure java implementation of subprocess extraction with artifact export. For
 * debug and testing purposes only.
 * 
 * @author Julian Flake
 *
 */
public class SubProcessExtractionJavaDebugOutput implements
		SubProcessExtraction {

	private boolean initialized = false;

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.udo.cs.ls14.jf.bpmn.app.SubProcessExtraction#init()
	 */
	@Override
	public void init() {
		Registries.registerAll();
		initialized = true;
	}

	/**
	 * Does nothing, use runDebug in this class instead
	 */
	@Override
	public ProcessTransformation run(Definitions defs1, Definitions defs2)
			throws Exception {
		System.err.println("use runDebug(...)");
		return null;
	}

	/**
	 * Runs the java based application and writes results and analysis
	 * artifacts.
	 * 
	 * @param filename1
	 *            filename of model1
	 * @param filename2
	 *            filename of model2
	 * @param targetDir
	 *            output directory
	 * @throws Exception
	 *             if an error occurs
	 */
	public void runDebug(String filename1, String filename2, String targetDir)
			throws Exception {
		if (!initialized) {
			init();
		}
		File file1 = new File(filename1);
		File file2 = new File(filename2);
		ProcessAnalyzer analyzer = new ProcessAnalyzerImpl();
		// 1a. analyze model1
		Definitions def1 = Bpmn2ResourceSet.getInstance().loadDefinitions(
				file1.toString());
		List<String> nodes1 = DefinitionsUtil.getProcess(def1)
				.getFlowElements().stream()
				.filter(e -> e instanceof Event || e instanceof Activity)
				.map(ae -> ae.getName()).collect(Collectors.toList());
		ProcessAnalysis analysis1 = analyzer.analyzeAndDebug(def1,
				dirname(file1), basename(file1), targetDir, nodes1);
		ProcessAnalysisUtil.writeToFile(targetDir + basename(file1) + "."
				+ BpmnAnalysisPackage.eNAME, analysis1);

		// 1b. analyze model2
		Definitions def2 = Bpmn2ResourceSet.getInstance().loadDefinitions(
				file2.toString());
		List<String> nodes2 = DefinitionsUtil.getProcess(def2)
				.getFlowElements().stream()
				.filter(e -> e instanceof Event || e instanceof Activity)
				.map(ae -> ae.getName()).collect(Collectors.toList());
		analyzer = new ProcessAnalyzerImpl();
		ProcessAnalysis analysis2 = analyzer.analyzeAndDebug(def2,
				dirname(file2), basename(file2), targetDir, nodes2);
		ProcessAnalysisUtil.writeToFile(targetDir + basename(file2) + "."
				+ BpmnAnalysisPackage.eNAME, analysis2);

		// 2. match models
		ProcessMatcher matcher = new ProcessMatcherImpl();
		ProcessMatching matching = matcher.match(analysis1, analysis2);
		ProcessMatchingUtil.writeToFile(targetDir + basename(file1) + "_"
				+ basename(file2) + "_matching." + BpmnMatchingPackage.eNAME,
				matching);

		// 3. transform models
		ProcessTransformer transformer = new ProcessTransformerImpl();
		ProcessTransformation transformation = transformer.transform(matching);
		ProcessTransformationUtil.writeToFile(targetDir + basename(file1) + "_"
				+ basename(file2) + "." + BpmnTransformationPackage.eNAME,
				transformation);
		ProcessTransformationUtil.writeResults(targetDir, transformation);

	}

	private String basename(File file) {
		return file.getName();
	}

	private String dirname(File file) {
		return file.getParent();
	}
}
