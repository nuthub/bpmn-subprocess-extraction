package edu.udo.cs.ls14.jf.bpmnanalysis.ws.impl;

import javax.jws.WebService;

import org.eclipse.bpmn2.Process;

import edu.udo.cs.ls14.jf.bpmn.util.DefinitionsUtil;
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis;
import edu.udo.cs.ls14.jf.bpmnanalysis.conditionalprofile.ConditionalProfiler;
import edu.udo.cs.ls14.jf.bpmnanalysis.util.ProcessAnalysisUtil;
import edu.udo.cs.ls14.jf.bpmnanalysis.ws.ConditionalProfilerSEI;

/**
 * Implementation of ConditionalProfilerSEI Service endpoint interface.
 * 
 * @author Julian Flake
 *
 */
@WebService(endpointInterface = "edu.udo.cs.ls14.jf.bpmnanalysis.ws.ConditionalProfilerSEI")
public class ConditionalProfilerImpl implements ConditionalProfilerSEI {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ProcessAnalysis profile(ProcessAnalysis processAnalysis) {
		try {
			Process process = DefinitionsUtil.getProcess(processAnalysis
					.getDefinitions());
			processAnalysis.getResults().put(
					ProcessAnalysisUtil.CONDITIONALPROFILE,
					ConditionalProfiler.generateProfile(process));
			return processAnalysis;
		} catch (Exception e) {
			return null;
		}
	}

}
