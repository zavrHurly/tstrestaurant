package com.example.tstrestaurant.votes;

import com.example.tstrestaurant.models.Vote;

import java.time.LocalDate;

import static com.example.tstrestaurant.restaurants.RestaurantTestData.restaurant1;
import static com.example.tstrestaurant.restaurants.RestaurantTestData.restaurant2;
import static com.example.tstrestaurant.users.UserTestData.*;

public class VoteTestData {

    public static final Vote VOTE_USER = new Vote(100001L, USER_ID, restaurant1.getId());

    public static final Vote VOTE_ADMIN = new Vote(100001L, ADMIN_ID, restaurant1.getId());

    public static final Vote VOTE_YTKA = new Vote(100001L, CRYA_ID, restaurant2.getId());

    public static final Vote NEW_VOTE = new Vote(100L, ADMIN_ID, restaurant2.getId());
}
