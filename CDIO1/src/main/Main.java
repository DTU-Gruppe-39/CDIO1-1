package main;

import java.sql.SQLException;

import data.Connector;
import data.IUserDAO;
import data.IUserDAO.DALException;
import data.UserDBDAO;
import func.FuncImpl;
import func.IFuncImpl;
import ui.IUI;
import ui.TUI;

public class Main {

	public static void main(String[] args) throws DALException {
		// TODO Auto-generated method stub
		IUserDAO d = new UserDBDAO();
		IFuncImpl f = new FuncImpl(d);
		IUI i = new TUI(f);
		try {
			Connector ct = new Connector();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			System.out.println("failed at main");
		}
		i.run();
	}

}
