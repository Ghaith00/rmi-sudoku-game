import java.rmi.*;
/**
 * @author Ghaith Tabib <br />
 *         Bilel Aouadhi
 */
public interface Grid extends Remote {
		public void createGame() throws RemoteException ;
		public String setNumber(int x, int y, String input)  throws RemoteException ;	
		public boolean isValid() throws RemoteException;		
		public void deleteNumber(int x, int y) throws RemoteException;	
		public int[][] getGame() throws RemoteException;		

}