package com.example.tstrestaurant.repository;

import com.example.tstrestaurant.error.DataConflictException;
import com.example.tstrestaurant.models.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface RestaurantRepository extends BaseRepository<Restaurant> {


    @Query(value = "SELECT r FROM Restaurant r WHERE r.id=:id")
    public Optional<Restaurant> getRestaurant(Long id);

    @Query("SELECT r FROM Restaurant r WHERE r.creationDate=:localDate")
    public List<Restaurant> getAll(LocalDate localDate);


    default Restaurant checkBelong(Long id) {
        return getRestaurant(id).orElseThrow(
                () -> new DataConflictException("Vote id=" + id + " doesn't belong"));
    }
}