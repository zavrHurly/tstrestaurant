package com.example.tstrestaurant.restaurants;

import com.example.tstrestaurant.MatcherFactory;
import com.example.tstrestaurant.models.Restaurant;
import com.example.tstrestaurant.to.RestaurantTo;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RestaurantTestData {

    public static final int NOT_FOUND = 10;

    public static final LocalDate thisDay = LocalDate.now();



    public static final Restaurant restaurant1 = new Restaurant(1001L, "Ресторан 1", thisDay);

    public static final Restaurant restaurant2 = new Restaurant(1002L, "Ресторан 2", thisDay);

    public static final Restaurant restaurant3 = new Restaurant(1003L, "Ресторан 3", thisDay);

    public static final RestaurantTo restaurantTo1 = new RestaurantTo(1001L, "Ресторан 1", thisDay, 2);

    public static final RestaurantTo restaurantTo2 = new RestaurantTo(1001L, "Ресторан 1", thisDay, 4);

    public static final List<Restaurant> restaurants = List.of(restaurant1, restaurant2, restaurant3);

    public static final List<RestaurantTo> restaurantTos = List.of(restaurantTo1, restaurantTo2);

    public static Restaurant getNew() {
        return new Restaurant(null, "Созданный ресторан");
    }
}
