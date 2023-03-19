package com.example.tstrestaurant.dishes;

import com.example.tstrestaurant.MatcherFactory;
import com.example.tstrestaurant.models.Dish;
import com.example.tstrestaurant.models.Restaurant;

import java.util.List;

import static com.example.tstrestaurant.restaurants.RestaurantTestData.restaurant1;
import static com.example.tstrestaurant.restaurants.RestaurantTestData.restaurant2;

public class DishTestData {

    public static MatcherFactory.Matcher<Dish> DISH_MATCHER = MatcherFactory.usingEqualsComparator(Dish.class);

    public static final Dish dish1 = new Dish(10001L, "Суп", 200, new Restaurant(restaurant1));
    public static final Dish dish2 = new Dish(10002L, "Мяско", 300,  new Restaurant(restaurant2));
    public static final Dish dish3 = new Dish(10003L, "Салат", 100, new Restaurant(restaurant2));
    public static final Dish dish4 = new Dish(10004L, "Компот", 50, new Restaurant(restaurant1));

    public static final List<Dish> dishes = List.of(dish4, dish3, dish2, dish1);

    public static final List<Dish> dishesFromR1 = List.of(dish1, dish4);

    public static Dish getNew() {
        return new Dish(9999L, "Созданное блюдо", 350);
    }

    public static Dish getNewWithRestaurant() {
        return new Dish(9999L, "Созданное блюдо", 350, new Restaurant(restaurant1));
    }
}
