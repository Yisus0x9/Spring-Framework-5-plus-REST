package org.yisus.spring.users.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yisus.spring.users.entities.User;
import org.yisus.spring.users.services.UserService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<Page<User>> getUsers(@RequestParam(value = "contain",required = false) String contain,
                                               @RequestParam(value = "page", defaultValue = "0",required = false) Integer page,
                                               @RequestParam(value = "size", defaultValue = "1000",required = false) Integer size) {
        return new ResponseEntity<>(userService.findAll(contain,page,size), HttpStatus.OK);
    }


    @GetMapping(value="/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") UUID id) {
        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }

    @GetMapping(value="/byEmail/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable("email") String email) {
        return new ResponseEntity<>(userService.findByEmail(email), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable("id") UUID id) {
        return new ResponseEntity<>(userService.update(user, id), HttpStatus.OK);
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") UUID id) {
        return new ResponseEntity<>(userService.delete(id), HttpStatus.OK);
    }
}
