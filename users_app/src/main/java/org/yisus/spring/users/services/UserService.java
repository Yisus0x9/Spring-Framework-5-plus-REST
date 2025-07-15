package org.yisus.spring.users.services;

import com.github.javafaker.Faker;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
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

    public List<User> getUsers() {
        return users;
    }

}
