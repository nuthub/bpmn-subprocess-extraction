package edu.udo.cs.ls14.jf.unfolding.mcmillan;

import java.util.Set;

import fr.lip6.move.pnml.ptnet.hlapi.PlaceHLAPI;

public class Place {

	private PlaceHLAPI place;
	private Set<Transition> preds;
	
	public Place(PlaceHLAPI place, Set<Transition> preds) {
		super();
		this.place = place;
		this.preds = preds;
	}

	public PlaceHLAPI getPlace() {
		return place;
	}

	public void setPlace(PlaceHLAPI place) {
		this.place = place;
	}

	public Set<Transition> getPreds() {
		return preds;
	}

	public void setPreds(Set<Transition> preds) {
		this.preds = preds;
	}
	
	
}
