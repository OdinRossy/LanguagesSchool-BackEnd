package com.company.logic.workers;

import com.company.database.MySqlCoursesHandler;
import com.company.model.Course;
import com.company.model.buffers.CoursesArrayList;
import com.company.transport.response.Response;

import java.util.List;

public class CourseWorker {

    private static Response getResponse(List<Course> courses) {
        if (courses != null) {
            CoursesArrayList coursesArrayList = new CoursesArrayList(courses);
            System.out.println(coursesArrayList.toString());
            return new Response(true, coursesArrayList, "");
        } else {
            return new Response(false, null, "");
        }
    }

    public static Response getAllCourses() {
        List<Course> courses = MySqlCoursesHandler.selectAllCourses();
        return getResponse(courses);
    }

    public static Response getAllCoursesByStudentUsername(String username) {
        List<Course> courses = MySqlCoursesHandler.selectAllCoursesByStudentUsername(username);
        return getResponse(courses);
    }

    public static Response getAllCoursesByTeacherUsername(String username) {
        List<Course> courses = MySqlCoursesHandler.selectAllCoursesByTeacherUsername(username);
        return getResponse(courses);
    }
}