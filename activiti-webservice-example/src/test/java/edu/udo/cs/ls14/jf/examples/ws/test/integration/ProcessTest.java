package edu.udo.cs.ls14.jf.examples.ws.test.integration;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import edu.udo.cs.ls14.jf.examples.ws.Process;

public class ProcessTest {

	@Test
	public void testProcess() throws Exception {
		Process analyzer = new Process();
		analyzer.init();
		String testString = "This is my test string";
		int result = analyzer.run(testString);
		assertEquals(testString.length(), result);
	}

}
