package Controller;

import Backend.DataBase;
import Components.JAlert;
import Model.User;

import java.sql.SQLException;

public class RemoveFriend {
    private User user, f;
    private DataBase dataBase;

    public RemoveFriend(User user, User f, DataBase dataBase) {
        this.user = user;
        this.f = f;
        this.dataBase = dataBase;
    }

    public boolean isRemoved() {
        boolean removed = false;
        String delete = "DELETE FROM friends WHERE User = '" + user.getId() + "' AND Friend = '" + f.getId() + "';";
        try {
            dataBase.getStatement().execute(delete);
            removed = true;
        } catch (SQLException e) {
            new JAlert(e.getMessage(), null);
        }
        return removed;
    }

}
