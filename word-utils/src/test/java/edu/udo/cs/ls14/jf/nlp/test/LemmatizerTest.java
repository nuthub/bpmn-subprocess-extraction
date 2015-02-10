package edu.udo.cs.ls14.jf.nlp.test;

import org.junit.Test;

import edu.udo.cs.ls14.jf.nlp.Lemmatizer;

public class LemmatizerTest {
	@Test
	public void testLemmatizer() throws Exception {
		runTest("Güter");
		runTest("Waren");
		runTest("Artikel");
		runTest("verpackten");
		runTest("Versand");
		runTest("Rechnung");
		runTest("Rechnungen");
		runTest("Lieferschein");
		runTest("erstellten");
		runTest("verschickten");
	}

	@Test
	public void testConstructor() {
		new Lemmatizer();
	}

	@Test(expected = Exception.class)
	public void testLanguage() {
		Lemmatizer.lemmatize("en", "dog");
	}

	@Test(expected = Exception.class)
	public void testInjection() {
		Lemmatizer.lemmatize("de", "inject'");
	}

	private void runTest(String word) throws Exception {
		Lemmatizer.lemmatize("de", word);
		// System.out.println(word + ": " + );
	}

}
