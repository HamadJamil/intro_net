package Controller;

import Backend.DataBase;
import Components.JAlert;
import Model.Post;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReadPostLikes {
    private int likes;

    public ReadPostLikes(Post post, DataBase dataBase) {
        likes = 0;
        String select = "SELECT * FROM `likes` WHERE `Post` = '"+post.getPostID()+"';";
        try {
            ResultSet resultSet = dataBase.getStatement().executeQuery(select);
            while (resultSet.next()){
                likes++;
            }
        } catch (SQLException e) {
            new JAlert(e.getMessage(),null);
        }
    }
    public int getLikesCount(){
        return likes;
    }
}
