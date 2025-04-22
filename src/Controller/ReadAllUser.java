package Controller;

import Backend.DataBase;
import Components.JAlert;
import Model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReadAllUser {
    private ArrayList<User> users;

    public ReadAllUser(DataBase dataBase, User user) {
        String select = "SELECT * FROM `users`;";
        users = new ArrayList<>();
        try {
            ResultSet resultSet = dataBase.getStatement().executeQuery(select);
            while (resultSet.next()) {
                User u = new User();
                u.setId(resultSet.getInt("ID"));
                u.setFirstName(resultSet.getString("FirstName"));
                u.setLastName(resultSet.getString("LastName"));
                u.setEmail(resultSet.getString("Email"));
                if (u.getId() != user.getId()) {
                    users.add(u);
                }
            }
        } catch (SQLException e) {
            new JAlert(e.getMessage(), null);
        }
    }
    public ArrayList<User> getList(){
        return users;
    }
}
