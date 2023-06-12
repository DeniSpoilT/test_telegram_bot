package ru.komarov;

import ru.komarov.db.PostgresConnectionManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

public class ErrorDescriptor {

    Set<String> errors;

    public ErrorDescriptor() {
        try (Statement stmt = new PostgresConnectionManager().getConnection().createStatement()) {
            ResultSet resultSet = stmt.executeQuery("select id from t_bot_db.f_errors");
            errors = new HashSet<>();
            while (resultSet.next()){
                errors.add(resultSet.getString("id"));
            }
            System.out.println("Error descriptor created");
            errors.forEach(System.out::println);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getDescription(String error){
        System.out.println("entering to method");
        String description = "";
        try (Statement stmt = new PostgresConnectionManager().getConnection().createStatement()) {
            ResultSet resultSet = stmt.executeQuery(
                    "select text_column from t_bot_db.f_errors where id = '" +  error + "'"
            );
        //обязательно поправить в защиту от sql - инъекций
            while (resultSet.next()) {
                description = resultSet.getString("text_column");
                System.out.println(description);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  description;
    }


    public Set<String> getErrors() {
        return errors;
    }

}
