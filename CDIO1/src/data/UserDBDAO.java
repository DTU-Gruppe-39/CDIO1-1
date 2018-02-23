package data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserDBDAO implements IUserDAO{

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

	@Override
	public void createUser(UserDTO user) throws DALException {
		System.out.println("Mellem dem");
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

	public int retrieveUserId() throws DALException {
		ResultSet rs =  Connector.doQuery("SELECT MAX(userID) from personer;");
		try {
			System.out.println("jhfsdaf");
			return rs.getInt("userID");
		} catch (SQLException e) {
			System.out.println("FAILED trying to get userID when creating user");
			e.printStackTrace();
		}
		System.out.println(-1);
		return -1;
	}
	@Override
	public void updateUser(UserDTO user) throws DALException {
		Connector.doUpdate(	//NEEDS TO BE SETUP------------------------------------------------------------------------
				"UPDATE personer SET " + 
				 "userName = '" + user.getUserName() + "', ini = '" + user.getIni() + "', " + 
				"roles = '" + Arrays.toString(user.getRoles().toArray()) + "', cpr ='" + user.getCpr() + "', passwd = '" + user.getPassword() + "'" + 
				"WHERE userID = " + user.getUserId() + ";"
				);
	}

	@Override
	public void deleteUser(int userId) throws DALException {
		Connector.doUpdate(	//NEEDS TO BE SETUP------------------------------------------------------------------------
				"DELETE FROM personer WHERE userID = " + userId + ";"
				);
	}
}