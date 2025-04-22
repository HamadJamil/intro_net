package Controller;

import Backend.DataBase;
import Components.JComment;
import Components.JPost;
import Model.Comment;
import Model.Post;
import Model.User;
import View.JFrame;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReadUserComments {
    private ArrayList<JPanel> panels;

    public ArrayList<JPanel> getPanels() {
        return panels;
    }

    public ReadUserComments(User user, DataBase dataBase, JFrame jFrame) {
        panels = new ArrayList<>();
        String select = "SELECT * FROM `comments` WHERE `User` = '" + user.getId() + "';";
        try {
            ResultSet resultSet = dataBase.getStatement().executeQuery(select);
            ArrayList<Comment> comments = new ArrayList<>();
            ArrayList<Integer> postIDs = new ArrayList<>();
            while (resultSet.next()) {
                Comment comment = new Comment();
                comment.setCommentID(resultSet.getInt("ID"));
                comment.setContent(resultSet.getString("Content"));
                comment.setUser(user);
                comment.setDateTimeFromString(resultSet.getString("DateTime"));
                comments.add(comment);
                postIDs.add(resultSet.getInt("Post"));
            }
            for (int i = 0; i < comments.size(); i++) {
                Post post = new ReadPostByID(postIDs.get(i),dataBase).getPost();
                panels.add(new JPost(user,post,dataBase,jFrame) );
                panels.add(new JComment(comments.get(i)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
