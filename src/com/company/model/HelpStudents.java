package com.company.model;

import java.util.Date;

public class HelpStudents {
    private int id;
    private String studentUsername;
    private String message;
    private Date requestDateTime;

    @Override
    public String toString() {
        return "HelpStudents{" +
                "id=" + id +
                ", studentUsername='" + studentUsername + '\'' +
                ", message='" + message + '\'' +
                ", requestDateTime=" + requestDateTime +
                '}';
    }

    public HelpStudents() {
    }

    public HelpStudents(String studentUsername, String message) {
        this.studentUsername = studentUsername;
        this.message = message;
    }

    public HelpStudents(int id, String studentUsername, String message, Date requestDateTime) {
        this.id = id;
        this.studentUsername = studentUsername;
        this.message = message;
        this.requestDateTime = requestDateTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentUsername() {
        return studentUsername;
    }

    public void setStudentUsername(String studentUsername) {
        this.studentUsername = studentUsername;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getRequestDateTime() {
        return requestDateTime;
    }

    public void setRequestDateTime(Date requestDateTime) {
        this.requestDateTime = requestDateTime;
    }
}
