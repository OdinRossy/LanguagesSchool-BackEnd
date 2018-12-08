package com.company;

import com.company.database.MySqlHandler;
import com.company.database.configuration.DbConfiguration;
import com.company.database.configuration.DbQueries;
import com.company.database.configuration.DbTables;
import com.company.transport.configuration.ServerConfiguration;
import com.company.transport.serverManager.ServerManager;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        try {
            initializeDatabase();
            initialiseServer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void initializeDatabase() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Database schema name: " + DbConfiguration.DB_SCHEMA);
        System.out.println("Database url: " + DbConfiguration.DB_URL);
        System.out.println("Database user: " + DbConfiguration.DB_USER);
        System.out.println("Database password: " + DbConfiguration.DB_PASSWORD);
        System.out.println("You can configure database parameters in " + DbConfiguration.PATH_TO_PROPERTY_FILE);
        System.err.println("Do you need to initialize database tables? [Y/N]");
        String answer = scanner.nextLine().toLowerCase();

        switch (answer) {
            case "y" : {
                System.out.println("Create table " + DbTables.TABLE_LANGUAGES + ": "
                        + MySqlHandler.createTable(DbTables.TABLE_LANGUAGES));

                System.out.println("Create table " + DbTables.TABLE_LEVELS_OF_LANGUAGE + ": "
                        + MySqlHandler.createTable(DbTables.TABLE_LEVELS_OF_LANGUAGE));

                System.out.println("Create table " + DbTables.TABLE_NAMES_OF_COURSES + ": "
                        + MySqlHandler.createTable(DbTables.TABLE_NAMES_OF_COURSES));

                System.out.println("Create table " + DbTables.TABLE_STUDENTS + ": "
                        + MySqlHandler.createTable(DbTables.TABLE_STUDENTS));

                System.out.println("Create table " + DbTables.TABLE_TEACHERS + ": "
                        + MySqlHandler.createTable(DbTables.TABLE_TEACHERS));

                System.out.println("Create table " + DbTables.TABLE_COURSES + ": "
                        + MySqlHandler.createTable(DbTables.TABLE_COURSES));

                System.out.println("Create table " + DbTables.TABLE_ORDER_COURSES + ": "
                        + MySqlHandler.createTable(DbTables.TABLE_ORDER_COURSES));

                System.out.println("Create table " + DbTables.TABLE_HELP_STUDENTS + ": "
                        + MySqlHandler.createTable(DbTables.TABLE_HELP_STUDENTS));

                System.out.println("======================================================================");
                System.out.println("Importing data..");
                System.out.println("Insert " + DbTables.TABLE_LANGUAGES + ": " +
                        MySqlHandler.importData(DbTables.TABLE_LANGUAGES));

                System.out.println("Insert " + DbTables.TABLE_LEVELS_OF_LANGUAGE + ": " +
                        MySqlHandler.importData(DbTables.TABLE_LEVELS_OF_LANGUAGE));

                System.out.println("Insert " + DbTables.TABLE_NAMES_OF_COURSES + ": " +
                        MySqlHandler.importData(DbTables.TABLE_NAMES_OF_COURSES));

                System.out.println("Insert " + DbTables.TABLE_STUDENTS + ": " +
                        MySqlHandler.importData(DbTables.TABLE_STUDENTS));

                System.out.println("Insert " + DbTables.TABLE_TEACHERS + ": " +
                        MySqlHandler.importData(DbTables.TABLE_TEACHERS));

                System.out.println("Insert " + DbTables.TABLE_COURSES + ": " +
                        MySqlHandler.importData(DbTables.TABLE_COURSES));

                System.out.println("Insert " + DbTables.TABLE_ORDER_COURSES + ": " +
                        MySqlHandler.importData(DbTables.TABLE_ORDER_COURSES));
                break;
            }
            case "n" :{
                break;
            }
            default: initializeDatabase();
        }
    }

    private static void initialiseServer() {
        ServerManager serverManager = ServerManager.getInstance(ServerConfiguration.TCP_SERVER_PORT);
        serverManager.launchServer();
    }
}
