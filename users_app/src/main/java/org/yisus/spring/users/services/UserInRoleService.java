package org.yisus.spring.users.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.yisus.spring.users.entities.Role;
import org.yisus.spring.users.entities.User;
import org.yisus.spring.users.entities.UserInRole;
import org.yisus.spring.users.repositories.RoleRepository;
import org.yisus.spring.users.repositories.UserInRoleRepository;
import org.yisus.spring.users.repositories.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserInRoleService {
    @Autowired
    private UserInRoleRepository userInRoleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    public UserInRole save(UUID userId,UUID roleId){
        Optional<UserInRole> userInRoleExist = userInRoleRepository.findByUserIdAndRoleId(userId, roleId);
        if(userInRoleExist.isPresent()){
            throw new ResponseStatusException(HttpStatus.CONFLICT,String.format("User %s has Role %s",userId,roleId));
        }
        Optional<User> user = userRepository.findById(userId);
        Optional<Role> role = roleRepository.findById(roleId);
        if(user.isPresent() && role.isPresent()) {
            UserInRole userInRole=new UserInRole();
            userInRole.setUser(user.get());
            userInRole.setRole(role.get());
            return userInRoleRepository.save(userInRole);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("User %s or Role %s not found",userId,roleId));
    }


    public Page<UserInRole> findAll(List<String> roles,Integer page,Integer size){
        if(roles!=null && !roles.isEmpty()){
            return userInRoleRepository.findAllByRoles(roles,PageRequest.of(page,size));
        }
        return userInRoleRepository.findAll(PageRequest.of(page,size));
    }

    public UserInRole findByUserIdAndRoleId(UUID userId, UUID roleId){
        return userInRoleRepository.findByUserIdAndRoleId(userId,roleId).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("User %s does not have role %s",userId,roleId)));
    }

    public Page<Role> findRolesByUserId(UUID userId,Integer page,Integer size){
        return userInRoleRepository.findRolesByUserId(userId, PageRequest.of(page,size));
    }


    public String delete(UUID userId,UUID roleId){
        Optional<UserInRole> userInRole = userInRoleRepository.findByUserIdAndRoleId(userId, roleId);
        if(userInRole.isPresent()){
            userInRoleRepository.delete(userInRole.get());
            return "User %s is not "+ userInRole.get().getRole().getName();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("User %s or Role %s not found",userId,roleId));
    }
}
