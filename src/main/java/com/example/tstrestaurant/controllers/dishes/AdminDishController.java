package com.example.tstrestaurant.controllers.dishes;

import com.example.tstrestaurant.models.Dish;
import com.example.tstrestaurant.repository.DishRepository;
import com.example.tstrestaurant.services.DishService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping(value = AdminDishController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
public class AdminDishController {

    public static final String REST_URL = "api/admin/restaurant/dish";

    @Autowired
    private DishRepository dishRepository;

    @Autowired
    private DishService dishService;



    @GetMapping("/{id}")
    public ResponseEntity<Dish> get(@PathVariable Long id) {
        log.info("get dish {}", id);
        return ResponseEntity.of(dishRepository.getDish(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        log.info("delete {}", id);
        dishRepository.delete(id);
    }


    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody Dish dish, @PathVariable Long id) {
        log.info("update {}", dish);
        dishRepository.get(id);
        dishService.create(dish);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> createWithLocation(@RequestBody Dish dish) {
        log.info("create {}", dish);
        Dish created = dishService.create(dish);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}
