/*
   Jonathan Ocles 00112226
   17/04/2016
   TestMyColorChooser: archivo que prueba MyColorChooser
   
*/
import javax.swing.JFrame;
public class TestMyColorChooser{
	public  static void main(String args[]){
   
		MyColorChooser color = new MyColorChooser();
		color.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		color.setSize(349, 258);
      color.setResizable(false);
		color.setVisible(true);
      
	}
}