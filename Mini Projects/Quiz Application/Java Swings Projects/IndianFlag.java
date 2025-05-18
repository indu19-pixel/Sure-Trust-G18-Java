
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

public class IndianFlag extends JPanel {
    private static final Color SAFFRON = new Color(255, 153, 51);
    private static final Color WHITE = Color.WHITE;
    private static final Color GREEN = new Color(19, 136, 8);
    private static final Color CHAKRA_BLUE = new Color(0, 56, 147);

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        // Enable anti-aliasing for smoother graphics
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        
        drawFlag(g2d);
    }

    private void drawFlag(Graphics2D g) {
        int flagWidth = 450;  // 3:2 ratio
        int flagHeight = 300;
        
        // Center the flag
        int x = (getWidth() - flagWidth) / 2;
        int y = (getHeight() - flagHeight) / 2;
        
        // Draw the three stripes
        int stripeHeight = flagHeight / 3;
        
        // Saffron stripe
        g.setColor(SAFFRON);
        g.fillRect(x, y, flagWidth, stripeHeight);
        
        // White stripe with chakra
        g.setColor(WHITE);
        g.fillRect(x, y + stripeHeight, flagWidth, stripeHeight);
        
        // Green stripe
        g.setColor(GREEN);
        g.fillRect(x, y + 2 * stripeHeight, flagWidth, stripeHeight);
        
        // Draw the Ashoka Chakra
        int chakraDiameter = (int)(stripeHeight * 0.8);  // 80% of stripe height
        drawAshokaChakra(g, x + flagWidth/2, y + stripeHeight + stripeHeight/2, chakraDiameter);
        
        // Draw flag pole
        g.setColor(new Color(139, 69, 19)); // Brown wooden pole
        g.fillRect(x - 30, y - 50, 20, flagHeight + 100);
        
        // Draw pole top
        g.setColor(new Color(184, 134, 11)); // Gold color
        g.fillRect(x - 35, y - 50, 30, 15);
    }

    private void drawAshokaChakra(Graphics2D g, int centerX, int centerY, int diameter) {
        int radius = diameter / 2;
        
        // Draw outer blue circle
        g.setColor(CHAKRA_BLUE);
        g.fillOval(centerX - radius, centerY - radius, diameter, diameter);
        
        // Draw inner white circle (makes the spokes look better)
        int innerWhiteDiameter = (int)(diameter * 0.9);
        int innerWhiteRadius = innerWhiteDiameter / 2;
        g.setColor(WHITE);
        g.fillOval(centerX - innerWhiteRadius, centerY - innerWhiteRadius, 
                  innerWhiteDiameter, innerWhiteDiameter);
        
        // Draw the 24 spokes with precise geometry
        g.setColor(CHAKRA_BLUE);
        Stroke originalStroke = g.getStroke();
        g.setStroke(new BasicStroke(1.5f));
        
        // Create a circular clip to ensure spokes don't extend beyond chakra
        Area clipArea = new Area(new Ellipse2D.Double(
            centerX - radius, centerY - radius, diameter, diameter));
        
        // Save original clip
        Shape originalClip = g.getClip();
        g.setClip(clipArea);
        
        for (int i = 0; i < 24; i++) {
            double angle = Math.toRadians(i * 15); // 24 spokes at 15° intervals
            
            // Calculate points for each spoke
            double outerRadius = radius * 0.95;
            double innerRadius = radius * 0.35;
            
            int x1 = (int)(centerX + outerRadius * Math.cos(angle));
            int y1 = (int)(centerY + outerRadius * Math.sin(angle));
            int x2 = (int)(centerX + innerRadius * Math.cos(angle));
            int y2 = (int)(centerY + innerRadius * Math.sin(angle));
            
            // Draw the spoke
            g.drawLine(x1, y1, x2, y2);
            
            // Draw small ticks at the end of each spoke
            double tickAngle1 = angle + Math.toRadians(90);
            double tickAngle2 = angle - Math.toRadians(90);
            int tickLength = 3;
            
            int tx1 = (int)(x1 + tickLength * Math.cos(tickAngle1));
            int ty1 = (int)(y1 + tickLength * Math.sin(tickAngle1));
            int tx2 = (int)(x1 + tickLength * Math.cos(tickAngle2));
            int ty2 = (int)(y1 + tickLength * Math.sin(tickAngle2));
            
            g.drawLine(tx1, ty1, tx2, ty2);
        }
        
        // Restore original clip
        g.setClip(originalClip);
        g.setStroke(originalStroke);
        
        // Draw inner blue circle
        int innerBlueDiameter = (int)(diameter * 0.2);
        g.setColor(CHAKRA_BLUE);
        g.fillOval(centerX - innerBlueDiameter/2, centerY - innerBlueDiameter/2, 
                  innerBlueDiameter, innerBlueDiameter);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Indian National Flag");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            IndianFlag flagPanel = new IndianFlag();
            flagPanel.setBackground(new Color(240, 240, 240)); // Light gray background
            
            frame.add(flagPanel);
            frame.setSize(600, 500);
            frame.setMinimumSize(new Dimension(400, 300));
            frame.setLocationRelativeTo(null); // Center on screen
            frame.setVisible(true);
        });
    }
}