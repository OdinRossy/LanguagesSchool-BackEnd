package com.company.database;

import com.company.model.Language;
import com.company.model.Teacher;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MySqlTeachersHandler extends MySqlHandler {

    public static boolean updateTeacher(Teacher teacher) {
        try (Connection connection = getDBConnection()) {
            final String query1 = "update users " +
                    "set " +
                    "users.first_name = ?, " +
                    "users.middle_name = ?, " +
                    "users.last_name = ?, " +
                    "users.password = ?, " +
                    "users.birthdate = ?, " +
                    "users.gender = ? " +
                    "where users.login = ?;";

            preparedStatement = connection.prepareStatement(query1);
            preparedStatement.setString(1, teacher.getFirstName());
            preparedStatement.setString(2, teacher.getMiddleName());
            preparedStatement.setString(3, teacher.getLastName());
            preparedStatement.setString(4, teacher.getPassword());
            Calendar cal = Calendar.getInstance();
            cal.setTime(teacher.getBirthdate());
            String formatedDate = cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DATE);
            preparedStatement.setString(5, formatedDate);
            preparedStatement.setString(6, (teacher.isMale() ? "Мужской" : "Женский"));
            preparedStatement.setString(7, teacher.getUsername());
            preparedStatement.execute();

            final String query2 = "update teachers set teachers.salary = ? " +
                    "where teachers.username = ?;";

            preparedStatement = connection.prepareStatement(query2);
            preparedStatement.setDouble(1, teacher.getSalary());
            preparedStatement.setString(2, teacher.getUsername());

            preparedStatement.execute();

            return true;
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

            final String query = "select " +
                    "users.last_name, " +
                    "users.first_name, " +
                    "users.middle_name, " +
                    "users.login, " +
                    "users.password, " +
                    "users.birthdate, " +
                    "users.gender, " +
                    "teachers.salary, " +
                    "teachers.bio, " +
                    "languages.name " +
                    "from users " +
                    "inner join teachers on users.login = teachers.username " +
                    "inner join languages on teachers.id_language = languages.id " +
                    "where teachers.username = ? and users.password = ?;";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, teacher.getUsername());
            preparedStatement.setString(2, teacher.getPassword());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                teacher.setFirstName(resultSet.getString("first_name"));
                teacher.setMiddleName(resultSet.getString("middle_name"));
                teacher.setLastName(resultSet.getString("last_name"));
                teacher.setUsername(resultSet.getString("login"));
                teacher.setPassword(resultSet.getString("password"));
                teacher.setBirthdate(resultSet.getDate("birthdate"));
                teacher.setMale(resultSet.getString("gender").equalsIgnoreCase("Мужской"));
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
                    "users.first_name, " +
                    "users.middle_name, " +
                    "users.last_name, " +
                    "users.birthdate, " +
                    "languages.name " +
                    "from courses " +
                    "inner join users on courses.username_teacher = users.login " +
                    "    inner join teachers on courses.username_teacher = teachers.username " +
                    "    inner join languages on courses.id_language = languages.id " +
                    "    inner join orders_courses on courses.id = orders_courses.id_course " +
                    " where orders_courses.username_student = ? ;";

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
                    "teachers.id, " +
                    "users.first_name, " +
                    "users.middle_name, " +
                    "users.last_name, " +
                    "users.login, " +
                    "users.password, " +
                    "users.birthdate, " +
                    "users.gender, " +
                    "teachers.salary, " +
                    "teachers.bio, " +
                    "languages.name " +
                    "from users\n" +
                    "\tinner join teachers on users.login = teachers.username\n" +
                    "    inner join languages on teachers.id_language = languages.id;\n";

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
                teacher.setMale(resultSet.getString(8).equalsIgnoreCase("Мужской"));
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
            final String query1 = "INSERT INTO " +
                    "users (" +
                    "first_name, " +
                    "middle_name, last_name, " +
                    "login, password, " +
                    "birthdate, " +
                    "gender) VALUES (?,?,?,?,?, ?, ?);";
            preparedStatement = connection.prepareStatement(query1);
            Calendar cal = Calendar.getInstance();
            cal.setTime(teacher.getBirthdate());
            String formatedDate = cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DATE);
            preparedStatement.setString(1, teacher.getFirstName());
            preparedStatement.setString(2, teacher.getMiddleName());
            preparedStatement.setString(3, teacher.getLastName());
            preparedStatement.setString(4, teacher.getUsername());
            preparedStatement.setString(5, teacher.getPassword());
            preparedStatement.setString(6, formatedDate);
            preparedStatement.setString(7, (teacher.isMale() ? "Мужской" : "Женский"));
            preparedStatement.execute();

            final String query2 = "INSERT INTO " +
                    "teachers (" +
                    "username, " +
                    "salary, " +
                    "id_language) VALUES (?, ?, ?);";
            preparedStatement = connection.prepareStatement(query2);
            preparedStatement.setString(1,teacher.getUsername());
            preparedStatement.setDouble(2, teacher.getSalary());
            preparedStatement.setInt(3, teacher.getLanguage().getId());
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closePreparedStatement();
        }
    }
}
