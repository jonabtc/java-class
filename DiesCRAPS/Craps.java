/*Jonathan Ocles 00112226
Archivo Craps: reutiliza el codigo del juego de dado de la saccion 6.10 y genera una interface grafica del mismo
*/

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.security.SecureRandom;


public class Craps extends JFrame{

   private enum Status {CONTINUE, WON, LOST};
   private static final SecureRandom randomNumbers = new SecureRandom();
   
   private static final int SNAKE_EYES = 2;
   private static final int TREY = 3;
   private static final int SEVEN = 7;
   private static final int YO_LEVEN = 11;
   private static final int BOX_CARS = 12;

   private JButton roll;
   private JLabel label1, label2,label3, label4;
   private JTextField die1, die2, sum, points;
      
   public Craps(){
   
      super("Craps Game");
      setLayout(new FlowLayout());
      
      roll = new JButton("ROLL THE DICE");
      label1 = new JLabel("DIE ONE");
      label2 = new JLabel("DIE TWO");
      label3 = new JLabel("SUM");
      label4 = new JLabel("ESTATUS/POINTS");
      die1 = new JTextField(5);
      die2 = new JTextField(5);
      sum = new JTextField(5);
      points = new JTextField(5);
      
      ButtonHandler handler = new ButtonHandler();
      
      add(label1);
      add(die1);
      die1.setEditable(false);
     
      add(label2);
      add(die2); 
      die2.setEditable(false);
          
      add(label3);
      add(sum);
      sum.setEditable(false);
                
      add(label4);
      add(points);
      points.setEditable(false);
                
      add(roll);
      roll.addActionListener(handler);
   }
   public int rollDice()
   {
      // pick random die values
      die1.setText(Integer.toString(1 + randomNumbers.nextInt(6))); // first die roll
      die2.setText(Integer.toString(1 + randomNumbers.nextInt(6))); // second die roll

      int suma = Integer.parseInt(die1.getText()) + Integer.parseInt(die2.getText()); // sum of die values
      sum.setText(Integer.toString(suma));

      // display results of this roll
      System.out.printf("Player rolled %s + %s = %d%n", 
         die1.getText(), die2.getText(), suma);

      return suma; 
   }

    private class ButtonHandler implements ActionListener{
      // handle button event
      @Override
      public void actionPerformed(ActionEvent event){
         int myPoint = 0; // point if no win or loss on first roll
         Status gameStatus; // can contain CONTINUE, WON or LOST
         gameStatus=Status.CONTINUE;
         int aux = 0;
   
         int sumOfDice = rollDice(); // first roll of the dice
         
         if(aux==0){
         // determine game status and point based on first roll 
            switch (sumOfDice){
               case SEVEN: // win with 7 on first roll
               case YO_LEVEN: // win with 11 on first roll           
                  gameStatus = Status.WON;
                  points.setText("WIN");
                  break;
               case SNAKE_EYES: // lose with 2 on first roll
               case TREY: // lose with 3 on first roll
               case BOX_CARS: // lose with 12 on first roll
                  gameStatus = Status.LOST;
                  points.setText("LOSE");
                  break;
               default: // did not win or lose, so remember point         
                  gameStatus = Status.CONTINUE; // game is not over
                  myPoint = sumOfDice; // remember the point
                  points.setText(Integer.toString(myPoint));
                  System.out.printf("Point is %d%n", myPoint);
                  break;
            } aux++;
         }

      // while game is not complete
      if (gameStatus == Status.CONTINUE) // not WON or LOST
      { 
         sumOfDice = rollDice(); // roll dice again

         // determine game status
         if (sumOfDice == myPoint){ // win by making point
            gameStatus = Status.WON;
            points.setText("WIN");
         }
         else if (sumOfDice == SEVEN){ // lose by rolling 7 before point
               gameStatus = Status.LOST;
               points.setText("LOSE");
         }
      } 

      // display won or lost message
      if (gameStatus == Status.CONTINUE)
         System.out.println("Player continue in the game");
      else if (gameStatus == Status.WON)
            System.out.println("Player wins");
           else
            System.out.println("Player loses");
      }
   }
} 

