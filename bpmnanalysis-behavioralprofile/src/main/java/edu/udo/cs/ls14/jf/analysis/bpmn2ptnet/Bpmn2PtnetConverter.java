package edu.udo.cs.ls14.jf.analysis.bpmn2ptnet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.apache.commons.lang3.NotImplementedException;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.bpmn2.Activity;
import org.eclipse.bpmn2.BoundaryEvent;
import org.eclipse.bpmn2.CallActivity;
import org.eclipse.bpmn2.ComplexGateway;
import org.eclipse.bpmn2.EndEvent;
import org.eclipse.bpmn2.Event;
import org.eclipse.bpmn2.EventBasedGateway;
import org.eclipse.bpmn2.EventBasedGatewayType;
import org.eclipse.bpmn2.ExclusiveGateway;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.Gateway;
import org.eclipse.bpmn2.GatewayDirection;
import org.eclipse.bpmn2.ImplicitThrowEvent;
import org.eclipse.bpmn2.InclusiveGateway;
import org.eclipse.bpmn2.IntermediateCatchEvent;
import org.eclipse.bpmn2.IntermediateThrowEvent;
import org.eclipse.bpmn2.ParallelGateway;
import org.eclipse.bpmn2.Process;
import org.eclipse.bpmn2.SequenceFlow;
import org.eclipse.bpmn2.StartEvent;
import org.eclipse.bpmn2.SubProcess;
import org.eclipse.bpmn2.Task;
import org.javatuples.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.lip6.move.pnml.framework.utils.ModelRepository;
import fr.lip6.move.pnml.framework.utils.PNMLUtils;
import fr.lip6.move.pnml.framework.utils.exception.InvalidIDException;
import fr.lip6.move.pnml.framework.utils.exception.VoidRepositoryException;
import fr.lip6.move.pnml.ptnet.hlapi.ArcHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.NameHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.PNTypeHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.PTMarkingHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.PageHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.PetriNetDocHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.PetriNetHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.PlaceHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.TransitionHLAPI;

public class Bpmn2PtnetConverter {

	private static final Logger LOG = LoggerFactory
			.getLogger(Bpmn2PtnetConverter.class);

	private PetriNetHLAPI net;
	private PageHLAPI page;
	private PetriNetDocHLAPI doc;
	private Set<Pair<String, Set<TransitionHLAPI>>> prePlaces;
	private Set<Pair<Set<TransitionHLAPI>, String>> postPlaces;

	public PetriNetHLAPI convertToPetriNet(Process process) throws Exception {
		createPetrinetFromProcess(process);
		return net;
	}

	public void saveToPnmlFile(String pnmlFileName) throws Exception {
		if (doc == null) {
			throw new Exception(
					"Document not created yet, call convertToPetriNet(...) first.");
		}
		PNMLUtils.exportPetriNetDocToPNML(doc, pnmlFileName);
		LOG.info("Wrote PNML to " + pnmlFileName);
	}

	private void createPetrinetFromProcess(Process process) throws Exception {
		// Build maps (perhaps sets are ok, since ordering of FlowNodes and
		// SequenceFlows is undefined. => i need a directory, which ids are
		// sequenceflows (step 2))
		Map<String, FlowNode> flowNodes = new HashMap<String, FlowNode>();
		Map<String, SequenceFlow> sequenceFlows = new HashMap<String, SequenceFlow>();
		List<FlowElement> flowElements = ((Process) process).getFlowElements();
		for (FlowElement e : flowElements) {
			if (e instanceof FlowNode) {
				flowNodes.put(((FlowNode) e).getId(), (FlowNode) e);
			} else if (e instanceof SequenceFlow) {
				sequenceFlows.put(((SequenceFlow) e).getId(), (SequenceFlow) e);
			} else {
				throw new UnsupportedOperationException(
						"Unknown FlowElement type: " + e.getClass());
			}
		}

		// Initialize Sets / Maps
		ModelRepository.getInstance().createDocumentWorkspace(
				"void-" + new Random().nextInt(10000));
		doc = new PetriNetDocHLAPI();
		net = new PetriNetHLAPI("net0", PNTypeHLAPI.PTNET, doc);
		page = new PageHLAPI("page0", net);
		prePlaces = new HashSet<Pair<String, Set<TransitionHLAPI>>>();
		postPlaces = new HashSet<Pair<Set<TransitionHLAPI>, String>>();

		// 1. Iterate over all flownodes and create transitions and places
		// remember mapped ids
		for (FlowNode n : flowNodes.values()) {
			if (n instanceof Event) {
				handleEvent((Event) n);
			} else if (n instanceof Activity) {
				handleActivity((Activity) n);
			} else if (n instanceof Gateway) {
				handleGateway((Gateway) n);
			} else {
				throw new UnsupportedOperationException(
						"Unknown FlowNode type: " + n.getClass());
			}
		}

		// 2. postPlaces+prePlaces matchen, neue Places für Matches erzeugen,
		// entsprechende Edges erzeugen.
		for (Pair<Set<TransitionHLAPI>, String> p : postPlaces) {
			for (Pair<String, Set<TransitionHLAPI>> p2 : prePlaces) {
				if (p.getValue1().equals(p2.getValue0())) {
					PlaceHLAPI place = new PlaceHLAPI("P-"
							+ getConcatenatedIds(p.getValue0()) + "-"
							+ getConcatenatedIds(p2.getValue1()),
							new NameHLAPI("P-"
									+ getConcatenatedNames(p.getValue0()) + "-"
									+ getConcatenatedNames(p2.getValue1())),
							null, null, page);
					for (TransitionHLAPI t : p2.getValue1()) {
						String arcId = "Arc-" + place.getId() + "---"
								+ t.getId();
						new ArcHLAPI(arcId, new NameHLAPI(arcId), place, t,
								null, null, page);
					}
					for (TransitionHLAPI t : p.getValue0()) {
						String arcId = "Arc-" + t.getId() + "---"
								+ place.getId();
						new ArcHLAPI(arcId, new NameHLAPI(arcId), t, place,
								null, null, page);
					}
				}
			}
		}
		ModelRepository.getInstance().destroyCurrentWorkspace();
	}

	private void handleGateway(Gateway n) throws InvalidIDException,
			VoidRepositoryException {
		if (n instanceof ParallelGateway
				|| (n instanceof EventBasedGateway && ((EventBasedGateway) n)
						.getEventGatewayType() == EventBasedGatewayType.PARALLEL)) {
			// TODO: Test with mixed gateway directions
			TransitionHLAPI t = new TransitionHLAPI(n.getId(),
					new NameHLAPI(""), null, page);
			for (SequenceFlow f : n.getIncoming()) {
				prePlaces.add(new Pair<String, Set<TransitionHLAPI>>(f.getId(),
						new HashSet<TransitionHLAPI>(Arrays.asList(t))));
			}
			for (SequenceFlow f : n.getOutgoing()) {
				postPlaces.add(new Pair<Set<TransitionHLAPI>, String>(
						new HashSet<TransitionHLAPI>(Arrays.asList(t)), f
								.getId()));
			}
		} else if (n instanceof ExclusiveGateway
				|| (n instanceof EventBasedGateway && ((EventBasedGateway) n)
						.getEventGatewayType() == EventBasedGatewayType.EXCLUSIVE)) {
			if (((Gateway) n).getGatewayDirection() == GatewayDirection.DIVERGING) {
				Set<TransitionHLAPI> ts = new HashSet<TransitionHLAPI>();
				for (SequenceFlow f : ((Gateway) n).getOutgoing()) {
					TransitionHLAPI t = new TransitionHLAPI(n.getId() + "."
							+ f.getId(), new NameHLAPI(""), null, page);
					ts.add(t);
					postPlaces.add(new Pair<Set<TransitionHLAPI>, String>(
							new HashSet<TransitionHLAPI>(Arrays.asList(t)), f
									.getId()));
				}
				for (SequenceFlow f : ((Gateway) n).getIncoming()) {
					prePlaces.add(new Pair<String, Set<TransitionHLAPI>>(f
							.getId(), ts));
				}
			} else if (((Gateway) n).getGatewayDirection() == GatewayDirection.CONVERGING) {
				Set<TransitionHLAPI> ts = new HashSet<TransitionHLAPI>();
				for (SequenceFlow f : ((Gateway) n).getIncoming()) {
					TransitionHLAPI t = new TransitionHLAPI(f.getId() + "."
							+ n.getId(), new NameHLAPI(""), null, page);
					ts.add(t);
					prePlaces.add(new Pair<String, Set<TransitionHLAPI>>(f
							.getId(), new HashSet<TransitionHLAPI>(Arrays
							.asList(t))));
				}
				for (SequenceFlow f : ((Gateway) n).getOutgoing()) {
					postPlaces.add(new Pair<Set<TransitionHLAPI>, String>(ts, f
							.getId()));
				}
			} else {
				// TODO: check if there is a way for supporting mixed
				// gateways
				throw new NotImplementedException(
						"unsupported GatewayDirection: "
								+ ((Gateway) n).getGatewayDirection()
								+ ". Must be Diverging or Converging.");
			}
		} else if (n instanceof InclusiveGateway) {
			throw new NotImplementedException("InclusiveGateway");
		} else if (n instanceof ComplexGateway) {
			throw new NotImplementedException("ComplexGateway");
		} else {
			throw new NotImplementedException("Unsupported Gateway: " + n);
		}
	}

	private void handleActivity(Activity n) throws Exception {
		if (n instanceof Task) {
			TransitionHLAPI t = new TransitionHLAPI(n.getId(), new NameHLAPI(""
					+ n.getName()), null, page);

			for (SequenceFlow f : n.getIncoming()) {
				prePlaces.add(new Pair<String, Set<TransitionHLAPI>>(f.getId(),
						new HashSet<TransitionHLAPI>(Arrays.asList(t))));
			}
			for (SequenceFlow f : n.getOutgoing()) {
				postPlaces.add(new Pair<Set<TransitionHLAPI>, String>(
						new HashSet<TransitionHLAPI>(Arrays.asList(t)), f
								.getId()));
			}
		} else if (n instanceof CallActivity) {
			throw new NotImplementedException("CallActivity");
		} else if (n instanceof SubProcess) {
			throw new NotImplementedException("SubProcess");
		} else {
			throw new NotImplementedException("Unknown Activity");
		}
	}

	private void handleEvent(Event n) throws Exception {
		if (n instanceof StartEvent) {
			TransitionHLAPI t = new TransitionHLAPI(n.getId(), new NameHLAPI(""
					+ n.getName()), null, page);
			PlaceHLAPI p = new PlaceHLAPI("P-null-" + n.getId(), new NameHLAPI(
					"P-" + n.getName()), null, new PTMarkingHLAPI(1), page);
			new ArcHLAPI("Arc-" + p.getId() + "---" + t.getId(), new NameHLAPI(
					"Arc-" + p.getName().getText() + "---"
							+ t.getName().getText()), p, t, null, null, page);
			for (SequenceFlow f : n.getOutgoing()) {
				postPlaces.add(new Pair<Set<TransitionHLAPI>, String>(
						new HashSet<TransitionHLAPI>(Arrays.asList(t)), f
								.getId()));
			}
		} else if (n instanceof EndEvent) {
			TransitionHLAPI t = new TransitionHLAPI(n.getId(), new NameHLAPI(""
					+ n.getName()), null, page);
			PlaceHLAPI p = new PlaceHLAPI("P-" + n.getId() + "-null",
					new NameHLAPI("P-" + n.getName() + "-null"), null, null,
					page);
			new ArcHLAPI("Arc-" + t.getId() + "---" + p.getId(), new NameHLAPI(
					"Arc-" + t.getNameHLAPI() + "---" + p.getNameHLAPI()), t,
					p, null, null, page);
			for (SequenceFlow f : n.getIncoming()) {
				prePlaces.add(new Pair<String, Set<TransitionHLAPI>>(f.getId(),
						new HashSet<TransitionHLAPI>(Arrays.asList(t))));
			}
		} else if (n instanceof IntermediateCatchEvent
				|| n instanceof IntermediateThrowEvent) {
			TransitionHLAPI t = new TransitionHLAPI(n.getId(), new NameHLAPI(""
					+ n.getName()), null, page);
			for (SequenceFlow f : n.getIncoming()) {
				prePlaces.add(new Pair<String, Set<TransitionHLAPI>>(f.getId(),
						new HashSet<TransitionHLAPI>(Arrays.asList(t))));
			}
			for (SequenceFlow f : n.getOutgoing()) {
				postPlaces.add(new Pair<Set<TransitionHLAPI>, String>(
						new HashSet<TransitionHLAPI>(Arrays.asList(t)), f
								.getId()));
			}
		} else if (n instanceof ImplicitThrowEvent) {
			throw new NotImplementedException("ImplicitThrowEvent");
		} else if (n instanceof BoundaryEvent) {
			throw new NotImplementedException("BoundaryEvent");
		} else {
			throw new NotImplementedException("Unknown Event");
		}
	}

	private String getConcatenatedIds(Set<TransitionHLAPI> ts) {
		List<String> tIds = new ArrayList<String>();
		for (TransitionHLAPI t : ts) {
			tIds.add(t.getId());
		}
		return StringUtils.join(tIds, ".");
	}

	private String getConcatenatedNames(Set<TransitionHLAPI> ts) {
		List<String> tIds = new ArrayList<String>();
		for (TransitionHLAPI t : ts) {
			tIds.add(t.getNameHLAPI().getText());
		}
		return StringUtils.join(tIds, ".");
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(System.getProperty("line.separator"));
		sb.append("-- Transitions ------------------");
		for (TransitionHLAPI t : page.getObjects_TransitionHLAPI()) {
			sb.append(System.getProperty("line.separator"));
			sb.append(t.getId() + " (" + t.getName().getText() + ")");
		}
		sb.append(System.getProperty("line.separator"));
		sb.append("-- Places -----------------------");
		for (PlaceHLAPI p : page.getObjects_PlaceHLAPI()) {
			sb.append(System.getProperty("line.separator"));
			sb.append(p.getId());
		}
		sb.append(System.getProperty("line.separator"));
		sb.append("-- Arcs -------------------------");
		for (ArcHLAPI a : page.getObjects_ArcHLAPI()) {
			sb.append(System.getProperty("line.separator"));
			sb.append(a.getId());
		}
		sb.append(System.getProperty("line.separator"));
		sb.append("---------------------------------");
		sb.append(System.getProperty("line.separator"));
		return sb.toString();
	}

	public String toDot() {
		String nl = System.getProperty("line.separator");
		StringBuffer sb = new StringBuffer();
		sb.append("strict digraph \"net0\" {" + nl);
		sb.append("overlap=scale;" + nl);
		sb.append("splines=true;" + nl);
		sb.append("rankdir=LR;" + nl);
		sb.append("node[fixedsize=false;fontsize=32];" + nl);
		sb.append("edge[];" + nl);
		sb.append("ordering=out;" + nl);
		sb.append("color=black;" + nl);
		for (PlaceHLAPI p : page.getObjects_PlaceHLAPI()) {
			sb.append("  \"" + p.getId()
					+ "\" [shape=circle, height=.40, width=.40,label=\"\"];"
					+ nl);
		}
		for (TransitionHLAPI t : page.getObjects_TransitionHLAPI()) {
			sb.append("  \"" + t.getId()
					+ "\" [shape=box, height=.50, width=.20,label=\""
					+ t.getName().getText() + "\"];" + nl);
		}
		for (ArcHLAPI a : page.getObjects_ArcHLAPI()) {
			sb.append("  \"" + a.getSourceHLAPI().getId() + "\"");
			sb.append(" ->");
			sb.append(" \"" + a.getTargetHLAPI().getId() + "\"");
			sb.append("[label=\"\"];");
			sb.append(nl);
		}
		sb.append("}" + nl);
		return sb.toString();
	}
}
