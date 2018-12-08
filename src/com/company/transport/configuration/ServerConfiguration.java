package com.company.transport.configuration;

import com.company.utils.propertyLoader.PropertyLoader;

public interface ServerConfiguration {

    String PATH_TO_PROPERTY_FILE = "src/resources/ServerConfiguration.properties";
    PropertyLoader propertyLoader = new PropertyLoader(PATH_TO_PROPERTY_FILE);

//          Properties names:
    String PROPERTY_SERVER_PORT = "server.port";
    String PROPERTY_SERVER_ADDRESS = "server.address";

    //          Final fields for socket transport.configuration
    int TCP_SERVER_PORT = Integer.parseInt(propertyLoader.loadProperty(PROPERTY_SERVER_PORT));
    String TCP_SERVER_ADDRESS = propertyLoader.loadProperty(PROPERTY_SERVER_ADDRESS);

}
