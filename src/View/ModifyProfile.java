package View;

import Backend.DataBase;
import Components.JAlert;
import Components.JButton;
import Components.JLabel;
import Components.JTextField;
import Controller.UpdateUser;
import Model.User;
import Utilities.GUIConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static View.WelcomeScreen.isValidGmailEmail;

public class ModifyProfile {
    public ModifyProfile(User user, DataBase dataBase) {
        JFrame jFrame = new JFrame();
        JPanel jPanel = new JPanel(new BorderLayout());
        jPanel.setBackground(null);
        jPanel.setBorder(BorderFactory.createEmptyBorder(72, 84, 149, 84));
        jPanel.add(new JLabel("Modify Your Profile", 40, GUIConstants.blue, Font.BOLD), BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        centerPanel.setBackground(null);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(50, 231, 17, 231));

        JTextField firstName = new JTextField("First Name");
        firstName.setText(user.getFirstName());
        firstName.setForeground(GUIConstants.black);
        centerPanel.add(firstName);

        JTextField lastName = new JTextField("Last Name");
        lastName.setText(user.getLastName());
        lastName.setForeground(GUIConstants.black);
        centerPanel.add(lastName);

        JTextField email = new JTextField("Email");
        email.setText(user.getEmail());
        email.setForeground(GUIConstants.black);
        centerPanel.add(email);

        JButton submit = new JButton("Submit", 45, 20);
        submit.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (firstName.isEmpty()) {
                    new JAlert("First Name can not be Empty!", jFrame);
                    return;
                }
                if (lastName.isEmpty()) {
                    new JAlert("Last Name can not be Empty!", jFrame);
                    return;
                }
                if (email.isEmpty()) {
                    new JAlert("Email can not be Empty!", jFrame);
                    return;
                }
                if (!isValidGmailEmail(email.getText())) {
                    new JAlert("Email format should be like user@gmail.com.", jFrame);
                    return;
                }

                User updatedUser = user;

                updatedUser.setFirstName(firstName.getText());
                updatedUser.setLastName(lastName.getText());
                updatedUser.setEmail(email.getText());
                UpdateUser updateUser = new UpdateUser(updatedUser, dataBase);
                if (!email.getText().equals(user.getEmail()) && updateUser.isEmailUsed()) {
                    new JAlert("Email is Already in Use!", jFrame);
                    return;
                }
                if (updateUser.upDate()) {
                    new Home(updatedUser, dataBase);
                    new JAlert("Profile Updated Successfully!", jFrame);
                    jFrame.dispose();
                } else {
                    new Home(user, dataBase);
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

        centerPanel.add(submit);
        jPanel.add(centerPanel, BorderLayout.CENTER);

        JLabel changePassword = new JLabel("Change Password", 20, GUIConstants.black, Font.BOLD);
        changePassword.setCursor(new Cursor(Cursor.HAND_CURSOR));
        changePassword.setHorizontalAlignment(JLabel.CENTER);
        jPanel.add(changePassword, BorderLayout.SOUTH);

        changePassword.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new ChangePassword(user, dataBase);
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
