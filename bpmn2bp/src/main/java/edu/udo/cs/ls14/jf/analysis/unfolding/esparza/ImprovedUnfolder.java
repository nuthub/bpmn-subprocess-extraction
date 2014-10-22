package edu.udo.cs.ls14.jf.analysis.unfolding.esparza;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

import fr.lip6.move.pnml.ptnet.Arc;
import fr.lip6.move.pnml.ptnet.Place;
import fr.lip6.move.pnml.ptnet.Transition;

public class ImprovedUnfolder {

	public class MyAdequateComparator implements Comparator<Event> {
		public int compare(Event o1, Event o2) {
			// if(o1 == null) {
			// return -1;
			// }
			// if(o2 == null) {
			// return 1;
			// }
			if (o1.getInputConditions().size() < o2.getInputConditions().size()) {
				return -1;
			}
			if (o1.getInputConditions().size() > o2.getInputConditions().size()) {
				return 1;
			}
			return 0;
		}
	}

	private Unfolding fin;
	private PriorityQueue<Event> pe;
	private Set<Event> cutOffs;

	/**
	 * Procedure 3.2 The unfolding algorithm
	 * 
	 * input: An net system S = (N; M0), where M0 = {s1,; ... , sk}.
	 * 
	 * output: The Unfolding in S.
	 * 
	 * @param pIn
	 * @param tIn
	 * @param fIn
	 * @param m0
	 * @return
	 * @throws Exception
	 */
	public Unfolding unfold(Set<Place> pIn, Set<Transition> tIn, Set<Place> m0)
			throws Exception {
		// Fin = (s1,0), ..., (sk,0)
		fin = new Unfolding();
		for (Place s : m0) {
			fin.addCondition(new Condition(s, null));
		}
		// pe := PE(Fin)
		pe = possibleExtensions(tIn);
		System.out.println("possible extensions: " + pe);
		// cut-off := 0
		cutOffs = new HashSet<Event>();
		// while pe != 0
		while (!pe.isEmpty()) {
			// choose an event e=(t,B) in pe such that [e] (localconf(e)) is
			// minimal with respect to < (adequate order)
			Event e = pe.poll();
			System.out.println("e is now: " + e);
			// append event e and a condition c=(s,e) for every output place
			// s of t
			fin.addEvent(e);
			for (Place s : outputs(e.getTrans())) {
				fin.addCondition(new Condition(s, e));
			}
			// pe = PE(Fin)
			pe = possibleExtensions(tIn);
			System.out.println("possible extensions now: " + pe.size());
			// end if
		}
		// endwhile
		System.out.println("places: " + fin.getPlaces());
		return fin;
	}

	/**
	 * Algorithm 3.6 The complete finite prefix algorithm.
	 * 
	 * input: An n-safe net system S = (N; M0), where M0 = {s1,; ... , sk}.
	 * 
	 * output: A complete finite prefix Fin of Unf.
	 * 
	 * @param pIn
	 * @param tIn
	 * @param fIn
	 * @param m0
	 * @return
	 * @throws Exception
	 */
	public Unfolding finiteCompletePrefix(Set<Place> pIn, Set<Transition> tIn,
			Set<Place> m0) throws Exception {
		// Fin = (s1,0), ..., (sk,0)
		fin = new Unfolding();
		for (Place s : m0) {
			fin.addCondition(new Condition(s, null));
		}
		// pe := PE(Fin)
		pe = possibleExtensions(tIn);
		System.out.println("possible extensions: " + pe);
		// cut-off := 0
		cutOffs = new HashSet<Event>();
		// while pe != 0
		while (!pe.isEmpty()) {
			// choose an event e=(t,B) in pe such that [e] (localconf(e)) is
			// minimal with respect to < (adequate order)
			Event e = pe.poll();
			// if [e] is not a cut-off event then
			if (!isCutOff(e)) {
				// append event e and a condition c=(s,e) for every output place
				// s of t
				fin.addEvent(e);
				for (Place s : outputs(e.getTrans())) {
					fin.addCondition(new Condition(s, e));
				}
				// pe = PE(Fin)
				pe = possibleExtensions(tIn);
				// if e is a cut-off event of Fin then
				if (isCutOff(e)) {
					// cut-off = cut-off u {e}
					cutOffs.add(e);
				}
			} else {
				// else remove e from pe
				pe.remove(e);
			}
			// end if

		}
		// endwhile
		return fin;
	}

	/**
	 * Return the possible extensions of fin
	 * 
	 * Definition 3.1
	 * 
	 * PE(beta), beta is a branching process (sequence of nodes)
	 * 
	 * @return
	 * @throws Exception
	 */
	private PriorityQueue<Event> possibleExtensions(Set<Transition> tIn)
			throws Exception {
		// Gib alle aktivierten Transitionen als Events zurück
		PriorityQueue<Event> pe = new PriorityQueue<Event>(11,
				new MyAdequateComparator());

		for (Transition t : tIn) {
			// is t a possible extension?
			// Baue Menge B mit allen vorhanden conditions c, deren Places in *t
			// sind
			// TODO: Mehrere Mengen B möglich!
			Set<Place> inputs = inputs(t);
			Set<Condition> b = new HashSet<Condition>();
			Set<Place> pOfB = new HashSet<Place>();
			for (Condition c : fin.getConditions()) {
				if (inputs.contains(c.getPlace())) {
					b.add(c);
					pOfB.add(c.getPlace());
				}
			}
			System.out.println("B: " + b);
			// Condition 1: p(B)=*t
			boolean condition1 = inputs.equals(pOfB);
			if (!condition1) {
				continue;
			}
			// Condition 2: there is no event e with p(e)=t and *e=B
			boolean condition2 = true;
			for(Event e: fin.getEvents()) {
				if(e.getTrans().equals(t) && b.equals(e.getInputConditions())) {
					condition2 = false;
				}
			}
			if(!condition2) {
				continue;
			}
			pe.add(new Event(t,b));
		}
		return pe;
	}

	/**
	 * Return the local configuration of Event e
	 * 
	 * Definition 3.4
	 * 
	 * The local configuration [e] of an event of a branching process is the set
	 * of events e' such that e' \leq e.
	 * 
	 * \leq ist der reflexive, transitive Abschluss der Flussrelation
	 * 
	 * @param p
	 * @return
	 */
	private Set<Event> localConfiguration(Event e) {

		return null;
	}

	/**
	 * return true, if event is a cut off event with regard to adequate relation
	 * 
	 * Definition 3.5
	 * 
	 * @param e
	 * @return
	 */
	private boolean isCutOff(Event e) {
		Set<Event> lc = localConfiguration(e);
		for (Event ePrime : fin.getEvents()) {
			Set<Event> lcPrime = localConfiguration(ePrime);
			if (mark(lcPrime).equals(mark(lc)) && lcPrime.size() < lc.size()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Die Markierung, die sich aus der Konfiguration conf ergibt
	 * 
	 * @param conf
	 * @return
	 */
	private Set<Condition> mark(Set<Event> conf) {
		return null;
	}

	/**
	 * Returns the output places of transition
	 * 
	 * @param transition
	 * @return
	 * @throws Exception
	 */
	private Set<Place> outputs(Transition transition) throws Exception {
		Set<Place> outputs = new HashSet<Place>();
		for (Arc arc : transition.getOutArcs()) {
			fr.lip6.move.pnml.ptnet.Node node = arc.getTarget();
			if (!(node instanceof Place)) {
				throw new Exception("output of transition is not a place!");
			}
			outputs.add((Place) node);
		}
		return outputs;
	}

	/**
	 * Returns the input places of transition
	 * 
	 * @param transition
	 * @return
	 * @throws Exception
	 */
	private Set<Place> inputs(Transition transition) throws Exception {
		Set<Place> inputs = new HashSet<Place>();
		for (Arc arc : transition.getInArcs()) {
			fr.lip6.move.pnml.ptnet.Node node = arc.getSource();
			if (!(node instanceof Place)) {
				throw new Exception("output of transition is not a place!");
			}
			inputs.add((Place) node);
		}
		return inputs;
	}

}
