package com.example.tstrestaurant.controllers.votes;

import com.example.tstrestaurant.services.VoteService;
import com.example.tstrestaurant.to.RestaurantTo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping(value = AdminVoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
public class AdminVoteController {
    public static final String REST_URL = "api/admin/result";

    private final VoteService voteService;

    @GetMapping
    public RestaurantTo getVotingResult() {
        LocalDate today = LocalDate.now();
        return voteService.getVotingResult(today);
    }
}
