package edu.udo.cs.ls14.jf.analysis.unfolding.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.Set;

import org.apache.commons.lang3.NotImplementedException;
import org.eclipse.emf.ecore.EObject;
import org.junit.Ignore;
import org.junit.Test;

import edu.udo.cs.ls14.jf.analysis.unfolding.esparza.Condition;
import edu.udo.cs.ls14.jf.analysis.unfolding.esparza.Event;
import edu.udo.cs.ls14.jf.analysis.unfolding.esparza.ImprovedUnfolder;
import edu.udo.cs.ls14.jf.analysis.unfolding.esparza.Unfolding;
import edu.udo.cs.ls14.jf.analysis.unfolding.mcmillan.PetriNetUnfolder;
import fr.lip6.move.pnml.framework.hlapi.HLAPIRootClass;
import fr.lip6.move.pnml.framework.utils.ModelRepository;
import fr.lip6.move.pnml.framework.utils.PNMLUtils;
import fr.lip6.move.pnml.ptnet.Page;
import fr.lip6.move.pnml.ptnet.PetriNet;
import fr.lip6.move.pnml.ptnet.PetriNetDoc;
import fr.lip6.move.pnml.ptnet.Place;
import fr.lip6.move.pnml.ptnet.Transition;
import fr.lip6.move.pnml.ptnet.hlapi.ArcHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.NameHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.PNTypeHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.PTMarkingHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.PageHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.PetriNetDocHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.PetriNetHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.PlaceHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.TransitionHLAPI;

public class UnfoldedNetTest {

	@Test
	public void testPriorityQueue() {

		Comparator<Set<?>> c = new Comparator<Set<?>>() {

			public int compare(Set<?> o1, Set<?> o2) {
				if (o1.size() < o2.size()) {
					return -1;
				}
				if (o1.size() > o2.size()) {
					return 1;
				}
				return 0;
			}
		};
		Queue<Set<String>> q = new PriorityQueue<Set<String>>(11, c);
		Set<String> s1 = new HashSet<String>(Arrays.asList("a"));
		Set<String> s2 = new HashSet<String>(Arrays.asList("a", "b"));
		Set<String> s3 = new HashSet<String>(Arrays.asList("a", "b", "c"));
		Set<String> s4 = new HashSet<String>(Arrays.asList("a", "b", "c", "d"));
		q.add(s3);
		q.add(s1);
		q.add(s4);
		q.add(s2);
		assertEquals(s1, q.poll());
		assertEquals(s2, q.poll());
		assertEquals(s3, q.poll());
		assertEquals(s4, q.poll());
		assertTrue(q.isEmpty());
	}

	@Test
	@Ignore
	public void testUnfold() throws Exception {
		File file = new File(getClass().getResource(
				"/edu/udo/cs/ls14/jf/pnml/looping-events-example.pnml").toURI());

		HLAPIRootClass rc = PNMLUtils.importPnmlDocument(file, false);
		if (!PNMLUtils.isPTNetDocument(rc)) {
			throw new NotImplementedException(
					"only fr.lip6.move.pnml.ptnet.hlapi.PetriNetDocHLAPI type pnml files implemented");
		}
		PetriNetDocHLAPI ptDoc = (PetriNetDocHLAPI) rc;
		PetriNetUnfolder unfolder = new PetriNetUnfolder();
		List<PetriNetHLAPI> netsIn = ptDoc.getNetsHLAPI();
		if (netsIn.size() != 1) {
			throw new NotImplementedException(
					"Only PetriNetDocs with exactly one net supported");
		}
		PetriNetHLAPI netIn = netsIn.get(0);
		List<PageHLAPI> pagesIn = netIn.getPagesHLAPI();
		if (pagesIn.size() != 1) {
			throw new NotImplementedException(
					"Only PetriNets with exactly one page supported");
		}
		PageHLAPI pageIn = pagesIn.get(0);

		Set<TransitionHLAPI> t = new HashSet<TransitionHLAPI>();
		Set<PlaceHLAPI> p = new HashSet<PlaceHLAPI>();
		Set<PlaceHLAPI> m0 = new HashSet<PlaceHLAPI>();
		for (PlaceHLAPI place : pageIn.getObjects_PlaceHLAPI()) {
			p.add(place);
			if (place.getInitialMarkingHLAPI() != null) {
				m0.add(place);
			}
		}
		for (TransitionHLAPI transition : pageIn.getObjects_TransitionHLAPI()) {
			t.add(transition);
		}
		unfolder.unfold(p, t, m0);

	}

	@Test
	public void testEsparzaUnfolder() throws Exception {
		File file = new File(getClass().getResource(
				"/edu/udo/cs/ls14/jf/pnml/xor-example.pnml").toURI());

		HLAPIRootClass rc = PNMLUtils.importPnmlDocument(file, false);
		if (!PNMLUtils.isPTNetDocument(rc)) {
			throw new NotImplementedException(
					"only fr.lip6.move.pnml.ptnet.hlapi.PetriNetDocHLAPI type pnml files implemented");
		}
		PetriNetDocHLAPI ptDocHLAPI = (PetriNetDocHLAPI) rc;
		PetriNetDoc ptDoc = ptDocHLAPI.getContainedItem();
		List<PetriNet> netsIn = ptDoc.getNets();
		if (netsIn.size() != 1) {
			throw new NotImplementedException(
					"Only PetriNetDocs with exactly one net supported");
		}
		PetriNet netIn = netsIn.get(0);
		List<Page> pagesIn = netIn.getPages();
		if (pagesIn.size() != 1) {
			throw new NotImplementedException(
					"Only PetriNets with exactly one page supported");
		}
		Page pageIn = pagesIn.get(0);

		Set<Transition> t = new HashSet<Transition>();
		Set<Place> p = new HashSet<Place>();
		Set<Place> m0 = new HashSet<Place>();
		for (EObject obj : pageIn.eContents()) {
			if (obj instanceof Place) {
				Place place = (Place) obj;
				p.add(place);
				if (place.getInitialMarking() != null) {
					m0.add(place);
				}
			} else if (obj instanceof Transition) {
				Transition transition = (Transition) obj;
				t.add(transition);
			}
		}

		ImprovedUnfolder unfolder = new ImprovedUnfolder();
		Unfolding unf = unfolder.unfold(p, t, m0);
		System.out.println(unf.getConditions());
		System.out.println(unf.getEvents());
		System.out.println("unf contains " + unf.getConditions().size()
				+ " conditions");
		System.out
				.println("unf contains " + unf.getEvents().size() + " events");

		ModelRepository.getInstance().createDocumentWorkspace(
				"void-" + new Random().nextInt(10000));
		PetriNetDocHLAPI doc = new PetriNetDocHLAPI();
		PetriNetHLAPI net = new PetriNetHLAPI("net0", PNTypeHLAPI.PTNET, doc);
		PageHLAPI page = new PageHLAPI("page0", net);
		Map<PlaceHLAPI, Place> places = new HashMap<PlaceHLAPI, Place>();
		Map<TransitionHLAPI, Transition> transitions = new HashMap<TransitionHLAPI, Transition>();

		Random prng = new Random();
		for (Condition c : unf.getConditions()) {
			PlaceHLAPI place = new PlaceHLAPI(c.getPlace().getId()
					+ prng.nextInt(1000));
			place.setNameHLAPI(new NameHLAPI(c.getPlace().getName()));
			place.setInitialMarkingHLAPI(new PTMarkingHLAPI(c.getPlace()
					.getInitialMarking()));
			places.put(place, c.getPlace());
			page.addObjectsHLAPI(place);
			Event e = c.getInputEvent();
			if (e != null) {
				TransitionHLAPI transition = new TransitionHLAPI(e.getTrans()
						.getId() + prng.nextInt(1000));
				transition.setNameHLAPI(new NameHLAPI(e.getTrans().getName()));
				transitions.put(transition, e.getTrans());
				page.addObjectsHLAPI(transition);
				ArcHLAPI arc = new ArcHLAPI(transition.getId() + "--" + place.getId(),
						transition, place);
				page.addObjectsHLAPI(arc);
			}
		}
		// for(Event e: unf.getEvents()) {
		// TransitionHLAPI transition = new TransitionHLAPI(e.getTrans().getId()
		// + prng.nextInt(1000));
		// transition.setNameHLAPI(new NameHLAPI(e.getTrans().getName()));
		// transitions.put(transition, e.getTrans());
		// page.addObjectsHLAPI(transition);
		// }
		System.out.println("output contains " + places.size() + " places");
		System.out.println("output contains " + transitions.size()
				+ " transitions");
		PNMLUtils.exportPetriNetDocToPNML(doc, "/tmp/xor-example-unf.pnml");
	}
}
