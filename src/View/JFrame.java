package View;

import Utilities.GUIConstants;

import java.awt.*;

public class JFrame extends javax.swing.JFrame {
    public JFrame(){
        super("Intro Net");
        getContentPane().setBackground(GUIConstants.backgroundColor);
        setSize(900,625);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}
