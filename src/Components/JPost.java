package Components;

import Backend.DataBase;
import Controller.DislikePost;
import Controller.LikePost;
import Controller.ReadPostComments;
import Controller.ReadPostLikes;
import Model.User;
import Utilities.GUIConstants;
import View.Comments;
import View.JFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class JPost extends JPanel {

    private JLabel likesCounter;
    private Model.Post post;
    private DataBase dataBase;

    public JPost(User user, Model.Post post, DataBase dataBase, JFrame jFrame) {
        this.post = post;
        this.dataBase = dataBase;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(GUIConstants.white);
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 25));

        // Header of Posts
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(null);

        JLabel author = new JLabel(post.getUser().getName(), 20, GUIConstants.postColor, Font.BOLD);
        header.add(author, BorderLayout.WEST);
        JLabel date = new JLabel(post.getDateToString(), 15, GUIConstants.postColor, Font.PLAIN);
        header.add(date, BorderLayout.EAST);

        add(header);
        add(Box.createVerticalStrut(7));

        // Center of Posts
        JPanel center = new JPanel(new FlowLayout(FlowLayout.LEADING));
        center.setBackground(null);

        JTextArea content = new JTextArea(post.getContent(), 18, GUIConstants.postColor, Font.PLAIN);
        center.add(content);

        add(center);
        add(Box.createVerticalStrut(7));

        // Footer of Posts
        JPanel bottom = new JPanel(new BorderLayout());
        bottom.setBackground(null);


        JPanel likes = new JPanel(new FlowLayout(FlowLayout.LEFT, 13, 13));
        likes.setBackground(null);

        JLabel like = new JLabel(new ImageIcon("pics/like.png"), 25, 25);

        if (user.isLiked(post)) {
            Image scaledImage = new ImageIcon("pics/redLike.png").getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);
            like.setIcon(scaledIcon);
        } else {
            Image scaledImage = new ImageIcon("pics/like.png").getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);
            like.setIcon(scaledIcon);
        }
        like.setCursor(new Cursor(Cursor.HAND_CURSOR));
        like.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!user.isLiked(post)) {
                    if (new LikePost(user, dataBase, post).isLiked()) {
                        Image scaledImage = new ImageIcon("pics/redLike.png").getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
                        ImageIcon scaledIcon = new ImageIcon(scaledImage);
                        like.setIcon(scaledIcon);
                        user.like(post);
                    }
                } else {
                    if (new DislikePost(user, dataBase, post).isDisliked()) {
                        Image scaledImage = new ImageIcon("pics/like.png").getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
                        ImageIcon scaledIcon = new ImageIcon(scaledImage);
                        like.setIcon(scaledIcon);
                        user.dislike(post);
                    }
                }
                refreshLikesCounter();
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
        likes.add(like);


        likesCounter = new Components.JLabel("", 15, GUIConstants.textFieldHint, Font.BOLD);
        refreshLikesCounter();
        likes.add(likesCounter);

        int commentsCounter = new ReadPostComments(post,dataBase).getLikesCount();
        JLabel comments = new Components.JLabel("", 15, GUIConstants.textFieldHint, Font.BOLD);
        comments.setCursor(new Cursor(Cursor.HAND_CURSOR));
        if (commentsCounter < 2) {
            comments.setText(commentsCounter + " Comment");
        } else {
            comments.setText(commentsCounter + " Comments");
        }
        comments.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new Comments(user,post,dataBase);
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
                comments.setForeground(GUIConstants.black);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                comments.setForeground(GUIConstants.textFieldHint);
            }
        });
        bottom.add(likes, BorderLayout.WEST);
        bottom.add(comments, BorderLayout.EAST);

        add(bottom);

        int height = (int) (115 + content.getPreferredSize().getHeight());
        Dimension dimension = new Dimension(500, height);
        setPreferredSize(dimension);
        setMaximumSize(dimension);
        setMinimumSize(dimension);

    }

    private void refreshLikesCounter() {
        int postLikesCount = new ReadPostLikes(post, dataBase).getLikesCount();
        String strLikes;
        if (postLikesCount < 2) {
            likesCounter.setText(postLikesCount + " Like");
        } else {
            likesCounter.setText(postLikesCount + " Likes");
        }
    }
}
