package edu.udo.cs.ls14.jf.services.bpmn;

import java.util.HashMap;
import java.util.Set;
import java.util.logging.Logger;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.Process;
import org.eclipse.emf.ecore.EObject;
import org.javatuples.Pair;

import edu.udo.cs.ls14.jf.analysis.behaviorprofile.BehavioralProfile;
import edu.udo.cs.ls14.jf.analysis.behaviorprofile.BehavioralProfiler;
import edu.udo.cs.ls14.jf.analysis.behaviorprofile.RelationType;
import edu.udo.cs.ls14.jf.analysis.bpmn2ptnet.Bpmn2PtnetConverter;
import edu.udo.cs.ls14.jf.analysis.reachabilitygraph.ReachabilityGraph;
import edu.udo.cs.ls14.jf.analysis.reachabilitygraph.Trace;
import edu.udo.cs.ls14.jf.analysis.reachabilitygraph.Tracer;
import edu.udo.cs.ls14.jf.bpmn.utils.EObjectXmlConverter;
import edu.udo.cs.ls14.jf.bpmn.utils.ProcessLoader;
import edu.udo.cs.ls14.jf.services.adapter.BehaviourProfileAdapter;
import fr.lip6.move.pnml.ptnet.hlapi.PetriNetHLAPI;

@WebService
public class Bpmn2BP {

	private static final Logger LOG = Logger.getLogger(Bpmn2BP.class.getName());

	@WebMethod
	@WebResult(name = "behaviourProfile")
	@XmlJavaTypeAdapter(BehaviourProfileAdapter.class)
	public BehavioralProfile getBehavioralProfile(
			@WebParam(name = "definitionsXmi") String definitionsXml)
			throws Exception {
		LOG.fine("Received: " + definitionsXml);
		EObject eObject = EObjectXmlConverter.xml2EObject(definitionsXml);
		if (!(eObject instanceof Definitions)) {
			throw new Exception("definitionsXmi is not XMI of Definitions");
		}
		Definitions definitions = (Definitions) eObject;
		Process process = ProcessLoader.getProcessFromDefinitions(definitions);
		LOG.info("Process: " + process);
		// create petri net
		Bpmn2PtnetConverter bpmn2ptnet = new Bpmn2PtnetConverter();
		PetriNetHLAPI ptnet = bpmn2ptnet.convertToPetriNet(process);
		LOG.info("ptnet: " + ptnet);
		// create reachabilitygraph
		ReachabilityGraph reachabilityGraph = new ReachabilityGraph();
		reachabilityGraph.createFromPTNet(ptnet.getContainedItem());
		LOG.info("Reachability graph: " + reachabilityGraph);
		// create traces
		Set<Trace> traces = Tracer.getTraces(process, reachabilityGraph);
		LOG.info("traces: " + traces);
		// create behavioral profile
		BehavioralProfile bp = BehavioralProfiler.generateProfile(process, traces);
		LOG.info("behavioralProfile: " + bp.toString());
		return bp;
	}

}
