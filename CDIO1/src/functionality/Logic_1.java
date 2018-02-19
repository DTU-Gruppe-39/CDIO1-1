package functionality;

import dal.*;

public class Logic_1 implements ILogic {
	private IUserDAO d;
	
	public Logic_1(IUserDAO d) {
		this.d = d;
	}
}
