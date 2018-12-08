package com.company.model.buffers;

import com.company.model.Teacher;
import com.company.model.abstraction.Model;

import java.util.ArrayList;
import java.util.List;

public class TeachersArrayList implements Model {

    private ArrayList<Teacher> teacherArrayList;

    @Override
    public String getModelName() {
        return "TeacherArrayList";
    }

    @Override
    public String toString() {
        String string = "";
        for (Teacher teacher: teacherArrayList) {
            string += teacher.toString() + "\n";
        }
        return string;
    }

    public TeachersArrayList(List<Teacher> teacherArrayList) {
        this.teacherArrayList = (ArrayList<Teacher>) teacherArrayList;
    }

    public ArrayList<Teacher> getTeacherArrayList() {
        return teacherArrayList;
    }

    public void setTeacherArrayList(ArrayList<Teacher> teacherArrayList) {
        this.teacherArrayList = teacherArrayList;
    }
}