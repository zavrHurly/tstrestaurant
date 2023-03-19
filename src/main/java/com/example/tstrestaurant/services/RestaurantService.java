package com.example.tstrestaurant.services;

import com.example.tstrestaurant.models.Restaurant;
import com.example.tstrestaurant.repository.RestaurantRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;


    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Transactional
    public Restaurant update(Restaurant restaurant, Long id) {
        Optional<Restaurant> restaurantFromDB = restaurantRepository.getRestaurant(id);
        BeanUtils.copyProperties(restaurant, restaurantFromDB, "id");
        return restaurant;
    }
}
