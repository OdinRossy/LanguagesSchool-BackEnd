package com.company.database;

import com.company.model.Course;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlCoursesHandler extends MySqlHandler {

    public static List<Course> selectAllCourses() {
        List<Course> courses = new ArrayList<>();
        ResultSet resultSet = null;
        try (Connection connection = getDBConnection()) {
            final String query = "select " +
                    "courses.id, " +
                    "courses.cost, " +
                    "courses.start_date, " +
                    "courses.duration, " +
                    "names_of_courses.name, " +
                    "languages.name, " +
                    "levels_of_language.level, " +
                    "users.last_name, " +
                    "users.first_name, " +
                    "users.middle_name " +
                    "from courses " +
                    "inner join names_of_courses on courses.id_name_of_course = names_of_courses.id " +
                    "inner join languages on courses.id_language = languages.id\n" +
                    "inner join levels_of_language on courses.id_level = levels_of_language.id " +
                    "inner join users on courses.username_teacher = users.login " +
                    "inner join teachers on users.login = teachers.username;";
            MySqlHandler.preparedStatement = connection.prepareStatement(query);
            resultSet = MySqlHandler.preparedStatement.executeQuery();
            return getCourses(courses, resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                closePreparedStatement();
            }
        }
    }

    public static List<Course> selectAllCoursesByStudentUsername(String username) {
        List<Course> courses = new ArrayList<>();
        ResultSet resultSet = null;
        try (Connection connection = getDBConnection()) {
            final String query = "select " +
                    "courses.id, " +
                    "courses.cost, " +
                    "courses.start_date, " +
                    "courses.duration, " +
                    "names_of_courses.name, " +
                    "languages.name, " +
                    "levels_of_language.level, " +
                    "users.last_name, " +
                    "users.first_name, " +
                    "users.middle_name " +
                    "from courses " +
                    "inner join names_of_courses on courses.id_name_of_course = names_of_courses.id " +
                    "inner join languages on courses.id_language = languages.id\n" +
                    "inner join levels_of_language on courses.id_level = levels_of_language.id " +
                    "inner join users on courses.username_teacher = users.login " +
                    "inner join teachers on users.login = teachers.username " +
                    "inner join orders_courses on courses.id = orders_courses.id_course " +
                    "inner join students on orders_courses.username_student = students.username " +
                    "where username_student = ?;";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);

            resultSet = preparedStatement.executeQuery();
            return getCourses(courses, resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                closePreparedStatement();
            }
        }
    }

    public static List<Course> selectAllCoursesByTeacherUsername(String username) {
        List<Course> courses = new ArrayList<>();
        ResultSet resultSet = null;
        try (Connection connection = getDBConnection()) {
            final String query = "select " +
                    "courses.id, " +
                    "courses.cost, " +
                    "courses.start_date, " +
                    "courses.duration, " +
                    "names_of_courses.name, " +
                    "languages.name, " +
                    "levels_of_language.level, " +
                    "users.last_name, " +
                    "users.first_name, " +
                    "users.middle_name " +
                    "from courses\n" +
                    "\tinner join names_of_courses on courses.id_name_of_course = names_of_courses.id\n" +
                    "    inner join languages on courses.id_language = languages.id\n" +
                    "    inner join levels_of_language on courses.id_level = levels_of_language.id\n" +
                    "    inner join users on courses.username_teacher = users.login\n" +
                    "    inner join teachers on users.login = teachers.username\n" +
                    "\t\twhere teachers.username = ?;";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);

            resultSet = preparedStatement.executeQuery();
            return getCourses(courses, resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                closePreparedStatement();
            }
        }
    }

    //    Generated by Idea inspector

    private static List<Course> getCourses(List<Course> courses, ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            Course course = new Course();
            course.setNameOfCourse(resultSet.getString(5));
            course.setLanguage(resultSet.getString(6));
            course.setLevel(resultSet.getString(7));
            course.setStartDate(resultSet.getDate(3));
            course.setDuration(resultSet.getShort(4));
            course.setTeacher(resultSet.getString(8) + " " +
                    resultSet.getString(9).charAt(0) + ". " +
                    resultSet.getString(10).charAt(0) + ".");
            course.setId(resultSet.getInt(1));
            course.setCost(resultSet.getDouble(2));
            courses.add(course);
        }
        return courses;
    }
}
