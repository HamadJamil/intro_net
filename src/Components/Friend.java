package Components;


import Backend.DataBase;
import Controller.AddFriend;
import Controller.RemoveFriend;
import Model.User;
import Utilities.GUIConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Friend extends JPanel {
    public Friend(User mainUser,DataBase dataBase, User u) {
        setLayout(new BorderLayout());
        setBackground(GUIConstants.white);
        setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        JLabel author = new JLabel(u.getName(), 20, GUIConstants.postColor, Font.BOLD);
        add(author, BorderLayout.WEST);

        JPanel right = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        right.setBackground(null);

        Components.JButton addButton = new Components.JButton("Follow", 35, 16);
        addButton.setPreferredSize(new Dimension(81, 37));
        addButton.setVisible(false);
        right.add(addButton);

        JLabel remove = new JLabel("Unfollow", 16, GUIConstants.blue, Font.BOLD);
        remove.setCursor(new Cursor(Cursor.HAND_CURSOR));
        remove.setVisible(false);
        remove.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        right.add(remove);

        if (mainUser.isFriend(u)) {
            addButton.setVisible(false);
            remove.setVisible(true);
        } else {
            addButton.setVisible(true);
            remove.setVisible(false);
        }

        addButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (new AddFriend(mainUser,u,dataBase).isAdded()){
                    mainUser.addFriend(u);
                    addButton.setVisible(false);
                    remove.setVisible(true);
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

        remove.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (new RemoveFriend(mainUser,u,dataBase).isRemoved()){
                    mainUser.removeFriend(u);
                    addButton.setVisible(true);
                    remove.setVisible(false);

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
        add(right);

        Dimension dimension = new Dimension(500, 67);
        setPreferredSize(dimension);
        setMaximumSize(dimension);
        setMinimumSize(dimension);
    }
}
