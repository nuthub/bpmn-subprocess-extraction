package edu.udo.cs.ls14.jf.unfolding.mcmillan;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

import fr.lip6.move.pnml.ptnet.hlapi.ArcHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.NodeHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.PlaceHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.TransitionHLAPI;

public class PetriNetUnfolder {

	// global P',T',Q',HashTable 0
	private Unfolding unf;
	private Queue<Transition> queue;
	private Map<Set<Transition>, Transition> ht;

	// function Unfold(P,T, Mo)
	public Unfolding unfold(Set<PlaceHLAPI> pIn, Set<TransitionHLAPI> tIn,
			Set<PlaceHLAPI> m0) throws Exception {
		
		// P' = T' = Q' = emptySet; clear HashTable
		unf = new Unfolding();
		queue = new PriorityQueue<Transition>(11, new Comparator<Transition>() {
			public int compare(Transition t1, Transition t2) {
				if(localConfig(t1).size() < localConfig(t2).size()) {
					return -1;
				}
				if(localConfig(t1).size() > localConfig(t2).size()) {
					return 1;
				}
				return 0;
			}
		});
		ht = new HashMap<Set<Transition>, Transition>();

		// for each p E M0 do
		for (PlaceHLAPI p : m0) {
			// add p' = (p, \emptyset) to P'
			HashSet<Transition> tSet = new HashSet<Transition>();
			Place pPrime = new Place(p, tSet);
			unf.addPlace(pPrime);
			// GenTrans({p'}, T)
			Set<Place> pSet = new HashSet<Place>();
			pSet.add(pPrime);
			genTrans(pSet, tIn);
			// end for
		}
		// while the queue Q' is not empty do
		while (!queue.isEmpty()) {
			// pull the first t' off of Q'
			Transition tPrime = queue.poll();
			// if not IsCutoffPoint?(t') do
			if (!isCutOffPoint(tPrime)) {
				// for each p in outputs of trans(t') do
				for (PlaceHLAPI p : outputs(tPrime.getTrans())) {
					// add p' = (p, {t'}) to P '
					HashSet<Transition> tSet = new HashSet<Transition>();
					tSet.add(tPrime);
					Place pPrime = new Place(p, tSet);
					unf.addPlace(pPrime);
					// GenTrans({p'}, T)
					Set<Place> pSet = new HashSet<Place>();
					pSet.add(pPrime);
					genTrans(pSet, tIn);

				}
				// end for

			}
			// end if

		}
		// end while

		// return(P',T')
		return unf;
		// end function
	}

	// procedure GenTrans(S', T)
	private void genTrans(Set<Place> sPrime, Set<TransitionHLAPI> T)
			throws Exception {
		// if not exists t \in T such that place(S') \subseteq inputs of t then
		// return
		// TODO: Wenn es keine möglichen Erweiterungen gibt, abbrechen
		// if Predecessors(S') has forward conflict then return
		// TODO: forward conflict (= zwei t mit nicht leerem Schnitt ihrer inputs), abbrechen
		// forall t E T do
		for (TransitionHLAPI t : T) {
			// if place(S') = inputs of t then
			if (sPrime.equals(inputs(t))) {
				// add t' = (t, S') to set T'
				Transition tPrime = new Transition(t, sPrime);
				unf.addTransition(tPrime);
				// insert t' in Q' in order of |LocalConfig(t')|
				queue.add(tPrime);
			}
		}
		// end for

		// TODO: clarify, what 'older' means
		// for all p' in P['] where p' older than any member of S' do
		// GenTrans(S' U p', 7")

		// end procedure
	}

	// function IsCutoffPoint?(t1')
	private boolean isCutOffPoint(Transition t1Prime) {
		// TODO: implement
		// C1' = LocalConfig(t1')
		// S1' = FinalState(C1')
		// L' = HashTahle[HashFun(S1')]
		// forall t2' in L' do
		// C2' = LocalConfig(t2')
		// if S1' == FinalState(C2') and Size(C2') < Size(C1') then return(1)
		// end for
		// add t1' to HashTable[HashFun(S1')]
		// return(0)
		return false;
		// end function
	}

	private Set<Transition> localConfig(Transition tPrime) {
		// TODO: implement
		// return(Predecessors({t'}) cut T')
		// end
		return null;
	}

	private Set<Place> predecessors(Place P) {
		// TODO: implement
		// do
		// S' = S' u preds(S')
		// until S' unchanged
		return null;
	}

	private Set<Place> finalState(Set<Transition> c) {
		// TODO: implement
		// let S' be the set of all p' E P' such that preds(p') \subseteq C'
		// return(place(S' - preds(C'))
		return null;
	}

	private Set<PlaceHLAPI> outputs(TransitionHLAPI trans) throws Exception {
		Set<PlaceHLAPI> outputs = new HashSet<PlaceHLAPI>();
		for (ArcHLAPI arc : trans.getOutArcsHLAPI()) {
			NodeHLAPI node = arc.getTargetHLAPI();
			if (!(node instanceof PlaceHLAPI)) {
				throw new Exception("output of transition is not a place!");
			}
			outputs.add((PlaceHLAPI) node);
		}
		return outputs;
	}

	private Set<PlaceHLAPI> inputs(TransitionHLAPI trans) throws Exception {
		Set<PlaceHLAPI> inputs = new HashSet<PlaceHLAPI>();
		for (ArcHLAPI arc : trans.getInArcsHLAPI()) {
			NodeHLAPI node = arc.getSourceHLAPI();
			if (!(node instanceof PlaceHLAPI)) {
				throw new Exception("output of transition is not a place!");
			}
			inputs.add((PlaceHLAPI) node);
		}
		return inputs;
	}

}
