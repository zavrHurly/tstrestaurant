package com.example.tstrestaurant.votes;

import com.example.tstrestaurant.AbstractControllerTest;
import com.example.tstrestaurant.controllers.votes.AdminVoteController;
import com.example.tstrestaurant.services.VoteService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static com.example.tstrestaurant.restaurants.RestaurantTestData.restaurantTo1;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AdminVoteControllerTest extends AbstractControllerTest {
    private static final String REST_URL_WITH_SLASH = "/" + AdminVoteController.REST_URL;

    private AdminVoteController controller;
    private VoteService service;
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        service = mock(VoteService.class);
        controller = new AdminVoteController(service);
        mockMvc = mockController(controller);
    }

    @Test
    public void getVotingResult() throws Exception {
        LocalDate today = LocalDate.now();
        when(service.getVotingResult(today)).thenReturn(restaurantTo1);
        mockMvc.perform(get(REST_URL_WITH_SLASH))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(service, times(1)).getVotingResult(today);
    }
}
