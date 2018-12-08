package com.company.model.buffers;

import com.company.model.HelpStudents;
import com.company.model.abstraction.Model;

import java.util.ArrayList;

public class HelpStudentsArrayList implements Model {

    @Override
    public String getModelName() {
        return "HelpStudents";
    }

    @Override
    public String toString() {
        String string = "";
        for (HelpStudents helpStudents : helpStudentsArrayList) {
            string += helpStudents.toString() + "\n";
        }
        return string;
    }

    private ArrayList<HelpStudents> helpStudentsArrayList;

    public HelpStudentsArrayList(ArrayList<HelpStudents> helpStudentsArrayList) {
        this.helpStudentsArrayList = helpStudentsArrayList;
    }

    public ArrayList<HelpStudents> getHelpStudentsArrayList() {
        return helpStudentsArrayList;
    }

    public void setHelpStudentsArrayList(ArrayList<HelpStudents> helpStudentsArrayList) {
        this.helpStudentsArrayList = helpStudentsArrayList;
    }
}
