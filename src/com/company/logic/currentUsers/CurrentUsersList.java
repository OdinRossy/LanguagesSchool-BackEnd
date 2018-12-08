package com.company.logic.currentUsers;

import com.company.model.abstraction.User;

import java.util.ArrayList;
import java.util.List;

public class CurrentUsersList {
    private static List<User> userList = new ArrayList<>();

    public static void addCurrenrUser(User user) {
        if (user != null) {
            userList.add(user);
        }
    }

    public static User getCurrentUser(String username) {
        for (User user: userList) {
            if (user.getUsername().equals(username)){
                return user;
            }
        }
        return null;
    }

    public static int getSize() {
        return userList.size();
    }

}
