package com.example.tstrestaurant.controllers.votes;

import com.example.tstrestaurant.models.Vote;
import com.example.tstrestaurant.repository.VoteRepository;
import com.example.tstrestaurant.services.VoteService;
import com.example.tstrestaurant.web.AuthUser;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;


@RestController
@RequestMapping(value = ProfileVoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
public class ProfileVoteController{

    public static String endOfVoting = "11:00:00";

    public static final String REST_URL = "api/profile";

    private VoteService voteService;

    private VoteRepository voteRepository;

    @Autowired
    public ProfileVoteController(VoteRepository repository, VoteService service) {
        voteRepository = repository;
        voteService = service;
    }


    @GetMapping("/vote/{id}")
    public ResponseEntity<Vote> get(@AuthenticationPrincipal AuthUser authUser, @PathVariable long id) {
        log.info("get meal {} for user {}", id, authUser.id());
        return ResponseEntity.of(voteRepository.get(id, authUser.id()));
    }

    @DeleteMapping("/vote/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@AuthenticationPrincipal AuthUser authUser, @PathVariable long id) {
        log.info("delete {} for user {}", id, authUser.id());
        Vote vote = voteRepository.checkBelong(id, authUser.id());
        voteRepository.delete(vote);
    }


    @PutMapping(value = "/restaurant/{restaurantId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@AuthenticationPrincipal AuthUser authUser, @Valid @RequestBody Vote vote, @PathVariable long restaurantId) {
        long userId = authUser.id();
        log.info("update {} for user {}", vote, userId);
        LocalDate today = LocalDate.now();
        Vote voteFromDb = voteRepository.getFromUser(today, authUser.id());
        voteRepository.checkBelong(voteFromDb.getId(), userId);
        voteService.save(vote, voteFromDb.getId(), restaurantId, endOfVoting);
    }

    @PostMapping(value = "/restaurant/{restaurantId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vote> createWithLocation(@AuthenticationPrincipal AuthUser authUser,
                                                   @Valid @RequestBody Vote vote, @PathVariable long restaurantId) {
        long userId = authUser.id();
        log.info("create {} for user {}", vote, userId);
        Vote created = voteService.save(vote, restaurantId, userId, endOfVoting);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}
