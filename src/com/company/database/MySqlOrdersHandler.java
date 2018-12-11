package com.company.database;

import com.company.model.Course;

import java.sql.Connection;
import java.sql.SQLException;

public class MySqlOrdersHandler extends MySqlHandler {

    public static boolean insertOrder(Course course, String username) {
        try (Connection connection = getDBConnection()) {

            final String query = "INSERT INTO LanguagesSchool.orders_courses " +
                    "(username_student, id_course) VALUES (?, ?);";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setInt(2, course.getId());

            int rows = preparedStatement.executeUpdate();

            if (rows < 1) {
                throw new RuntimeException("Error while adding order");
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closePreparedStatement();
        }
    }

    public static boolean deleteOrder(Course course, String username) {
        try (Connection connection = getDBConnection()) {

            final String query = "DELETE FROM LanguagesSchool.orders_courses " +
                    "WHERE (id_course = ?) and (username_student = ?);";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, course.getId());
            preparedStatement.setString(2, username);

            int rows = preparedStatement.executeUpdate();

            if (rows < 1) {
                throw new RuntimeException("Error while adding order");
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closePreparedStatement();
        }
    }
}
