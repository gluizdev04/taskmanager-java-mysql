package io.github.luiz.databaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConenction {
    private static final String url = "jdbc:mysql://localhost:3306/task_manager";
    private static final String user = "SEU_USUARIO";
    private static final String password = "SUA_SENHA";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
