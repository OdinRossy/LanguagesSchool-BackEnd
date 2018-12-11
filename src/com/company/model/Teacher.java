package com.company.model;

import com.company.model.abstraction.User;

import java.util.Date;

public class Teacher extends User {

    private double salary;
    private String info;
    private Language language;

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

    public Teacher(String firstName, String middleName, String lastName, String username, String password, Date bitrhdate, boolean isMale, double salary, String info, Language language) {
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

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public String getLanguageName() {
        return language.getName();
    }
}