package com.example.tstrestaurant.utils;

import com.example.tstrestaurant.models.Role;
import com.example.tstrestaurant.models.User;
import com.example.tstrestaurant.to.UserTo;
import lombok.experimental.UtilityClass;

import static com.example.tstrestaurant.config.SecurityConfig.PASSWORD_ENCODER;

@UtilityClass
public class UsersUtil {

    public static User createNewFromTo(UserTo userTo) {
        return new User(null, userTo.getName(), userTo.getEmail().toLowerCase(), userTo.getPassword(), Role.USER);
    }

    public static User updateFromTo(User user, UserTo userTo) {
        user.setName(userTo.getName());
        user.setEmail(userTo.getEmail().toLowerCase());
        user.setPassword(userTo.getPassword());
        return user;
    }

    public static User prepareToSave(User user) {
        user.setPassword(PASSWORD_ENCODER.encode(user.getPassword()));
        user.setEmail(user.getEmail().toLowerCase());
        return user;
    }
}
