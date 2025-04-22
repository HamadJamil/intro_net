package Controller;

import Backend.DataBase;
import Model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReadUserByID {

    private User user;

    public ReadUserByID(int ID, DataBase dataBase) {
        String select = "SELECT * FROM `users` WHERE `ID` = '" + ID + "';";
        try {
            ResultSet resultSet = dataBase.getStatement().executeQuery(select);
            if (resultSet.next()) { // Check if the result set contains any rows
                user = new User();
                user.setId(ID);
                user.setFirstName(resultSet.getString("FirstName"));
                user.setLastName(resultSet.getString("LastName"));
                user.setEmail(resultSet.getString("Email"));
            } else {
                throw new RuntimeException("No user found with ID: " + ID);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error reading user with ID: " + ID, e);
        }
    }

    public User getUser() {
        return user;
    }
}
