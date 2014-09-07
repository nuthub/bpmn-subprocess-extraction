package edu.udo.cs.ls14.jf.bpmn2pnml;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.NotImplementedException;
import org.eclipse.bpmn2.Activity;
import org.eclipse.bpmn2.BoundaryEvent;
import org.eclipse.bpmn2.CallActivity;
import org.eclipse.bpmn2.ComplexGateway;
import org.eclipse.bpmn2.EndEvent;
import org.eclipse.bpmn2.Event;
import org.eclipse.bpmn2.EventBasedGateway;
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

import edu.udo.cs.ls14.jf.petrinet.Arc;
import edu.udo.cs.ls14.jf.petrinet.PetriNet;
import edu.udo.cs.ls14.jf.petrinet.Place;
import edu.udo.cs.ls14.jf.petrinet.Transition;
import fr.lip6.move.pnml.framework.utils.exception.InvalidIDException;
import fr.lip6.move.pnml.framework.utils.exception.VoidRepositoryException;
import fr.lip6.move.pnml.ptnet.hlapi.PetriNetHLAPI;

public class Bpmn2PnmlConverter {

	private Set<Transition> transitions;
	private Set<Place> places;
	private Set<Arc> arcs;

	private Set<Place> placesTemp;
	private Map<String, Set<String>> substitutedTransitions;
	

	public PetriNetHLAPI convertToPTNet(Process process) throws Exception {
		convert(process);
		outputNet();
		return createPN().toPTNet();
	}

	public String convertToPnmlString(Process process) throws Exception {
		convert(process);
		outputNet();
		return createPN().toPNML();
	}

	public void convertToPnmlFile(Process process, String pnmlFileName)
			throws Exception {
		convert(process);
		outputNet();
		createPN().save(pnmlFileName);
	}

	private void convert(Process process) throws Exception {
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
						"Unkmown FlowElement type: " + e.getClass());
			}
		}

		// Initialize Sets / Maps
		transitions = new HashSet<Transition>();
		placesTemp = new HashSet<Place>();
		places = new HashSet<Place>();
		arcs = new HashSet<Arc>();
		substitutedTransitions = new HashMap<String, Set<String>>();

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

		// 2. Places zusammenfassen
		for (Place p : placesTemp) {
			if (sequenceFlows.containsKey(p.getTgtId())) {
				for (Place p2 : placesTemp) {
					if (p.getTgtId().equals(p2.getSrcId())) {
						places.add(new Place(p.getSrcId(), p2.getTgtId(), p.isMarked()));
					}
				}
			} else if (!sequenceFlows.containsKey(p.getSrcId())) {
				places.add(p);
			}
		}

		// 3. edges erzeugen
		for (Place p : places) {
			if (p.getSrcId() != null) {
				for (String t : getSubstitutedTransitions(p.getSrcId())) {
					arcs.add(new Arc(t, p.getId()));
				}
			}
			if (p.getTgtId() != null) {
				for (String t : getSubstitutedTransitions(p.getTgtId())) {
					arcs.add(new Arc(p.getId(), t));
				}
			}
		}
	}

	public PetriNet createPN() throws InvalidIDException, VoidRepositoryException {
		PetriNet net = new PetriNet();
		for (Transition t : transitions) {
			net.addTransition(t.getId(), t.getLabel());
		}
		int i = 0;
		for (Place p : places) {
			net.addPlace(p.getId(), "P"+i++, p.isMarked());
		}
		for (Arc a : arcs) {
			net.addArc(a.getSource(), a.getTarget());
		}
		return net;
	}

	private void outputNet() {
		System.out.println("-- Transitions ------------------");
		for (Transition t : transitions) {
			System.out.println(t);
		}
		System.out.println("-- Places -----------------------");
		for (Place p : places) {
			System.out.println(p);
		}
		System.out.println("-- Arcs -------------------------");
		for (Arc a : arcs) {
			System.out.println(a);
		}
		System.out.println("---------------------------------");
	}

	private void handleGateway(Gateway n) {
		if (n instanceof ParallelGateway) {
			// TODO: Test with mixed gateway directions
			transitions.add(new Transition(n.getId(), ""));
			for (SequenceFlow f : n.getIncoming()) {
				placesTemp.add(new Place(f.getId(), n.getId(), false));
			}
			for (SequenceFlow f : n.getOutgoing()) {
				placesTemp.add(new Place(n.getId(), f.getId(), false));
			}
		} else if (n instanceof ExclusiveGateway) {
			if (((ExclusiveGateway) n).getGatewayDirection() == GatewayDirection.DIVERGING) {
				for (SequenceFlow f : ((ExclusiveGateway) n).getOutgoing()) {
					String substId = n.getId() + "." + f.getId();
					transitions.add(new Transition(substId, ""));
					substituteTransition(n.getId(), substId);
					placesTemp.add(new Place(substId, f.getId(), false));
				}
				for (SequenceFlow f : ((ExclusiveGateway) n).getIncoming()) {
					placesTemp.add(new Place(f.getId(), n.getId(), false));
				}
			} else if (((ExclusiveGateway) n).getGatewayDirection() == GatewayDirection.CONVERGING) {
				for (SequenceFlow f : ((ExclusiveGateway) n).getIncoming()) {
					String substId = f.getId() + "." + n.getId();
					transitions.add(new Transition(substId, ""));
					substituteTransition(n.getId(), substId);
					placesTemp.add(new Place(f.getId(), substId, false));
				}
				for (SequenceFlow f : ((ExclusiveGateway) n).getOutgoing()) {
					placesTemp.add(new Place(n.getId(), f.getId(), false));
				}
			} else {
				// TODO: check if there is a way for supporting mixed
				// gateways
				throw new NotImplementedException(
						"unsupported GatewayDirection: "
								+ ((ExclusiveGateway) n).getGatewayDirection());
			}
		} else if (n instanceof EventBasedGateway) {
			throw new NotImplementedException("EventBasedGateway");
		} else if (n instanceof InclusiveGateway) {
			throw new NotImplementedException("InclusiveGateway");
		} else if (n instanceof ComplexGateway) {
			throw new NotImplementedException("ComplexGateway");
		}
	}

	private void handleActivity(Activity n) throws Exception {
		if (n instanceof Task) {
			transitions.add(new Transition(n.getId(), n.getName()));
			for (SequenceFlow f : n.getIncoming()) {
				placesTemp.add(new Place(f.getId(), n.getId(), false));
			}
			for (SequenceFlow f : n.getOutgoing()) {
				placesTemp.add(new Place(n.getId(), f.getId(), false));
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
			transitions.add(new Transition(n.getId(), n.getName()));
			placesTemp.add(new Place(null, n.getId(), true));
			for (SequenceFlow f : n.getOutgoing()) {
				placesTemp.add(new Place(n.getId(), f.getId(), false));
			}
		} else if (n instanceof EndEvent) {
			transitions.add(new Transition(n.getId(), n.getName()));
			placesTemp.add(new Place(n.getId(), null, false));
			for (SequenceFlow f : n.getIncoming()) {
				placesTemp.add(new Place(f.getId(), n.getId(), false));
			}
		} else if (n instanceof IntermediateCatchEvent
				|| n instanceof IntermediateThrowEvent) {
			for (SequenceFlow f : n.getIncoming()) {
				placesTemp.add(new Place(f.getId(), n.getId(), false));
			}
			transitions.add(new Transition(n.getId(), n.getName()));
			for (SequenceFlow f : n.getOutgoing()) {
				placesTemp.add(new Place(n.getId(), f.getId(), false));
			}
		} else if (n instanceof ImplicitThrowEvent) {
			throw new NotImplementedException("ImplicitThrowEvent");
		} else if (n instanceof BoundaryEvent) {
			throw new NotImplementedException("BoundaryEvent");
		} else {
			throw new NotImplementedException("Unknown Event");
		}
	}

	private Set<String> getSubstitutedTransitions(String id) {
		if (substitutedTransitions.containsKey(id)) {
			return substitutedTransitions.get(id);
		}
		Set<String> set = new HashSet<String>();
		set.add(id);
		return set;
	}

	private void substituteTransition(String id, String substId) {
		if (!substitutedTransitions.containsKey(id)) {
			substitutedTransitions.put(id, new HashSet<String>());
		}
		substitutedTransitions.get(id).add(substId);
	}

}
