package View;

import Backend.DataBase;
import Components.*;
import Components.JButton;
import Components.JTextArea;
import Controller.CreateComment;
import Controller.ReadPostComments;
import Model.Comment;
import Model.User;
import Utilities.GUIConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Comments {
    private User user;
    private Model.Post post;
    private DataBase dataBase;

    public Comments(User user, Model.Post post, DataBase dataBase) {
        this.user = user;
        this.post = post;
        this.dataBase = dataBase;

        JFrame jFrame = new JFrame();

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(null);

        panel.add(new JPost(user, post, dataBase, jFrame));
        panel.add(Box.createVerticalStrut(7));

        JPanel newComment = new JPanel(new BorderLayout());
        newComment.setBackground(GUIConstants.white);
        Dimension dimension = new Dimension(500, 58);
        newComment.setPreferredSize(dimension);
        newComment.setMaximumSize(dimension);
        newComment.setMinimumSize(dimension);
        newComment.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 15));

        JTextArea commentIn = new JTextArea("Type a comment", 18, 5);
        newComment.add(new Components.JScrollPane(commentIn), BorderLayout.CENTER);

        JButton commentButton = new JButton("Send", 35, 16);
        commentButton.setPreferredSize(new Dimension(81, 37));
        commentButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (commentIn.isEmpty()) {
                    new JAlert("Cannot publish empty Comment!", jFrame);
                    return;
                }
                Comment comment = new Comment(commentIn.getText(), user);
                if (new CreateComment(comment, post, user, dataBase).commented()) {
                    new JAlert("Comment published!", jFrame);
                    commentIn.setText("");
                    panel.add(new JComment(comment) );
                    panel.revalidate();
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
        newComment.add(commentButton, BorderLayout.EAST);
        panel.add(newComment);
        panel.add(Box.createVerticalStrut(7));

        ArrayList<Comment> comments = new ReadPostComments(post, dataBase).getComments();
        for (Comment c : comments) {
            panel.add(new JComment(c));
            panel.add(Box.createVerticalStrut(7));
        }

        jFrame.getContentPane().add(new Components.JScrollPane(panel));
        jFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                super.windowClosed(e);
                new Home(user,dataBase);
            }
        });
        jFrame.setVisible(true);
        jFrame.requestFocus();

    }

}
