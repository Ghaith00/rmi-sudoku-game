import java.rmi.*;
import java.rmi.server.*;
import java.lang.Thread.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * @author Ghaith Tabib <br />
 *         Bilel Aouadhi
 */

public class GridImpl extends UnicastRemoteObject implements Grid {
    private Game game ; 
	private static int  clientsCounter = 0;
	private int clientId;
    /*
     *  Construct Grid object
     */
	public GridImpl() throws RemoteException {
	    super();
        clientsCounter++;
		clientId = clientsCounter;
		try{
            System.out.println("[+] Client "+clientId+" is connected ("+getClientHost()+")");
            createGame();
		}
        catch(Exception e){
            System.out.println("[!] Error Connection : " + e.toString());
        }			
    }
    /*
     * Create Grid game
     */
	public void createGame()  throws RemoteException {
        this.game = new Game(); 
         System.out.println( "[.] Client "+clientId+" sets a new game");
	}	 
	
    /*
     * Set client input
     * @ params x , y   cordinations of input in grid (generated by interface)
     * @ params input   user input
     */
	public String setNumber(int x, int y, String input) throws RemoteException {
        try {
            Integer  val = Integer.valueOf(input);
            if (1 > val || 9 < val) {
                throw new Exception("Number must be in range 1..9");
            }
            if (!this.game.isSelectedNumberCandidate(x,y,val)){
                throw new Exception("Number must be valid");    
            }
            this.game.setNumber(x,y,val);
            System.out.println( "[.] Client "+clientId+" Changed(" +x+","+y+") "+val);
            return "OK" ;    
        } catch (Exception e){
            System.out.println("[!] "+e.toString());
            return e.toString();
        }
	}

    /**
     * Checks whether the game is valid.
     *
     * @param game      Game to check.
     * @return          True if game is valid, false otherwise.
     */
    public boolean isValid() throws RemoteException {
        return this.game.checkGame();
    }	
	

	/**
     * Delete selected position
     *
     * @param x , y     position to delete
     */
    public void deleteNumber(int x , int y) throws RemoteException {
        if (!this.game.blockedPos(x,y)){
            this.game.deleteNumber(x,y) ;
            System.out.println("[-] Deleted at " +x +" , " +y);
        } else {
            System.out.println("[!] Can't delete at " +x +" , " +y);
        }
    }

    /**
     * Get current game.
     * @return          The game matrice
     */
	public int[][] getGame() throws RemoteException { 
        return this.game.getGame();
    }
}
