package data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import data.IUserDAO.DALException;


/**
 * 
 * @author Grp22
 * 
 */
public class Connector
{
	/**
	 * Connects to a given database, on a MySQL object called Connection, that is, the return type is of "Connection"
	 */
	public static Connection connectToDatabase(String url, String username, String password)
			throws InstantiationException, IllegalAccessException,
					ClassNotFoundException, SQLException
	{
		// call the driver class' no argument constructor
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		
		// get Connection-object via DriverManager
        return (Connection) DriverManager.getConnection(url, username, password);
	}
	
	//Variables for use in Connector constructor
	private static Connection conn;
	private static Statement stm;
	
	/**
	 * Tries to connect to a given database. if so desired. 
	 * also automatically called by constructor Connector() with pre-given information defined in Constant
	 * @param server - ex. localhost
	 * @param port - ex 3306
	 * @param database - ex localDBGrp22
	 * @param username - ex root
	 * @param password - ex ""
	 */
	public Connector(String server, int port, String database,
			String username, String password)
				throws InstantiationException, IllegalAccessException,
					ClassNotFoundException, SQLException
	{	
		executeDB(); //automatically creates a database
		
		conn	= connectToDatabase("jdbc:mysql://"+server+":"+port+"/"+database,
					username, password);
		System.out.println("sucessfully initiated driver");
		stm		= conn.createStatement();
	}
	
	/**
	 * Executes Sql statements to create a database in case user doesn't have one already.
	 */
	public void executeDB() throws SQLException
	{
		try 
		{
			Connection conn = DriverManager.getConnection("jdbc:mysql://" + Constant.server + "/?user=" + Constant.username + "&password=" + Constant.password);
			Statement s = conn.createStatement();
			s.executeUpdate("CREATE DATABASE IF NOT EXISTS cdio_2Semester;");
			s.executeUpdate("USE cdio_2Semester;");
			s.executeUpdate("CREATE TABLE IF NOT EXISTS personer (\n" + 
					"	userID int NOT NULL AUTO_INCREMENT,\n" + 
					"	userName varchar(48), \n" + 
					"        ini varchar(6),\n" + 
					"        roles varchar(36),\n" + 
					"        cpr varchar(11),\n" + 
					"        passwd varchar(64),\n" + 
					"        PRIMARY KEY (userID)\n" + 
					"                        );");
			conn.close();
		}
		catch (Exception e) {
			System.err.println("Failed to Execute");
		} 
	}
	
	/**
	 * calls the class constructor that actually creates the database Connection and executes a table.
	 */
	public Connector() throws InstantiationException, IllegalAccessException,
			ClassNotFoundException, SQLException
	{
		this(Constant.server, Constant.port, Constant.database,
				Constant.username, Constant.password); 
		//calls its own constructor that takes the same parameters, in other words:
		// the other constructor in this class.
	}
	
	/**
	 * executes a SQL command like Select
	 * doesn't execute update, insert, delete or create, use doUpdate instead.
	 */
	public static ResultSet doQuery(String cmd) throws data.IUserDAO.DALException
	{
		try { return stm.executeQuery(cmd); }
		catch (SQLException e) { throw new DALException(e.getMessage(),e); 
		}
	}
	
	/**
	 * executes a SQL command like update, insert, create and delete.
	 * doesn't execute select.
	 */
	public static int doUpdate(String cmd) throws DALException
	{
		try { return stm.executeUpdate(cmd); }
		catch (SQLException e) { throw new DALException(e.getMessage(),e);
		}
	}
}