package com.company.model;

import com.company.model.abstraction.Model;

import java.util.Date;

public class Course implements Model {

    private int id;
    private String nameOfCourse;
    private String language;
    private String level;
    private String teacher;
    private double cost;
    private Date startDate;
    private short duration;
    private String info;

    @Override
    public String getModelName() {
        return "Course";
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", nameOfCourse='" + nameOfCourse + '\'' +
                ", language='" + language + '\'' +
                ", level='" + level + '\'' +
                ", teacher='" + teacher + '\'' +
                ", cost=" + cost +
                ", startDate=" + startDate +
                ", duration=" + duration +
                ", info='" + info + '\'' +
                '}';
    }

    public Course() {
    }

    public Course(String nameOfCourse, String language, String level, String teacher, double cost, Date startDate, short duration) {
        this.nameOfCourse = nameOfCourse;
        this.language = language;
        this.level = level;
        this.teacher = teacher;
        this.cost = cost;
        this.startDate = startDate;
        this.duration = duration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameOfCourse() {
        return nameOfCourse;
    }

    public void setNameOfCourse(String nameOfCourse) {
        this.nameOfCourse = nameOfCourse;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public short getDuration() {
        return duration;
    }

    public void setDuration(short duration) {
        this.duration = duration;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
