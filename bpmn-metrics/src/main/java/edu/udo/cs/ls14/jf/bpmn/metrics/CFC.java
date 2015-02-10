package edu.udo.cs.ls14.jf.bpmn.metrics;

import java.util.Collection;

import org.eclipse.bpmn2.ExclusiveGateway;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.GatewayDirection;
import org.eclipse.bpmn2.InclusiveGateway;
import org.eclipse.bpmn2.ParallelGateway;

/**
 * Class that calculates Cardoso's CFC of a set of given and connected
 * BPMN-FlowElements.
 * 
 * @author Julian Flake
 *
 */
public class CFC {

	/**
	 * Return Cardoso's CFC for connected flow elements.
	 * 
	 * @param elements
	 *            Connected FlowElements
	 * @return the CFC
	 */

	public static int getComplexity(Collection<FlowElement> elements) {
		return cfcXorSplits(elements) + cfcOrSplits(elements)
				+ cfcAndSplits(elements);
	}

	private static int cfcXorSplits(Collection<FlowElement> elements) {
		return elements
				.stream()
				.filter(e -> e instanceof ExclusiveGateway)
				.map(e -> ((ExclusiveGateway) e))
				.filter(g -> g.getGatewayDirection() == GatewayDirection.DIVERGING)
				.mapToInt(g -> g.getOutgoing().size()).sum();
	}

	private static int cfcOrSplits(Collection<FlowElement> elements) {
		return elements
				.stream()
				.filter(e -> e instanceof InclusiveGateway)
				.map(e -> ((InclusiveGateway) e))
				.filter(g -> g.getGatewayDirection() == GatewayDirection.DIVERGING)
				.mapToInt(g -> (int) Math.pow(2, g.getOutgoing().size()) - 1)
				.sum();
	}

	private static int cfcAndSplits(Collection<FlowElement> elements) {
		return elements
				.stream()
				.filter(e -> e instanceof ParallelGateway)
				.map(e -> (ParallelGateway) e)
				.filter(g -> g.getGatewayDirection() == GatewayDirection.DIVERGING)
				.mapToInt(g -> 1).sum();
	}
}
