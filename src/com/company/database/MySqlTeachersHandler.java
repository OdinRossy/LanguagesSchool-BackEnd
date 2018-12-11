package com.company.database;

import com.company.database.configuration.DbTables;
import com.company.model.Language;
import com.company.model.Teacher;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MySqlTeachersHandler extends MySqlHandler {

    public static boolean updateTeacher(Teacher teacher) {
        try (Connection connection = getDBConnection()) {
            final String query = "UPDATE " + DbTables.TABLE_TEACHERS + " SET " +
                    "LanguagesSchool.teachers.first_name = ?, " +
                    "LanguagesSchool.teachers.middle_name = ?, " +
                    "LanguagesSchool.teachers.last_name = ?, " +
                    "LanguagesSchool.teachers.password = ?, " +
                    "LanguagesSchool.teachers.birthdate = ?, " +
                    "LanguagesSchool.teachers.gender = ?, " +
                    "LanguagesSchool.teachers.salary = ?, " +
                    "LanguagesSchool.teachers.bio = ? " +
                    "WHERE (LanguagesSchool.teachers.username=?);";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, teacher.getFirstName());
            preparedStatement.setString(2, teacher.getMiddleName());
            preparedStatement.setString(3, teacher.getLastName());
            preparedStatement.setString(4, teacher.getPassword());
            Calendar cal = Calendar.getInstance();
            cal.setTime(teacher.getBirthdate());
            String formatedDate = cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DATE);
            preparedStatement.setString(5, formatedDate);
            preparedStatement.setString(6, (teacher.isMale() ? "Male" : "Female"));
            preparedStatement.setDouble(7, teacher.getSalary());
            preparedStatement.setString(8, teacher.getInfo());
            preparedStatement.setString(9, teacher.getUsername());

            int rows = preparedStatement.executeUpdate();

            if (rows == 1) {
                return true;
            } else {
                throw new RuntimeException("Teacher " + teacher.toString() + "is not updated");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closePreparedStatement();
        }
    }

    public static Teacher selectTeacherByUsernameAndPassword(Teacher teacher) {
        ResultSet resultSet = null;
        try (Connection connection = getDBConnection()) {

            final String query = "SELECT LanguagesSchool.teachers.id,\n" +
                    "                LanguagesSchool.teachers.first_name,\n" +
                    "                LanguagesSchool.teachers.last_name,\n" +
                    "                LanguagesSchool.teachers.middle_name,\n" +
                    "                LanguagesSchool.teachers.username,\n" +
                    "                LanguagesSchool.teachers.password,\n" +
                    "                LanguagesSchool.teachers.birthdate, \n" +
                    "                LanguagesSchool.teachers.gender,\n" +
                    "                LanguagesSchool.teachers.salary,\n" +
                    "                LanguagesSchool.teachers.bio,\n" +
                    "                LanguagesSchool.languages.name\n" +
                    "                FROM LanguagesSchool.teachers \n" +
                    "                INNER JOIN LanguagesSchool.languages " +
                    "on teachers.id_language = LanguagesSchool.languages.id\n" +
                    "                where teachers.username = ? and teachers.password = ?;";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, teacher.getUsername());
            preparedStatement.setString(2, teacher.getPassword());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                teacher.setId(resultSet.getInt("id"));
                teacher.setFirstName(resultSet.getString("first_name"));
                teacher.setMiddleName(resultSet.getString("middle_name"));
                teacher.setLastName(resultSet.getString("last_name"));
                teacher.setUsername(resultSet.getString("username"));
                teacher.setPassword(resultSet.getString("password"));
                teacher.setBirthdate(resultSet.getDate("birthdate"));
                teacher.setMale(resultSet.getString("gender").equalsIgnoreCase("male"));
                teacher.setSalary(resultSet.getDouble("salary"));
                teacher.setInfo(resultSet.getString("bio"));
                Language language = new Language();
                language.setName(resultSet.getString("name"));
                teacher.setLanguage(language);
            }
            return teacher;
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

    public static List<Teacher> selectAllTeachersByStudentUsername(String username) {
        List<Teacher> teachers = new ArrayList<>();
        ResultSet resultSet = null;
        try (Connection connection = getDBConnection()) {

            final String query = "select " +
                    "LanguagesSchool.teachers.first_name, " +
                    "LanguagesSchool.teachers.middle_name, " +
                    "LanguagesSchool.teachers.last_name," +
                    "LanguagesSchool.teachers.birthdate, " +
                    "LanguagesSchool.languages.name " +
                    "from LanguagesSchool.courses\n" +
                    "    inner join LanguagesSchool.teachers " +
                    "on LanguagesSchool.courses.id_teacher = LanguagesSchool.teachers.id\n" +
                    "    inner join LanguagesSchool.languages " +
                    "on courses.id_language = languages.id\n" +
                    "    inner join LanguagesSchool.orders_courses " +
                    "on courses.id = LanguagesSchool.orders_courses.id_course\n" +
                    "    where LanguagesSchool.orders_courses.username_student = ?;";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Teacher teacher = new Teacher();
                teacher.setFirstName(resultSet.getString(1));
                teacher.setMiddleName(resultSet.getString(2));
                teacher.setLastName(resultSet.getString(3));
                teacher.setBirthdate(resultSet.getDate(4));
                Language language = new Language();
                language.setName(resultSet.getString("name"));
                teacher.setLanguage(language);
                teachers.add(teacher);
            }
            return teachers;
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

    public static List<Teacher> selectAllTeachers() {
        List<Teacher> teachers = new ArrayList<>();
        ResultSet resultSet = null;
        try (Connection connection = getDBConnection()) {

            final String query = "select " +
                    "LanguagesSchool.teachers.id, " +
                    "LanguagesSchool.teachers.first_name, " +
                    "LanguagesSchool.teachers.middle_name, " +
                    "LanguagesSchool.teachers.last_name, " +
                    "LanguagesSchool.teachers.username, " +
                    "LanguagesSchool.teachers.password, " +
                    "LanguagesSchool.teachers.birthdate, " +
                    "LanguagesSchool.teachers.gender, " +
                    "LanguagesSchool.teachers.salary, " +
                    "LanguagesSchool.teachers.bio, " +
                    "LanguagesSchool.languages.name from LanguagesSchool.teachers\n" +
                    "    inner join LanguagesSchool.languages on LanguagesSchool.teachers.id_language = languages.id;";

            preparedStatement = connection.prepareStatement(query);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Teacher teacher = new Teacher();
                teacher.setId(1);
                teacher.setFirstName(resultSet.getString(2));
                teacher.setMiddleName(resultSet.getString(3));
                teacher.setLastName(resultSet.getString(4));
                teacher.setUsername(resultSet.getString(5));
                teacher.setPassword(resultSet.getString(6));
                teacher.setBirthdate(resultSet.getDate(7));
                teacher.setMale(resultSet.getString(8).equalsIgnoreCase("Male"));
                teacher.setSalary(resultSet.getDouble(9));
                teacher.setInfo(resultSet.getString(10));
                Language language = new Language();
                language.setName(resultSet.getString("name"));
                teacher.setLanguage(language);
                teachers.add(teacher);
            }
            return teachers;
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

    public static boolean deleteTeacher(Teacher teacher) {
        try (Connection connection = getDBConnection()) {

            final String query = "DELETE FROM LanguagesSchool.teachers WHERE (LanguagesSchool.teachers.username=?);";
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, teacher.getUsername());
            int rows = preparedStatement.executeUpdate();

            return rows > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            closePreparedStatement();
        }
    }

    public static boolean insertTeacher(Teacher teacher) {
        try (Connection connection = getDBConnection()) {
            final String query = "INSERT INTO `languagesschool`.`teachers` (" +
                    "`first_name`, " +
                    "`middle_name`, " +
                    "`last_name`, " +
                    "`username`, " +
                    "`password`, " +
                    "`birthdate`, " +
                    "`gender`, " +
                    "`salary`, " +
                    "`id_language`" +
                    ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
            preparedStatement = connection.prepareStatement(query);
            Calendar cal = Calendar.getInstance();
            cal.setTime(teacher.getBirthdate());
            String formatedDate = cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DATE);
            preparedStatement.setString(1, teacher.getFirstName());
            preparedStatement.setString(2, teacher.getMiddleName());
            preparedStatement.setString(3, teacher.getLastName());
            preparedStatement.setString(4, teacher.getUsername());
            preparedStatement.setString(5, teacher.getPassword());
            preparedStatement.setString(6, formatedDate);
            preparedStatement.setString(7, (teacher.isMale() ? "Male" : "Female"));
            preparedStatement.setDouble(8, teacher.getSalary());
            preparedStatement.setInt(9, teacher.getLanguage().getId());
            return preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closePreparedStatement();
        }
    }
}
