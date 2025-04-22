package Controller;

import Backend.DataBase;
import Components.JAlert;
import Model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReadUser {
    private boolean loggedIn = false;
    private User user;
    public ReadUser(String email, String password, DataBase dataBase){
        String select = "SELECT * FROM `users` WHERE `Email` = '" + email + "' AND `Password` = '" + password + "';";
        try {
            ResultSet resultSet = dataBase.getStatement().executeQuery(select);
            loggedIn = resultSet.next();
            if (loggedIn){
                user = new User();
                user.setId(resultSet.getInt("ID"));
                user.setFirstName(resultSet.getString("FirstName"));
                user.setLastName(resultSet.getString("LastName"));
                user.setEmail(resultSet.getString("Email"));
                user.setPassword(resultSet.getString("Password"));

                String findFriends = "SELECT * FROM friends WHERE User = '"+user.getId()+"';";
                ResultSet resultSet1 = dataBase.getStatement().executeQuery(findFriends);
                ArrayList<Integer> friendsIds = new ArrayList<>();
                while (resultSet1.next()){
                    friendsIds.add(resultSet1.getInt("Friend"));
                }
                user.setFriendsIDs(friendsIds);

                String findLikedPosts = "SELECT * FROM likes WHERE User = '"+user.getId()+"';";
                ResultSet resultSet2 = dataBase.getStatement().executeQuery(findLikedPosts);
                ArrayList<Integer> likedPostsIds = new ArrayList<>();
                while (resultSet2.next()){
                    likedPostsIds.add(resultSet2.getInt("Post"));
                }
                user.setLikesIDs(likedPostsIds);
            }
        } catch (SQLException e) {
            new JAlert(e.getMessage(),null);
        }
    }

    public boolean isLoggedIn(){
        return loggedIn;
    }

    public User getUser(){
        return user;
    }
}
