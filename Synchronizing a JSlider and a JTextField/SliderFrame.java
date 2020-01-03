/*
   Jonathan Ocles 00112226
   SliderFrame: sincroniza los valores de un Slider y de un TextField
*/
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Container;


public class SliderFrame extends JFrame{
   private final JSlider slider1;
   private final JTextField texto;
   private JLabel label; 
   Container contentpane;
   
   public SliderFrame(){
      super ("Synchoronizing a JSlider and a JTextField");
      label = new JLabel("Valor de slider: ");
      contentpane = getContentPane();
      contentpane.setLayout(new FlowLayout());
      
      slider1 = new JSlider(SwingConstants.HORIZONTAL, 0, 100, 0);
      slider1.setMajorTickSpacing(5); 
      slider1.setPaintTicks(true);
      slider1.addChangeListener(
         new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent e){
               texto.setText(Integer.toString(slider1.getValue()));
            }
         }
      );
      
      texto = new JTextField(5);
      texto.setText(Integer.toString(slider1.getValue()));      
      texto.addActionListener(
         new ActionListener(){
            public void actionPerformed(ActionEvent e){
               slider1.setValue(Integer.parseInt(texto.getText()));
            }
         }
      );
      contentpane.add(label);
      contentpane.add(texto);
      contentpane.add(slider1);
   }
   
}