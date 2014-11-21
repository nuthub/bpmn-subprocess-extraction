package edu.udo.cs.ls14.jf.ws.bpmnanalysis.factory;

import javax.jws.WebService;

import org.eclipse.bpmn2.Definitions;

import edu.udo.cs.ls14.jf.bpmn.utils.ProcessAnalysisFactory;
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis;

@WebService(endpointInterface = "edu.udo.cs.ls14.jf.ws.bpmnanalysis.factory.ProcessAnalysisFactorySEI")
public class ProcessAnalysisFactoryImpl implements ProcessAnalysisFactorySEI {

	@Override
	public ProcessAnalysis createProcessAnalysis(Definitions definitions) {
		return ProcessAnalysisFactory.createAnalysis(definitions);
	}

}
