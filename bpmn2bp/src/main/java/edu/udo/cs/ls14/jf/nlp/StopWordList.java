package edu.udo.cs.ls14.jf.nlp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StopWordList {

	private static final Logger LOG = LoggerFactory
			.getLogger(StopWordList.class);

	private static final String DRIVER = "org.sqlite.JDBC";
	private static final String CONNECTION = "jdbc:sqlite:"
			+ Lemmatizer.class.getResource("stopwords.sqlite").getPath();
	private static Connection c = null;

	private static void connect() throws ClassNotFoundException, SQLException {
		Class.forName(DRIVER);
		LOG.debug("Trying to connect to " + CONNECTION);
		c = DriverManager.getConnection(CONNECTION);
		LOG.info("Connected to " + CONNECTION);
	}

	public static boolean contains(String language, String word)
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
		ResultSet resultSet = statement
				.executeQuery("SELECT word FROM stopwords "
						+ "WHERE language = '" + language + "' AND word LIKE '"
						+ word + "';");

		if (resultSet.next()) {
			return true;
		}
		return false;
	}

}
