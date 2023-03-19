package com.example.tstrestaurant.dishes;

import com.example.tstrestaurant.models.Dish;
import com.example.tstrestaurant.models.Restaurant;
import com.example.tstrestaurant.repository.DishRepository;
import com.example.tstrestaurant.repository.RestaurantRepository;
import com.example.tstrestaurant.services.DishService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.example.tstrestaurant.dishes.DishTestData.*;

import static com.example.tstrestaurant.restaurants.RestaurantTestData.restaurant1;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@Transactional
public class DishServiceTest {

    private DishRepository dishRepository;

    private DishService service;


    @Before
    public void setUp() throws Exception {
        dishRepository = mock(DishRepository.class);
        service = new DishService(dishRepository);
    }


    @Test
    public void save() throws Exception {
        Dish dish = new Dish(dish1);
        Restaurant restaurant = new Restaurant(restaurant1);
        when(dishRepository.save(dish1)).thenReturn(dish1);

        Dish save = service.create(dish);

        verify(dishRepository, times(1)).save(dish1);
        assertThat(save).isEqualTo(dish1);

    }
}
