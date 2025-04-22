package Controller;

import Backend.DataBase;
import Components.JAlert;
import Model.Post;
import Model.User;

import java.sql.SQLException;

public class LikePost {
    private User user;
    private DataBase dataBase;
    private Post p;

    public LikePost(User user, DataBase dataBase, Post p) {
        this.user = user;
        this.dataBase = dataBase;
        this.p = p;
    }

    public boolean isLiked() {
        boolean liked = false;

        String insert = "INSERT INTO `likes`(`User`, `Post`) VALUES ('"+user.getId()+"','"+p.getPostID()+"');";
        try {
            dataBase.getStatement().execute(insert);
            liked = true;
        } catch (SQLException e) {

            new JAlert(e.getMessage(), null);
            liked = false;
        }
        return liked;
    }
}
