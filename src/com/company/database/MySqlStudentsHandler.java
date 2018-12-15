package com.company.database;

import com.company.model.Student;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MySqlStudentsHandler extends MySqlHandler {

    public static int getCountByTeacherUsername(String username) {
        ResultSet resultSet = null;
        int count = 0;
        try (Connection connection = getDBConnection()) {
            final String query = "select " +
                    "count(distinct students.username) from orders_courses " +
                    "inner join courses " +
                    "on courses.id = orders_courses.id_course " +
                    "    inner join teachers " +
                    "on teachers.username = courses.username_teacher" +
                    "    inner join students " +
                    "on orders_courses.username_student = students.username" +
                    "\t\twhere teachers.username = ?;";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                count = resultSet.getInt("count(distinct students.username)");
            }

            return count;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
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

    public static List<Student> selectAllStudents() {
        ResultSet resultSet = null;
        try (Connection connection = getDBConnection()) {

            final String query = "select " +
                    "students.id, " +
                    "users.first_name, " +
                    "users.middle_name, " +
                    "users.last_name, " +
                    "users.login, " +
                    "users.password, " +
                    "users.birthdate, " +
                    "users.gender " +
                    "from users\n" +
                    "\tinner join students on users.login = students.username;\n";

            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            List<Student> studentList = new ArrayList<>();
            while (resultSet.next()) {
                Student student = new Student();
                student.setFirstName(resultSet.getString("first_name"));
                student.setMiddleName(resultSet.getString("middle_name"));
                student.setLastName(resultSet.getString("last_name"));
                student.setUsername(resultSet.getString("username"));
                student.setPassword(resultSet.getString("password"));
                student.setBirthdate(resultSet.getString("birthdate"));
                studentList.add(student);
            }
            return studentList;
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

    public static List<Student> selectAllStudentsByTeacherUsername(String username) {
        ResultSet resultSet = null;
        try (Connection connection = getDBConnection()) {

            final String query = "select " +
                    "users.first_name, " +
                    "users.middle_name, " +
                    "users.last_name, " +
                    "users.birthdate, " +
                    "names_of_courses.name " +
                    "from orders_courses\n" +
                    "\tinner join students on orders_courses.username_student = students.username\n" +
                    "    inner join users on students.username = users.login\n" +
                    "    inner join courses on orders_courses.id_course = courses.id\n" +
                    "    inner join teachers on courses.username_teacher = teachers.username\n" +
                    "    inner join names_of_courses on courses.id_name_of_course = names_of_courses.id\n" +
                    "\t\twhere teachers.username = ?;";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            List<Student> studentList = new ArrayList<>();
            while (resultSet.next()) {
                Student student = new Student();
                student.setFirstName(resultSet.getString("first_name"));
                student.setMiddleName(resultSet.getString("middle_name"));
                student.setLastName(resultSet.getString("last_name"));
                student.setBirthdate(resultSet.getDate("birthdate"));
                student.setAdditionInfo(resultSet.getString("name"));
                studentList.add(student);
            }
            return studentList;
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

    public static Student selectStudentByUsernameAndPassword(Student student) {
        ResultSet resultSet = null;
        try (Connection connection = getDBConnection()) {
            final String query = "select " +
                    "users.first_name, " +
                    "users.middle_name," +
                    "users.last_name, " +
                    "users.login, " +
                    "users.password, " +
                    "users.birthdate, " +
                    "users.gender " +
                    "from users " +
                    "inner join students on users.login = students.username " +
                    " where students.username = ? and users.password = ?;\n";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, student.getUsername());
            preparedStatement.setString(2, student.getPassword());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                student.setFirstName(resultSet.getString("first_name"));
                student.setMiddleName(resultSet.getString("middle_name"));
                student.setLastName(resultSet.getString("last_name"));
                student.setUsername(resultSet.getString("login"));
                student.setPassword(resultSet.getString("password"));
                student.setBirthdate(resultSet.getDate("birthdate"));
                student.setMale((resultSet.getString("gender").equalsIgnoreCase("Мужской")));
            }
            return student;
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

    public static boolean updateStudent(Student student) {
        try (Connection connection = getDBConnection()) {
            final String query = "update users " +
                    "set " +
                    "users.first_name = ?, " +
                    "users.middle_name = ?, " +
                    "users.last_name = ?, " +
                    "users.password = ?, " +
                    "users.birthdate = ?, " +
                    "users.gender = ?\n" +
                    "\twhere users.login = ?;\n";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, student.getFirstName());
            preparedStatement.setString(2, student.getMiddleName());
            preparedStatement.setString(3, student.getLastName());
            preparedStatement.setString(4, student.getPassword());
            Calendar cal = Calendar.getInstance();
            cal.setTime(student.getBirthdate());
            String formatedDate = cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DATE);
            preparedStatement.setString(5, formatedDate);
            preparedStatement.setString(6, (student.isMale() ? "Мужской" : "Женский"));
            preparedStatement.setString(7, student.getUsername());

            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closePreparedStatement();
        }
    }

    public static boolean deleteStudent(Student student) {
        try (Connection connection = getDBConnection()) {

            final String query = "DELETE FROM LanguagesSchool.students WHERE (LanguagesSchool.students.username=?);";
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, student.getUsername());
            int rows = preparedStatement.executeUpdate();

            return rows > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            closePreparedStatement();
        }
    }

    public static Student insertStudent(Student student) {
        try (Connection connection = getDBConnection()) {
            final String query1 = "INSERT INTO users (" +
                    "first_name, " +
                    "middle_name, " +
                    "last_name, " +
                    "login, " +
                    "password, " +
                    "birthdate, " +
                    "gender) VALUES (?,?,?,?,?,?,?);";
            preparedStatement = connection.prepareStatement(query1);
            preparedStatement.setString(1, student.getFirstName());
            preparedStatement.setString(2, student.getMiddleName());
            preparedStatement.setString(3, student.getLastName());
            preparedStatement.setString(4, student.getUsername());
            preparedStatement.setString(5, student.getPassword());
            Calendar cal = Calendar.getInstance();
            cal.setTime(student.getBirthdate());
            String formatedDate = cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DATE);
            preparedStatement.setString(6, formatedDate);
            preparedStatement.setString(7, (student.isMale() ? "Мужской" : "Женский"));

            preparedStatement.execute();

            final String query2 = "INSERT INTO students (username) VALUES (?);";

            preparedStatement = connection.prepareStatement(query2);
            preparedStatement.setString(1, student.getUsername());
            preparedStatement.execute();

            return selectStudentByUsernameAndPassword(student);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            closePreparedStatement();
        }
    }
}
