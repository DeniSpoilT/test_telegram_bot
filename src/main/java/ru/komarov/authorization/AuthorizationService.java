package ru.komarov.authorization;

import ru.komarov.db.PostgresConnectionManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

public class AuthorizationService {
    Set<String> userNames;

    public AuthorizationService() {

        userNames = new HashSet<>();
        try (Statement stmt = new PostgresConnectionManager().getConnection().createStatement()) {
            ResultSet resultSet = stmt.executeQuery("select * from t_bot_db.users");

            while (resultSet.next()) {
                userNames.add(resultSet.getString("username"));
            }

            System.out.println("AuthorizationService has been created.");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Set<String> getUserNames() {
        return userNames;
    }
}
