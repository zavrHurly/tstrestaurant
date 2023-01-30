package com.example.tstrestaurant.models;

import com.example.tstrestaurant.HasId;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.domain.Persistable;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id","name"})
public class Restaurant extends BaseEntity {

    protected String name;

    protected LocalDate creationDate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    @OnDelete(action = OnDeleteAction.CASCADE)
    List<Dish> menu;

    public Restaurant(Long id, String name, List <Dish> menu) {
        super(id);
        this.name = name;
        this.menu = menu;
    }

    public Restaurant(Long id, String name, LocalDate creationDate) {
        super(id);
        this.name = name;
        this.creationDate = creationDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public List<Dish> getMenu() {
        return menu;
    }

    public void setMenu(List<Dish> menu) {
        this.menu = menu;
    }
}
