package com.example.tstrestaurant.repository;

import com.example.tstrestaurant.error.DataConflictException;
import com.example.tstrestaurant.models.Vote;
import com.example.tstrestaurant.to.RestaurantTo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface VoteRepository extends BaseRepository<Vote>{

    @Query("SELECT m FROM Vote m WHERE m.id = :id and m.userId = :userId")
    Optional<Vote> get(long id, long userId);

    @Query("SELECT v FROM Vote v JOIN FETCH v.restaurantId WHERE v.id = ?1 and v.restaurantId = ?2")
    Optional<Vote> getWithRestaurant(long id, long restaurantId);

    @Query("SELECT v.restaurantId, r.name, COUNT(v.userId) AS countresult  FROM Vote v LEFT JOIN Restaurant r WHERE v.creationDay = ?1 GROUP BY v.restaurantId ORDER BY countresult DESC")
    List<RestaurantTo> getVotingResult(LocalDate today);

    @Query("SELECT v FROM Vote v WHERE v.creationDay = :today and v.userId = :userId")
    Vote getFromUser(LocalDate today, long userId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Vote v WHERE v.id= ?1 AND v.userId= ?2")
    int delete(long id, long userId);

    default Vote checkBelong(long id, long userId) {
        return get(id, userId).orElseThrow(
                () -> new DataConflictException("Vote id=" + id + " doesn't belong to User id=" + userId));
    }
}
