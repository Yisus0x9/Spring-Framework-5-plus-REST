package org.yisus.spring.users.services;

import com.github.javafaker.Faker;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yisus.spring.users.entities.User;
import org.yisus.spring.users.repositories.UserRepository;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private Faker faker;

    @PostConstruct
    public void init(){
        for (int i = 0; i < 50; i++) {
            User user = new User();
            user.setName(faker.name().firstName());
            user.setNickname(faker.funnyName().name());
            user.setEmail(faker.internet().emailAddress());
            user.setPassword(String.format("password%s", i));
            userRepository.save(user);
        }
    }

    public User save(User user) {
        return userRepository.save(user);
    }
    public User findById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User with id " + id + " does not exist."));
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User update(User user,UUID id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("User with id " + id + " does not exist.");
        }
        return userRepository.save(user);
    }

    public String delete(UUID id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("User with id " + id + " does not exist.");
        }
        userRepository.deleteById(id);
        return "User with id " + id + " deleted successfully.";
    }
}
