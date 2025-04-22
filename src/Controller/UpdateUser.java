package Controller;

import Backend.DataBase;
import Components.JAlert;
import Model.User;

import java.sql.SQLException;

public class UpdateUser {

    private DataBase dataBase;
    private User user;

    public UpdateUser(User user, DataBase dataBase) {
        this.dataBase = dataBase;
        this.user = user;
    }

    public boolean isEmailUsed() {
        return new CreateUser(user, dataBase).isEmailUsed();
    }

    public boolean upDate() {
        boolean updated = false;
        String update = "UPDATE `users` SET `FirstName` = '" + user.getFirstName() + "', `LastName` = '" + user.getLastName() + "', `Email` = '" + user.getEmail() + "' WHERE `ID` = " + user.getId() + ";";
        try {
            dataBase.getStatement().execute(update);
            updated = true;
        } catch (SQLException e) {
            new JAlert(e.getMessage(), null);
            updated = false;
        }
        return updated;
    }
}
