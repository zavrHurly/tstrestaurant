package com.example.tstrestaurant.services;

import com.example.tstrestaurant.models.Dish;
import com.example.tstrestaurant.repository.DishRepository;
import com.example.tstrestaurant.repository.RestaurantRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DishService {

    private final DishRepository dishRepository;


    @Autowired
    public DishService(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    @Transactional
    public Dish create(Dish dish) {
        return dishRepository.save(dish);
    }
}