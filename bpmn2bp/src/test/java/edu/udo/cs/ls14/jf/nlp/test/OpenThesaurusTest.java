package edu.udo.cs.ls14.jf.nlp.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import org.junit.Test;

import edu.udo.cs.ls14.jf.nlp.OpenThesaurus;

public class OpenThesaurusTest {

	@Test
	public void testSynonyms() throws ClassNotFoundException, SQLException {
		assertSynonyms("Güter", "Waren");
		assertSynonyms("Waren", "Artikel");
		assertSynonyms("Artikel", "Güter");

		assertSynonyms("Güter", "Artikel");
		assertSynonyms("Artikel", "Waren");
		assertSynonyms("Waren", "Güter");
		
		assertSynonyms("Ware", "Gut");

		assertSynonyms("versenden", "verschicken");
		assertSynonyms("senden", "schicken");

		assertSynonyms("erstellen", "anfertigen");

		assertSynonyms("Lieferschein", "Lieferschein");
}


	@Test
	public void testNonSynonyms() throws ClassNotFoundException, SQLException {
		assertNonSynonyms("Güter", "Gut");
		assertNonSynonyms("Ware", "Waren");
		assertNonSynonyms("Güter", "Ware");
		assertNonSynonyms("erstellen", "abfertigen");
	}
	
	private void assertNonSynonyms(String string1, String string2) throws ClassNotFoundException, SQLException {
		assertFalse(OpenThesaurus.areSynonyms(string1, string2));

	}
	private void assertSynonyms(String string1, String string2) throws ClassNotFoundException, SQLException {
		assertTrue(OpenThesaurus.areSynonyms(string1, string2));
	}

}
