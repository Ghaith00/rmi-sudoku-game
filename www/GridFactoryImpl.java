import java.rmi.*;
import java.rmi.server.*;
/**
 * @author Ghaith Tabib <br />
 *         Bilel Aouadhi 
 */

public class GridFactoryImpl extends UnicastRemoteObject implements GridFactory {
 	
 		public GridFactoryImpl() throws RemoteException {
				super();
		}	 
		public Grid newGrid() throws RemoteException {
				return new GridImpl();
		}
 }
