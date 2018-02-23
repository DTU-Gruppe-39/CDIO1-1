package data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author Grp22
 * Database access object
 * Handles database access
 */
public class UserDBDAO implements IUserDAO{

	/**
	 * gets user information and saves in a UserDTO objekt and transfered back to method call.
	 */
	@Override
	public UserDTO getUser(int userId) throws DALException {		
		ResultSet rs = Connector.doQuery("SELECT * FROM personer WHERE userID = " + userId);
	    try {
	    
	    	if (!rs.first()) throw new DALException("Personen med ID: " + userId + " findes ikke"); //rs.first() returns false if tuple doesn't exist,
	    	// so it returns false and becomes true in the if statement and then throws exception
	    	
	    	return new UserDTO (rs.getInt("userID"), rs.getString("userName"), rs.getString("ini"), Arrays.asList(rs.getString("roles").split(", ")), rs.getString("cpr"), rs.getString("passwd"));
	    
	    }
	    catch (SQLException e) {throw new DALException(e.getMessage(), e); }
	}

	/**
	 * gets user information from all database rows and puts into a list of UserDTO objekts and returns to method caller.
	 */
	@Override
	public List<UserDTO> getUserList() throws DALException {
		// TODO Auto-generated method stub
		List<UserDTO> list = new ArrayList<UserDTO>();
		ResultSet rs = Connector.doQuery("SELECT * FROM personer");
		try
		{
			while (rs.next()) 
			{
				list.add(new UserDTO(rs.getInt("userID"), rs.getString("userName"), rs.getString("ini"), Arrays.asList(rs.getString("roles").split(", ")), rs.getString("cpr"), rs.getString("passwd")));
			}
		}
		catch (SQLException e) { throw new DALException(e.getMessage(), e); }
		return list;
	}

	/**
	 * takes a UserDTO objekt and saved a new user with the objects information into the database.
	 */
	@Override
	public void createUser(UserDTO user) throws DALException {
		// TODO Auto-generated method stub
		Connector.doUpdate(	//NEEDS TO BE SETUP------------------------------------------------------------------------
		"INSERT INTO personer(userName, ini, roles, cpr, passwd) VALUES " + 
		"('" + user.getUserName() + "',"
				+ " '" + user.getIni() + 
		"',"
		+ " '" + Arrays.toString(user.getRoles().toArray())  + "',"
				+ " '" + user.getCpr() + "',"
						+ " '" + user.getPassword() + "');"
		);
		
		
	}

	/**
	 * Takes a UserDTO object and updates an already existing database row with the objects information
	 */
	@Override
	public void updateUser(UserDTO user) throws DALException {
		// TODO Auto-generated method stub
		Connector.doUpdate(	//NEEDS TO BE SETUP------------------------------------------------------------------------
				"UPDATE personer SET " + 
				 "userName = '" + user.getUserName() + "', ini = '" + user.getIni() + "', " + 
				"roles = '" + Arrays.toString(user.getRoles().toArray()) + "', cpr ='" + user.getCpr() + "', passwd = '" + user.getPassword() + "'" + 
				"WHERE userID = " + user.getUserId() + ";"
				);
	}

	/**
	 * deletes a row in the database with a given userId.
	 */
	@Override
	public void deleteUser(int userId) throws DALException {
		// TODO Auto-generated method stub
		Connector.doUpdate(	//NEEDS TO BE SETUP------------------------------------------------------------------------
				"DELETE FROM personer WHERE userID = " + userId + ";"
				);
	}
}