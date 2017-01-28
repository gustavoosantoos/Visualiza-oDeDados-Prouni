package com.prouni.datavisualization.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	private static final String CLASS_NAME = 	"com.mysql.jdbc.Driver";
	private static final String URL =  			"jdbc:mysql://localhost/prouni?useSSL=true";
	private static final String USER = 			"root";
	private static final String PASS = 			"";
	
	public static Connection getConnection() throws SQLException, ClassNotFoundException{
		Class.forName(CLASS_NAME);
		return DriverManager.getConnection(URL, USER, PASS);
	}
	
}
