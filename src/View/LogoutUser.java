package View;

import Backend.DataBase;
import Components.JLabel;
import Model.User;
import Utilities.GUIConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class LogoutUser extends JPanel {
    public LogoutUser(User u, DataBase database, JFrame f) {
        setLayout(new BorderLayout());
        setBackground(GUIConstants.backgroundColor);
        setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        JLabel author = new JLabel(u.getFirstName() + " " + u.getLastName(), 21, GUIConstants.black, Font.BOLD);
        add(author, BorderLayout.WEST);

        JPanel right = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        right.setBackground(null);

        Components.JButton logOut = new Components.JButton("Logout", 35, 16);
        logOut.setPreferredSize(new Dimension(81, 37));
        logOut.setVisible(true);
        logOut.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new LogIn(database);
                f.dispose();
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
        right.add(logOut);


        add(right);

        Dimension dimension = new Dimension(500, 73);
        setPreferredSize(dimension);
        setMaximumSize(dimension);
        setMinimumSize(dimension);
    }
}
