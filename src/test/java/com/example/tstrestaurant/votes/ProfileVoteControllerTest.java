package com.example.tstrestaurant.votes;

import com.example.tstrestaurant.AbstractControllerTest;
import com.example.tstrestaurant.controllers.votes.AdminVoteController;
import com.example.tstrestaurant.controllers.votes.ProfileVoteController;
import com.example.tstrestaurant.models.Dish;
import com.example.tstrestaurant.models.User;
import com.example.tstrestaurant.repository.VoteRepository;
import com.example.tstrestaurant.services.VoteService;
import com.example.tstrestaurant.utils.JsonUtil;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static com.example.tstrestaurant.dishes.DishTestData.dish1;
import static com.example.tstrestaurant.restaurants.RestaurantTestData.restaurant1;
import static com.example.tstrestaurant.users.UserTestData.user;
import static com.example.tstrestaurant.votes.VoteTestData.VOTE_USER;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ProfileVoteControllerTest extends AbstractControllerTest {
    private static final String REST_URL_WITH_SLASH = "/" + ProfileVoteController.REST_URL;

    private ProfileVoteController controller;
    private VoteService service;

    private VoteRepository repository;
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        service = mock(VoteService.class);
        repository = mock(VoteRepository.class);
        controller = new ProfileVoteController(repository, service);
        mockMvc = mockController(controller);
    }
}
