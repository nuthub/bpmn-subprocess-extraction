package edu.udo.cs.ls14.jf.nlp.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Ignore;
import org.junit.Test;

import edu.udo.cs.ls14.jf.nlp.Wortschatz;

@Ignore
public class WortschatzTest {

	@Test
	public void testSynonyms() throws Exception {
		assertTrue(Wortschatz.synonyms("de", "Güter", 100).contains("Ware"));
		assertTrue(Wortschatz.synonyms("de",
				Wortschatz.baseform("de", "Güter"), 100).contains("Ware"));
		assertTrue(Wortschatz.synonyms("de", "Artikel", 100).contains("Ware"));
		assertTrue(Wortschatz.synonyms("de", "Ware", 100).contains("Artikel"));
		assertTrue(!Wortschatz.synonyms("de", "Waren", 100).contains("Ware"));
		assertTrue(Wortschatz.synonyms("de",
				Wortschatz.baseform("de", "Waren"), 100).contains("Artikel"));
	}

	@Test
	public void testBaseform() throws Exception {
		assertEquals("Ware", Wortschatz.baseform("de", "Waren"));
		assertEquals("Gut", Wortschatz.baseform("de", "Güter"));
		assertEquals("versenden", Wortschatz.baseform("de", "versende"));
		assertEquals(null, Wortschatz.baseform("de", "GibsnIcht"));
	}

	@Test
	public void testSimilarity() throws Exception {
		Wortschatz.similarity("de", "Waren", 100);
	}

	@Test
	public void testThesaurus() throws Exception {
		Wortschatz.thesaurus("de", "Ware", 100);
	}

}
