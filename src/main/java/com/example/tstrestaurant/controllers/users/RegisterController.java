package com.example.tstrestaurant.controllers.users;

import com.example.tstrestaurant.models.User;
import com.example.tstrestaurant.repository.UserRepository;
import com.example.tstrestaurant.to.UserTo;
import com.example.tstrestaurant.utils.UsersUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = RegisterController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RegisterController {
    static final String REST_URL = "api";

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final UserRepository userRepository;

    @Autowired
    public RegisterController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> register(@Valid @RequestBody User user) {
        log.info("register {}", user);
        User created = userRepository.save(UsersUtil.prepareToSave(UsersUtil.createNewUser(user)));
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL).build().toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}
