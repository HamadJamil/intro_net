package Controller;

import Backend.DataBase;
import Components.JAlert;
import Model.User;

import java.sql.SQLException;

public class ChangePassword {
    private String password;
    private int ID;
    private DataBase dataBase;

    public ChangePassword(String password, int ID, DataBase dataBase) {
       this.password = password;
        this.ID = ID;
        this.dataBase = dataBase;
    }

    public boolean change(){
        boolean changed = false;
        String update = "UPDATE `users` SET `Password` = '" + password + "' WHERE `ID` = " + ID + ";";
        try {
            dataBase.getStatement().execute(update);
            changed = true;
        } catch (SQLException e) {
            new JAlert(e.getMessage(), null);
            changed = false;
        }
        return changed;
    }
}
