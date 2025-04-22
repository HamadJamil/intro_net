package Controller;

import Backend.DataBase;
import Components.JAlert;
import Model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CreateUser {
    private User user;
    private DataBase dataBase;

    public CreateUser(User user, DataBase dataBase) {
        this.user = user;
        this.dataBase = dataBase;
    }

    // Create User
    public void create() {
        String insert = "INSERT INTO `users` (`FirstName`, `LastName`, `Email`, `Password`) VALUES ('" + user.getFirstName() + "', '" + user.getLastName() + "', '" + user.getEmail() + "', '" + user.getPassword() + "');";
        try {
            dataBase.getStatement().execute(insert);
        } catch (SQLException exception) {
            new JAlert(exception.getMessage(), null);
        }
    }

    // Check Email is Taken or not
    public boolean isEmailUsed() {
        String select = "SELECT * FROM `users` WHERE `Email` = '" + user.getEmail() + "';";
        boolean used = false;
        try {
            ResultSet resultSet = dataBase.getStatement().executeQuery(select);
            used = resultSet.next();
        } catch (SQLException e) {
            new JAlert(e.getMessage(), null);
        }
        return used;
    }


    // Get User
    public User getUser() {
        user.setFriends(new ArrayList<>());
        String select = "SELECT 'ID' FROM `users` WHERE `Email` = '" + user.getEmail() + "' AND `Password` = '" + user.getPassword() + "';";

        try {
            ResultSet resultSet = dataBase.getStatement().executeQuery(select);
            resultSet.next();
            user.setId(resultSet.getInt("ID"));
        } catch (SQLException e) {
            new JAlert(e.getMessage(), null);
        }
        return user;
    }
}
