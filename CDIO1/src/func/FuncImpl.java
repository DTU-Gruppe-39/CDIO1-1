package func;

import java.util.List;
import java.security.SecureRandom;

import data.IUserDAO;
import data.IUserDAO.DALException;
import data.UserDTO;

public class FuncImpl implements IFuncImpl{
	//For generating password
	
	private static SecureRandom random = new SecureRandom();
	private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
	private static final String NUMERIC = "0123456789";
	private static final String SPECIAL = ".-_+!?=";

	IUserDAO d;
	UserDTO dt;
	
	public FuncImpl(IUserDAO d)
	{
		this.d = d; 
	}

	@Override
	public UserDTO getUser(int userId) throws DALException {
		UserDTO user = this.d.getUser(userId);
		return user;
	}

	@Override
	public List<UserDTO> getUserList() throws DALException {
		List<UserDTO> list = this.d.getUserList();
		return list;
	}

	@Override
	public void createUser(String userName, String ini, List<String> roles, String cpr) throws DALException {
		// Creates a UserDTO and sets the attributes to the corresponding parameters.
		this.dt = new UserDTO();    
		this.dt.setUserName(userName);
		this.dt.setIni(ini);
		this.dt.setRoles(roles);
		this.dt.setCpr(cpr);
		String password = createPass(6, UPPER + LOWER + SPECIAL + NUMERIC);
		this.dt.setPassword(password);
		this.d.createUser(this.dt);
	}

	@Override
	public void updateUser(int userId, String userName, String ini, List<String> roles, String cpr) throws DALException {
		// Updates the user by getting the user and then set the users attributes to the corresponding parameters.
		UserDTO user = getUser(userId);
			user.setUserName(userName);              
			user.setIni(ini);
			user.setRoles(roles);
			user.setCpr(cpr);
			this.d.updateUser(user);	
	}

	@Override
	public void deleteUser(int userId) throws DALException {
		// Deletes the user by getting the user and if the user exists then it calls the deleteUser method of the UserDBDAO.
		UserDTO user = getUser(userId);
//		System.out.println(user);
		if(user != null)
		{
			this.d.deleteUser(userId);	
		}
	}

	@Override
	public void exit() {

	}
	/**
	 * Method will generate random string based on the parameters
	 * 
	 * @param len
	 *            the length of the random string
	 * @param dic
	 *            the dictionary used to generate the password
	 * @return the random password
	 */
	@Override
	public String createPass(int len, String dic) {

		String result = "";
		for (int i = 0; i < len; i++) {
			int index = random.nextInt(dic.length());
			result += dic.charAt(index);
		}
		return result;
	}
}

//tostring

