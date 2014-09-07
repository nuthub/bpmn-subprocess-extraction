package edu.udo.cs.ls14.jf.unfolding.mcmillan;

import java.util.Set;

import fr.lip6.move.pnml.ptnet.hlapi.TransitionHLAPI;

public class Transition {

	private TransitionHLAPI trans;
	private Set<Place> preds;

	public Transition(TransitionHLAPI trans, Set<Place> preds) {
		super();
		this.trans = trans;
		this.preds = preds;
	}

	public TransitionHLAPI getTrans() {
		return trans;
	}

	public void setTrans(TransitionHLAPI trans) {
		this.trans = trans;
	}

	public Set<Place> getPreds() {
		return preds;
	}

	public void setPreds(Set<Place> preds) {
		this.preds = preds;
	}

}
