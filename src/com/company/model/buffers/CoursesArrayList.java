package com.company.model.buffers;

import com.company.model.Course;
import com.company.model.abstraction.Model;

import java.util.ArrayList;
import java.util.List;

public class CoursesArrayList implements Model {

    private ArrayList<Course> courseArrayList;

    @Override
    public String getModelName() {
        return "CoursesArrayList";
    }

    @Override
    public String toString() {
        String string = "";
        for (Course course: courseArrayList) {
            string += course.toString() + "\n";
        }
        return string;
    }

    public CoursesArrayList(List<Course> courseArrayList) {
        this.courseArrayList = (ArrayList<Course>) courseArrayList;
    }

    public ArrayList<Course> getCourseArrayList() {
        return courseArrayList;
    }

    public void setCourseArrayList(ArrayList<Course> courseArrayList) {
        this.courseArrayList = courseArrayList;
    }
}