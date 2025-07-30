package org.yisus.spring.users.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yisus.spring.users.entities.Role;
import org.yisus.spring.users.entities.UserInRole;
import org.yisus.spring.users.services.UserInRoleService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/userInRole")
public class UserInRoleController {
    @Autowired
    private UserInRoleService userInRoleService;


    @GetMapping
    public ResponseEntity<Page<UserInRole>> getUserInRole(
            @RequestParam(name = "roles",required = false) List<String> roles,
            @RequestParam(name = "page",required = false,defaultValue = "0") Integer page,
            @RequestParam(name = "size",required = false,defaultValue = "100")Integer size){
        return new ResponseEntity<>(userInRoleService.findAll(roles,page, size), HttpStatus.OK);
    }

    @GetMapping("/rolesByUser/{userId}")
    public ResponseEntity<Page<Role>> getRolesByUser(
            @PathVariable("userId") UUID userId,
            @RequestParam(name = "page",required = false,defaultValue = "0") Integer page,
            @RequestParam(name = "size",required = false,defaultValue = "100")Integer size){
        return new ResponseEntity<>(userInRoleService.findRolesByUserId(userId,page,size),HttpStatus.OK);
    }

    @GetMapping("/user/{userId}/role/{roleId}")
    public ResponseEntity<UserInRole> getRolesByUser(
            @PathVariable("userId") UUID userId,
            @PathVariable("roleId") UUID roleId){
        return new ResponseEntity<>(userInRoleService.findByUserIdAndRoleId(userId,roleId),HttpStatus.OK);
    }

    @PostMapping("/user/{userId}/role/{roleId}")
    public ResponseEntity<UserInRole> create(
            @PathVariable("userId") UUID userId,
            @PathVariable("roleId") UUID roleId){
        return new ResponseEntity<>(userInRoleService.save(userId,roleId),HttpStatus.CREATED);
    }

    @DeleteMapping("/user/{userId}/role/{roleId}")
    public ResponseEntity<String> delete(
            @PathVariable("userId") UUID userId,
            @PathVariable("roleId") UUID roleId){
        return new ResponseEntity<>(userInRoleService.delete(userId,roleId),HttpStatus.OK);
    }

}
