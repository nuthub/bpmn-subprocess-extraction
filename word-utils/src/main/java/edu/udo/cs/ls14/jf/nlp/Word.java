package edu.udo.cs.ls14.jf.nlp;

import java.sql.SQLException;

/**
 * Represents a word.
 * 
 * @author Julian Flake
 *
 */
public class Word {

	private String language = null;
	private String word = null;
	private String baseform = null;
	private WordType type = null;

	/**
	 * Constructor that takes language and the word as string.
	 * 
	 * @param language
	 *            the language
	 * @param word
	 *            the word as string
	 */
	public Word(String language, String word) {
		super();
		this.language = language;
		this.word = word;
	}

	/**
	 * Returns the language.
	 * 
	 * @return language
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * Returns the word as String
	 * 
	 * @return the word as String
	 */
	public String getWord() {
		return word;
	}

	/**
	 * Checks whether this word is synonym with another word.
	 * 
	 * @param that
	 *            the other word
	 * @return true, iff both words are synonym
	 * @throws ClassNotFoundException
	 *             if SQLite driver is not on claspath
	 * @throws SQLException
	 *             if an SQL relaed error occurs
	 */
	public boolean isSynonymWith(Word that) throws ClassNotFoundException,
			SQLException {
		return OpenThesaurusSQLite.areSynonyms(getBaseform(),
				that.getBaseform());
	}

	/**
	 * Returns the base form of this word.
	 * 
	 * @return base form of this word
	 */
	public String getBaseform() {
		if (baseform == null) {
			baseform = Lemmatizer.lemmatize(language, word);
		}
		return baseform;
	}

	/**
	 * Returns the type of this word. Type can be StopWord or Normal word.
	 * 
	 * @return type of this word
	 */
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
