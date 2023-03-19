package com.example.tstrestaurant.dishes;

import com.example.tstrestaurant.AbstractControllerTest;
import com.example.tstrestaurant.controllers.dishes.AdminDishController;
import com.example.tstrestaurant.models.Dish;
import com.example.tstrestaurant.repository.DishRepository;
import com.example.tstrestaurant.services.DishService;
import com.example.tstrestaurant.utils.JsonUtil;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import java.util.Optional;

import static com.example.tstrestaurant.dishes.DishTestData.*;
import static com.example.tstrestaurant.restaurants.RestaurantTestData.restaurant1;
import static com.example.tstrestaurant.restaurants.RestaurantTestData.restaurant2;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class AdminDishControllerTest extends AbstractControllerTest {

    private final String REST_URL_WITH_SLASH = "/" + AdminDishController.REST_URL;

    private AdminDishController controller;

    private DishRepository repository;

    private DishService service;

    private MockMvc mockMvc;


    @Before
    public void setUp() throws Exception {
        repository = mock(DishRepository.class);
        service = mock(DishService.class);
        controller = new AdminDishController(repository, service);
        mockMvc = mockController(controller);
    }

    @Test
    public void getDish() throws Exception {
        when(repository.getDish(dish1.getId())).thenReturn(Optional.of(dish1));
        Dish dish = new Dish(dish1);
        dish.setRestaurant(restaurant1);
        long id = dish1.getId();

        mockMvc.perform(get(REST_URL_WITH_SLASH + "/" + id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(JsonUtil.writeValue(dish1)));

        verify(repository, times(1)).getDish(id);
    }

    @Test
    public void create() throws Exception {

        Dish dish = new Dish(dish1);
        when(service.create(dish)).thenReturn(dish);
        when(repository.save(dish)).thenReturn(dish);

        mockMvc.perform(post(REST_URL_WITH_SLASH)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(JsonUtil.writeValue(dish)))
                .andDo(print())
                .andExpect(status().isCreated());

        verify(service, times(1)).create(dish1);
    }

    @Test
    public void update() throws Exception {
        Dish dish = new Dish(dish1);
        dish.setRestaurant(restaurant2);

        mockMvc.perform(put(REST_URL_WITH_SLASH + "/" + dish1.getId())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(JsonUtil.writeValue(dish1)))
                .andDo(print())
                .andExpect(status().isNoContent());

        verify(service, times(1)).create(dish);
    }

    @Test
    public void deleteDish() throws Exception {
        long id = dish1.getId();

        mockMvc.perform(delete(REST_URL_WITH_SLASH + "/" + id))
                .andDo(print())
                .andExpect(status().isNoContent());

        verify(repository, times(1)).delete(id);
    }
}
