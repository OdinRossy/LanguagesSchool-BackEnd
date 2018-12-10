package com.company.model.buffers;

import com.company.model.TeacherStatistics;
import com.company.model.abstraction.Model;

import java.util.ArrayList;
import java.util.List;

public class TeacherStatisticsArrayList implements Model {

    private ArrayList<TeacherStatistics> teacherStatisticsArrayList = new ArrayList<>();

    public TeacherStatisticsArrayList(List<TeacherStatistics> teacherStatisticsArrayList) {
        this.teacherStatisticsArrayList = (ArrayList<TeacherStatistics>) teacherStatisticsArrayList;
    }

    public ArrayList<TeacherStatistics> getTeacherStatisticsArrayList() {
        return teacherStatisticsArrayList;
    }

    public void setTeacherStatisticsArrayList(ArrayList<TeacherStatistics> teacherStatisticsArrayList) {
        this.teacherStatisticsArrayList = teacherStatisticsArrayList;
    }

    @Override
    public String getModelName() {
        return "TeacherStatisticsArrayList";
    }
}
