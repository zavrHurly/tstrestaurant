package com.example.tstrestaurant.web;

import com.example.tstrestaurant.models.User;
import lombok.Getter;
import org.springframework.lang.NonNull;

public class AuthUser extends org.springframework.security.core.userdetails.User {

    @Getter
    private final User user;

    public AuthUser(@NonNull User user) {
        super(user.getEmail(), user.getPassword(), user.getRoles());
        this.user = user;
    }

    public long id() {
        return user.getId();
    }

    @Override
    public String toString() {
        return "AuthUser:" + user.getId() + '[' + user.getEmail() + ']';
    }
}
