package edu.udo.cs.ls14.jf.ws.bpmn.analysis;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.jws.WebService;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.util.Bpmn2ResourceFactoryImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis;
import edu.udo.cs.ls14.jf.bpmnanalysis.util.BpmnAnalysisResourceFactoryImpl;
import edu.udo.cs.ls14.jf.bpmnmatching.util.BpmnMatchingResourceFactoryImpl;

@WebService(endpointInterface = "edu.udo.cs.ls14.jf.ws.bpmn.analysis.BpmnAnalysisSEI")
public class BpmnAnalysisImpl implements BpmnAnalysisSEI {

	private static final Logger LOG = LoggerFactory
			.getLogger(BpmnAnalysisImpl.class);

	@PostConstruct
	public void init() {
		Map<String, Object> map = Resource.Factory.Registry.INSTANCE
				.getExtensionToFactoryMap();
		LOG.info("Registering Factories");
		map.putIfAbsent("bpmn", new Bpmn2ResourceFactoryImpl());
		map.putIfAbsent("bpmnanalysis", new BpmnAnalysisResourceFactoryImpl());
		map.putIfAbsent("bpmnmatching", new BpmnMatchingResourceFactoryImpl());
	}

	@Override
	public ProcessAnalysis analyze(Definitions definitions) throws Exception {
		LOG.info("Service input: " + definitions);
//		ProcessAnalysis analysis = ProcessAnalyzer.analyze(definitions);
//		LOG.info("Service output: " + analysis);
//		return analysis;
		return null;
	}
}
