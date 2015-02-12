package edu.udo.cs.ls14.jf.nlp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * OpenThesaurus interface.
 * 
 * @author Julian Flake
 *
 */
public class OpenThesaurusSQLite {

	private static final Logger LOG = LoggerFactory
			.getLogger(OpenThesaurusSQLite.class);

	private static final String DRIVER = "org.sqlite.JDBC";
	private static final String CONNECTION = "jdbc:sqlite::resource:edu/udo/cs/ls14/jf/nlp/openthesaurus.sqlite";
	private static Connection connection = null;

	private static void connect() throws SQLException, ClassNotFoundException {
		Class.forName(DRIVER);
		LOG.debug("Trying to connect to " + CONNECTION);
		connection = DriverManager.getConnection(CONNECTION);
		LOG.info("Connected to " + CONNECTION);
	}

	/**
	 * Returns true if two given words iff there is a synset, that contains both
	 * words.
	 * 
	 * @param word1
	 *            first given word
	 * @param word2
	 *            second given word
	 * @return true, iff there is a synset containing both words
	 * @throws ClassNotFoundException
	 *             if classpath does not contain SQLite driver
	 * @throws SQLException
	 *             if a SQL related error occurs
	 */
	public static boolean areSynonyms(String word1, String word2)
			throws ClassNotFoundException, SQLException {
		// SELECT t1.synset_id,t1.word,t2.word FROM term AS t1 LEFT JOIN term AS
		// t2 ON t1.synset_id=t2.synset_id WHERE t1.word='Gut' AND
		// t2.word='Ware';
		if (word1.equals(word2)) {
			return true;
		}
		if (connection == null) {
			connect();
		}
		Statement statement = connection.createStatement();
		String query = "SELECT t1.synset_id,t1.word,t2.word "
				+ "FROM term AS t1 "
				+ "LEFT JOIN term AS t2 ON t1.synset_id=t2.synset_id "
				+ "WHERE t1.word='" + word1 + "' AND t2.word='" + word2 + "';";
		ResultSet resultSet = statement.executeQuery(query);
		if (resultSet.next()) {
			return true;
		}
		return false;
	}
}
