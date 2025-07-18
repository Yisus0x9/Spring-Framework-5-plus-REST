package org.yisus.spring.users.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.yisus.spring.users.entities.Role;
import org.yisus.spring.users.repositories.RoleRepository;

import java.util.List;
import java.util.UUID;

@Service
public class RoleService {
    @Autowired
    private  RoleRepository repository;


    public List<Role> findAll() {
        return repository.findAll();
    }

    public Role findById(UUID id) {
        return repository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Role with id %s not found", id)));
    }

    public Role save(Role role) {
        if (findAll().stream().anyMatch(r -> r.getName().equals(role.getName()))) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, String.format("Role with id %s already exists", role.getId()));
        }
        return repository.save(role);
    }

    public Role update(Role role, UUID id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Role with id %s not found", id));
        }
        return repository.save(role);
    }

    public String delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Role with id %s not found", id));
        }
        repository.deleteById(id);
        return String.format("Role with id %s deleted successfully", id);
    }
}
