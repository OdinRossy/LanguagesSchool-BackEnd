package com.company.model;

import com.company.model.abstraction.Model;

public class HelpStudent implements Model{
    private Student student;
    private String text;

    @Override
    public String getModelName() {
        return "HelpStudent";
    }

    @Override
    public String toString() {
        return "HelpStudent{" +
                "student=" + student +
                ", text='" + text + '\'' +
                '}';
    }

    public HelpStudent() {
    }

    public HelpStudent(Student student, String text) {
        this.student = student;
        this.text = text;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
