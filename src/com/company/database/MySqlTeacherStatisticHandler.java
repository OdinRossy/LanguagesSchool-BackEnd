package com.company.database;

import com.company.model.Course;
import com.company.model.TeacherStatistics;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MySqlTeacherStatisticHandler extends MySqlHandler {

    public static List<TeacherStatistics> selectStatisticByTeacherUsername(String username) {
        List<TeacherStatistics> statistics = new ArrayList<>();
        ResultSet resultSet = null;
        try (Connection connection = getDBConnection()) {

            final String query =
                    "select distinct names_of_courses.name, " +
                            "count(students.username) over (partition by names_of_courses.name) from orders_courses\n" +
                    "inner join courses on courses.id = orders_courses.id_course\n" +
                    "inner join teachers on teachers.id = courses.id_teacher\n" +
                    "inner join names_of_courses on courses.id_name_of_course = names_of_courses.id\n" +
                    "inner join students on orders_courses.username_student = students.username\n" +
                    "where teachers.username = ?;";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                TeacherStatistics teacherStatistics = new TeacherStatistics();
                teacherStatistics.setStudentsCount(resultSet.getInt("count(students.username) over (partition by names_of_courses.name)"));
                teacherStatistics.setNameOfCourse(resultSet.getString("name") + " (" +
                        teacherStatistics.getStudentsCount() + ")");

                statistics.add(teacherStatistics);
            }
            return statistics;
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
}
