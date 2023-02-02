package com.example.tstrestaurant.controllers.restaurants;

import com.example.tstrestaurant.models.Restaurant;
import com.example.tstrestaurant.repository.RestaurantRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = ProfileRestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class ProfileRestaurantController {

    static final String REST_URL = "rest/profile/restaurant";

    private final RestaurantRepository restaurantRepository;

    @Autowired
    public ProfileRestaurantController(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @GetMapping
    public List<Restaurant> getAll() {
        log.info("getAll restaurant");
        LocalDate today = LocalDate.now();
        return restaurantRepository.getAll(today);
    }
}
