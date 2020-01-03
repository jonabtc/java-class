/*Jonathan Ocles 00112226
Archivo TestCraps: realiza la instanciacion de Craps
*/
import javax.swing.JFrame;


public class TestCraps{ 
   public static void main (String [] args){

      Craps gameCraps = new Craps(); 
      gameCraps.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      gameCraps.setSize(700, 100); 
      gameCraps.setVisible(true); 
   } 
}
