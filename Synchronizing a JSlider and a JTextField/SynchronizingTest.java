/*
   Jonathan Ocles 00112226
   SynchrinizingTest: implementa el método principal en el cual instancia SliderFrame
*/
import javax.swing.JFrame;

public class SynchronizingTest{
   public static void main(String []args){
     
      SliderFrame slider = new SliderFrame();
      slider.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      slider.setSize(240, 100);
      slider.setVisible(true);
   }
}