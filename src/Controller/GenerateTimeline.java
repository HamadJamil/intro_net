package Controller;

import Backend.DataBase;
import Model.Post;
import Model.User;

import java.sql.*;
import java.util.ArrayList;

public class GenerateTimeline {
    private DataBase dataBase;
    private User user;
    private ArrayList<Post> posts;

    public GenerateTimeline(DataBase dataBase, User user) {
        this.dataBase = dataBase;
        this.user = user;
        this.posts = new ArrayList<>();

        // Build the WHERE clause
        StringBuilder stringBuilder = new StringBuilder();
        if (!user.getFriendsID().isEmpty()) {
            for (int i = 0; i < user.getFriendsID().size(); i++) {
                stringBuilder.append("`User` = '").append(user.getFriendsID().get(i)).append("'");
                if (i != user.getFriendsID().size() - 1) {
                    stringBuilder.append(" OR ");
                }
            }
        } else {
            return; // No friends, no posts to fetch
        }

        String select = "SELECT * FROM posts WHERE " + stringBuilder.toString();

        try (ResultSet resultSet = dataBase.getStatement().executeQuery(select)) {
            ArrayList<Integer> userIds = new ArrayList<>();
            while (resultSet.next()) {
                Post p = new Post();
                p.setPostID(resultSet.getInt("ID"));
                p.setContent(resultSet.getString("Content"));
                userIds.add(resultSet.getInt("User"));
                p.setDateTimeFromString(resultSet.getString("DateTime"));
                posts.add(p);
            }
            for (int i = 0; i < posts.size(); i++) {
                posts.get(i).setUser(new ReadUserByID(userIds.get(i), dataBase).getUser());
            }
        } catch (SQLException e) {
            System.err.println("Error executing query: " + select);
            e.printStackTrace();
            throw new RuntimeException("Failed to generate timeline", e);
        }
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }
}
