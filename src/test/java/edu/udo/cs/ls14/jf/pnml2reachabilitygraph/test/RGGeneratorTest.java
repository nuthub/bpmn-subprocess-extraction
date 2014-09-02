package edu.udo.cs.ls14.jf.pnml2reachabilitygraph.test;

import java.io.File;

import javax.swing.JFrame;

import org.junit.Test;

import edu.udo.cs.ls14.jf.pnml2reachabilitygraph.ReachabilityGraph;
import edu.udo.cs.ls14.jf.pnml2reachabilitygraph.ReachabilityGraphVisualizer;
import edu.udo.cs.ls14.jf.pnml2reachabilitygraph.model.Trace;

public class RGGeneratorTest {

	@Test
	public void testRG_PM1() throws Exception {
		testGenerateRG("PM1-mit-Fragment1");
	}
	
	@Test
	public void testPM1() throws Exception {
		testGenerateRG("PM2-mit-Fragment1");		
	}
	
	@Test
	public void testXor() throws Exception {
		testGenerateRG("xor-example");
	}
	
	@Test
	public void testLoop() throws Exception {
		testGenerateRG("looping-events-example");
	}
	
	private ReachabilityGraph testGenerateRG(String basename) throws Exception {
		System.out.println("Now testing " + basename);
		ReachabilityGraph rg = new ReachabilityGraph();
		File file = new File(getClass().getResource(basename + ".pnml").toURI());
		rg.createFromPnml(file);
		System.out.println("|V| = " + rg.getVertices().size());
		System.out.println("|E| = " + rg.getEdges().size());
		for(Trace t: rg.getTraces()) {
			System.out.println(t);
		}
//		rg.saveGraphML("/tmp/" + BASENAME + ".graphml");
		return rg;
	}


	public static void main(String[] args) throws Exception {
		RGGeneratorTest rgtest = new RGGeneratorTest();

		System.out.println("PM1");
		ReachabilityGraph graph1 = rgtest.testGenerateRG("PM1-mit-Fragment1");
		
		System.out.println("PM2");
		ReachabilityGraph graph2 = rgtest.testGenerateRG("PM2-mit-Fragment1");
		
		System.out.println("xor");
		ReachabilityGraph graph3 = rgtest.testGenerateRG("xor-example");
		
		System.out.println("loop");
		ReachabilityGraph graph4 = rgtest.testGenerateRG("looping-events-example");
		
		 JFrame frame = new ReachabilityGraphVisualizer().visualize(graph1);
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 frame.setVisible(true);
//		System.exit(0);
	}
}
