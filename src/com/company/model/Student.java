package com.company.model;

import com.company.model.abstraction.User;
import java.util.Date;

public class Student extends User {

    @Override
    public String getModelName() {
        return "Student";
    }

    public Student() {
    }

    public Student(String username, String password) {
        super(username, password);
    }

    public Student(String firstName, String middleName, String lastName, String username, String password, Date bitrhdate, boolean isMale) {
        super(firstName, middleName, lastName, username, password, bitrhdate, isMale);
    }
}