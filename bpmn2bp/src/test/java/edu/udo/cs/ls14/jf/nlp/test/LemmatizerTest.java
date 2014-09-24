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

	private void runTest(String word) throws Exception {
		System.out.println(word + ": " + Lemmatizer.lemmatize("de", word));
	}

}
