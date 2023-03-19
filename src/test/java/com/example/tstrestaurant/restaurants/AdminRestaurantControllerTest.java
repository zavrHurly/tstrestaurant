package com.example.tstrestaurant.restaurants;

import com.example.tstrestaurant.AbstractControllerTest;
import com.example.tstrestaurant.controllers.dishes.AdminDishController;
import com.example.tstrestaurant.controllers.restaurants.AdminRestaurantController;
import com.example.tstrestaurant.models.Dish;
import com.example.tstrestaurant.models.Restaurant;
import com.example.tstrestaurant.repository.DishRepository;
import com.example.tstrestaurant.repository.RestaurantRepository;
import com.example.tstrestaurant.services.DishService;
import com.example.tstrestaurant.services.RestaurantService;
import com.example.tstrestaurant.utils.JsonUtil;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Optional;

import static com.example.tstrestaurant.dishes.DishTestData.dish1;
import static com.example.tstrestaurant.dishes.DishTestData.dishesFromR1;
import static com.example.tstrestaurant.restaurants.RestaurantTestData.*;
import static com.example.tstrestaurant.restaurants.RestaurantTestData.restaurants;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AdminRestaurantControllerTest extends AbstractControllerTest {

    private final String REST_URL_WITH_SLASH = "/" + AdminRestaurantController.REST_URL;

    private AdminRestaurantController controller;

    private RestaurantRepository repository;

    private RestaurantService service;

    private MockMvc mockMvc;


    @Before
    public void setUp() throws Exception {
        repository = mock(RestaurantRepository.class);
        service = mock(RestaurantService.class);
        controller = new AdminRestaurantController(repository, service);
        mockMvc = mockController(controller);
    }

    @Test
    public void getRestaurant() throws Exception {
        when(repository.getRestaurant(restaurant1.getId())).thenReturn(Optional.of(restaurant1));
        Long id = restaurant1.getId();

        mockMvc.perform(get(REST_URL_WITH_SLASH + "/" + id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(JsonUtil.writeValue(restaurant1)));

        verify(repository, times(1)).getRestaurant(id);
    }

    @Test
    public void create() throws Exception {
        Restaurant restaurant = new Restaurant(restaurant1);
        when(repository.save(restaurant)).thenReturn(restaurant);

        mockMvc.perform(post(REST_URL_WITH_SLASH)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(JsonUtil.writeValue(restaurant)))
                .andDo(print())
                .andExpect(status().isCreated());

        verify(repository, times(1)).save(restaurant);
    }

    @Test
    public void update() throws Exception {
        Restaurant restaurant = new Restaurant(restaurant1);

        mockMvc.perform(put(REST_URL_WITH_SLASH + "/" + restaurant2.getId())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(JsonUtil.writeValue(restaurant)))
                .andDo(print())
                .andExpect(status().isNoContent());

        verify(service, times(1)).update(restaurant, restaurant2.getId());
    }

    @Test
    public void deleteRestaurant() throws Exception {
        long id = restaurant1.getId();

        mockMvc.perform(delete(REST_URL_WITH_SLASH + "/" + id))
                .andDo(print())
                .andExpect(status().isNoContent());

        verify(repository, times(1)).delete(id);
    }
}
