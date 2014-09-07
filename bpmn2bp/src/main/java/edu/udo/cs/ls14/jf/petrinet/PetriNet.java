package edu.udo.cs.ls14.jf.petrinet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import fr.lip6.move.pnml.framework.utils.ModelRepository;
import fr.lip6.move.pnml.framework.utils.PNMLUtils;
import fr.lip6.move.pnml.framework.utils.exception.BadFileFormatException;
import fr.lip6.move.pnml.framework.utils.exception.InvalidIDException;
import fr.lip6.move.pnml.framework.utils.exception.OCLValidationFailed;
import fr.lip6.move.pnml.framework.utils.exception.OtherException;
import fr.lip6.move.pnml.framework.utils.exception.UnhandledNetType;
import fr.lip6.move.pnml.framework.utils.exception.ValidationFailedException;
import fr.lip6.move.pnml.framework.utils.exception.VoidRepositoryException;
import fr.lip6.move.pnml.ptnet.hlapi.ArcHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.NameHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.NodeHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.PNTypeHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.PTMarkingHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.PageHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.PetriNetDocHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.PetriNetHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.PlaceHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.TransitionHLAPI;

/**
 * 
 * @author flake
 * 
 * @deprecated use PNML Framework
 */
@Deprecated
public class PetriNet {

	private PetriNetDocHLAPI doc;
	private PetriNetHLAPI net;
	private PageHLAPI page;
	private Map<String, NodeHLAPI> nodes;
	private Map<String, ArcHLAPI> edges;

	public PetriNet() throws InvalidIDException, VoidRepositoryException {
		ModelRepository.getInstance().createDocumentWorkspace(
				"void-" + new Random().nextInt(10000));
		doc = new PetriNetDocHLAPI();
		net = new PetriNetHLAPI("net0", PNTypeHLAPI.PTNET, doc);
		page = new PageHLAPI("page0", net);
		nodes = new HashMap<String, NodeHLAPI>();
		edges = new HashMap<String, ArcHLAPI>();
	}

	public void save(String fileName) throws UnhandledNetType,
			OCLValidationFailed, IOException, ValidationFailedException,
			BadFileFormatException, OtherException, InvalidIDException,
			VoidRepositoryException {
		PNMLUtils.exportPetriNetDocToPNML(doc, fileName);
		ModelRepository.getInstance().destroyCurrentWorkspace();
	}

	public String addTransition(String id, String name)
			throws InvalidIDException, VoidRepositoryException {
		TransitionHLAPI t = new TransitionHLAPI(id, new NameHLAPI(name), null,
				page);
		nodes.put(id, t);
		return t.getId();
	}

	public String addPlace(String id, String label, boolean marked)
			throws InvalidIDException, VoidRepositoryException {
		PTMarkingHLAPI m = null;
		if (marked) {
			m = new PTMarkingHLAPI(1);
		}
		PlaceHLAPI p = new PlaceHLAPI(id, new NameHLAPI(label), null, m, page);
		nodes.put(id, p);
		return p.getId();
	}

	public void addArc(String source, String target) throws InvalidIDException,
			VoidRepositoryException {
		String id = source + "--" + target;
		NodeHLAPI sourceNode = nodes.get(source);
		NodeHLAPI targetNode = nodes.get(target);
		ArcHLAPI a = new ArcHLAPI(id, sourceNode, targetNode, page);
		edges.put(id, a);

	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(net.getId() + " = <T,P,F>");
		sb.append(System.getProperty("line.separator"));
		sb.append(" T = {");
		sb.append(System.getProperty("line.separator"));
		for (NodeHLAPI n : nodes.values()) {
			if (n instanceof TransitionHLAPI) {
				sb.append("  " + n.getId());
				sb.append(System.getProperty("line.separator"));
			}
		}
		sb.append(" }");
		sb.append(System.getProperty("line.separator"));

		sb.append(" P = {");
		sb.append(System.getProperty("line.separator"));
		for (NodeHLAPI n : nodes.values()) {
			if (n instanceof PlaceHLAPI) {
				sb.append("  " + n.getId());
				sb.append(System.getProperty("line.separator"));
			}
		}
		sb.append(" }");
		sb.append(System.getProperty("line.separator"));

		sb.append(" F = {");
		sb.append(System.getProperty("line.separator"));
		for (ArcHLAPI e : edges.values()) {
			if (e instanceof ArcHLAPI) {
				sb.append("  " + e.getId());
				sb.append(System.getProperty("line.separator"));
			}
		}
		sb.append(" }");
		return sb.toString();
	}
	
	public PetriNetHLAPI toPTNet() {
		return net;
	}

	public String toPNML() {
		return net.toPNML();
	}

}
