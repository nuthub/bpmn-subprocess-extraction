package edu.udo.cs.ls14.jf.nlp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Lemmatizer {

	private static final Logger LOG = LoggerFactory.getLogger(Lemmatizer.class);
	private static final String DRIVER = "org.sqlite.JDBC";
	private static final String CONNECTION = "jdbc:sqlite:"
			+ Lemmatizer.class.getResource("lemmatizer.sqlite").getPath();
	private static Connection c = null;

	private static void connect() throws ClassNotFoundException, SQLException {
		Class.forName(DRIVER);
		LOG.debug("Trying to connect to " + CONNECTION);
		c = DriverManager.getConnection(CONNECTION);
		LOG.info("Connected to " + CONNECTION);
	}

	public static String lemmatize(String language, String word)
			throws Exception {
		if (language != "de") {
			throw new Exception("No baseform information for language "
					+ language + " available.");
		}
		if (c == null) {
			connect();
		}
		Statement statement;
		statement = c.createStatement();
		ResultSet resultSet = null;
		String query = "SELECT grundform FROM word_mapping "
				+ "WHERE wortform = '" + word + "' LIMIT 0,1;";
		LOG.debug("Query: " + query);
		resultSet = statement.executeQuery(query);

		if (resultSet.next()) {
			String result = resultSet.getString("grundform");
			LOG.debug("Returning result: " + result);
			return result;
		}
		LOG.debug("Returning query word: " + word);
		return word;
	}
}
