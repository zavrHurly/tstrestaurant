package com.example.tstrestaurant.repository;

import com.example.tstrestaurant.error.DataConflictException;
import com.example.tstrestaurant.models.Dish;
import com.example.tstrestaurant.models.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface DishRepository extends BaseRepository<Dish> {

    @Query("SELECT d FROM Dish d WHERE d.restaurant.id=:restaurantId")
    List<Dish> getAll(Long restaurantId);

    @Query("SELECT d FROM Dish d WHERE d.id = :id and d.restaurant.id = :restaurantId")
    Optional <Dish> get(Long id, Long restaurantId);

    @Query(value = "SELECT d FROM Dish d WHERE d.id=:id")
    public Optional<Dish> getDish(Long id);

    @Modifying
    @Transactional
    @Query("DELETE FROM Dish d WHERE d.id=:id AND d.restaurant.id=:restaurantId")
    int delete(Long id, Long restaurantId);
}
