package Controller;

import Backend.DataBase;
import Components.JAlert;
import Model.Comment;
import Model.Post;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReadPostComments {
    private int commentsCount;
    private Comment comment;
    private DataBase dataBase;
    ArrayList<Comment> comments;


    public ReadPostComments(Post post, DataBase dataBase) {
        this.dataBase = dataBase;
        commentsCount = 0;
        String select = "SELECT * FROM `comments` WHERE `Post` = '" + post.getPostID() + "';";
        ArrayList<Integer> userIDs = new ArrayList<>();
        comments = new ArrayList<>();
        try {
            ResultSet resultSet = dataBase.getStatement().executeQuery(select);
            while (resultSet.next()) {
                commentsCount++;
                Comment c = new Comment();
                c.setCommentID(resultSet.getInt("ID"));
                c.setContent(resultSet.getString("Content"));
                userIDs.add(resultSet.getInt("User"));
                c.setDateTimeFromString(resultSet.getString("DateTime"));
                comments.add(c);
            }
            for (int i = 0; i < comments.size(); i++) {
                comments.get(i).setUser(new ReadUserByID(userIDs.get(i), dataBase).getUser());
            }
        } catch (SQLException e) {
            new JAlert(e.getMessage(), null);
        }
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public int getLikesCount() {
        return commentsCount;
    }
}
