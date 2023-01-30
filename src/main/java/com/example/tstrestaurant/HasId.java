package com.example.tstrestaurant;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface HasId {
    Long getId();

    void setId(Long id);

    @JsonIgnore
    default boolean isNew() {
        return getId() == null;
    }

    // doesn't work for hibernate lazy proxy
    default Long id() {
        if (getId() == null) {
            throw new IllegalArgumentException("Entity must have id");
        }
        return getId();
    }
}
