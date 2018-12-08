package com.company.transport.serverManager;

import com.company.transport.configuration.ServerConfiguration;
import com.company.transport.serverThread.ServerThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerManager {

    private int port;
    private static ServerManager instance;
    private static int currentClients = 0;

    public void launchServer() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.err.println("Server works on: " + serverSocket.getInetAddress() + ":" + serverSocket.getLocalPort());
            System.out.println("You can configure server parameters in " + ServerConfiguration.PATH_TO_PROPERTY_FILE);
            while (true) {
                Socket socket = serverSocket.accept();
                try {
                    System.out.println("New connection: " + socket.getInetAddress() + ":" + socket.getPort());
                    System.out.println("Count of current clients: " + (Thread.activeCount() - 1));
                    new ServerThread(socket);
                } catch (IOException e) {
                    socket.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static synchronized ServerManager getInstance(int serverPort) {
        if (instance == null){
            instance = new ServerManager(serverPort);
        }
        return instance;
    }
    private ServerManager(int serverPort) {
        this.port = serverPort;
    }
}
