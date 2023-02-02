package com.example.tstrestaurant.controllers.dishes;

import com.example.tstrestaurant.models.Dish;
import com.example.tstrestaurant.repository.DishRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = ProfileDishController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
public class ProfileDishController{

    static final String REST_URL = "rest/profile/restaurant/{restaurantId}/dish";

    private final DishRepository dishRepository;

    @GetMapping
    public List<Dish> getAll(@PathVariable Long restaurantId) {
        log.info("getAll restaurant");
        return dishRepository.getAll(restaurantId);
    }
}
