package com.company.model;

import com.company.model.abstraction.Model;

public class Admin implements Model {

    public static final String ACTION_STOP_SERVER = "actionStopServer";

    private String action;

    @Override
    public String getModelName() {
        return "Admin";
    }

    public Admin(String action) {
        this.action = action;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
