
import java.util.Timer;
import java.util.TimerTask;
 
import javax.swing.JDialog;
import javax.swing.JOptionPane;
 
/**
 * The first time you do a GUI project, there is a function to check the computer information. If an exception message appears, a window will pop up automatically, and a warning will be displayed. The user can actively close it. If the user does not actively close the window, the window will automatically close after 30 seconds.
   * Because it is a simple prompt, corresponding to JDialog, JOptionPane can save a lot of code, and JOptionPane, did not find how to automatically close.
   * After reading the API, you can use JOptionPane to create a JDialog. This saves a lot of things.
 * @author 
 * @since 
 */
public class test {
 
    /**
           * Test dialog automatically closes
     * 
     * @param args
     */
    public static void main(String[] args) {
 
                 JOptionPane op = new JOptionPane("This dialog will close after 30 seconds", JOptionPane.INFORMATION_MESSAGE);
                 final JDialog dialog = op.createDialog("server self-test exception");
        
                 // Create a new timer
        Timer timer = new Timer();
 
                 // Perform this task after 30 seconds
        timer.schedule(new TimerTask() {
            public void run() {
                dialog.setVisible(false);
                dialog.dispose();
            }
        }, 3000);
 
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setAlwaysOnTop(true);
        dialog.setModal(false);
        dialog.setVisible(true);
    }
}