package data;


/**
 * 
 * @author Grp22
 * Holds information about the desired server, etc.
 */
public abstract class Constant
{
	public static final String
		server					= "localhost",  // database-serveren
		database				=  "cdio_2Semester",//"localDBGrp22",  //"jdbcdatabase", // navnet paa din database = dit studienummer
		username				= "root", // dit brugernavn = dit studienummer 
		password				= ""; // dit password som du har valgt til din database
	
	public static final int
		port					= 3306;
	//jdbc:mysql://localhost:3306/cdio_2Semester
}
