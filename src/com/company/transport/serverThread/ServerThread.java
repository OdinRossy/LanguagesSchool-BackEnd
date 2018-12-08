package com.company.transport.serverThread;

import com.company.transport.messageBuilder.ResponseGenerator;
import com.company.transport.request.Request;
import com.company.transport.response.Response;

import java.io.*;
import java.net.Socket;

public class ServerThread extends Thread {
    private Socket clientSocket;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;
    private ResponseGenerator responseGenerator = ResponseGenerator.getInstance();

    public ServerThread(Socket socket) throws IOException {
        clientSocket = socket;
        OutputStream outputStream = socket.getOutputStream();
        InputStream inputStream = socket.getInputStream();
        objectOutputStream = new ObjectOutputStream(outputStream);
        objectInputStream = new ObjectInputStream(inputStream);
        start();
    }

    public void run() {
        String client = clientSocket.getInetAddress() + ":" + clientSocket.getPort();
        try {
            while (!clientSocket.isClosed()) {
                Request request = (Request) objectInputStream.readObject();
                System.out.println("Request from" + client + ": " + request.toString());
                Response response = responseGenerator.generateResponse(request);
                System.out.println("Response to " + client + ": " + response.toString());
                objectOutputStream.writeObject(response);
                clientSocket.close();
            }
        } catch (IOException e) {
            System.err.println("Error while reading and writing with client " + client);
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
                System.err.println("Connection with client " + client + " is closed.");
            } catch (IOException e) {
                System.err.println("Error while closing connection with " + client);
            }
        }
    }
}
