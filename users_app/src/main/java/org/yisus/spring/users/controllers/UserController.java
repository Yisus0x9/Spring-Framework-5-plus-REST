package org.yisus.spring.users.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yisus.spring.users.models.User;
import org.yisus.spring.users.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getUsers(@RequestParam(value = "contain",required = false) String contain) {
        return new ResponseEntity<>(userService.getUsers(contain), HttpStatus.OK);
    }


    @GetMapping(value="/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable("email") String email) {
        return new ResponseEntity<>(userService.getUserByEmail(email), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

    @PutMapping(value="/{email}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable("email") String email) {
        return new ResponseEntity<>(userService.updateUser(user, email), HttpStatus.OK);
    }

    @DeleteMapping(value="/{email}")
    public ResponseEntity<String> deleteUser(@PathVariable("email") String email) {
        return new ResponseEntity<>(userService.deleteUser(email), HttpStatus.OK);
    }
}
