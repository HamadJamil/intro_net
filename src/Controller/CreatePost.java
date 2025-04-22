package Controller;

import Backend.DataBase;
import Components.JAlert;
import Model.Post;

import java.sql.SQLException;

public class CreatePost {
    private Post post;
    private DataBase dataBase;

    public CreatePost(Post post, DataBase dataBase) {
        this.post = post;
        this.dataBase = dataBase;
    }

    public boolean posted() {
        boolean posted = false;
        String content = post.getContent().replace("'", "''");
        String insert = "INSERT INTO `posts`(`Content`, `User`, `DateTime`) VALUES ('" + content + "', '" + post.getUser().getId() + "', '" + post.getDateTimeToString() + "');";
        try {
            dataBase.getStatement().execute(insert);
            posted = true;
        } catch (SQLException e) {
            new JAlert(e.getMessage(), null);
            posted = false;
        }
        return posted;
    }
}
