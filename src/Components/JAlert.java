package Components;

import Utilities.GUIConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import javax.swing.Timer;

public class JAlert {

    public JAlert(String content, JFrame parent) {
        // Create the alert JFrame
        JFrame jFrame = new JFrame();
        jFrame.setSize(430, 170);
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame.setResizable(false);
        jFrame.setUndecorated(true); // Remove title bar for clean look
        jFrame.setOpacity(0f); // Start with 0 opacity for fade-in effect

        // Make the JFrame rounded
        jFrame.setShape(new RoundRectangle2D.Double(0, 0, jFrame.getWidth(), jFrame.getHeight(), 20, 20));

        // Panel for alert content
        JPanel jPanel = new JPanel(new BorderLayout(5, 5)) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Create a gradient background
                Graphics2D g2d = (Graphics2D) g;
                Color color1 = new Color(85, 176, 230); // light blue
                Color color2 = new Color(48, 126, 186); // darker blue
                GradientPaint gradient = new GradientPaint(0, 0, color1, 0, getHeight(), color2);
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        jPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        jPanel.setLayout(new BorderLayout(5, 5)); // Set layout for proper spacing

        // Title Label
        JLabel title = new JLabel("Alert", 24, Color.WHITE, Font.BOLD);
        title.setHorizontalAlignment(JLabel.CENTER);
        jPanel.add(title, BorderLayout.NORTH);

        // Message Label
        JLabel msg = new JLabel(content, 18, Color.WHITE, Font.BOLD);
        msg.setHorizontalAlignment(JLabel.CENTER);
        jPanel.add(msg, BorderLayout.CENTER);

        // Set the content panel inside the JFrame
        jFrame.getContentPane().add(jPanel);
        jFrame.setLocationRelativeTo(parent); // Center the alert dialog
        jFrame.setVisible(true); // Show the frame

        // Fade-in Animation (from opacity 0 to 1)
        Timer fadeInTimer = new Timer(10, new ActionListener() {
            private float opacity = 0f;

            @Override
            public void actionPerformed(ActionEvent e) {
                opacity += 0.05f; // Increase opacity gradually
                if (opacity >= 1f) {
                    opacity = 1f;
                    ((Timer) e.getSource()).stop(); // Stop timer once fade-in is complete
                }
                jFrame.setOpacity(opacity);
            }
        });
        fadeInTimer.start(); // Start fade-in animation

        // Automatically close the alert after 3 seconds (adjust as needed)
        Timer closeTimer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Fade-out effect (decrease opacity)
                Timer fadeOutTimer = new Timer(10, new ActionListener() {
                    private float opacity = 1f;

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        opacity -= 0.05f; // Decrease opacity gradually
                        if (opacity <= 0f) {
                            opacity = 0f;
                            jFrame.dispose(); // Dispose the frame after fade-out completes
                            ((Timer) e.getSource()).stop(); // Stop timer once fade-out is complete
                        }
                        jFrame.setOpacity(opacity);
                    }
                });
                fadeOutTimer.start(); // Start fade-out animation
            }
        });
        closeTimer.setRepeats(false); // Only run once
        closeTimer.start(); // Start the auto-close timer (3 seconds)
    }
}
