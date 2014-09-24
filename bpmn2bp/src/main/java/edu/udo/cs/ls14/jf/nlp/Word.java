package edu.udo.cs.ls14.jf.nlp;

import java.sql.SQLException;

public class Word {

	private String language = null;
	private String word = null;
	private String baseform = null;
	private WordType type = null;

	public Word(String language, String word) {
		super();
		this.language = language;
		this.word = word;
	}

	public String getLanguage() {
		return language;
	}

	public String getWord() {
		return word;
	}

	public boolean isSynonymWith(Word word2) throws ClassNotFoundException, SQLException {
		return OpenThesaurus.areSynonyms(getBaseform(), word2.getBaseform());
	}

	public String getBaseform() {
		if (baseform == null) {
			try {
				baseform = Lemmatizer.lemmatize(language, word);
			} catch (Exception e) {
				System.err.println("Cannot get baseform of word " + word
						+ " (language = " + language + ")");
				e.printStackTrace();
			}
		}
		return baseform;
	}

	public WordType getType() {
		if (type == null) {
			try {
				if (StopWordList.contains(language, word)) {
					type = WordType.STOPWORD;
				} else {
					type = WordType.NORMAL;
				}
			} catch (Exception e) {
				System.err.println("Cannot load stopword list for language "
						+ language);
				e.printStackTrace();
				type = WordType.UNKNOWN;
			}
		}
		return type;
	}

}
