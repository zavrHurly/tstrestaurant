package com.example.tstrestaurant.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "votes", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id"}, name = "vote_unique_user_datetime_idx")})
@Getter
@Setter
@ToString(callSuper = true, exclude = {"user"})
public class Vote extends BaseEntity {

    @Column(name= "user_id")
    @NotNull
    private long userId;

    @Column(name= "restaurant_id")
    @NotNull
    private long restaurantId;

    @Column(name = "creationDate")
    @NotNull
    private LocalDate creationDay;

    public Vote (Long id, long userId, long restaurantId, @NotNull LocalDate creationDay) {
        this.id = id;
        this.userId = userId;
        this.restaurantId = restaurantId;
        this.creationDay = this.creationDay.now();
    }

    public Vote (Long id, long userId, long restaurantId) {
        this.id = id;
        this.userId = userId;
        this.restaurantId = restaurantId;
        creationDay = creationDay.now();
    }

    public Vote(Vote vote) {
        this(vote.id, vote.userId, vote.restaurantId, vote.creationDay);
    }

    public Vote() {
    }

    public Long getId() {
        return id;
    }
}
