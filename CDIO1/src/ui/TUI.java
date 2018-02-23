package ui;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import data.IUserDAO.DALException;
import func.IFuncImpl;

public class TUI implements IUI {

	IFuncImpl f;

	public TUI(IFuncImpl f)
	{
		this.f = f;
	}

	@Override
	public void run() throws DALException { //Displays welcome screen 
		System.out.println("Velkommen! Tryk på det tal, der svarer til det menupunkt du gerne vil tilgå");
		System.out.println("1: Find bruger"); 			//Get User
		System.out.println("2: Udskriv alle brugere");	//Get UserList
		System.out.println("3: Opret bruger");			//Create user
		System.out.println("4: Rediger bruger");			//Update user
		System.out.println("5: Slet bruger");			//Delete user
		System.out.println("6: Afslut program");			//Exit program
		Scanner sc = new Scanner(System.in);

		boolean hasEnded = false;
		while (!hasEnded) {
			int selection = Integer.parseInt(sc.nextLine());
			switch (selection) {
			case 1: //getUser
				System.out.println("Indtast bruger ID'et på den bruger du gerne vil finde: ");
				int userId = Integer.parseInt(sc.nextLine());
				System.out.println(f.getUser(userId));
//				System.out.println("1"); //Only for test purposes
				menuOptions();
				break;
			case 2: //GetUserList
				System.out.println("Printer liste...");
				System.out.println(f.getUserList());
//				System.out.println("2");	//Only for test purposes
				menuOptions();
				break;
			case 3: //Create user
				System.out.println("Bruger oprettes...");
				System.out.println("Indtast dit burgernavn: ");
				String userName3 = sc.nextLine();
				System.out.println("Indtast dine initialer: ");
				String ini3 = sc.nextLine();
				System.out.println("Indtast din rolle(r), hvis du har flere så seperer med komma og mellemrum: ");
				String roles3 = sc.nextLine();
				List<String> roles = Arrays.asList(roles3.split(", "));
				System.out.println("Indtast dit CPR-nummer: ");
				String cpr3 = sc.nextLine();
				f.createUser(userName3, ini3, roles, cpr3);	//Creates a user with the inputted information
//				f.retrieveUserId();
//				System.out.println("3");	//Only for test purposes
				menuOptions();
				break;
			case 4: //Update user.
				System.out.println("Indtast dit bruger ID: ");
				int userId4 = Integer.parseInt(sc.nextLine());
				f.getUser(userId4);
				
				String userName4 = null;
				String ini4 = null;
				String cpr4 = null;
				List<String> roles1 = null;

				System.out.println("Indtast brugernavn");
				userName4 = sc.nextLine();
				
				System.out.println("Indtast initialer");
				ini4 = sc.nextLine();
				
				System.out.println("Indtast roller");
				String roles4 = sc.nextLine();
				roles1 = Arrays.asList(roles4.split(", "));
				
				System.out.println("Indtast CPR");
				cpr4 = sc.nextLine();
//				
				f.updateUser(userId4, userName4, ini4, roles1, cpr4);

//				System.out.println("4");	//Only for test purposes
				menuOptions();
				break;
			case 5: //Delete user
				System.out.println("Indtast bruger ID'et på den bruger du gerne vil slette: ");
				int userId5 = Integer.parseInt(sc.nextLine());
				f.deleteUser(userId5);
				System.out.println("5");	//Only for test purposes
				menuOptions();
				break;
			case 6: //Exit
				System.out.println("Lukker ned...");
				System.out.println("Farvel");

				f.exit();
				hasEnded = true;
				System.out.println("6");	//Only for test purposes
				break;
			default: 
				System.out.println("Default");	//Only for test purposes
				break;
			}
		}
		sc.close();
	}

	@Override
	public void scenario() {

	}

	@Override
	public void createUser() {

	}

	@Override
	public void updateUser() {

	}

	@Override
	public void getUser() {

	}

	@Override
	public void getUserList() {

	}

	@Override
	public void deleteUser() {

	}
//	public String chooseRoles() {
//		List<String> roller = new ArrayList<String>();
//		boolean run = true;
//		String st;
//		while (run) {
//			Scanner sc = new Scanner(System.in);
//			int input = sc.nextInt();
//			switch(input) {
//			case 1: //Admin
//				st = "Admin";
//				if(roller.contains(st))
//					System.out.println("Allerede tilføjet");
//				else roller.add(st);
//				break;
//			case 2: //Foreman
//				st = "Foreman";
//				if(roller.contains(st))
//					System.out.println("Allerede tilføjet");
//				else roller.add(st);
//				break;
//			case 3: //Pharmacist
//				st = "Pharmacist";
//				if(roller.contains(st))
//					System.out.println("Allerede tilføjet");
//				else roller.add(st);
//				break;
//			case 4: //Operator
//				st = "Operator";
//				if(roller.contains(st))
//					System.out.println("Allerede tilføjet");
//				else roller.add(st);
//				break;
//			case 5: //Exit
//				//TODO exit code.
//				run = false;
//				break;
//			default:
//			}
//			sc.close();
//		}
//		return null;
//	}
	public void menuOptions() {
		System.out.println("---------------------------------"); 			//Get User
		System.out.println("1: Find bruger"); 			//Get User
		System.out.println("2: Udskriv alle brugere");	//Get UserList
		System.out.println("3: Opret bruger");			//Create user
		System.out.println("4: Rediger bruger");			//Update user
		System.out.println("5: Slet bruger");			//Delete user
		System.out.println("6: Afslut program");			//Exit program
	}
}