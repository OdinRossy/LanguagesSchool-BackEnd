package com.company.database.configuration;

import com.company.utils.propertyLoader.PropertyLoader;

public interface DbConfiguration {

    String PATH_TO_PROPERTY_FILE = "src/resources/DataBaseConfiguration.properties";
    PropertyLoader propertyLoader = new PropertyLoader(PATH_TO_PROPERTY_FILE);

    String DB_URL = propertyLoader.loadProperty("db.url");
    String DB_PARAMETERS = propertyLoader.loadProperty("db.parameters");
    String DB_USER = propertyLoader.loadProperty("db.user");
    String DB_PASSWORD = propertyLoader.loadProperty("db.password");
    String DB_SCHEMA = propertyLoader.loadProperty("db.schema.name");
}
