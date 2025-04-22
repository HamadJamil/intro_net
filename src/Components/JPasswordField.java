package Components;

import Utilities.GUIConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.geom.RoundRectangle2D;

public class JPasswordField extends javax.swing.JPasswordField {
    private Shape shape;
    private String hint;

    public JPasswordField(String hint) {
        super();
        this.hint = hint;

        setFont(new Font("Agu Display", Font.BOLD, 20));
        setOpaque(false);
        setEchoChar('\0');
        setText(hint);
        setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        setForeground(GUIConstants.textFieldHint);

        // Focus listener for hint text behavior
        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (getText().equals(hint)) {
                    setText("");
                    setForeground(GUIConstants.black);
                    setEchoChar('*');  // Mask the password characters
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (getText().equals("")) {
                    setText(hint);
                    setForeground(GUIConstants.textFieldHint);
                    setEchoChar('\0');  // Show hint text in plain format (no mask)
                }
            }
        });
    }

    // For rounded corners in the background
    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(GUIConstants.white);  // Set background color
        g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 45, 45);
        super.paintComponent(g);
    }

    // For rounded border around the text field
    @Override
    protected void paintBorder(Graphics g) {
        g.setColor(GUIConstants.white);  // Set border color
        g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 45, 45);
    }

    @Override
    public boolean contains(int x, int y) {
        if (shape == null || shape.getBounds().equals(getBounds())) {
            shape = new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, 45, 45);
        }
        return shape.contains(x, y);
    }

    // Returns whether the field is empty or contains the hint text
    public boolean isEmpty() {
        return (new String(getPassword()).equals("") || new String(getPassword()).equals(hint));
    }
}
