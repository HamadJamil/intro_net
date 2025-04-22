package Controller;

import Backend.DataBase;
import Model.Post;
import Model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReadUserPosts {
    private ArrayList<Post> posts;

    public ArrayList<Post> getPosts() {
        return posts;
    }

    public ReadUserPosts(User user, DataBase dataBase) {
        posts = new ArrayList<>();
        String select = "SELECT * FROM `posts` WHERE `User` = '"+user.getId()+"';";
        try {
            ResultSet resultSet = dataBase.getStatement().executeQuery(select);
            while (resultSet.next()){
                Post post = new Post();
                post.setPostID(resultSet.getInt("ID"));
                post.setContent(resultSet.getString("Content"));
                post.setUser(user);
                post.setDateTimeFromString(resultSet.getString("DateTime"));
                posts.add(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
