package View;

import Backend.DataBase;
import Components.*;
import Components.JLabel;
import Components.JTextArea;
import Controller.*;
import Model.User;
import Utilities.GUIConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Home {
    public Home(User user, DataBase dataBase) {
        JFrame jFrame = new JFrame();
        jFrame.getContentPane().setLayout(new BorderLayout());

        JPanel sideBar = new JPanel();
        sideBar.setBackground(GUIConstants.white);
        Dimension sideBarDim = new Dimension(182, 1000);
        sideBar.setPreferredSize(sideBarDim);
        sideBar.setMaximumSize(sideBarDim);
        sideBar.setMinimumSize(sideBarDim);
        sideBar.setLayout(new BoxLayout(sideBar, BoxLayout.Y_AXIS));
        sideBar.add(Box.createVerticalStrut(10));

        JPanel profile = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        profile.setBackground(GUIConstants.white);
        profile.setMaximumSize(new Dimension(182, 50));
        JLabel userName = new JLabel(user.getName(), 19, GUIConstants.black, Font.BOLD);
        profile.add(userName);
        sideBar.add(profile);
        profile.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new ModifyProfile(user, dataBase);
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

                profile.setBackground(GUIConstants.blue);
                userName.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                profile.setBackground(GUIConstants.white);
                userName.setForeground(Color.BLACK);
            }
        });

        sideBar.add(Box.createVerticalStrut(3));
        sideBar.add(new SideBarButton("Posts", "myPosts", user, dataBase, jFrame));
        sideBar.add(Box.createVerticalStrut(3));
        sideBar.add(new SideBarButton("Comments", "myComments", user, dataBase, jFrame));
        sideBar.add(Box.createVerticalStrut(3));
        sideBar.add(new SideBarButton("Likes", "myLikes", user, dataBase, jFrame));
        sideBar.add(Box.createVerticalStrut(3));
        sideBar.add(new SideBarButton("Friends", "myFriends", user, dataBase, jFrame));
        sideBar.add(Box.createVerticalStrut(3));
        sideBar.add(new SideBarButton("Mutual Friends", "mutualFriends", user, dataBase, jFrame));
        sideBar.add(Box.createVerticalStrut(3));
        sideBar.add(new SideBarButton("Recommendation", "myRecommend", user, dataBase, jFrame));
        sideBar.add(Box.createVerticalStrut(3));
        sideBar.add(new SideBarButton("Log Out", "logOut", user, dataBase, jFrame));

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(null);

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(GUIConstants.white);
        Dimension dimension = new Dimension(500, 159);
        header.setPreferredSize(dimension);
        header.setMaximumSize(dimension);
        header.setMinimumSize(dimension);
        header.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        JPanel north = new JPanel(new BorderLayout());
        north.setBackground(null);
        north.add(new JLabel("H O M E", 20, GUIConstants.black, Font.BOLD), BorderLayout.WEST);
        header.add(north, BorderLayout.NORTH);

        JTextArea postIn = new JTextArea("Share Your Thoughts...", 18, 20);
        postIn.setWrapStyleWord(true);
        postIn.setLineWrap(true);
        header.add(new Components.JScrollPane(postIn), BorderLayout.CENTER);

        JPanel south = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        south.setBackground(null);

        Components.JButton postBtn = new Components.JButton("Post", 35, 16);
        postBtn.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (postIn.isEmpty()) {
                    new JAlert("Cannot Publish Empty Post!", jFrame);
                    return;
                }
                Model.Post post = new Model.Post(user, postIn.getText());
                if (new CreatePost(post, dataBase).posted()) {
                    new JAlert("Posted Successfully!", jFrame);
                    postIn.setText("");
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
        postBtn.setPreferredSize(new Dimension(81, 37));
        south.add(postBtn);

        header.add(south, BorderLayout.SOUTH);
        panel.add(header);

        ArrayList<Model.Post> posts = new GenerateTimeline(dataBase, user).getPosts();
        for (int i = 0; i < posts.size(); i++) {
            panel.add(Box.createVerticalStrut(7));
            panel.add(new JPost(user, posts.get(i), dataBase, jFrame));
        }

        jFrame.getContentPane().add(new Components.JScrollPane(panel), BorderLayout.CENTER);
        jFrame.getContentPane().add(Box.createHorizontalStrut(182), BorderLayout.EAST);
        jFrame.getContentPane().add(sideBar, BorderLayout.WEST);

        jFrame.setVisible(true);
        jFrame.requestFocus();
    }
}
