package View;

import Backend.DataBase;

import Components.*;
import Components.JButton;
import Components.JLabel;
import Controller.*;
import Model.Graph;
import Model.Post;
import Model.User;
import Utilities.GUIConstants;

import javax.swing.*;
import java.awt.*;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CustomView {
    public CustomView(String view, User user, DataBase dataBase) {
        JFrame jFrame = new JFrame();

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(null);

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(GUIConstants.white);
        Dimension dimension = new Dimension(500, 50);
        header.setPreferredSize(dimension);
        header.setMaximumSize(dimension);
        header.setMinimumSize(dimension);
        header.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        JPanel north = new JPanel(new BorderLayout());
        north.setBackground(GUIConstants.white);
        north.add(new JLabel("My " + view, 20, GUIConstants.black, Font.BOLD), BorderLayout.WEST);

        JLabel home = new JLabel(new ImageIcon("pics/myHome.png"), 30, 30);
        home.setCursor(new Cursor(Cursor.HAND_CURSOR));
        home.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new Home(user, dataBase);
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
                Image scaledImage = new ImageIcon("pics/myHomeHover.png").getImage().getScaledInstance(31, 31, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(scaledImage);
                home.setIcon(scaledIcon);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                Image scaledImage = new ImageIcon("pics/myHome.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(scaledImage);
                home.setIcon(scaledIcon);
            }
        });
        north.add(home, BorderLayout.EAST);
        header.add(north, BorderLayout.NORTH);
        panel.add(header);

        ArrayList<User> users = new ReadAllUser(dataBase, user).getList();
        switch (view) {
            case "Friends": {

                for (User u : users) {
                    panel.add(Box.createVerticalStrut(7));
                    panel.add(new Friend(user, dataBase, u));
                }
                break;
            }
            case "Posts": {
                ArrayList<Post> posts = new ReadUserPosts(user, dataBase).getPosts();
                for (Post p : posts) {
                    panel.add(Box.createVerticalStrut(7));
                    panel.add(new JPost(user, p, dataBase, jFrame));
                }
                break;
            }
            case "Comments": {

                for (JPanel p : new ReadUserComments(user, dataBase, jFrame).getPanels()) {
                    panel.add(Box.createVerticalStrut(7));
                    panel.add(p);
                }
                break;
            }
            case "Likes": {
                ArrayList<Post> likes = new ReadUserLikes(user, dataBase).getPosts();
                for (Post p : likes) {
                    panel.add(Box.createVerticalStrut(7));
                    panel.add(new JPost(user, p, dataBase, jFrame));
                }
                break;
            }
//            case "Logout": {
//                panel.add(Box.createVerticalStrut(7));
//                panel.add(new LogoutUser(user, dataBase, jFrame));
//                break;
//            }
            case "Mutual Friends": {
                Graph graph = new Graph();
                Set<Integer> friendIds = graph.getFriends(user.getId());
                ArrayList<User> users1 = new ArrayList<>();
                for (User u : users) {
                    if (friendIds.contains(u.getId())) {
                        users1.add(u);
                    }
                }
                for (User u : users1) {
                    panel.add(Box.createVerticalStrut(7));
                    panel.add(new Mutual(user, dataBase, u));
                }
                break;
            }
            case "Recommendation": {
                Graph graph1 = new Graph();
                RecommendedFriend friend = new RecommendedFriend();
                List<Integer> recommendations = friend.getFriendsOfFriend(user.getId());

                for (int recommendation : recommendations) {
                    String username = graph1.getUsernameById(recommendation);

                    JPanel recommendationPanel = new JPanel(new BorderLayout());
                    recommendationPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
                    recommendationPanel.setBackground(GUIConstants.white);

                    JLabel friendLabel = new JLabel(username, 20, GUIConstants.black, Font.BOLD);
                    recommendationPanel.add(friendLabel, BorderLayout.WEST);

                    JButton followButton = new JButton("Follow", 20, 24);
                    followButton.setPreferredSize(new Dimension(81, 37));

                    followButton.addMouseListener(new MouseListener() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            if (followButton.isEnabled()) {
                                User user1 = new ReadUserByID(recommendation, dataBase).getUser();
                                boolean followed = new AddFriend(user, user1, dataBase).isAdded();
                                user.addFriend(user1);
                                if (followed) {
                                    JAlert jAlert = new JAlert("You are now following " + username + "!", jFrame);
                                    followButton.setEnabled(false);
                                } else {
                                    JAlert jAlert = new JAlert("Failed to follow " + username + ".", jFrame);
                                }
                            } else {
                                JAlert jAlert = new JAlert("Already followed!", jFrame);
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

                    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
                    buttonPanel.setBackground(null);
                    buttonPanel.add(followButton);

                    recommendationPanel.add(buttonPanel, BorderLayout.EAST);
                    panel.add(Box.createVerticalStrut(7));
                    panel.add(recommendationPanel);
                }
                break;
            }
        }


        jFrame.getContentPane().add(new Components.JScrollPane(panel));
        jFrame.setVisible(true);
        jFrame.requestFocus();

    }
}
