package com.example.tstrestaurant.services;

import com.example.tstrestaurant.models.Vote;
import com.example.tstrestaurant.repository.RestaurantRepository;
import com.example.tstrestaurant.repository.UserRepository;
import com.example.tstrestaurant.repository.VoteRepository;
import com.example.tstrestaurant.to.RestaurantTo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@AllArgsConstructor
public class VoteService {

    private final VoteRepository voteRepository;

    private final RestaurantRepository restaurantRepository;

    private final UserRepository userRepository;

    public RestaurantTo getVotingResult(LocalDate today) {
        List<RestaurantTo> result = voteRepository.getVotingResult(today);
        return result.get(0);
    }

    @Transactional
    public Vote save(Vote vote, long restaurantId, long userId, String endOfVoting) {
        if (LocalTime.now().isAfter(LocalTime.parse(endOfVoting))) { return null;}
        vote.setRestaurantId(restaurantRepository.getReferenceById(restaurantId).getId());
        vote.setUserId((userRepository.getReferenceById(userId).getId()));
        return voteRepository.save(vote);
    }

}
