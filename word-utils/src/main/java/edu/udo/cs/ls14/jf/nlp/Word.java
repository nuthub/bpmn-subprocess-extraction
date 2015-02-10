package edu.udo.cs.ls14.jf.nlp;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Word {

	private static final Logger LOG = LoggerFactory.getLogger(Word.class);

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

	public boolean isSynonymWith(Word word2) throws ClassNotFoundException,
			SQLException {
		return OpenThesaurusSQLite.areSynonyms(getBaseform(),
				word2.getBaseform());
	}

	public String getBaseform() {
		if (baseform == null) {
			baseform = Lemmatizer.lemmatize(language, word);
		}
		return baseform;
	}

	public WordType getType() {
		if (type == null) {
			if (StopWordList.contains(language, word)) {
				type = WordType.STOPWORD;
			} else {
				type = WordType.NORMAL;
			}
		}
		return type;
	}

}
