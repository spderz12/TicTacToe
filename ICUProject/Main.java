
/**
 * Write a description of class Main here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import javax.swing.JFrame;


public class Main
{
    
    public static void main (String[] args){
        JFrame frame = new JFrame();
        //Parameters for game space
        frame.setBounds(0,0, 600, 600 );
        frame.setTitle("Jack's Tic Tac Toe");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
        TTTGame game = new TTTGame();
        frame.add(game);
        frame.setVisible(true);
        
    }
}

