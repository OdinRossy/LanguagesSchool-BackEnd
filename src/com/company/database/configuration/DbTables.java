package com.company.database.configuration;

public interface DbTables extends DbConfiguration {

    String TABLE_LANGUAGES = propertyLoader.loadProperty("db.table.languages");
    String TABLE_LEVELS_OF_LANGUAGE = propertyLoader.loadProperty("db.table.levels_of_language");
    String TABLE_NAMES_OF_COURSES = propertyLoader.loadProperty("db.table.names_of_courses");
    String TABLE_STUDENTS = propertyLoader.loadProperty("db.table.students");
    String TABLE_TEACHERS = propertyLoader.loadProperty("db.table.teachers");
    String TABLE_COURSES = propertyLoader.loadProperty("db.table.courses");
    String TABLE_ORDER_COURSES = propertyLoader.loadProperty("db.table.order_courses");
    String TABLE_HELP_STUDENTS = propertyLoader.loadProperty("db.table.help_students");

}
