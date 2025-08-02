package org.yisus.spring.users.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.yisus.spring.users.DTOs.AuditDetails;
import org.yisus.spring.users.anotations.AccessControlAuthorization;
import org.yisus.spring.users.entities.Role;
import org.yisus.spring.users.repositories.RoleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class RoleService {
    @Autowired
    private  RoleRepository repository;
    @Autowired
    private KafkaTemplate<Integer,String> kafkaTemplate;
    @Autowired
    private ObjectMapper mapper;

    @PostConstruct
    public void init(){
       List<String> roles = List.of("ADMIN", "USER", "GUEST", "MODERATOR", "SUPER_ADMIN");
       roles.forEach(role -> {
              Role r = new Role();
              r.setName(role);
                repository.save(r);
       });
    }

    //@Secured("ROLE_SUPER_ADMIN") Anotación propia de Spring
    @RolesAllowed(value = {"SUPER_ADMIN","ADMIN"}) // Anotación de Jakarta
    public List<Role> findAll() {
        return repository.findAll();
    }

    public Role findById(UUID id) {
        return repository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Role with id %s not found", id)));
    }

    @AccessControlAuthorization
    public Role save(Role role) {
        if (findAll().stream().anyMatch(r -> r.getName().equals(role.getName()))) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, String.format("Role with id %s already exists", role.getId()));
        }
        Role roleSave=repository.save(role);
        AuditDetails auditDetails=new AuditDetails( SecurityContextHolder.getContext().getAuthentication().getName(),roleSave.getName());
        try {
            kafkaTemplate.send("yisus09",mapper.writeValueAsString(auditDetails));
        } catch (JsonProcessingException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Error parsed the message");
        }
        return roleSave;
    }

    @AccessControlAuthorization
    public Role update(Role role, UUID id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Role with id %s not found", id));
        }
        return repository.save(role);
    }

    @AccessControlAuthorization
    public String delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Role with id %s not found", id));
        }
        repository.deleteById(id);
        return String.format("Role with id %s deleted successfully", id);
    }
}
