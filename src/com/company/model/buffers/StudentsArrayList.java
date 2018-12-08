package com.company.model.buffers;

import com.company.model.Student;
import com.company.model.abstraction.Model;

import java.util.ArrayList;
import java.util.List;

public class StudentsArrayList implements Model {

    @Override
    public String getModelName() {
        return "HelpStudents";
    }

    @Override
    public String toString() {
        String string = "";
        for (Student student: studentArrayList) {
            string += student.toString() + "\n";
        }
        return string;
    }

    private ArrayList<Student> studentArrayList;

    public StudentsArrayList(List<Student> studentArrayList) {
        this.studentArrayList = (ArrayList<Student>) studentArrayList;
    }

    public ArrayList<Student> getStudentArrayList() {
        return studentArrayList;
    }

    public void setStudentArrayList(ArrayList<Student> studentArrayList) {
        this.studentArrayList = studentArrayList;
    }
}
