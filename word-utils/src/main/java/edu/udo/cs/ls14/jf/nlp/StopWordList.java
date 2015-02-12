package edu.udo.cs.ls14.jf.nlp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represents a list of stop words.
 * 
 * @author Julian Flake
 *
 */
public class StopWordList {

	private static final Logger LOG = LoggerFactory
			.getLogger(StopWordList.class);

	private static final String DRIVER = "org.sqlite.JDBC";
	private static final String CONNECTION = "jdbc:sqlite::resource:edu/udo/cs/ls14/jf/nlp/stopwords.sqlite";
	private static Connection c = null;

	private static void connect() throws ClassNotFoundException, SQLException {
		Class.forName(DRIVER);
		LOG.info("Trying to connect to " + CONNECTION);
		c = DriverManager.getConnection(CONNECTION);
		LOG.info("Connected to " + CONNECTION);
	}

	/**
	 * Returns true if a given word is contained in the language's list of words
	 * to ignore.
	 * 
	 * @param language
	 *            language to consider
	 * @param word
	 *            given word
	 * @return true, iff word is stop word for the language
	 */
	public static boolean contains(String language, String word) {
		try {
			if (c == null) {
				connect();
			}
			Statement statement;
			statement = c.createStatement();
			ResultSet resultSet = statement
					.executeQuery("SELECT word FROM stopwords "
							+ "WHERE language = '" + language
							+ "' AND word LIKE '" + word + "';");

			if (resultSet.next()) {
				return true;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return false;
	}

}
