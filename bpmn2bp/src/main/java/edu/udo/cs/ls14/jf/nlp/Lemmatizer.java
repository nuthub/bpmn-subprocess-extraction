package edu.udo.cs.ls14.jf.nlp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Lemmatizer {

	private static final String DRIVER = "org.sqlite.JDBC";
	private static final String CONNECTION = "jdbc:sqlite:"
			+ Lemmatizer.class.getResource("lemmatizer.sqlite").getPath();
	private static Connection c = null;

	private static void connect() throws ClassNotFoundException, SQLException {
		Class.forName(DRIVER);
		System.out.println("Trying to connect to " + CONNECTION);
		c = DriverManager.getConnection(CONNECTION);
		System.out.println("Opened database successfully");
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
		// resultSet = statement
		// .executeQuery("SELECT COUNT(grundform) FROM word_mapping WHERE wortform='"
		// + word + "';");
		// resultSet.next();
		// System.out.println(word + " hat " +
		// resultSet.getInt("COUNT(grundform)") + " Grundformen:");
		resultSet = statement
				.executeQuery("SELECT grundform FROM word_mapping "
						+ "WHERE wortform = '" + word + "' LIMIT 0,1;");

		if (resultSet.next()) {
			// System.out.println(resultSet.getString("grundform"));
			return resultSet.getString("grundform");
		}
		return word;
	}
}
