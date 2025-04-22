package Components;

import Backend.DataBase;
import Model.User;
import Utilities.GUIConstants;
import View.CustomView;
import View.JFrame;
import View.LogIn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SideBarButton extends JPanel {
    public SideBarButton(String text, String pic, User user, DataBase dataBase, JFrame frame) {

        super(new FlowLayout(FlowLayout.LEFT, 15, 10));
        setMaximumSize(new Dimension(182, 50));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setBackground(GUIConstants.white);

        JLabel imageLabel = new JLabel(new ImageIcon("pics/" + pic + ".png"), 25, 25);
        add(imageLabel);

//        add(Box.createHorizontalStrut(10));
        add(new JLabel(text, 17, GUIConstants.black, Font.BOLD));

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (text.equals("Log Out")) {
                    new LogIn(dataBase);
                    frame.dispose();
                } else {
                    new CustomView(text, user, dataBase);
                    frame.dispose();
                }

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(GUIConstants.hoverColor);
                Image scaledImage = new ImageIcon("pics/" + pic + "Hover.png").getImage().getScaledInstance(28, 28, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(scaledImage);
                imageLabel.setIcon(scaledIcon);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(GUIConstants.white);
                Image scaledImage = new ImageIcon("pics/" + pic + ".png").getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(scaledImage);
                imageLabel.setIcon(scaledIcon);
            }
        });
    }
}
