/*
   Jonathan Ocles 0011226
   17/04/2016
   DoRectangle: Archivo sublase de JPanel, dibuja un rectangulo en el panel y cambia el color 
*/
import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.Color;

public class DoRectangle extends JPanel{
    private Color color;
    DoRectangle(){
       repaint();
    }
    public void putColor(int r, int b, int g){
      color = new Color(r, b, g);
      repaint();
    }
    public void paintComponent(Graphics g){
      super.paintComponent(g);
      g.setColor(color);
      g.fillRect(20, 20, 300, 80); 
    }
            
}