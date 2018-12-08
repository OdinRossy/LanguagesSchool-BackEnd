package com.company.transport.testClients;

import com.company.model.Student;
import com.company.transport.request.Request;
import com.company.transport.response.Response;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class TestClient2 {
    public static void main(String[] args) {
        testServer();
    }

    static void testServer() {
        try (Scanner scanner = new Scanner(System.in);
             Socket socket = new Socket("localhost",9000);
             ObjectOutputStream dataOutputStream = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream dataInputStream = new ObjectInputStream(socket.getInputStream())) {

            Student student = new Student("gleebka@icloud.com", "qwerty");

            Request request = new Request(Request.GET,student,"Student");
            dataOutputStream.writeObject(request);
            System.out.println(request);
            Response response = (Response) dataInputStream.readObject();
            System.out.println(response);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

