package com.example.tstrestaurant.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Dish extends BaseEntity {

    @JsonProperty("name")
    @NotBlank
    protected String name;

    @JsonProperty("price")
    @NotNull
    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    @NotNull
    private Restaurant restaurant;

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Dish(long id, String name, int price){
        super(id);
        this.name = name;
        this.price = price;
    }

    public Dish(Dish dish) {
        this(dish.id, dish.name, dish.price);
    }

    public Dish(long id, String name, int price, Restaurant restaurant){
        super(id);
        this.name = name;
        this.price = price;
        this.restaurant = restaurant;
    }

    public String toString() {
        return "Dish{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", restaurant=" + restaurant +
                '}';
    }
}
