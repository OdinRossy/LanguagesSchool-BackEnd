package com.company.model.abstraction;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class User implements Model {
    private int id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String username;
    private String password;
    private Date birthdate;
    private boolean isMale;
    private String additionInfo;

    public User() {
    }

    public User (String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String firstName, String middleName, String lastName, String username, String password, Date bitrhdate, boolean isMale) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.birthdate = bitrhdate;
        this.isMale = isMale;
    }

    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", birthdate=" + birthdate +
                ", isMale=" + isMale +
                '}';
    }

    public String getAdditionInfo() {
        return additionInfo;
    }

    public void setAdditionInfo(String additionInfo) {
        this.additionInfo = additionInfo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Object getBirthdate(String returnType) {
        switch (returnType.toLowerCase()) {
            case "date" : {
                return birthdate;
            }
            case "string" : {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                return simpleDateFormat.format(birthdate);
            }
            default: return null;
        }
    }

    public void setBirthdate(String birthdate){
        try {
            this.birthdate = new SimpleDateFormat("yyyy-MM-dd").parse(birthdate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public boolean isMale() {
        return isMale;
    }

    public void setMale(boolean male) {
        isMale = male;
    }
}
