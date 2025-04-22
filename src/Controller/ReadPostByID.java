package Controller;

import Backend.DataBase;
import Model.Post;
import Model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReadPostByID {
    private Post post;

    public ReadPostByID(int ID, DataBase dataBase) {
        String select = "SELECT * FROM `posts` WHERE `ID` = '"+ID+"';";
        try {
            ResultSet resultSet = dataBase.getStatement().executeQuery(select);
            resultSet.next();
            post = new Post();
            post.setPostID(ID);
            post.setContent(resultSet.getString("Content"));
            post.setDateTimeFromString(resultSet.getString("DateTime"));
            post.setUser(new ReadUserByID(resultSet.getInt("User"),dataBase).getUser());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Post getPost() {
        return post;
    }
}
