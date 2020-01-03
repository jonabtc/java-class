/*Jonathan Ocles 00112226
Archivo GuessNumberGame: implementa en interface grafica un juego de adivinar un numero que se genera aleatoriamente, con el 
uso de la libreria Math, y da pistas al usuario de cuan cerca esta del objetivo (cambia el color de fondo
anunciando con rojo la proximidad y con azul la lejania tomando en cuenta un rango de mas menos 20 para dichos valores)
*/

import java.awt.FlowLayout;
import java.awt.event.*;
import java.awt.Color;
import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.lang.*;

public class GuessNumberGame extends JFrame implements ActionListener {

      JLabel label1, label2, label3, label4;
      JTextField userEntry;
      JButton reset;
      int numero;
      Container contentpane;      
      
      GuessNumberGame(){
      
         super("Guess Number Game");
         setSize(400, 200); 
         setVisible(true);
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         contentpane = getContentPane();
         contentpane.setLayout(new FlowLayout());
          
         numero = (int)Math.floor(Math.random()*(1-1000)+1000);
         label1 = new JLabel("I have a number between 1 and 1000. Can you guess my number?");
         label2 = new JLabel("Please enter your first guess");
         label3 = new JLabel(" ");
                               
         userEntry = new JTextField(3);
         userEntry.addActionListener(this);
         reset = new JButton("Reset");
         reset.addActionListener(this);
         reset.setEnabled(false);
         contentpane.add(label1);
         contentpane.add(label2);
         contentpane.add(userEntry);
         contentpane.add(reset);
         contentpane.add(label3);

         paintComponents(getGraphics());
      }
            
   public void actionPerformed (ActionEvent e){
   
      if (e.getSource()==userEntry){
      int aux = Integer.parseInt(userEntry.getText());
      
         if(aux>numero) label3.setText("Too High");
         else  label3.setText("Too Low");
         if(aux<numero+20&&aux>numero-20){
            contentpane.setBackground(Color.RED);
         }
         else{
            contentpane.setBackground(Color.BLUE);            
         }
         
         if(aux==numero){
            label3.setText("WINNER");
            contentpane.setBackground(Color.GREEN);
           reset.setEnabled(true);
           userEntry.setEnabled(false);
         }
      }
      
      if (e.getSource()==reset){
         numero = (int)Math.floor(Math.random()*(1-1000)+1000);
         contentpane.setBackground(Color.WHITE);
         userEntry.setEnabled(true);
        reset.setEnabled(false);
      }
   }
   
   public static void main (String args[]){
      GuessNumberGame juego= new GuessNumberGame();
   }
}