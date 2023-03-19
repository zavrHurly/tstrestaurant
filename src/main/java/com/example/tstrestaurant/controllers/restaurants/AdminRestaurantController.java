package com.example.tstrestaurant.controllers.restaurants;

import com.example.tstrestaurant.models.Dish;
import com.example.tstrestaurant.models.Restaurant;
import com.example.tstrestaurant.repository.DishRepository;
import com.example.tstrestaurant.repository.RestaurantRepository;
import com.example.tstrestaurant.services.RestaurantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping(value = AdminRestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminRestaurantController{

    public static final String REST_URL = "api/admin/restaurant";

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final RestaurantRepository restaurantRepository;

    private final RestaurantService restaurantService;

    @Autowired
    public AdminRestaurantController(RestaurantRepository restaurantRepository, RestaurantService restaurantService) {
        this.restaurantRepository = restaurantRepository;
        this.restaurantService = restaurantService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> get(@PathVariable Long id) {
        log.info("get restaurant {}", id);
        return ResponseEntity.of(restaurantRepository.getRestaurant(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        log.info("delete restaurant {}", id);
        restaurantRepository.delete(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> createWithLocation(@RequestBody Restaurant restaurant) {
        log.info("create {}", restaurant);
        Restaurant created = restaurantRepository.save(restaurant);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody Restaurant restaurant, @PathVariable Long id) {
        log.info("update {}", restaurant);
        restaurantService.update(restaurant, id);
    }
}
