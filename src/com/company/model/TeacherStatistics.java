package com.company.model;

import com.company.model.abstraction.Model;

public class TeacherStatistics implements Model {
    private String teacherUsername;
    private String nameOfCourse;
    private int studentsCount;

    public TeacherStatistics() {
    }

    public TeacherStatistics(String teacherUsername) {
        this.teacherUsername = teacherUsername;
    }

    public String getTeacherUsername() {
        return teacherUsername;
    }

    public void setTeacherUsername(String teacherUsername) {
        this.teacherUsername = teacherUsername;
    }

    public String getNameOfCourse() {
        return nameOfCourse;
    }

    public void setNameOfCourse(String nameOfCourse) {
        this.nameOfCourse = nameOfCourse;
    }

    public int getStudentsCount() {
        return studentsCount;
    }

    public void setStudentsCount(int studentsCount) {
        this.studentsCount = studentsCount;
    }

    @Override
    public String getModelName() {
        return "TeacherStatistics";
    }
}
