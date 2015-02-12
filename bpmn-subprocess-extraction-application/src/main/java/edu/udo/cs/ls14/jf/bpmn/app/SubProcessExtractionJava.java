package edu.udo.cs.ls14.jf.bpmn.app;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.bpmn2.Activity;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.udo.cs.ls14.jf.bpmn.util.Bpmn2ResourceSet;
import edu.udo.cs.ls14.jf.bpmn.util.DefinitionsUtil;
import edu.udo.cs.ls14.jf.bpmn.util.Registries;
import edu.udo.cs.ls14.jf.bpmnanalysis.BpmnAnalysisPackage;
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis;
import edu.udo.cs.ls14.jf.bpmnanalysis.IProcessAnalyzer;
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalyzerImpl;
import edu.udo.cs.ls14.jf.bpmnanalysis.util.ProcessAnalysisUtil;
import edu.udo.cs.ls14.jf.bpmnmatching.BpmnMatchingPackage;
import edu.udo.cs.ls14.jf.bpmnmatching.IProcessMatcher;
import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatcherImpl;
import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;
import edu.udo.cs.ls14.jf.bpmnmatching.util.ProcessMatchingUtil;
import edu.udo.cs.ls14.jf.bpmntransformation.BpmnTransformationPackage;
import edu.udo.cs.ls14.jf.bpmntransformation.ProcessTransformation;
import edu.udo.cs.ls14.jf.bpmntransformation.IProcessTransformer;
import edu.udo.cs.ls14.jf.bpmntransformation.ProcessTransformerImpl;
import edu.udo.cs.ls14.jf.bpmntransformation.util.ProcessTransformationUtil;

/**
 * Pure java implementation of interfaces ProcessAnalyzer, ProcessMatcher and
 * ProcessTransformer.
 * 
 * @author Julian Flake
 *
 */
public class SubProcessExtractionJava implements
		ISubProcessExtractionProfiling, ISubProcessExtractionDebug {

	private boolean initialized = false;
	private static final Logger LOGGER = LoggerFactory
			.getLogger(SubProcessExtractionJava.class.getName());

	/**
	 * {@inheritDoc}
	 * 
	 * @see edu.udo.cs.ls14.jf.bpmn.app.ISubProcessExtraction#init()
	 */
	@Override
	public void init() {
		Registries.registerAll();
		initialized = true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * edu.udo.cs.ls14.jf.bpmn.app.SubProcessExtraction#run(org.eclipse.bpmn2
	 * .Definitions, org.eclipse.bpmn2.Definitions)
	 */
	@Override
	public ProcessTransformation runAndProfile(Definitions definitions1,
			Definitions definitions2) throws Exception {

		if (!initialized) {
			init();
		}

		IProcessAnalyzer analyzer = new ProcessAnalyzerImpl();
		long timeAnalysis1Start = System.currentTimeMillis();
		ProcessAnalysis analysis1 = analyzer.analyze(definitions1);
		long timeAnalysis1End = System.currentTimeMillis();
		long timeAnalysis2Start = System.currentTimeMillis();
		ProcessAnalysis analysis2 = analyzer.analyze(definitions2);
		long timeAnalysis2End = System.currentTimeMillis();

		IProcessMatcher matcher = new ProcessMatcherImpl();
		long timeMatchingStart = System.currentTimeMillis();
		ProcessMatching matching = matcher.match(analysis1, analysis2);
		long timeMatchingEnd = System.currentTimeMillis();

		IProcessTransformer transformer = new ProcessTransformerImpl();
		long timeTransformationStart = System.currentTimeMillis();
		ProcessTransformation transformation = transformer.transform(matching);
		long timeTransformationEnd = System.currentTimeMillis();

		LOGGER.info("Time for Analyzing Model 1       : "
				+ (timeAnalysis1End - timeAnalysis1Start) + "ms");
		LOGGER.info("Time for Analyzing Model 2       : "
				+ (timeAnalysis2End - timeAnalysis2Start) + "ms");
		LOGGER.info("Time for Matching Models         : "
				+ (timeMatchingEnd - timeMatchingStart) + "ms");
		LOGGER.info("Time for Transformation of Models: "
				+ (timeTransformationEnd - timeTransformationStart) + "ms");

		return transformation;
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
	public ProcessTransformation runDebug(String filename1, String filename2,
			String targetDir) throws Exception {
		if (!initialized) {
			init();
		}
		File file1 = new File(filename1);
		File file2 = new File(filename2);
		Definitions def1 = Bpmn2ResourceSet.getInstance().loadDefinitions(
				file1.toString());
		Definitions def2 = Bpmn2ResourceSet.getInstance().loadDefinitions(
				file2.toString());

		IProcessAnalyzer analyzer = new ProcessAnalyzerImpl();
		// 1a. analyze model1
		List<String> nodes1 = DefinitionsUtil.getProcess(def1)
				.getFlowElements().stream()
				.filter(e -> e instanceof Event || e instanceof Activity)
				.map(ae -> ae.getName()).collect(Collectors.toList());
		ProcessAnalysis analysis1 = analyzer.analyzeAndDebug(def1,
				dirname(file1), basename(file1), targetDir, nodes1);
		ProcessAnalysisUtil.writeToFile(targetDir + basename(file1) + "."
				+ BpmnAnalysisPackage.eNAME, analysis1);

		// 1b. analyze model2
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
		IProcessMatcher matcher = new ProcessMatcherImpl();
		ProcessMatching matching = matcher.match(analysis1, analysis2);
		ProcessMatchingUtil.writeToFile(targetDir + basename(file1) + "_"
				+ basename(file2) + "_matching." + BpmnMatchingPackage.eNAME,
				matching);

		// 3. transform models
		IProcessTransformer transformer = new ProcessTransformerImpl();
		ProcessTransformation transformation = transformer.transform(matching);
		ProcessTransformationUtil.writeToFile(targetDir + basename(file1) + "_"
				+ basename(file2) + "." + BpmnTransformationPackage.eNAME,
				transformation);
		ProcessTransformationUtil.writeResults(targetDir, transformation);
		return transformation;
	}

	private String basename(File file) {
		return file.getName();
	}

	private String dirname(File file) {
		return file.getParent();
	}

}
