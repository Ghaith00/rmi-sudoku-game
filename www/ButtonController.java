import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * This class controls all user actions from ButtonPanel.
 * @author Ghaith Tabib <br />
 *         Bilel Aouadhi
 */
public class ButtonController implements ActionListener {
    private SudokuPanel sudokuPanel;    // Panel to control.
    private Sudoku sudoku;              // Current Sudoku game.
    private JFrame frame ;              // The main frame to ouput errors

    /**
     * Constructor, sets game.
     *
     * @param game  Game to be set.
     */
    public ButtonController(Sudoku sudoku,SudokuPanel sudokuPanel, JFrame frame) {
        this.sudokuPanel = sudokuPanel;
        this.sudoku = sudoku;
        this.frame = frame ;
    }

    /**
     * Performs action after user pressed button.
     *
     * @param e ActionEvent.
     */
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getActionCommand().equals("New"))
                sudokuPanel.newGame();
            else if (e.getActionCommand().equals("Check"))
                sudokuPanel.done();
            else if (e.getActionCommand().equals("Exit"))
                System.exit(0);
        } catch (Exception error){
               System.out.println(error.toString());
        }
    }
}