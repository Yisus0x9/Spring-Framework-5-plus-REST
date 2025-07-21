package org.yisus.spring.users.controllers;

import io.micrometer.core.annotation.Timed;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Usuarios", description = "API para gestión de usuarios")
public class UserController {
    @Autowired
    private UserService userService;

    @Operation(
            summary = "Obtener todos los usuarios",
            description = "Retorna una lista paginada de todos los usuarios del sistema",
            tags = {"Usuarios"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Lista de usuarios obtenida exitosamente"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error interno del servidor"
            )
    })
    @GetMapping
    @Timed(value = "get.users", description = "Time taken to get users")
    public ResponseEntity<Page<User>> getUsers(@RequestParam(value = "contain",required = false) String contain,
                                               @RequestParam(value = "page", defaultValue = "0",required = false) Integer page,
                                               @RequestParam(value = "size", defaultValue = "1000",required = false) Integer size) {
        return new ResponseEntity<>(userService.findAll(contain,page,size), HttpStatus.OK);
    }

    @GetMapping("/emails")
    public ResponseEntity<Page<String>> getEmails(
            @RequestParam(value = "page", defaultValue = "0",required = false) Integer page,
            @RequestParam(value = "size", defaultValue = "1000",required = false) Integer size) {
        return new ResponseEntity<>(userService.findAllEmails(page,size), HttpStatus.OK);
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