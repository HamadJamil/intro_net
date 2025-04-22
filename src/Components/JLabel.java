package Components;

import Utilities.GUIConstants;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class JLabel extends javax.swing.JLabel {

    public JLabel(ImageIcon imageIcon) {
    }

    public JLabel(String text, int textSize, Color color, int style) {
        setFont(new Font("Agu Display", style, textSize));
        setText(text);
        setForeground(color);
    }

    public JLabel(ImageIcon imageIcon, int width, int height) {
        Image originalImage = imageIcon.getImage();
        Image scaledImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        setIcon(scaledIcon);
    }
}
