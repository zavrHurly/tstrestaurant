package com.example.tstrestaurant.restaurants;

import com.example.tstrestaurant.AbstractControllerTest;
import com.example.tstrestaurant.controllers.dishes.ProfileDishController;
import com.example.tstrestaurant.controllers.restaurants.ProfileRestaurantController;
import com.example.tstrestaurant.models.Dish;
import com.example.tstrestaurant.repository.DishRepository;
import com.example.tstrestaurant.repository.RestaurantRepository;
import com.example.tstrestaurant.utils.JsonUtil;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static com.example.tstrestaurant.dishes.DishTestData.dish1;
import static com.example.tstrestaurant.dishes.DishTestData.dishesFromR1;
import static com.example.tstrestaurant.restaurants.RestaurantTestData.restaurant1;
import static com.example.tstrestaurant.restaurants.RestaurantTestData.restaurants;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
public class ProfileRestaurantControllerTest extends AbstractControllerTest {

    private String REST_URL_WITH_SLASH = "/" + ProfileRestaurantController.REST_URL;

    private ProfileRestaurantController controller;

    private RestaurantRepository repository;

    private MockMvc mockMvc;


    @Before
    public void setUp() throws Exception {
        repository = mock(RestaurantRepository.class);
        controller = new ProfileRestaurantController(repository);
        mockMvc = mockController(controller);
    }

    @Test
    public void getAll() throws Exception {
        LocalDate today = LocalDate.now();
        when(repository.getAll(today)).thenReturn(restaurants);

        mockMvc.perform(get(REST_URL_WITH_SLASH))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(JsonUtil.writeValue(restaurants)));

        verify(repository, times(1)).getAll(today);
    }
}