package test;


import java.sql.SQLException;
import java.util.List;

import data.Connector;


public class DBTester {
	//TODO refactor as JUnit test???
	public static void main(String[] args) 
	{
		try {
			Connector ct = new Connector();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			System.out.println("failed at main");
		}
		
	}
}
