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

//    public static List<HelpStudents> selectAllHelpsStudentsByStudentUsername(HelpStudents helpStudent) {
//        List<HelpStudents> helpStudents = new ArrayList<>();
//        ResultSet resultSet = null;
//        try (Connection connection = getDBConnection()) {
//
//            final String query = "SELECT * FROM LanguagesSchool.orders_courses WHERE student_username = ?";
//
//            preparedStatement = connection.prepareStatement(query);
//            preparedStatement.setString(1,helpStudent.getStudentUsername());
//
//            resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                helpStudent = new HelpStudents();
//                helpStudent.setId(resultSet.getInt("id"));
//                helpStudent.setStudentUsername(resultSet.getString("student_username"));
//                helpStudent.setMessage(resultSet.getString("message"));
//                helpStudent.setRequestDateTime(resultSet.getDate("request_date_time"));
//                helpStudents.add(helpStudent);
//            }
//            return helpStudents;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return null;
//        } finally {
//            try {
//                if (resultSet != null) {
//                    resultSet.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            } finally {
//                closePreparedStatement();
//            }
//        }
//    }

}
