package com.example.tstrestaurant.votes;

import com.example.tstrestaurant.models.Restaurant;
import com.example.tstrestaurant.models.User;
import com.example.tstrestaurant.models.Vote;
import com.example.tstrestaurant.repository.DishRepository;
import com.example.tstrestaurant.repository.RestaurantRepository;
import com.example.tstrestaurant.repository.UserRepository;
import com.example.tstrestaurant.repository.VoteRepository;
import com.example.tstrestaurant.services.DishService;
import com.example.tstrestaurant.services.VoteService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static com.example.tstrestaurant.controllers.votes.ProfileVoteController.endOfVoting;
import static com.example.tstrestaurant.restaurants.RestaurantTestData.*;
import static com.example.tstrestaurant.users.UserTestData.user;
import static com.example.tstrestaurant.votes.VoteTestData.NEW_VOTE;
import static com.example.tstrestaurant.votes.VoteTestData.VOTE_USER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@Transactional
public class VoteServiceTest {

    private RestaurantRepository restaurantRepository;

    private VoteService service;

    private UserRepository userRepository;

    private VoteRepository voteRepository;

    @Before
    public void setUp() throws Exception {
        restaurantRepository = mock(RestaurantRepository.class);
        userRepository = mock(UserRepository.class);
        voteRepository = mock(VoteRepository.class);
        service = new VoteService(voteRepository, restaurantRepository, userRepository);
    }

    @Test
    public void saveAfterGraduationVoting() {
        Vote vote = new Vote(NEW_VOTE);
        Vote save = service.save(vote, vote.getRestaurantId(), vote.getUserId(), endOfVoting);
        assertThat(save).isNull();
    }

    @Test
    public void save() throws Exception {
        Vote vote = new Vote(VOTE_USER);
        Vote toSave = new Vote(null, vote.getUserId(), vote.getRestaurantId(), vote.getCreationDay());
        when(voteRepository.save(toSave)).thenReturn(vote);
        when(restaurantRepository.getReferenceById(vote.getRestaurantId())).thenReturn(restaurant1);
        when(userRepository.getReferenceById(vote.getUserId())).thenReturn(user);
        Vote save = service.save(toSave, vote.getRestaurantId(), vote.getUserId(),
                "23:59:00");

        verify(voteRepository, times(1)).save(toSave);
        assertThat(save).isNotNull();
    }

    @Test
    public void getResultVotes() throws Exception {
        LocalDate now = LocalDate.now();
        when(voteRepository.getVotingResult(now)).thenReturn(restaurantTos);
        assertThat(service.getVotingResult(now)).isNotNull();
        verify(voteRepository, times(1)).getVotingResult(now);
    }

    @Test
    public void get() {
        when(voteRepository.get(user.getId())).thenReturn(VOTE_USER);
        Vote vote = VOTE_USER;
        assertThat(voteRepository.get(user.getId())).equals(VOTE_USER);
        verify(voteRepository, times(1)).get(user.getId());
    }
}
