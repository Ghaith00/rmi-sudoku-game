import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;
/**
 * Server class
 * @author Ghaith Tabib <br />
 *         Bilel Aouadhi
 */
public class SudokuServer {
	public static void main (String args[])	
		{	
			try {
				Registry reg = LocateRegistry.createRegistry(1099);
				System.setProperty( "java.rmi.server.hostname","localhost" ) ;
				GridFactoryImpl gridFactoryImpl = new  GridFactoryImpl();
				Naming.rebind("GridFactory",gridFactoryImpl);
				System.out.println("Serveur pret.");

			} catch (Exception e) {
				System.out.println(e.toString());
	       } 
		}
}
