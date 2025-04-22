package View;

import Backend.DataBase;
import Components.JAlert;
import Components.JButton;
import Components.JLabel;
import Components.JPasswordField;
import Components.JTextField;
import Controller.CreateUser;
import Model.User;
import Utilities.GUIConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.regex.*;


public class WelcomeScreen {
    public WelcomeScreen(DataBase dataBase) {
        JFrame jFrame = new JFrame();
        JPanel jPanel = new JPanel(new BorderLayout());

        jPanel.setBackground(null);
        jPanel.setBorder(BorderFactory.createEmptyBorder(53, 84, 76, 84));

        jPanel.add(new JLabel("Welcome", 40, GUIConstants.blue, Font.BOLD), BorderLayout.NORTH);
        JPanel centerPanel = new JPanel(new GridLayout(6, 1, 10, 10));
//        centerPanel.setLayout(new BoxLayout(centerPanel,BoxLayout.Y_AXIS));
        centerPanel.setBackground(null);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(22, 231, 17, 231));

        // TextFields

        JTextField firstName = new JTextField("First Name");
        centerPanel.add(firstName);
        JTextField lastName = new JTextField("Last Name");
        centerPanel.add(lastName);

        JTextField email = new JTextField("Email");
        centerPanel.add(email);

        JPasswordField password = new JPasswordField("Password");
        centerPanel.add(password);
        JPasswordField confirmPassword = new JPasswordField("Confirm Password");
        centerPanel.add(confirmPassword);

        // Buttons
        JButton createAccount = new JButton("Create Account", 45, 20);
        centerPanel.add(createAccount);
        jPanel.add(centerPanel, BorderLayout.CENTER);


        JLabel login = new JLabel("Already have an account? Login", 20, GUIConstants.black, Font.BOLD);
        login.setCursor(new Cursor(Cursor.HAND_CURSOR));
        login.setHorizontalAlignment(JLabel.CENTER);
        jPanel.add(login, BorderLayout.SOUTH);

        // Mouse Listener
        createAccount.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (firstName.isEmpty()) {
                    new JAlert("First Name cannot be Empty!", jFrame);
                    return;
                }
                if (lastName.isEmpty()) {
                    new JAlert("Last Name cannot be Empty!", jFrame);
                    return;
                }
                if (email.isEmpty()) {
                    new JAlert("Email cannot be Empty!", jFrame);
                    return;
                }
                if (!isValidGmailEmail(email.getText())) {
                    new JAlert("Email format should be like user@gmail.com.", jFrame);
                    return;
                }
                if (password.isEmpty()) {
                    new JAlert("Password cannot be Empty!", jFrame);
                    return;
                }
                if (password.getText().length() < 6) {
                    new JAlert("Password should contains 6 Characters", jFrame);
                    return;
                }
                if (confirmPassword.isEmpty()) {
                    new JAlert("Please Confirm Password!", jFrame);
                    return;
                }
                if (!password.getText().equals(confirmPassword.getText())) {
                    new JAlert("Password Doesn't match", jFrame);
                    return;
                }
                User user = new User();
                user.setFirstName(firstName.getText());
                user.setLastName(lastName.getText());
                user.setEmail(email.getText());
                user.setPassword(password.getText());
                CreateUser createUser = new CreateUser(user, dataBase);
                if (!createUser.isEmailUsed()) {
                    createUser.create();
                    user = createUser.getUser();
                    new Home(user,dataBase);
                    jFrame.dispose();
                } else {
                    new JAlert("Email has Already Taken, ID : " + user.getId(), jFrame);
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
        login.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new LogIn(dataBase);
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

    public  static boolean isValidGmailEmail(String email) {
        // Regular expression to match emails with @gmail.com in a case-insensitive manner
        String regex = "^[a-zA-Z0-9._%+-]+@gmail\\.com$";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

}
