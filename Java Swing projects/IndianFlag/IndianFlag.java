

import java.awt.Graphics;
import javax.swing.*;
import java.awt.*;

public class IndianFlag extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawFlag(g);
    }

    private void drawFlag(Graphics g) {
       
        g.setColor(new Color(255, 153, 51)); 
        g.fillRect(50, 20, 300, 100);

        g.setColor(Color.WHITE);
        g.fillRect(50, 120, 300, 100);

        g.setColor(new Color(19, 136, 8)); 
        g.fillRect(50, 220, 300, 100);

        
        drawChakra(g, 200, 170, 30);
    }

    private void drawChakra(Graphics g, int x, int y, int radius) {
        
        g.setColor(Color.BLUE);
        g.drawOval(x - radius, y - radius, radius * 2, radius * 2);

        for (int i = 0; i < 24; i++) {
            double angle = Math.toRadians(i * 15);
            int x1 = (int) (x + radius * Math.cos(angle));
            int y1 = (int) (y + radius * Math.sin(angle));
            g.drawLine(x, y, x1, y1);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Indian Flag");
        IndianFlag flag = new IndianFlag();
        frame.add(flag);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}