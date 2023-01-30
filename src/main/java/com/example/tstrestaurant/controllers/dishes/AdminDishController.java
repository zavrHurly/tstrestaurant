package com.example.tstrestaurant.controllers.dishes;

import com.example.tstrestaurant.models.Dish;
import com.example.tstrestaurant.repository.DishRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static com.example.tstrestaurant.utils.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = AdminDishController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
public class AdminDishController {
    static final String REST_URL = "rest/admin/dish";

    private final DishRepository dishRepository;


    @GetMapping("/{id}")
    public ResponseEntity<Dish> get(@PathVariable Long id) {
        log.info("get restaurant {}", id);
        return ResponseEntity.of(dishRepository.getDish(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id, Long restaurantId) {
        log.info("delete {} for restaurant {}", id, restaurantId);
        dishRepository.delete(id, restaurantId);
    }

    @GetMapping
    public List<Dish> getAll() {
        log.info("getAll");
        return dishRepository.getAll();
    }


    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody Dish dish, @PathVariable ("id") Dish dishFromDb) {
        log.info("update {}", dish);
        BeanUtils.copyProperties(dish, dishFromDb, "id");
        dishRepository.save(dish);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> createWithLocation(@RequestBody Dish dish) {
        log.info("create {} for restaurant", dish);
        checkNew(dish);
        Dish created = dishRepository.save(dish);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}
