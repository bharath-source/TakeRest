package com.test.db;

// Get Database Connection

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection
{
	public Connection getConnection()
	{
		Connection connection = null;
		
		try
		{
			String connectionUrl = "jdbc:mysql://localhost:3306/maindb";
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(connectionUrl, "test", "test");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return connection;
	}
}