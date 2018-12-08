package com.company.transport.response;

import com.company.model.abstraction.Model;

import java.io.Serializable;

public class Response implements Serializable {

    private boolean isSuccess;
    private Model model;
    private String message;

    @Override
    public String toString() {
        return "Response{" +
                "isSuccess=" + isSuccess +
                ", model=" + model +
                ", message='" + message + '\'' +
                '}';
    }

    public Response() {
    }

    public Response(boolean isSuccess, Model model) {
        this.isSuccess = isSuccess;
        this.model = model;
    }

    public Response(boolean isSuccess, Model model, String message) {
        this.isSuccess = isSuccess;
        this.model = model;
        this.message = message;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
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