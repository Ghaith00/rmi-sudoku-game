import java.rmi.*;
import java.rmi.registry.*;
/**
 * Sudoku class, used to solve a sudoku.
 * 
 * @author Ghaith Tabib <br />
 *         Bilel Aouadhi
 */
public class Sudoku{
	private int[][] gridTab;
	private boolean[][] blockedTab ;
	private Grid grid ;
	public int x ;
	public int y ;
	public String val ;
	/**
	 * Creates a new instance of the Sudoku class.
	 */
	public Sudoku(Grid grid) throws Exception{
		this.grid = grid ;		
		blockedTab = new boolean[9][9];
		gridTab = (int[][])this.grid.getGame();

		for (int i = 0 ; i < 9 ; i++ ){
			for (int j = 0 ; j < 9 ; j++ ){
				blockedTab[j][i] = (gridTab[i][j] == 0) ? false : true ;
			}
		}
		
	}
	/**
	 * Ask the server to generate a grid
	 */
	public void createGame() throws RemoteException {
		grid.createGame() ;
		gridTab = (int[][])this.grid.getGame();
		for (int i = 0 ; i < 9 ; i++ ){
			for (int j = 0 ; j < 9 ; j++ ){
				blockedTab[j][i] = (gridTab[i][j] == 0) ? false : true ;
			}
		}
	}
	/*
     * Prints given game to console. Used for debug.
     *
     */
    public void print() {
        System.out.println();
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++)
                System.out.print(" " + gridTab[y][x]);
            System.out.println();
        }
    }
    /**
     * Set selected number
     *
     */
    public void selectNumber(int x , int y){
    	this.x = x ;
    	this.y = y ;
    }

	/**
	 * Retrieve the Grid in the form of a multidimensional array.
	 * 
	 * @return The solution
	 */
	public int[][] getGrid() {
		return gridTab;
	}

	/**
	 * Sets the value of the specified square.
	 * 
	 * @param r
	 *            The row of the square
	 * @param c
	 *            The column of the square
	 * @param value
	 *            The value to be set
	 */
	public String setNumber(int r, int c, String value) throws RemoteException {
		String msg = grid.setNumber(r,c,value) ;
		System.out.println(msg );
		if (msg.equals("OK")){
			gridTab[r][c] = Integer.valueOf(value);
			return msg;
		} else {
			return msg ;
		}
	}
	/**
	 * Sets the value of the specified square.
	 * 
	 * @param r
	 *            The row of the square
	 * @param c
	 *            The column of the square
	 * @param value
	 *            The value to be set
	 */
	public String setNumber() throws RemoteException {
		String msg = grid.setNumber(x,y,val) ;
		if (msg == "OK"){
			gridTab[x][y] = Integer.valueOf(val);
		} 
		return msg ;
	}
	/**
	 * Checks if the grid is valid. 
	 * 
	 */
	public boolean isValid() throws RemoteException {
		return grid.isValid() ;
	}

	/**
	 * Gets the value from the specified square.
	 * 
	 * @param r
	 *            The row of the square
	 * @param c
	 *            The column of the square
	 * @return The value in the square
	 */
	public int getSquare(int r, int c) {
		return gridTab[r][c];
	}

	/**
	 * Delete number
	 *  @param x ,y position in the grid
	 */
	public void deleteNumber(int x, int y) throws RemoteException {
		grid.deleteNumber(x,y);
	}
	/**
	 * Delete number
	 */
	public void deleteNumber() throws RemoteException {
		grid.deleteNumber(x,y);
	}
	/**
	 * Clears the sudoku grid.
	 */
	public void clear() {
		gridTab = new int[9][9];
	}
	/**
     * Returns number of given position.
     *
     * @param x     X position in game.
     * @param y     Y position in game.
     * @return      Number of given position.
     */
    public int getNumber(int x, int y) {
        return gridTab[y][x];
    }
    /**
     * Check if the fiel is blocked
     * 
     */
    public boolean isBlocked(int x,int y){
    	return blockedTab[x][y];
    }
}