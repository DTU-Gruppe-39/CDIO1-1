package ui;

import data.IUserDAO.DALException;
import func.FuncImpl;
import func.IFuncImpl;

public interface IUI {
	void run() throws DALException;
	void scenario();
	void createUser();
	void updateUser();
	void getUser();
	void getUserList();
	void deleteUser();
}
