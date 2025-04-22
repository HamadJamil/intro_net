package Components;

import Utilities.GUIConstants;

import javax.swing.JLabel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

public class JButton extends JLabel {
    private Shape shape;
    private int radius;

    public JButton(String text, int radius, int textSize) {
        super(text);
        this.radius = radius;
        setOpaque(false);
        setFont(new Font("Agu Display", Font.BOLD, textSize));
        setForeground(GUIConstants.white);
        setHorizontalAlignment(CENTER);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    // for Rounded corner
    protected void paintComponent(Graphics g) {
        g.setColor(GUIConstants.blue);
        g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
        super.paintComponent(g);
    }

    // for Rounded Border
    protected void paintBorder(Graphics g) {
        g.setColor(GUIConstants.blue);
        g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
    }

    public boolean contains(int x, int y) {
        if (shape == null || shape.getBounds().equals(getBounds())) {
            shape = new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, 45, 45);
        }
        return shape.contains(x, y);
    }

}
