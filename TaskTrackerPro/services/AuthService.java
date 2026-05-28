package services;

import models.Admin;
import models.User;

public class AuthService {

    public User login(String username) {

        if (username.equalsIgnoreCase("admin")) {
            return new Admin(username);
        }

        return new User(username);
    }
}
