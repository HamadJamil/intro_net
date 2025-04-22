package View;

import Backend.DataBase;
import Components.JAlert;
import Components.JButton;
import Components.JLabel;
import Components.JTextField;
import Model.User;
import Utilities.GUIConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ChangePassword {
    public ChangePassword(User user, DataBase dataBase) {
        JFrame jFrame = new JFrame();
        JPanel jPanel = new JPanel(new BorderLayout());
        jPanel.setBackground(null);
        jPanel.setBorder(BorderFactory.createEmptyBorder(83, 99, 175, 99));
        jPanel.add(new JLabel("Change Password", 40, GUIConstants.blue, Font.BOLD), BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        centerPanel.setBackground(null);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(58, 216, 0, 216));

        JTextField oldPassword = new Components.JTextField("Old Password");
        centerPanel.add(oldPassword);

        JTextField newPassword = new Components.JTextField("New Password");
        centerPanel.add(newPassword);

        JTextField confirmPassword = new JTextField("Confirm Password");
        centerPanel.add(confirmPassword);

        JButton submit = new JButton("Submit", 45, 20);
        submit.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (oldPassword.isEmpty()) {
                    new JAlert("Please Enter yor Old Password!", jFrame);
                    return;
                }
                if (!oldPassword.getText().equals(user.getPassword())) {
                    new JAlert("Incorrect Old Password!", jFrame);
                    return;
                }
                if (newPassword.isEmpty()) {
                    new JAlert("Please Enter New Password!", jFrame);
                    return;
                }
                if (newPassword.getText().length() < 6) {
                    new JAlert("Password should be atleast 6 Characters!", jFrame);
                    return;
                }
                if (confirmPassword.isEmpty()) {
                    new JAlert("Please Enter Confirm Password!", jFrame);
                    return;
                }
                if (!newPassword.getText().equals(confirmPassword.getText())) {
                    new JAlert("Password Don't Match!", jFrame);
                    return;
                }
                Controller.ChangePassword change = new Controller.ChangePassword(newPassword.getText(), user.getId(), dataBase);
                if (change.change()) {
                    new Home(user, dataBase);
                    new JAlert("Password Changed Successfully!", jFrame);
                    jFrame.dispose();
                }
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
        centerPanel.add(submit);
        jPanel.add(centerPanel, BorderLayout.CENTER);

        jFrame.getContentPane().add(jPanel);
        jFrame.setVisible(true);
        jFrame.requestFocus();
    }
}
