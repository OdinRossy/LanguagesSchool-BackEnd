package com.company.logic.workers;

import com.company.database.MySqlStudentsHandler;
import com.company.model.Student;
import com.company.model.buffers.StudentsArrayList;
import com.company.transport.response.Response;

import java.util.List;

public class StudentWorker {

    public static Response getAllStudents() {
        return null;
    }

    public static Response getAllStudentsByTeacherUsername(String username) {
        List<Student> students = MySqlStudentsHandler.selectAllStudentsByTeacherUsername(username);
        if (students != null) {
            StudentsArrayList studentsArrayList = new StudentsArrayList(students);
            System.out.println(studentsArrayList.toString());
            return new Response(true, studentsArrayList, "");
        } else {
            return new Response(false, null, "");
        }
    }

    public static Response signIn(Student student) throws NullPointerException {
        student = MySqlStudentsHandler.selectStudentByUsernameAndPassword(student);
//        System.out.println(student.toString());
        if (student.getFirstName() != null) {
            return new Response(true,student,"Success");
        } else {
            return new Response(false,null);
        }
    }

    public static Response signUp(Student student) {
        student = MySqlStudentsHandler.insertStudent(student);
        if (student.getFirstName() != null) {
            return new Response(true,student,"Success");
        } else {
            return new Response(false,null);
        }
    }

    public static Response editStudent(Student student) {
        boolean isUpdated = MySqlStudentsHandler.updateStudent(student);
        if (isUpdated) {
            student = MySqlStudentsHandler.selectStudentByUsernameAndPassword(student);
            return new Response(true,student,"Success");
        } else {
            return new Response(false,null);
        }
    }

    public static Response deleteStudent(Student student) {
        if (MySqlStudentsHandler.deleteStudent(student)){
            return new Response(true, null);
        } else {
            return new Response(false, null);

        }
    }
}
