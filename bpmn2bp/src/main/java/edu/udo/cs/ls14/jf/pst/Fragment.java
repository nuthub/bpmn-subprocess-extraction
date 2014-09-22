package edu.udo.cs.ls14.jf.pst;

import org.eclipse.bpmn2.SequenceFlow;

public class Fragment {

	private SequenceFlow entry;
	private SequenceFlow exit;
	private Fragment parent;

	public Fragment(SequenceFlow entry, SequenceFlow exit) {
		this.entry = entry;
		this.exit = exit;
	}

	public SequenceFlow getEntry() {
		return entry;
	}

	public SequenceFlow getExit() {
		return exit;
	}

	public void setParent(Fragment parent) {
		this.parent = parent;
	}

	public Fragment getParent() {
		return parent;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Fragment (");
		if (entry == null) {
			sb.append("null");
		} else {
			sb.append(entry.getName());
		}
		sb.append(", ");
		if (exit == null) {
			sb.append("null");
		} else {
			sb.append(exit.getName());
		}
		sb.append(")");
		return sb.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((entry == null) ? 0 : entry.hashCode());
		result = prime * result + ((exit == null) ? 0 : exit.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fragment other = (Fragment) obj;
		if (entry == null) {
			if (other.entry != null)
				return false;
		} else if (!entry.equals(other.entry))
			return false;
		if (exit == null) {
			if (other.exit != null)
				return false;
		} else if (!exit.equals(other.exit))
			return false;
		return true;
	}

}
