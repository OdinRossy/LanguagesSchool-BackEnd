package com.company.logic.workers;

import com.company.database.MySqlTeachersHandler;
import com.company.model.Teacher;
import com.company.model.buffers.TeachersArrayList;
import com.company.transport.response.Response;

import java.util.List;

public class TeacherWorker {

    public static Response signIn(Teacher teacher) {
        teacher = MySqlTeachersHandler.selectTeacherByUsernameAndPassword(teacher);
//        System.out.println(teacher.toString());
        if (teacher.getFirstName() != null) {
            return new Response(true, teacher,"Success");
        } else {
            return new Response(false,null);
        }
    }

    public static Response editTeacher(Teacher teacher) {
        boolean isUpdated = MySqlTeachersHandler.updateTeacher(teacher);
        if (isUpdated) {
            teacher = MySqlTeachersHandler.selectTeacherByUsernameAndPassword(teacher);
            return new Response(true,teacher,"Success");
        } else {
            return new Response(false,null);
        }
    }

    public static Response deleteTeacher(Teacher teacher) {
        if (MySqlTeachersHandler.deleteTeacher(teacher)){
            return new Response(true, null);
        } else {
            return new Response(false, null);
        }
    }

    public static Response getAllTeaches() {
        List<Teacher> teachers = MySqlTeachersHandler.selectAllTeachers();
        return getTeachers(teachers);
    }

    public static Response getAllTeachersByStudentUsername(String username) {
        List<Teacher> teachers = MySqlTeachersHandler.selectAllTeachersByStudentUsername(username);
        return getTeachers(teachers);
    }

    private static Response getTeachers(List<Teacher> teachers) {
        if (teachers != null) {
            TeachersArrayList teachersArrayList = new TeachersArrayList(teachers);
            System.out.println(teachersArrayList.toString());
            return new Response(true, teachersArrayList);
        } else {
            return new Response(false, null);
        }
    }
}
