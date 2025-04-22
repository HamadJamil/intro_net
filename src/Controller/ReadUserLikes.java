package Controller;

import Backend.DataBase;
import Model.Post;
import Model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReadUserLikes {
    private ArrayList<Post> posts;

    public ArrayList<Post> getPosts() {
        return posts;
    }

    public ReadUserLikes(User user, DataBase dataBase) {
        posts = new ArrayList<>();
        String select = "SELECT * FROM `likes` WHERE `User` = '"+user.getId()+"';";
        ArrayList<Integer> postIDs = new ArrayList<>();
        try {
            ResultSet resultSet = dataBase.getStatement().executeQuery(select);
            while (resultSet.next()){
                postIDs.add(resultSet.getInt("Post"));
            }
            for (int i = 0; i < postIDs.size(); i++) {
                posts.add(new ReadPostByID(postIDs.get(i),dataBase).getPost());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
