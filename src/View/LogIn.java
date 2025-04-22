package View;

import Backend.DataBase;
import Components.JAlert;
import Components.JButton;
import Components.JLabel;
import Components.JPasswordField;
import Components.JTextField;
import Controller.ReadUser;
import Model.User;
import Utilities.GUIConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class LogIn {
    public LogIn(DataBase dataBase) {
        JFrame jFrame = new JFrame();
        JPanel jPanel = new JPanel(new BorderLayout());
        jPanel.setBackground(null);
        jPanel.setBorder(BorderFactory.createEmptyBorder(115, 0, 182, 0));

        JLabel title = new JLabel("LogIn", 40, GUIConstants.blue, Font.BOLD);
        title.setHorizontalAlignment(JLabel.CENTER);
        jPanel.add(title, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        centerPanel.setBackground(null);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(34, 315, 17, 315));

        JTextField email = new JTextField("Email");
        centerPanel.add(email);
        JPasswordField password = new JPasswordField("Password");
        centerPanel.add(password);
        JButton logIn = new JButton("Log in", 45, 20);
        centerPanel.add(logIn);

        jPanel.add(centerPanel, BorderLayout.CENTER);

        JLabel createAcc = new JLabel("Don't have an account? Create a new one", 20, GUIConstants.black, Font.BOLD);
        createAcc.setCursor(new Cursor(Cursor.HAND_CURSOR));
        createAcc.setHorizontalAlignment(JLabel.CENTER);
        jPanel.add(createAcc, BorderLayout.SOUTH);

        logIn.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (email.isEmpty()) {
                    new JAlert("Email cannot be Empty!", jFrame);
                    return;
                }
                if (password.isEmpty()) {
                    new JAlert("Password cannot be Empty!", jFrame);
                    return;
                }
                ReadUser readUser = new ReadUser(email.getText(), password.getText(), dataBase);
                if (readUser.isLoggedIn()) {
                    User user = readUser.getUser();
                    new JAlert("Successfully Logged in!", jFrame);
                    new Home(user, dataBase);
                    jFrame.dispose();
                } else {
                    new JAlert("Incorrect Email or Password!", jFrame);
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

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        createAcc.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new WelcomeScreen(dataBase);
                jFrame.dispose();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        jFrame.getContentPane().add(jPanel);
        jFrame.setVisible(true);
        jFrame.requestFocus();
    }
}
