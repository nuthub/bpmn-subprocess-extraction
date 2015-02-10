package edu.udo.cs.ls14.jf.nlp.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import edu.udo.cs.ls14.jf.nlp.StopWordList;

public class StopWordListTest {

	@Test
	public void testConstructor() {
		new StopWordList();
	}
	
	@Test
	public void testStopWordListDE() throws Exception {
		assertTrue(StopWordList.contains("de", "der"));
		assertTrue(StopWordList.contains("de", "die"));
		assertTrue(StopWordList.contains("de", "das"));
		assertTrue(StopWordList.contains("de", "ein"));
		assertTrue(StopWordList.contains("de", "Den"));
	}
}
