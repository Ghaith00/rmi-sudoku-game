import java.awt.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;
import java.rmi.*;
import java.rmi.registry.*;

/**
 * The sudoku panel containing the grid of text fields representing the squares.
 * 
 * @author Ghaith Tabib <br />
 *         Bilel Aouadhi
 * 
 */
public class SudokuPanel extends JPanel {
	// Color constant for candidates.
    private static final Color COLOR_CANDIDATE = new Color(80, 153, 255);

    private Field[][] fields;       // Array of fields.
    private JPanel[][] panels;      // Panels holding the fields.
    public int Sx ;                 // Selected field position x
    public int Sy ;   
	boolean[][] blocked ; // Bloacked fields
	Sudoku sudoku;

	/**
	 * Creates a new sudoku panel.
	 */
	public SudokuPanel(Sudoku sudoku) {
		super(new GridLayout(3, 3));
		this.sudoku = sudoku ;

        panels = new JPanel[3][3];
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                panels[y][x] = new JPanel(new GridLayout(3, 3));
                panels[y][x].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
                add(panels[y][x]);
            }
        }

        fields = new Field[9][9];
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                fields[y][x] = new Field(x, y);
                panels[y / 3][x / 3].add(fields[y][x]);
            }
        }
        Sx = -1 ;
        Sy = -1 ;
        setGame();
	}
	/**
     * Sets the fields corresponding to given game.
     *
     * @param game  Game to be set.
     */
    public void setGame() {
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                fields[y][x].setBackground(sudoku.isBlocked(x,y) ? new Color(200,200,200) : Color.WHITE);
                fields[y][x].setNumber(sudoku.getNumber(x, y), false);
            }
        }
    }
    /**
     * Sets the new game 
     *
     */
    public void newGame(){
        try {
            sudoku.createGame();
            setGame();
        } catch (RemoteException e){
            System.out.println(e);
        }
    }
    /**
     * Checks if the grid is valid. 
     * 
     */
    public void done() {
        try {
            if (!sudoku.isValid()){
                System.out.println("not Done");
                JOptionPane.showMessageDialog(this,
                    "Not valid , Try again !",
                    "Game not valid",
                    JOptionPane.ERROR_MESSAGE);
            } else {
                int n = JOptionPane.showConfirmDialog(this,
                        "You finished the game ! would you like to replay ?",
                        "Congras !",
                        JOptionPane.YES_NO_OPTION);
                if (n == 0){
                    newGame();
                } else {
                    System.exit(0);
                }
                                }
        } catch (RemoteException e){

        }
        
    }

     /**
     * Select field 
     * @param x 
     * @param y
     */
    public void selectField(int x , int y){
	    sudoku.x = x ;
	    sudoku.y = y ;
	    if (Sx != -1 || Sy != -1){
	        fields[Sy][Sx].setBorder(BorderFactory.createLineBorder(Color.GRAY));
	        fields[y][x].setBorder(BorderFactory.createLineBorder(Color.RED));
	        Sx = x ; Sy = y ;
	    } else {
	        fields[y][x].setBorder(BorderFactory.createLineBorder(Color.RED));
	        Sx = x ; Sy = y ;
        }
    }
    /**
     * Set a number or caracter
     *
     */
    public String setNumber(String input) throws RemoteException {
		String msg = sudoku.setNumber(Sx,Sy,input) ;
		if(msg.equals("OK")){
			fields[Sy][Sx].setNumber(Integer.valueOf(input) , false);
		} 
		return msg ;
	}
	/**
	 * Delete a number 
	 *
	 */
	public void deleteNumber(int x ,int y)throws RemoteException{
        if (!sudoku.isBlocked(x,y))fields[y][x].setNumber(0 , false);
		sudoku.deleteNumber(x ,y);
	}
    /**
     * Adds controller to all sub panels.
     *
     * @param sudokuController  Controller which controls all user actions.
     */
    public void setController(SudokuController sudokuController) {
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++){
                panels[y][x].addMouseListener(sudokuController);
            }
        }
    }
	
	


}