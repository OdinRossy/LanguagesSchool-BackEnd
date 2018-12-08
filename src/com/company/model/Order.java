package com.company.model;

import com.company.model.abstraction.Model;

public class Order implements Model {

    private Student student;
    private Course course;

    @Override
    public String getModelName() {
        return "Order";
    }

    @Override
    public String toString() {
        return "Order{" +
                "student=" + student +
                ", course=" + course +
                '}';
    }

    public Order(Student student, Course course) {
        this.student = student;
        this.course = course;
    }

    public Order() {
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
