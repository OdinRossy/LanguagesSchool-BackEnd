package com.company.database;

import com.company.model.HelpStudent;

import java.sql.Connection;
import java.sql.SQLException;

public class MySqlHelpStudentHandler extends MySqlHandler {

    public static boolean insertHelpStudent(String username, String message) {
        try (Connection connection = getDBConnection()) {

            final String query = "INSERT INTO LanguagesSchool.help_students " +
                    "(student_username, message) VALUES (?, ?);";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2,message);

            return preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closePreparedStatement();
        }
    }

}
