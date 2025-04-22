package Backend;

import Components.JAlert;

import java.sql.*;

public class DataBase {
    private String user = "root";
    private String password = "";
    private String url = "jdbc:mysql://localhost:3306/database1";
    private Statement statement;

    public DataBase() {
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException exception) {
            new JAlert(exception.getMessage(),null);
        }
    }
    public Statement getStatement(){
        return statement;
    }
}
