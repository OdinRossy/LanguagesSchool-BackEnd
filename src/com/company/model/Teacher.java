package com.company.model;

import com.company.model.abstraction.User;

import java.util.Date;

public class Teacher extends User {

    //    private int id;
//    private String firstName;
//    private String middleName;
//    private String lastName;
//    private Date birthdate;
//    private String username;
//    private String password;
//    private boolean isMale;
    private double salary;
    private String info;
    private String language;

    @Override
    public String getModelName() {
        return "Teacher";
    }

    @Override
    public String toString() {
        return "Teacher{" + super.toString() +
                "salary=" + salary +
                ", info='" + info + '\'' +
                ", language='" + language + '\'' +
                '}';
    }

    public Teacher() {
    }

    public Teacher(String username, String password) {
        super(username, password);
    }

    public Teacher(String firstName, String middleName, String lastName, String username, String password, Date bitrhdate, boolean isMale, double salary, String info, String language) {
        super(firstName, middleName, lastName, username, password, bitrhdate, isMale);
        this.salary = salary;
        this.info = info;
        this.language = language;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}