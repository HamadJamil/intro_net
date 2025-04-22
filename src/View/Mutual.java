package View;

import Backend.DataBase;
import Components.JAlert;
import Components.JButton;
import Model.Graph;
import Model.User;
import Utilities.GUIConstants;

import javax.swing.*;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

public class Mutual extends JPanel {

    public Mutual(User mainUser, DataBase database, User u) {
        setLayout(new BorderLayout());
        setBackground(GUIConstants.white);
        setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        Components.JLabel author = new Components.JLabel(u.getFirstName() + " " + u.getLastName(), 21, GUIConstants.black, Font.BOLD);
        add(author, BorderLayout.WEST);

        JPanel right = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        right.setBackground(null);

        JButton viewMutualFriend = new JButton("View", 35, 16);
        viewMutualFriend.setPreferredSize(new Dimension(81, 37));
        right.add(viewMutualFriend);


        viewMutualFriend.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Graph graph = new Graph();
                List<String> mutualFriends = graph.getMutualFriends(mainUser.getId(), u.getId());
                if (mutualFriends.isEmpty()) {
                    new JAlert("No Mutual Friends Found to show!",null);
                    return;
                }
                JOptionPane.showMessageDialog(null, mutualFriends);
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

        Dimension dimension = new Dimension(500, 73);
        setPreferredSize(dimension);
        setMaximumSize(dimension);
        setMinimumSize(dimension);
    }

}
