package Controller;

import Backend.DataBase;
import Components.Friend;
import Components.JAlert;
import Model.User;

import java.sql.SQLException;

public class AddFriend {
    private User user, f;
    private DataBase dataBase;

    public AddFriend(User user, User f, DataBase dataBase) {
        this.user = user;
        this.f = f;
        this.dataBase = dataBase;
    }

    public boolean isAdded() {
        boolean added = false;
        String insert = "INSERT INTO `friends`(`User`, `Friend`) VALUES ('" + user.getId() + "','" + f.getId() + "');";
        try {
            dataBase.getStatement().execute(insert);
            added = true;
        } catch (SQLException e) {
            new JAlert(e.getMessage(), null);
            added = false;
        }
        return added;
    }
}
