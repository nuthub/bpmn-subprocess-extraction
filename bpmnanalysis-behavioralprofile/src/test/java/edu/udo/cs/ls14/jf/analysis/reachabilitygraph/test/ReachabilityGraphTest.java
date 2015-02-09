package edu.udo.cs.ls14.jf.analysis.reachabilitygraph.test;

import java.io.File;

import org.junit.Test;

import edu.udo.cs.ls14.jf.analysis.reachabilitygraph.ReachabilityGraph;

public class ReachabilityGraphTest {

	@Test
	public void test() throws Exception {
		String basename = "complete1-ptnet";
		File file = new File(getClass().getResource(
				"/pnml/" + basename + ".pnml").toURI());
		ReachabilityGraph rg = new ReachabilityGraph();
		rg.createFromPnml(file);
		System.out.println("|V| = " + rg.getVertices().size());
		System.out.println("|E| = " + rg.getEdges().size());
	

	}
	
}
