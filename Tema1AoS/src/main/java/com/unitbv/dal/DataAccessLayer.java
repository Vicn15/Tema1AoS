package com.unitbv.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataAccessLayer {

	private Connection getConnection() {
		Connection conn;

		try {
			conn = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/aosLab1DB", "postgres", "1q2w3e");
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return conn;
	}

	public void insertPerson(String name, String email) {
		String query = "INSERT INTO person(name, email) VALUES (?,?);";

		try (Connection conn = getConnection(); PreparedStatement preparedStatement = conn.prepareStatement(query)) {
			conn.setAutoCommit(false);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, email);
			preparedStatement.addBatch();
			preparedStatement.executeBatch();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
