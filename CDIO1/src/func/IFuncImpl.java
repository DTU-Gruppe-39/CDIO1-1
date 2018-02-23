package func;

import java.util.List;

import data.UserDTO;
import data.IUserDAO.DALException;

public interface IFuncImpl {
	UserDTO getUser(int userId) throws DALException; //return objekt and use toString in tui
	List<UserDTO> getUserList() throws DALException; //same concept
	void createUser(String userName, String ini, List<String> roles, String cpr) throws DALException; //create userobjekt in funcImpl and ask for neccesary data
	void updateUser(int userId, String userName, String ini, List<String> roles, String cpr) throws DALException; // same concept
	void deleteUser(int userId) throws DALException; //same concept
	void exit(); //Shuts down program
	String createPass(int len, String dic);
}
