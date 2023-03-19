package com.example.tstrestaurant.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Table
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Restaurant extends BaseEntity {

    @NotBlank
    @JsonProperty("name")
    protected String name;

    @JsonProperty("creationDay")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    protected LocalDate creationDate;

    @JsonProperty("menu")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    @OnDelete(action = OnDeleteAction.CASCADE)
    List<Dish> menu;

    public Long getId() {
        return id;
    }

    public List<Dish> getMenu() {
        return menu;
    }

    public void setMenu(List<Dish> menu) {
        this.menu = menu;
    }

    public Restaurant(Long id, String name) {
        super(id);
        this.name = name;
        creationDate = LocalDate.now();
    }

    public Restaurant(Long id, String name, LocalDate creationDate) {
        super(id);
        this.name = name;
        this.creationDate = creationDate;
    }

    public Restaurant(Restaurant restaurant) {
        this(restaurant.id, restaurant.name);
    }

}
