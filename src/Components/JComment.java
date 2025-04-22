package Components;

import Backend.DataBase;
import Model.Comment;
import Model.User;
import Utilities.GUIConstants;

import javax.swing.*;
import java.awt.*;

public class JComment extends JPanel {

    public JComment(Comment comment) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(GUIConstants.white);
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 25));

        // Header of Comment
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(null);

        JLabel author = new JLabel(comment.getUser().getName(), 20, GUIConstants.postColor, Font.BOLD);
        header.add(author, BorderLayout.WEST);
        add(header);

        // Center of Comments
        JPanel center = new JPanel(new FlowLayout(FlowLayout.LEADING));
        center.setBackground(null);

        JTextArea content = new JTextArea(comment.getContent(), 18, GUIConstants.postColor, Font.PLAIN);
        center.add(content);

        add(center);

        // Bottom of Comments
        JPanel bottom = new JPanel(new BorderLayout());
        bottom.setBackground(null);
        bottom.add(new JLabel(comment.getDateToString(), 15, GUIConstants.postColor, Font.PLAIN), BorderLayout.EAST);
        add(bottom);

        int height = (int) (90 + content.getPreferredSize().getHeight());
        Dimension dimension = new Dimension(500, height);
        setPreferredSize(dimension);
        setMaximumSize(dimension);
        setMinimumSize(dimension);
    }
}
