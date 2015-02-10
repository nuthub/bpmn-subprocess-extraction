package edu.udo.cs.ls14.jf.nlp.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import org.junit.Test;

import edu.udo.cs.ls14.jf.nlp.StopWordList;
import edu.udo.cs.ls14.jf.nlp.Word;
import edu.udo.cs.ls14.jf.nlp.WordType;

public class WordTest {

	@Test
	public void testWaren() {
		Word w = new Word("de", "Waren");
		assertEquals("de", w.getLanguage());
		assertEquals("Waren", w.getWord());
		assertEquals("Ware", w.getBaseform());
		assertEquals(WordType.NORMAL, w.getType());
	}

	@Test
	public void testGüter() throws ClassNotFoundException, SQLException {
		Word w = new Word("de", "Güter");
		assertEquals("de", w.getLanguage());
		assertEquals("Güter", w.getWord());
		assertEquals("Gut", w.getBaseform());
		assertEquals(WordType.NORMAL, w.getType());
		assertTrue(w.isSynonymWith(new Word("de", "Ware")));
	}
	
	@Test
	public void testSynonymity() throws ClassNotFoundException, SQLException {
		Word w1 = new Word("de", "Waren");
		Word w2 = new Word("de", "Güter");
		assertFalse(w1.getType()==WordType.STOPWORD);
		assertFalse(w2.getType()==WordType.STOPWORD);
		assertTrue(w1.isSynonymWith(w2));
		assertTrue(w2.isSynonymWith(w1));
	}
	
	@Test
	public void testStopWords() {
		Word w1 = new Word("de", "die");
		assertEquals(w1.getType(), WordType.STOPWORD);
		Word w2 = new Word("de", "Artikel");
		assertEquals(w2.getType(), WordType.NORMAL);
		assertEquals(w2.getType(), WordType.NORMAL);
	}
}
