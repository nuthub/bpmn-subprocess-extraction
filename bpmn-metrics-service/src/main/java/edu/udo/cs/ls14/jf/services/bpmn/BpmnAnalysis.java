package edu.udo.cs.ls14.jf.services.bpmn;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.jws.WebService;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.util.Bpmn2ResourceFactoryImpl;
import org.eclipse.emf.ecore.resource.Resource;

import edu.udo.cs.ls14.jf.analysis.ProcessAnalyzer;
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis;
import edu.udo.cs.ls14.jf.bpmnanalysis.util.BpmnAnalysisResourceFactoryImpl;
import edu.udo.cs.ls14.jf.bpmnmatching.util.BpmnMatchingResourceFactoryImpl;

@WebService(endpointInterface = "edu.udo.cs.ls14.jf.services.bpmn.BpmnAnalysisSEI")
public class BpmnAnalysis implements BpmnAnalysisSEI {

	@PostConstruct
	public void init() {
		Map<String, Object> map = Resource.Factory.Registry.INSTANCE
				.getExtensionToFactoryMap();
		map.putIfAbsent("bpmn", new Bpmn2ResourceFactoryImpl());
		map.putIfAbsent("bpmnanalysis", new BpmnAnalysisResourceFactoryImpl());
		map.putIfAbsent("bpmnmatching", new BpmnMatchingResourceFactoryImpl());
	}

	@Override
	public ProcessAnalysis analyze(Definitions definitions)
			throws Exception {
		System.out.println("Service in: " + definitions);
		ProcessAnalysis analysis = ProcessAnalyzer.analyze(definitions);
		System.out.println("Service out: " + analysis);
		return analysis;
	}
}
