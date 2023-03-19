package com.example.tstrestaurant.users;

import com.example.tstrestaurant.MatcherFactory;
import com.example.tstrestaurant.models.Role;
import com.example.tstrestaurant.models.User;
import com.example.tstrestaurant.utils.JsonUtil;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTestData {

    public static final long USER_ID = 100L;
    public static final long ADMIN_ID = 2L;
    public static final long CRYA_ID = 3L;

    public static final long NOT_FOUND = 100;
    public static final String USER_MAIL = "user@yandex.ru";
    public static final String ADMIN_MAIL = "admin@gmail.com";
    public static final String GUEST_MAIL = "guest@gmail.com";

    public static final User user = new User(USER_ID, "User", USER_MAIL, "password", Role.USER);
    public static final User admin = new User(ADMIN_ID, "Admin", ADMIN_MAIL, "admin", Role.ADMIN, Role.USER);
    public static final User crya = new User(CRYA_ID, "Ytka", GUEST_MAIL, "guest", Role.USER);

    public static User getNew() {
        return new User(null, "New", "new@gmail.com", "newPass", false, Collections.singleton(Role.USER));
    }

    public static User getUpdated() {
        return new User(USER_ID, "UpdatedName", USER_MAIL, "newPass", false, List.of(Role.ADMIN));
    }

    public static String jsonWithPassword(User user, String passw) {
        return JsonUtil.writeAdditionProps(user, "password", passw);
    }


}
