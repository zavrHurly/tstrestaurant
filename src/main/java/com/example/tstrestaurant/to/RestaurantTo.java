package com.example.tstrestaurant.to;

import lombok.EqualsAndHashCode;
import lombok.Value;

import java.time.LocalDate;

@Value
@EqualsAndHashCode(callSuper = true)
public class RestaurantTo extends BaseTo {

    String name;

    LocalDate creationDay;

    int voteCount;

    public RestaurantTo(long id, String name, LocalDate creationDay, int voteCount) {
        super(id);
        this.name = name;
        this.creationDay = creationDay;
        this.voteCount = voteCount;
    }
}
