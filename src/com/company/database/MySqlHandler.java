package com.company.database;

import com.company.database.configuration.DbConfiguration;
import com.company.database.configuration.DbQueries;
import com.company.database.configuration.DbTables;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MySqlHandler implements DbConfiguration {

    protected static PreparedStatement preparedStatement;

    public static boolean createTable(String tableName) {

        if (tableName.equals(DbTables.TABLE_LANGUAGES)) {
           return executeCustomQuery(DbQueries.CREATE_TABLE_LANGUAGES);
        }
        if (tableName.equals(DbTables.TABLE_LEVELS_OF_LANGUAGE)) {
            return executeCustomQuery(DbQueries.CREATE_TABLE_LEVELS_OF_LANGUAGE);
        }
        if (tableName.equals(DbTables.TABLE_NAMES_OF_COURSES)) {
            return executeCustomQuery(DbQueries.CREATE_TABLE_NAMES_OF_COURSES);
        }
        if (tableName.equals(DbTables.TABLE_STUDENTS)) {
            return executeCustomQuery(DbQueries.CREATE_TABLE_STUDENTS);
        }
        if (tableName.equals(DbTables.TABLE_TEACHERS)) {
            return executeCustomQuery(DbQueries.CREATE_TABLE_TEACHERS);
        }
        if (tableName.equals(DbTables.TABLE_COURSES)) {
            return executeCustomQuery(DbQueries.CREATE_TABLE_COURSES);
        }
        if (tableName.equals(DbTables.TABLE_ORDER_COURSES)) {
            return executeCustomQuery(DbQueries.CREATE_TABLE_ORDER_COURSES);
        }
        if (tableName.equals(DbTables.TABLE_HELP_STUDENTS)) {
            return executeCustomQuery(DbQueries.CREATE_TABLE_HELP_STUDENTS);
        }
        return false;
    }

    public static boolean importData(String tableName) {

        if (tableName.equals(DbTables.TABLE_LANGUAGES)) {
            return executeCustomQuery(DbQueries.INSERT_LANGUAGES);
        }
        if (tableName.equals(DbTables.TABLE_LEVELS_OF_LANGUAGE)) {
            return executeCustomQuery(DbQueries.INSERT_LEVELS_OF_LANGUAGE);
        }
        if (tableName.equals(DbTables.TABLE_NAMES_OF_COURSES)) {
            return executeCustomQuery(DbQueries.INSERT_NAMES_OF_COURSES);
        }
        if (tableName.equals(DbTables.TABLE_STUDENTS)) {
            return executeCustomQuery(DbQueries.INSERT_STUDENTS);
        }
        if (tableName.equals(DbTables.TABLE_TEACHERS)) {
            return executeCustomQuery(DbQueries.INSERT_TEACHERS);
        }
        if (tableName.equals(DbTables.TABLE_COURSES)) {
            return executeCustomQuery(DbQueries.INSERT_COURSES);
        }
        if (tableName.equals(DbTables.TABLE_ORDER_COURSES)) {
            return executeCustomQuery(DbQueries.INSERT_ORDER_COURSES);
        }
        return false;
    }

    private static boolean executeCustomQuery(String query) {
        try (Connection connection = getDBConnection()) {
            preparedStatement = connection.prepareStatement(query);
            return preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closePreparedStatement();
        }
    }

    protected static void closePreparedStatement(){
        try {
            if (!preparedStatement.isClosed()){
                preparedStatement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected static Connection getDBConnection() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return DriverManager.getConnection(DB_URL + "/" + DB_SCHEMA + "?" + DB_PARAMETERS, DB_USER, DB_PASSWORD);
    }
}

