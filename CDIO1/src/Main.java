import dal.*;
import dto.*;
import functionality.*;
import ui.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UserDTO newUser = new UserDTO();	//Missing interface

		IUserDAO iDAO = new UserDAO(); //Missing IDAO implementation
		ILogic L = new Logic_1(iDAO);
		IUI ui = new TUI_1(L);
	}

}
