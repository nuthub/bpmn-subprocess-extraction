package edu.udo.cs.ls14.jf.bpmn.utils;

import java.util.UUID;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.emf.common.util.URI;

import edu.udo.cs.ls14.jf.bpmnanalysis.BpmnAnalysisFactory;
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis;
import edu.udo.cs.ls14.jf.bpmnanalysis.util.BpmnAnalysisResourceFactoryImpl;

/**
 * Factory method for ProcessAnalysis objects.
 * 
 * @author Julian Flake
 *
 */
public class ProcessAnalysisFactory {

	/**
	 * Create a new ProcessAnalysis object with Definitions added.
	 * 
	 * @param definitions
	 *            Definitions to add
	 * @return non-empty ProcessAnalysis object
	 */
	public static ProcessAnalysis createAnalysis(Definitions definitions) {
		ProcessAnalysis analysis = BpmnAnalysisFactory.eINSTANCE
				.createProcessAnalysis();
		new BpmnAnalysisResourceFactoryImpl()
				.createResource(
						URI.createFileURI(UUID.randomUUID().toString()
								+ ".bpmnanalysis")).getContents().add(analysis);
		analysis.setDefinitions(DefinitionsUtil.copy(definitions));
		return analysis;
	}
}
