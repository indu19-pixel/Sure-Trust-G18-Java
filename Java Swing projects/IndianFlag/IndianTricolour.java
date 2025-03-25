import javax.swing.*;
import java.awt.*;


public class IndianTricolour
{
  public static void main(String[] args) 
  {
     new IndianTricolour();
  }

  public IndianTricolour()
  {
    JFrame frame = new JFrame("Indian Tricolor");           
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
    frame.getContentPane().add(new FlagComponent());        
    frame.setSize(900,630);                                 
    frame.setVisible(true);  
  }

  public class FlagComponent extends JComponent
  {
      public void paint(Graphics g)
      {
          int height1 = 1300;                               
          int width1 = 1300;
          g.setColor(Color.black);  
          g.fillRect(0,0,height1,width1);                       
          
          height1 = 900;
          width1 = 200;
          Color saffron = new Color(255, 153, 51);              
          g.setColor(saffron);          
          g.fillRect(0,0,height1,width1);                       
          g.setColor(Color.white);
          g.fillRect(0,200,height1,width1);                   
		  Image image = new ImageIcon("Chakra.jpg").getImage();
		  g.drawImage(image,380,210,180,180,this);   
		       
          Color green = new Color(0,128,0);                     
          g.setColor(green);            
          g.fillRect(0,400,height1,width1);                     
     }
  }
}