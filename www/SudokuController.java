import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.EventQueue;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
/**
 * This class controls all user actions from SudokuPanel.
 * @author Ghaith Tabib <br />
 *         Bilel Aouadhi
 */
public class SudokuController implements MouseListener,KeyListener{
    private SudokuPanel sudokuPanel;    // Panel to control.
    private Sudoku sudoku;                  // Current Sudoku game.
    private JFrame frame ; 

    /**
     * Constructor, sets game.
     *
     * @param game  Game to be set.
     */
    public SudokuController(SudokuPanel sudokuPanel, Sudoku sudoku, JFrame frame) {
        this.sudokuPanel = sudokuPanel;
        this.sudoku = sudoku;
        this.frame = frame ;
    }

    /**
     * Recovers if user clicked field in game. If so it sets the selected number
     * at clicked position in game and updates clicked field. If user clicked a
     * field and used left mouse button, number at clicked position will be
     * cleared in game and clicked field will be updated.
     *
     * @param e MouseEvent.
     */
    public void mousePressed(MouseEvent e) {
        JPanel panel = (JPanel)e.getSource();
        Component component = panel.getComponentAt(e.getPoint());
        if (component instanceof Field) {
            Field field = (Field)component;
            int x = field.getFieldX();
            int y = field.getFieldY();
            sudokuPanel.selectField(x,y);
        }
    }



    public void mouseClicked(MouseEvent e) { }
    public void mouseEntered(MouseEvent e) { }
    public void mouseExited(MouseEvent e) { }
    public void mouseReleased(MouseEvent e) { }
    
    public void keyTyped(KeyEvent e) {}
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            if (sudoku.x != 0)sudokuPanel.selectField(sudoku.x-1,sudoku.y);
            return ;
        }

        if (key == KeyEvent.VK_RIGHT) {
            if (sudoku.x != 8)sudokuPanel.selectField(sudoku.x+1,sudoku.y);
            return ;
        }

        if (key == KeyEvent.VK_UP) {
            if (sudoku.y != 0)sudokuPanel.selectField(sudoku.x,sudoku.y-1);
            return ;
        }

        if (key == KeyEvent.VK_DOWN) {
            if (sudoku.y != 8)sudokuPanel.selectField(sudoku.x,sudoku.y+1);
            return ;
        }
       
        if (key ==  KeyEvent.VK_DELETE) {
            try {
                sudokuPanel.deleteNumber(sudoku.x,sudoku.y);
                return ;
            } catch (Exception error ){}
        }
        try {
            String msg = sudokuPanel.setNumber(String.valueOf(e.getKeyChar())) ;
            System.out.println(msg);
            if ( msg.equals("OK")){
            } else {
                JOptionPane.showMessageDialog(frame,
                    msg,
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception error){} 
    }
    public void keyReleased(KeyEvent e) {}
 
        
       

}