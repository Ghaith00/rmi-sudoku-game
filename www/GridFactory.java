import java.rmi.*;
/**
 * @author Ghaith Tabib <br />
 *         Bilel Aouadhi
 */
public interface GridFactory extends Remote {
		public Grid newGrid () throws RemoteException ;
}