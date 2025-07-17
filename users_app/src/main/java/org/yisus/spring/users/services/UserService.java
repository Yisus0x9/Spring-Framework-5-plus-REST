package org.yisus.spring.users.services;

import com.github.javafaker.Faker;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.yisus.spring.users.models.User;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private  Faker faker;
    @Autowired
    @Qualifier("list")
    private List<User> users;


    @PostConstruct
    public void init(){
        for (int i = 0; i < 100; i++) {
           users.add(new User(faker.name().name(),faker.funnyName().name(),
                   faker.internet().emailAddress(),faker.internet().password()));
        }
    }

    public User createUser(User user) throws ResponseStatusException {
        if(users.stream().anyMatch(u -> u.getEmail().equals(user.getEmail()))){
            throw  new ResponseStatusException(HttpStatus.CONFLICT,String.format("user whith email %s already exists", user.getEmail()));
        }
        users.add(user);
        return user;
    }

    public User getUserByName(String name){
        return users.stream().filter(user->user.getName().equals(name)).findFirst().orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("User with name %s not found", name)));
    }

    public User getUserByEmail(String email){
        return users.stream().filter(user->user.getEmail().equals(email)).findFirst().orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("User with email %s not found", email)));
    }

    public List<User> getUsers(String contains) {
        if (contains!=null) {
            return users.stream().filter(user -> user.getName().contains(contains))
                    .toList();
        }
        return users;
    }

    public User updateUser(User user, String email) throws ResponseStatusException {
        User existingUser = getUserByEmail(email);
        existingUser.setName(user.getName());
        existingUser.setNickname(user.getNickname());
        existingUser.setPassword(user.getPassword());
        return existingUser;
    }

    public String deleteUser(String email) throws ResponseStatusException {
        users.remove(getUserByEmail(email));
        return "User with email " + email + " deleted";
    }
}
