/*
   Jonathan Ocles 00112226
   17/04/2016
   MyColorChooser: archivo que implementa DoRectangle, mediante TextFields y Sliders repinta el rectangulo dibujado en el GUI
*/
import javax.swing.JTextField;
import javax.swing.JSlider;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;


public class MyColorChooser extends JFrame{

	private JSlider redSlider, blueSlider, greenSlider;
	private JTextField redText, blueText, greenText;
   private JPanel panel;
   private DoRectangle rect;

	MyColorChooser(){
      super("My Color Chooser");
      
      SliderHandler handlerS = new SliderHandler();
      
      //inicializacion de sliders y registro del manejador
		redSlider = new JSlider(SwingConstants.HORIZONTAL, 0, 255, 5);   redSlider.addChangeListener(handlerS);
		blueSlider = new JSlider(SwingConstants.HORIZONTAL, 0, 255, 5);  blueSlider.addChangeListener(handlerS);
		greenSlider = new JSlider(SwingConstants.HORIZONTAL, 0, 255, 5); greenSlider.addChangeListener(handlerS);
      
      //inicializacion de textfields y registro del manejador
      TextFieldHandler handlerT = new TextFieldHandler();
      redText = new JTextField("RED", 10);   redText.addActionListener(handlerT);
      blueText = new JTextField("BLUE", 10);  blueText.addActionListener(handlerT);
      greenText = new JTextField("GREEN", 10); greenText.addActionListener(handlerT);
      
      //inicializacion de paneles auxiliares y agregacion de elementos al panel principal
      panel = new JPanel();
      panel.setLayout(new GridLayout(4,2,2,2));
      
      panel.add(redSlider);    panel.add(redText); 
      panel.add(blueSlider);   panel.add(blueText);
      panel.add(greenSlider);  panel.add(greenText);
      
      rect = new DoRectangle();
      add(rect);
      add(panel, BorderLayout.SOUTH);
      getContentPane().validate();
	}
      //Creacion de manejadores
   private class TextFieldHandler implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e){
      if(e.getSource()==redText)
         redSlider.setValue(Integer.parseInt(redText.getText()));
      if(e.getSource()==blueText)
         blueSlider.setValue(Integer.parseInt(blueText.getText()));
      if(e.getSource()==greenText)
         greenSlider.setValue(Integer.parseInt(greenText.getText()));
         
      rect.putColor(redSlider.getValue(),greenSlider.getValue(),blueSlider.getValue());
    }
   }
   
   private class SliderHandler implements ChangeListener{
    @Override
    public void stateChanged (ChangeEvent e){
      if(e.getSource()==redSlider)
         redText.setText(Integer.toString(redSlider.getValue()));
      if(e.getSource()==blueSlider)
         blueText.setText(Integer.toString(blueSlider.getValue()));
      if(e.getSource()==greenSlider)
         greenText.setText(Integer.toString(greenSlider.getValue()));
         
      rect.putColor(redSlider.getValue(),greenSlider.getValue(), blueSlider.getValue());

    }
   }
}