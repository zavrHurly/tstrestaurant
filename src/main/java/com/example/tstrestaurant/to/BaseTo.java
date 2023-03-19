package com.example.tstrestaurant.to;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public abstract class BaseTo {

    protected Long id;

    @Override
    public String toString() {
        return getClass().getSimpleName() + ":" + id;
    }
}
