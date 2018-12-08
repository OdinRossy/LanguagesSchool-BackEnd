package com.company.transport.messageBuilder;

import com.company.logic.workers.*;
import com.company.model.*;
import com.company.transport.configuration.ServerConfiguration;
import com.company.transport.request.Request;
import com.company.transport.response.Response;

public class ResponseGenerator implements ServerConfiguration {

    public Response generateResponse(Request request) {

        try {
            switch (request.getMethod()) {
                case Request.GET: {
                    return actionGet(request);
                }
                case Request.POST: {
                    return actionPost(request);
                }
                case Request.PUT: {
                    return actionPut(request);
                }
                case Request.DELETE: {
                    return actionDelete(request);
                }
                default:
                    throw new RuntimeException("Unknown method " + request.getMethod());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Response(false, null, e.toString());
        }
    }

    private Response actionGet(Request request) throws RuntimeException {
        String modelName = request.getModel().getModelName();
        switch (modelName) {
            case "Student" : {
                Student student = (Student) request.getModel();
                String[] parsed = request.getMessage().toLowerCase().split(":");
                switch (parsed[0].toLowerCase()) {
                    case "all" : {
                        return StudentWorker.getAllStudents();
                    }
                    case "by_teacher_username" : {
                        return StudentWorker.getAllStudentsByTeacherUsername(parsed[1]);

                    }
                    case "sign_in" : {
                        return StudentWorker.signIn(student);
                    }
                    default: throw new RuntimeException("Unknown message for " + modelName + ": " + request.getMessage());
                }
            }
            case "Teacher" : {
                Teacher teacher = (Teacher) request.getModel();
                String[] parsed = request.getMessage().toLowerCase().split(":");
                switch (parsed[0].toLowerCase()) {
                    case "all" : {
                        return TeacherWorker.getAllTeaches();
                    }
                    case "by_student_username" : {
                        return TeacherWorker.getAllTeachersByStudentUsername(parsed[1]);

                    }
                    case "sign_in" : {
                        return TeacherWorker.signIn(teacher);
                    }
                    default: throw new RuntimeException("Unknown message for " + modelName + ": " + request.getMessage());
                }
            }
            case "Course" : {
                Course course = (Course) request.getModel();
                String[] parsed = request.getMessage().toLowerCase().split(":");
                switch (parsed[0].toLowerCase()) {
                    case "all" : {
                        return CourseWorker.getAllCourses();
                    }
                    case "by_student_username" : {
                        return CourseWorker.getAllCoursesByStudentUsername(parsed[1]);

                    }
                    case "by_teacher_username" : {
                        return CourseWorker.getAllCoursesByTeacherUsername(parsed[1]);
                    }
                    default: throw new RuntimeException("Unknown message for " + modelName + ": " + request.getMessage());
                }
            }
            default: throw new RuntimeException("Unknown model " + modelName);
        }
    }

    private Response actionPost(Request request) throws RuntimeException {
        String modelName = request.getModel().getModelName();
        switch (modelName) {
            case "Student" : {
                Student student = (Student) request.getModel();
                return StudentWorker.signUp(student);
            }

            case "Teacher" : {
                throw new RuntimeException("teacher is not ready");
            }

            case "Course" : {
                throw new RuntimeException("course is not ready");
            }

            case "Order" : {
                Order order = (Order) request.getModel();
                return OrderWorker.addNewOrder(order);
            }
            case "HelpStudent" : {
                HelpStudent helpStudent = (HelpStudent) request.getModel();
                return HelpStudentWorker.insertHelpStudent(helpStudent);
            }

            default:
                throw new RuntimeException("Unknown model " + modelName);
        }
    }

    private Response actionPut(Request request) throws RuntimeException {
        String modelName = request.getModel().getModelName();
        switch (modelName) {
            case "Student" : {
                Student student = (Student) request.getModel();
                return StudentWorker.editStudent(student);
            }

            case "Teacher" : {
                Teacher teacher = (Teacher) request.getModel();
                return TeacherWorker.editTeacher(teacher);
            }

            case "Course" : {
                throw new RuntimeException("course is not ready");
            }

            default:
                throw new RuntimeException("Unknown model " + modelName);
        }
    }

    private Response actionDelete(Request request) throws RuntimeException {
        String modelName = request.getModel().getModelName();
        switch (modelName) {

            case "Student" : {
                Student student = (Student) request.getModel();
                return StudentWorker.deleteStudent(student);
            }
            case "Teacher" : {
                Teacher teacher = (Teacher) request.getModel();
                return TeacherWorker.deleteTeacher(teacher);
            }

            case "Course" : {
                throw new RuntimeException("course is not ready");
            }

            case "Order" : {
                Order order = (Order) request.getModel();
                return OrderWorker.deleteOrder(order);
            }

            default:
                throw new RuntimeException("Unknown model " + modelName);
        }
    }

    private static ResponseGenerator instance;

    public static synchronized ResponseGenerator getInstance() {
        if (instance == null) {
            instance = new ResponseGenerator();
        }
        return instance;
    }

    private ResponseGenerator() {}
}
