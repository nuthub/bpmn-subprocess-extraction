package edu.udo.cs.ls14.jf.nlp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO static vs. singleton vs. object
 * 
 * @author flake
 *
 */
public class OpenThesaurus {

	private static final Logger LOG = LoggerFactory.getLogger(OpenThesaurus.class);
	
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String CONNECTION = "jdbc:mysql://localhost/openthesaurus?user=openthesaurus&password=04dSv7H0";
	private static Connection connection = null;

	private static void connect() throws SQLException, ClassNotFoundException {
		Class.forName(DRIVER);
		LOG.debug("Trying to connect to " + CONNECTION);
		connection = DriverManager.getConnection(CONNECTION);
		LOG.info("Connected to " + CONNECTION);
	}

	public static boolean areSynonyms(String word1, String word2)
			throws ClassNotFoundException, SQLException {
		// SELECT t1.synset_id,t1.word,t2.word FROM term AS t1 LEFT JOIN term AS
		// t2 ON t1.synset_id=t2.synset_id WHERE t1.word='Gut' AND
		// t2.word='Ware';
		if(word1.equals(word2)) {
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
