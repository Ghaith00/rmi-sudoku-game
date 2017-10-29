import javax.swing.*;
import java.awt.*;
import java.util.Scanner;
import java.lang.Thread.*;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.UIManager;
import java.rmi.* ;
import java.rmi.server.* ;
import java.rmi.registry.* ;
import java.rmi.server.RMIClassLoader;
/**
 * The graphical interface of the sudoku RMI application.
 * 
 * @author Ghaith Tabib <br />
 *         Bilel Aouadhi
 * 
 */
public class SudokuClient {

	public SudokuClient (String args[]){
		try {
			if (System.getSecurityManager() == null )
				System.setSecurityManager( new SecurityManager());
			Registry registry = LocateRegistry.getRegistry("localhost",1099);
			GridFactory gridFact = (GridFactory) registry.lookup("Fabrique");
			Grid grid = (Grid) gridFact.newGrid() ;
			
			/**
			 * GUI params
			 */
			JFrame frame;
			SudokuPanel sPanel;
			ButtonPanel bPanel;
			/**
			 * GUI controllers
			 */
			SudokuController sController ;
			ButtonController bController ;
			
			frame = new JFrame("Sudoku RMI");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        	frame.setLayout(new BorderLayout());

			Sudoku sudoku = new Sudoku(grid);
			sPanel = new SudokuPanel(sudoku);
			bPanel = new ButtonPanel();
			sController = new SudokuController(sPanel,sudoku,frame);
			sPanel.setFocusable(true);
			sPanel.addKeyListener(sController);
			sPanel.setController(sController);
			bController = new ButtonController(sudoku,sPanel,frame);
			bPanel.setController(bController);

			
			frame.add(sPanel, BorderLayout.NORTH);
			frame.add(bPanel, BorderLayout.SOUTH);

			frame.setResizable(false);
			frame.setVisible(true);
			frame.pack();
			sudoku.print();

		}catch (Exception e) {
			System.out.println ("Erreur d'acces a l'objet distant.");
			System.out.println (e.toString());
		}
	}
	
}
