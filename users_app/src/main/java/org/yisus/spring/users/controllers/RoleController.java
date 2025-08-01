package org.yisus.spring.users.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.yisus.spring.users.entities.Role;
import org.yisus.spring.users.services.RoleService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/roles")
public class RoleController {
    @Autowired
    private RoleService roleService;
    private static final Logger log = LoggerFactory.getLogger(RoleController.class);


    @GetMapping()
    public ResponseEntity<List<Role>> getRoles() {
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        log.info("\033[31m Name {}",auth.getName());
        log.info("\033[31m Principal {}",auth.getPrincipal());
        log.info("\033[31m Credentials {}",auth.getCredentials());
        log.info("\033[31m Roles {}",auth.getAuthorities().stream().toList());
        return new ResponseEntity<>(roleService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{roleId}")
    public ResponseEntity<Role> getRoleById(@PathVariable UUID roleId) {
        return new ResponseEntity<>(roleService.findById(roleId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Role> createRole(@RequestBody Role role) {
        return new ResponseEntity<>(roleService.save(role), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{roleId}")
    public ResponseEntity<Role> updateRole(@RequestBody Role role, @PathVariable UUID roleId) {
        return new ResponseEntity<>(roleService.update(role, roleId), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{roleId}")
    public ResponseEntity<String> deleteRole(@PathVariable UUID roleId) {
        return new ResponseEntity<>(roleService.delete(roleId),HttpStatus.OK);
    }
}
