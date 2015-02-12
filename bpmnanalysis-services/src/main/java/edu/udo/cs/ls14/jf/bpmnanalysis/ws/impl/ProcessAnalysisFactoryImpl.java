package edu.udo.cs.ls14.jf.bpmnanalysis.ws.impl;

import javax.jws.WebService;

import org.eclipse.bpmn2.Definitions;

import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis;
import edu.udo.cs.ls14.jf.bpmnanalysis.util.ProcessAnalysisFactory;
import edu.udo.cs.ls14.jf.bpmnanalysis.ws.ProcessAnalysisFactorySEI;

/**
 * Implementation of ProcessAnalysisFactorySEI Service endpoint interface.
 * 
 * @author Julian Flake
 *
 */
@WebService(endpointInterface = "edu.udo.cs.ls14.jf.bpmnanalysis.ws.ProcessAnalysisFactorySEI")
public class ProcessAnalysisFactoryImpl implements ProcessAnalysisFactorySEI {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ProcessAnalysis createProcessAnalysis(Definitions definitions) {
		return ProcessAnalysisFactory.createAnalysis(definitions);
	}

}
