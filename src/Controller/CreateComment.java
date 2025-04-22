package Controller;

import Backend.DataBase;
import Components.JAlert;
import Model.Comment;
import Model.Post;
import Model.User;

import java.sql.SQLException;

public class CreateComment {
    private Comment comment;
    private DataBase dataBase;
    private Post post;
    private User user;

    public CreateComment(Comment comment, Post post, User user, DataBase dataBase) {
        this.comment = comment;
        this.post = post;
        this.user = user;
        this.dataBase = dataBase;
    }

    public boolean commented() {
        boolean commented = false;
        String content = comment.getContent().replace("'", "''");
        String insert = "INSERT INTO `comments`(`Content`, `Post`, `User`, `DateTime`) VALUES ('" + content + "','" + post.getPostID() + "','" + user.getId() + "','" + comment.getDateTimeToString() + "');";
        try {
            dataBase.getStatement().execute(insert);
            commented = true;
        } catch (SQLException e) {
            e.printStackTrace();
//            new JAlert(e.getMessage(), null);
            commented = false;
        }
        return commented;
    }
}
