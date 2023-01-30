package com.example.tstrestaurant.services;

import com.example.tstrestaurant.models.Dish;
import com.example.tstrestaurant.repository.DishRepository;
import com.example.tstrestaurant.repository.RestaurantRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class DishService {

    private final DishRepository dishRepository;

    private final RestaurantRepository restaurantRepository;


    @Transactional
    public Dish create(Dish dish, Long restaurantId) {
        dish.setRestaurant(restaurantRepository.getExisted(restaurantId));
        return dishRepository.save(dish);
    }
}