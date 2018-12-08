package com.company.database;

import com.company.model.Course;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlCoursesHandler extends MySqlHandler {

//    public static boolean insertCourse(Course course) {
//        try (Connection connection = getDBConnection()) {
//
//            final String query = "INSERT INTO LanguagesSchool.courses (id_name_of_course, id_language, id_level, id_teacher, cost, start_date, duration, description) VALUES (?,?,?,?,?,?,?,?)";
//            MySqlHandler.preparedStatement = connection.prepareStatement(query);
//
//            preparedStatement.setString(course.get);
//            preparedStatement.setString();
//            preparedStatement.setString();
//            preparedStatement.setString(4,student.getUsername());
//            preparedStatement.setString(5,student.getPassword());
//            preparedStatement.setString(6, (String) student.getBirthdate("String"));
//            preparedStatement.setString(7,(student.isMale()?"Male":"Female"));
//
//            return getStudent(student);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        } finally {
//            closePreparedStatement();
//        }
//    }

    public static List<Course> selectAllCourses() {
        List<Course> courses = new ArrayList<>();
        ResultSet resultSet = null;
        try (Connection connection = getDBConnection()) {
            final String query = "SELECT " +
                    "LanguagesSchool.courses.id, " +
                    "LanguagesSchool.courses.cost, " +
                    "LanguagesSchool.courses.start_date, " +
                    "LanguagesSchool.courses.duration, " +
                    "LanguagesSchool.names_of_courses.name, " +
                    "LanguagesSchool.languages.name, " +
                    "LanguagesSchool.levels_of_language.level, " +
                    "LanguagesSchool.teachers.last_name, " +
                    "LanguagesSchool.teachers.first_name, " +
                    "LanguagesSchool.teachers.middle_name " +
                    "FROM LanguagesSchool.courses\n" +
                    "\tINNER JOIN LanguagesSchool.names_of_courses " +
                    "ON courses.id_name_of_course = LanguagesSchool.names_of_courses.id\n" +
                    "    INNER JOIN LanguagesSchool.languages " +
                    "ON courses.id_language = languages.id\n" +
                    "    INNER JOIN LanguagesSchool.levels_of_language " +
                    "ON courses.id_level = levels_of_language.id\n" +
                    "    INNER JOIN LanguagesSchool.teachers ON courses.id_teacher = teachers.id;";
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

            final String query = "SELECT " +
                    "LanguagesSchool.courses.id, " +
                    "LanguagesSchool.courses.cost, " +
                    "LanguagesSchool.courses.start_date, " +
                    "LanguagesSchool.courses.duration, " +
                    "LanguagesSchool.names_of_courses.name, " +
                    "LanguagesSchool.languages.name, " +
                    "LanguagesSchool.levels_of_language.level, " +
                    "LanguagesSchool.teachers.last_name, " +
                    "LanguagesSchool.teachers.first_name, " +
                    "LanguagesSchool.teachers.middle_name FROM " +
                    "LanguagesSchool.orders_courses\n" +
                    "\tINNER JOIN LanguagesSchool.students " +
                    "ON students.username = orders_courses.username_student\n" +
                    "   \tINNER JOIN LanguagesSchool.courses " +
                    "ON courses.id = orders_courses.id_course\n" +
                    "    \tINNER JOIN LanguagesSchool.names_of_courses " +
                    "ON courses.id_name_of_course = LanguagesSchool.names_of_courses.id\n" +
                    "    \tINNER JOIN LanguagesSchool.languages " +
                    "ON courses.id_language = LanguagesSchool.languages.id\n" +
                    "    \tINNER JOIN LanguagesSchool.levels_of_language " +
                    "ON courses.id_level = LanguagesSchool.levels_of_language.id\n" +
                    "    \tINNER JOIN LanguagesSchool.teachers " +
                    "ON courses.id_teacher = LanguagesSchool.teachers.id\n" +
                    "    \tWHERE username_student = ?;";

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

            final String query = "SELECT " +
                    "                    LanguagesSchool.courses.id, " +
                    "                    LanguagesSchool.courses.cost, " +
                    "                    LanguagesSchool.courses.start_date, " +
                    "                    LanguagesSchool.courses.duration, " +
                    "                    LanguagesSchool.names_of_courses.name, " +
                    "                    LanguagesSchool.languages.name, " +
                    "                    LanguagesSchool.levels_of_language.level, " +
                    "                    LanguagesSchool.teachers.last_name, " +
                    "                    LanguagesSchool.teachers.first_name, " +
                    "                    LanguagesSchool.teachers.middle_name FROM LanguagesSchool.courses" +
                    "                    INNER JOIN LanguagesSchool.names_of_courses\n" +
                    "                    ON courses.id_name_of_course = LanguagesSchool.names_of_courses.id" +
                    "                    INNER JOIN LanguagesSchool.languages" +
                    "                    ON courses.id_language = LanguagesSchool.languages.id" +
                    "                    INNER JOIN LanguagesSchool.levels_of_language" +
                    "                    ON courses.id_level = LanguagesSchool.levels_of_language.id" +
                    "                    INNER JOIN LanguagesSchool.teachers " +
                    "                    ON courses.id_teacher = LanguagesSchool.teachers.iD" +
                    "                    WHERE teachers.username = ?;";

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
