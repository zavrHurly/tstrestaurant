package com.example.tstrestaurant.dishes;

import com.example.tstrestaurant.AbstractControllerTest;
import com.example.tstrestaurant.controllers.dishes.AdminDishController;
import com.example.tstrestaurant.controllers.dishes.ProfileDishController;
import com.example.tstrestaurant.models.Dish;
import com.example.tstrestaurant.repository.DishRepository;
import com.example.tstrestaurant.services.DishService;
import com.example.tstrestaurant.utils.JsonUtil;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static com.example.tstrestaurant.dishes.DishTestData.dish1;
import static com.example.tstrestaurant.dishes.DishTestData.dishesFromR1;
import static com.example.tstrestaurant.restaurants.RestaurantTestData.restaurant1;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
public class ProfileDishControllerTest extends AbstractControllerTest {

    private String REST_URL_WITH_SLASH = "/" + ProfileDishController.REST_URL;

    private ProfileDishController controller;

    private DishRepository repository;

    private MockMvc mockMvc;


    @Before
    public void setUp() throws Exception {
        repository = mock(DishRepository.class);
        controller = new ProfileDishController(repository);
        mockMvc = mockController(controller);
    }

    @Test
    public void getAll() throws Exception {
        when(repository.getAll(restaurant1.getId())).thenReturn(dishesFromR1);
        Dish dish = new Dish(dish1);
        dish.setRestaurant(restaurant1);
        long id = restaurant1.getId();

        mockMvc.perform(get(REST_URL_WITH_SLASH, id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(JsonUtil.writeValue(dishesFromR1)));

        verify(repository, times(1)).getAll(id);
    }
}
