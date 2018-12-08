package com.company.transport.request;

import com.company.model.abstraction.Model;

import java.io.Serializable;

public class Request implements Serializable {

    private String method;
    private Model model;
    private String message;

    public final static String GET = "GET";
    public final static String POST = "POST";
    public final static String PUT = "PUT";
    public final static String DELETE = "DELETE";

    @Override
    public String toString() {
        return "Request{" +
                "method='" + method + '\'' +
                ", model=" + model.toString() +
                ", message='" + message + '\'' +
                '}';
    }

    public Request(String method, Model model) {
        this.method = method;
        this.model = model;
    }

    public Request(String method, Model model, String message) {
        this.method = method;
        this.model = model;
        this.message = message;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}