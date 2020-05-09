
/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
* @author Nicolás Penagos Montoya
* nicolas.penagosm98@gmail.com
**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*/

package myExceptions;

public class DisabledThreadException extends Exception{
	
	//-------------------------------------
	// Constructor
	//-------------------------------------
	public DisabledThreadException(int i, int j) {
		super(report(i,j));
	}
	
	//-------------------------------------
	// Methods
	//-------------------------------------
	public static String report(int i, int j) {
		
		String report =  "*************************************\n";
		       report += "       DisabledThreadException       \n";
		       report += "-------------------------------------\n";
		       report += "    The square thread in position    \n";
		       report += "    ["+i+"]["+j+"] has been          \n";
		       report += "    diabled.                         \n";
		       report += "*************************************\n";
		       
		return report;
	}

}
