package Controller;

import Backend.DataBase;
import Components.JAlert;
import Model.Post;
import Model.User;

import java.sql.SQLException;

public class DislikePost {
    private User user;
    private DataBase dataBase;
    private Post p;

    public DislikePost(User user, DataBase dataBase, Post p) {
        this.user = user;
        this.dataBase = dataBase;
        this.p = p;
    }

    public boolean isDisliked() {
        boolean dislike = false;

        String delete = "DELETE FROM `likes` WHERE `User` = '" + user.getId() + "' AND `Post`= '" + p.getPostID() + "';";
        try {
            dataBase.getStatement().execute(delete);
            dislike = true;
        } catch (SQLException e) {

            new JAlert(e.getMessage(), null);
            dislike = false;
        }
        return dislike;
    }
}
