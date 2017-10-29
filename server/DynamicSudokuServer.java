import java.rmi.* ;
import java.rmi.server.* ;
import java.rmi.registry.* ;
import java.util.Properties ;
/**
 * @author Ghaith Tabib <br />
 *         Bilel Aouadhi
 */

public class DynamicSudokuServer{
	public static void main(String[] args )
	{
	try {
		if (System.getSecurityManager() == null )
			System.setSecurityManager( new SecurityManager());

		Registry registry = LocateRegistry.createRegistry(1099) ;
		System.out.println( "Serveur: Construction de l'implementation") ;
		System.out.println( "Objet Fabrique lie dans le RMIregistry") ;
		System.out.println(registry);
		Properties p = System.getProperties();
		String url = p.getProperty("java.rmi.server.codebase");
		System.out.println(url);
		Class ClasseServeur= RMIClassLoader.loadClass(url, "GridFactoryImpl") ;
		System.out.println(ClasseServeur);
		registry.rebind("Fabrique" , (Remote)ClasseServeur.newInstance());
		System.out.println("after");
		System.out.println("server pret ...") ;
		System.out.println(".....");
	}
	catch (Exception e) {
		System.out.println("erreur de laision ") ;
		System.out.println(e.toString() ) ; }
	} 
}
