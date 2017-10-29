import java.rmi.server.RMIClassLoader;

import java.util.*;
import java.lang.reflect.Constructor ;


/**
 * @author Ghaith Tabib <br />
 *         Bilel Aouadhi
 */

public class DynamicClient {

  public DynamicClient (String[] args) throws Exception {

    Properties p = System.getProperties();
    String url = p.getProperty("java.rmi.server.codebase");

    Class ClasseClient = RMIClassLoader.loadClass( url , "SudokuClient" ) ;

    Constructor [] C = ClasseClient.getConstructors() ;
    C[0].newInstance ( new Object[] {args} ) ;
  }


  public static void main(String[] args) {

    System.setSecurityManager(new SecurityManager() );
    
    try {
      DynamicClient cli = new DynamicClient(args) ;
      System.out.println(cli);
        }
    catch (Exception e) { System.out.println(e.toString() ) ; }
    } 
}
